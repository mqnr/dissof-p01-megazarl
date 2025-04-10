package edu.student.itson.dissof.megazarl.configuracion;

public record ConfiguracionFuentes(
        FuenteDatos cliente,
        FuenteDatos paqueteria
) {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fuente cliente: ").append(cliente);
        return builder.toString();
    }
}
