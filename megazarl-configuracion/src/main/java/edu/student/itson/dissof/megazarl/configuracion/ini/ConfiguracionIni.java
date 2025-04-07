package edu.student.itson.dissof.megazarl.configuracion.ini;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public record ConfiguracionIni(Map<String, Map<String, String>> secciones) {
    public ConfiguracionIni(Map<String, Map<String, String>> secciones) {
        // Crearemos una copia inmutable del mapa
        Map<String, Map<String, String>> seccionesInmutables = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entrada : secciones.entrySet()) {
            seccionesInmutables.put(entrada.getKey(), Map.copyOf(entrada.getValue()));
        }
        this.secciones = Collections.unmodifiableMap(seccionesInmutables);
    }

    public Map<String, String> obtenerSeccion(String nombreSeccion) {
        Map<String, String> seccion = secciones.get(nombreSeccion.toLowerCase());
        return seccion != null ? seccion : Collections.emptyMap();
    }

    public boolean tieneSeccion(String nombreSeccion) {
        return secciones.containsKey(nombreSeccion.toLowerCase());
    }

    public boolean tieneClave(String nombreSeccion, String clave) {
        String seccionNormalizada = nombreSeccion.toLowerCase();
        String claveNormalizada = clave.toLowerCase();
        Map<String, String> seccion = secciones.get(seccionNormalizada);
        return seccion != null && seccion.containsKey(claveNormalizada);
    }

    public String obtenerString(String nombreSeccion, String clave) {
        return obtenerString(nombreSeccion, clave, null);
    }

    public String obtenerString(String nombreSeccion, String clave, String valorPorDefecto) {
        String seccionNormalizada = nombreSeccion.toLowerCase();
        String claveNormalizada = clave.toLowerCase();
        Map<String, String> seccion = secciones.get(seccionNormalizada);
        if (seccion == null || !seccion.containsKey(claveNormalizada)) {
            return valorPorDefecto;
        }
        return seccion.get(claveNormalizada);
    }

    public Integer obtenerInt(String nombreSeccion, String clave) {
        return obtenerInt(nombreSeccion, clave, null);
    }

    public Integer obtenerInt(String nombreSeccion, String clave, Integer valorPorDefecto) {
        String valor = obtenerString(nombreSeccion, clave);
        if (valor != null) {
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                // Si obtuvimos una excepción, la ignoramos y regresamos el valor por defecto
            }
        }
        return valorPorDefecto;
    }

    public Double obtenerDouble(String nombreSeccion, String clave) {
        return obtenerDouble(nombreSeccion, clave, null);
    }

    public Double obtenerDouble(String nombreSeccion, String clave, Double valorPorDefecto) {
        String valor = obtenerString(nombreSeccion, clave);
        if (valor != null) {
            try {
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                // Si obtuvimos una excepción, la ignoramos y regresamos el valor por defecto
            }
        }
        return valorPorDefecto;
    }

    public Boolean obtenerBoolean(String nombreSeccion, String clave) {
        return obtenerBoolean(nombreSeccion, clave, null);
    }

    public Boolean obtenerBoolean(String nombreSeccion, String clave, Boolean valorPorDefecto) {
        String valor = obtenerString(nombreSeccion, clave);
        if (valor != null) {
            return switch (valor.toLowerCase()) {
                case "true", "yes", "verdadero", "si", "sí", "1" -> true;
                case "false", "no", "falso", "0" -> false;
                default -> valorPorDefecto;
            };
        }
        return valorPorDefecto;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Map<String, String>> seccion : secciones.entrySet()) {
            builder.append("[").append(seccion.getKey()).append("]\n");
            for (Map.Entry<String, String> entrada : seccion.getValue().entrySet()) {
                builder.append(entrada.getKey()).append(" = ").append(entrada.getValue()).append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
