
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ActualizacionProductoInventarioDTODatos {
    
    private IdEntidadGenericoDatos id;
    
    private Boolean apartado;

    public ActualizacionProductoInventarioDTODatos(IdEntidadGenericoDatos id) {
        this.id = id;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public Boolean getApartado() {
        return apartado;
    }
    
     public void setApartado(Boolean apartado) {
        this.apartado = apartado;
    }
    
    public boolean tieneApartado(){
        return apartado != null;
    }
}
