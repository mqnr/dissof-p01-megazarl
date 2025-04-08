package edu.student.itson.dissof.megazarl.repositorio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.modelos.ClienteDTO;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionCliente;
import edu.student.itson.dissof.megazarl.repositorio.entidades.RepositorioClientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class RepositorioClienteEnMemoria implements RepositorioClientes {
    private final List<ClienteDTO> listaClientes;

    public RepositorioClienteEnMemoria() {
        listaClientes = new ArrayList<>();
    }

    public RepositorioClienteEnMemoria(Collection<ClienteDTO> clientes) {
        listaClientes = new ArrayList<>(clientes);
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) {
        return listaClientes.stream()
                .filter(cliente -> cliente.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(Integer id) {
        return existe(cliente -> cliente.id().equals(id));
    }

    @Override
    public ClienteDTO actualizar(Integer id, ActualizacionCliente actualizacion) {
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteDTO cliente = listaClientes.get(i);
            if (cliente.id().equals(id)) {
                ClienteDTO clienteActualizado = aplicar(cliente, actualizacion);
                listaClientes.set(i, clienteActualizado);
                return clienteActualizado;
            }
        }
        return null;
    }

    @Override
    public void guardar(ClienteDTO cliente) {
        listaClientes.add(cliente);
    }

    @Override
    public void guardarMuchos(Collection<ClienteDTO> clientes) {
        listaClientes.addAll(clientes);
    }

    @Override
    public List<ClienteDTO> encontrarTodos() {
        return new ArrayList<>(listaClientes);
    }

    @Override
    public long cuenta() {
        return listaClientes.size();
    }

    @Override
    public boolean existe(Predicate<ClienteDTO> criterio) {
        return listaClientes.stream().anyMatch(criterio);
    }

    private ClienteDTO aplicar(ClienteDTO clienteOriginal, ActualizacionCliente actualizacion) {
        return new ClienteDTO(
                clienteOriginal.id(),
                actualizacion.tieneNombres() ? actualizacion.getNombres() : clienteOriginal.nombres(),
                actualizacion.tieneApellidoPaterno() ? actualizacion.getApellidoPaterno() : clienteOriginal.apellidoPaterno(),
                actualizacion.tieneApellidoMaterno() ? actualizacion.getApellidoMaterno() : clienteOriginal.apellidoMaterno(),
                actualizacion.tieneCodigoPostalEnvio() ? actualizacion.getCodigoPostalEnvio() : clienteOriginal.codigoPostalEnvio(),
                actualizacion.tieneCalleEnvio() ? actualizacion.getCalleEnvio() : clienteOriginal.calleEnvio(),
                actualizacion.tieneNumeroDomicilioEnvio() ? actualizacion.getNumeroDomicilioEnvio() : clienteOriginal.numeroDomicilioEnvio()
        );
    }
}
