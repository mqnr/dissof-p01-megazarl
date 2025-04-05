package edu.student.itson.dissof.megazarl.dto;

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
    private Integer id;
    private String nombre;
    private String variedad;
    private Double precio;
    private Integer milesSemillas;
    private String direccionImagenProducto;
    private Integer idProveedor;
    private String nombreProveedor;
    private String direccionImagenProvedor;

    public InformacionProductoInicioDTO(Integer id, String nombre, String variedad, Double precio, Integer milesSemillas, String direccionImagenProducto, Integer idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.milesSemillas = milesSemillas;
        this.direccionImagenProducto = direccionImagenProducto;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setDireccionImagenProvedor(String direccionImagenProvedor) {
        this.direccionImagenProvedor = direccionImagenProvedor;
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccionImagenProvedor() {
        return direccionImagenProvedor;
    }
}
