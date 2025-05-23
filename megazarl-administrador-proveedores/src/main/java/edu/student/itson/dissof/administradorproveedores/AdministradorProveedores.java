package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;;


class AdministradorProveedores implements IAdministradorProveedores {
    
    public AdministradorProveedores(){
        
    }
    
    @Override
    public ProveedorDTONegocios obtenerProveedor(IdProveedorDTONegocios idProveedorDTO){
        
        return Proveedor.recuperarPorId(idProveedorDTO);

    }

    @Override
    public String obtenerDireccionImagenProveedor(IdProveedorDTONegocios idProveedorDTO) throws ProveedoresIdProveedorInvalidoException{
        
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedorDTO)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        ProveedorDTONegocios proveedor = obtenerProveedor(idProveedorDTO);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        // Se obtiene y devuelve la imagen del proveedor.
        return proveedor.getDireccionImagen();
    }
    

    @Override
    public String obtenerNombreProveedor(IdProveedorDTONegocios idProveedorDTO) throws ProveedoresIdProveedorInvalidoException{
        
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedorDTO)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        ProveedorDTONegocios proveedor = obtenerProveedor(idProveedorDTO);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        // Se obtiene y devuelve el nombre del proveedor.
        return proveedor.getNombre();
    }
    
    @Override
    public boolean validarProveedor(IdProveedorDTONegocios idProveedorDTO){
        
        if (idProveedorDTO == null || idProveedorDTO.getIdProveedor() == null || !Proveedor.existePorId(idProveedorDTO)) {
            return false;
        }
        
        return true;
        
    }
    /**
    @Override
    public boolean agregarProveedor(ProveedorDTO proveedorDTO) throws ProveedorNoRegistradoException {
        if (proveedorDTO == null) {
            return false;
        } else {
            Proveedor.agregar(proveedorDTO);
            return true;
        }
    }
    **/
}
