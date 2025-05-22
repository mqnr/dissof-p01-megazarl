package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

/**
 * InformacionProductoCarritoDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * la información de un producto que se encuentra en el carrito de compras,
 * incluyendo detalles del producto y la cantidad seleccionada por el cliente.
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
public class InformacionProductoCarritoDTO {
    private IdEntidadGenerico id;
    private String nombre;
    private String variedad;
    private Double precio;
    private Integer milesSemillas;
    private Integer cantidad;
    private String nombreProveedor;
    private String direccionImagenProducto;

    public InformacionProductoCarritoDTO(IdEntidadGenerico id, Integer cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public IdEntidadGenerico getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setMilesSemillas(Integer milesSemillas) {
        this.milesSemillas = milesSemillas;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setDireccionImagenProducto(String direccionImagenProducto) {
        this.direccionImagenProducto = direccionImagenProducto;
    }
}
