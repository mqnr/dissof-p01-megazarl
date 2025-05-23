
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
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
            IdEntidadGenerico id,
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
            IdEntidadGenerico id,
            Boolean esVigente,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria) {
        
        super(id, esVigente);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTO(
            IdEntidadGenerico id,
            Boolean esVigente,
            ClienteDTO cliente,
            List<ProductoCarritoDTO> productosCarrito) {
        
        super(id, esVigente);
        
        this.cliente = cliente;
        this.productosCarrito = productosCarrito;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTO(
            Boolean esVigente,
            ClienteDTO cliente,
            PaqueteriaDTO paqueteria) {
        
        super(esVigente);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTO(
            Boolean esVigente,
            ClienteDTO cliente) {
        
        super(esVigente);
        
        this.cliente = cliente;
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
    public IdEntidadGenerico getIdCliente() {
        return cliente.getId();
    }

    @Override
    public IdEntidadGenerico getIdPaqueteria() {
        return paqueteria.getId();
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosCarrito() {
        
        List<IdEntidadGenerico> idsProductoCarrito = new LinkedList();
        
        for(ProductoCarritoDTO productoCarritoDTO: productosCarrito){
            idsProductoCarrito.add(productoCarritoDTO.getId());
        }
        
        return idsProductoCarrito;
                
    }
}
