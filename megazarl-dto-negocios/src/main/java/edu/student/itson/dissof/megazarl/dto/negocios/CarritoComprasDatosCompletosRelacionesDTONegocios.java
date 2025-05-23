
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class CarritoComprasDatosCompletosRelacionesDTONegocios extends CarritoComprasDTONegocios{
    
    /**
     * Objeto ClienteDTONegocios que representa el cliente asociado a este carrito de compras.
     */
    private ClienteDTONegocios cliente;

    /**
     * Objeto PaqueteriaDTONegocios que representa la paquetería asociada a este carrito de compras.
     */
    private PaqueteriaDTONegocios paqueteria;

    /**
     * Objeto {@literal List<ProductoCarritoDTONegocios>} que representa los productos dentro de este carrito.
     */
    private List<ProductoCarritoDTONegocios> productosCarrito;

    public CarritoComprasDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            ClienteDTONegocios cliente,
            PaqueteriaDTONegocios paqueteria,
            List<ProductoCarritoDTONegocios> productosCarrito) {
        
        super(id, esVigente);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.productosCarrito = productosCarrito;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            ClienteDTONegocios cliente,
            PaqueteriaDTONegocios paqueteria) {
        
        super(id, esVigente);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            ClienteDTONegocios cliente,
            List<ProductoCarritoDTONegocios> productosCarrito) {
        
        super(id, esVigente);
        
        this.cliente = cliente;
        this.productosCarrito = productosCarrito;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTONegocios(
            Boolean esVigente,
            ClienteDTONegocios cliente,
            PaqueteriaDTONegocios paqueteria) {
        
        super(esVigente);
        
        this.cliente = cliente;
        this.paqueteria = paqueteria;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTONegocios(
            Boolean esVigente,
            ClienteDTONegocios cliente) {
        
        super(esVigente);
        
        this.cliente = cliente;
    }
    
    public CarritoComprasDatosCompletosRelacionesDTONegocios(
            Boolean esVigente,
            ClienteDTONegocios cliente,
            List<ProductoCarritoDTONegocios> productosCarrito) {
        
        super(esVigente);
        
        this.cliente = cliente;
        this.productosCarrito = productosCarrito;
    }

    public ClienteDTONegocios getCliente(){
        return cliente;
    }
    
    public PaqueteriaDTONegocios getPaqueteria(){
        return paqueteria;
    }
    
    public List<ProductoCarritoDTONegocios> getProductosCarrito(){
        return productosCarrito;
    }
    
    @Override
    public IdEntidadGenericoNegocios getIdCliente() {
        return cliente.getId();
    }

    @Override
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return paqueteria.getId();
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosCarrito() {
        
        List<IdEntidadGenericoNegocios> idsProductoCarrito = new LinkedList();
        
        for(ProductoCarritoDTONegocios productoCarritoDTO: productosCarrito){
            idsProductoCarrito.add(productoCarritoDTO.getId());
        }
        
        return idsProductoCarrito;
                
    }
    
}
