
package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.Objects;


public class Producto {
    private Integer id;
    private Integer idProveedor;
    private String nombre;
    private String variedad;
    private String descripcion;
    private Integer milesSemillas;
    private Float precio;
    private String nombreProveedor;
    private String direccionImagenProducto;
    private String direccionImagenProveedor;
    private ProductoInventario[] arregloProductoInventario;

    public Producto(Integer id, Integer idProveedor, String nombre, String variedad, String descripcion, Integer milesSemillas, Float precio, String nombreProveedor, String direccionImagenProducto, String direccionImagenProveedor, ProductoInventario[] arregloProductoInventario) {
        this.id = id;
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.nombreProveedor = nombreProveedor;
        this.direccionImagenProducto = direccionImagenProducto;
        this.direccionImagenProveedor = direccionImagenProveedor;
        this.arregloProductoInventario = arregloProductoInventario;
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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
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

    public ProductoInventario[] getArregloProductoInventario() {
        return arregloProductoInventario;
    }

    public void setArregloProductoInventario(ProductoInventario[] arregloProductoInventario) {
        this.arregloProductoInventario = arregloProductoInventario;
    }

    

    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
    
    
}
