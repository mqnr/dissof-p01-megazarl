
package edu.student.itson.dissof.megazarl.dto;

import java.util.HashMap;


public class InformacionCrearPedidoDTO {
    
    private Integer idCliente;
    private Integer idPaqueteria;
    private HashMap<Integer, Integer> mapaIdsProductosCantidad;

    public InformacionCrearPedidoDTO(Integer idCliente, Integer idPaqueteria, HashMap<Integer, Integer> mapaIdsProductosCantidad) {
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.mapaIdsProductosCantidad = mapaIdsProductosCantidad;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdPaqueteria() {
        return idPaqueteria;
    }

    public HashMap<Integer, Integer> getMapaIdsProductosCantidad() {
        return mapaIdsProductosCantidad;
    }
    
    
}
