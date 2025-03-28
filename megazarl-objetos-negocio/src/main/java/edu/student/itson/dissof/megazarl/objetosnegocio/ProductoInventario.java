package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.Objects;

public class ProductoInventario {

    private Integer id;
    private Integer idProducto;
    private Integer idSucursal;
    private Float tiempoEnvioMatriz;
    private Integer cantidad;

    public ProductoInventario(Integer id, Integer idProducto, Integer idSucursal, Float tiempoEnvioMatriz, Integer cantidad) {
        this.id = id;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        this.tiempoEnvioMatriz = tiempoEnvioMatriz;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Float getTiempoEnvioMatriz() {
        return tiempoEnvioMatriz;
    }

    public void setTiempoEnvioMatriz(Float tiempoEnvioMatriz) {
        this.tiempoEnvioMatriz = tiempoEnvioMatriz;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
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
