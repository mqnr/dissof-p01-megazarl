package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesCorreoElectronicoYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesTelefonoNuevoClienteYaExisteException;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NuevoClienteDTONegocios;

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
     * @param idClienteDTO IdClienteDTONegocios que contiene el ID del Cliente a validar.
     * @return true, si el ID del cliente es válido, false en caso contrario.
     */
    public abstract boolean validarCliente(IdClienteDTONegocios idClienteDTO) throws ClientesPersistenciaException;

    /**
     * Métodoo que permite obtener un objeto Cliente a partir de si ID, si este existe.
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del Cliente a obtener.
     * @return Objeto ClienteDTO que representa al Cliente con el ID del parámetro.
     */
    public abstract ClienteDTONegocios obtenerCliente(IdClienteDTONegocios idClienteDTONegocios) throws ClientesPersistenciaException;

    /**
     * Método que permite actualizar los datos de la dirección de envío del cliente, que son Código Postal, Calle y Número.
     * @param informacioDireccionEnvioActualizadaClienteDTO Objeto InformacionDireccionEnvioActualizadaClienteDTONegocios que representa los datos de la dirección
 de envío del Cliente, que son Código Postal, Colonia, Calle y Número.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID de
     * Cliente recibido es inválido, dentro de este subsistema.
     * @throws ClientesAccesoArchivoCodigosPostalesFallidoException
     * @throws ClientesArchivoCodigosPostalesVacioException
     */
    public abstract void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTONegocios informacioDireccionEnvioActualizadaClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException,
            ClientesArchivoCodigosPostalesVacioException,
            ClientesPersistenciaException;
    
    /**
     * Método que permite obtener la información sobre la dirección de envío de un Cliente que este ingresa (Código Postal, Calle y Número)
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del Cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionDTONegocios que contiene el Código 
 Postal, Calle y Número de la dirección de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     * @throws ClientesIdDireccionInvalidoException
     */
    public abstract InformacionNoDerivadaCPDireccionDTONegocios obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException,
            ClientesPersistenciaException;
    
    /**
     * Método que permite obtener la información sobre la dirección derivada de un cliente.
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del Cliente a buscar sus datos de dirección.
     * @return Objeto InformacionNoDerivadaCPDireccionDTONegocios que contiene el Código 
 Postal, Calle y Número de la dirección de envío del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     * @throws ClientesIdDireccionInvalidoException
     */
    public abstract InformacionDerivadaCPDireccionDTONegocios obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException,
            ClientesPersistenciaException;

    /**
     * Método que permite obtener el o los nombres y el apellido paterno de un Cliente.
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del Cliente a obtener los datos.
     * @return Objeto NombresApellidoClienteDTONegocios que contiene el o lo nombres y el apellido paterno del Cliente.
     * @throws ClientesIdClienteInvalidoException Se lanza si se comprueba que el ID 
     * del Cliente es inválido, en este subsistema.
     */
    public abstract NombresApellidoClienteDTONegocios obtenerNombresApellidoCliente(IdClienteDTONegocios idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesPersistenciaException;
    
    
    public abstract void registrarCliente(NuevoClienteDTONegocios nuevoClienteDTONegocios)
            throws ClientesTelefonoNuevoClienteYaExisteException,
            ClientesCorreoElectronicoYaExisteException,
            ClientesPersistenciaException;
}
