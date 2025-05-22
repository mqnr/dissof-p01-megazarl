
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.LinkedList;
import java.util.List;


public class ClienteIdsRelacionesDTO extends ClienteDTO {
    

    private IdEntidadGenerico idDireccionEnvio;

    private List<IdEntidadGenerico> idsCarritosCompras = new LinkedList<>();

    public ClienteIdsRelacionesDTO(
            IdEntidadGenerico id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            IdEntidadGenerico idDireccionEnvio,
            List<IdEntidadGenerico> idsCarritosCompras) {
        
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
            IdEntidadGenerico idDireccionEnvio,
            List<IdEntidadGenerico> idsCarritosCompras) {
        
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
            List<IdEntidadGenerico> idsCarritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idsCarritosCompras = idsCarritosCompras;
        
    }

    @Override
    public IdEntidadGenerico getIdDireccionEnvio() {
        return idDireccionEnvio;
    }

    @Override
    public List<IdEntidadGenerico> getIdsCarritosCompras() {
        
        return idsCarritosCompras;
        
    }
}
