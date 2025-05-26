package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesPersistenciaException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;

import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSucursalInicioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import java.util.Comparator;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class AdministradorSucursales implements IAdministradorSucursales {

    
    private final IAdministradorDirecciones administradorDirecciones;
    
    public AdministradorSucursales(IAdministradorDirecciones administradorDirecciones){
        this.administradorDirecciones = administradorDirecciones;
    }
    
    @Override
    public CodigosSucursalesDTONegocios obtenerCodigosSucursales(){
        List<IdEntidadGenericoNegocios> idsSucursales = new LinkedList<>();

        for (SucursalDTONegocios sucursal: Sucursal.recuperarTodos()) {
            idsSucursales.add(sucursal.getId());
        }
        CodigosSucursalesDTONegocios codigosSucursalesDTO = new CodigosSucursalesDTONegocios(idsSucursales);

        return codigosSucursalesDTO;
    }
    
    @Override
    public boolean validarSucursal(IdSucursalDTONegocios idSucursalDTO)
            throws SucursalesPersistenciaException{
        
        try {
            if (idSucursalDTO == null || idSucursalDTO.getIdSucursal() == null || !Sucursal.existePorId(idSucursalDTO)) {
                return false;
            }
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new SucursalesPersistenciaException(ex.getMessage());
        }
        
        return true;
    }

    @Override
    public String obtenerCodigoPostal(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException,
            SucursalesPersistenciaException{
        
        try {
            // Se valida el ID de sucursal.
            if (!validarSucursal(idSucursalDTONegocios)) {
                throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
            }
        } catch (SucursalesPersistenciaException ex) {
            throw new SucursalesPersistenciaException(ex.getMessage());
        }

        SucursalDTONegocios sucursal = obtenerSucursal(idSucursalDTONegocios);

        if(sucursal == null){
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        
        DireccionDTONegocios direccionSucursal = sucursal.getDireccion();
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        

        // Se recupera y devuelve el Código Postal.
        return direccionSucursal.getCodigoPostal();

    }

    @Override
    public String obtenerCalle(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException,
            SucursalesPersistenciaException{
        
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTONegocios)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTONegocios sucursal = obtenerSucursal(idSucursalDTONegocios);

        if (sucursal == null) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        
        DireccionDTONegocios direccionSucursal = sucursal.getDireccion();
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }

        // Se recupera y devuelve la Calle.
        return direccionSucursal.getCalle();
    }

    @Override
    public String obtenerNumero(IdSucursalDTONegocios idSucursalDTO) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException,
            SucursalesPersistenciaException{
        
        // Se valida la ID de sucursal
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTONegocios sucursal = obtenerSucursal(idSucursalDTO);
        
        if (sucursal == null) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        DireccionDTONegocios direccionSucursal = sucursal.getDireccion();
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
 
        // Se recupera y devuelve el Número.
        return direccionSucursal.getNumero();
    }

    @Override
    public SucursalDTONegocios obtenerSucursalMatriz() throws SucursalesPersistenciaException{
        
        SucursalDTONegocios sucursalMatriz;
        try {
            sucursalMatriz = Sucursal.obtenerSucursalMatriz();
        } catch (RegistroInexistenteNegocioException ex) {
            throw new SucursalesPersistenciaException(ex.getMessage());
        }

        return sucursalMatriz;
    }

    @Override
    public SucursalDTONegocios obtenerSucursal(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesPersistenciaException{
        
        try {
            return Sucursal.recuperarPorId(idSucursalDTONegocios);
        } catch (FormatoIdInvalidoNegocioException | ParametroNuloNegocioException | RegistroInexistenteNegocioException ex) {
            throw new SucursalesPersistenciaException(ex.getMessage());
        }
        
    }

    @Override
    public List<InformacionSucursalInicioDTONegocios> obtenerSucursales() {
        List<InformacionSucursalInicioDTONegocios> listaSucursalInicioDTO = new LinkedList<>();

        // Se recorre la lista de Sucursales y se añade la información a la lista
        // de DTOs, de aquellas que esten registradas.
        
        List<SucursalDTONegocios> listaSucursales = Sucursal.recuperarTodos();
        
        for (SucursalDTONegocios sucursal: listaSucursales) {
            listaSucursalInicioDTO.add(new InformacionSucursalInicioDTONegocios(
                    sucursal.getId(),
                    sucursal.getEsMatriz(),
                    sucursal.getDireccion())
            );
        }
        
        // Se ordena la lista de DTO, por id
        listaSucursalInicioDTO.sort(
            Comparator.comparing(dto -> dto.getIdSucursal().getId().toString())
        );
        
        return listaSucursalInicioDTO;
    }
}
