
package edu.student.itson.dissof.megazarl.negocio.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.List;
import edu.student.itson.dissof.megazarl.negocio.modelos.Producto;


public interface IAdministradorProductos {
 
    public abstract List<ProductoInicioDTO> obtenerProductosVenta();
    
    public abstract InformacionProductoDTO obtenerInformacionProducto(Integer idProducto);
    
    public abstract Producto obtenerProductoPorId(Integer idProducto);
}
