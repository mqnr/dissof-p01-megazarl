#!/bin/sh
# generar_todas_actualizaciones.sh - Genera todas las clases de actualización

# Definir directorios
DTO_DIR="megazarl-dto/src/main/java/edu/student/itson/dissof/megazarl/dto"
OUTPUT_DIR="megazarl-repositorio/src/main/java/edu/student/itson/dissof/megazarl/repositorio/actualizaciones"

# Asegurarse que existe el directorio de salida
mkdir -p "$OUTPUT_DIR"

# Encontrar todos los archivos DTO marcados con "[GEN] generar-actualizacion"
echo 'Buscando clases DTO marcadas con "[GEN] generar-actualizacion"...'
find "$DTO_DIR" -type f -name "*.java" | while read -r file; do
  if grep -q "\[GEN\] generar-actualizacion" "$file"; then
    echo "Procesando: $file"
    sh generar_actualizacion.sh "$file" "$OUTPUT_DIR"
  fi
done

echo "Proceso completado. Las clases de actualización han sido generadas en $OUTPUT_DIR"
