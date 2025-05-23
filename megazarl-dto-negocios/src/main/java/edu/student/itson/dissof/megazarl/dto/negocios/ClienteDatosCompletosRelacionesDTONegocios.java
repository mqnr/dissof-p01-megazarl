
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class ClienteDatosCompletosRelacionesDTONegocios extends ClienteDTONegocios{
    
    /**
     * Objeto DireccionDTONegocios que representa la dirección de envío del cliente.
     */
    private DireccionDTONegocios direccionEnvio;
    
    /**
     * Objeto {@literal List<CarritoComprasDTONegocios>} que representa la lista de carritos
     * de compra vigentes y no vigentes que tiene o ha tenido.
     */
    private List<CarritoComprasDTONegocios> carritosCompras = new LinkedList<>();

    public ClienteDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTONegocios direccionEnvio,
            List<CarritoComprasDTONegocios> carritosCompras) {
        
        super(
            id,
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        this.carritosCompras = carritosCompras;
        
    }
    
    public ClienteDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTONegocios direccionEnvio) {
        
        super(
            id,
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        
    }
    
    public ClienteDatosCompletosRelacionesDTONegocios(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTONegocios direccionEnvio,
            List<CarritoComprasDTONegocios> carritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        this.carritosCompras = carritosCompras;
        
    }
    
    public ClienteDatosCompletosRelacionesDTONegocios(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTONegocios direccionEnvio) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        
    }

    public List<CarritoComprasDTONegocios> getCarritosCompras(){
        
        return carritosCompras;
        
    }
    
    public DireccionDTONegocios getDireccionEnvio(){
        return direccionEnvio;
    }
    
    @Override
    public IdEntidadGenericoNegocios getIdDireccionEnvio() {
        return direccionEnvio.getId();
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsCarritosCompras() {
        
        List<IdEntidadGenericoNegocios> idsCarritosCompras = new LinkedList<>();
        
        for(CarritoComprasDTONegocios carritoComprasDTO: carritosCompras){
            idsCarritosCompras.add(carritoComprasDTO.getId());
        }
        
        return idsCarritosCompras;
        
    }
    
}
