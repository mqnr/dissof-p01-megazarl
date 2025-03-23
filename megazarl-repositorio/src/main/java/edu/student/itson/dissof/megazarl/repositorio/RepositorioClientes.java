package edu.student.itson.dissof.megazarl.repositorio;

import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;

public interface RepositorioClientes extends Repositorio<Cliente> {

    Cliente buscarPorId(Integer id);

    boolean existePorId(Integer id);
}
