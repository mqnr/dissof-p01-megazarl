package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTO;
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
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del producto a validar.
     * @return true si el ID del producto es válido, false en caso contrario.
     */
    public abstract boolean validarProducto(IdProductoDTO idProductoDTO);

    /**
     * Método que permite consultar el inventario disponible de un producto
     * específico identificado por su ID.
     *
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del producto a consultar.
     * @return Valor int que representa la cantidad disponible en inventario.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     */
    public abstract int cosultarInventarioProducto(IdProductoDTO idProductoDTO) throws ProductosIdProductoInvalidoException;

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
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del producto a consultar.
     * @return Objeto InformacionProductoDetalladaDTO que contiene la información detallada
 del producto.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     */
    public abstract InformacionProductoDetalladaDTO obtenerInformacionProductoVenta(IdProductoDTO idProductoDTO) throws ProductosIdProductoInvalidoException;

    /**
     * Método que permite buscar productos por nombre.
     *
     * @param nombreProducto Objeto String que representa el nombre o parte del nombre
     * del producto a buscar.
     * @return Objeto List de InformacionProductoInicioDTO que contiene la información
     * resumida de los productos que coinciden con el criterio de búsqueda.
     */
    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProducto(String nombreProducto);

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
    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoVariedad(
            String nombreProducto,
            String variedadProducto);

    /**
     * Método que permite buscar productos por nombre y proveedor.
     *
     * @param nombreProducto Objeto String que representa el nombre o parte del nombre
     * del producto a buscar.
     * @param nombreProveedor Objeto String que representa el nombre del proveedor o parte del
     * nombre del proveedor del producto a buscar.
     * @return Objeto List de InformacionProductoInicioDTO que contiene la información
     * resumida de los productos que coinciden con los criterios de búsqueda.
     * @throws ProductosIdProveedorInvalidoException
     */
    public abstract List<InformacionProductoInicioDTO> obtenerProductosBusquedaNombreProductoProveedor(
            String nombreProducto,
            String nombreProveedor)
            throws ProductosIdProveedorInvalidoException;


    /**
     * Método que permite obtener un producto específico identificado por su ID.
     *
     * @param idProductoDTO Objeto IdProductoDTO que contiene el ID del producto a obtener.
     * @return Objeto Producto que representa el producto con el ID especificado.
     */
    public abstract ProductoDTO obtenerProducto(IdProductoDTO idProductoDTO);
    
    /**
     * Método que permite obtener un producto en inventario específico identificado por su ID.
     *
     * @param idProductoInventario Objeto IdProductoInventarioDTO que contiene el 
     * ID del producto en inventario a obtener.
     * @return Objeto Producto que representa el producto con el ID especificado.
     */
    public abstract ProductoInventarioDTO obtenerProductoInventario(IdProductoInventarioDTO idProductoInventario);

    /**
     * Método que permite verificar si el ID de un producto en inventario es válido.
     *
     * @param idProductoInventarioDTO Objeto IdProductoInventarioDTO que contiene el ID del producto en
     * inventario a validar.
     * @return true si el ID del producto en inventario es válido, false en caso contrario.
     */
    public abstract boolean validarProductoInventario(IdProductoInventarioDTO idProductoInventarioDTO);
    
    public void apartarProductoInventarioPedido(IdProductoInventarioDTO idProductoInventarioDTO) 
            throws ProductosIdProductoInventarioInvalidoException ;
}
