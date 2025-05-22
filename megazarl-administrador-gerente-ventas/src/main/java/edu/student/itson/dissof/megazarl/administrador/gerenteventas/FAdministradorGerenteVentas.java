package edu.student.itson.dissof.megazarl.administrador.gerenteventas;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTO;
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
    public boolean validarGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO) {
        return administradorGerenteVentas.validarGerenteVentas(idGerenteVentasDTO);
    }

    @Override
    public GerenteVentasDTO obtenerGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO) {
        return administradorGerenteVentas.obtenerGerenteVentas(idGerenteVentasDTO);
    }

    @Override
    public NombresApellidoGerenteVentasDTO obtenerNombresApellidoGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO) throws IdGerenteVentasInvalidoException {
        return administradorGerenteVentas.obtenerNombresApellidoGerenteVentas(idGerenteVentasDTO);
    }

}