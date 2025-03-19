
package administradorProductos;

import dto.InformacionProductoDTO;
import dto.ProductoInicioDTO;
import java.util.List;
import objetosnegocio.Producto;


public interface IAdministradorProductos {
 
    public abstract List<ProductoInicioDTO> obtenerProductosVenta();
    
    public abstract InformacionProductoDTO obtenerInformacionProducto(Integer idProducto);
    
    public abstract Producto obtenerProductoPorId(Integer idProducto);
}
