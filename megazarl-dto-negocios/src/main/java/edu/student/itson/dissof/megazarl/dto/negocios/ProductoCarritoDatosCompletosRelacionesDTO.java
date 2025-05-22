
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;


public class ProductoCarritoDatosCompletosRelacionesDTO extends ProductoCarritoDTO {

    /**
     * Objeto CarritoComprasDTO que representa el carrito compras al que pertenece el
     * producto.
     */
    private CarritoComprasDTO carritoCompras;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private ProductoDTO producto;

    public ProductoCarritoDatosCompletosRelacionesDTO(
            IdEntidadGenerico id, 
            Integer cantidad,
            CarritoComprasDTO carritoCompras,
            ProductoDTO producto) {
        
        super(
            id,
            cantidad);

        this.carritoCompras = carritoCompras;
        this.producto = producto;

    }
    
    public ProductoCarritoDatosCompletosRelacionesDTO(
            IdEntidadGenerico id, 
            Integer cantidad,
            ProductoDTO producto) {
        
        super(
            id,
            cantidad);

        this.producto = producto;

    }
    
    public ProductoCarritoDatosCompletosRelacionesDTO(
            Integer cantidad,
            CarritoComprasDTO carritoCompras,
            ProductoDTO producto) {
        
        super(
            cantidad);

        this.carritoCompras = carritoCompras;
        this.producto = producto;

    }


    public CarritoComprasDTO getCarritoCompras(){
        return carritoCompras;
    }
    
    public ProductoDTO getProducto(){
        return producto;
    }
    
    @Override
    public IdEntidadGenerico getIdCarritoCompras() {
        return carritoCompras.getId();
    }

    @Override
    public IdEntidadGenerico getIdProducto() {
        return producto.getId();
    }

}
