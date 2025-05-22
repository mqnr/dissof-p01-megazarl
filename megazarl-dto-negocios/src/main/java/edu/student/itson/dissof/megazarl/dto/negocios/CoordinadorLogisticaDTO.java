
package edu.student.itson.dissof.megazarl.dto.negocios;


public class CoordinadorLogisticaDTO {
    
    private Long id;
    
    private String nombres;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;

    public CoordinadorLogisticaDTO(Long id, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public CoordinadorLogisticaDTO(String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
