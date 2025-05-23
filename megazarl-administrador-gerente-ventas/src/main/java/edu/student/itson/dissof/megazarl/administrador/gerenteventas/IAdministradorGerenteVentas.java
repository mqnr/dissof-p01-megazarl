package edu.student.itson.dissof.megazarl.administrador.gerenteventas;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTONegocios;
/**
 * IAdministradorGerenteVentas.java
 * 
 * Interfaz del subsistema AdministradorGerenteVentas, que administra a los Gerentes de Ventas
 * registrados en el sistema
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public interface IAdministradorGerenteVentas {
    
    /**
     * Método que permite verificar si el ID recibido corresponde a un Gerente de Ventas real o no.
     * @param idGerenteVentasDTO IdGerenteVentasDTONegocios que contiene el ID del Gerente de Ventas a validar.
     * @return true, si el ID del Gerente de Ventas es válido, false en caso contrario.
     */
    public abstract boolean validarGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO);
    
    /**
     * Métodoo que permite obtener un objeto Gerente de Ventas a partir de si ID, si este existe.
     * @param idGerenteVentasDTO Objeto IdGerenteVentasDTONegocios que contiene el ID del Gerente de Ventas a obtener.
     * @return Objeto IdGerenteVentasDTONegocios que representa al Gerente de Ventas con el ID del parámetro.
     */
    public abstract GerenteVentasDTONegocios obtenerGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO);
    
    /**
     * Método que permite obtener el o los nombres y el apellido paterno de un Gerente de Ventas.
     * @param idGerenteVentasDTO Objeto IdGerenteVentasDTONegocios que contiene el ID del Gerente de Ventas a obtener los datos.
     * @return Objeto NombresApellidoClienteDTO que contiene el o lo nombres y el apellido paterno del Cliente.
     * @throws IdGerenteVentasInvalidoException Se lanza si se comprueba que el ID 
     * del Gerente de Ventas es inválido, en este subsistema.
     */
    public abstract NombresApellidoGerenteVentasDTONegocios obtenerNombresApellidoGerenteVentas(IdGerenteVentasDTONegocios idGerenteVentasDTO)
            throws IdGerenteVentasInvalidoException;
}