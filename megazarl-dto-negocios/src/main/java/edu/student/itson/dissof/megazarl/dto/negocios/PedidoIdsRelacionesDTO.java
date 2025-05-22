
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.List;

public class PedidoIdsRelacionesDTO extends PedidoDTO{
    
    private IdEntidadGenerico idCliente;
    
    private IdEntidadGenerico idPaqueteria;
    
    private List<IdEntidadGenerico> idsProductoPedido;

    public PedidoIdsRelacionesDTO(
            IdEntidadGenerico id,
            String estado,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria,
            IdEntidadGenerico idCliente,
            IdEntidadGenerico idPaqueteria) {
        
        super(
            id,
            estado);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public IdEntidadGenerico getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(IdEntidadGenerico idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public IdEntidadGenerico getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosPedido() {
        return idsProductoPedido;
    }
    
}
