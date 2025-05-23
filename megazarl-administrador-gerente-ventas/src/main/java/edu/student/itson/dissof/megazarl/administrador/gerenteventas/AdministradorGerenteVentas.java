package edu.student.itson.dissof.megazarl.administrador.gerenteventas;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.GerenteVentas;
/**
 * AdministradorGerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class AdministradorGerenteVentas implements IAdministradorGerenteVentas {

    @Override
    public boolean validarGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO) {
        if (idGerenteVentasDTO == null || idGerenteVentasDTO.getIdGerenteVentas() == null || !GerenteVentas.existePorId(idGerenteVentasDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public GerenteVentasDTONegocios obtenerGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO) {
        return GerenteVentas.recuperarPorId(idGerenteVentasDTO);
    }

    @Override
    public NombresApellidoGerenteVentasDTONegocios obtenerNombresApellidoGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO) throws IdGerenteVentasInvalidoException {
        if(!validarGerenteVentas(idGerenteVentasDTO)){
            throw new IdGerenteVentasInvalidoException("El ID del gerente de ventas es inválido.");
        }
        
        GerenteVentasDTONegocios gerenteVentas = obtenerGerenteVentas(idGerenteVentasDTO);
        
        if(gerenteVentas == null){
            throw new IdGerenteVentasInvalidoException("El ID del gerente de ventas es inválido.");
        }

        return new NombresApellidoGerenteVentasDTONegocios(gerenteVentas.getNombre(), gerenteVentas.getApellidoPaterno());
    }

}