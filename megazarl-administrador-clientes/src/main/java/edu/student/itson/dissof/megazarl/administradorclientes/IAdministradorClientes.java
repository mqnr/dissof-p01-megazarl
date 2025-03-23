package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;

public interface IAdministradorClientes {

    boolean validarIdCliente(Integer idCliente);

    NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente) throws ClienteNoExisteException;
}
