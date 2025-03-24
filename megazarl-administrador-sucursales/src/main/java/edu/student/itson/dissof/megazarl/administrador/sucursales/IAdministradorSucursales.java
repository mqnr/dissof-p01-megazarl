
package edu.student.itson.dissof.megazarl.administrador.sucursales;

import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;


public interface IAdministradorSucursales {
    
    public abstract boolean validarSucursal(Integer codigoSucursal);
    
    public abstract CodigosSucursalesDTO obtenerCodigosSucursales();
    
    public abstract String obtenerCodigoPostal(Integer idSucursal);
    
}
