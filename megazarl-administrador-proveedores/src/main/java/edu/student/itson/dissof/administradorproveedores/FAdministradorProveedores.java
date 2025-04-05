
package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;
import java.util.List;


public class FAdministradorProveedores implements IAdministradorProveedores{

    private List<Proveedor> listaProveedores;

    public FAdministradorProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }
    
    @Override
    public boolean validarProveedor(Integer idProveedor){
        for(Proveedor proveedor: listaProveedores){
            if(proveedor.getId() == idProveedor){
                return true;
            }
        }
        
        return false;
    }
    
    public Proveedor obtenerProveedor(Integer idProveedor){
        Proveedor proveedorRecuperado = null;
        for(Proveedor proveedor: listaProveedores){
            if(proveedor.getId() == idProveedor){
                proveedorRecuperado = proveedor;
            }
        }
        return proveedorRecuperado;
    }
    
    @Override
    public String obtenerDireccionImagenProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException{
        
        // Se valida el ID del proevedor.
        if(!validarProveedor(idProveedor)){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        Proveedor proveedor = obtenerProveedor(idProveedor);
        
        if(proveedor == null){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        // Se obtiene y devuelve la imagen del proveedor.
        return proveedor.getDireccionImagenProveedor();
    }
    
    
    @Override
    public String obtenerNombreProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException{
        // Se valida el ID del proevedor.
        if(!validarProveedor(idProveedor)){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        Proveedor proveedor = obtenerProveedor(idProveedor);
        
        if(proveedor == null){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        // Se obtiene y devuelve el nombre del proveedor.
        return proveedor.getNombre();

    }
    
    
}
