package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProductoDTO;
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
    
    public FAdministradorProductos(){
        this.administradorProductos = new AdministradorProductos();
    }
    
    /**
     * Implementación del método cosultarInventarioProducto(), de la interfaz,
     * {@link IAdministradorProductos}, que permite obtener las existencias
     * de un ProductoDTO a partir de su ID.
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del ProductoDTO del que
     * se obtendrán sus existencias.
     * @return Dato int que representa la existencias actuales del ProductoDTO con
     * el ID del parámetro.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se comprueba
     * que el ID del ProductoDTO es inválido, dentro de este subsistema.
     */
    @Override
    public int cosultarInventarioProducto(IdProductoDTO idProductoDTO) throws ProductosIdProductoInvalidoException {
        return administradorProductos.cosultarInventarioProducto(idProductoDTO);
    }

    /**
     * Implementación del método validarProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite verificar si el ID
     * de un producto corresponde a un objeto ProductoDTO real.
     *
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del producto a validar.
     * @return true si existe un objeto ProductoDTO con el ID del parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProducto(IdProductoDTO idProductoDTO) {
        return  administradorProductos.validarProducto(idProductoDTO);
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
        return  administradorProductos.obtenerProductosVenta();
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProducto(String nombreProducto) {
        return  administradorProductos.obtenerProductosBusquedaNombreProducto(nombreProducto);
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoVariedad(String nombreProducto, String variedadProducto) {
        return  administradorProductos.obtenerProductosBusquedaNombreProductoVariedad(nombreProducto, variedadProducto);
    }
    
    /**
     * Implementación del método obtenerProductosBusqueda(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener la información de los productos cuyo nombre esté contenido dentro del
     * nombre recibido como parámetro y cuyo nombre de proveedor esté contenido dentro del nombre de proveedor
     * recibido como parámetro.
     * @param nombreProducto Objeto String que representa el nombre del o los productos a buscar.
     * @param proveedorProducto Objeto String que representa el nombre del proveedor del o los productos a buscar.
     * @return Objeto List {@literal <InformacionProductoInicioDTO\>} que contiene
     * DTOs con la información de los productos a mostrar.
     */
    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoProveedor(
            String nombreProducto,
            String proveedorProducto) {
        
        return  administradorProductos.obtenerProductosBusquedaNombreProductoProveedor(nombreProducto, proveedorProducto);
    }

    /**
     * Implementación del método obtenerInformacionProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite obtener la información de un
     * ProductoDTO a partir de su ID.
     * @param idProductoDTO Objeto IdProductoDTO que representa el ID del producto del que
     * se obtendrá su información.
     * @return Objeto InformacionProductoDetalladaDTO que contiene los valores de los
 atributos del objeto ProductoDTO buscado, null si no se encuentra el Producto.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se verifica que
     * que ID del ProductoDTO que recibe como parámetro es inválido, dentro de
     * este subsistema.
     */
    @Override
    public InformacionProductoDetalladaDTO obtenerInformacionProductoVenta(IdProductoDTO idProductoDTO)
            throws ProductosIdProductoInvalidoException {
        return  administradorProductos.obtenerInformacionProductoVenta(idProductoDTO);
    }

    /**
     * Implementación del método obtenerProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite obtener un objeto de tipo
     * ProductoDTO a partir de su ID.
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del Producto buscado.
     * @return Objeto de tipo ProductoDTO cuyo ID es igual al ID del parámetro.
     */
    @Override
    public ProductoDTO obtenerProducto(IdProductoDTO idProductoDTO) {
        return  administradorProductos.obtenerProducto(idProductoDTO);
    }

    /**
     * Implementación del método validarIdProductoInventario(), de la interfaz
     * {@link IAdministradorProductos}, que permite verificar si el ID
     * de un producto en inventario corresponde a un objeto ProductoInventario
     * real.
     * @param idProductoInventarioDTO Objeto IdProductoInventarioDTO que contiene el ID del objeto
     * ProductoInventarioDTO buscado.
     * @return true si existe un objeto ProductoInventario con el ID del
     * parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProductoInventario(IdProductoInventarioDTO idProductoInventarioDTO) {
        return  administradorProductos.validarProductoInventario(idProductoInventarioDTO);
    }

}
