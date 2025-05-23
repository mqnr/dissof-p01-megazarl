package edu.student.itson.dissof.megazarl.administrador.gerenteventas;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTONegocios;
/**
 * FAdministradorGerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class FAdministradorGerenteVentas implements IAdministradorGerenteVentas {
    
    private final AdministradorGerenteVentas administradorGerenteVentas;
    
    public FAdministradorGerenteVentas(){
        this.administradorGerenteVentas = new AdministradorGerenteVentas();
    }

    @Override
    public boolean validarGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO) {
        return administradorGerenteVentas.validarGerenteVentas(idGerenteVentasDTO);
    }

    @Override
    public GerenteVentasDTONegocios obtenerGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO) {
        return administradorGerenteVentas.obtenerGerenteVentas(idGerenteVentasDTO);
    }

    @Override
    public NombresApellidoGerenteVentasDTONegocios obtenerNombresApellidoGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO) throws IdGerenteVentasInvalidoException {
        return administradorGerenteVentas.obtenerNombresApellidoGerenteVentas(idGerenteVentasDTO);
    }

}