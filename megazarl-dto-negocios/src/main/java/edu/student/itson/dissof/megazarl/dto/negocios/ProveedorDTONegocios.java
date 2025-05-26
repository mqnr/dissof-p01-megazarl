
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;


public class ProveedorDTONegocios {
    /**
     * Objeto Long que representa el ID del proveedor.
     */
    private IdEntidadGenericoNegocios id;

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
    
    private DireccionDTONegocios direccion;
    

    /**
     * Constructor que permite instanciar un objeto de tipo ProveedorDTO
     * @param id                        Objeto IdEntidadGenericoNegocios que representa el ID del proveedor.
     * @param nombre                    Objeto String que representa el nombre del proveedor.
     * @param telefono                  Objeto String que representa el teléfono del proveedor.
     * @param correoElectronico         Objeto String que representa el correo electrónico del proveedor.
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del proveedor.
     * @param direccion
     * 
    */
    public ProveedorDTONegocios(
            IdEntidadGenericoNegocios id, 
            String nombre,
            String telefono,
            String correoElectronico,
            String direccionImagen,
            DireccionDTONegocios direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo ProveedorDTO, sin ID.
     * @param nombre                    Objeto String que representa el nombre del proveedor.
     * @param telefono                  Objeto String que representa el teléfono del proveedor.
     * @param correoElectronico         Objeto String que representa el correo electrónico del proveedor.
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del proveedor.
     * @param direccion
     * 
    */
    public ProveedorDTONegocios(
            String nombre,
            String telefono,
            String correoElectronico,
            String direccionImagen,
            DireccionDTONegocios direccion) {
        
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }

    /**
     * Método que permite obtener el ID del proveedor.
     * @return Objeto Long que representa el ID del proveedor.
     */
    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    /**
     * Método que permite obtener el nombre del proveedor.
     * @return Objeto String que representa el nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener el teléfono del proveedor.
     * @return Objeto String que representa el teléfono del proveedor.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que permite obtener el correo electrónico del proveedor.
     * @return Objeto String que representa el correo electrónico del proveedor.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    /**
     * Método que permite obtener la dirección de la imagen del proveedor.
     * @return Objeto String que representa la dirección de la imagen del proveedor.
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }
    
    /**
     * Métdo que permite establecer el ID del proveedor.
     * @param id Objeto Long que representa el ID del proveedor.
     */
    public void setId(IdEntidadGenericoNegocios id) {
        this.id = id;
    }

    public DireccionDTONegocios getDireccion() {
        return direccion;
    }
    
    /**
    * Método que permite obtener el hash code del proveedor, a partir de su ID
    * @return Dato int que representa el hash code del proveedor.
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + java.util.Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este proveedor, basándose en su ID.
     * @param obj Objeto a determinar si es igual a este proveedor.
     * @return Dato boolean, true si el objeto es igual al proveedor, false en
     * caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProveedorDTONegocios other = (ProveedorDTONegocios) obj;
        return java.util.Objects.equals(this.id, other.id);
    }
}
