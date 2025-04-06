package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoInicioDTO;
import java.util.LinkedList;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventarioON;
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
    private final AdministradorProductos administrador;

    /**
     * Constructor que inicializa un administrador de productos con la lista de productos.
     *
     * @param listaProductos Objeto List que contiene los productos registrados en el sistema.
     */
    public FAdministradorProductos(List<ProductoON> listaProductos) {
        administrador = new AdministradorProductos(listaProductos);
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
    public int cosultarInventarioProducto(Integer idProducto) throws ProductosIdProductoInvalidoException {
        return administrador.cosultarInventarioProducto(idProducto);
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
    public void apartarProductoInventario(Integer idProducto, int cantidad) throws ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException {
        administrador.apartarProductoInventario(idProducto, cantidad);
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
    public void desapartarProductoInventario(Integer idProducto, int cantidad) throws ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException {
        administrador.desapartarProductoInventario(idProducto, cantidad);
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
    public boolean validarProducto(Integer idProducto) {
        return administrador.validarProducto(idProducto);
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
        return administrador.obtenerProductosVenta();
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto) {
        return administrador.obtenerProductosBusqueda(nombreProducto);
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto) {
        return administrador.obtenerProductosBusqueda(nombreProducto, variedadProducto);
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
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto, String nombreProveedor) {
        return administrador.obtenerProductosBusqueda(nombreProducto, variedadProducto, nombreProveedor);
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
    public InformacionProductoVentaDTO obtenerInformacionProductoVenta(Integer idProducto) throws ProductosIdProductoInvalidoException {
        return administrador.obtenerInformacionProductoVenta(idProducto);
    }

    /**
     * Implementación del método obtenerProducto(), de la interfaz
     * {@link IAdministradorProductos}, que permite obtener un objeto de tipo
     * Producto a partir de su ID.
     * @param idProducto Objeto Integer que representa el ID del Producto buscado.
     * @return Objeto de tipo Producto cuyo ID es igual al ID del parámetro.
     */
    @Override
    public ProductoON obtenerProducto(Integer idProducto) {
        return administrador.obtenerProducto(idProducto);
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
        return administrador.validarIdProductoInventario(idProductoInventario);
    }
}
