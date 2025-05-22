package edu.student.itson.dissof.dto.datos.enumeradores;
/**
 * EstadoPedido.java
 *
 * Enumeración que representa los posibles estados de un pedido en el sistema,
 * permitiendo realizar seguimiento del mismo desde que se crea hasta que es entregado.
 * Dentro del subsistema administradorSQL.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public enum EstadoPedido {
    PENDIENTE,
    ENVIADO,
    RECIBIDO
}