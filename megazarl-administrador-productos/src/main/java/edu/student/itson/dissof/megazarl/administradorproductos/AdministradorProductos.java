package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.utils.CadenasTextoUtils;
import edu.student.itson.dissof.megazarl.administradorproductos.utils.Normalizador;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;


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
        List<Long> idsProductosInventario = producto.getIdsProductosInventario();
        
        int disponibilidadProducto = 0;

        for(Long idProductoInventario: idsProductosInventario){
            
            IdProductoInventarioDTO idProductoInventarioDTO = new IdProductoInventarioDTO(idProductoInventario);
             
            if(!validarProductoInventario(idProductoInventarioDTO)){
                throw new ProductosIdProductoInvalidoException("El ID de producto en inventario es inválido.");
            }
            
            ProductoInventarioDTO productoInventario = obtenerProductoInventario(idProductoInventarioDTO);
            
            if(productoInventario == null){
                throw new ProductosIdProductoInvalidoException("El ID de producto en inventario es inválido."); 
            }
            
            if(!productoInventario.getApartado()){
                disponibilidadProducto++;
            }
            
        }
 
        return disponibilidadProducto;
    }
    private static final Logger LOG = Logger.getLogger(AdministradorProductos.class.getName());


    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosVenta() {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de Productos y se añade la información a la lista
        // de DTOs, de aquellos que tengan existencias.
        
        List<ProductoDTO> listaProductos = Producto.recuperarTodos();
        
        for (ProductoDTO producto: listaProductos) {
            

            listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
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
        listaProductoInicioDTO.sort(Comparator.comparing(InformacionProductoInicioDTO::getNombreProducto));
        
        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProducto(String nombreProducto){

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se eliminan los caracteres con acentos del nombre del producto buscado.
        String nombreProductoSinAcentos = Normalizador.quitarAcentosCadenaTexto(nombreProducto);
        
        // Se convierte el nombre recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProductoSinAcentos.toLowerCase().replaceAll("\\s", "");

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias y su nombre este contenido dentro del nombre del parámetro.
        for(ProductoDTO producto: Producto.recuperarTodos()){
            
            Long idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTO(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {}
            
            String nombreProductoActualSinAcentos = Normalizador.quitarAcentosCadenaTexto(producto.getNombre());
                        
            String nombreProductoActualMinusculasSinEspacios = nombreProductoActualSinAcentos.toLowerCase().replaceAll("\\s", "");
            
            boolean existeCoincidenciaNombreProducto = 
                    CadenasTextoUtils.verificarExisteSubcadenaComun(
                            nombreProductoMinusculasSinEspacios, 
                            nombreProductoActualMinusculasSinEspacios);
                    
                        
            if(cantidadProducto > 0 && existeCoincidenciaNombreProducto){
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoVariedad(
            String nombreProducto, 
            String variedadProducto){
        
        // Se eliminan los acentos del nombre del producto a buscar.
        String nombreProductoSinAcentos = Normalizador.quitarAcentosCadenaTexto(nombreProducto);
        
        // Se convierte el nombre de producto recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProductoSinAcentos.toLowerCase().replaceAll("\\s", "");

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

            String nombreProductoActualSinAcentos = Normalizador.quitarAcentosCadenaTexto(producto.getNombre());
            
            String nombreProductoActualMinusculasSinEspacios = nombreProductoActualSinAcentos.toLowerCase().replaceAll("\\s", "");
            
            if(cantidadProducto > 0 
                    && ((nombreProductoActualMinusculasSinEspacios.contains(nombreProductoMinusculasSinEspacios)
                    || nombreProductoMinusculasSinEspacios.contains(nombreProductoActualMinusculasSinEspacios))
                    && producto.getVariedad().equals(variedadProducto))){
                
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoProveedor(
            String nombreProducto, 
            String proveedorProducto) 
            throws ProductosIdProveedorInvalidoException{

        String nombreProductoSinAcentos = Normalizador.quitarAcentosCadenaTexto(nombreProducto);
        
        // Se convierte el nombre de producto recibido a letras minusculas y se eliminan los espacios.
        String nombreProductoMinusculasSinEspacios = nombreProductoSinAcentos.toLowerCase().replaceAll("\\s", "");

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenga al nombre del parámetro,
        // y cuyo nombre de proveedor esté contenido detro del nombre de proveedor del parámetro del parámetro.
        List<ProductoDTO> listaProductos = Producto.recuperarTodos();
        for(ProductoDTO producto: listaProductos){
            Long idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(new IdProductoDTO(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {}

            String nombreProductoActualSinAcentos = Normalizador.quitarAcentosCadenaTexto(producto.getNombre());
            
            String nombreProductoActualMinusculasSinEspacios = nombreProductoActualSinAcentos.toLowerCase().replaceAll("\\s", "");
            
            IdProveedorDTO idProveedorDTO = new IdProveedorDTO(producto.getIdProveedor());
            
            if(!administradorProveedores.validarProveedor(idProveedorDTO)){
                throw new ProductosIdProveedorInvalidoException("El ID de proveedor es inválido");
            }
            
            ProveedorDTO proveedorProductoActual = administradorProveedores.obtenerProveedor(idProveedorDTO);
            
            
            if(cantidadProducto > 0 
                    && ((nombreProductoActualMinusculasSinEspacios.contains(nombreProductoMinusculasSinEspacios)
                    || nombreProductoMinusculasSinEspacios.contains(nombreProductoActualMinusculasSinEspacios))
                    && proveedorProductoActual.getNombre().equals(proveedorProducto))){
                
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
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
                productoRecuperado.getIdProveedor());

        return informacionProductoDTO;

    }

    @Override
    public ProductoDTO obtenerProducto(IdProductoDTO idProductoDTO){
        
        return Producto.recuperarPorId(idProductoDTO);
    }
    
    @Override
    public ProductoInventarioDTO obtenerProductoInventario(IdProductoInventarioDTO idProductoInventario) {
        
        return ProductoInventario.recuperarPorId(idProductoInventario);
    }
    
    @Override
    public void apartarProductoInventarioPedido(IdProductoInventarioDTO idProductoInventarioDTO) 
            throws ProductosIdProductoInventarioInvalidoException {
        
        if(!validarProductoInventario(idProductoInventarioDTO)){
            throw new ProductosIdProductoInventarioInvalidoException("El ID del producto en inventario es inválido.");
        }
        
        ActualizacionProductoInventarioDTO actualizacionProductoInventarioDTO = 
                            new ActualizacionProductoInventarioDTO(idProductoInventarioDTO.getIdProductoInventario());
                    
        actualizacionProductoInventarioDTO.setApartado(true);

        ProductoInventario.actualizar(actualizacionProductoInventarioDTO);

    }
    
    
    @Override
    public boolean validarProducto(IdProductoDTO idProductoDTO){
        
        if (idProductoDTO == null || idProductoDTO.getIdProducto() == null || !Producto.existePorId(idProductoDTO)) {
            return false;
        }
        
        return true;
        
    }
    
    @Override
    public boolean validarProductoInventario(IdProductoInventarioDTO idProductoInventarioDTO) {
        
        return ProductoInventario.existePorId(idProductoInventarioDTO);
        
    }

    
}
