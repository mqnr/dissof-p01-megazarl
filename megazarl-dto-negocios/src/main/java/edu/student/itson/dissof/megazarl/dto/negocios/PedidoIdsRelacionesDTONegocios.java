
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;

public class PedidoIdsRelacionesDTONegocios extends PedidoDTONegocios{
    
    private IdEntidadGenericoNegocios idCliente;
    
    private IdEntidadGenericoNegocios idPaqueteria;
    
    private List<IdEntidadGenericoNegocios> idsProductoPedido;

    public PedidoIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            String estado,
            ClienteDTONegocios cliente,
            PaqueteriaDTONegocios paqueteria,
            IdEntidadGenericoNegocios idCliente,
            IdEntidadGenericoNegocios idPaqueteria) {
        
        super(
            id,
            estado);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(IdEntidadGenericoNegocios idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosPedido() {
        return idsProductoPedido;
    }
    
}
