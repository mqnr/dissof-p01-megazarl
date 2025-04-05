
package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalException;
import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionMatrizDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;


public interface IAdministradorSucursales {
    
    public abstract boolean validarSucursal(Integer codigoSucursal);
    
    public abstract CodigosSucursalesDTO obtenerCodigosSucursales() throws SucursalesIdSucursalException;
    
    public abstract String obtenerCodigoPostal(Integer idSucursal) throws SucursalesIdSucursalException;
    
    public abstract String obtenerCalle(Integer idSucursal) throws SucursalesIdSucursalException;
    
    public abstract String obtenerNumero(Integer idSucursal) throws SucursalesIdSucursalException;
    
    public abstract DireccionMatrizDTO obtenerDireccionMatriz();
    
    public abstract Sucursal obtenerSucursal(Integer idSucursal);
}
