package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import java.util.List;

public class FAdministradorClientes implements IAdministradorClientes {

    private List<Cliente> clientes;

    public FAdministradorClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public boolean validarIdCliente(Integer idCliente) {
        return this.buscarCliente(idCliente).getId().equals(idCliente);
    }

    @Override
    public NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer id) throws ClienteNoExisteException {
        if (this.validarIdCliente(id)) {
            for (Cliente cliente : clientes) {
                if (cliente.getId().equals(id)) {
                    NombreApellidoClienteDTO nombreApellidoClienteDTO = new NombreApellidoClienteDTO(
                            cliente.getNombre(), cliente.getApellidoMaterno());
                    return nombreApellidoClienteDTO;
                }
            }
        }
        throw new ClienteNoExisteException("El Id del cliente no es válido.");
    }

    private Cliente buscarCliente(Integer idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }

        return null;
    }
}
