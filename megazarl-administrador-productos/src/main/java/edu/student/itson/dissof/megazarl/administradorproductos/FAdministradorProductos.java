package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosPersistenciaException;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTONegocios;
import java.util.List;

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


    private final AdministradorProductos administradorProductos;
    
    public FAdministradorProductos(IAdministradorProveedores administradorProveedores){
        this.administradorProductos = new AdministradorProductos(administradorProveedores);
    }
    
    /**
     * Implementación del método cosultarInventarioProducto(), de la interfaz,
     * {@link IAdministradorProductos}, que permite obtener las existencias
 de un ProductoDTONegocios a partir de su ID.
     * @param idProductoDTO Objeto IdProductoDTONegocios que contiene el ID del ProductoDTONegocios del que
 se obtendrán sus existencias.
     * @return Dato int que representa la existencias actuales del ProductoDTONegocios con
 el ID del parámetro.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se comprueba
 que el ID del ProductoDTONegocios es inválido, dentro de este subsistema.
     */
    @Override
    public int cosultarInventarioProducto(IdProductoDTONegocios idProductoDTO) 
            throws ProductosIdProductoInvalidoException, 
            ProductosPersistenciaException {
        
        return administradorProductos.cosultarInventarioProducto(idProductoDTO);
    }

    /**
     * Implementación del método validarProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite verificar si el ID
 de un producto corresponde a un objeto ProductoDTONegocios real.
     *
     * @param idProductoDTO Objeto IdProductoDTONegocios que contiene el ID del producto a validar.
     * @return true si existe un objeto ProductoDTONegocios con el ID del parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProducto(IdProductoDTONegocios idProductoDTO) 
            throws ProductosPersistenciaException {
        
        return  administradorProductos.validarProducto(idProductoDTO);
    }

    /**
     * Implementación del método obtenerProductosVenta(), de la interfaz
     * {@link IAdministradorProductos}, que permte obtener la información de los
     * productos en venta con existencias disponibles.
     * @return Objeto List {@literal <InformacionProductoInicioDTONegocios\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosVenta() {
        return  administradorProductos.obtenerProductosVenta();
    }

    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del
     * nombre recibido como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del producto a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTONegocios\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosBusquedaNombreProducto(String nombreProducto) 
            throws ProductosPersistenciaException {
        return  administradorProductos.obtenerProductosBusquedaNombreProducto(nombreProducto);
    }

    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del
     * nombre recibido como parámetro y cuya variedad esté contenida dentro de la variedad
     * recibida como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del o los productos a buscar.
     * @param variedadProducto Objeto String que representa la variedad del o los productos a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTONegocios\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosBusquedaNombreProductoVariedad(String nombreProducto, String variedadProducto) 
            throws ProductosPersistenciaException {
        
        return  administradorProductos.obtenerProductosBusquedaNombreProductoVariedad(nombreProducto, variedadProducto);
    }
    
    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del
     * nombre recibido como parámetro y cuyo nombre de proveedor esté contenido dentro del nombre de proveedor
     * recibido como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del o los productos a buscar.
     * @param proveedorProducto Objeto String que representa el nombre del proveedor del o los productos a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTONegocios\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTONegocios> obtenerProductosBusquedaNombreProductoProveedor(
            String nombreProducto,
            String proveedorProducto) 
            
            throws ProductosIdProveedorInvalidoException, 
            ProductosPersistenciaException {
        
        return  administradorProductos.obtenerProductosBusquedaNombreProductoProveedor(nombreProducto, proveedorProducto);
    }

    /**
     * Implementación del método obtenerInformacionProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite obtener la información de un
 ProductoDTONegocios a partir de su ID.
     * @param idProductoDTO Objeto IdProductoDTONegocios que representa el ID del producto del que
 se obtendrá su información.
     * @return Objeto InformacionProductoDetalladaDTONegocios que contiene los valores de los
 atributos del objeto ProductoDTONegocios buscado, null si no se encuentra el Producto.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se verifica que
 que ID del ProductoDTONegocios que recibe como parámetro es inválido, dentro de
 este subsistema.
     */
    @Override
    public InformacionProductoDetalladaDTONegocios obtenerInformacionProductoVenta(IdProductoDTONegocios idProductoDTO)
            throws ProductosIdProductoInvalidoException, 
            ProductosPersistenciaException {
        
        return  administradorProductos.obtenerInformacionProductoVenta(idProductoDTO);
    }

    /**
     * Implementación del método obtenerProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite obtener un objeto de tipo
 ProductoDTONegocios a partir de su ID.
     * @param idProductoDTO Objeto IdProductoDTONegocios que contiene el ID del Producto buscado.
     * @return Objeto de tipo ProductoDTONegocios cuyo ID es igual al ID del parámetro.
     */
    @Override
    public ProductoDTONegocios obtenerProducto(IdProductoDTONegocios idProductoDTO) 
            throws ProductosPersistenciaException {
        
        return administradorProductos.obtenerProducto(idProductoDTO);
    }

    @Override
    public void apartarProductoInventarioPedido(IdProductoInventarioDTONegocios idProductoInventarioDTO) 
            throws ProductosIdProductoInventarioInvalidoException, 
            ProductosPersistenciaException {
        
        administradorProductos.apartarProductoInventarioPedido(idProductoInventarioDTO);
    }
    
    /**
     * Implementación del método validarIdProductoInventario(), de la interfaz
     * {@link IAdministradorProductos}, que permite verificar si el ID
     * de un producto en inventario corresponde a un objeto ProductoInventario
     * real.
     * @param idProductoInventarioDTO Objeto IdProductoInventarioDTONegocios que contiene el ID del objeto
 ProductoInventarioDTONegocios buscado.
     * @return true si existe un objeto ProductoInventario con el ID del
     * parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProductoInventario(IdProductoInventarioDTONegocios idProductoInventarioDTO) throws ProductosPersistenciaException {
        
        return  administradorProductos.validarProductoInventario(idProductoInventarioDTO);
    }

    @Override
    public ProductoInventarioDTONegocios obtenerProductoInventario(IdProductoInventarioDTONegocios idProductoInventario) 
            throws ProductosPersistenciaException {
        return administradorProductos.obtenerProductoInventario(idProductoInventario);
    }

    @Override
    public List<ProductoInventarioDTONegocios> obtenerProductosInventarioIdProducto(IdProductoDTONegocios idProductoDTONegocios) 
            throws ProductosIdProductoInvalidoException, 
            ProductosPersistenciaException {
        
            return administradorProductos.obtenerProductosInventarioIdProducto(idProductoDTONegocios);
    }

    

}
