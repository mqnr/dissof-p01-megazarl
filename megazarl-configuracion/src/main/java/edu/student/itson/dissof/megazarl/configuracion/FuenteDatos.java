package edu.student.itson.dissof.megazarl.configuracion;

public enum FuenteDatos {
    MEMORIA,
    MOCK;
//    MYSQL;

    public static FuenteDatos de(String contenido) {
        return switch (contenido.toLowerCase()) {
            case "memoria", "en memoria", "inmemory", "in-memory", "in memory", "ram" -> MEMORIA;
            case "mock" -> MOCK;
//            case "sql", "mysql" -> MYSQL;
            default -> throw new IllegalArgumentException("Valor no esperado: " + contenido);
        };
    }
}
