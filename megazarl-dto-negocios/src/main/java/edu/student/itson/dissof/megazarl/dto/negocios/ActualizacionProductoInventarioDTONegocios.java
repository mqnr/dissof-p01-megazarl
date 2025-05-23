
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class ActualizacionProductoInventarioDTONegocios {
    
    private IdEntidadGenericoNegocios id;
    
    private Boolean apartado;

    public ActualizacionProductoInventarioDTONegocios(IdEntidadGenericoNegocios id) {
        this.id = id;
    }

    public IdEntidadGenericoNegocios getId() {
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
