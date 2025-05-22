package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;;


class AdministradorProveedores implements IAdministradorProveedores {
    
    public AdministradorProveedores(){
        
    }
    
    @Override
    public ProveedorDTO obtenerProveedor(IdProveedorDTO idProveedorDTO){
        
        return Proveedor.recuperarPorId(idProveedorDTO);

    }

    @Override
    public String obtenerDireccionImagenProveedor(IdProveedorDTO idProveedorDTO) throws ProveedoresIdProveedorInvalidoException{
        
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedorDTO)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        ProveedorDTO proveedor = obtenerProveedor(idProveedorDTO);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        // Se obtiene y devuelve la imagen del proveedor.
        return proveedor.getDireccionImagen();
    }
    

    @Override
    public String obtenerNombreProveedor(IdProveedorDTO idProveedorDTO) throws ProveedoresIdProveedorInvalidoException{
        
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedorDTO)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        ProveedorDTO proveedor = obtenerProveedor(idProveedorDTO);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        // Se obtiene y devuelve el nombre del proveedor.
        return proveedor.getNombre();
    }
    
    @Override
    public boolean validarProveedor(IdProveedorDTO idProveedorDTO){
        
        if (idProveedorDTO == null || idProveedorDTO.getIdProveedor() == null || !Proveedor.existePorId(idProveedorDTO)) {
            return false;
        }
        
        return true;
        
    }
}
