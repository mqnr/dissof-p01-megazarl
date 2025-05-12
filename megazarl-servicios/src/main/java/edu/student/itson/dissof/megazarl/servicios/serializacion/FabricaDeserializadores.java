package edu.student.itson.dissof.megazarl.servicios.serializacion;

public class FabricaDeserializadores {
    public static IDeserializador crear(String formato) {
        return switch (formato.toLowerCase()) {
            case "json" -> new DeserializadorJson();
            case "xml" -> throw new UnsupportedOperationException("XML no implementado");
            default -> throw new IllegalArgumentException("Formato no reconocido: " + formato);
        };
    }
}
