
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.List;


public class ProductoIdsRelacionesDTO extends ProductoDTO {
    

    private IdEntidadGenerico idProveedor;

    private List<IdEntidadGenerico> idsProductosInventario;

    private List<IdEntidadGenerico> idsProductosCarrito;
    
    private List<IdEntidadGenerico> idsProductosPedido;

    public ProductoIdsRelacionesDTO(
            IdEntidadGenerico id, 
            String nombre, 
            String variedad, 
            String descripcion, 
            Integer milesSemillas, 
            Double precio, 
            Double pesoKg, 
            String direccionImagen,
            IdEntidadGenerico idProveedor,
            List<IdEntidadGenerico> idsProductosInventario,
            List<IdEntidadGenerico> idsProductosCarrito,
            List<IdEntidadGenerico> idsProductosPedido) {
        
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
    public IdEntidadGenerico getIdProveedor() {
        return idProveedor;
    }
    
    @Override
    public List<IdEntidadGenerico> getIdsProductosInventario() {
        return idsProductosInventario;
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosPedido() {
        return idsProductosPedido;
    }

}
