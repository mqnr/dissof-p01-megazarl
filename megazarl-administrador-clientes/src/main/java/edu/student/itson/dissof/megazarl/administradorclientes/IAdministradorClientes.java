package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;

public interface IAdministradorClientes {

    public abstract boolean validarIdCliente(Integer idCliente);

    public abstract NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente) throws ClienteNoExisteException;
}
