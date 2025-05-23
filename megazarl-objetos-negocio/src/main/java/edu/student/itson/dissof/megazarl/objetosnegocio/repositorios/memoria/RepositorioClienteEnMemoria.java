package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class RepositorioClienteEnMemoria implements RepositorioCliente {
    
    private final List<ClienteDTONegocios> listaClientes;
    
    private static Long ID_CLIENTE_ACTUAL = 1L;

    public RepositorioClienteEnMemoria() {
        listaClientes = new ArrayList<>();
    }

    public RepositorioClienteEnMemoria(Collection<ClienteDTONegocios> clientes) {
        listaClientes = new ArrayList<>(clientes);
    }

    @Override
    public ClienteDTONegocios recuperarPorId(IdClienteDTONegocios idClienteDTO) {
        return listaClientes.stream()
                .filter(cliente -> cliente.getId().getId().equals(idClienteDTO.getIdCliente().getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdClienteDTONegocios idClienteDTO) {
        
        return listaClientes.stream().anyMatch(cliente -> cliente.getId().getId().equals(idClienteDTO.getIdCliente().getId()));

    }
    
    @Override
    public ClienteDTONegocios actualizar(ActualizacionClienteDTONegocios actualizacionClienteDTO) {
        
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteDTONegocios cliente = listaClientes.get(i);
            if (cliente.getId().equals(actualizacionClienteDTO.getId())) {
                ClienteDTONegocios clienteActualizado = aplicar(cliente, actualizacionClienteDTO);
                listaClientes.set(i, clienteActualizado);
                return clienteActualizado;
            }
        }
        return null;
    }

    @Override
    public void agregar(ClienteDTONegocios cliente) {
        cliente.setId(new IdEntidadGenericoNegocios(ID_CLIENTE_ACTUAL++));
        listaClientes.add(cliente);
    }

    @Override
    public void agregar(Collection<ClienteDTONegocios> clientes) {
        for(ClienteDTONegocios cliente: clientes){
            cliente.setId(new IdEntidadGenericoNegocios(ID_CLIENTE_ACTUAL++));
        }
        listaClientes.addAll(clientes);
    }

    @Override
    public List<ClienteDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaClientes);
    }
    
    
    private ClienteDTONegocios aplicar(ClienteDTONegocios clienteOriginal, ActualizacionClienteDTONegocios actualizacionClienteDTO) {
        
        return new ClienteDatosCompletosRelacionesDTONegocios(
                ((ClienteDatosCompletosRelacionesDTONegocios)clienteOriginal).getId(),
                actualizacionClienteDTO.tieneNombres() ? actualizacionClienteDTO.getNombres() : clienteOriginal.getNombres(),
                actualizacionClienteDTO.tieneApellidoPaterno() ? actualizacionClienteDTO.getApellidoPaterno() : clienteOriginal.getApellidoPaterno(),
                actualizacionClienteDTO.tieneApellidoMaterno() ? actualizacionClienteDTO.getApellidoMaterno() : clienteOriginal.getApellidoMaterno(),
                clienteOriginal.getTelefono(),
                clienteOriginal.getCorreoElectronico(),
                actualizacionClienteDTO.tieneDireccionEnvio() 
                        ? actualizacionClienteDTO.getDireccionEnvio()
                        : ((ClienteDatosCompletosRelacionesDTONegocios)clienteOriginal).getDireccionEnvio(),
                ((ClienteDatosCompletosRelacionesDTONegocios)clienteOriginal).getCarritosCompras()
        );
    }

}