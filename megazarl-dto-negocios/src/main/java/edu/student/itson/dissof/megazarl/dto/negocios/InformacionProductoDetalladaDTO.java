package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

/**
 * InformacionProductoDetalladaDTO.java

 Clase DTO que contiene la información detallada de un producto para ser mostrada
 en la ventana de detalles del producto, incluyendo descripción completa y
 datos del proveedor.
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
public class InformacionProductoDetalladaDTO {
    /**
     * Objeto Long que representa el ID del producto.
     */
    private IdEntidadGenerico idProducto;
    
    /**
     * Objeto String que representa el nombre del producto.
     */
    private String nombreProducto;
    
    /**
     * Objeto String que representa la variedad del producto.
     */
    private String variedadProducto;
    
    /**
     * Objeto String que representa la descripcion del producto.
     */
    private String descripcionProducto;
    
    /**
     * Objeto Double que representa el precio dle producto.
     */
    private Double precioProducto;
    
    /**
     * Objeto Integer que representa la cantidad de semillas en miles que contiene
     * cada unidad del producto.
     */
    private Integer milesSemillasProducto;
    
    /**
     * Objeto String que representa la dirección de la imagen del producto.
     */
    private String direccionImagenProducto;
    
    /**
     * Objeto Long que representa el ID del proveedor del producto.
     */
    private IdEntidadGenerico idProveedor;
    
    /**
     * Objeto String que representa el nombre del proveedor del producto.
     */
    private String nombreProveedor;
    
    /**
     * Objeto String que representa la dirección de la imagen del producto.
     */
    private String direccionImagenProveedor; 

    public InformacionProductoDetalladaDTO(
            IdEntidadGenerico idProducto,
            String nombreProducto, 
            String variedadProducto, 
            String descripcionProducto,
            Double precioProducto, 
            Integer milesSemillasProducto, 
            String direccionImagenProducto,
            IdEntidadGenerico idProveedor) {
        
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.variedadProducto = variedadProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.milesSemillasProducto = milesSemillasProducto;
        this.direccionImagenProducto = direccionImagenProducto;
        this.idProveedor = idProveedor;
    }

    public IdEntidadGenerico getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getVariedadProducto() {
        return variedadProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public Integer getMilesSemillasProducto() {
        return milesSemillasProducto;
    }

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public IdEntidadGenerico getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }
    
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
        this.direccionImagenProveedor = direccionImagenProveedor;
    }
}
