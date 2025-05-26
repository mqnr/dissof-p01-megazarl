
package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesCorreoElectronicoYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesTelefonoNuevoClienteYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.utils.HashesUtils;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NuevoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.List;

/**
 * AdministradorClientes.java
 * 
 * Clase que proporciona métodos para administrar clientes, incluyendo operaciones
 * de recuperación, actualización, validación y registro. Maneja excepciones específicas
 * relacionadas con la persistencia y validación de datos de clientes.
 * 
 * @author Manuel Romo López
 */
class AdministradorClientes {

    private final String MENSAJE_TELEFONO_DUPLICADO = "Ya existe un cliente registrado con el mismo teléfono";
    private final String MENSAJE_CORREO_ELECTRONICO_DUPLICADO = "Ya existe un cliente registrado con el mismo correo electrónico";
    
    private final IAdministradorDirecciones administradorDirecciones;
    
    /**
     * Constructor que inicializa el administrador de clientes con un administrador de direcciones.
     * 
     * @param administradorDirecciones Instancia de IAdministradorDirecciones para gestionar direcciones.
     */
    public AdministradorClientes(IAdministradorDirecciones administradorDirecciones){
        this.administradorDirecciones = administradorDirecciones;
    }
    
    /**
     * Recupera un cliente por su identificador.
     * 
     * @param idClienteDTONegocios DTO con el identificador del cliente.
     * @return DTO con la información del cliente encontrado.
     * @throws ClientesPersistenciaException Si ocurre un error al acceder a la persistencia.
     */
    public ClienteDTONegocios obtenerCliente(IdClienteDTONegocios idClienteDTONegocios) throws ClientesPersistenciaException {
        try {
            return Cliente.recuperarPorId(idClienteDTONegocios);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ParametroNuloNegocioException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
    }
    
    /**
     * Actualiza la dirección de envío de un cliente.
     * 
     * @param informacionDireccionEnvioActualizadaClienteDTO DTO con la nueva información de dirección.
     * @throws ClientesIdClienteInvalidoException Si el ID del cliente es inválido.
     * @throws ClientesAccesoArchivoCodigosPostalesFallidoException Si falla el acceso al archivo de códigos postales.
     * @throws ClientesArchivoCodigosPostalesVacioException Si el archivo de códigos postales está vacío.
     * @throws ClientesPersistenciaException Si ocurre un error al actualizar la información.
     */
    public void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTONegocios informacionDireccionEnvioActualizadaClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException,
            ClientesArchivoCodigosPostalesVacioException,
            ClientesPersistenciaException {
        
        IdEntidadGenericoNegocios idCliente = informacionDireccionEnvioActualizadaClienteDTO.getIdCliente();
        
        if(!validarCliente(new IdClienteDTONegocios(idCliente))){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ActualizacionClienteDTONegocios actualizacionClienteDTO = new ActualizacionClienteDTONegocios(idCliente);
        
        String nuevoNumero = informacionDireccionEnvioActualizadaClienteDTO.getNumero();
        String nuevaCalle = informacionDireccionEnvioActualizadaClienteDTO.getCalle();
        String nuevaColonia = informacionDireccionEnvioActualizadaClienteDTO.getColonia();
        String nuevoCodigoPostal = informacionDireccionEnvioActualizadaClienteDTO.getCodigoPostal();
        
        DireccionDTONegocios nuevoDireccionEnvioCliente = new DireccionDTONegocios(
                                            nuevoCodigoPostal, 
                                            nuevaColonia, 
                                            nuevaCalle, 
                                            nuevoNumero);

        actualizacionClienteDTO.setDireccionEnvio(nuevoDireccionEnvioCliente);

        try {
            Cliente.actualizar(actualizacionClienteDTO);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
            
            throw new ClientesPersistenciaException(ex.getMessage());
        }       

    }   
    
    /**
     * Obtiene la información no derivada de la dirección de envío de un cliente (número, calle, colonia, código postal).
     * 
     * @param IdClienteDTONegocios DTO con el identificador del cliente.
     * @return DTO con la información no derivada de la dirección.
     * @throws ClientesIdClienteInvalidoException Si el ID del cliente es inválido.
     * @throws ClientesIdDireccionInvalidoException Si el ID de la dirección es inválido.
     * @throws ClientesPersistenciaException Si ocurre un error al recuperar la información.
     */
    public InformacionNoDerivadaCPDireccionDTONegocios obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTONegocios IdClienteDTONegocios) 
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException,
            ClientesPersistenciaException{
        
        if(!validarCliente(IdClienteDTONegocios)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente;
        try {
            cliente = obtenerCliente(IdClienteDTONegocios);
        } catch (ClientesPersistenciaException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        DireccionDTONegocios direccionEnvioCliente = cliente.getDireccionEnvio();
        
        

        InformacionNoDerivadaCPDireccionDTONegocios informacionNoDerivadaCPDireccionDTO 
                = new InformacionNoDerivadaCPDireccionDTONegocios(
                    direccionEnvioCliente.getNumero(),
                    direccionEnvioCliente.getCalle(),
                    direccionEnvioCliente.getColonia(),
                    direccionEnvioCliente.getCodigoPostal());   
        
       return informacionNoDerivadaCPDireccionDTO;
    }
    
    /**
     * Obtiene la información derivada de la dirección de envío de un cliente (estado, ciudad).
     * 
     * @param idClienteDTONegocios DTO con el identificador del cliente.
     * @return DTO con la información derivada de la dirección.
     * @throws ClientesIdClienteInvalidoException Si el ID del cliente es inválido.
     * @throws ClientesIdDireccionInvalidoException Si el ID de la dirección es inválido.
     * @throws ClientesPersistenciaException Si ocurre un error al recuperar la información.
     */
    public InformacionDerivadaCPDireccionDTONegocios obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTONegocios)
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException,
            ClientesPersistenciaException{
        
        
        // Se valida que el ID del cliente.
        if(!validarCliente(idClienteDTONegocios)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente;
        try {
            cliente = obtenerCliente(idClienteDTONegocios);
        } catch (ClientesPersistenciaException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        DireccionDTONegocios direccionEnvioCliente = cliente.getDireccionEnvio();
        
        if(direccionEnvioCliente == null){
            throw new ClientesIdDireccionInvalidoException("El ID de la dirección del cliente es inválido.");
        }
        
        InformacionDerivadaCPDireccionDTONegocios informacionDerivadaCPDireccionDTO 
                = new InformacionDerivadaCPDireccionDTONegocios(
                        direccionEnvioCliente.getEstado(), 
                        direccionEnvioCliente.getCiudad());

        return informacionDerivadaCPDireccionDTO;
    }

    /**
     * Obtiene los nombres y apellido de un cliente.
     * 
     * @param idClienteDTONegocios DTO con el identificador del cliente.
     * @return DTO con los nombres y apellido del cliente.
     * @throws ClientesIdClienteInvalidoException Si el ID del cliente es inválido.
     * @throws ClientesPersistenciaException Si ocurre un error al recuperar la información.
     */
    public NombresApellidoClienteDTONegocios obtenerNombresApellidoCliente(IdClienteDTONegocios idClienteDTONegocios)
            throws ClientesIdClienteInvalidoException,
            ClientesPersistenciaException{
        
        if(!validarCliente(idClienteDTONegocios)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente = obtenerCliente(idClienteDTONegocios);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        return new NombresApellidoClienteDTONegocios(
                cliente.getNombres(),
                cliente.getApellidoPaterno()
        );
    }
    
    /**
     * Valida si un cliente existe en la persistencia.
     * 
     * @param idClienteDTONegocios DTO con el identificador del cliente.
     * @return true si el cliente es válido, false en caso contrario.
     * @throws ClientesPersistenciaException Si ocurre un error al validar el cliente.
     */
    public boolean validarCliente(IdClienteDTONegocios idClienteDTONegocios) throws ClientesPersistenciaException{
        
        try {
            if (idClienteDTONegocios == null || idClienteDTONegocios.getIdCliente() == null || !Cliente.existePorId(idClienteDTONegocios)) {
                return false;
            }
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
        
        return true;
    }

    /**
     * Registra un nuevo cliente verificando duplicados de teléfono y correo electrónico.
     * 
     * @param nuevoClienteDTONegocios DTO con la información del nuevo cliente.
     * @throws ClientesTelefonoNuevoClienteYaExisteException Si el teléfono ya está registrado.
     * @throws ClientesCorreoElectronicoYaExisteException Si el correo electrónico ya está registrado.
     * @throws ClientesPersistenciaException Si ocurre un error al registrar el cliente.
     */
    public void registrarCliente(NuevoClienteDTONegocios nuevoClienteDTONegocios)
            throws ClientesTelefonoNuevoClienteYaExisteException,
            ClientesCorreoElectronicoYaExisteException,
            ClientesPersistenciaException{
        
        List<ClienteDTONegocios> clientesRegistrados = Cliente.recuperarTodos();
        
        String hashContrasenia = null;
        
        for(ClienteDTONegocios cliente: clientesRegistrados){
            
            if(cliente.getTelefono().equals(nuevoClienteDTONegocios.getTelefono())){
                throw new ClientesTelefonoNuevoClienteYaExisteException(MENSAJE_TELEFONO_DUPLICADO);
            }
            
            if(cliente.getCorreoElectronico().equals(nuevoClienteDTONegocios.getCorreoElectronico())){
                throw new ClientesCorreoElectronicoYaExisteException(MENSAJE_CORREO_ELECTRONICO_DUPLICADO);
            }
            
            
        }
        
        hashContrasenia = HashesUtils.hashearContrasenia(nuevoClienteDTONegocios.getContrasenia());
        
        ClienteDTONegocios clienteDTONegocios = new ClienteDTONegocios(
                    nuevoClienteDTONegocios.getNombres(), 
                    nuevoClienteDTONegocios.getApellidoPaterno(),
                    nuevoClienteDTONegocios.getApellidoMaterno(),
                    nuevoClienteDTONegocios.getTelefono(),
                    nuevoClienteDTONegocios.getCorreoElectronico(),
                    hashContrasenia);
       
        try {
            
            Cliente.agregar(clienteDTONegocios);
            
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
        
    }
}
