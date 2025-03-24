package edu.student.itson.dissof.megazarl.administradorclientes;
import edu.student.itson.dissof.megazarl.dto.DireccionEntradaDTO;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;

public class FAdministradorClientes implements IAdministradorClientes {


    @Override
    public boolean validarIdCliente(Integer idCliente) {
        if(idCliente == 3){
            return true;
        }
        return false;
    }

    @Override
    public NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente) {

        if(idCliente == 3){
            return new NombreApellidoClienteDTO(
                "Juan",
                "Pérez"
            );
        }
        return null;
        
    }
    
    
    @Override
    public String obtenerCodigoPostalCliente(Integer idCliente){
        if(idCliente == 1){
            return "85000";
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerCalleCliente(Integer idCliente){
        if(idCliente == 1){
            return "Guerrero";
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerColoniaCliente(Integer idCliente){
        if(idCliente == 1){
            return "Centro";
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerEstadoCliente(Integer idCliente){
        if(idCliente == 1){
            return "Sonora";
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerCiudadCliente(Integer idCliente){
        if(idCliente == 1){
            return "Sonora";
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerNumeroCliente(Integer idCliente){
        if(idCliente == 1){
            return "2034";
        } else{
            return null;
        }
    }
    
    @Override
    public boolean registrarCliente(DireccionEntradaDTO direccionEntradaDTO){
        return true;
    }
    
    
    
    
 
    
    
    
}
