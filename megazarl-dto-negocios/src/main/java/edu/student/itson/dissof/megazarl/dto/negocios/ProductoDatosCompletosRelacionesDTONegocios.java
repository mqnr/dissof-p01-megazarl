
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class ProductoDatosCompletosRelacionesDTONegocios extends ProductoDTONegocios{

    private ProveedorDTONegocios proveedor;

    private List<ProductoInventarioDTONegocios> productosInventario;

    private List<ProductoCarritoDTONegocios> productosCarrito;
    
    private List<ProductoPedidoDTONegocios> productosPedido;

    public ProductoDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            ProveedorDTONegocios proveedor,
            List<ProductoInventarioDTONegocios> productosInventario,
            List<ProductoCarritoDTONegocios> productosCarrito,
            List<ProductoPedidoDTONegocios> productosPedido) {
        
        super(
            id, 
            nombre,
            variedad,
            descripcion,
            milesSemillas,
            precio,
            pesoKg,
            direccionImagen);
        
        this.proveedor = proveedor;
        this.productosCarrito = productosCarrito;
        this.productosInventario = productosInventario;
        this.productosPedido = productosPedido;

    }
    
    public ProductoDatosCompletosRelacionesDTONegocios(
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            ProveedorDTONegocios proveedor,
            List<ProductoInventarioDTONegocios> productosInventario,
            List<ProductoCarritoDTONegocios> productosCarrito,
            List<ProductoPedidoDTONegocios> productosPedido) {
        
        super(
            nombre,
            variedad,
            descripcion,
            milesSemillas,
            precio,
            pesoKg,
            direccionImagen);
        
        this.proveedor = proveedor;
        this.productosCarrito = productosCarrito;
        this.productosInventario = productosInventario;
        this.productosPedido = productosPedido;

    }
    
    public List<ProductoInventarioDTONegocios> getProductosInventario(){
        return productosInventario;
    }
    
    public List<ProductoCarritoDTONegocios> getProductosCarrito(){
        return productosCarrito;
    }
    
    public List<ProductoPedidoDTONegocios> getProductosPedido(){
        return productosPedido;
    }
    
    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosInventario() {
        List<IdEntidadGenericoNegocios> idsProductosInventario = new LinkedList<>();
        
        for(ProductoInventarioDTONegocios productoInventario: productosInventario){
            idsProductosInventario.add(productoInventario.getId());
        }
        return idsProductosInventario;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosCarrito() {
        List<IdEntidadGenericoNegocios> idsProductosCarrito = new LinkedList<>();
        
        for(IdEntidadGenericoNegocios idProductoCarrito: idsProductosCarrito){
            
            idsProductosCarrito.add(idProductoCarrito);
        }
        
        return idsProductosCarrito;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosPedido() {
        List<IdEntidadGenericoNegocios> idsProductosPedido = new LinkedList<>();
        
        for(IdEntidadGenericoNegocios idProductoPedido: idsProductosPedido){
            
            idsProductosPedido.add(idProductoPedido);
        }
        
        return idsProductosPedido;
    }

    @Override
    public IdEntidadGenericoNegocios getIdProveedor() {
        return proveedor.getId();
    }
    
}
