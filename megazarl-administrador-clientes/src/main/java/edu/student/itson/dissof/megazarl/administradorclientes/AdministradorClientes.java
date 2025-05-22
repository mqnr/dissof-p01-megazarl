package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesCorreoElectronicoYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesTelefonoNuevoClienteYaExisteException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import java.util.List;



class AdministradorClientes implements IAdministradorClientes {

    private final IAdministradorDirecciones administradorDirecciones;
    
    public AdministradorClientes(IAdministradorDirecciones administradorDirecciones){
        this.administradorDirecciones = administradorDirecciones;
    }

    @Override
    public ClienteDTO obtenerCliente(IdClienteDTO idClienteDTO) {
        return Cliente.recuperarPorId(idClienteDTO);
    }
    

    @Override
    public void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTO informacionDireccionEnvioActualizadaClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException,
            ClientesArchivoCodigosPostalesVacioException{
        
        Long idCliente = informacionDireccionEnvioActualizadaClienteDTO.getIdCliente();
        
        if(!validarCliente(new IdClienteDTO(idCliente))){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ActualizacionClienteDTO actualizacionClienteDTO = new ActualizacionClienteDTO(idCliente);
        
        String nuevoNumero = informacionDireccionEnvioActualizadaClienteDTO.getNumero();
        String nuevaCalle = informacionDireccionEnvioActualizadaClienteDTO.getCalle();
        String nuevaColonia = informacionDireccionEnvioActualizadaClienteDTO.getColonia();
        String nuevoCodigoPostal = informacionDireccionEnvioActualizadaClienteDTO.getCodigoPostal();
        
        try {
            DireccionDTO nuevaDireccionCliente = administradorDirecciones.registrarDireccion(
                    new DireccionDTO(
                    nuevoCodigoPostal, 
                    nuevaColonia, 
                    nuevaCalle, 
                    nuevoNumero));
            
            actualizacionClienteDTO.setDireccionEnvio(nuevaDireccionCliente);
            
            Cliente.actualizar(actualizacionClienteDTO);
            
        
        } catch (DireccionesAccesoArchivoCodigosPostalesFallidoException ex) {
            throw new ClientesAccesoArchivoCodigosPostalesFallidoException(ex.getMessage());
        }
        catch (DireccionesArchivoCodigosPostalesVacioException ex) {
            throw new ClientesArchivoCodigosPostalesVacioException(ex.getMessage());
        }
    }   
    
    @Override
    public InformacionNoDerivadaCPDireccionDTO obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO) 
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException{
        
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        Long idDireccionEnvioCliente = cliente.getIdDireccionEnvio();
        
        IdDireccionDTO idDireccionDTO = new IdDireccionDTO(idDireccionEnvioCliente);
        
        if(!administradorDirecciones.validarDireccion(new IdDireccionDTO(idDireccionEnvioCliente))){
            throw new ClientesIdDireccionInvalidoException("El ID de la dirección de envío del cliente es inválido.");
        }
        
        DireccionDTO direccionEnvioCliente = administradorDirecciones.obtenerDireccion(idDireccionDTO);
        
        

        InformacionNoDerivadaCPDireccionDTO informacionNoDerivadaCPDireccionDTO 
                = new InformacionNoDerivadaCPDireccionDTO(
                    direccionEnvioCliente.getNumero(),
                    direccionEnvioCliente.getCalle(),
                    direccionEnvioCliente.getColonia(),
                    direccionEnvioCliente.getCodigoPostal());   
        
       return informacionNoDerivadaCPDireccionDTO;
    }
    
    @Override
    public InformacionDerivadaCPDireccionDTO obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException {
        
        
        // Se valida que el ID del cliente.
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        Long idDireccionEnvioCliente = cliente.getIdDireccionEnvio();
        
        IdDireccionDTO idDireccionDTO = new IdDireccionDTO(idDireccionEnvioCliente);
        
        if(!administradorDirecciones.validarDireccion(idDireccionDTO)){
            throw new ClientesIdDireccionInvalidoException("El ID de la dirección del cliente es inválido.");
        }
        
        DireccionDTO direccionEnvioCliente = administradorDirecciones.obtenerDireccion(idDireccionDTO);
        
        if(direccionEnvioCliente == null){
            throw new ClientesIdDireccionInvalidoException("El ID de la dirección del cliente es inválido.");
        }
        
        InformacionDerivadaCPDireccionDTO informacionDerivadaCPDireccionDTO 
                = new InformacionDerivadaCPDireccionDTO(
                        direccionEnvioCliente.getEstado(), 
                        direccionEnvioCliente.getCiudad());

        return informacionDerivadaCPDireccionDTO;
    }

    @Override
    public NombresApellidoClienteDTO obtenerNombresApellidoCliente(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException {
        
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        return new NombresApellidoClienteDTO(
                cliente.getNombres(),
                cliente.getApellidoPaterno()
        );
    }
    
    
    @Override
    public boolean validarCliente(IdClienteDTO idClienteDTO) {
        
        if (idClienteDTO == null || idClienteDTO.getIdCliente() == null || !Cliente.existePorId(idClienteDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public void registrarCliente(ClienteDTO nuevoCliente)
            throws ClientesTelefonoNuevoClienteYaExisteException,
            ClientesCorreoElectronicoYaExisteException{
        
        List<ClienteDTO> clientesRegistrados = Cliente.recuperarTodos();
        
        for(ClienteDTO cliente: clientesRegistrados){
            
            if(cliente.getTelefono().equals(nuevoCliente.getTelefono())){
                throw new ClientesTelefonoNuevoClienteYaExisteException("Ya existe un cliente registrado con el mismo teléfono");
            }
            
            if(cliente.getCorreoElectronico().equals(nuevoCliente.getCorreoElectronico())){
                throw new ClientesCorreoElectronicoYaExisteException("Ya existe un cliente registrado con el mismo correo electrónico");
            }
            
            
        }
        
        Cliente.agregar(nuevoCliente);
        
    }


}
