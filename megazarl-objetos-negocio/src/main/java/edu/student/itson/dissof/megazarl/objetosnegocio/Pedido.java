package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.HashMap;
import java.util.Objects;

public class Pedido {
    
    private Integer id;
    HashMap<ProductoInventario, Boolean> mapaProductosRequeridos;
    private Paqueteria paqueteria;
    private EstadoPedido estado;

    private Integer PROXIMO_ID_PEDIDO = 1;
    
    public Pedido(HashMap<ProductoInventario, Boolean> mapaProductosRequeridos, Paqueteria paqueteria, EstadoPedido estado) {
        this.id = PROXIMO_ID_PEDIDO++;
        this.mapaProductosRequeridos = mapaProductosRequeridos;
        this.paqueteria = paqueteria;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HashMap<ProductoInventario, Boolean> getMapaProductosRequeridos() {
        return mapaProductosRequeridos;
    }

    public void setMapaProductosRequeridos(HashMap<ProductoInventario, Boolean> mapaProductosRequeridos) {
        this.mapaProductosRequeridos = mapaProductosRequeridos;
    }

    public Paqueteria getPaqueteria() {
        return paqueteria;
    }

    public void setPaqueteria(Paqueteria paqueteria) {
        this.paqueteria = paqueteria;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Integer getPROXIMO_ID_PEDIDO() {
        return PROXIMO_ID_PEDIDO;
    }

    public void setPROXIMO_ID_PEDIDO(Integer PROXIMO_ID_PEDIDO) {
        this.PROXIMO_ID_PEDIDO = PROXIMO_ID_PEDIDO;
    }
    
    
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
