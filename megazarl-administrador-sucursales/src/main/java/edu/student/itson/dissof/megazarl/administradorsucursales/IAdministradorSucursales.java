package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalInvalidoException;

import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;


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
     * Método que permite obtener la lista de IDs de todas las sucursales registradas en el sistema.
     *
     * @return Objeto CodigosSucursalesDTO que contiene la lista de IDs de las sucursales.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si ocurre un error al obtener los códigos
     * de las sucursales.
     */
    public abstract CodigosSucursalesDTONegocios obtenerCodigosSucursales() throws SucursalesIdSucursalInvalidoException;

    /**
     * Método que permite obtener el código postal de una sucursal específica.
     *
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que representa el ID de la sucursal.
     * @return Objeto String que representa el código postal de la sucursal.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si el ID de la sucursal es inválido.
     * @throws SucursalesIdDireccionInvalidoException
     */
    public abstract String obtenerCodigoPostal(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException;

    /**
     * Método que permite obtener la calle donde se ubica una sucursal específica.
     *
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que contiene el ID de la sucursal.
     * @return Objeto String que representa la calle donde se ubica la sucursal.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si el ID de la sucursal es inválido.
     * @throws SucursalesIdDireccionInvalidoException
     */
    public abstract String obtenerCalle(IdSucursalDTONegocios idSucursalDTONegocios)
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException;

    /**
     * Método que permite obtener el número de domicilio de una sucursal específica.
     *
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que contiene el ID de la sucursal.
     * @return Objeto String que representa el número de domicilio de la sucursal.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si el ID de la sucursal es inválido.
     * @throws SucursalesIdDireccionInvalidoException
     */
    public abstract String obtenerNumero(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException;

    /**
     * Método que permite obtener un objetoDTO con la información de la sucursal matriz
     * @return Objeto SucursalDTO que contiene la información de la sucursal matriz
     */
    public abstract SucursalDTONegocios obtenerSucursalMatriz();

    /**
     * Método que permite obtener una sucursal específica a partir de su ID.
     *
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal a obtener.
     * @return Objeto Sucursal que representa la sucursal con el ID especificado.
     */
    public abstract SucursalDTONegocios obtenerSucursal(IdSucursalDTONegocios idSucursalDTO);
    
    /**
     * Método que permite verificar si el ID de una sucursal es válido.
     *
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que contiene el ID de la sucursal a validar.
     * @return true si el ID de la sucursal es válido, false en caso contrario.
     */

    public abstract boolean validarSucursal(IdSucursalDTONegocios idSucursalDTONegocios);
    
    /**
     * Método que permite obtener la lista de todas las sucursales registradas en el sistema.
     *
     * @return Objeto List de InformacionSucursalInicioDTO que contiene la información
     * resumida de las sucursales registradas.
     */
    public abstract List<InformacionSucursalInicioDTO> obtenerSucursales();
}

