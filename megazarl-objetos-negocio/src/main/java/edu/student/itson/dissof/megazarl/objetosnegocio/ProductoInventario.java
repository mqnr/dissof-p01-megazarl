package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.Objects;

public class ProductoInventario {

    private Integer id;
    private Producto producto;
    private Sucursal sucursal;
    private Boolean apartado;

    public ProductoInventario(Integer id, Sucursal sucursal, Boolean apartado) {
        this.id = id;
        this.sucursal = sucursal;
        this.apartado = apartado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Boolean getApartado() {
        return apartado;
    }

    public void setApartado(Boolean apartado) {
        this.apartado = apartado;
    }

    
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final ProductoInventario other = (ProductoInventario) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
