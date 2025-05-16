
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.List;


public class ProductoIdsRelacionesDTO extends ProductoDTO {
    

    private IdProveedorDTO idProveedor;

    private List<IdProductoInventarioDTO> idsProductosInventario;

    private List<IdProductoCarritoDTO> idsProductosCarrito;
    
    private List<IdProductoPedidoDTO> idsProductosPedido;

    public ProductoIdsRelacionesDTO(
            Long id, 
            String nombre, 
            String variedad, 
            String descripcion, 
            Integer milesSemillas, 
            Double precio, 
            Double pesoKg, 
            String direccionImagen,
            IdProveedorDTO idProveedor,
            List<IdProductoInventarioDTO> idsProductosInventario,
            List<IdProductoCarritoDTO> idsProductosCarrito,
            List<IdProductoPedidoDTO> idsProductosPedido) {
        
        super(
            id, 
            nombre, 
            variedad, 
            descripcion, 
            milesSemillas,
            precio, 
            pesoKg,
            direccionImagen);
        
        this.idProveedor = idProveedor;
        this.idsProductosCarrito = idsProductosCarrito;
        this.idsProductosInventario = idsProductosInventario;
        this.idsProductosPedido = idsProductosPedido;
        
    }

    @Override
    public IdProveedorDTO getIdProveedor() {
        return idProveedor;
    }
    
    @Override
    public List<IdProductoInventarioDTO> getIdsProductosInventario() {
        return idsProductosInventario;
    }

    @Override
    public List<IdProductoCarritoDTO> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }

    @Override
    public List<IdProductoPedidoDTO> getIdsProductosPedido() {
        return idsProductosPedido;
    }

}
