
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class ProveedorDatosCompletosRelacionesDTONegocios extends ProveedorDTONegocios {
    
    private List<ProductoDTONegocios> productosOfrecidos;
    
    private DireccionDTONegocios direccion;
    
    
    public ProveedorDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<ProductoDTONegocios> productosOfrecidos, 
            DireccionDTONegocios direccion) {
        
        super(
            id, 
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.productosOfrecidos = productosOfrecidos;
        this.direccion = direccion;
    }
    
    public ProveedorDatosCompletosRelacionesDTONegocios(
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<ProductoDTONegocios> productosOfrecidos, 
            DireccionDTONegocios direccion) {
        
        super(
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.productosOfrecidos = productosOfrecidos;
        this.direccion = direccion;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getListaIdsProductosOfrecidos() {
        
        List<IdEntidadGenericoNegocios> idsProductosOfrecidos = new LinkedList<>();
        
        for(ProductoDTONegocios productoDTO: productosOfrecidos){
            idsProductosOfrecidos.add(productoDTO.getId());
        }
        
        return idsProductosOfrecidos;
    }

    @Override
    public IdEntidadGenericoNegocios getIdDireccion() {
        return direccion.getId();
    }
    
}
