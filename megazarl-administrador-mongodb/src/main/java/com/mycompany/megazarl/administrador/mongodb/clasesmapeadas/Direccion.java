
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;

/**
 * Clase que representa una dirección en el sistema, esta anidada dentro de
 * las clases que representan a un Cliente, Proveedor y Sucursal registrados en el sistema.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class Direccion {
    
    private ObjectId id;

    private String codigoPostal;
    
    private String colonia;
    
    private String calle;
    
    private String numero;

    public Direccion() {}
        
    public Direccion(
            ObjectId id,
            String codigoPostal,
            String colonia,
            String calle, 
            String numero) {
        
        this.id = id;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }
    
    public Direccion(
            String codigoPostal,
            String colonia,
            String calle, 
            String numero) {
        
        this.id = new ObjectId();
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
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
    
}
