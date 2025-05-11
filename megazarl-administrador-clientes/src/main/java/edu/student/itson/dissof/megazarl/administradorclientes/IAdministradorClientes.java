package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTO;

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
     * @param idClienteDTO IdClienteDTO que contiene el ID del Cliente a validar.
     * @return true, si el ID del cliente es válido, false en caso contrario.
     */
    public abstract boolean validarCliente(IdClienteDTO idClienteDTO);

    /**
     * Métodoo que permite obtener un objeto Cliente a partir de si ID, si este existe.
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del Cliente a obtener.
     * @return Objeto ClienteDTO que representa al Cliente con el ID del parámetro.
     */
    public abstract ClienteDTO obtenerCliente(IdClienteDTO idClienteDTO);

    /**
     * Método que permite actualizar los datos de la dirección de envío del cliente, que son Código Postal, Calle y Número.
     * @param informacioDireccionEnvioActualizadaClienteDTO Objeto InformacionDireccionEnvioActualizadaClienteDTO que representa los datos de la dirección
     * de envío del Cliente, que son Código Postal, Colonia, Calle y Número.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID de
     * Cliente recibido es inválido, dentro de este subsistema.
     * @throws ClientesAccesoArchivoCodigosPostalesFallidoException
     * @throws ClientesArchivoCodigosPostalesVacioException
     */
    public abstract void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTO informacioDireccionEnvioActualizadaClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException,
            ClientesArchivoCodigosPostalesVacioException;
    
    /**
     * Método que permite obtener la información sobre la dirección de envío de un Cliente que este ingresa (Código Postal, Calle y Número)
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del Cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionDTO que contiene el Código 
     * Postal, Calle y Número de la dirección de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     */
    public abstract InformacionNoDerivadaCPDireccionDTO obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException;
    
    /**
     * Método que permite obtener la información sobre la dirección derivada de un cliente.
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del Cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionDTO que contiene el Código 
     * Postal, Calle y Número de la dirección de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     * @throws ClientesIdDireccionInvalidoException
     */
    public abstract InformacionDerivadaCPDireccionDTO obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException;

    /**
     * Método que permite obtener el o los nombres y el apellido paterno de un Cliente.
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del Cliente a obtener los datos.
     * @return Objeto NombresApellidoClienteDTO que contiene el o lo nombres y el apellido paterno del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     */
    public abstract NombresApellidoClienteDTO obtenerNombresApellidoCliente(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException;
}
