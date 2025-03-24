package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.List;

public interface IAdministradorProductos {

    public abstract boolean validarProducto(Integer idProducto);
    
    public abstract int cosultarInventarioProducto(Integer idProducto);
            
    public abstract List<ProductoInicioDTO> obtenerProductosVenta();

    public abstract InformacionProductoDTO obtenerInformacionProducto(Integer idProducto);

    public abstract List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto);

    public abstract List<ProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto);

    public abstract List<ProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto,
            String nombreProveedor);

    public abstract List<Float> obtenerTiempoMatrizProductosInventario(Integer idProducto);

    public abstract double obtenerCostoProducto(Integer idProducto);
    
    public abstract boolean eliminarProductoInventario(Integer idProducto, Integer cantidad);
    
    public abstract boolean apartarProductoInventario(Integer idProducto);
    
    public abstract boolean desapartarProductoInventario(Integer idProducto);
    
    public abstract boolean validarProductoInventario(Integer idProductoInventario);
    
    public abstract Double obtenerPesoProductoInventario(Integer idProductoInventario);
    
    public abstract Double obtenerTiempoMatrzProductoInventario(Integer idProductoInventario);
}
