package edu.student.itson.dissof.megazarl.dto;

public class InformacionProductoCarritoDTO {

    private Integer id;
    private String nombre;
    private String variedad;
    private Float precio;
    private Integer milesSemillas;
    private String nombreProveedor;
    private String direccionImagenProducto;

    public InformacionProductoCarritoDTO(Integer id, String nombre, String variedad, Float precio, Integer milesSemillas, String nombreProveedor, String direccionImagenProducto) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.precio = precio;
        this.milesSemillas = milesSemillas;
        this.nombreProveedor = nombreProveedor;
        this.direccionImagenProducto = direccionImagenProducto;
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
}
