package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoInicioDTO;
import java.util.LinkedList;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import java.util.Comparator;

/**
 * FAdministradorProductos.java
 *
 * Clase que implementa la interfaz IAdministradorProductos, proporcionando
 * la funcionalidad para gestionar el catálogo de productos disponibles en el sistema,
 * consultar inventario y permitir búsquedas de productos.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class FAdministradorProductos implements IAdministradorProductos {

    private List<Producto> listaProductos;

    /**
     * Constructor que inicializa un administrador de productos con la lista de productos.
     *
     * @param listaProductos Objeto List que contiene los productos registrados en el sistema.
     */
    public FAdministradorProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * Implementación del método cosultarInventarioProducto(), de la interfaz,
     * {@link IAdministradorProductos}, que permite obtener las existencias
     * de un Producto a partir de su ID.
     * @param idProducto Objeto Integer que representa el ID del Producto del que
     * se obtendrán sus existencias.
     * @return Dato int que representa la existencias actuales del Producto con
     * el ID del parámetro.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se comprueba 
     * que el ID del Producto es inválido, dentro de este subsistema.
     */
    @Override
    public int cosultarInventarioProducto(Integer idProducto) throws ProductosIdProductoInvalidoException{
        
        // Se valida el ID del producto.
        if(!validarProducto(idProducto)){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        Producto producto = obtenerProducto(idProducto);
        
        if(producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        // Se obtiene la cantidad de objetos de tipo ProductoInventario de la lista
        // del objeto Producto con el ID del parámetro que no están apartados.
        int disponibilidadProducto 
                = (int) producto.getListaProductoInventario().stream().filter(productoInventario -> !productoInventario.getApartado()).count();
 
        return disponibilidadProducto;
            
    }

    /**
     * Implementación del método apartarProductoInventario(), de la interfaz
     * {@link IAdministradorProductos}, que permite asignar al objeto de tipo
     * ProductoInventario dentro de la lista de tipo ProductoInventario del
     * Producto con el ID del parámetro con el menor valor en 
     * @param idProducto
     * @param cantidad
     * @throws ProductosIdProductoInvalidoException
     * @throws ProductosProductoSinInventarioException 
     */
    @Override
    public void apartarProductoInventario(Integer idProducto, int cantidad) 
            throws ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException{
        
        // Se valida el ID del producto.
        if(!validarProducto(idProducto)){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        Producto producto = obtenerProducto(idProducto);
    
        if(producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
            
        if(cosultarInventarioProducto(idProducto) - cantidad < 0){
            throw new ProductosProductoSinInventarioException("El producto con ID: " + idProducto + " no cuenta con inventario suficiente.");
        }

        List<ProductoInventario> productosInventarioProductoApartar = producto.getListaProductoInventario();
         
        productosInventarioProductoApartar.sort(
            Comparator.comparing((ProductoInventario productoOrdenar) -> productoOrdenar.getSucursal().getTiempoMatriz())
        );
        
        int cantidadProductosApartados = 0;
       
        for(ProductoInventario productoInventario: productosInventarioProductoApartar){
            if(!productoInventario.getApartado()){
                productoInventario.setApartado(true);
                cantidadProductosApartados++;
            }
            if(cantidadProductosApartados >= cantidad){
                break;
            }
        }

    }

    /**
     * Implementación del método desapartarProductoInventario(), de la interfaz
     * {@link IAdministradorProductos}, que permite desmarcar como apartadas las
     * unidades de un producto específico en el inventario.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a desapartar.
     * @param cantidad Valor int que representa la cantidad de unidades a desapartar.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws ProductosProductoSinInventarioException Se lanza si ocurre un error al
     * desapartar las unidades especificadas.
     */
    @Override
    public void desapartarProductoInventario(Integer idProducto, int cantidad) 
            throws ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException{
        
        // Se valida el ID del producto.
        if(!validarProducto(idProducto)){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        Producto producto = obtenerProducto(idProducto);
    
        if(producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }    

        List<ProductoInventario> productosInventarioProductoApartar = producto.getListaProductoInventario();
        
        productosInventarioProductoApartar.sort(
            Comparator.comparing((ProductoInventario productoOrdenar) -> productoOrdenar.getSucursal().getTiempoMatriz()).reversed()
        );
        
        int cantidadProductosDesapartados = 0;
        
        for(ProductoInventario productoInventario: productosInventarioProductoApartar){
            if(productoInventario.getApartado()){
                productoInventario.setApartado(false);
                cantidadProductosDesapartados++;
            }
            if(cantidadProductosDesapartados >= cantidad){
                break;
            }
        }  
        
    }

    /**
     * Implementación del método validarProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite verificar si el ID
     * de un producto corresponde a un objeto Producto real.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a validar.
     * @return true si existe un objeto Producto con el ID del parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProducto(Integer idProducto){
        
        for(Producto producto: listaProductos){
            if(producto.getId() == idProducto){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Implementación del método obtenerProductosVenta(), de la interfaz 
     * {@link IAdministradorProductos}, que permte obtener la información de los
     * productos en venta con existencias disponibles.
     * @return Objeto List {@literal <InformacionProductoInicioDTO\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosVenta() {

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de Productos y se añade la información a la lista
        // de DTOs, de aquellos que tengan existencias.
        for(Producto producto: listaProductos){
            
            Integer idProducto = producto.getId();
            int cantidadProducto = 0;
            
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}
                
            if(cantidadProducto > 0){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                   producto.getId(), 
                   producto.getNombre(), 
                   producto.getVariedad(),
                   producto.getPrecio(),
                   producto.getMilesSemillas(), 
                   producto.getDireccionImagenProducto(), 
                   producto.getProveedor().getId())

                );
            } 
                
            
 
        }
 

        return listaProductoInicioDTO;
    }
    
    
    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del 
     * nombre recibido como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del producto a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTO\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto){
        
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();
         
        // Se convierte el nombre recibido a letras minusculas.
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        
        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias y su nombre este contenido dentro del nombre del parámetro.
        for(Producto producto: listaProductos){
            
            int idProducto = producto.getId();
            
            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(nombreProductoMinusculas.contains(producto.getNombre().toLowerCase()) && cantidadProducto > 0){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                    producto.getId(), 
                    producto.getNombre(), 
                    producto.getVariedad(),
                    producto.getPrecio(),
                    producto.getMilesSemillas(), 
                    producto.getDireccionImagenProducto(), 
                    producto.getProveedor().getId())

                 );
            }
            
        }

        return listaProductoInicioDTO;
    }
    
    
    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del 
     * nombre recibido como parámetro y cuya variedad esté contenida dentro de la variedad
     * recibida como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del o los productos a buscar.
     * @param variedadProducto Objeto String que representa la variedad del o los productos a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTO\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto){
        
        // El nombre y la variedada del parámetro se conviertene a letras minúsculas.
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        String variedadProductoMinusculas = variedadProducto.toLowerCase();

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        
        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenido dentro del nombre del parámetro,
        // y cuya variedad esté contenida detro de la variedad del parámetro.
        for(Producto producto: listaProductos){
            
            int idProducto = producto.getId();
            
            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(cantidadProducto > 0 && nombreProductoMinusculas.contains(producto.getNombre().toLowerCase())
                    && variedadProductoMinusculas.contains(producto.getVariedad().toLowerCase())){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                    producto.getId(), 
                    producto.getNombre(), 
                    producto.getVariedad(),
                    producto.getPrecio(),
                    producto.getMilesSemillas(), 
                    producto.getDireccionImagenProducto(), 
                    producto.getProveedor().getId())

                 );
            }
            
        }
        
        return listaProductoInicioDTO;
    }
    
    
    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del 
     * nombre recibido como parámetro, cuya variedad esté dentro de la variedad recibida como parámetro y 
     * cuyo nombre de proveedor esté contenido dentro del nombre de proveedor recibido como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del o los productos a buscar.
     * @param variedadProducto Objeto String que representa la variedad del o los productos a buscar.
     * @param nombreProveedor Objeto String que representa el nombre del proveedor del o los productos a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTO\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto, String nombreProveedor){
        
        // El nombre, variedad y nombre de proveedor del producto son convertidos a cadenas 
        // de letras minúsculas.
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        String variedadProductoMinusculas = variedadProducto.toLowerCase();
        String nombreProveedorMinusculas = nombreProveedor.toLowerCase(); 

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        
        // Se recorre la lista de productos para almacenar los datos de aquellos que tengan existencias, 
        // cuyo su nombre este contenido dentro del nombre del parámetro, cuya variedad esté contenida detro
        // de la variedad del parámetro y cuyo nombre de proveedor esté contenido dentro del nombre de
        // proveedor del parámetro.
        for(Producto producto: listaProductos){
            
            int idProducto = producto.getId();
            
            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(cantidadProducto > 0 && nombreProductoMinusculas.contains(producto.getNombre().toLowerCase())
                    && variedadProductoMinusculas.contains(producto.getVariedad().toLowerCase()) 
                    && nombreProveedorMinusculas.contains(producto.getProveedor().getNombre().toLowerCase())){
                
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                    producto.getId(), 
                    producto.getNombre(), 
                    producto.getVariedad(),
                    producto.getPrecio(),
                    producto.getMilesSemillas(), 
                    producto.getDireccionImagenProducto(), 
                    producto.getProveedor().getId())

                 );
            }
 
        }

        return listaProductoInicioDTO;
            
    }
    
    
    /**
     * Implementación del método obtenerInformacionProducto(), de la interfaz 
     * {@link IAdministradorProductos}, que permite obtener la información de un 
     * Producto a partir de su ID.
     * @param idProducto Objeto Integer que representa el ID del producto del que
     * se obtendrá su información.
     * @return Objeto InformacionProductoVentaDTO que contiene los valores de los
     * atributos del objeto Producto buscado, null si no se encuentra el Producto.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se verifica que 
     * que ID del Producto que recibe como parámetro es inválido, dentro de 
     * este subsistema.
     */
    @Override 
    public InformacionProductoVentaDTO obtenerInformacionProductoVenta(Integer idProducto)
            throws ProductosIdProductoInvalidoException{

        // Se valida el ID del Producto.
        if(!validarIdProductoInventario(idProducto)){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        Producto productoRecuperado = obtenerProducto(idProducto);
        
        if(productoRecuperado == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        // Se crea una DTO de tipo InformacionProductoVentaDTO, con la información del Producto con el ID
        // del parámetro.
        InformacionProductoVentaDTO informacionProductoDTO = new InformacionProductoVentaDTO(
                                                                    productoRecuperado.getId(),
                                                                    productoRecuperado.getNombre(),
                                                                    productoRecuperado.getVariedad(),
                                                                    productoRecuperado.getDescripcion(),
                                                                    productoRecuperado.getPrecio(),
                                                                    productoRecuperado.getMilesSemillas(),
                                                                    productoRecuperado.getDireccionImagenProducto(),
                                                                    productoRecuperado.getProveedor().getId());
              
        return informacionProductoDTO;
        
    }
    
    
    /**
     * Implementación del método obtenerProducto(), de la interfaz 
     * {@link IAdministradorProductos}, que permite obtener un objeto de tipo
     * Producto a partir de su ID.
     * @param idProducto Objeto Integer que representa el ID del Producto buscado.
     * @return Objeto de tipo Producto cuyo ID es igual al ID del parámetro.
     */
    @Override
    public Producto obtenerProducto(Integer idProducto){
        Producto productoBuscar = null;
        
        for(Producto producto: listaProductos){
            if(producto.getId() == idProducto){
                productoBuscar = producto;
            }
        }
        
        return productoBuscar;
    }

    /**
     * Implementación del método validarIdProductoInventario(), de la interfaz
     * {@link IAdministradorProductos}, que permite verificar si el ID
     * de un producto en inventario corresponde a un objeto ProductoInventario
     * real.
     * @param idProductoInventario Objeto Integer que representa el ID del objeto
     * ProductoInventario buscado.
     * @return true si existe un objeto ProductoInventario con el ID del 
     * parámetro, false en caso contrario.
     */
    @Override
    public boolean validarIdProductoInventario(Integer idProductoInventario) {
        
        // Se recorre la lista de productos.
        for(Producto producto: listaProductos){
            // Se recorre la lista de productos en inventario de cada producto,
            // se verifica si su ID es igual al ID del parámetro.
            for(ProductoInventario productoInventario: producto.getListaProductoInventario()){
                if(productoInventario.getId() == idProductoInventario){
                    return true;
                }
            }
        }
        
        return false;
    }
}
