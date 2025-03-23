package edu.student.itson.dissof.megazarl.mock.repositorios;

import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.repositorio.RepositorioClientes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class RepositorioClientesMock implements RepositorioClientes {

    private final List<Cliente> listaClientes;

    public RepositorioClientesMock() {
        listaClientes = new ArrayList<>();
    }

    public RepositorioClientesMock(Collection<Cliente> clientes) {
        listaClientes = new ArrayList<>(clientes);
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return listaClientes.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Cliente cliente) {
        listaClientes.add(cliente);
    }

    @Override
    public void guardarMuchos(Collection<Cliente> clientes) {
        listaClientes.addAll(clientes);
    }

    @Override
    public List<Cliente> encontrarTodos() {
        return new ArrayList<>(listaClientes);
    }

    @Override
    public long cuenta() {
        return listaClientes.size();
    }

    @Override
    public boolean existe(Predicate<Cliente> criterio) {
        return listaClientes.stream().anyMatch(criterio);
    }

    @Override
    public boolean existePorId(Integer id) {
        return existe(cliente -> cliente.getId().equals(id));
    }
}
