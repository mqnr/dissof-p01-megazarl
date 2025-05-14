package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * InformacionProductoInicioDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * la información resumida de un producto para ser mostrada en la página
 * de inicio o catálogo general, incluyendo datos básicos del producto
 * y su proveedor.
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
public class InformacionProductoInicioDTO {
    /**
     * Objeto Long que representa el ID del producto.
     */
    private Long idProducto;
    /**
     * Objeto String que representa el nombre del producto.
     */
    private String nombreProducto;
    /**
     * Objeto String que representa la variedad del producto.
     */
    private String variedadProducto;
    
    private String proveedorProducto;
    
    /**
     * Objeto Double que representa el precio del producto.
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
     * Objeto Long que representa el id del proveedor del producto.
     */
    private Long idProveedor;

    /**
     * Objeto String que representa la dirección de la imagen del proveedor del producto.
     */
    private String direccionImagenProveedor;

    /**
     * Constructor de la clase que recibe informacion del producto a mostrar 
     * en pantalla inicial.
     * @param idProducto                Objeto Long que representa el ID del producto.
     * @param nombreProducto            Objeto String que representa el nombre del producto.
     * @param variedadProducto          Objeto String que representa la variedad del producto.
     * @param precioProducto            Objeto Double que representa el precio del producto.
     * @param milesSemillasProducto     Objeto Integer que representa la cantidad de semillas en miles que contiene 
     *                                  cada unidad del producto.
     * @param direccionImagenProducto   Objeto String que representa la dirección de la imagen del producto.
     * @param idProveedor               Objeto Long que representa el ID del proveedor.
     */
    public InformacionProductoInicioDTO(
            Long idProducto, 
            String nombreProducto,
            String variedadProducto,
            Double precioProducto, 
            Integer milesSemillasProducto,
            String direccionImagenProducto,
            Long idProveedor) {
        
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.variedadProducto = variedadProducto;
        this.precioProducto = precioProducto;  
        this.milesSemillasProducto = milesSemillasProducto;
        this.direccionImagenProducto = direccionImagenProducto;
        this.idProveedor = idProveedor;
    }

 
    /**
     * Método que permite obener el ID del producto.
     * @return Objeto Long que representa el ID del producto.
     */
    public Long getIdProducto() {
        return idProducto;
    }

    /**
     * Método que permite obtener el nombre del producto.
     * @return Objeto String que representa el nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Método que permite obtener la variedad del producto.
     * @return Objeto String que representa la variedad del producto.
     */
    public String getVariedadProducto() {
        return variedadProducto;
    }

    public String getProveedorProducto() {
        return proveedorProducto;
    }

    /**
     * Método que permite obtener el precio del producto.
     * @return Objeto Double que representa el precio del producto.
     */
    public Double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Método que permite obtener la cantidad de semillas en miles que contiene cada unidad
     * del producto.
     * @return Objeto Integer que representa los miles de semillas que contiene cada unidad del producto.
     */
    public Integer getMilesSemillasProducto() {
        return milesSemillasProducto;
    }

    /**
     * Método que permite obtener la dirección de la imagen de producto.
     * @return Objeto String que representa la dirección de la imagen del producto.
     */
    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    /**
     * Método que permite obtener el ID del proveedor del producto.
     * @return Objeto Long que representa el ID del proveedor del producto.
     */
    public Long getIdProveedor() {
        return idProveedor;
    }
    
    /**
     * Método que permite obtener la dirección de la imagen del proveedor del producto.
     * @return Objeto Srting que representa la dirección de la imagen del proveedor del producto.
     */
    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

    /**
     * Método que permite establecer la dirección de imagen de un proveedor.
     * @param direccionImagenProveedor Objeto String que representa la dirección de la 
     * imagen del proveedor.
     */
    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    public void setProveedorProducto(String proveedorProducto) {
        this.proveedorProducto = proveedorProducto;
    }
    
    
    
    

}
