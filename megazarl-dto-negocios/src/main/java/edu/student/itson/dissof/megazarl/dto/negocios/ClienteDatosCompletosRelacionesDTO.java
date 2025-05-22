
package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.LinkedList;
import java.util.List;


public class ClienteDatosCompletosRelacionesDTO extends ClienteDTO{
    
    /**
     * Objeto DireccionDTO que representa la dirección de envío del cliente.
     */
    private DireccionDTO direccionEnvio;
    
    /**
     * Objeto {@literal List<CarritoComprasDTO>} que representa la lista de carritos
     * de compra vigentes y no vigentes que tiene o ha tenido.
     */
    private List<CarritoComprasDTO> carritosCompras = new LinkedList<>();

    public ClienteDatosCompletosRelacionesDTO(
            Long id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTO direccionEnvio,
            List<CarritoComprasDTO> carritosCompras) {
        
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
    
    public ClienteDatosCompletosRelacionesDTO(
            Long id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTO direccionEnvio) {
        
        super(
            id,
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        
    }
    
    public ClienteDatosCompletosRelacionesDTO(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTO direccionEnvio,
            List<CarritoComprasDTO> carritosCompras) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        this.carritosCompras = carritosCompras;
        
    }
    
    public ClienteDatosCompletosRelacionesDTO(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correoElectronico,
            DireccionDTO direccionEnvio) {
        
        super(
            nombres,
            apellidoPaterno,
            apellidoMaterno, 
            telefono,
            correoElectronico);
        
        this.direccionEnvio = direccionEnvio;
        
    }

    public List<CarritoComprasDTO> getCarritosCompras(){
        
        return carritosCompras;
        
    }
    
    public DireccionDTO getDireccionEnvio(){
        return direccionEnvio;
    }
    
    @Override
    public Long getIdDireccionEnvio() {
        return direccionEnvio.getId();
    }

    @Override
    public List<Long> getIdsCarritosCompras() {
        
        List<Long> idsCarritosCompras = new LinkedList<>();
        
        for(CarritoComprasDTO carritoComprasDTO: carritosCompras){
            idsCarritosCompras.add(carritoComprasDTO.getId());
        }
        
        return idsCarritosCompras;
        
    }
    
}
