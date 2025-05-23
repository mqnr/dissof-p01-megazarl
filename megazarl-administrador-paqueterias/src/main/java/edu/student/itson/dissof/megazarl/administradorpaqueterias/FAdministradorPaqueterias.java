package edu.student.itson.dissof.megazarl.administradorpaqueterias;


import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import java.util.List;

/**
 * FAdministradorPaqueterias.java
 *
 * Clase que implementa la interfaz IAdministradorPaqueterias, proporcionando
 * la funcionalidad para administrar las paqueterías disponibles en el sistema
 * y calcular costos de envío.
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
public class FAdministradorPaqueterias implements IAdministradorPaqueterias{
    
    private final AdministradorPaqueterias administradorPaqueterias;
    
    public FAdministradorPaqueterias(
            IAdministradorClientes administradorClientes,
            IAdministradorSucursales administradorSucursales,
            IAdministradorProveedores administradorProveedores,
            IAdministradorDirecciones administradorDirecciones,
            IAdministradorMapas administradorMapas,
            DireccionDTONegocios direccionDefectoPaqueteria){
        
        this.administradorPaqueterias = new AdministradorPaqueterias(
                administradorClientes, 
                administradorSucursales, 
                administradorProveedores,
                administradorDirecciones,
                administradorMapas, 
                direccionDefectoPaqueteria);
        
    }

    /**
     * Método que permite obtener una paquetería a partir de su ID.
     * @param idPaqueteriaDTONegocios Objeto IdPaqueteriaDTONegocios que contiene el ID de la paquetería a obtener.
     * @return Objeto Paqueteria que representa la paquetería con el ID especificado,
     * o null si no se encuentra una paquetería con ese ID.
     */
    @Override
    public PaqueteriaDTONegocios obtenerPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTONegocios) {
        return administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTONegocios);
    }

    /**
     * Método que permite obtener la lista de todas las paqueterías disponibles
     * en el sistema con información básica para su selección.
     *
     * @return Objeto List de InformacionSeleccionPaqueteriaDTO que contiene la información
     * básica de las paqueterías disponibles.
     */
    @Override
    public List<InformacionSeleccionPaqueteriaDTONegocios> obtenerPaqueterias() {
        return administradorPaqueterias.obtenerPaqueterias();
    }

    /**
     * Método que permite calcular el costo de envío de un producto desde una sucursal
     * hacia la Matriz.
     *
     * @param informacionEnvioProductoSucursalMatrizDTONegocios Objeto InformacionEnvioProductoSucursalMatrizDTONegocios
     * que contiene los IDs de la sucursal de envío y de la Matriz, así como el ID de la
     * paquetería.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws PaqueteriasIdSucursalInvalidoException
     */
    @Override
    public float obtenerCostoEnvioSucursalMatriz(InformacionEnvioProductoSucursalMatrizDTONegocios informacionEnvioProductoSucursalMatrizDTONegocios)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdSucursalInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        return administradorPaqueterias.obtenerCostoEnvioSucursalMatriz(informacionEnvioProductoSucursalMatrizDTONegocios);
        
    }
    
    /**
     * Método que permite calcular el costo de envío desde la Matriz de la empresa
     * hasta la dirección de envío de un cliente.
     *
     * @param informacionEnvioProductoMatrizClienteDTONegocios Objeto InformacionEnvioProductoMatrizClienteDTONegocios
     * que contiene los IDs de paquetería, cliente, y Matriz de la empresa.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     */
    @Override
    public float obtenerCostoEnvioMatrizCliente(InformacionEnvioProductoMatrizClienteDTONegocios informacionEnvioProductoMatrizClienteDTONegocios) 
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdClienteInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        return administradorPaqueterias.obtenerCostoEnvioMatrizCliente(informacionEnvioProductoMatrizClienteDTONegocios);
    }
    
    /**
     * Método que permite calcular el costo de envío de un producto desde un proveedor
     * hacia la Matriz de la empresa.
     * @param informacionEnvioProductoProveedorMatrizDTONegocios Objeto InformacionEnvioProductoProveedorMatrizDTONegocios
     * que contiene los IDs de paquetería, sucursal Matriz y proveedor.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     */
    @Override
    public float obtenerCostoEnvioProveedorMatriz(InformacionEnvioProductoProveedorMatrizDTONegocios informacionEnvioProductoProveedorMatrizDTONegocios) 
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        return administradorPaqueterias.obtenerCostoEnvioProveedorMatriz(informacionEnvioProductoProveedorMatrizDTONegocios);
        
    }

    /**
     * Implementación del método obtenerTiempoEnvioMatrizEstimado(), 
     * de la interfaz {@link IAdministradorPaqueterias}, que permite obtener el 
     * tiempo máximo de envío a Matriz, de entre las paqueterías registradas.
     * @param idProveedorDTONegocios
     * @return Objeto Float que representa el tiempo máximo de envío a Matriz en horas
     * de las paqueterías registradas, null si no existen paqueterías registradas.
     * @throws PaqueteriasIdDireccionInvalidoException
     */
    @Override
    public Float obtenerTiempoEnvioMatrizEstimado(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws PaqueteriasIdProveedorInvalidoException, 
            PaqueteriasIdDireccionInvalidoException {
        
        return administradorPaqueterias.obtenerTiempoEnvioMatrizEstimado(idProveedorDTONegocios);
    }
    
    /**
     * Método que permite verificar si el ID de una paquetería es válido.
     * @param idPaqueteriaDTONegocios Objeto IdPaqueteriaDTONegocios que contiene el ID de la paquetería a validar.
     * @return true si el ID de la paquetería es válido y existe en el sistema, false en caso contrario.
     */
    @Override
    public boolean validarPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTONegocios) {
        return administradorPaqueterias.validarPaqueteria(idPaqueteriaDTONegocios);
    }

}
