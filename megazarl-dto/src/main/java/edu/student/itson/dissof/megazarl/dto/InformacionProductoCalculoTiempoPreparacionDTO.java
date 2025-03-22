
package edu.student.itson.dissof.megazarl.dto;

public class InformacionProductoCalculoTiempoPreparacionDTO {
    private Integer idProducto;
    private Integer cantidad;

    public InformacionProductoCalculoTiempoPreparacionDTO(Integer idProducto, Integer cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
