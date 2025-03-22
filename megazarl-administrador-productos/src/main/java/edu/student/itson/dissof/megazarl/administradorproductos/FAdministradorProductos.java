package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;

public class FAdministradorProductos implements IAdministradorProductos {

    private List<Producto> listaProductos;
             
    public FAdministradorProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    
    public void cosultarInventarioProducto(Integer idProducto){
        
    }
    
            
            
    @Override
    public List<ProductoInicioDTO> obtenerProductosVenta() {

        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for (Producto producto : listaProductos) {
            List<ProductoInventario> listaProductoInventario = producto.getListaProductoInventario();
                
                boolean hayExistencias = false;
                for (ProductoInventario productoInventario: listaProductoInventario) {
                    if (productoInventario.getCantidad() != 0) {
                        hayExistencias = true;
                    }
                }

            if (hayExistencias) {
                listaProductoInicioDTO.add(new ProductoInicioDTO(producto.getId(), producto.getNombre(), producto.getVariedad(), producto.getPrecio(),
                        producto.getMilesSemillas(), producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor()));
            }

        }

        return listaProductoInicioDTO;
    }
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto){
        
        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for (Producto producto : listaProductos) {
            if(nombreProducto.toLowerCase().contains(producto.getNombre().toLowerCase())){
                List<ProductoInventario> listaProductoInventario = producto.getListaProductoInventario();
                
                boolean hayExistencias = false;
                for (ProductoInventario productoInventario: listaProductoInventario) {
                    if (productoInventario.getCantidad() != 0) {
                        hayExistencias = true;
                    }
                }

                if (hayExistencias) {
                    listaProductoInicioDTO.add(new ProductoInicioDTO(producto.getId(), producto.getNombre(), producto.getVariedad(), producto.getPrecio(),
                            producto.getMilesSemillas(), producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor()));
                }
            }
            

        }

        return listaProductoInicioDTO;
    }
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto){
        
        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for (Producto producto : listaProductos) {
            if(nombreProducto.toLowerCase().contains(producto.getNombre().toLowerCase()) && 
                    variedadProducto.toLowerCase().contains(producto.getVariedad().toLowerCase())){
                List<ProductoInventario> listaProductoInventario = producto.getListaProductoInventario();
                
                boolean hayExistencias = false;
                for (ProductoInventario productoInventario: listaProductoInventario) {
                    if (productoInventario.getCantidad() != 0) {
                        hayExistencias = true;
                    }
                }

                if (hayExistencias) {
                    listaProductoInicioDTO.add(new ProductoInicioDTO(producto.getId(), producto.getNombre(), producto.getVariedad(), producto.getPrecio(),
                            producto.getMilesSemillas(), producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor()));
                }
            }
            

        }

        return listaProductoInicioDTO;
    }
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto, String nombreProveedor){
        
        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for (Producto producto : listaProductos) {
            if(nombreProducto.toLowerCase().contains(producto.getNombre().toLowerCase()) 
                    && variedadProducto.toLowerCase().contains(producto.getVariedad().toLowerCase()) 
                    && nombreProveedor.toLowerCase().contains(nombreProveedor.toLowerCase())){
                List<ProductoInventario> listaProductoInventario = producto.getListaProductoInventario();
                
                boolean hayExistencias = false;
                for (ProductoInventario productoInventario: listaProductoInventario) {
                    if (productoInventario.getCantidad() != 0) {
                        hayExistencias = true;
                    }
                }

                if (hayExistencias) {
                    listaProductoInicioDTO.add(new ProductoInicioDTO(producto.getId(), producto.getNombre(), producto.getVariedad(), producto.getPrecio(),
                            producto.getMilesSemillas(), producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor()));
                }
            }
            

        }

        return listaProductoInicioDTO;
    }

    @Override
    public InformacionProductoDTO obtenerInformacionProducto(Integer idProducto) {

        InformacionProductoDTO informacionProductoDTO = null;

        for (Producto producto : listaProductos) {
            if (producto.getId().equals(idProducto)) {

                List<ProductoInventario> listaProductoInventario = producto.getListaProductoInventario();
                boolean hayExistencias = false;
                for (ProductoInventario productoInventario: listaProductoInventario) {
                    if (productoInventario.getCantidad() != 0) {
                        hayExistencias = true;
                    }
                }
                if (hayExistencias) {
                    return new InformacionProductoDTO(producto.getId(), producto.getNombre(), producto.getVariedad(),
                            producto.getDescripcion(), producto.getPrecio(), producto.getMilesSemillas(), producto.getNombreProveedor(),
                            producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor());
                }
            }
        }

        return informacionProductoDTO;
    }

    @Override
    public InformacionProductoCarritoDTO obtenerInformacionProductoCarrito(InformacionProductoCarritoDTO informacionProductoCarritoDTO) {
        Integer idProducto = informacionProductoCarritoDTO.getId();
        
        Producto producto = this.obtenerProductoPorId(idProducto);
        
        informacionProductoCarritoDTO.setNombre(producto.getNombre());
        informacionProductoCarritoDTO.setVariedad(producto.getVariedad());
        informacionProductoCarritoDTO.setPrecio(producto.getPrecio());
        informacionProductoCarritoDTO.setMilesSemillas(producto.getMilesSemillas());
        informacionProductoCarritoDTO.setNombreProveedor(producto.getNombreProveedor());
        informacionProductoCarritoDTO.setDireccionImagenProducto(producto.getDireccionImagenProducto());
        
        return informacionProductoCarritoDTO;
    }
    
    @Override
    public List<ProductoInventario> obtenerListaProductoInventario(Integer idProducto){
        
        Producto producto = this.obtenerProductoPorId(idProducto);
        
        return producto.getListaProductoInventario();
    }
    
     @Override
    public double obtenerCostoProducto(Integer idProducto){
        Producto producto = this.obtenerProductoPorId(idProducto);
        
        return producto.getPrecio();
    }
      
    private Producto obtenerProductoPorId(Integer idProducto) {

        for (Producto producto : listaProductos) {
            if (producto.getId().equals(idProducto)) {
                return producto;
            }
        }

        return null;

    }

}
