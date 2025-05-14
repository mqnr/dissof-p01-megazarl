package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;

import java.util.LinkedList;
import java.util.List;

class AdministradorSucursales implements IAdministradorSucursales {

    @Override
    public CodigosSucursalesDTO obtenerCodigosSucursales(){
        List<Long> idsSucursales = new LinkedList<>();

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
    public String obtenerCodigoPostal(IdSucursalDTO idSucursalDTO) throws SucursalesIdSucursalException{
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalException("El ID de sucursal es inválido.");
        }

        SucursalDTO sucursal = obtenerSucursal(idSucursalDTO);

        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal es inválido.");
        }

        // Se recupera y devuelve el Código Postal.
        return sucursal.getDireccion().getCodigoPostal();

    }

    @Override
    public String obtenerCalle(IdSucursalDTO idSucursalDTO) throws SucursalesIdSucursalException {
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalException("El ID de sucursal es inválido.");
        }

        SucursalDTO sucursal = obtenerSucursal(idSucursalDTO);

        if (sucursal == null) {
            throw new SucursalesIdSucursalException("El ID de sucursal es inválido.");
        }

        // Se recupera y devuelve la Calle.
        return sucursal.getDireccion().getCalle();
    }

    @Override
    public String obtenerNumero(IdSucursalDTO idSucursalDTO) throws SucursalesIdSucursalException{
        // Se valida la ID de sucursal
        if (!validarSucursal(idSucursalDTO)) {
            throw new SucursalesIdSucursalException("El ID de sucursal es inválido.");
        }

        SucursalDTO sucursal = obtenerSucursal(idSucursalDTO);

        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal es inválido.");
        }

        // Se recupera y devuelve el Número.
        return sucursal.getDireccion().getNumero();
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
