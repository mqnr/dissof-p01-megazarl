package edu.student.itson.dissof.megazarl.dto;

public class InformacionProductoVentaDTO {

    private Integer id;
    private String nombre;
    private String variedad;
    private String descripcion;
    private Double precio;
    private Integer milesSemillas;
    private String direccionImagenProducto;
    private Integer idProveedor;
    private String nombreProveedor;
    private String direccionImagenProveedor; 

    public InformacionProductoVentaDTO(Integer id, String nombre, String variedad, String descripcion, Double precio, Integer milesSemillas, String direccionImagenProducto, Integer idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.milesSemillas = milesSemillas;
        this.direccionImagenProducto = direccionImagenProducto;
        this.idProveedor = idProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
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

    public Double getPrecio() {
        return precio;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

    

    
}
