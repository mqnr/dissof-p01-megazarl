
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;


public class ActualizacionProductoInventarioDTO {
    
    private IdEntidadGenerico id;
    
    private Boolean apartado;

    public ActualizacionProductoInventarioDTO(IdEntidadGenerico id) {
        this.id = id;
    }

    public IdEntidadGenerico getId() {
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
