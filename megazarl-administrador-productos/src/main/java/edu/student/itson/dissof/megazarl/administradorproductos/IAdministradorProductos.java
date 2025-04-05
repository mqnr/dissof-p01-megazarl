package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import java.util.List;

public interface IAdministradorProductos {

    public abstract boolean validarProducto(Integer idProducto);
    
    public abstract int cosultarInventarioProducto(Integer idProducto)  throws ProductosIdProductoInvalidoException;
            
    public abstract List<InformacionProductoInicioDTO> obtenerProductosVenta();

    public abstract InformacionProductoVentaDTO obtenerInformacionProductoVenta(Integer idProducto) throws ProductosIdProductoInvalidoException;

    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto);

    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto);

    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto,
            String nombreProveedor);

    public abstract Producto obtenerProducto(Integer idProducto) throws ProductosIdProductoInvalidoException;
    
    public abstract void apartarProductoInventario(Integer idProducto, int cantidad) 
            throws ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;
    
    public abstract void desapartarProductoInventario(Integer idProducto, int cantidad) 
            throws ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;
    
    public abstract boolean validarIdProductoInventario(Integer idProductoInventario);
    
    
}
