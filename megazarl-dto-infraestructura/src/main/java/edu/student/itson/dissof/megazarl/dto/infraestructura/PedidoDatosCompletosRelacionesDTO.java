
package edu.student.itson.dissof.megazarl.dto.infraestructura;
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
    public IdClienteDTO getIdCliente() {
        return new IdClienteDTO(cliente.getId());
    }

    @Override
    public void setIdCliente(IdClienteDTO idClienteDTO) {
        cliente.setId(idClienteDTO.getIdCliente());
    }

    @Override
    public IdPaqueteriaDTO getIdPaqueteria() {
        return new IdPaqueteriaDTO(paqueteria.getId());
    }

    @Override
    public List<IdProductoPedidoDTO> getIdsProductosPedido() {
        
        List<IdProductoPedidoDTO> listaIdsProductosPedido = new LinkedList<>();
        
        for(ProductoPedidoDTO productoPedidoDTO: productosPedido){
            listaIdsProductosPedido.add(new IdProductoPedidoDTO(productoPedidoDTO.getId()));
        }
        
        return listaIdsProductosPedido;
    }

}
