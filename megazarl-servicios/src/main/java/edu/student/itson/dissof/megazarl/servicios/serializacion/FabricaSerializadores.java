package edu.student.itson.dissof.megazarl.servicios.serializacion;

public class FabricaSerializadores {
    public static ISerializador crear(String formato) {
        return switch (formato.toLowerCase()) {
            case "json" -> new SerializadorJson();
            case "xml" -> throw new UnsupportedOperationException("XML no implementado");
            default -> throw new IllegalArgumentException("Formato no reconocido: " + formato);
        };
    }
}
