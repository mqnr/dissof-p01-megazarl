
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.LinkedList;
import java.util.List;


public class CarritoComprasDatosCompletosRelacionesDTO extends CarritoComprasDTO{
    
    /**
     * Objeto ClienteDTO que representa el cliente asociado a este carrito de compras.
     */
    private ClienteDTO cliente;

    /**
     * Objeto PaqueteriaDTO que representa la paquetería asociada a este carrito de compras.
     */
    private PaqueteriaDTO paqueteria;

    /**
     * Objeto {@literal List<ProductoCarritoDTO>} que representa los productos dentro de este carrito.
     */
    private List<ProductoCarritoDTO> productosCarrito;

    public CarritoComprasDatosCompletosRelacionesDTO(
            Long id,
            Boolean esVigente,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria,
            List<ProductoCarritoDTO> productosCarrito) {
        
        super(id, esVigente);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.productosCarrito = productosCarrito;
    }
    
     public CarritoComprasDatosCompletosRelacionesDTO(
            Boolean esVigente,
            ClienteDTO cliente,
            List<ProductoCarritoDTO> productosCarrito) {
        
        super(esVigente);
        
        this.cliente = cliente;
        this.productosCarrito = productosCarrito;
    }

    public ClienteDTO getCliente(){
        return cliente;
    }
    
    public PaqueteriaDTO getPaqueteria(){
        return paqueteria;
    }
    
    public List<ProductoCarritoDTO> getProductosCarrito(){
        return productosCarrito;
    }
    
    @Override
    public IdClienteDTO getIdCliente() {
        return new IdClienteDTO(cliente.getId());
    }

    @Override
    public IdPaqueteriaDTO getIdPaqueteria() {
        return new IdPaqueteriaDTO(paqueteria.getId());
    }

    @Override
    public List<IdProductoCarritoDTO> getIdsProductosCarrito() {
        
        List<IdProductoCarritoDTO> idsProductoCarrito = new LinkedList();
        
        for(ProductoCarritoDTO productoCarritoDTO: productosCarrito){
            idsProductoCarrito.add(new IdProductoCarritoDTO(productoCarritoDTO.getId()));
        }
        
        return idsProductoCarrito;
                
    }
}
