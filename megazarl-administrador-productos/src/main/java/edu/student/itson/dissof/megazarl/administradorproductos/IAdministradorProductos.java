package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;

public interface IAdministradorProductos {

    public abstract List<ProductoInicioDTO> obtenerProductosVenta();

    public abstract InformacionProductoDTO obtenerInformacionProducto(Integer idProducto);

    public abstract List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto);
    
    public abstract List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, 
            String variedadProducto);
            
    public abstract List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, 
            String variedadProducto, String nombreProveedor);        
    
    public abstract InformacionProductoCarritoDTO obtenerInformacionProductoCarrito(InformacionProductoCarritoDTO informacionProductoCarritoDTO);
    
    public abstract List<ProductoInventario> obtenerListaProductoInventario(Integer idProducto);
    
    public abstract double obtenerCostoProducto(Integer idProducto);
}
