package edu.student.itson.dissof.megazarl.administradorclientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesCorreoElectronicoYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesTelefonoNuevoClienteYaExisteException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NuevoClienteDTONegocios;

/**
 * FAdministradorClientes.java
 * 
 * Clase Fachada que representa la implementación de la interfaz {@link IAdministradorClientes}
 * del subsistema AdministradorClientes.
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
public class FAdministradorClientes implements IAdministradorClientes{
    
    private final AdministradorClientes administradorClientes;
    
    public FAdministradorClientes(IAdministradorDirecciones administradorDirecciones){
        this.administradorClientes = new AdministradorClientes(administradorDirecciones);
    }
    
    /**
     * Implementación del método validarCliente, de la interfaz {@link IAdministradorClientes},
     * que permite verificar si el ID recibido corresponde a un cliente real o no.
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente a validar.
     * @return true, si el ID del cliente es válido, false en caso contrario.
     */
    @Override
    public boolean validarCliente(IdClienteDTONegocios idClienteDTONegocios) throws ClientesPersistenciaException {
        
        return administradorClientes.validarCliente(idClienteDTONegocios);
    }

    /**
     * Implementación del método obtenerCliente() de la interfaz {@link IAdministradorClientes},
     * permite obtener un objeto Cliente a partir de si ID, si este existe.
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente a obtener.
     * @return Objeto Cliente que representa al cliente con el ID del parémetro.
     */
    @Override
    public ClienteDTONegocios obtenerCliente(IdClienteDTONegocios idClienteDTONegocios) throws ClientesPersistenciaException {
        return administradorClientes.obtenerCliente(idClienteDTONegocios);
    }

    /**
     * Implementación del método actualizarDireccionCliente(), de la interfaz {@link IAdministradorClientes},
     * permite actualizar los datos de la dirección de envío del cliente, que son Código Postal, Calle y Número.
     * @param informacionDireccionEnvioActualizadaClienteDTO Objeto InformacionDireccionEnvioActualizadaClienteDTONegocios que representa 
 los nuevos datos para actualizar la dirección de un cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID de
     * Cliente recibido es inválido, dentro de este subsistema.
     */
    @Override
    public void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTONegocios informacionDireccionEnvioActualizadaClienteDTO) 
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException, 
            ClientesArchivoCodigosPostalesVacioException,
            ClientesPersistenciaException {
        
        administradorClientes.actualizarDireccionCliente(informacionDireccionEnvioActualizadaClienteDTO);
    }

    /**
     * Implementación del método obtenerInformacionNoDerivadaCPDireccionEnvio(), de la interfaz {@link IAdministradorClientes},
     * que permite obtener la información sobre la dirección de envío de un cliente que este ingresa (Código Postal, Calle y Número)
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionDTONegocios que contiene el Código
 Postal, Calle y Número de la dirección de envío del cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del Cliente es inválido, en este subsistema.
     */
    @Override
    public InformacionNoDerivadaCPDireccionDTONegocios obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTONegocios) 
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException,
            ClientesPersistenciaException{
        
        return administradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(idClienteDTONegocios);
        
    }

    /**
     * Implementación del método obtenerNombreApellidoCliente(), de la interfaz {@link IAdministradorClientes},
     * que permite obtener el o los nombres y el apellido paterno de un cliente.
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente a obtener los datos.
     * @return Objeto NombresApellidoClienteDTONegocios que contiene el o lo nombres y el apellido paterno del cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * es inválido, en este subsistema.
     */
    @Override
    public NombresApellidoClienteDTONegocios obtenerNombresApellidoCliente(IdClienteDTONegocios idClienteDTONegocios)
            throws ClientesIdClienteInvalidoException, 
            ClientesPersistenciaException {
        
        return administradorClientes.obtenerNombresApellidoCliente(idClienteDTONegocios);
    }

    @Override
    public InformacionDerivadaCPDireccionDTONegocios obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTONegocios) 
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException,
            ClientesPersistenciaException {
        
        return administradorClientes.obtenerInformacionDerivadaCPDireccionEnvio(idClienteDTONegocios);
        
    }

    @Override
    public void registrarCliente(NuevoClienteDTONegocios nuevoClienteDTONegocios) 
            throws ClientesTelefonoNuevoClienteYaExisteException,
            ClientesCorreoElectronicoYaExisteException,
            ClientesPersistenciaException{
        
        administradorClientes.registrarCliente(nuevoClienteDTONegocios);
    }
}
