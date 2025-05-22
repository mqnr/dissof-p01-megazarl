
package edu.student.itson.dissof.megazarl.dto.negocios;

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
    public List<Long> getIdsProductosInventario() {
        List<Long> idsProductosInventario = new LinkedList<>();
        
        for(ProductoInventarioDTO productoInventario: productosInventario){
            idsProductosInventario.add(productoInventario.getId());
        }
        return idsProductosInventario;
    }

    @Override
    public List<Long> getIdsProductosCarrito() {
        List<Long> idsProductosCarrito = new LinkedList<>();
        
        for(Long idProductoCarrito: idsProductosCarrito){
            
            idsProductosCarrito.add(idProductoCarrito);
        }
        
        return idsProductosCarrito;
    }

    @Override
    public List<Long> getIdsProductosPedido() {
        List<Long> idsProductosPedido = new LinkedList<>();
        
        for(Long idProductoPedido: idsProductosPedido){
            
            idsProductosPedido.add(idProductoPedido);
        }
        
        return idsProductosPedido;
    }

    @Override
    public Long getIdProveedor() {
        return proveedor.getId();
    }
    
}
