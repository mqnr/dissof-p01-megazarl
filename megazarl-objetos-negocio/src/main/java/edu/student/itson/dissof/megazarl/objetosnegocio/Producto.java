package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.List;
import java.util.Objects;

public class Producto {

    private Integer id;
    private String nombre;
    private String variedad;
    private String descripcion;
    private Integer milesSemillas;
    private Double precio;
    private Double pesoKg;
    private String direccionImagenProducto;
    private Proveedor proveedor;
    private List<ProductoInventario> listaProductoInventario;

    public Producto(Integer id, String nombre, String variedad, String descripcion, Integer milesSemillas, Double precio, Double pesoKg, String direccionImagenProducto, Proveedor proveedor, List<ProductoInventario> listaProductoInventario) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagenProducto = direccionImagenProducto;
        this.proveedor = proveedor;
        this.listaProductoInventario = listaProductoInventario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    public void setDireccionImagenProducto(String direccionImagenProducto) {
        this.direccionImagenProducto = direccionImagenProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<ProductoInventario> getListaProductoInventario() {
        return listaProductoInventario;
    }

    public void setListaProductoInventario(List<ProductoInventario> listaProductoInventario) {
        this.listaProductoInventario = listaProductoInventario;
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
