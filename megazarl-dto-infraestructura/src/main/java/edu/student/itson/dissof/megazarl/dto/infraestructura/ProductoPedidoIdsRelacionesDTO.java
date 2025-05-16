
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class ProductoPedidoIdsRelacionesDTO extends ProductoPedidoDTO{

    private Long idProducto;
    
    private Long idPedido;

    public ProductoPedidoIdsRelacionesDTO(
            Long id,
            Integer cantidadRequerida,
            Integer cantidadCompleta,
            Long idProducto,
            Long idPedido) {
        super(
            id, 
            cantidadRequerida,
            cantidadCompleta);
        
        this.idProducto = idProducto;     
        this.idPedido = idPedido;
    }
    
    
    @Override
    public Long getIdProducto() {
        return idProducto;
    }

    @Override
    public Long getIdPedido() {
        return idPedido;
    }

    
}
