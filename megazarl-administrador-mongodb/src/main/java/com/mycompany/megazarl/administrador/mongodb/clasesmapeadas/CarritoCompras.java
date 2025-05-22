
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class CarritoCompras {
    
    private ObjectId id;
    
    private Long idLong;
    
    private Boolean esVigente;
    
    private Cliente cliente;
    
    private Paqueteria paqueteria;

    public CarritoCompras(Boolean esVigente, Cliente cliente, Paqueteria paqueteria) {
        this.esVigente = esVigente;
        this.cliente = cliente;
        this.paqueteria = paqueteria;
    }

    public CarritoCompras() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getIdLong() {
        return idLong;
    }

    public void setIdLong(Long idLong) {
        this.idLong = idLong;
    }
    
    public Boolean getEsVigente() {
        return esVigente;
    }

    public void setEsVigente(Boolean esVigente) {
        this.esVigente = esVigente;
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
    
    
    
}
