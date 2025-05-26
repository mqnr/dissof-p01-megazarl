
package edu.student.itson.dissof.megazarl.administradorclientes.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashesUtils {
    
    /**
     * Método de utiliería que permite obtener el hash de una contraseña representada
     * por un arreglo de caracteres.
     * @param contrasenia Arreglo de caracteres qe representa la contraseña del cliente.
     * @return Objeto String que representa el hash de la contraseña en SHA-256.
     */
    public static String hashearContrasenia(char[] contrasenia){
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] bytes = new String(contrasenia).getBytes(StandardCharsets.UTF_8);

            byte[] hashBytes = md.digest(bytes);

            StringBuilder sb = new StringBuilder();
            
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            
            throw new RuntimeException("Error al crear el hash de la contraseña", ex);
        }
    }
}
