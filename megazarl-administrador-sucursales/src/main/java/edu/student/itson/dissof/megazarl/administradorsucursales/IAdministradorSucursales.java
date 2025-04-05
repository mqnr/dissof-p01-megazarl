package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalException;
import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionMatrizDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;

/**
 * IAdministradorSucursales.java
 *
 * Interfaz del subsistema AdministradorSucursales, que administra las sucursales
 * de la empresa registradas en el sistema, permitiendo obtener información
 * sobre ellas y validar su existencia.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public interface IAdministradorSucursales {
    /**
     * Método que permite verificar si el ID de una sucursal es válido.
     *
     * @param codigoSucursal Objeto Integer que representa el ID de la sucursal a validar.
     * @return true si el ID de la sucursal es válido, false en caso contrario.
     */
    public abstract boolean validarSucursal(Integer codigoSucursal);

    /**
     * Método que permite obtener la lista de IDs de todas las sucursales registradas en el sistema.
     *
     * @return Objeto CodigosSucursalesDTO que contiene la lista de IDs de las sucursales.
     * @throws SucursalesIdSucursalException Se lanza si ocurre un error al obtener los códigos
     * de las sucursales.
     */
    public abstract CodigosSucursalesDTO obtenerCodigosSucursales() throws SucursalesIdSucursalException;

    /**
     * Método que permite obtener el código postal de una sucursal específica.
     *
     * @param idSucursal Objeto Integer que representa el ID de la sucursal.
     * @return Objeto String que representa el código postal de la sucursal.
     * @throws SucursalesIdSucursalException Se lanza si el ID de la sucursal es inválido.
     */
    public abstract String obtenerCodigoPostal(Integer idSucursal) throws SucursalesIdSucursalException;

    /**
     * Método que permite obtener la calle donde se ubica una sucursal específica.
     *
     * @param idSucursal Objeto Integer que representa el ID de la sucursal.
     * @return Objeto String que representa la calle donde se ubica la sucursal.
     * @throws SucursalesIdSucursalException Se lanza si el ID de la sucursal es inválido.
     */
    public abstract String obtenerCalle(Integer idSucursal) throws SucursalesIdSucursalException;

    /**
     * Método que permite obtener el número de domicilio de una sucursal específica.
     *
     * @param idSucursal Objeto Integer que representa el ID de la sucursal.
     * @return Objeto String que representa el número de domicilio de la sucursal.
     * @throws SucursalesIdSucursalException Se lanza si el ID de la sucursal es inválido.
     */
    public abstract String obtenerNumero(Integer idSucursal) throws SucursalesIdSucursalException;

    /**
     * Método que permite obtener la dirección completa de la sucursal matriz.
     *
     * @return Objeto DireccionMatrizDTO que contiene la información de dirección de la sucursal matriz.
     */

    public abstract DireccionMatrizDTO obtenerDireccionMatriz();

    /**
     * Método que permite obtener una sucursal específica a partir de su ID.
     *
     * @param idSucursal Objeto Integer que representa el ID de la sucursal a obtener.
     * @return Objeto Sucursal que representa la sucursal con el ID especificado.
     */
    public abstract Sucursal obtenerSucursal(Integer idSucursal);
}
