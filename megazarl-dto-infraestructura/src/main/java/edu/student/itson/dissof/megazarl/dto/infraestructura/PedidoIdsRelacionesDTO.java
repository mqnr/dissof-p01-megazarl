
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.List;

public class PedidoIdsRelacionesDTO extends PedidoDTO{
    
    private IdClienteDTO idCliente;
    
    private IdPaqueteriaDTO idPaqueteria;
    
    private List<IdProductoPedidoDTO> idsProductoPedido;

    public PedidoIdsRelacionesDTO(
            Long id,
            String estado,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria,
            IdClienteDTO idCliente,
            IdPaqueteriaDTO idPaqueteria) {
        
        super(
            id,
            estado);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public IdClienteDTO getIdCliente() {
        return new IdClienteDTO(idCliente.getIdCliente());
    }

    @Override
    public void setIdCliente(IdClienteDTO idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public IdPaqueteriaDTO getIdPaqueteria() {
        return new IdPaqueteriaDTO(idPaqueteria.getIdPaqueteria());
    }

    @Override
    public List<IdProductoPedidoDTO> getIdsProductosPedido() {
        return idsProductoPedido;
    }
    
}
