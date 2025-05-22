
package edu.student.itson.dissof.dto.datos;

import java.util.LinkedList;
import java.util.List;


public class ClienteIdsRelacionesDTO extends ClienteDTO {
    

    private Long idDireccionEnvio;

    private List<Long> idsCarritosCompras = new LinkedList<>();

    public ClienteIdsRelacionesDTO(
            Long id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            Long idDireccionEnvio,
            List<Long> idsCarritosCompras) {
        
        super(
            id,
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idDireccionEnvio = idDireccionEnvio;
        this.idsCarritosCompras = idsCarritosCompras;
        
    }
    
    public ClienteIdsRelacionesDTO(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            Long idDireccionEnvio,
            List<Long> idsCarritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idDireccionEnvio = idDireccionEnvio;
        this.idsCarritosCompras = idsCarritosCompras;
        
    }
    
    public ClienteIdsRelacionesDTO(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            List<Long> idsCarritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idsCarritosCompras = idsCarritosCompras;
        
    }

    @Override
    public Long getIdDireccionEnvio() {
        return idDireccionEnvio;
    }

    @Override
    public List<Long> getIdsCarritosCompras() {
        
        return idsCarritosCompras;
        
    }
}
