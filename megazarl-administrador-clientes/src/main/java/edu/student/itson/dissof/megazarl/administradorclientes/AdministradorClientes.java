package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.FAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.modelos.ClienteDTO;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionCliente;
import edu.student.itson.dissof.megazarl.repositorio.entidades.Cliente;

enum AdministradorClientes implements IAdministradorClientes {
    INSTANCIA;

    @Override
    public boolean validarIdCliente(Integer id) {
        return Cliente.existePorId(id);
    }

    @Override
    public ClienteDTO obtenerCliente(Integer id) {
        return Cliente.buscarPorId(id);
    }

    @Override
    public String obtenerColoniaCliente(Integer id)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException {
        ClienteDTO cliente = obtenerCliente(id);
        validarCliente(cliente, id);

        String codigoPostalCliente = cliente.codigoPostalEnvio();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // la colonia de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = FAdministradorDirecciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve la colonia obtenida.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getColonia();
        }

        return null;
    }

    @Override
    public String obtenerCiudadCliente(Integer id)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException
    {
        // Se valida que el ID del cliente es válido.
        ClienteDTO cliente = obtenerCliente(id);
        validarCliente(cliente, id);

        String codigoPostalCliente = cliente.codigoPostalEnvio();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // la ciudad de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = FAdministradorDirecciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve la ciudad obtenida.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getCiudad();
        }

        return null;
    }

    @Override
    public String obtenerEstadoCliente(Integer id)
            throws ClientesIdClienteInvalidoException,
            DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException
    {
        ClienteDTO cliente = obtenerCliente(id);
        validarCliente(cliente, id);

        String codigoPostalCliente = cliente.codigoPostalEnvio();

        // Se obtiene los datos derivados a paritr del Código Postal, incluyendo
        // el estado de envío; utilizando el método obtenerDatosDireccionDerivados()
        // del subsistema direcciones.
        InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO
                = FAdministradorDirecciones.obtenerDatosDireccionDerivados(codigoPostalCliente);

        // Se obtiene y se devuelve el estado obtenido.
        if (informacionDerivadaDireccionEnvioDTO != null) {
            return informacionDerivadaDireccionEnvioDTO.getEstado();
        }

        return null;
    }

    @Override
    public void actualizarDireccionCliente(InformacionNoDerivadaCPDireccionEnvioDTO informacion) throws ClientesIdClienteInvalidoException{
        ClienteDTO clienteActualizado = Cliente.actualizar(
                informacion.getIdCliente(),
                new ActualizacionCliente()
                        .codigoPostalEnvio(informacion.getCodigoPostal())
                        .calleEnvio(informacion.getCalle())
                        .numeroDomicilioEnvio(informacion.getNumero())
        );

        validarCliente(clienteActualizado, informacion.getIdCliente());
    }

    @Override
    public InformacionNoDerivadaCPDireccionEnvioDTO obtenerInformacionNoDerivadaCPDireccionEnvio(Integer id) throws ClientesIdClienteInvalidoException{
        ClienteDTO cliente = obtenerCliente(id);
        validarCliente(cliente, id);

        return new InformacionNoDerivadaCPDireccionEnvioDTO(
                cliente.numeroDomicilioEnvio(),
                cliente.calleEnvio(),
                cliente.codigoPostalEnvio()
        );
    }

    @Override
    public NombresApellidoClienteDTO obtenerNombresApellidoCliente(Integer id) throws ClientesIdClienteInvalidoException {
        ClienteDTO cliente = obtenerCliente(id);
        validarCliente(cliente, id);

        return new NombresApellidoClienteDTO(
                cliente.nombres(),
                cliente.apellidoPaterno()
        );
    }

    private void validarCliente(ClienteDTO cliente, Integer id) throws ClientesIdClienteInvalidoException {
        if (cliente == null) {
            throw new ClientesIdClienteInvalidoException("El ID del cliente: " + id + " es inválido.");
        }
    }
}
