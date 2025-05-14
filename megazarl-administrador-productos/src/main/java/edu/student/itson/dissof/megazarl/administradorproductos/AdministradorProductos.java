package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.utils.CadenasTextoUtils;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


class AdministradorProductos implements IAdministradorProductos {
    
    @Override
    public int cosultarInventarioProducto(IdProductoDTO idProductoDTO) throws ProductosIdProductoInvalidoException{
        
        // Se valida el ID del producto.
        if (!validarProducto(idProductoDTO)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTO producto = obtenerProducto(idProductoDTO);

        if (producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }
        
        
        // Se obtiene la cantidad de objetos de tipo ProductoInventario de la lista
        // del objeto Producto con el ID del parámetro.
        int disponibilidadProducto = producto.getProductosInventario().size();
 
        return disponibilidadProducto;
    }

    
    
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosVenta() {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de Productos y se añade la información a la lista
        // de DTOs, de aquellos que tengan existencias.
        
        List<ProductoDTO> listaProductos = Producto.recuperarTodos();
        
        for (ProductoDTO producto: listaProductos) {
            
            Long idProducto = producto.getId();
            int cantidadProducto = 0;

            try {
                
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTO(idProducto));
                
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(cantidadProducto > 0){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getProveedor().getId())

                );
            }
        }
        
        // Se ordena la lista de DTO, alfabéticamente
        listaProductoInicioDTO.sort(Comparator.comparing(InformacionProductoInicioDTO::getNombreProducto));
        
        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProducto(String nombreProducto){

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se convierte el nombre recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProducto.toLowerCase().replaceAll("\\s", "");

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias y su nombre este contenido dentro del nombre del parámetro.
        for(ProductoDTO producto: Producto.recuperarTodos()){
            
            Long idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTO(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {}
            
            String nombreProductoActualMinusculasSinEspacios = producto.getNombre().toLowerCase().replaceAll("\\s", "");

            boolean existeCoincidenciaNombreProducto = 
                    CadenasTextoUtils.verificarExisteSubcadenaComun(nombreProductoMinusculasSinEspacios, nombreProductoActualMinusculasSinEspacios);
                    
            if(cantidadProducto > 0 && existeCoincidenciaNombreProducto){
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getProveedor().getId())
                );
            }
        }

        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoVariedad(
            String nombreProducto, 
            String variedadProducto){
        
        // Se convierte el nombre de producto recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProducto.toLowerCase().replaceAll("\\s", "");

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenido dentro del nombre del parámetro,
        // y cuya variedad esté contenida detro de la variedad del parámetro.
        for(ProductoDTO producto: Producto.recuperarTodos()){
            Long idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTO(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {}

            String nombreProductoActualMinusculasSinEspacios = producto.getNombre().toLowerCase().replaceAll("\\s", "");
            
            if(cantidadProducto > 0 
                    && (nombreProductoActualMinusculasSinEspacios.contains(nombreProductoMinusculasSinEspacios)
                    || nombreProductoMinusculasSinEspacios.contains(nombreProductoActualMinusculasSinEspacios)
                    && producto.getVariedad().equals(variedadProducto))){
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getProveedor().getId())
                );
            }
        }

        return listaProductoInicioDTO;
    }
    
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoProveedor(
            String nombreProducto, 
            String proveedorProducto){

        // Se convierte el nombre de producto recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProducto.toLowerCase().replaceAll("\\s", "");

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenga al nombre del parámetro,
        // y cuyo nombre de proveedor esté contenido detro del nombre de proveedor del parámetro del parámetro.
        for(ProductoDTO producto: Producto.recuperarTodos()){
            Long idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTO(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {}

            String nombreProductoActualMinusculasSinEspacios = producto.getNombre().toLowerCase().replaceAll("\\s", "");
            
            if(cantidadProducto > 0 
                    && (nombreProductoActualMinusculasSinEspacios.contains(nombreProductoMinusculasSinEspacios)
                    || nombreProductoMinusculasSinEspacios.contains(nombreProductoActualMinusculasSinEspacios)
                    && producto.getProveedor().getNombre().equals(proveedorProducto))){
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getProveedor().getId())
                );
            }
        }

        return listaProductoInicioDTO;
    }
    

    @Override
    public InformacionProductoDetalladaDTO obtenerInformacionProductoVenta(IdProductoDTO idProductoDTO)
            throws ProductosIdProductoInvalidoException{
        
        // Se valida el ID del Producto.
        if (!validarProducto(idProductoDTO)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTO productoRecuperado = obtenerProducto(idProductoDTO);

        if (productoRecuperado == null) {
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }

        // Se crea una DTO de tipo InformacionProductoDetalladaDTO, con la información del Producto con el ID
        // del parámetro.
        InformacionProductoDetalladaDTO informacionProductoDTO = new InformacionProductoDetalladaDTO(
                productoRecuperado.getId(),
                productoRecuperado.getNombre(),
                productoRecuperado.getVariedad(),
                productoRecuperado.getDescripcion(),
                productoRecuperado.getPrecio(),
                productoRecuperado.getMilesSemillas(),
                productoRecuperado.getDireccionImagen(),
                productoRecuperado.getProveedor().getId());

        return informacionProductoDTO;

    }

    @Override
    public ProductoDTO obtenerProducto(IdProductoDTO idProductoDTO){
        
        return Producto.recuperarPorId(idProductoDTO);
    }

    @Override
    public boolean validarProductoInventario(IdProductoInventarioDTO idProductoInventarioDTO) {
        
        return ProductoInventario.existePorId(idProductoInventarioDTO);
        
    }
    
    @Override
    public boolean validarProducto(IdProductoDTO idProductoDTO){
        
        if (idProductoDTO == null || idProductoDTO.getIdProducto() == null || !Producto.existePorId(idProductoDTO)) {
            return false;
        }
        
        return true;
        
    }
    
}
