package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionProveedorInicioDTONegocios.java

 Clase que representa un objeto de transferencia de datos que contiene
 la información resumida de un proveedor para ser mostrada en la página
 del caso de uso orden de compra, incluyendo datos básicos del proveedor
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 *
 */
public class InformacionProveedorInicioDTONegocios {
    
    /**
     * Objeto Long que representa el ID del proveedor.
     */
    private IdEntidadGenericoNegocios idProveedor;
    
    /**
     * Objeto String que representa el nombre del proveedor.
     */
    private String nombreProveedor;
    
    /**
     * Objeto String que representa el teléfono del proveedor.
     */
    private String telefonoProveedor;
    
    /**
     * Objeto String que representa el correo electrónico del proveedor.
     */
    private String correoElectronicoProveedor;
    
    /**
     * Objeto String que representa la dirección de imagen del proveedor.
     */
    private String direccionImagenProveedor;

    /**
     * Constructor de la clase que recibe informacion del proveedor a mostrar 
     * en la pantalla del caso de uso orden de compra.
     * 
     * @param idProveedor                   Objeto IdEntidadGenerico que representa el id del proveedor.
     * @param nombreProveedor               Objeto String que representa el nombre del proveedor.
     * @param telefonoProveedor             Objeto String que representa el teléfono del proveedor.
     * @param correoElectronicoProveedor    Objeto String que representa el correo electrónico del proveedor.
     * @param direccionImagenProveedor      Objeto String que representa la dirección que guarda la imagen del proveedor.
     */
    public InformacionProveedorInicioDTONegocios(IdEntidadGenericoNegocios idProveedor, String nombreProveedor, String telefonoProveedor, String correoElectronicoProveedor, String direccionImagenProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.correoElectronicoProveedor = correoElectronicoProveedor;
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    /**
     * Método que permite obener el ID del proveedor.
     * @return Objeto IdEntidadGenerico que representa el ID del proveedor.
     */
    public IdEntidadGenericoNegocios getIdProveedor() {
        return idProveedor;
    }

    /**
     * Método que permite obener el nombre del proveedor.
     * @return Objeto String que representa el nombre del proveedor.
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * Método que permite obener el teléfono del proveedor.
     * @return Objeto String que representa el teléfono del proveedor.
     */
    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    /**
     * Método que permite obener el correo electrónico del proveedor.
     * @return Objeto String que representa el correo electrónico del proveedor.
     */
    public String getCorreoElectronicoProveedor() {
        return correoElectronicoProveedor;
    }

    /**
     * Método que permite obener la dirección que guarda la imagen del proveedor.
     * @return Objeto String que representa la dirección donde se guarda la imagen del proveedor.
     */
    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

    /**
     * Método que permite establecer la dirección de imagen de un proveedor.
     * @param direccionImagenProveedor      Objeto String que representa la dirección que guardará la imagen del proveedor
     */
    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

}