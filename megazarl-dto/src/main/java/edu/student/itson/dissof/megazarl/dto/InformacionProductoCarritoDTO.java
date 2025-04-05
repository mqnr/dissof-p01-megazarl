package edu.student.itson.dissof.megazarl.dto;

public class InformacionProductoCarritoDTO {

    private Integer id;
    private String nombre;
    private String variedad;
    private Double precio;
    private Integer milesSemillas;
    private Integer cantidad;
    private String nombreProveedor;
    private String direccionImagenProducto;

    public InformacionProductoCarritoDTO(Integer id, Integer cantidad) {
        this.id = id;
        this.cantidad = cantidad;
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
