
package edu.student.itson.dissof.megazarl.dto.negocios;
import java.util.LinkedList;
import java.util.List;


public class PedidoDatosCompletosRelacionesDTO extends PedidoDTO{
    
    /**
     * Objeto Cliente DTO que representa el cliente que reliza el pedido.
     */
    private ClienteDTO cliente;
    
    /**
     * Objeto PaqueteriaDTO que representa la paquetería utilizada para el pedido.
     */
    private PaqueteriaDTO paqueteria;
    
    private List<ProductoPedidoDTO> productosPedido;

    public PedidoDatosCompletosRelacionesDTO(
            Long id,
            String estado,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria,
            List<ProductoPedidoDTO> productosPedido) {
        
        super(
            id, 
            estado);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.productosPedido = productosPedido;
    }
    
    public PedidoDatosCompletosRelacionesDTO(
            String estado,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria,
            List<ProductoPedidoDTO> productosPedido) {
        
        super(
            estado);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.productosPedido = productosPedido;
    }
    
    @Override
    public Long getIdCliente() {
        return cliente.getId();
    }

    @Override
    public void setIdCliente(Long idCliente) {
        cliente.setId(idCliente);
    }

    @Override
    public Long getIdPaqueteria() {
        return paqueteria.getId();
    }

    @Override
    public List<Long> getIdsProductosPedido() {
        
        List<Long> listaIdsProductosPedido = new LinkedList<>();
        
        for(ProductoPedidoDTO productoPedidoDTO: productosPedido){
            listaIdsProductosPedido.add(productoPedidoDTO.getId());
        }
        
        return listaIdsProductosPedido;
    }

}
