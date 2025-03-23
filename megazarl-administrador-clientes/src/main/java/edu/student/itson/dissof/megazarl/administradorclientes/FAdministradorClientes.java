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
        return buscarCliente(idCliente) != null;
    }

    @Override
    public NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer id) throws ClienteNoExisteException {
        Cliente cliente = buscarCliente(id);
        if (cliente == null) {
            throw new ClienteNoExisteException();            
        }
        
        return new NombreApellidoClienteDTO(
                cliente.getNombre(),
                cliente.getApellidoMaterno()
        );
    }

    private Cliente buscarCliente(Integer idCliente) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(idCliente))
                .findFirst()
                .orElse(null);
    }
}
