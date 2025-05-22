
package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.List;


public class ProveedorIdsRelacionesDTO extends ProveedorDTO{
    
    private List<Long> idsProductosOfrecidos;
    
    private Long idDireccion;
    
    public ProveedorIdsRelacionesDTO(
            Long id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<Long> idsProductosOfrecidos, 
            Long idDireccion) {
        
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
            List<Long> idsProductosOfrecidos, 
            Long idDireccion) {
        
        super(
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.idDireccion = idDireccion;
    }

    @Override
    public List<Long> getListaIdsProductosOfrecidos() {
        
        return idsProductosOfrecidos;
        
    }

    @Override
    public Long getIdDireccion() {
        return idDireccion;
    }
    
}
