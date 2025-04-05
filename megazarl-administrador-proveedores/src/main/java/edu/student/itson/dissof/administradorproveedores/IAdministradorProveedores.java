
package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;

public interface IAdministradorProveedores {
    
    public abstract boolean validarProveedor(Integer idProveedor);
    
    public abstract String obtenerDireccionImagenProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException;
    
    public abstract String obtenerNombreProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException;
    
    public abstract Proveedor obtenerProveedor(Integer idProveedor);
}
