package edu.student.itson.dissof.megazarl.dto.modelos;

public record ClienteDTO(
        Integer id,
        String nombres,
        String apellidoPaterno,
        String apellidoMaterno,
        String codigoPostalEnvio,
        String calleEnvio,
        String numeroDomicilioEnvio) {

}
