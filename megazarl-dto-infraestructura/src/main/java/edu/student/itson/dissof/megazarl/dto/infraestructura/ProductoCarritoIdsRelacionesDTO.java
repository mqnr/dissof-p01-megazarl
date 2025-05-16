
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class ProductoCarritoIdsRelacionesDTO extends ProductoCarritoDTO{


    private IdCarritoComprasDTO idCarritoCompras;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private IdProductoDTO idProducto;

    public ProductoCarritoIdsRelacionesDTO(
            Long id, 
            Integer cantidad,
            IdCarritoComprasDTO idCarritoCompras,
            IdProductoDTO idProducto) {
        
        super(
            id,
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }
    
    public ProductoCarritoIdsRelacionesDTO(
            Integer cantidad,
            IdCarritoComprasDTO idCarritoCompras,
            IdProductoDTO idProducto) {
        
        super(
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }


    @Override
    public IdCarritoComprasDTO getIdCarritoCompras() {
        return idCarritoCompras;
    }

    @Override
    public IdProductoDTO getIdProducto() {
        return idProducto;
    }

    
}
