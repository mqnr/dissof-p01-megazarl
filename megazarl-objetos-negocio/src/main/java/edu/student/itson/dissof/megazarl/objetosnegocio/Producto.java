package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.List;


public class Producto {

    private Integer id;
    private Integer idProveedor;
    private String nombre;
    private String variedad;
    private String descripcion;
    private Integer milesSemillas;
    private Double precio;
    private Double pesoKg;
    private String nombreProveedor;
    private String direccionImagenProducto;
    private String direccionImagenProveedor;
    private List<ProductoInventario> listaProductoInventario;

    public Producto(Integer id, Integer idProveedor, String nombre, String variedad, String descripcion, Integer milesSemillas, Double precio, Double pesoKg, String nombreProveedor, String direccionImagenProducto, String direccionImagenProveedor, List<ProductoInventario> listaProductoInventario) {
        this.id = id;
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.nombreProveedor = nombreProveedor;
        this.direccionImagenProducto = direccionImagenProducto;
        this.direccionImagenProveedor = direccionImagenProveedor;
        this.listaProductoInventario = listaProductoInventario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public void setMilesSemillas(Integer milesSemillas) {
        this.milesSemillas = milesSemillas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public void setDireccionImagenProducto(String direccionImagenProducto) {
        this.direccionImagenProducto = direccionImagenProducto;
    }

    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    public List<ProductoInventario> getListaProductoInventario() {
        return listaProductoInventario;
    }

    public void setListaProductoInventario(List<ProductoInventario> listaProductoInventario) {
        this.listaProductoInventario = listaProductoInventario;
    }

    
   

    
}
