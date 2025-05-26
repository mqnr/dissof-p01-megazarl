package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorproductos.utils.CadenasTextoUtils;
import edu.student.itson.dissof.megazarl.administradorproductos.utils.Normalizador;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class AdministradorProductos implements IAdministradorProductos {
    
    private final IAdministradorProveedores administradorProveedores;
    
    public AdministradorProductos(IAdministradorProveedores administradorProveedores){
        this.administradorProveedores = administradorProveedores;
    }
    
    @Override
    public int cosultarInventarioProducto(IdProductoDTONegocios idProductoDTO) 
            throws ProductosIdProductoInvalidoException,
            ProductosPersistenciaException{
        
        
        // Se valida el ID del producto.
        if (!validarProducto(idProductoDTO)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTONegocios producto;
        producto = obtenerProducto(idProductoDTO);

        if (producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }
        
        
        // Se obtiene la cantidad de objetos de tipo ProductoInventario de la lista
        // del objeto Producto con el ID del parámetro.
        List<ProductoInventarioDTONegocios> productosInventario;
        try {
            productosInventario = ProductoInventario.recuperarPorIdProducto(idProductoDTO);
        } catch (ParametroNuloNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        } catch (FormatoIdInvalidoNegocioException ex) {
             throw new ProductosPersistenciaException(ex.getMessage());
        }
        
        int disponibilidadProducto = 0;

        for(ProductoInventarioDTONegocios productoInventario: productosInventario){
            
            if(!productoInventario.getApartado()){
                disponibilidadProducto++;
            }
            
        }
 
        return disponibilidadProducto;
    }

    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosVenta() {
        List<InformacionProductoInicioDTONegocios> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de Productos y se añade la información a la lista
        // de DTOs, de aquellos que tengan existencias.
        
        List<ProductoDTONegocios> listaProductos = Producto.recuperarTodos();
        
        for (ProductoDTONegocios producto: listaProductos) {
            

            listaProductoInicioDTO.add(new InformacionProductoInicioDTONegocios(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getVariedad(),
                    producto.getPrecio(),
                    producto.getMilesSemillas(),
                    producto.getDireccionImagen(),
                    producto.getIdProveedor())

            );

        }
        
        // Se ordena la lista de DTO, alfabéticamente
        listaProductoInicioDTO.sort(Comparator.comparing(InformacionProductoInicioDTONegocios::getNombreProducto));
        
        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosBusquedaNombreProducto(String nombreProducto) 
            throws ProductosPersistenciaException{

        List<InformacionProductoInicioDTONegocios> listaProductoInicioDTO = new LinkedList<>();

        // Se eliminan los caracteres con acentos del nombre del producto buscado.
        String nombreProductoSinAcentos = Normalizador.quitarAcentosCadenaTexto(nombreProducto);
        
        // Se convierte el nombre recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProductoSinAcentos.toLowerCase().replaceAll("\\s", "");

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias y su nombre este contenido dentro del nombre del parámetro.
        for(ProductoDTONegocios producto: Producto.recuperarTodos()){
            
            IdEntidadGenericoNegocios idProducto = producto.getId();

            int cantidadProducto = 0;

            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new ProductosPersistenciaException(ex.getMessage());
            }

                
            String nombreProductoActualSinAcentos = Normalizador.quitarAcentosCadenaTexto(producto.getNombre());
                        
            String nombreProductoActualMinusculasSinEspacios = nombreProductoActualSinAcentos.toLowerCase().replaceAll("\\s", "");
            
            boolean existeCoincidenciaNombreProducto = 
                    CadenasTextoUtils.verificarExisteSubcadenaComun(
                            nombreProductoMinusculasSinEspacios, 
                            nombreProductoActualMinusculasSinEspacios);
                    
                        
            if(cantidadProducto > 0 && existeCoincidenciaNombreProducto){
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTONegocios(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getIdProveedor())
                );
            }
        }

        return listaProductoInicioDTO;
    }
    
    
    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosBusquedaNombreProductoVariedad(
            String nombreProducto, 
            String variedadProducto) 
            
            throws ProductosPersistenciaException{
        
        // Se eliminan los acentos del nombre del producto a buscar.
        String nombreProductoSinAcentos = Normalizador.quitarAcentosCadenaTexto(nombreProducto);
        
        // Se convierte el nombre de producto recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProductoSinAcentos.toLowerCase().replaceAll("\\s", "");

        List<InformacionProductoInicioDTONegocios> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenido dentro del nombre del parámetro,
        // y cuya variedad esté contenida detro de la variedad del parámetro.
        for(ProductoDTONegocios producto: Producto.recuperarTodos()){
            IdEntidadGenericoNegocios idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new ProductosPersistenciaException(ex.getMessage());
            }

            String nombreProductoActualSinAcentos = Normalizador.quitarAcentosCadenaTexto(producto.getNombre());
            
            String nombreProductoActualMinusculasSinEspacios = nombreProductoActualSinAcentos.toLowerCase().replaceAll("\\s", "");
            
            if(cantidadProducto > 0 
                    && ((nombreProductoActualMinusculasSinEspacios.contains(nombreProductoMinusculasSinEspacios)
                    || nombreProductoMinusculasSinEspacios.contains(nombreProductoActualMinusculasSinEspacios))
                    && producto.getVariedad().equals(variedadProducto))){
                
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTONegocios(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getIdProveedor())
                );
            }
        }
        
        

        return listaProductoInicioDTO;
    }
    
    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosBusquedaNombreProductoProveedor(
            String nombreProducto, 
            String proveedorProducto) 
            
            throws ProductosIdProveedorInvalidoException,
            ProductosPersistenciaException{

        String nombreProductoSinAcentos = Normalizador.quitarAcentosCadenaTexto(nombreProducto);
        
        // Se convierte el nombre de producto recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProductoSinAcentos.toLowerCase().replaceAll("\\s", "");

        List<InformacionProductoInicioDTONegocios> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenga al nombre del parámetro,
        // y cuyo nombre de proveedor esté contenido detro del nombre de proveedor del parámetro del parámetro.
        List<ProductoDTONegocios> listaProductos = Producto.recuperarTodos();
        for(ProductoDTONegocios producto: listaProductos){
            IdEntidadGenericoNegocios idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new ProductosPersistenciaException(ex.getMessage());
            }

            String nombreProductoActualSinAcentos = Normalizador.quitarAcentosCadenaTexto(producto.getNombre());
            
            String nombreProductoActualMinusculasSinEspacios = nombreProductoActualSinAcentos.toLowerCase().replaceAll("\\s", "");
            
            IdProveedorDTONegocios idProveedorDTO = new IdProveedorDTONegocios(producto.getIdProveedor());
            
            try {
                if(!administradorProveedores.validarProveedor(idProveedorDTO)){
                    throw new ProductosIdProveedorInvalidoException("El ID de proveedor es inválido");
                }
            } catch (ProveedoresPersistenciaException ex) {
                throw new ProductosPersistenciaException(ex.getMessage());
            }

            
            ProveedorDTONegocios proveedorProductoActual;
            try {
                proveedorProductoActual = administradorProveedores.obtenerProveedor(idProveedorDTO);
            } catch (ProveedoresPersistenciaException ex) {
                throw new ProductosPersistenciaException(ex.getMessage());
            }
            
            
            if(cantidadProducto > 0 
                    && ((nombreProductoActualMinusculasSinEspacios.contains(nombreProductoMinusculasSinEspacios)
                    || nombreProductoMinusculasSinEspacios.contains(nombreProductoActualMinusculasSinEspacios))
                    && proveedorProductoActual.getNombre().equals(proveedorProducto))){
                
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTONegocios(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagen(),
                        producto.getIdProveedor())
                );
            }
        }

        return listaProductoInicioDTO;
    }
    

    @Override
    public InformacionProductoDetalladaDTONegocios obtenerInformacionProductoVenta(IdProductoDTONegocios idProductoDTO)
            throws ProductosIdProductoInvalidoException,
            ProductosPersistenciaException{
        
        // Se valida el ID del Producto.
        if (!validarProducto(idProductoDTO)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTONegocios productoRecuperado;
        productoRecuperado = obtenerProducto(idProductoDTO);

        if (productoRecuperado == null) {
            throw new ProductosIdProductoInvalidoException("El ID de producto es inválido.");
        }

        // Se crea una DTO de tipo InformacionProductoDetalladaDTONegocios, con la información del Producto con el ID
        // del parámetro.
        InformacionProductoDetalladaDTONegocios informacionProductoDTO = new InformacionProductoDetalladaDTONegocios(
                productoRecuperado.getId(),
                productoRecuperado.getNombre(),
                productoRecuperado.getVariedad(),
                productoRecuperado.getDescripcion(),
                productoRecuperado.getPrecio(),
                productoRecuperado.getMilesSemillas(),
                productoRecuperado.getDireccionImagen(),
                productoRecuperado.getIdProveedor());
        
        return informacionProductoDTO;

    }

    

    @Override
    public ProductoDTONegocios obtenerProducto(IdProductoDTONegocios idProductoDTO) 
            throws ProductosPersistenciaException{
        
        try {
            return Producto.recuperarPorId(idProductoDTO);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ParametroNuloNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        }
    }
    
    @Override
    public ProductoInventarioDTONegocios obtenerProductoInventario(IdProductoInventarioDTONegocios idProductoInventario)
            throws ProductosPersistenciaException{
        
        try {
            return ProductoInventario.recuperarPorId(idProductoInventario);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ParametroNuloNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        }
    }
    
    @Override
    public void apartarProductoInventarioPedido(IdProductoInventarioDTONegocios idProductoInventarioDTO) 
            throws ProductosIdProductoInventarioInvalidoException,
            ProductosPersistenciaException{
        
        if(!validarProductoInventario(idProductoInventarioDTO)){
            throw new ProductosIdProductoInventarioInvalidoException("El ID del producto en inventario es inválido.");
        }
        
        ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTO = 
                            new ActualizacionProductoInventarioDTONegocios(idProductoInventarioDTO.getIdProductoInventario());
                    
        actualizacionProductoInventarioDTO.setApartado(true);

        try {
            ProductoInventario.actualizar(actualizacionProductoInventarioDTO);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ParametroNuloNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        }

    }
    
    
    @Override
    public boolean validarProducto(IdProductoDTONegocios idProductoDTO)
            throws ProductosPersistenciaException{
        
        try {
            if (idProductoDTO == null || idProductoDTO.getIdProducto() == null || !Producto.existePorId(idProductoDTO)) {
                return false;
            }
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        }
        
        return true;
        
    }
    
    @Override
    public boolean validarProductoInventario(IdProductoInventarioDTONegocios idProductoInventarioDTO) 
            throws ProductosPersistenciaException{
        
        try {
            return ProductoInventario.existePorId(idProductoInventarioDTO);
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        }
        
    }

    @Override
    public List<ProductoInventarioDTONegocios> obtenerProductosInventarioIdProducto(IdProductoDTONegocios idProductoDTONegocios)
            throws ProductosIdProductoInvalidoException,
            ProductosPersistenciaException{
        
        if(!validarProducto(idProductoDTONegocios)){
            throw new ProductosIdProductoInvalidoException("El ID del producto recibido es inválido.");
        }
        
        try {
            return ProductoInventario.recuperarPorIdProducto(idProductoDTONegocios);
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new ProductosPersistenciaException(ex.getMessage());
        }
    }

    
}
