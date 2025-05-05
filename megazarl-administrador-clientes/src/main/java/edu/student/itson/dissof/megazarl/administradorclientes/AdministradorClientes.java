package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.DireccionDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;



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
    public String obtenerCiudadCliente(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException
    {
        // Se valida que el ID del cliente.
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        String codigoPostalCliente = cliente.getDireccionEnvio().getCodigoPostal();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // la ciudad de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = administradorDirecciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve la ciudad obtenida.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getCiudad();
        }

        return null;
    }

    @Override
    public String obtenerEstadoCliente(IdClienteDTO idClienteDTO)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException {
        
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        String codigoPostalCliente = cliente.getDireccionEnvio().getCodigoPostal();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // el estado de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = administradorDirecciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve el estado obtenido.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getEstado();
        }

        return null;
    }

    @Override
    public void actualizarDireccionCliente(InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO)
            throws ClientesIdClienteInvalidoException{
        
        Long idCliente = informacionNoDerivadaCPDireccionEnvioDTO.getIdCliente();
        
        if(!validarCliente(new IdClienteDTO(idCliente))){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ActualizacionClienteDTO actualizacionClienteDTO = new ActualizacionClienteDTO(idCliente);
        
        String nuevoNumero = informacionNoDerivadaCPDireccionEnvioDTO.getNumero();
        String nuevaCalle = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
        String nuevaColonia = informacionNoDerivadaCPDireccionEnvioDTO.getColonia();
        String nuevoCodigoPostal = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
        
        DireccionDTO nuevaDireccion = new DireccionDTO(
                nuevoCodigoPostal, 
                nuevaColonia, 
                nuevaCalle, 
                nuevoNumero);
        
        actualizacionClienteDTO.setDireccionEnvio(nuevaDireccion);
        
        Cliente.actualizar(actualizacionClienteDTO);

    }

    @Override
    public InformacionNoDerivadaCPDireccionEnvioDTO obtenerInformacionNoDerivadaCPDireccionEnvio(IdClienteDTO idClienteDTO) 
            throws ClientesIdClienteInvalidoException{
        
        if(!validarCliente(idClienteDTO)){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new ClientesIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        return new InformacionNoDerivadaCPDireccionEnvioDTO(
                cliente.getDireccionEnvio().getNumero(),
                cliente.getDireccionEnvio().getCalle(),
                cliente.getDireccionEnvio().getColonia(),
                cliente.getDireccionEnvio().getCodigoPostal()
        );
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

}
