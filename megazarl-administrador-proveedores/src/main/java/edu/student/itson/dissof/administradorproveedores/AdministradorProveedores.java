package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;

import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProveedorInicioDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
;



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