package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.HashMap;

/**
 * InformacionPedidoClienteDTO.java
 *
 * Clase DTO que contiene toda la información necesaria para calcular el costo total de un pedido,
 * incluyendo el cliente, la paquetería seleccionada y un mapa con los
 * productos y sus cantidades respectivas.
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
public class InformacionPedidoClienteDTO {
    
    Long idCliente;
    Long idPaqueteria;
    HashMap<Long, Integer> mapaProductosCantidades;

    public InformacionPedidoClienteDTO(
            Long idCliente, 
            Long idPaqueteria, 
            HashMap<Long, Integer> mapaProductosCantidades) {
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.mapaProductosCantidades = mapaProductosCantidades;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Long getIdPaqueteria() {
        return idPaqueteria;
    }

    public HashMap<Long, Integer> getMapaProductosCantidades() {
        return mapaProductosCantidades;
    }
}
