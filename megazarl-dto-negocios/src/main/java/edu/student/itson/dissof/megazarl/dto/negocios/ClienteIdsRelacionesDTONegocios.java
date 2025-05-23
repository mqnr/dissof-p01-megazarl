
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class ClienteIdsRelacionesDTONegocios extends ClienteDTONegocios {
    

    private IdEntidadGenericoNegocios idDireccionEnvio;

    private List<IdEntidadGenericoNegocios> idsCarritosCompras = new LinkedList<>();

    public ClienteIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            IdEntidadGenericoNegocios idDireccionEnvio,
            List<IdEntidadGenericoNegocios> idsCarritosCompras) {
        
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
    
    public ClienteIdsRelacionesDTONegocios(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            IdEntidadGenericoNegocios idDireccionEnvio,
            List<IdEntidadGenericoNegocios> idsCarritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idDireccionEnvio = idDireccionEnvio;
        this.idsCarritosCompras = idsCarritosCompras;
        
    }
    
    public ClienteIdsRelacionesDTONegocios(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            List<IdEntidadGenericoNegocios> idsCarritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.idsCarritosCompras = idsCarritosCompras;
        
    }

    @Override
    public IdEntidadGenericoNegocios getIdDireccionEnvio() {
        return idDireccionEnvio;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsCarritosCompras() {
        
        return idsCarritosCompras;
        
    }
}
