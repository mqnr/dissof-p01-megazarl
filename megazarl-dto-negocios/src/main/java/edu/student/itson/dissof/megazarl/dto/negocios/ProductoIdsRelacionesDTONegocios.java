
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;


public class ProductoIdsRelacionesDTONegocios extends ProductoDTONegocios {
    

    private IdEntidadGenericoNegocios idProveedor;

    private List<IdEntidadGenericoNegocios> idsProductosInventario;

    private List<IdEntidadGenericoNegocios> idsProductosCarrito;
    
    private List<IdEntidadGenericoNegocios> idsProductosPedido;

    public ProductoIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            String nombre, 
            String variedad, 
            String descripcion, 
            Integer milesSemillas, 
            Double precio, 
            Double pesoKg, 
            String direccionImagen,
            IdEntidadGenericoNegocios idProveedor,
            List<IdEntidadGenericoNegocios> idsProductosInventario,
            List<IdEntidadGenericoNegocios> idsProductosCarrito,
            List<IdEntidadGenericoNegocios> idsProductosPedido) {
        
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
    public IdEntidadGenericoNegocios getIdProveedor() {
        return idProveedor;
    }
    
    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosInventario() {
        return idsProductosInventario;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosPedido() {
        return idsProductosPedido;
    }

}
