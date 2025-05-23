
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;


public class ProveedorIdsRelacionesDTONegocios extends ProveedorDTONegocios{
    
    private List<IdEntidadGenericoNegocios> idsProductosOfrecidos;
    
    private IdEntidadGenericoNegocios idDireccion;
    
    public ProveedorIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdEntidadGenericoNegocios> idsProductosOfrecidos, 
            IdEntidadGenericoNegocios idDireccion) {
        
        super(
            id, 
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.idDireccion = idDireccion;
    }
    
    public ProveedorIdsRelacionesDTONegocios(
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdEntidadGenericoNegocios> idsProductosOfrecidos, 
            IdEntidadGenericoNegocios idDireccion) {
        
        super(
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.idDireccion = idDireccion;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getListaIdsProductosOfrecidos() {
        
        return idsProductosOfrecidos;
        
    }

    @Override
    public IdEntidadGenericoNegocios getIdDireccion() {
        return idDireccion;
    }
    
}
