
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.List;


public class ProveedorDTODatos{
    
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
    
    private List<ProductoDTODatos> productosOfrecidos;
    
    private DireccionDTODatos direccion;
    
    public ProveedorDTODatos(
            IdEntidadGenericoDatos id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<ProductoDTODatos> productosOfrecidos, 
            DireccionDTODatos direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.productosOfrecidos = productosOfrecidos;
        this.direccion = direccion;
    }
    
    public ProveedorDTODatos(
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
    
    public ProveedorDTODatos(
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            DireccionDTODatos direccion) {
        
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public List<ProductoDTODatos> getproductosOfrecidos() {
        return productosOfrecidos;
    }

    public DireccionDTODatos getDireccion() {
        return direccion;
    }
    
}
