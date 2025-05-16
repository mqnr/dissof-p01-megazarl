
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public abstract class ProductoPedidoDTO {
    
    protected Long id;
    
    protected Integer cantidadRequerida;
    
    protected Integer cantidadCompleta;

    public ProductoPedidoDTO(
            Long id,
            Integer cantidadRequerida,
            Integer cantidadCompleta) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.cantidadCompleta = cantidadCompleta;
    }
    
    public ProductoPedidoDTO(
            Integer cantidadRequerida,
            Integer cantidadCompleta) {
        this.cantidadRequerida = cantidadRequerida;
        this.cantidadCompleta = cantidadCompleta;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }
    
    public Integer getCantidadCompleta() {
        return cantidadCompleta;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public abstract Long getIdProducto();
    
    public abstract Long getIdPedido();

    

    
}
