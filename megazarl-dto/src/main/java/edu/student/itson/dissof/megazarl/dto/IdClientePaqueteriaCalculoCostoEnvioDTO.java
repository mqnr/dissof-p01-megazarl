package edu.student.itson.dissof.megazarl.dto;

/**
 * IdClientePaqueteriaCalculoCostoEnvioDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * los identificadores del cliente y la paquetería seleccionada, necesarios
 * para realizar el cálculo del costo de envío de un pedido.
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
public class IdClientePaqueteriaCalculoCostoEnvioDTO {
    private Integer idCliente;
    private Integer idPaqueteria;

    public IdClientePaqueteriaCalculoCostoEnvioDTO(Integer idCliente, Integer idPaqueteria) {
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdPaqueteria() {
        return idPaqueteria;
    }
}
