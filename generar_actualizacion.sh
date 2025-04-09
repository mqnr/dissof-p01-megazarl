#!/bin/sh

if [ $# -lt 2 ]; then
  echo "Uso: $0 archivo_dto directorio_salida"
  exit 1
fi

input_file="$1"
output_dir="$2"
# Eliminar slash final si existe
output_dir=$(echo "$output_dir" | sed 's/\/$//')
mkdir -p "$output_dir"

# Determinar el paquete basado en la ruta de salida
package_path=$(echo "$output_dir" | sed -n 's/.*src\/main\/java\/\(.*\)/\1/p')
package_name=$(echo "$package_path" | tr '/' '.')

# Extraer nombre de la clase
class_name=$(grep -m 1 "public record" "$input_file" | sed 's/.*public\s\+record\s\+\([A-Za-z0-9_]\+\).*/\1/')
update_class_name="Actualizacion${class_name%DTO}"

# Crear archivo temporal para el script awk
awk_script_file=$(mktemp)
cat > "$awk_script_file" << 'EOF'
BEGIN { in_record=0; in_params=0; }
/public record/ { in_record=1; }
in_record && /\(/ { in_params=1; next; }
in_params && /\)[^,]*\{/ {
    # Capturar el último campo si está en la misma línea que el paréntesis
    if (match($0, /([A-Za-z0-9_<>]+ [A-Za-z0-9_]+)\)/, arr)) {
        print arr[1];
    }
    in_params=0;
    exit;
}
in_params && /\)/ { in_params=0; exit; }
in_params { print; }
EOF

# Extraer los campos usando el script awk
fields_content=$(awk -f "$awk_script_file" "$input_file")
rm "$awk_script_file"

echo "$fields_content" > temp_record.txt

# Procesar líneas para buscar atributos y anotaciones
exclude_next=0
fields=""

while IFS= read -r line; do
  # Saltarse líneas vacías o sólo espacios
  if echo "$line" | grep -q "^[[:space:]]*$"; then
    continue
  fi

  # Si la línea contiene [GEN] excluir-atributo, marcar para excluir el próximo campo
  if echo "$line" | grep -q "\[GEN\] excluir-atributo"; then
    exclude_next=1
    continue
  fi

  # Extraer el tipo y nombre del campo
  sanitized_line=$(echo "$line" | sed 's/,$//' | sed 's/^[[:space:]]*//')
  if [ -n "$sanitized_line" ]; then
    # Si no está marcado para excluir, agregarlo a la lista
    if [ $exclude_next -eq 0 ]; then
      fields="${fields}${sanitized_line}\n"
    fi

    # Reiniciar el flag de exclusión
    exclude_next=0
  fi
done < temp_record.txt
rm temp_record.txt

# Crear archivo de salida
output_file="$output_dir/${update_class_name}.java"

# Generar la clase
cat > "$output_file" <<EOF
package $package_name;

public class $update_class_name {
EOF

# Agregar declaraciones de campos
printf "%b" "$fields" | while IFS= read -r line; do
  [ -z "$line" ] && continue
  type=$(echo "$line" | cut -d ' ' -f 1)
  name=$(echo "$line" | cut -d ' ' -f 2)
  echo "    private $type $name;" >> "$output_file"
  echo "    private boolean ${name}E;" >> "$output_file"
done

# Agregar métodos fluidos
printf "%b" "$fields" | while IFS= read -r line; do
  [ -z "$line" ] && continue
  type=$(echo "$line" | cut -d ' ' -f 1)
  name=$(echo "$line" | cut -d ' ' -f 2)
  cat >> "$output_file" <<EOF

    public $update_class_name $name($type $name) {
        this.$name = $name;
        ${name}E = true;
        return this;
    }
EOF
done

# Agregar getters
echo "" >> "$output_file"
printf "%b" "$fields" | while IFS= read -r line; do
  [ -z "$line" ] && continue
  type=$(echo "$line" | cut -d ' ' -f 1)
  name=$(echo "$line" | cut -d ' ' -f 2)
  echo "    public $type get$(echo "$name" | sed 's/\b\(.\)/\u\1/g')() { return $name; }" >> "$output_file"
done

# Agregar métodos de verificación
echo "" >> "$output_file"
printf "%b" "$fields" | while IFS= read -r line; do
  [ -z "$line" ] && continue
  name=$(echo "$line" | cut -d ' ' -f 2)
  echo "    public boolean tiene$(echo "$name" | sed 's/\b\(.\)/\u\1/g')() { return ${name}E; }" >> "$output_file"
done

# Cerrar la clase
echo "}" >> "$output_file"

echo "Generado $update_class_name en $output_file"
