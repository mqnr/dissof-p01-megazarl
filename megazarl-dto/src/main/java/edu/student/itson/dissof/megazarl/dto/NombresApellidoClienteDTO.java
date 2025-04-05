package edu.student.itson.dissof.megazarl.dto;

/**
 * NombresApellidoClienteDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * los nombres y apellido materno de un cliente, utilizados para identificación
 * y presentación en la interfaz de usuario.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class NombresApellidoClienteDTO {
    private String nombresCliente;
    private String apellidoMaternoCliente;

    public NombresApellidoClienteDTO(String nombresCliente, String apellidoMaternoCliente) {
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
