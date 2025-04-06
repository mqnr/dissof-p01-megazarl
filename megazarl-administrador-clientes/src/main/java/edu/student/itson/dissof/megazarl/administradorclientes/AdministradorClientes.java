package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ClienteON;

import java.util.List;

class AdministradorClientes implements IAdministradorClientes {
    private final IAdministradorDirecciones direcciones;
    private final List<ClienteON> listaClientes;

    public AdministradorClientes(IAdministradorDirecciones direcciones, List<ClienteON> listaClientes) {
        this.listaClientes = listaClientes;
        this.direcciones = direcciones;
    }

    @Override
    public boolean validarIdCliente(Integer idCliente) {
        // Se recorre la lista de Clientes para verificar si alguno tiene el ID
        // recibido.
        for (ClienteON cliente: listaClientes) {
            Integer clienteId = cliente.getId();

            if (clienteId.equals(idCliente)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ClienteON obtenerCliente(Integer idCliente){
        ClienteON clienteRecuperado = null;

        // Se recorre la lista de Clientes para comprobar si alguno
        // tiene el valor del ID del parámetro.
        for (ClienteON cliente: listaClientes) {
            if (cliente.getId().equals(idCliente)) {
                clienteRecuperado = cliente;
            }
        }

        return clienteRecuperado;
    }


    @Override
    public String obtenerColoniaCliente(Integer idCliente)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException{


        // Se valida que el ID del cliente es válido.
        ClienteON cliente = obtenerCliente(idCliente);

        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + idCliente + " es inválido.");
        }

        // Se obtiene el Código Postal de envío del cliente.
        String codigoPostalCliente  = cliente.getCodigoPostalEnvio();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // la colonia de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = direcciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve la colonia obtenida.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getColonia();
        }

        return null;
    }

    @Override
    public String obtenerCiudadCliente(Integer idCliente)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException
    {
        // Se valida que el ID del cliente es válido.
        ClienteON cliente = obtenerCliente(idCliente);

        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + idCliente + " es inválido.");
        }

        // Se obtiene el Código Postal de envío del cliente.
        String codigoPostalCliente = cliente.getCodigoPostalEnvio();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // la ciudad de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = direcciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve la ciudad obtenida.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getCiudad();
        }

        return null;
    }

    @Override
    public String obtenerEstadoCliente(Integer idCliente)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException
    {
        // Se valida que el ID del cliente es válido.
        ClienteON cliente = obtenerCliente(idCliente);

        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + idCliente + " es inválido.");
        }

        // Se obtiene el Código Postal de envío del cliente.
        String codigoPostalCliente  = cliente.getCodigoPostalEnvio();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // el estado de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = direcciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve el estado obtenido.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getEstado();
        }

        return null;
    }

    @Override
    public void actualizarDireccionCliente(InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO) throws ClientesIdClienteInvalidoException{
        Integer idCliente = informacionNoDerivadaCPDireccionEnvioDTO.getIdCliente();

        // Se valida el ID del cliente.
        ClienteON cliente = obtenerCliente(informacionNoDerivadaCPDireccionEnvioDTO.getIdCliente());

        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + idCliente + " es inválido.");
        }

        // Se obtiene el Código Postal, Calle y Número de envío nuevos del Cliente.
        String codigoPostalCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
        String calleCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
        String numeroDomicilioCliente = informacionNoDerivadaCPDireccionEnvioDTO.getNumero();

        // Se actualizan los atributos correspondientes del Cliente.
        cliente.setCodigoPostalEnvio(codigoPostalCliente);
        cliente.setCalleEnvio(calleCliente);
        cliente.setNumeroDomicilioEnvio(numeroDomicilioCliente);
    }

    @Override
    public InformacionNoDerivadaCPDireccionEnvioDTO obtenerInformacionNoDerivadaCPDireccionEnvio(Integer idCliente) throws ClientesIdClienteInvalidoException{
        // Se valida el ID del cliente.
        ClienteON cliente = obtenerCliente(idCliente);

        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + idCliente + " es inválido.");
        }

        // Se obtienen los datos actuales del cliente y se envían en un DTO.
        String codigoPostalCliente = cliente.getCodigoPostalEnvio();
        String numeroCliente  = cliente.getNumeroDomicilioEnvio();
        String calleCliente = cliente.getCalleEnvio();

        return new InformacionNoDerivadaCPDireccionEnvioDTO(numeroCliente, calleCliente, codigoPostalCliente);
    }

    @Override
    public NombresApellidoClienteDTO obtenerNombresApellidoCliente(Integer idCliente) throws ClientesIdClienteInvalidoException {
        // Se valida el ID del cliente.
        ClienteON cliente = obtenerCliente(idCliente);

        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + idCliente + " es inválido.");
        }

        // Se obtienen los datos actuales del cliente y se envían en un DTO.
        String nombresCliente = cliente.getNombres();
        String apellidoPaternoCliente = cliente.getApellidoPaterno();

        return new NombresApellidoClienteDTO(nombresCliente, apellidoPaternoCliente);
    }
}
