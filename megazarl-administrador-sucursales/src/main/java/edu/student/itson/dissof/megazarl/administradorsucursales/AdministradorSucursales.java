package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalException;
import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionMatrizDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.SucursalON;

import java.util.LinkedList;
import java.util.List;

class AdministradorSucursales implements IAdministradorSucursales {
    private final List<SucursalON> listaSucursales;

    public AdministradorSucursales(List<SucursalON> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    @Override
    public boolean validarSucursal(Integer idSucursal) {
        for (SucursalON sucursal: listaSucursales) {
            if (sucursal.getId().equals(idSucursal)){
                return true;
            }
        }
        return false;
    }

    @Override
    public CodigosSucursalesDTO obtenerCodigosSucursales(){
        List<Integer> idsSucursales = new LinkedList<>();

        for (SucursalON sucursal: listaSucursales) {
            idsSucursales.add(sucursal.getId());
        }
        CodigosSucursalesDTO codigosSucursalesDTO = new CodigosSucursalesDTO(idsSucursales);

        return codigosSucursalesDTO;
    }

    @Override
    public String obtenerCodigoPostal(Integer idSucursal) throws SucursalesIdSucursalException{
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursal)) {
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }

        SucursalON sucursal = obtenerSucursal(idSucursal);

        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }

        // Se recupera y devuelve el Código Postal.
        return sucursal.getCodigoPostal();

    }

    @Override
    public String obtenerCalle(Integer idSucursal) throws SucursalesIdSucursalException {
        // Se valida el ID de sucursal.
        if (!validarSucursal(idSucursal)) {
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }

        SucursalON sucursal = obtenerSucursal(idSucursal);

        if (sucursal == null) {
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }

        // Se recupera y devuelve la Calle.
        return sucursal.getCalle();
    }

    @Override
    public String obtenerNumero(Integer idSucursal) throws SucursalesIdSucursalException{
        // Se valida la ID de sucursal
        if (!validarSucursal(idSucursal)) {
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }

        SucursalON sucursal = obtenerSucursal(idSucursal);

        if(sucursal == null){
            throw new SucursalesIdSucursalException("El ID de sucursal: " + idSucursal + " es inválido.");
        }

        // Se recupera y devuelve el Número.
        return sucursal.getNumero();
    }

    @Override
    public DireccionMatrizDTO obtenerDireccionMatriz() {
        DireccionMatrizDTO direccionMatrizDTO = null;
        for (SucursalON sucursal: listaSucursales) {
            if (sucursal.getEsMatriz()) {
                direccionMatrizDTO =
                        new DireccionMatrizDTO(sucursal.getCodigoPostal(), sucursal.getCalle(), sucursal.getNumero());
            }
        }

        return direccionMatrizDTO;
    }

    @Override
    public SucursalON obtenerSucursal(Integer idSucursal){
        SucursalON sucursalRecuperada = null;
        for (SucursalON sucursal: listaSucursales) {
            if (sucursal.getId().equals(idSucursal)) {
                sucursalRecuperada = sucursal;
            }
        }

        return sucursalRecuperada;
    }
}
