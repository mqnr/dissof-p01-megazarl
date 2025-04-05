package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;
import java.util.List;

/**
 * IAdministradorProductos.java
 *
 * Interfaz del subsistema AdministradorProductos, que se encarga de gestionar
 * el catálogo de productos disponibles en el sistema, permitiendo consultar
 * información detallada, verificar inventario y realizar búsquedas.
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
public interface IAdministradorProductos {
    /**
     * Método que permite verificar si el ID de un producto es válido.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a validar.
     * @return true si el ID del producto es válido, false en caso contrario.
     */
    public abstract boolean validarProducto(Integer idProducto);

    /**
     * Método que permite consultar el inventario disponible de un producto
     * específico identificado por su ID.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a consultar.
     * @return Valor int que representa la cantidad disponible en inventario.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     */
    public abstract int cosultarInventarioProducto(Integer idProducto)  throws ProductosIdProductoInvalidoException;

    /**
     * Método que permite obtener la lista de todos los productos disponibles
     * para venta en el sistema.
     *
     * @return Objeto List de InformacionProductoInicioDTO que contiene la información
     * resumida de los productos disponibles.
     */
    public abstract List<InformacionProductoInicioDTO> obtenerProductosVenta();

    /**
     * Método que permite obtener la información detallada de un producto específico
     * identificado por su ID.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a consultar.
     * @return Objeto InformacionProductoVentaDTO que contiene la información detallada
     * del producto.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     */
    public abstract InformacionProductoVentaDTO obtenerInformacionProductoVenta(Integer idProducto) throws ProductosIdProductoInvalidoException;

    /**
     * Método que permite buscar productos por nombre.
     *
     * @param nombreProducto Objeto String que representa el nombre o parte del nombre
     * del producto a buscar.
     * @return Objeto List de InformacionProductoInicioDTO que contiene la información
     * resumida de los productos que coinciden con el criterio de búsqueda.
     */
    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto);

    /**
     * Método que permite buscar productos por nombre y variedad.
     *
     * @param nombreProducto Objeto String que representa el nombre o parte del nombre
     * del producto a buscar.
     * @param variedadProducto Objeto String que representa la variedad o parte de la
     * variedad del producto a buscar.
     * @return Objeto List de InformacionProductoInicioDTO que contiene la información
     * resumida de los productos que coinciden con los criterios de búsqueda.
     */
    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto);

    /**
     * Método que permite buscar productos por nombre, variedad y proveedor.
     *
     * @param nombreProducto Objeto String que representa el nombre o parte del nombre
     * del producto a buscar.
     * @param variedadProducto Objeto String que representa la variedad o parte de la
     * variedad del producto a buscar.
     * @param nombreProveedor Objeto String que representa el nombre o parte del nombre
     * del proveedor del producto a buscar.
     * @return Objeto List de InformacionProductoInicioDTO que contiene la información
     * resumida de los productos que coinciden con los criterios de búsqueda.
     */
    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusqueda(
            String nombreProducto,
            String variedadProducto,
            String nombreProveedor);

    /**
     * Método que permite obtener un producto específico identificado por su ID.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a obtener.
     * @return Objeto Producto que representa el producto con el ID especificado.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     */
    public abstract ProductoON obtenerProducto(Integer idProducto) throws ProductosIdProductoInvalidoException;

    /**
     * Método que permite apartar unidades de un producto específico en el inventario.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a apartar.
     * @param cantidad Valor int que representa la cantidad de unidades a apartar.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws ProductosProductoSinInventarioException Se lanza si se comprueba que no
     * hay suficiente inventario disponible para apartar.
     */
    public abstract void apartarProductoInventario(Integer idProducto, int cantidad) 
            throws ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;

    /**
     * Método que permite desapartar unidades de un producto específico en el inventario.
     *
     * @param idProducto Objeto Integer que representa el ID del producto a desapartar.
     * @param cantidad Valor int que representa la cantidad de unidades a desapartar.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws ProductosProductoSinInventarioException Se lanza si ocurre un error al
     * desapartar las unidades especificadas.
     */
    public abstract void desapartarProductoInventario(Integer idProducto, int cantidad) 
            throws ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;

    /**
     * Método que permite verificar si el ID de un producto en inventario es válido.
     *
     * @param idProductoInventario Objeto Integer que representa el ID del producto en
     * inventario a validar.
     * @return true si el ID del producto en inventario es válido, false en caso contrario.
     */
    public abstract boolean validarIdProductoInventario(Integer idProductoInventario);
}
