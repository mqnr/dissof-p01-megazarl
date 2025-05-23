package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;

import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;
import java.util.Comparator;

import java.util.LinkedList;
import java.util.List;

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
    public boolean validarSucursal(IdSucursalDTONegocios idSucursalDTO) {
        
        if (idSucursalDTO == null || idSucursalDTO.getIdSucursal() == null || !Sucursal.existePorId(idSucursalDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String obtenerCodigoPostal(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException{
        
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTONegocios)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTONegocios sucursal = obtenerSucursal(idSucursalDTONegocios);

        if(sucursal == null){
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        IdDireccionDTONegocios idDireccionSucursal = new IdDireccionDTONegocios(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        
        DireccionDTONegocios direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        

        // Se recupera y devuelve el Código Postal.
        return direccionSucursal.getCodigoPostal();

    }

    @Override
    public String obtenerCalle(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException{
        
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTONegocios)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTONegocios sucursal = obtenerSucursal(idSucursalDTONegocios);

        if (sucursal == null) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        IdDireccionDTONegocios idDireccionSucursal = new IdDireccionDTONegocios(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        
        DireccionDTONegocios direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }

        // Se recupera y devuelve la Calle.
        return direccionSucursal.getCalle();
    }

    @Override
    public String obtenerNumero(IdSucursalDTONegocios idSucursalDTO) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException{
        
        // Se valida la ID de sucursal
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTONegocios sucursal = obtenerSucursal(idSucursalDTO);
        
        if (sucursal == null) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        IdDireccionDTONegocios idDireccionSucursal = new IdDireccionDTONegocios(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        
        DireccionDTONegocios direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
 
        // Se recupera y devuelve el Número.
        return direccionSucursal.getNumero();
    }

    @Override
    public SucursalDTONegocios obtenerSucursalMatriz() {
        
        SucursalDTONegocios sucursalMatriz = Sucursal.obtenerSucursalMatriz();

        return sucursalMatriz;
    }

    @Override
    public SucursalDTONegocios obtenerSucursal(IdSucursalDTONegocios idSucursalDTONegocios){
        
        return Sucursal.recuperarPorId(idSucursalDTONegocios);
        
    }

    @Override
    public List<InformacionSucursalInicioDTO> obtenerSucursales() {
        List<InformacionSucursalInicioDTO> listaSucursalInicioDTO = new LinkedList<>();

        // Se recorre la lista de Sucursales y se añade la información a la lista
        // de DTOs, de aquellas que esten registradas.
        
        List<SucursalDTO> listaSucursales = Sucursal.recuperarTodos();
        
        for (SucursalDTO sucursal: listaSucursales) {
            listaSucursalInicioDTO.add(new InformacionSucursalInicioDTO(
                    sucursal.getId(),
                    sucursal.esMatriz(),
                    sucursal.getIdDireccion())
            );
        }
        
        // Se ordena la lista de DTO, por id
        listaSucursalInicioDTO.sort(
            Comparator.comparing(dto -> dto.getIdSucursal().getId().toString())
        );
        
        return listaSucursalInicioDTO;
    }
}
