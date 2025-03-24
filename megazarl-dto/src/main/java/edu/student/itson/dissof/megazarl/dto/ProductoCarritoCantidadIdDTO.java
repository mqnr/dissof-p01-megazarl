
package edu.student.itson.dissof.megazarl.dto;

import java.util.List;


public class ProductoCarritoCantidadIdDTO {
    private List<Integer> codigosProductos;

    public ProductoCarritoCantidadIdDTO(List<Integer> codigosProductos) {
        this.codigosProductos = codigosProductos;
    }

    public List<Integer> getCodigosProductos() {
        return codigosProductos;
    }

   
    
    
}
