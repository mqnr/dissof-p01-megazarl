
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;

/**
 * Clase que representa un auxiliar de ventas registrado en el sistema.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class AuxiliarVentas {
    
    private ObjectId id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private ObjectId idSucursal;

    public AuxiliarVentas(){}
    
    public AuxiliarVentas(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno,
            ObjectId idSucursal) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idSucursal = idSucursal;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public ObjectId getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(ObjectId idSucursal) {
        this.idSucursal = idSucursal;
    }
    
}
