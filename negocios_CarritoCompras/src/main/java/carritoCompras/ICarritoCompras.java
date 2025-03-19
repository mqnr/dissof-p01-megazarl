
package carritoCompras;

import dto.InformacionProductoCarritoDTO;
import java.util.List;
import objetosnegocio.Producto;


public interface ICarritoCompras {
    
    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente);
    
    public abstract void agregarProducto(Integer idCliente, Producto producto);
}
