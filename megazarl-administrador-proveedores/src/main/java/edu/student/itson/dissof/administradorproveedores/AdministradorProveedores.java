package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresPersistenciaException;

import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProveedorInicioDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
;



class AdministradorProveedores implements IAdministradorProveedores {
    
    public AdministradorProveedores(){
        
    }
    
    @Override
    public ProveedorDTONegocios obtenerProveedor(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws ProveedoresPersistenciaException{
        
        try {
            return Proveedor.recuperarPorId(idProveedorDTONegocios);
        } catch (FormatoIdInvalidoNegocioException | ParametroNuloNegocioException | RegistroInexistenteNegocioException ex) {
            throw new ProveedoresPersistenciaException(ex.getMessage());
        }

    }

    @Override
    public String obtenerDireccionImagenProveedor(IdProveedorDTONegocios idProveedorDTO) 
            throws ProveedoresIdProveedorInvalidoException,
            ProveedoresPersistenciaException{
        
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
    public String obtenerNombreProveedor(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws ProveedoresIdProveedorInvalidoException,
            ProveedoresPersistenciaException{
        
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedorDTONegocios)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        ProveedorDTONegocios proveedor = obtenerProveedor(idProveedorDTONegocios);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }

        // Se obtiene y devuelve el nombre del proveedor.
        return proveedor.getNombre();
    }
    
    @Override
    public boolean validarProveedor(IdProveedorDTONegocios idProveedorDTO) 
            throws ProveedoresPersistenciaException{
        
        try {
            if (idProveedorDTO == null || idProveedorDTO.getIdProveedor() == null || !Proveedor.existePorId(idProveedorDTO)) {
                return false;
            }
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new ProveedoresPersistenciaException(ex.getMessage());
        }
        
        return true;
        
    }

    @Override
    public List<InformacionProveedorInicioDTONegocios> obtenerProveedores() {
        List<InformacionProveedorInicioDTONegocios> listaProveedorInicioDTO = new LinkedList<>();

        // Se recorre la lista de Proveedores y se añade la información a la lista
        // de DTOs, de aquellos que esten registrados.
        
        List<ProveedorDTONegocios> listaProveedores = Proveedor.recuperarTodos();
        
        for (ProveedorDTONegocios proveedor: listaProveedores) {
            listaProveedorInicioDTO.add(new InformacionProveedorInicioDTONegocios(
                    proveedor.getId(),
                    proveedor.getNombre(),
                    proveedor.getTelefono(),
                    proveedor.getCorreoElectronico(),
                    proveedor.getDireccionImagen())
            );
        }
        
        // Se ordena la lista de DTO, alfabéticamente
        listaProveedorInicioDTO.sort(Comparator.comparing(InformacionProveedorInicioDTONegocios::getNombreProveedor));
        
        return listaProveedorInicioDTO;
    }

}