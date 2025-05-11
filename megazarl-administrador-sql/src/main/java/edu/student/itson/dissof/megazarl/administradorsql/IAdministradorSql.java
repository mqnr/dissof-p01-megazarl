package edu.student.itson.dissof.megazarl.administradorsql;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PedidoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
/**
 * IAdministradorSql.java
 *
 * Interfaz del subsistema AdministradorSql, que se encarga de gestionar las consultas,
 * inserciones y actualizaciones relacionadas con carritos de compras, clientes, paqueterías,
 * pedidos, productos, inventario, proveedores y sucursales
 *
 * Esta interfaz actúa como punto de acceso para las operaciones de persistencia sobre los
 * objetos de negocio correspondientes a la capa SQL del sistema
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
public interface IAdministradorSql {
    
    // -------------------------------
    //Métodos para Carritos de Compras
    // -------------------------------

    /**
     * Recupera un carrito de compras por su ID
     * 
     * @param idCarritoComprasDTO ID del carrito a recuperar
     * @return CarritoComprasDTO con la información del carrito
     */
    public abstract CarritoComprasDTO recuperarCarritoComprasPorId(IdCarritoComprasDTO idCarritoComprasDTO);
    
    /**
     * Verifica si existe un carrito de compras por su ID
     * 
     * @param idCarritoComprasDTO ID del carrito
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    public abstract boolean existeCarritoComprasPorId(IdCarritoComprasDTO idCarritoComprasDTO);
    
    /**
     * Actualiza la información de un carrito de compras
     * 
     * @param actualizacionCarritoComprasDTO Datos actualizados del carrito
     * @return CarritoComprasDTO actualizado
     */
    public abstract CarritoComprasDTO actualizarCarritoCompras(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO);
     
    /**
     * Agrega un nuevo carrito de compras al sistema
     * 
     * @param carritoCompras Carrito a agregar
     */
    public abstract void agregarCarritoCompras(CarritoComprasDTO carritoCompras);
    
    /**
     * Agrega una colección de carritos de compras
     * 
     * @param carritosCompras Colección de carritos a agregar
     */
    public abstract void agregarCarritoComprasColeccion(Collection<CarritoComprasDTO> carritosCompras);
     
    /**
     * Recupera todos los carritos de compras almacenados
     * 
     * @return Lista de CarritoComprasDTO
     */
    public abstract List<CarritoComprasDTO> recuperarTodosCarritosCompra();
    
    /**
     * Cuenta la cantidad total de carritos de compras almacenados
     * 
     * @return Número total de carritos
     */
    public abstract long cuentaCarritosCompra();
    
    /**
     * Verifica si existe al menos un carrito que cumpla con un criterio específico
     * 
     * @param criterio Predicado de evaluación
     * @return {@code true} si al menos un carrito cumple el criterio
     */
    public abstract boolean existeCarritoCompras(Predicate<CarritoComprasDTO> criterio);
    
    
    // -----------------------
    // Métodos para Clientes
    // -----------------------
    
    /**
    * Recupera un cliente por su identificador único
    *
    * @param idClienteDTO Objeto que contiene el identificador del cliente
    * @return ClienteDTO con los datos del cliente recuperado
    */
    public abstract ClienteDTO recuperarClientePorId(IdClienteDTO idClienteDTO);

    /**
    * Verifica si existe un cliente dado su identificador
    *
    * @param idClienteDTO Objeto con el ID del cliente a verificar
    * @return {@code true} si el cliente existe, {@code false} en caso contrario
    */
    public abstract boolean existeClientePorId(IdClienteDTO idClienteDTO);

    /**
    * Actualiza los datos de un cliente existente en la base de datos
    *
    * @param actualizacionClienteDTO Objeto con los datos actualizados del cliente
    * @return ClienteDTO con la información actualizada
    */
    public abstract ClienteDTO actualizarCliente(ActualizacionClienteDTO actualizacionClienteDTO);

    /**
    * Agrega un nuevo cliente al sistema
    *
    * @param cliente Objeto ClienteDTO que representa al cliente a agregar
    */
    public abstract void agregarCliente(ClienteDTO cliente);

    /**
    * Agrega una colección de clientes al sistema
    *
    * @param clientes Colección de objetos ClienteDTO a insertar
    */
    public abstract void agregarClienteColeccion(Collection<ClienteDTO> clientes);
    
    /**
    * Recupera todos los clientes registrados en el sistema
    *
    * @return Lista con todos los objetos ClienteDTO
    */
    public abstract List<ClienteDTO> recuperarTodosClientes();

    /**
    * Cuenta la cantidad total de clientes en el sistema
    *
    * @return Número total de clientes
    */
    public abstract long cuentaClientes();

    /**
    * Verifica si existe al menos un cliente que cumpla con un criterio específico
    *
    * @param criterio Predicado que representa la condición a evaluar
    * @return {@code true} si existe algún cliente que cumpla el criterio, {@code false} en caso contrario
    */
    public abstract boolean existeCliente(Predicate<ClienteDTO> criterio);
    
    // -----------------------
    // Métodos para Paqueterias
    // -----------------------
    
    /**
    * Recupera una paquetería por su identificador único
    *
    * @param idPaqueteriaDTO Objeto que contiene el identificador de la paquetería
    * @return PaqueteriaDTO con los datos de la paquetería recuperada
    */
    public abstract PaqueteriaDTO recuperarPaqueteriaPorId(IdPaqueteriaDTO idPaqueteriaDTO);

    /**
    * Verifica si existe una paquetería en el sistema mediante su identificador
    *
    * @param idPaqueteriaDTO Objeto que contiene el ID de la paquetería a verificar
    * @return {@code true} si la paquetería existe, {@code false} en caso contrario.
    */
    public abstract boolean existePaqueteriaPorId(IdPaqueteriaDTO idPaqueteriaDTO);

    /**
    * Agrega una nueva paquetería al sistema
    *
    * @param cliente Objeto PaqueteriaDTO que representa la paquetería a insertar
    */
    public abstract void agregarPaqueteria(PaqueteriaDTO cliente);

    /**
    * Agrega una colección de paqueterías al sistema
    *
    * @param paqueterias Colección de objetos PaqueteriaDTO a insertar
    */
    public abstract void agregarPaqueteriaColeccion(Collection<PaqueteriaDTO> paqueterias);

    /**
    * Recupera todas las paqueterías registradas en el sistema
    *
    * @return Lista de objetos PaqueteriaDTO
    */
    public abstract List<PaqueteriaDTO> recuperarTodasPaqueterias();

    /**
    * Obtiene un flujo (stream) de las paqueterías registradas
    *
    * @return Stream de objetos PaqueteriaDTO
    */
    public abstract Stream<PaqueteriaDTO> streamPaqueteria();

    /**
    * Cuenta la cantidad total de paqueterías en el sistema
    *
    * @return Número total de paqueterías
    */
    public abstract long cuentaPaqueterias();

    /**
    * Verifica si existe al menos una paquetería que cumpla con un criterio específico
    *
    * @param criterio Predicado que representa la condición a evaluar
    * @return {@code true} si existe alguna paquetería que cumpla el criterio, {@code false} en caso contrario
    */
    public abstract boolean existePaqueteria(Predicate<PaqueteriaDTO> criterio);
    
    
    // -----------------------
    // Métodos para Pedidos
    // -----------------------
    
    /**
    * Recupera un pedido específico a partir de su identificador
    *
    * @param id Identificador único del pedido
    * @return PedidoDTO con los datos del pedido recuperado
    */
    public abstract PedidoDTO recuperarPedidoPorId(Integer id);
    
    /**
    * Verifica si existe un pedido en el sistema mediante su identificador
    *
    * @param id Identificador del pedido a verificar
    * @return {@code true} si el pedido existe, {@code false} en caso contrario
    */
    public abstract boolean existePedidoPorId(Integer id);
     
    /**
    * Agrega un nuevo pedido al sistema
    *
    * @param pedido Objeto PedidoDTO que representa el pedido a insertar
    */
    public abstract void agregarPedido(PedidoDTO pedido);
    
    /**
    * Agrega una colección de pedidos al sistema
    *
    * @param pedidos Colección de objetos PedidoDTO a insertar
    */
    public abstract void agregarPedidoColeccion(Collection<PedidoDTO> pedidos);
     
    /**
    * Recupera todos los pedidos registrados en el sistema
    *
    * @return Lista de objetos PedidoDTO
    */
    public abstract List<PedidoDTO> recuperarTodosPedidos();
    
    /**
    * Cuenta la cantidad total de pedidos en el sistema
    *
    * @return Número total de pedidos
    */
    public abstract long cuentaPedidos();
    
    /**
    * Verifica si existe al menos un pedido que cumpla con un criterio específico
    *
    * @param criterio Predicado que representa la condición a evaluar
    * @return {@code true} si existe algún pedido que cumpla el criterio, {@code false} en caso contrario
    */
    public abstract boolean existePedido(Predicate<PedidoDTO> criterio);
    
    // -----------------------
    // Métodos para Productos
    // -----------------------
    
    /**
    * Recupera un producto a partir de su identificador
    *
    * @param idProductoDTO Objeto que contiene el identificador único del producto
    * @return ProductoDTO con los datos del producto recuperado
    */
    public abstract ProductoDTO recuperarProductoPorId(IdProductoDTO idProductoDTO);
    
    /**
    * Verifica si existe un producto en el sistema mediante su identificador
    *
    * @param idProductoDTO Objeto que contiene el ID del producto a verificar
    * @return {@code true} si el producto existe, {@code false} en caso contrario
    */
    public abstract boolean existeProductoPorId(IdProductoDTO idProductoDTO);
     
    /**
    * Agrega un nuevo producto al sistema
    *
    * @param producto Objeto ProductoDTO que representa el producto a insertar
    */
    public abstract void agregarProducto(ProductoDTO producto);
    
    /**
    * Agrega una colección de productos al sistema
    *
    * @param productos Colección de objetos ProductoDTO a insertar
    */
    public abstract void agregarProductoColeccion(Collection<ProductoDTO> productos);
     
    /**
    * Recupera todos los productos registrados en el sistema
    *
    * @return Lista de objetos ProductoDTO
    */
    public abstract List<ProductoDTO> recuperarTodosProductos();

    /**
     * Cuenta la cantidad total de productos en el sistema
     *
     * @return Número total de productos
     */
    public abstract long cuentaProductos();
    
    /**
    * Verifica si existe al menos un producto que cumpla con un criterio específico
    *
    * @param criterio Predicado que representa la condición a evaluar
    * @return {@code true} si existe algún producto que cumpla el criterio, {@code false} en caso contrario
    */
    public abstract boolean existeProducto(Predicate<ProductoDTO> criterio);

    // -----------------------
    // Métodos para Productos en Inventario
    // -----------------------
    
    /**
    * Recupera un producto del inventario a partir de su identificador
    *
    * @param idProductoInventarioDTO Objeto que contiene el ID del producto en inventario
    * @return ProductoInventarioDTO con la información del producto recuperado
    */
    public abstract ProductoInventarioDTO recuperarProductosInventarioPorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
    /**
    * Verifica si existe un producto en inventario mediante su identificador
    *
    * @param idProductoInventarioDTO Objeto que contiene el ID del producto en inventario a verificar
    * @return {@code true} si el producto en inventario existe, {@code false} en caso contrario
    */
    public abstract boolean existeProductoInventarioPorId(IdProductoInventarioDTO idProductoInventarioDTO);
     
    /**
    * Agrega un nuevo producto al inventario
    *
    * @param productoInventario Objeto ProductoInventarioDTO que representa el producto en inventario a insertar
    */
    public abstract void agregarProductoInventario(ProductoInventarioDTO productoInventario);
    
    /**
    * Agrega una colección de productos al inventario
    *
    * @param productosInventario Colección de objetos ProductoInventarioDTO a insertar en el inventario
    */
    public abstract void agregarProductoInventarioColeccion(Collection<ProductoInventarioDTO> productosInventario);
    
    /**
     * Recupera todos los productos actualmente registrados en el inventario
     *
     * @return Lista de objetos ProductoInventarioDTO
     */
    public abstract List<ProductoInventarioDTO> recuperarTodosProductosInventario();
    
    /**
    * Cuenta la cantidad total de productos en inventario registrados en el sistema
    *
    * @return Número total de productos en inventario
    */
    public abstract long cuentaProductosInventario();
    
    /**
     * Verifica si existe al menos un producto en inventario que cumpla con un criterio específico
     *
     * @param criterio Predicado que representa la condición a evaluar
     * @return {@code true} si existe algún producto que cumpla el criterio, {@code false} en caso contrario
     */
    public abstract boolean existeProductoInventario(Predicate<ProductoInventarioDTO> criterio);
    
    // -----------------------
    // Métodos para Proveedores
    // -----------------------
    
    /**
    * Recupera un proveedor a partir de su identificador único
    *
    * @param idProveedorDTO Objeto que contiene el ID del proveedor
    * @return ProveedorDTO con los datos del proveedor recuperado
    */
    public abstract ProveedorDTO recuperarProveedorPorId(IdProveedorDTO idProveedorDTO);
     
    /**
     * Verifica si un proveedor existe en el sistema mediante su identificador
     *
     * @param idProveedorDTO Objeto que contiene el ID del proveedor a verificar
     * @return {@code true} si el proveedor existe, {@code false} en caso contrario
     */
    public abstract boolean existeProveedorPorId(IdProveedorDTO idProveedorDTO);
     
    /**
    * Agrega un nuevo proveedor al sistema
    *
    * @param proveedorDTO Objeto ProveedorDTO que representa al proveedor a insertar
    */
    public abstract void agregarProveedor(ProveedorDTO proveedorDTO);
    
    /**
    * Agrega una colección de proveedores al sistema
    *
    * @param proveedores Colección de objetos ProveedorDTO a insertar
    */
    public abstract void agregarProveedorColeccion(Collection<ProveedorDTO> proveedores);
     
    /**
    * Recupera todos los proveedores registrados en el sistema
    *
    * @return Lista de objetos ProveedorDTO
    */
    public abstract List<ProveedorDTO> recuperarTodosProveedores();
    
    /**
    * Cuenta la cantidad total de proveedores en el sistema
    *
    * @return Número total de proveedores
    */
    public abstract long cuentaProveedores();
    
    /**
    * Verifica si existe al menos un proveedor que cumpla con un criterio específico.
    *
    * @param criterio Predicado que representa la condición a evaluar.
    * @return {@code true} si existe algún proveedor que cumpla el criterio, {@code false} en caso contrario.
    */
    public abstract boolean existeProveedor(Predicate<ProveedorDTO> criterio);

    // -----------------------
    // Métodos para Sucursales
    // -----------------------
    
    /**
     * Recupera una sucursal a partir de su identificador único
     *
     * @param idSucursalDTO Objeto que contiene el ID de la sucursal
     * @return SucursalDTO con los datos de la sucursal recuperada
     */
    public abstract SucursalDTO recuperarSucursalPorId(IdSucursalDTO idSucursalDTO);
    
    /**
    * Verifica si una sucursal existe en el sistema mediante su identificador
    *
    * @param idSucursalDTO Objeto que contiene el ID de la sucursal a verificar
    * @return {@code true} si la sucursal existe, {@code false} en caso contrario
    */
    public abstract boolean existeSucursalPorId(IdSucursalDTO idSucursalDTO);
     
    /**
    * Agrega una nueva sucursal al sistema
    *
    * @param sucursal Objeto SucursalDTO que representa la sucursal a insertar
    */
    public abstract void agregarSucursal(SucursalDTO sucursal);
     
    /**
    * Agrega una nueva sucursal al sistema utilizando datos individuales
    *
    * @param id ID de la sucursal.
    * @param esMatriz Valor booleano que indica si la sucursal es la matriz
    * @param tiempoMatriz Tiempo estimado entre la sucursal y la matriz
    * @param direccion Objeto DireccionDTO que representa la dirección de la sucursal
    */
    public abstract void agregarSucursal2(
            Long id,
            Boolean esMatriz,
            Float tiempoMatriz,
            DireccionDTO direccion
    );
    
    /**
    * Agrega una colección de sucursales al sistema
    *
    * @param sucursales Colección de objetos SucursalDTO a insertar
    */
    public abstract void agregarSucursal(Collection<SucursalDTO> sucursales);
     
    /**
    * Recupera todas las sucursales registradas en el sistema
    *
    * @return Lista de objetos SucursalDTO
    */
    public abstract List<SucursalDTO> recuperarTodasSucursales();
    
    /**
     * Obtiene la sucursal que ha sido designada como matriz
     *
     * @return Objeto SucursalDTO que representa la sucursal matriz
     */
    public abstract SucursalDTO obtenerSucursalMatriz();
    
    /**
    * Cuenta la cantidad total de sucursales registradas en el sistema
    *
    * @return Número total de sucursales
    */
    public abstract long cuentaSucursales();
    
    /**
     * Verifica si existe al menos una sucursal que cumpla con un criterio específico
     *
     * @param criterio Predicado que representa la condición a evaluar
     * @return {@code true} si existe alguna sucursal que cumpla el criterio, {@code false} en caso contrario
     */
    public abstract boolean existeSucursal(Predicate<SucursalDTO> criterio);
    
}