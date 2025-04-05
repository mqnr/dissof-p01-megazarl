package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ClienteON;

/**
 * IAdministradorClientes.java
 * 
 * Interfaz del subsistema AdministradorClientes, que administra a los Clientes
 * registrados en el sistema, pudiendo actualizar su información.
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
public interface IAdministradorClientes {

    /**
     * Método que permite verificar si el ID recibido corresponde a un Cliente real o no.
     * @param idCliente Objeto Integer que representa el ID del Cliente a validar.
     * @return true, si el ID del cliente es válido, false en caso contrario.
     */
    public abstract boolean validarIdCliente(Integer idCliente);

    /**
     * Métodoo que permite obtener un objeto Cliente a partir de si ID, si este existe.
     * @param idCliente Objeto Integer que representa el ID del Cliente a obtener.
     * @return Objeto Cliente que representa al Cliente con el ID del parémetro.
     */
    public abstract ClienteON obtenerCliente(Integer idCliente);
    
    /**
     * Método que permite obtener la Colonia de envío de un Cliente, a partir del Código Postal de su colonia de envío.
     * @param idCliente Objeto Integer que representa el ID del Cliente a obtener los datos de dirección.
     * @return Objeto Stirng que representa la colonia de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * de Cliente es inválido, dentro de este subsistema.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si no
     * se pudo acceder al archivo con los Códigos Postales dentro del subsitema
     * direcciones.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se se comprueba
     * que el archivo de Códigos Postales está vacío, dentro del subsitema direcciones.
     */
    public abstract String obtenerColoniaCliente(Integer idCliente)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException;
    
    
    /**
     * Método que permite obtener la ciudad de envío de un Cliente, a partir del Código Postal de su dirección de envío.
     * @param idCliente Objeto Integer que representa el ID del Cliente a obtener su ciudad de envío.
     * @return Objeto Stirng que representa la ciudad de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * de Cliente es inválido, dentro de este subsistema.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si no
     * se pudo acceder al archivo con los Códigos Postales dentro del subsitema
     * direcciones.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se se comprueba
     * que el archivo de Códigos Postales está vacío, dentro del subsitema direcciones.
     */
    public abstract String obtenerCiudadCliente(Integer idCliente) 
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException;
    
    /**
     * Método que permite obtener la ciudad de envío de un Cliente, a partir del Código Postal de su dirección de envío.
     * @param idCliente Objeto Integer que representa el ID del Cliente a obtener su estado de envío.
     * @return Objeto Stirng que representa el estado de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID
     * de Cliente es inválido, dentro de este subsistema.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si no
     * se pudo acceder al recurso que accede los Códigos Postales, dentro del subsitema
     * direcciones.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se se comprueba
     * que el recurso con los Códigos Postales está vacío, dentro del subsitema direcciones.
     */
    public abstract String obtenerEstadoCliente(Integer idCliente) 
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException;
    
    /**
     * Método que permite actualizar los datos de la dirección de envío del cliente, que son Código Postal, Calle y Número.
     * @param informacionNoDerivadaCPDireccionEnvioDTO Objeto InformacionNoDerivadaCPDireccionEnvioDTO que representa los datos de la dirección
     * de envío del Cliente, que son Código Postal, Calle y Número.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID de
     * Cliente recibido es inválido, dentro de este subsistema.
     */
    public abstract void actualizarDireccionCliente(InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO) 
            throws ClientesIdClienteInvalidoException;

    /**
     * Método que permite obtener la información sobre la dirección de envío de un Cliente que este ingresa (Código Postal, Calle y Número)
     * @param idCliente Objeto Integer que representa el ID del Cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionEnvioDTO que contiene el Código 
     * Postal, Calle y Número de la dirección de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     */
    public abstract InformacionNoDerivadaCPDireccionEnvioDTO obtenerInformacionNoDerivadaCPDireccionEnvio(Integer idCliente)
            throws ClientesIdClienteInvalidoException;
    
    /**
     * Método que permite obtener el o los nombres y el apellido paterno de un Cliente.
     * @param idCliente Objeto Iteger que representa el ID del Cliente a obtener los datos.
     * @return Objeto NombresApellidoClienteDTO que contiene el o lo nombres y el apellido paterno del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     */
    public abstract NombresApellidoClienteDTO obtenerNombresApellidoCliente(Integer idCliente) 
            throws ClientesIdClienteInvalidoException;
}
