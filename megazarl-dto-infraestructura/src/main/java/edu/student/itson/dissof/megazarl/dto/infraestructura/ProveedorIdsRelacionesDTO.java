
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.List;


public class ProveedorIdsRelacionesDTO extends ProveedorDTO{
    
    private List<IdProductoDTO> idsProductosOfrecidos;
    
    private IdDireccionDTO idDireccion;
    
    public ProveedorIdsRelacionesDTO(
            Long id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdProductoDTO> idsProductosOfrecidos, 
            IdDireccionDTO idDireccion) {
        
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
            List<IdProductoDTO> idsProductosOfrecidos, 
            IdDireccionDTO idDireccion) {
        
        super(
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.idDireccion = idDireccion;
    }

    @Override
    public List<IdProductoDTO> getListaIdsProductosOfrecidos() {
        
        return idsProductosOfrecidos;
        
    }

    @Override
    public IdDireccionDTO getIdDireccion() {
        return idDireccion;
    }
    
}
