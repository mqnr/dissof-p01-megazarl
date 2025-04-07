package edu.student.itson.dissof.megazarl.configuracion.ini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserIni {
    // Sección: [nombre] y nada más (excepto espacios en blanco)
    private static final Pattern PATRON_SECCIONES = Pattern.compile("^\\s*\\[\\s*([^]]+)\\s*]\\s*$");
    // Clave-valor: clave = valor
    private static final Pattern PATRON_CLAVE_VALOR = Pattern.compile("^\\s*([^=]+)\\s*=\\s*(.*)$");
    // Comentario: línea que comienza con # o ;
    private static final Pattern PATRON_COMENTARIO = Pattern.compile("^\\s*[#;].*$");

    public static ConfiguracionIni parseArchivo(String rutaArchivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            return parseReader(reader);
        }
    }

    public static ConfiguracionIni parseCadena(String contenido) throws IOException {
        try (BufferedReader reader = new BufferedReader(new StringReader(contenido))) {
            return parseReader(reader);
        }
    }

    private static ConfiguracionIni parseReader(BufferedReader reader) throws IOException {
        Map<String, Map<String, String>> secciones = new HashMap<>();
        String linea;
        String seccionActual = null;
        Map<String, String> mapaSeccionActual = null;
        int numeroLinea = 0;

        while ((linea = reader.readLine()) != null) {
            numeroLinea++;
            linea = linea.trim();

            // Saltarse líneas vacías
            if (linea.isEmpty()) {
                continue;
            }

            // Saltarse líneas de comentarios
            if (PATRON_COMENTARIO.matcher(linea).matches()) {
                continue;
            }

            // Comprobar si es una sección
            Matcher matcherSecciones = PATRON_SECCIONES.matcher(linea);
            if (matcherSecciones.matches()) {
                seccionActual = matcherSecciones.group(1).trim().toLowerCase();
                mapaSeccionActual = secciones.computeIfAbsent(seccionActual, c -> new HashMap<>());
                continue;
            }

            // Comprobar si es un par clave-valor
            Matcher matcherClaveValor = PATRON_CLAVE_VALOR.matcher(linea);
            if (matcherClaveValor.matches()) {
                if (seccionActual == null) {
                    // Ignorar claves fuera de secciones
                    continue;
                }

                String clave = matcherClaveValor.group(1).trim().toLowerCase();
                String valor = matcherClaveValor.group(2).trim();
                mapaSeccionActual.put(clave, valor);
                continue;
            }

            // Si llegamos aquí, la línea no tiene un formato válido
            throw new IllegalArgumentException(
                    "Formato INI incorrecto en la línea " + numeroLinea + ": " + linea);
        }

        return new ConfiguracionIni(secciones);
    }
}
