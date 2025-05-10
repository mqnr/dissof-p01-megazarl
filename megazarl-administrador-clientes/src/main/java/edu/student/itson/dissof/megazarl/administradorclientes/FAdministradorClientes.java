package edu.student.itson.dissof.megazarl.administradorclientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ClienteDTO;

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
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente a validar.
     * @return true, si el ID del cliente es válido, false en caso contrario.
     */
    @Override
    public boolean validarCliente(IdClienteDTO idClienteDTO) {
        return administradorClientes.validarCliente(idClienteDTO);
    }

    /**
     * Implementación del método obtenerCliente() de la interfaz {@link IAdministradorClientes},
     * permite obtener un objeto Cliente a partir de si ID, si este existe.
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente a obtener.
     * @return Objeto Cliente que representa al cliente con el ID del parémetro.
     */
    @Override
    public ClienteDTO obtenerCliente(IdClienteDTO idClienteDTO) {
        return administradorClientes.obtenerCliente(idClienteDTO);
    }

    /**
     * Implementación del método actualizarDireccionCliente(), de la interfaz {@link IAdministradorClientes},
     * permite actualizar los datos de la dirección de envío del cliente, que son Código Postal, Calle y Número.
     * @param informacionDireccionEnvioActualizadaClienteDTO Objeto InformacionDireccionEnvioActualizadaClienteDTO que representa 
     * los nuevos datos para actualizar la dirección de un cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID de
     * Cliente recibido es inválido, dentro de este subsistema.
     */
    @Override
    public void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTO informacionDireccionEnvioActualizadaClienteDTO) 
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException, 
            ClientesArchivoCodigosPostalesVacioException {
        
        administradorClientes.actualizarDireccionCliente(informacionDireccionEnvioActualizadaClienteDTO);
    }

    /**
     * Implementación del método obtenerInformacionNoDerivadaCPDireccionEnvio(), de la interfaz {@link IAdministradorClientes},
     * que permite obtener la información sobre la dirección de envío de un cliente que este ingresa (Código Postal, Calle y Número)
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionDTO que contiene el Código
     * Postal, Calle y Número de la dirección de envío del cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del Cliente es inválido, en este subsistema.
     */
    @Override
    public InformacionNoDerivadaCPDireccionDTO obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO) 
            throws ClientesIdClienteInvalidoException {
        
        return administradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(idClienteDTO);
        
    }

    /**
     * Implementación del método obtenerNombreApellidoCliente(), de la interfaz {@link IAdministradorClientes},
     * que permite obtener el o los nombres y el apellido paterno de un cliente.
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente a obtener los datos.
     * @return Objeto NombresApellidoClienteDTO que contiene el o lo nombres y el apellido paterno del cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * es inválido, en este subsistema.
     */
    @Override
    public NombresApellidoClienteDTO obtenerNombresApellidoCliente(IdClienteDTO idClienteDTO) throws ClientesIdClienteInvalidoException {
        return administradorClientes.obtenerNombresApellidoCliente(idClienteDTO);
    }

    @Override
    public InformacionDerivadaCPDireccionDTO obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO) 
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException {
        
        return administradorClientes.obtenerInformacionDerivadaCPDireccionEnvio(idClienteDTO);
        
    }
}
