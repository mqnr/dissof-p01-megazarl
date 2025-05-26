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
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.List;



class AdministradorClientes implements IAdministradorClientes {

    private final IAdministradorDirecciones administradorDirecciones;
    
    public AdministradorClientes(IAdministradorDirecciones administradorDirecciones){
        this.administradorDirecciones = administradorDirecciones;
    }

    @Override
    public ClienteDTONegocios obtenerCliente(IdClienteDTONegocios idClienteDTONegocios) throws ClientesPersistenciaException {
        try {
            return Cliente.recuperarPorId(idClienteDTONegocios);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ParametroNuloNegocioException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
    }
    

    @Override
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
    
    @Override
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
    
    @Override
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

    @Override
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
    
    
    @Override
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

    @Override
    public void registrarCliente(ClienteDTONegocios nuevoCliente)
            throws ClientesTelefonoNuevoClienteYaExisteException,
            ClientesCorreoElectronicoYaExisteException,
            ClientesPersistenciaException{
        
        List<ClienteDTONegocios> clientesRegistrados = Cliente.recuperarTodos();
        
        for(ClienteDTONegocios cliente: clientesRegistrados){
            
            if(cliente.getTelefono().equals(nuevoCliente.getTelefono())){
                throw new ClientesTelefonoNuevoClienteYaExisteException("Ya existe un cliente registrado con el mismo teléfono");
            }
            
            if(cliente.getCorreoElectronico().equals(nuevoCliente.getCorreoElectronico())){
                throw new ClientesCorreoElectronicoYaExisteException("Ya existe un cliente registrado con el mismo correo electrónico");
            }
            
        }
        
        try {
            Cliente.agregar(nuevoCliente);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
            throw new ClientesPersistenciaException(ex.getMessage());
        }
        
    }


}
