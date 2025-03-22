
package edu.student.itson.dissof.megazarl.dto;


public class NombreApellidoClienteDTO {
    private String nombresCliente;
    private String apellidoMaternoCliente;

    public NombreApellidoClienteDTO(String nombresCliente, String apellidoMaternoCliente) {
        this.nombresCliente = nombresCliente;
        this.apellidoMaternoCliente = apellidoMaternoCliente;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getApellidoMaternoCliente() {
        return apellidoMaternoCliente;
    }

    public void setApellidoMaternoCliente(String apellidoMaternoCliente) {
        this.apellidoMaternoCliente = apellidoMaternoCliente;
    }
    
    
}
