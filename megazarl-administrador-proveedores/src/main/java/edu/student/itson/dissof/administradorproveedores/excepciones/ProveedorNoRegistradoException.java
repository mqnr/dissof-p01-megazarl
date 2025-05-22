/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.student.itson.dissof.administradorproveedores.excepciones;

/**
 *
 * @author Luis Rafael
 */
public class ProveedorNoRegistradoException extends Exception{

    /**
     * Contructor por defecto.
     */
    public ProveedorNoRegistradoException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProveedorNoRegistradoException(String message) {
        super(message);
    }
    
}
