package edu.student.itson.dissof.megazarl.configuracion;

public record ConfiguracionFuentes(
        FuenteDatos fuenteCliente
) {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fuente cliente: ").append(fuenteCliente);
        return builder.toString();
    }
}
