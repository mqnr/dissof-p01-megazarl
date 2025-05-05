
package edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * ProveedorDTO.java
 *
 * Clase DTO que representa un proveedor de semillas registrado en el sistema,
 * con su información de identificación y logotipo asociado.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class ProveedorDTO {

    /**
     * Objeto Long que representa el ID del proveedor.
     */
    private Long id;

    /**
     * Objeto String que representa el nombre del proveedor.
     */
    private String nombre;

    /**
     * Objeto String que representa el teléfono del proveedor.
     */
    private String telefono;
    
    /**
     * Objeto String que representa la dirección de la imagen del proveedor.
     */
    private String direccionImagen;
    
    /**
     * Objeto {@literal List<ProductoDTO>} que representa la lista de productos.
     * ofrecidos por el proveedor.
     */
    private List<ProductoDTO> listaProductosOfrecidos = new LinkedList<>();
    
    /**
     * Objeto DireccionDTO que representa la dirección del proveedor.
     */
    private DireccionDTO direccion;

    /**
     * Constructor que permite instanciar un objeto de tipo ProveedorDTO
     * @param id                        Objeto Long que representa el ID del proveedor.
     * @param nombre                    Objeto String que representa el nombre del proveedor.
     * @param telefono                  Objeto String que representa el teléfono del proveedor.
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del proveedor.
     * @param listaProductosOfrecidos   Objeto {@literal List<ProductoDTO>} que representa la lista de productos
     *                                  ofrecidos por el proveedor.
     * @param direccion                 Objeto DireccionDTO que representa la dirección del proveedor.
     * 
    */
    public ProveedorDTO(
            Long id, 
            String nombre,
            String telefono,
            String direccionImagen,
            List<ProductoDTO> listaProductosOfrecidos,
            DireccionDTO direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionImagen = direccionImagen;
        this.listaProductosOfrecidos = listaProductosOfrecidos;
        this.direccion = direccion;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo ProveedorDTO.
     * @param id                        Objeto Long que representa el ID del proveedor.
     * @param nombre                    Objeto String que representa el nombre del proveedor.
     * @param telefono                  Objeto String que representa el teléfono del proveedor.
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del proveedor.
     * @param direccion                 Objeto DireccionDTO que representa la dirección del proveedor.
    */
    public ProveedorDTO(
            Long id, 
            String nombre,
            String telefono,
            String direccionImagen,
            DireccionDTO direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }

    /**
     * Método que permite obtener el ID del proveedor.
     * @return Objeto Long que representa el ID del proveedor.
     */
    public Long getId() {
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
     * Método que permite obtener la dirección de la imagen del proveedor.
     * @return Objeto String que representa la dirección de la imagen del proveedor.
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }

    /**
     * Método que permite obtener la lista de productos ofrecidos por el proveedor.
     * @return Objeto {@literal List<ProductoDTO>} que representa la lista de productos ofrecidos por el proveedor.
     */
    public List<ProductoDTO> getListaProductosOfrecidos() {
        return listaProductosOfrecidos;
    }

    /**
     * Método que permite obtenre la dirección del proveedor.
     * @return Objeto DireccionDTO que representa la dirección del proveedor.
     */
    public DireccionDTO getDireccion() {
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
        final ProveedorDTO other = (ProveedorDTO) obj;
        return java.util.Objects.equals(this.id, other.id);
    }
}