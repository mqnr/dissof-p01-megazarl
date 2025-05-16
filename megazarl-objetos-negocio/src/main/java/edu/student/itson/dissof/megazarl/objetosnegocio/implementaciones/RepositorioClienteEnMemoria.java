package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioClienteEnMemoria implements RepositorioCliente {
    
    private final List<ClienteDTO> listaClientes;
    
    private static Long ID_CLIENTE_ACTUAL = 1L;

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
        cliente.setId(ID_CLIENTE_ACTUAL++);
        listaClientes.add(cliente);
    }

    @Override
    public void agregar(Collection<ClienteDTO> clientes) {
        for(ClienteDTO cliente: clientes){
            cliente.setId(ID_CLIENTE_ACTUAL++);
        }
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
        
        return new ClienteDatosCompletosRelacionesDTO(
                ((ClienteDatosCompletosRelacionesDTO)clienteOriginal).getId(),
                actualizacionClienteDTO.tieneNombres() ? actualizacionClienteDTO.getNombres() : clienteOriginal.getNombres(),
                actualizacionClienteDTO.tieneApellidoPaterno() ? actualizacionClienteDTO.getApellidoPaterno() : clienteOriginal.getApellidoPaterno(),
                actualizacionClienteDTO.tieneApellidoMaterno() ? actualizacionClienteDTO.getApellidoMaterno() : clienteOriginal.getApellidoMaterno(),
                clienteOriginal.getTelefono(),
                clienteOriginal.getCorreoElectronico(),
                actualizacionClienteDTO.tieneDireccionEnvio() 
                        ? actualizacionClienteDTO.getDireccionEnvio() 
                        : ((ClienteDatosCompletosRelacionesDTO)clienteOriginal).getDireccionEnvio(),
                ((ClienteDatosCompletosRelacionesDTO)clienteOriginal).getCarritosCompras()
        );
    }

}