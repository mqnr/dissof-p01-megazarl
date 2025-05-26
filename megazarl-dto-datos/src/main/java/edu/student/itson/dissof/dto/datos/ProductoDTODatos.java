
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * ProductoDTODatos.java
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
public class ProductoDTODatos{

    /**
     * Objeto Long que representa el ID del producto
     */
    private IdEntidadGenericoDatos id;

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
    
    private IdEntidadGenericoDatos idProveedor;

    private List<IdEntidadGenericoDatos> idsProductosInventario;

    public ProductoDTODatos(
            IdEntidadGenericoDatos id, 
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            IdEntidadGenericoDatos idProveedor) {
        
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.idProveedor = idProveedor;

    }
    
    public ProductoDTODatos(
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            IdEntidadGenericoDatos idProveedor) {
        
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.idProveedor = idProveedor;

    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public void setId(IdEntidadGenericoDatos id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public void setMilesSemillas(Integer milesSemillas) {
        this.milesSemillas = milesSemillas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
 
    public List<IdEntidadGenericoDatos> getIdsProductosInventario(){
        return idsProductosInventario;
    }
 
    public List<IdEntidadGenericoDatos> getIdsProductosCarrito() {
        List<IdEntidadGenericoDatos> idsProductosCarrito = new LinkedList<>();
        
        for(IdEntidadGenericoDatos idProductoCarrito: idsProductosCarrito){
            
            idsProductosCarrito.add(idProductoCarrito);
        }
        
        return idsProductosCarrito;
    }

    public List<IdEntidadGenericoDatos> getIdsProductosPedido() {
        List<IdEntidadGenericoDatos> idsProductosPedido = new LinkedList<>();
        
        for(IdEntidadGenericoDatos idProductoPedido: idsProductosPedido){
            
            idsProductosPedido.add(idProductoPedido);
        }
        
        return idsProductosPedido;
    }
    
    public IdEntidadGenericoDatos getIdProveedor() {
        return idProveedor;
    }
    
}
