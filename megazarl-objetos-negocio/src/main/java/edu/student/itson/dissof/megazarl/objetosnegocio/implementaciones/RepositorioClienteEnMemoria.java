package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ClienteDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioClienteEnMemoria implements RepositorioCliente {
    private final List<ClienteDTO> listaClientes;

    public RepositorioClienteEnMemoria() {
        listaClientes = new ArrayList<>();
    }

    public RepositorioClienteEnMemoria(Collection<ClienteDTO> clientes) {
        listaClientes = new ArrayList<>(clientes);
    }

    @Override
    public ClienteDTO recuperarPorId(IdClienteDTO idClienteDTO) {
        return listaClientes.stream()
                .filter(cliente -> cliente.getId().equals(idClienteDTO.getIdCliente()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdClienteDTO idClienteDTO) {
        return existe(cliente -> cliente.getId().equals(idClienteDTO.getIdCliente()));
    }

    @Override
    public ClienteDTO actualizar(ActualizacionClienteDTO actualizacionClienteDTO) {
        
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteDTO cliente = listaClientes.get(i);
            if (cliente.getId().equals(actualizacionClienteDTO.getId())) {
                ClienteDTO clienteActualizado = aplicar(cliente, actualizacionClienteDTO);
                listaClientes.set(i, clienteActualizado);
                return clienteActualizado;
            }
        }
        return null;
    }

    @Override
    public Stream<ClienteDTO> stream() {
        return listaClientes.stream();
    }

    @Override
    public void agregar(ClienteDTO cliente) {
        listaClientes.add(cliente);
    }

    @Override
    public void agregar(Collection<ClienteDTO> clientes) {
        listaClientes.addAll(clientes);
    }

    @Override
    public List<ClienteDTO> recuperarTodos() {
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

    private ClienteDTO aplicar(ClienteDTO clienteOriginal, ActualizacionClienteDTO actualizacionClienteDTO) {
        return new ClienteDTO(
                actualizacionClienteDTO.getId(),
                actualizacionClienteDTO.tieneNombres() ? actualizacionClienteDTO.getNombres() : clienteOriginal.getNombres(),
                actualizacionClienteDTO.tieneApellidoPaterno() ? actualizacionClienteDTO.getApellidoPaterno() : clienteOriginal.getApellidoPaterno(),
                actualizacionClienteDTO.tieneApellidoMaterno() ? actualizacionClienteDTO.getApellidoMaterno() : clienteOriginal.getApellidoMaterno(),
                actualizacionClienteDTO.tieneDireccionEnvio() ? actualizacionClienteDTO.getDireccionEnvio() : clienteOriginal.getDireccionEnvio()
        );
    }

}