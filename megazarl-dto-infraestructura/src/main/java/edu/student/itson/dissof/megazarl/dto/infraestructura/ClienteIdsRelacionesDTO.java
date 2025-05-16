
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.LinkedList;
import java.util.List;


public class ClienteIdsRelacionesDTO extends ClienteDTO {
    

    private IdDireccionDTO idDireccionEnvio;

    private List<IdCarritoComprasDTO> idsCarritosCompras = new LinkedList<>();

    public ClienteIdsRelacionesDTO(
            Long id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            IdDireccionDTO idDireccionEnvio,
            List<IdCarritoComprasDTO> idsCarritosCompras) {
        
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
            IdDireccionDTO idDireccionEnvio,
            List<IdCarritoComprasDTO> idsCarritosCompras) {
        
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
            List<IdCarritoComprasDTO> idsCarritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idsCarritosCompras = idsCarritosCompras;
        
    }

    @Override
    public IdDireccionDTO getIdDireccionEnvio() {
        return idDireccionEnvio;
    }

    @Override
    public List<IdCarritoComprasDTO> getIdsCarritosCompras() {
        
        return idsCarritosCompras;
        
    }
}
