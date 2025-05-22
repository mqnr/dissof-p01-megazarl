
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class Sucursal {
    
    private ObjectId id;
    
    private Boolean esMatriz;

    public Sucursal(ObjectId id, Boolean esMatriz) {
        this.id = id;
        this.esMatriz = esMatriz;
    }

    public Sucursal() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Boolean getEsMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(Boolean esMatriz) {
        this.esMatriz = esMatriz;
    }
    
}
