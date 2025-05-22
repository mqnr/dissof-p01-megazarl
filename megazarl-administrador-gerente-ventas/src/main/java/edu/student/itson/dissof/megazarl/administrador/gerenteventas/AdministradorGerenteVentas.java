package edu.student.itson.dissof.megazarl.administrador.gerenteventas;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTO;
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
    public boolean validarGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO) {
        if (idGerenteVentasDTO == null || idGerenteVentasDTO.getIdGerenteVentas() == null || !GerenteVentas.existePorId(idGerenteVentasDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public GerenteVentasDTO obtenerGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO) {
        return GerenteVentas.recuperarPorId(idGerenteVentasDTO);
    }

    @Override
    public NombresApellidoGerenteVentasDTO obtenerNombresApellidoGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO) throws IdGerenteVentasInvalidoException {
        if(!validarGerenteVentas(idGerenteVentasDTO)){
            throw new IdGerenteVentasInvalidoException("El ID del gerente de ventas es inválido.");
        }
        
        GerenteVentasDTO gerenteVentas = obtenerGerenteVentas(idGerenteVentasDTO);
        
        if(gerenteVentas == null){
            throw new IdGerenteVentasInvalidoException("El ID del gerente de ventas es inválido.");
        }

        return new NombresApellidoGerenteVentasDTO(gerenteVentas.getNombres(), gerenteVentas.getApellidoPaterno());
    }

}