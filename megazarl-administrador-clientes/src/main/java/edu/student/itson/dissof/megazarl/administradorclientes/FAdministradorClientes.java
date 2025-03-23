package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.repositorio.RepositorioClientes;

public class FAdministradorClientes implements IAdministradorClientes {

    private final RepositorioClientes repositorioClientes;

    public FAdministradorClientes(RepositorioClientes repositorioClientes) {
        this.repositorioClientes = repositorioClientes;
    }

    @Override
    public boolean validarIdCliente(Integer idCliente) {
        return repositorioClientes.existePorId(idCliente);
    }

    @Override
    public NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer id) throws ClienteNoExisteException {
        Cliente cliente = repositorioClientes.buscarPorId(id);
        if (cliente == null) {
            throw new ClienteNoExisteException();
        }

        return new NombreApellidoClienteDTO(
                cliente.getNombre(),
                cliente.getApellidoMaterno()
        );
    }
}
