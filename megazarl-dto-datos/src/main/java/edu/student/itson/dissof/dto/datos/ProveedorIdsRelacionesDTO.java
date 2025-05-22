
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import java.util.List;


public class ProveedorIdsRelacionesDTO extends ProveedorDTO{
    
    private List<IdEntidadGenerico> idsProductosOfrecidos;
    
    private IdEntidadGenerico idDireccion;
    
    public ProveedorIdsRelacionesDTO(
            IdEntidadGenerico id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdEntidadGenerico> idsProductosOfrecidos, 
            IdEntidadGenerico idDireccion) {
        
        super(
            id, 
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.idDireccion = idDireccion;
    }
    
    public ProveedorIdsRelacionesDTO(
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdEntidadGenerico> idsProductosOfrecidos, 
            IdEntidadGenerico idDireccion) {
        
        super(
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.idDireccion = idDireccion;
    }

    @Override
    public List<IdEntidadGenerico> getListaIdsProductosOfrecidos() {
        
        return idsProductosOfrecidos;
        
    }

    @Override
    public IdEntidadGenerico getIdDireccion() {
        return idDireccion;
    }
    
}
