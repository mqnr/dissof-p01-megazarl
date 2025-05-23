
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class ProductoCarritoDatosCompletosRelacionesDTONegocios extends ProductoCarritoDTONegocios {

    /**
     * Objeto CarritoComprasDTONegocios que representa el carrito compras al que pertenece el
 producto.
     */
    private CarritoComprasDTONegocios carritoCompras;
    
    /**
     * Objeto ProductoDTONegocios que representa el producto dentro del carrito.
     */
    private ProductoDTONegocios producto;

    public ProductoCarritoDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            Integer cantidad,
            CarritoComprasDTONegocios carritoCompras,
            ProductoDTONegocios producto) {
        
        super(
            id,
            cantidad);

        this.carritoCompras = carritoCompras;
        this.producto = producto;

    }
    
    public ProductoCarritoDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            Integer cantidad,
            ProductoDTONegocios producto) {
        
        super(
            id,
            cantidad);

        this.producto = producto;

    }
    
    public ProductoCarritoDatosCompletosRelacionesDTONegocios(
            Integer cantidad,
            CarritoComprasDTONegocios carritoCompras,
            ProductoDTONegocios producto) {
        
        super(
            cantidad);

        this.carritoCompras = carritoCompras;
        this.producto = producto;

    }


    public CarritoComprasDTONegocios getCarritoCompras(){
        return carritoCompras;
    }
    
    public ProductoDTONegocios getProducto(){
        return producto;
    }
    
    @Override
    public IdEntidadGenericoNegocios getIdCarritoCompras() {
        return carritoCompras.getId();
    }

    @Override
    public IdEntidadGenericoNegocios getIdProducto() {
        return producto.getId();
    }

}
