
package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.List;


public class ProductoIdsRelacionesDTO extends ProductoDTO {
    

    private Long idProveedor;

    private List<Long> idsProductosInventario;

    private List<Long> idsProductosCarrito;
    
    private List<Long> idsProductosPedido;

    public ProductoIdsRelacionesDTO(
            Long id, 
            String nombre, 
            String variedad, 
            String descripcion, 
            Integer milesSemillas, 
            Double precio, 
            Double pesoKg, 
            String direccionImagen,
            Long idProveedor,
            List<Long> idsProductosInventario,
            List<Long> idsProductosCarrito,
            List<Long> idsProductosPedido) {
        
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
    public Long getIdProveedor() {
        return idProveedor;
    }
    
    @Override
    public List<Long> getIdsProductosInventario() {
        return idsProductosInventario;
    }

    @Override
    public List<Long> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }

    @Override
    public List<Long> getIdsProductosPedido() {
        return idsProductosPedido;
    }

}
