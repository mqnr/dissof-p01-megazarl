
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.LinkedList;
import java.util.List;


public class ProductoDatosCompletosRelacionesDTO extends ProductoDTO{

    private ProveedorDTO proveedor;

    private List<ProductoInventarioDTO> productosInventario;

    private List<ProductoCarritoDTO> productosCarrito;
    
    private List<ProductoPedidoDTO> productosPedido;

    public ProductoDatosCompletosRelacionesDTO(
            Long id, 
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            ProveedorDTO proveedor,
            List<ProductoInventarioDTO> productosInventario,
            List<ProductoCarritoDTO> productosCarrito,
            List<ProductoPedidoDTO> productosPedido) {
        
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
    
    public ProductoDatosCompletosRelacionesDTO(
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            ProveedorDTO proveedor,
            List<ProductoInventarioDTO> productosInventario,
            List<ProductoCarritoDTO> productosCarrito,
            List<ProductoPedidoDTO> productosPedido) {
        
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
    
    public List<ProductoInventarioDTO> getProductosInventario(){
        return productosInventario;
    }
    
    public List<ProductoCarritoDTO> getProductosCarrito(){
        return productosCarrito;
    }
    
    public List<ProductoPedidoDTO> getProductosPedido(){
        return productosPedido;
    }
    
    @Override
    public List<IdProductoInventarioDTO> getIdsProductosInventario() {
        List<IdProductoInventarioDTO> idsProductosInventario = new LinkedList<>();
        
        for(ProductoInventarioDTO productoInventario: productosInventario){
            idsProductosInventario.add(new IdProductoInventarioDTO(productoInventario.getId()));
        }
        return idsProductosInventario;
    }

    @Override
    public List<IdProductoCarritoDTO> getIdsProductosCarrito() {
        List<IdProductoCarritoDTO> idsProductosCarrito = new LinkedList<>();
        
        for(IdProductoCarritoDTO idProductoCarrito: idsProductosCarrito){
            
            idsProductosCarrito.add(idProductoCarrito);
        }
        
        return idsProductosCarrito;
    }

    @Override
    public List<IdProductoPedidoDTO> getIdsProductosPedido() {
        List<IdProductoPedidoDTO> idsProductosPedido = new LinkedList<>();
        
        for(IdProductoPedidoDTO idProductoPedido: idsProductosPedido){
            
            idsProductosPedido.add(idProductoPedido);
        }
        
        return idsProductosPedido;
    }

    @Override
    public IdProveedorDTO getIdProveedor() {
        return new IdProveedorDTO(proveedor.getId());
    }
    
}
