
package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;
import java.util.Arrays;


public class FAdministradorSucursales implements IAdministradorSucursales {

    @Override
    public boolean validarSucursal(Integer idSucursal) {
        return (idSucursal == 1 || idSucursal == 2 || idSucursal == 3);
    }
    
    public CodigosSucursalesDTO obtenerCodigosSucursales(){
        
        return new CodigosSucursalesDTO(Arrays.asList(1,2,3));
    }
    
    public String obtenerCodigoPostal(Integer idSucursal){
        if(idSucursal == 1){
            return "84166";
        } else if(idSucursal == 2){
            return "83663";
        } else if(idSucursal == 3){
            return "85000";
        } else{
            return null;
        }
    }
}
