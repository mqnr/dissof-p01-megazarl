
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class ActualizacionProductoInventarioDTO {
    
    private Long id;
    
    private Boolean apartado;

    public ActualizacionProductoInventarioDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
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
