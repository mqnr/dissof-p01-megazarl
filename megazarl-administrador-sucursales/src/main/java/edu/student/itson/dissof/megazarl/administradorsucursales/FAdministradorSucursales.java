
package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalException;
import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionMatrizDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;
import java.util.LinkedList;
import java.util.List;


public class FAdministradorSucursales implements IAdministradorSucursales {

    private List<Sucursal> listaSucursales;

    public FAdministradorSucursales(List<Sucursal> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }
    
    @Override
    public boolean validarSucursal(Integer idSucursal) {
        for(Sucursal sucursal: listaSucursales){
            if(sucursal.getId() == idSucursal){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public CodigosSucursalesDTO obtenerCodigosSucursales(){
        
        List<Integer> idsSucursales = new LinkedList<>();

        for(Sucursal sucursal: listaSucursales){
            idsSucursales.add(sucursal.getId());
        }
        CodigosSucursalesDTO codigosSucursalesDTO = new CodigosSucursalesDTO(idsSucursales);
        
        return codigosSucursalesDTO;
    }
    
    @Override
    public String obtenerCodigoPostal(Integer idSucursal) throws SucursalesIdSucursalException{
        
        // Se valida el ID de sucursal.
        if(!validarSucursal(idSucursal)){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }
        
        Sucursal sucursal = obtenerSucursal(idSucursal);
        
        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }
        
        // Se recupera y devuelve el Código Postal.
        return sucursal.getCodigoPostal();
        
    }
    
    @Override
    public String obtenerCalle(Integer idSucursal) throws SucursalesIdSucursalException{
        
        // Se valida el ID de sucursal.
        if(!validarSucursal(idSucursal)){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }
        
        Sucursal sucursal = obtenerSucursal(idSucursal);
        
        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }
        
        // Se recupera y devuelve la Calle.
        return sucursal.getCalle();
    }
    
     @Override
    public String obtenerNumero(Integer idSucursal) throws SucursalesIdSucursalException{
        
        // Se valida el ID de sucursal.
        if(!validarSucursal(idSucursal)){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }
        
        Sucursal sucursal = obtenerSucursal(idSucursal);
        
        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }
        
        // Se recupera y devuelve el Número.
        return sucursal.getNumero();
    }

    @Override
    public DireccionMatrizDTO obtenerDireccionMatriz() {
        
        DireccionMatrizDTO direccionMatrizDTO = null;
        for(Sucursal sucursal: listaSucursales){
            if(sucursal.getEsMatriz()){
                direccionMatrizDTO = 
                    new DireccionMatrizDTO(sucursal.getCodigoPostal(), sucursal.getCalle(), sucursal.getNumero());
            }
        }
        
        return direccionMatrizDTO;

    }
    
    @Override
    public Sucursal obtenerSucursal(Integer idSucursal){
        
        Sucursal sucursalRecuperada = null;
        for(Sucursal sucursal: listaSucursales){
            if(sucursal.getId().equals(idSucursal)){
                sucursalRecuperada = sucursal;
            }
        }
        
        return sucursalRecuperada;
    }
}
