package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import java.util.List;

public interface IAdministradorProductos {

    List<ProductoInicioDTO> obtenerProductosVenta();

    InformacionProductoDTO obtenerInformacionProducto(Integer idProducto);

    List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto);

    List<ProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto);

    List<ProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto,
            String nombreProveedor);

    List<ProductoInventario> obtenerListaProductoInventario(Integer idProducto);

    double obtenerCostoProducto(Integer idProducto);
}
