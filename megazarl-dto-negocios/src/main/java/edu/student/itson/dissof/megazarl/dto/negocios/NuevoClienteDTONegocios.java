
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * NuevoClienteDTONegocios.java
 
 Clase que representa los datos de un nuevo cliente.
 * 
 * @author Manuel Romo López
 */
public class NuevoClienteDTONegocios {
    
    /**
     * Objeto String que representa los nombres del cliente.
     */
    private String nombres;
    
    /**
     * Objeto String que representa el apellido paterno del cliente.
     */
    private String apellidoPaterno;
    
    /**
     * Objeto String que representa el apellido materno del cliente.
     */
    private String apellidoMaterno;
    
    /**
     * Objeto String que representa el telefono del cliente.
     */
    private String telefono;
    
    /**
     * Objeto String que representa el correo electrónico del cliente.
     */
    private String correoElectronico;
    
    /**
     * Arreglo char que representa la contraseña ingresada para el cliente.
     */
    private char[] contrasenia;

    /**
     * Consutructor de la clase
     * @param nombres Objeto String que representa los nombres del cliente.
     * @param apellidoPaterno Objeto String que representa el apellido paterno del cliente.
     * @param apellidoMaterno Objeto String que representa el apellido materno del cliente.
     * @param telefono Objeto String que representa el telefono del cliente.
     * @param correoElectronico Objeto String que representa el correo electrónico del cliente.
     * @param contrasenia Arreglo char que representa la contraseña ingresada para el cliente.
     */
    public NuevoClienteDTONegocios(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            char[] contrasenia) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
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

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public char[] getContrasenia() {
        return contrasenia;
    }
    
    
    
    

}
