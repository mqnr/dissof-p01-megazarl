
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.List;
import java.util.Objects;

/**
 * 
 * ProductoDTO.java
 * 
 * Clase DTO que representa los datos de un producto ofrecido por la empresa
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
public class ProductoDTO {

    /**
     * Objeto Long que representa el ID del producto
     */
    private Long id;

    /**
     * Objeto String que representa el nombre del producto
     */
    private String nombre;

    /**
     * Objeto String que representa la variedad del producto
     */
    private String variedad;

    /**
     * Objeto String que representa la descripción del producto
     */
    private String descripcion;

    /**
     * Objeto Integer que representa la cantidad del producto en miles de semillas
     */
    private Integer milesSemillas;

    /**
     * Objeto Double que representa el precio del producto
     */
    private Double precio;

    /**
     * Objeto Double que representa el peso del producto en kilogramos
     */
    private Double pesoKg;

    /**
     * Objeto String que representa la dirección de la imagen del producto
     */
    private String direccionImagen;

    /**
     * Objeto ProveedorDTO que representa el proveedor del producto
     */
    private ProveedorDTO proveedor;

    /**
     * Lista de ProductoInventarioDTO que representa el inventario asociado al producto
     */
    private List<ProductoInventarioDTO> listaProductoInventario;

    /**
     * Constructor que permite instanciar un objeto de tipo ProductoDTO
     * @param id                        Objeto Long que representa el ID del producto
     * @param nombre                    Objeto String que representa el nombre del producto
     * @param variedad                  Objeto String que representa la variedad del producto
     * @param descripcion               Objeto String que representa la descripción del producto
     * @param milesSemillas             Objeto Integer que representa la cantidad del producto en miles de semillas
     * @param precio                    Objeto Double que representa el precio del producto
     * @param pesoKg                    Objeto Double que representa el peso del producto en kilogramos
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del producto
     * @param proveedor                 Objeto ProveedorDTO que representa el proveedor del producto
     * @param listaProductoInventario   Objeto {@literal List<ProductoInventarioDTO>} que representa el inventario asociado al producto
     */
    public ProductoDTO(
            Long id, 
            String nombre, 
            String variedad,
            String descripcion, 
            Integer milesSemillas, 
            Double precio,
            Double pesoKg,
            String direccionImagen, 
            ProveedorDTO proveedor, 
            List<ProductoInventarioDTO> listaProductoInventario) {
        
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.proveedor = proveedor;
        this.listaProductoInventario = listaProductoInventario;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo ProductoDTO
     * @param id                        Objeto Integer que representa el id del producto
     * @param nombre                    Objeto String que representa el nombre del producto
     * @param variedad                  Objeto String que representa la variedad del producto
     * @param descripcion               Objeto String que representa la descripción del producto
     * @param milesSemillas             Objeto Integer que representa la cantidad del producto en miles de semillas
     * @param precio                    Objeto Double que representa el precio del producto
     * @param pesoKg                    Objeto Double que representa el peso del producto en kilogramos
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del producto
     * @param proveedor                 Objeto ProveedorDTO que representa el proveedor del producto
     */
    public ProductoDTO(
            Long id, 
            String nombre, 
            String variedad,
            String descripcion, 
            Integer milesSemillas, 
            Double precio,
            Double pesoKg,
            String direccionImagen, 
            ProveedorDTO proveedor) {
        
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.proveedor = proveedor;
    }

    /**
     * Método que permite obtener el id del producto
     * @return Objeto Long que representa el ID del producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el nombre del producto
     * @return Objeto String que representa el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener la variedad del producto
     * @return Objeto String que representa la variedad del producto
     */
    public String getVariedad() {
        return variedad;
    }

    /**
     * Método que permite obtener la descripción del producto
     * @return Objeto String que representa la descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que permite obtener la cantidad del producto en miles de semillas
     * @return Objeto Integer que representa la cantidad en miles de semillas del producto
     */
    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    /**
     * Método que permite obtener el precio del producto
     * @return Objeto Double que representa el precio del producto
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Método que permite obtener el peso del producto en kilogramos
     * @return Objeto Double que representa el peso en kilogramos del producto
     */
    public Double getPesoKg() {
        return pesoKg;
    }

    /**
     * Método que permite obtener la dirección de la imagen del producto
     * @return Objeto String que representa la dirección de la imagen del producto
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }

    /**
     * Método que permite obtener el proveedor del producto
     * @return Objeto ProveedorDTO que representa el proveedor del producto
     */
    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    /**
     * Método que permite obtener la lista de inventarios asociados al producto.
     * @return Objeto {@literal List<ProductoInventarioDTO>} que representa el inventario asociado al producto
     */
    public List<ProductoInventarioDTO> getListaProductoInventario() {
        return listaProductoInventario;
    }

    /**
     * Método que permite obtener el hash code del producto, a partir de su ID
     * @return Dato int que representa el hash code del producto
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este producto, basándose en su ID
     * @param obj Objeto a determinar si es igual a este producto
     * @return Dato boolean, true si el objeto es igual al producto, false en
     * caso contrario
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
        final ProductoDTO other = (ProductoDTO) obj;
        return Objects.equals(this.id, other.id);
    }
       
}