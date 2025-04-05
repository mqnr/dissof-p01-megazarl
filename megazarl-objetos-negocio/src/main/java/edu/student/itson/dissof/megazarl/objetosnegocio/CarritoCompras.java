
package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.HashMap;
import java.util.Objects;


public class CarritoCompras {
    private Integer id;
    private Cliente cliente;
    private Paqueteria paqueteria;
    private Boolean pedidoRealizado;
    private HashMap<Producto, Integer> productosCantidades;
    
    private static Integer ID_PROXIMO_CARRITO_COMPRAS = 1;

    public CarritoCompras(Cliente cliente, Paqueteria paqueteria, Boolean pedidoRealizado, HashMap<Producto, Integer> productosCantidades) {
        this.id = ID_PROXIMO_CARRITO_COMPRAS++;
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.pedidoRealizado = pedidoRealizado;
        this.productosCantidades = productosCantidades;
    }
    
    public CarritoCompras(Cliente cliente, Boolean pedidoRealizado, HashMap<Producto, Integer> productosCantidades) {
        this.id = ID_PROXIMO_CARRITO_COMPRAS++;
        this.cliente = cliente;
        this.pedidoRealizado = pedidoRealizado;
        this.productosCantidades = productosCantidades;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Paqueteria getPaqueteria() {
        return paqueteria;
    }

    public void setPaqueteria(Paqueteria paqueteria) {
        this.paqueteria = paqueteria;
    }

    public Boolean getPedidoRealizado() {
        return pedidoRealizado;
    }

    public void setPedidoRealizado(Boolean pedidoRealizado) {
        this.pedidoRealizado = pedidoRealizado;
    }

    public HashMap<Producto, Integer> getProductosCantidades() {
        return productosCantidades;
    }

    public void setProductosCantidades(HashMap<Producto, Integer> productosCantidades) {
        this.productosCantidades = productosCantidades;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final CarritoCompras other = (CarritoCompras) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
    
    
    
}
