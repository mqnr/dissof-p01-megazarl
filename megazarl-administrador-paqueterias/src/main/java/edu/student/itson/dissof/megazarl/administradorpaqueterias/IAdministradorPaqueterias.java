package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTONegocios;

import java.util.List;

/**
 * IAdministradorPaqueterias.java
 *
 * Interfaz del subsistema AdministradorPaqueterias, que administra las paqueterías
 * disponibles en el sistema, permitiendo obtener información sobre ellas y calcular
 * costos de envío basados en características del producto y distancias.
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
public interface IAdministradorPaqueterias {

    /**
     * Método que permite obtener una paquetería a partir de su ID.
     *
     * @param idPaqueteriaDTO Objeto IdPaqueteriaDTO que contiene el ID de la paquetería a obtener.
     * @return Objeto Paqueteria que representa la paquetería con el ID especificado.
     */
    public abstract PaqueteriaDTONegocios obtenerPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTO);

    /**
     * Método que permite obtener la lista de todas las paqueterías disponibles
     * en el sistema con información básica para su selección.
     *
     * @return Objeto List de InformacionSeleccionPaqueteriaDTO que contiene la información
     * básica de las paqueterías disponibles.
     */
    public abstract List<InformacionSeleccionPaqueteriaDTONegocios> obtenerPaqueterias();
    
    /**
     * Método que permite calcular el costo de envío de un producto desde una sucursal
     * hacia la Matriz.
     *
     * @param informacionEnvioProductoSucursalMatrizDTO Objeto InformacionEnvioProductoSucursalMatrizDTO
     * que contiene los IDs de la sucursal de envío y de la Matriz, así como el ID de la
     * paquetería.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws PaqueteriasIdSucursalInvalidoException
     * @throws PaqueteriasIdDireccionInvalidoException
     */
    public abstract float obtenerCostoEnvioSucursalMatriz(InformacionEnvioProductoSucursalMatrizDTONegocios informacionEnvioProductoSucursalMatrizDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdSucursalInvalidoException,
            PaqueteriasIdDireccionInvalidoException;
    
    /**
     * Método que permite calcular el costo de envío desde la Matriz de la empresa
     * hasta la dirección de envío de un cliente.
     *
     * @param informacionEnvioProductoMatrizClienteDTO Objeto InformacionEnvioProductoMatrizClienteDTO
     * que contiene los IDs de paquetería, cliente, y Matriz de la empresa.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws PaqueteriasIdClienteInvalidoException
     * @throws PaqueteriasIdDireccionInvalidoException
     */
    public abstract float obtenerCostoEnvioMatrizCliente(InformacionEnvioProductoMatrizClienteDTONegocios informacionEnvioProductoMatrizClienteDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdClienteInvalidoException,
            PaqueteriasIdDireccionInvalidoException;
    
    /**
     * Método que permite calcular el costo de envío de un producto desde un proveedor
     * hacia la Matriz de la empresa.
     *
     * @param informacionEnvioProductoProveedorMatrizDTO Objeto InformacionEnvioProductoProveedorMatrizDTO
     * que contiene los IDs de paquetería, sucursal Matriz y proveedor.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws PaqueteriasIdProveedorInvalidoException
     * @throws PaqueteriasIdDireccionInvalidoException
     */
    public abstract float obtenerCostoEnvioProveedorMatriz(InformacionEnvioProductoProveedorMatrizDTONegocios informacionEnvioProductoProveedorMatrizDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException;
    
    /**
     * Método que permite obtener el tiempo máximo de envío a Matriz, de entre las 
     * paqueterías registradas.
     * @param idProveedorDTO Objeto IdProveedorPaqueteriaDTO que contiene el ID del
     * proveedor que ofrece los productos.
     * @return Objeto Float que representa el tiempo máximo de envío a Matriz en horas
     * de las paqueterías registradas, null si no existen paqueterías registradas.
     * @throws PaqueteriasIdProveedorInvalidoException Se lanza si se comprueba que
     * el ID de paquetería es inválido, dentro de este subsistema.
     * @throws PaqueteriasIdDireccionInvalidoException
     */
    public abstract Float obtenerTiempoEnvioMatrizEstimado(IdProveedorDTONegocios idProveedorDTO)
            throws PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException;
    
    /**
     * Método que permite verificar si el ID de una paquetería es válido.
     * @param idPaqueteriaDTO Objeto IdPaqueteriaDTO que contiene el ID de la paquetería a validar.
     * @return true si el ID de la paquetería es válido, false en caso contrario.
     */
    public abstract boolean validarPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTO);
    
}
