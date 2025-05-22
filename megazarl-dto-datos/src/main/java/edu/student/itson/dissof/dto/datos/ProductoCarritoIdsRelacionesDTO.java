
package edu.student.itson.dissof.dto.datos;


public class ProductoCarritoIdsRelacionesDTO extends ProductoCarritoDTO{


    private Long idCarritoCompras;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private Long idProducto;

    public ProductoCarritoIdsRelacionesDTO(
            Long id, 
            Integer cantidad,
            Long idCarritoCompras,
            Long idProducto) {
        
        super(
            id,
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }
    
    public ProductoCarritoIdsRelacionesDTO(
            Integer cantidad,
            Long idCarritoCompras,
            Long idProducto) {
        
        super(
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }


    @Override
    public Long getIdCarritoCompras() {
        return idCarritoCompras;
    }

    @Override
    public Long getIdProducto() {
        return idProducto;
    }

    
}
