
package edu.student.itson.dissof.dto.datos;


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
            Long id, 
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
            Long id, 
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
    public Long getIdCarritoCompras() {
        return carritoCompras.getId();
    }

    @Override
    public Long getIdProducto() {
        return producto.getId();
    }

}
