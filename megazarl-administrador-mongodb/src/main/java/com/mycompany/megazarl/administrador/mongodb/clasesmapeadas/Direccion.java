
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;

/**
 *
 * @author Laboratorios
 */
public class Direccion {
    
    private ObjectId id;

    private String codigoPostal;
    
    private String colonia;
    
    private String calle;
    
    private String numero;

    public Direccion(String codigoPostal, String colonia, String calle, String numero) {
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    public Direccion() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Direccion{" + "id=" + id + ", codigoPostal=" + codigoPostal + ", colonia=" + colonia + ", calle=" + calle + ", numero=" + numero + '}';
    }
    
}
