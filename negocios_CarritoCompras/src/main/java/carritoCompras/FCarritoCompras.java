
package carritoCompras;

import dto.InformacionProductoCarritoDTO;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import objetosnegocio.Producto;


public class FCarritoCompras implements ICarritoCompras{
    
    private HashMap<Integer, List<Producto>> mapaClientesProductos = new HashMap<>();
    
    @Override
    public List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente){
        
        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();
        
        Boolean clienteTieneCarrito = mapaClientesProductos.containsKey(idCliente);
        
        if(clienteTieneCarrito){
            List<Producto> listaProductosCliente = mapaClientesProductos.get(idCliente);
            
            for(Producto producto: listaProductosCliente){
            
            listaInformacionProductoCarritoDTO.add(new InformacionProductoCarritoDTO(producto.getId(), producto.getNombre(), producto.getVariedad(), 
                    producto.getPrecio(), producto.getMilesSemillas(), producto.getNombreProveedor(), 
                    producto.getDireccionImagenProducto()));
            }
            
        }

        return listaInformacionProductoCarritoDTO;
    }
    
    @Override
    public void agregarProducto(Integer idCliente, Producto producto){
        // TODO 
        // Validaciones
        
        mapaClientesProductos.putIfAbsent(idCliente, new LinkedList<>());
        
        mapaClientesProductos.get(idCliente).add(producto);
        
    }
    
    
}
