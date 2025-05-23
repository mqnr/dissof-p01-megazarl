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
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import java.util.List;



class AdministradorClientes implements IAdministradorClientes {

    private final IAdministradorDirecciones administradorDirecciones;
    
    public AdministradorClientes(IAdministradorDirecciones administradorDirecciones){
        this.administradorDirecciones = administradorDirecciones;
    }

    @Override
    public ClienteDTONegocios obtenerCliente(IdClienteDTONegocios idClienteDTO) {
        return Cliente.recuperarPorId(idClienteDTO);
    }
    

    @Override
    public void actualizarDireccionCliente(InformacionDireccionEnvioActualizadaClienteDTONegocios informacionDireccionEnvioActualizadaClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesAccesoArchivoCodigosPostalesFallidoException,
            ClientesArchivoCodigosPostalesVacioException{
        
        IdEntidadGenericoNegocios idCliente = informacionDireccionEnvioActualizadaClienteDTO.getIdCliente();
        
        if(!validarCliente(new IdClienteDTONegocios(idCliente))){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ActualizacionClienteDTONegocios actualizacionClienteDTO = new ActualizacionClienteDTONegocios(idCliente);
        
        String nuevoNumero = informacionDireccionEnvioActualizadaClienteDTO.getNumero();
        String nuevaCalle = informacionDireccionEnvioActualizadaClienteDTO.getCalle();
        String nuevaColonia = informacionDireccionEnvioActualizadaClienteDTO.getColonia();
        String nuevoCodigoPostal = informacionDireccionEnvioActualizadaClienteDTO.getCodigoPostal();
        
        try {
            DireccionDTONegocios nuevaDireccionCliente = administradorDirecciones.registrarDireccion(new DireccionDTONegocios(
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
    public InformacionNoDerivadaCPDireccionDTONegocios obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTO) 
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException{
        
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        IdEntidadGenericoNegocios idDireccionEnvioCliente = cliente.getIdDireccionEnvio();
        
        IdDireccionDTONegocios idDireccionDTO = new IdDireccionDTONegocios(idDireccionEnvioCliente);
        
        if(!administradorDirecciones.validarDireccion(new IdDireccionDTONegocios(idDireccionEnvioCliente))){
            throw new ClientesIdDireccionInvalidoException("El ID de la dirección de envío del cliente es inválido.");
        }
        
        DireccionDTONegocios direccionEnvioCliente = administradorDirecciones.obtenerDireccion(idDireccionDTO);
        
        

        InformacionNoDerivadaCPDireccionDTONegocios informacionNoDerivadaCPDireccionDTO 
                = new InformacionNoDerivadaCPDireccionDTONegocios(
                    direccionEnvioCliente.getNumero(),
                    direccionEnvioCliente.getCalle(),
                    direccionEnvioCliente.getColonia(),
                    direccionEnvioCliente.getCodigoPostal());   
        
       return informacionNoDerivadaCPDireccionDTO;
    }
    
    @Override
    public InformacionDerivadaCPDireccionDTONegocios obtenerInformacionDerivadaCPDireccionEnvio(IdClienteDTONegocios idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            ClientesIdDireccionInvalidoException {
        
        
        // Se valida que el ID del cliente.
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        IdEntidadGenericoNegocios idDireccionEnvioCliente = cliente.getIdDireccionEnvio();
        
        IdDireccionDTONegocios idDireccionDTO = new IdDireccionDTONegocios(idDireccionEnvioCliente);
        
        if(!administradorDirecciones.validarDireccion(idDireccionDTO)){
            throw new ClientesIdDireccionInvalidoException("El ID de la dirección del cliente es inválido.");
        }
        
        DireccionDTONegocios direccionEnvioCliente = administradorDirecciones.obtenerDireccion(idDireccionDTO);
        
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
    public NombresApellidoClienteDTONegocios obtenerNombresApellidoCliente(IdClienteDTONegocios idClienteDTO)
            throws ClientesIdClienteInvalidoException {
        
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        return new NombresApellidoClienteDTONegocios(
                cliente.getNombres(),
                cliente.getApellidoPaterno()
        );
    }
    
    
    @Override
    public boolean validarCliente(IdClienteDTONegocios idClienteDTO) {
        
        if (idClienteDTO == null || idClienteDTO.getIdCliente() == null || !Cliente.existePorId(idClienteDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public void registrarCliente(ClienteDTONegocios nuevoCliente)
            throws ClientesTelefonoNuevoClienteYaExisteException,
            ClientesCorreoElectronicoYaExisteException{
        
        List<ClienteDTONegocios> clientesRegistrados = Cliente.recuperarTodos();
        
        for(ClienteDTONegocios cliente: clientesRegistrados){
            
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
