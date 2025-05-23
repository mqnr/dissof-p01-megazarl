package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.Objects;
/**
 * 
 * ProductoOrdenCompraDTO.java
 * 
 * Clase DTO que representa los datos de un producto ofrecido por la empresa 
 * (adaptado para las necesidades del caso de uso Orden de COMPRA)
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public abstract class ProductoOrdenCompraDTO {
    
    /**
     * Objeto Long que representa el ID del producto
     */
    private IdEntidadGenerico id;

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
     * Constructor que permite instanciar un objeto de tipo ProductoDTO
     * @param id                        Objeto IdEntidadGenerico que representa el ID del producto
     * @param nombre                    Objeto String que representa el nombre del producto
     * @param variedad                  Objeto String que representa la variedad del producto
     * @param descripcion               Objeto String que representa la descripción del producto
     * @param milesSemillas             Objeto Integer que representa la cantidad del producto en miles de semillas
     * @param precio                    Objeto Double que representa el precio del producto
     * @param pesoKg                    Objeto Double que representa el peso del producto en kilogramos
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del producto
     */
    public ProductoOrdenCompraDTO(
            IdEntidadGenerico id, 
            String nombre, 
            String variedad, 
            String descripcion, 
            Integer milesSemillas, 
            Double precio, 
            Double pesoKg, 
            String direccionImagen) {
        
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo ProductoDTO, sin ID.
     * @param nombre                    Objeto String que representa el nombre del producto
     * @param variedad                  Objeto String que representa la variedad del producto
     * @param descripcion               Objeto String que representa la descripción del producto
     * @param milesSemillas             Objeto Integer que representa la cantidad del producto en miles de semillas
     * @param precio                    Objeto Double que representa el precio del producto
     * @param pesoKg                    Objeto Double que representa el peso del producto en kilogramos
     * @param direccionImagen           Objeto String que representa la dirección de la imagen del producto
     */
    public ProductoOrdenCompraDTO(
            String nombre, 
            String variedad,
            String descripcion, 
            Integer milesSemillas, 
            Double precio,
            Double pesoKg,
            String direccionImagen) {

        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
    }
    
    /**
     * Método que permite obtener el id del producto
     * @return Objeto Long que representa el ID del producto
     */
    public IdEntidadGenerico getId() {
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
    
    public abstract IdEntidadGenerico getIdProveedor();
    
    /**
     * Método que permite establecer el ID del producto.
     * @param id Objeto Long que representa el ID del producto.
     */
    public void setId(IdEntidadGenerico id) {
        this.id = id;
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
        final ProductoOrdenCompraDTO other = (ProductoOrdenCompraDTO) obj;
        return Objects.equals(this.id, other.id);
    }
    
}