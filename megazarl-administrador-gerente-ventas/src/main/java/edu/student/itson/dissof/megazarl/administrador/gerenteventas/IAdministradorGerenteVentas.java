package edu.student.itson.dissof.megazarl.administrador.gerenteventas;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTO;
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
     * @param idGerenteVentasDTO IdGerenteVentasDTO que contiene el ID del Gerente de Ventas a validar.
     * @return true, si el ID del Gerente de Ventas es válido, false en caso contrario.
     */
    public abstract boolean validarGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO);
    
    /**
     * Métodoo que permite obtener un objeto Gerente de Ventas a partir de si ID, si este existe.
     * @param idGerenteVentasDTO Objeto IdGerenteVentasDTO que contiene el ID del Gerente de Ventas a obtener.
     * @return Objeto IdGerenteVentasDTO que representa al Gerente de Ventas con el ID del parámetro.
     */
    public abstract GerenteVentasDTO obtenerGerenteVentas(IdGerenteVentasDTO idGerenteVentasDTO);
    
    /**
     * Método que permite obtener el o los nombres y el apellido paterno de un Gerente de Ventas.
     * @param idGerenteVentasDTO Objeto IdGerenteVentasDTO que contiene el ID del Gerente de Ventas a obtener los datos.
     * @return Objeto NombresApellidoClienteDTO que contiene el o lo nombres y el apellido paterno del Cliente.
     * @throws IdGerenteVentasInvalidoException Se lanza si se comprueba que el ID 
     * del Gerente de Ventas es inválido, en este subsistema.
     */
    public abstract NombresApellidoGerenteVentasDTO obtenerNombresApellidoCliente(IdGerenteVentasDTO idGerenteVentasDTO)
            throws IdGerenteVentasInvalidoException;
}