package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;

import java.util.LinkedList;
import java.util.List;

class AdministradorSucursales implements IAdministradorSucursales {

    
    private final IAdministradorDirecciones administradorDirecciones;
    
    public AdministradorSucursales(IAdministradorDirecciones administradorDirecciones){
        this.administradorDirecciones = administradorDirecciones;
    }
    
    @Override
    public CodigosSucursalesDTO obtenerCodigosSucursales(){
        List<IdEntidadGenerico> idsSucursales = new LinkedList<>();

        for (SucursalDTO sucursal: Sucursal.recuperarTodos()) {
            idsSucursales.add(sucursal.getId());
        }
        CodigosSucursalesDTO codigosSucursalesDTO = new CodigosSucursalesDTO(idsSucursales);

        return codigosSucursalesDTO;
    }
    
    @Override
    public boolean validarSucursal(IdSucursalDTO idSucursalDTO) {
        
        if (idSucursalDTO == null || idSucursalDTO.getIdSucursal() == null || !Sucursal.existePorId(idSucursalDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String obtenerCodigoPostal(IdSucursalDTO idSucursalDTO) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException{
        
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTO sucursal = obtenerSucursal(idSucursalDTO);

        if(sucursal == null){
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        IdDireccionDTO idDireccionSucursal = new IdDireccionDTO(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        
        DireccionDTO direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        

        // Se recupera y devuelve el Código Postal.
        return direccionSucursal.getCodigoPostal();

    }

    @Override
    public String obtenerCalle(IdSucursalDTO idSucursalDTO) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException{
        
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTO sucursal = obtenerSucursal(idSucursalDTO);

        if (sucursal == null) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        IdDireccionDTO idDireccionSucursal = new IdDireccionDTO(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        
        DireccionDTO direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }

        // Se recupera y devuelve la Calle.
        return direccionSucursal.getCalle();
    }

    @Override
    public String obtenerNumero(IdSucursalDTO idSucursalDTO) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException{
        
        // Se valida la ID de sucursal
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        SucursalDTO sucursal = obtenerSucursal(idSucursalDTO);
        
        if (sucursal == null) {
            throw new SucursalesIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }

        IdDireccionDTO idDireccionSucursal = new IdDireccionDTO(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
        
        DireccionDTO direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new SucursalesIdDireccionInvalidoException("El ID de dirección de sucursal es inválido.");
        }
 
        // Se recupera y devuelve el Número.
        return direccionSucursal.getNumero();
    }

    @Override
    public SucursalDTO obtenerSucursalMatriz() {
        
        SucursalDTO sucursalMatriz = Sucursal.obtenerSucursalMatriz();

        return sucursalMatriz;
    }

    @Override
    public SucursalDTO obtenerSucursal(IdSucursalDTO idSucursalDTO){
        
        return Sucursal.recuperarPorId(idSucursalDTO);
        
    }
}
