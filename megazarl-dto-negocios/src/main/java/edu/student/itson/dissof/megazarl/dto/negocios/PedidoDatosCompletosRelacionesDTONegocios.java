
package edu.student.itson.dissof.megazarl.dto.negocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class PedidoDatosCompletosRelacionesDTONegocios extends PedidoDTONegocios{
    
    /**
     * Objeto Cliente DTO que representa el cliente que reliza el pedido.
     */
    private ClienteDTONegocios cliente;
    
    /**
     * Objeto PaqueteriaDTONegocios que representa la paquetería utilizada para el pedido.
     */
    private PaqueteriaDTONegocios paqueteria;
    
    private List<ProductoPedidoDTONegocios> productosPedido;

    public PedidoDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            String estado,
            ClienteDTONegocios cliente,
            PaqueteriaDTONegocios paqueteria,
            List<ProductoPedidoDTONegocios> productosPedido) {
        
        super(
            id, 
            estado);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.productosPedido = productosPedido;
    }
    
    public PedidoDatosCompletosRelacionesDTONegocios(
            String estado,
            ClienteDTONegocios cliente,
            PaqueteriaDTONegocios paqueteria,
            List<ProductoPedidoDTONegocios> productosPedido) {
        
        super(
            estado);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.productosPedido = productosPedido;
    }
    
    @Override
    public IdEntidadGenericoNegocios getIdCliente() {
        return cliente.getId();
    }

    @Override
    public void setIdCliente(IdEntidadGenericoNegocios idCliente) {
        cliente.setId(idCliente);
    }

    @Override
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return paqueteria.getId();
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosPedido() {
        
        List<IdEntidadGenericoNegocios> listaIdsProductosPedido = new LinkedList<>();
        
        for(ProductoPedidoDTONegocios productoPedidoDTO: productosPedido){
            listaIdsProductosPedido.add(productoPedidoDTO.getId());
        }
        
        return listaIdsProductosPedido;
    }

}
