package edu.student.itson.dissof.megazarl.dto;

public class ProductoInicioDTO {

    private Integer id;
    private String nombre;
    private String variedad;
    private Double precio;
    private Integer milesSemillas;
    private String direccionImagenProducto;
    private String direccionImagenProveedor;

    public ProductoInicioDTO(Integer id, String nombre, String variedad, Double precio, Integer milesSemillas, String direccionImagenProducto, String direccionImagenProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.precio = precio;
        this.milesSemillas = milesSemillas;
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

    public Double getPrecio() {
        return precio;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }
}
