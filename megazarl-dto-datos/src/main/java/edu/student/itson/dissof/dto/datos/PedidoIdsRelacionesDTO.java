
package edu.student.itson.dissof.dto.datos;

import java.util.List;

public class PedidoIdsRelacionesDTO extends PedidoDTO{
    
    private Long idCliente;
    
    private Long idPaqueteria;
    
    private List<Long> idsProductoPedido;

    public PedidoIdsRelacionesDTO(
            Long id,
            String estado,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria,
            Long idCliente,
            Long idPaqueteria) {
        
        super(
            id,
            estado);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public Long getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public Long getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<Long> getIdsProductosPedido() {
        return idsProductoPedido;
    }
    
}
