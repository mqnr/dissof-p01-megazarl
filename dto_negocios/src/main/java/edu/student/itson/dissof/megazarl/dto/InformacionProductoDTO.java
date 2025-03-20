
package edu.student.itson.dissof.megazarl.dto;

public class InformacionProductoDTO {
    private Integer id;
    private String nombre;
    private String variedad;
    private String descripcion;
    private Float precio;
    private Integer milesSemillas;
    private String nombreProveedor;
    private String direccionImagenProducto;
    private String direccionImagenProveedor;

    public InformacionProductoDTO(Integer id, String nombre, String variedad, String descripcion, Float precio, Integer milesSemillas, String nombreProveedor, String direccionImagenProducto, String direccionImagenProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.milesSemillas = milesSemillas;
        this.nombreProveedor = nombreProveedor;
        this.direccionImagenProducto = direccionImagenProducto;
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

}
