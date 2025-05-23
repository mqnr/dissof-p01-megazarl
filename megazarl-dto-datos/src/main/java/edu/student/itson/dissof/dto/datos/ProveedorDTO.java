
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.List;


public class ProveedorDTO{
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID del proveedor.
     */
    private IdEntidadGenericoDatos id;

    /**
     * Objeto String que representa el nombre del proveedor.
     */
    private String nombre;

    /**
     * Objeto String que representa el teléfono del proveedor.
     */
    private String telefono;
    
    /**
     * Objeto String que representa el correo electrónico del proveedor.
     */
    private String correoElectronico;
    
    /**
     * Objeto String que representa la dirección de la imagen del proveedor.
     */
    private String direccionImagen;
    
    private List<IdEntidadGenericoDatos> idsProductosOfrecidos;
    
    private DireccionDTODatos direccion;
    
    public ProveedorDTO(
            IdEntidadGenericoDatos id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdEntidadGenericoDatos> idsProductosOfrecidos, 
            DireccionDTODatos direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.direccion = direccion;
    }
    
    public ProveedorDTO(
            IdEntidadGenericoDatos id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            DireccionDTODatos direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }
    
    public ProveedorDTO(
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<IdEntidadGenericoDatos> idsProductosOfrecidos, 
            DireccionDTODatos direccion) {
        
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.idsProductosOfrecidos = idsProductosOfrecidos;
        this.direccion = direccion;
    }

    public List<IdEntidadGenericoDatos> getListaIdsProductosOfrecidos() {
        
        return idsProductosOfrecidos;
        
    }

    public DireccionDTODatos getDireccion() {
        return direccion;
    }
    
}
