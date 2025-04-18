package edu.student.itson.dissof.megazarl.administradorclientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.modelos.ClienteDTO;

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
public class FAdministradorClientes {
    /**
     * Implementación del método validarIdCliente, de la interfaz {@link IAdministradorClientes},
     * que permite verificar si el ID recibido corresponde a un Cliente real o no.
     * @param id Objeto Integer que representa el ID del Cliente a validar.
     * @return true, si el ID del cliente es válido, false en caso contrario.
     */
    public static boolean validarIdCliente(Integer id) {
        return AdministradorClientes.INSTANCIA.validarIdCliente(id);
    }

    /**
     * Implementación del método obtenerCliente() de la interfaz {@link IAdministradorClientes},
     * permite obtener un objeto Cliente a partir de si ID, si este existe.
     * @param id Objeto Integer que representa el ID del Cliente a obtener.
     * @return Objeto Cliente que representa al Cliente con el ID del parémetro.
     */
    public static ClienteDTO obtenerCliente(Integer id) {
        return AdministradorClientes.INSTANCIA.obtenerCliente(id);
    }

    /**
     * Implementación del método obtenerColoniaCliente() de la interfaz {@link IAdministradorClientes},
     * permite obtener la Colonia de envío de un Cliente, a partir del Código Postal de su colonia de envío.
     * @param id Objeto Integer que representa el ID del Cliente a obtener los datos de dirección.
     * @return Objeto Stirng que representa la colonia de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * de Cliente es inválido, dentro de este subsistema.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si no
     * se pudo acceder al archivo con los Códigos Postales dentro del subsitema
     * direcciones.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se se comprueba
     * que el archivo de Códigos Postales está vacío, dentro del subsitema direcciones.
     */
    public static String obtenerColoniaCliente(Integer id) throws ClientesIdClienteInvalidoException, DireccionesAccesoArchivoCodigosPostalesFallidoException, DireccionesArchivoCodigosPostalesVacioException {
        return AdministradorClientes.INSTANCIA.obtenerColoniaCliente(id);
    }

    /**
     * Implementación del método obtenerColoniaCliente() de la interfaz {@link IAdministradorClientes},
     * permite obtener la ciudad de envío de un Cliente, a partir del Código Postal de su dirección de envío.
     * @param id Objeto Integer que representa el ID del Cliente a obtener su ciudad de envío.
     * @return Objeto Stirng que representa la ciudad de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * de Cliente es inválido, dentro de este subsistema.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si no
     * se pudo acceder al archivo con los Códigos Postales dentro del subsitema
     * direcciones.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se se comprueba
     * que el archivo de Códigos Postales está vacío, dentro del subsitema direcciones.
     */
    public static String obtenerCiudadCliente(Integer id) throws ClientesIdClienteInvalidoException, DireccionesAccesoArchivoCodigosPostalesFallidoException, DireccionesArchivoCodigosPostalesVacioException {
        return AdministradorClientes.INSTANCIA.obtenerCiudadCliente(id);
    }

    /**
     * Implementación del método obtenerEstadoCliente() de la interfaz {@link IAdministradorClientes},
     * permite obtener la ciudad de envío de un Cliente, a partir del Código Postal de su dirección de envío.
     * @param id Objeto Integer que representa el ID del Cliente a obtener su estado de envío.
     * @return Objeto Stirng que representa el estado de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * de Cliente es inválido, dentro de este subsistema.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si no
     * se pudo acceder al archivo con los Códigos Postales dentro del subsitema
     * direcciones.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se se comprueba
     * que el archivo de Códigos Postales está vacío, dentro del subsitema direcciones.
     */
    public static String obtenerEstadoCliente(Integer id) throws ClientesIdClienteInvalidoException, DireccionesAccesoArchivoCodigosPostalesFallidoException, DireccionesArchivoCodigosPostalesVacioException {
        return AdministradorClientes.INSTANCIA.obtenerEstadoCliente(id);
    }

    /**
     * Implementación del método actualizarDireccionCliente(), de la interfaz {@link IAdministradorClientes},
     * permite actualizar los datos de la dirección de envío del cliente, que son Código Postal, Calle y Número.
     * @param informacionNoDerivadaCPDireccionEnvioDTO Objeto InformacionNoDerivadaCPDireccionEnvioDTO que representa los datos de la dirección
     de envío del Cliente, que son Código Postal, Calle y Número.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID de
     * Cliente recibido es inválido, dentro de este subsistema.
     */
    public static void actualizarDireccionCliente(InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO) throws ClientesIdClienteInvalidoException {
        AdministradorClientes.INSTANCIA.actualizarDireccionCliente(informacionNoDerivadaCPDireccionEnvioDTO);
    }

    /**
     * Implementación del método obtenerInformacionNoDerivadaCPDireccionEnvio(), de la interfaz {@link IAdministradorClientes},
     * que permite obtener la información sobre la dirección de envío de un Cliente que este ingresa (Código Postal, Calle y Número)
     * @param id Objeto Integer que representa el ID del Cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionEnvioDTO que contiene el Código
     * Postal, Calle y Número de la dirección de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del Cliente es inválido, en este subsistema.
     */
    public static InformacionNoDerivadaCPDireccionEnvioDTO obtenerInformacionNoDerivadaCPDireccionEnvio(Integer id) throws ClientesIdClienteInvalidoException {
        return AdministradorClientes.INSTANCIA.obtenerInformacionNoDerivadaCPDireccionEnvio(id);
    }

    /**
     * Implementación del método obtenerNombreApellidoCliente(), de la interfaz {@link IAdministradorClientes},
     * que permite obtener el o los nombres y el apellido paterno de un Cliente.
     * @param id Objeto Iteger que representa el ID del Cliente a obtener los datos.
     * @return Objeto NombresApellidoClienteDTO que contiene el o lo nombres y el apellido paterno del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del Cliente es inválido, en este subsistema.
     */
    public static NombresApellidoClienteDTO obtenerNombresApellidoCliente(Integer id) throws ClientesIdClienteInvalidoException {
        return AdministradorClientes.INSTANCIA.obtenerNombresApellidoCliente(id);
    }
}
