
package edu.student.itson.dissof.dto.datos;


public abstract class ProductoPedidoDTO {
    
    protected Long id;
    
    protected Integer cantidadRequerida; 

    public ProductoPedidoDTO(
            Long id,
            Integer cantidadRequerida) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
    }
    
    public ProductoPedidoDTO(
            Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public abstract Long getIdProducto();
    
    public abstract Long getIdPedido();

    

    
}
