
package com.mycompany.megazarl.administrador.mongodb;

import com.mycompany.megazarl.administrador.mongodb.daos.AuxiliaresVentasDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.CarritosComprasDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.PaqueteriasDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.ClientesDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.GerentesVentasDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.PedidosDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.ProductosDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.ProductosInventarioDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.ProveedoresDAO;
import com.mycompany.megazarl.administrador.mongodb.daos.SucursalesDAO;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.ActualizacionClienteDTODatos;
import edu.student.itson.dissof.dto.datos.ActualizacionProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.ActualizacionProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.AuxiliarVentasDTODatos;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.ClienteDTODatos;
import edu.student.itson.dissof.dto.datos.GerenteVentasDTODatos;
import edu.student.itson.dissof.dto.datos.IdAuxiliarVentasDTODatos;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdClienteDTODatos;
import edu.student.itson.dissof.dto.datos.IdGerenteVentasDTODatos;
import edu.student.itson.dissof.dto.datos.IdPaqueteriaDTODatos;
import edu.student.itson.dissof.dto.datos.IdPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProveedorDTODatos;
import edu.student.itson.dissof.dto.datos.IdSucursalDTODatos;
import edu.student.itson.dissof.dto.datos.PaqueteriaDTODatos;
import edu.student.itson.dissof.dto.datos.PedidoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.ProveedorDTODatos;
import edu.student.itson.dissof.dto.datos.SucursalDTODatos;
import java.util.Collection;
import java.util.List;


public class FAdministradorMongodb implements IAdministradorMongodb{

    private final AuxiliaresVentasDAO auxiliaresVentasDAO;
    private final CarritosComprasDAO carritosComprasDAO;
    private final ClientesDAO clientesDAO;
    private final GerentesVentasDAO gerentesVentasDAO;
    private final PaqueteriasDAO paqueteriasDAO;
    private final PedidosDAO pedidosDAO;
    private final ProductosDAO productosDAO;
    private final ProductosInventarioDAO productosInventarioDAO;
    private final ProveedoresDAO proveedoresDAO;
    private final SucursalesDAO sucursalesDAO;
    
    
    public FAdministradorMongodb(){
        carritosComprasDAO = new CarritosComprasDAO();
        clientesDAO = new ClientesDAO();
        gerentesVentasDAO = new GerentesVentasDAO();
        paqueteriasDAO = new PaqueteriasDAO();
        auxiliaresVentasDAO = new AuxiliaresVentasDAO();
        pedidosDAO = new PedidosDAO();
        productosDAO = new ProductosDAO();
        productosInventarioDAO = new ProductosInventarioDAO();
        proveedoresDAO = new ProveedoresDAO();
        sucursalesDAO = new SucursalesDAO();
        
    }
    
    // Métodos de consultas de auxiliares de ventas
    @Override
    public AuxiliarVentasDTODatos recuperarAuxiliarVentasPorId(IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return auxiliaresVentasDAO.recuperarPorId(idAuxiliarVentasDTODatos);
        
    }

    @Override
    public boolean existeAuxiliarVentasPorId(IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return auxiliaresVentasDAO.existePorId(idAuxiliarVentasDTODatos);
    }
    
    @Override
    public void agregarAuxiliarVentas(AuxiliarVentasDTODatos auxiliarVentasDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
        auxiliaresVentasDAO.agregar(auxiliarVentasDTODatos);
    }

    @Override
    public void agregarColeccionAuxiliaresVentas(Collection<AuxiliarVentasDTODatos> auxiliaresVenta) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       auxiliaresVentasDAO.agregar(auxiliaresVenta);
    }

    @Override
    public List<AuxiliarVentasDTODatos> recuperarTodosAuxiliaresVentas() {
        
        return auxiliaresVentasDAO.recuperarTodos();
    }
    
    // Métodos para consultas de carritos de compras
    
    @Override
    public CarritoComprasDTODatos recuperarCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return carritosComprasDAO.recuperarPorId(idCarritoComprasDTODatos);
        
    }

    @Override
    public boolean existeCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return carritosComprasDAO.existePorId(idCarritoComprasDTODatos);
    }

    @Override
    public CarritoComprasDTODatos actualizarCarritoCompras(ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
        return carritosComprasDAO.actualizar(actualizacionCarritoComprasDTODatos);
    }

    @Override
    public void agregarCarritoCompras(CarritoComprasDTODatos carritoCompras) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
        carritosComprasDAO.agregar(carritoCompras);
    }

    @Override
    public void agregarColeccionCarritosCompra(Collection<CarritoComprasDTODatos> carritosCompras) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       carritosComprasDAO.agregar(carritosCompras);
    }

    @Override
    public List<CarritoComprasDTODatos> recuperarTodosCarritoCompras() {
        
        return carritosComprasDAO.recuperarTodos();
    }
    
    @Override
    public ProductoCarritoDTODatos recuperarProductoCarritoPorId(IdProductoCarritoDTODatos idProductoCarritoDTODatos) 
            throws RegistroInexistenteException, 
            ParametroNuloException, 
            FormatoIdInvalidoException {
        
        return carritosComprasDAO.recuperarProductoCarritoPorId(idProductoCarritoDTODatos);
        
    }

    @Override
    public boolean existeProductoCarritoPorId(IdProductoCarritoDTODatos idProductoCarritoDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        return carritosComprasDAO.existeProductoCarritoPorId(idProductoCarritoDTODatos);

    }

    @Override
    public ProductoCarritoDTODatos actualizarProductoCarrito(ActualizacionProductoCarritoDTODatos actualizacionProductoCarritoDTODatos) 
            throws RegistroInexistenteException,
            ParametroNuloException, 
            FormatoIdInvalidoException, 
            ValorParametroInvalidoException {
        
        return carritosComprasDAO.actualizarProductoCarrito(actualizacionProductoCarritoDTODatos);
        
    }

    @Override
    public void removerProductoCarritoPorId(IdProductoCarritoDTODatos idProdutoCarritoDTODatos) 
            throws ParametroNuloException, 
            FormatoIdInvalidoException {
        
        carritosComprasDAO.removerProductoCarritoPorId(idProdutoCarritoDTODatos);
    }

    @Override
    public void agregarProductoCarrito(ProductoCarritoDTODatos nuevoProductoCarrito)
            throws ParametroNuloException, 
            RegistroInexistenteException,
            FormatoIdInvalidoException {
        
            carritosComprasDAO.agregarProductoCarrito(nuevoProductoCarrito);
        
    }

    @Override
    public void agregarProductosCarrito(Collection<ProductoCarritoDTODatos> nuevosProductosCarrito) 
            throws ParametroNuloException,
            RegistroInexistenteException, 
            FormatoIdInvalidoException {
        
            carritosComprasDAO.agregarProductosCarrito(nuevosProductosCarrito);
        
    }

    @Override
    public List<ProductoCarritoDTODatos> recuperarTodosProductosCarrito() {
        
        return carritosComprasDAO.recuperarTodosProductosCarrito();
        
    }
    
    // Métodos para consultas de clientes
    
    @Override
    public ClienteDTODatos recuperarClientePorId(IdClienteDTODatos idClienteDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return clientesDAO.recuperarPorId(idClienteDTODatos);
        
    }

    @Override
    public boolean existeClientePorId(IdClienteDTODatos idClienteDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return clientesDAO.existePorId(idClienteDTODatos);
    }

    @Override
    public ClienteDTODatos actualizarCliente(ActualizacionClienteDTODatos actualizacionClienteDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        return clientesDAO.actualizar(actualizacionClienteDTODatos);
    }

    @Override
    public void agregarCliente(ClienteDTODatos cliente) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
        clientesDAO.agregar(cliente);
    }

    @Override
    public void agregarColeccionClientes(Collection<ClienteDTODatos> clientes) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
       clientesDAO.agregar(clientes);
    }

    @Override
    public List<ClienteDTODatos> recuperarTodosClientes() {
        
        return clientesDAO.recuperarTodos();
    }
    
    // Métodos para consultas de gerentes de ventas
    
    @Override
    public GerenteVentasDTODatos recuperarGerenteVentasPorId(IdGerenteVentasDTODatos idGerenteVentasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return gerentesVentasDAO.recuperarPorId(idGerenteVentasDTODatos);
        
    }

    @Override
    public boolean existeGerenteVentasPorId(IdGerenteVentasDTODatos idGerenteVentasDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return gerentesVentasDAO.existePorId(idGerenteVentasDTODatos);
    }

    @Override
    public void agregarGerenteVentas(GerenteVentasDTODatos gerenteVentasDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
        gerentesVentasDAO.agregar(gerenteVentasDTODatos);
    }

    @Override
    public void agregarColeccionGerentesVentas(Collection<GerenteVentasDTODatos> gerentesVentasDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException{
        
       gerentesVentasDAO.agregar(gerentesVentasDTODatos);
    }

    @Override
    public List<GerenteVentasDTODatos> recuperarTodosGerentesVentas() {
        
        return gerentesVentasDAO.recuperarTodos();
    }

    // Métodos para consultas de Paqueterías
    
    @Override
    public PaqueteriaDTODatos recuperarPaqueteriaPorId(IdPaqueteriaDTODatos idPaqueteriaDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return paqueteriasDAO.recuperarPorId(idPaqueteriaDTODatos);
        
    }

    @Override
    public boolean existePaqueteriaPorId(IdPaqueteriaDTODatos idPaqueteriaDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return paqueteriasDAO.existePorId(idPaqueteriaDTODatos);
    }

    @Override
    public void agregarPaqueteria(PaqueteriaDTODatos paqueteriaDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        paqueteriasDAO.agregar(paqueteriaDTODatos);
    }

    @Override
    public void agregarColeccionPaqueterias(Collection<PaqueteriaDTODatos> paqueteriasDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       paqueteriasDAO.agregar(paqueteriasDTODatos);
    }

    @Override
    public List<PaqueteriaDTODatos> recuperarTodosPaqueterias() {
        
        return paqueteriasDAO.recuperarTodos();
    }
    
    
    // Métodos de consultas de pedidos
    
    @Override
    public PedidoDTODatos recuperarPedidoPorId(IdPedidoDTODatos idPedidoDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return pedidosDAO.recuperarPorId(idPedidoDTODatos);
        
    }

    @Override
    public boolean existePedidoPorId(IdPedidoDTODatos idPedidoDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return pedidosDAO.existePorId(idPedidoDTODatos);
    }

    @Override
    public void agregarPedido(PedidoDTODatos pedidoDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        pedidosDAO.agregar(pedidoDTODatos);
    }

    @Override
    public void agregarColeccionPedidos(Collection<PedidoDTODatos> pedidosDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       pedidosDAO.agregar(pedidosDTODatos);
    }

    @Override
    public List<PedidoDTODatos> recuperarTodosPedidos() {
        
        return pedidosDAO.recuperarTodos();
    }
    
    // Métodos de consultas de productos
    
    @Override
    public boolean existeProductoPedidoPorId(IdProductoPedidoDTODatos idProductoPedidoDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return pedidosDAO.existeProductoPedidoPorId(idProductoPedidoDTODatos);
    }

    @Override
    public void agregarProductoPedido(ProductoPedidoDTODatos nuevoProductoPedido) 
            throws ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException {
        
        pedidosDAO.agregarProductoPedido(nuevoProductoPedido);
        
    }

    @Override
    public void agregarProductoPedido(Collection<ProductoPedidoDTODatos> nuevosProductosPedido)
            throws ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException {
        
        pedidosDAO.agregarProductoPedido(nuevosProductosPedido);
    }

    @Override
    public List<ProductoPedidoDTODatos> recuperarTodosProductosPedido() {
        
        return pedidosDAO.recuperarTodosProductosPedido();
        
    }
    
    @Override
    public ProductoDTODatos recuperarProductoPorId(IdProductoDTODatos idProductoDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return productosDAO.recuperarPorId(idProductoDTODatos);
        
    }

    @Override
    public boolean existeProductoPorId(IdProductoDTODatos idProductoDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return productosDAO.existePorId(idProductoDTODatos);
    }

    @Override
    public void agregarProducto(ProductoDTODatos productoDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        productosDAO.agregar(productoDTODatos);
    }

    @Override
    public void agregarColeccionProductos(Collection<ProductoDTODatos> productosDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       productosDAO.agregar(productosDTODatos);
    }

    @Override
    public List<ProductoDTODatos> recuperarTodosProductos() {
        
        return productosDAO.recuperarTodos();
    }
    
    // Métodos de consultas de productos en inventario
    
    @Override
    public ProductoInventarioDTODatos recuperarProductoInventarioPorId(IdProductoInventarioDTODatos idProductoInventarioDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return productosInventarioDAO.recuperarPorId(idProductoInventarioDTODatos);
        
    }

    @Override
    public boolean existeProductoInventarioPorId(IdProductoInventarioDTODatos idProductoInventarioDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return productosInventarioDAO.existePorId(idProductoInventarioDTODatos);
    }

    @Override
    public ProductoInventarioDTODatos actualizarProductoInventario(ActualizacionProductoInventarioDTODatos atualizacionProductoInventarioDTODatos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {
        
        return productosInventarioDAO.actualizar(atualizacionProductoInventarioDTODatos);
    }
    
    
    @Override
    public void agregarProductoInventario(ProductoInventarioDTODatos productoInventarioDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        productosInventarioDAO.agregar(productoInventarioDTODatos);
    }

    @Override
    public void agregarColeccionProductosInventario(Collection<ProductoInventarioDTODatos> productosInventarioDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       productosInventarioDAO.agregar(productosInventarioDTODatos);
    }
    
    @Override
    public List<ProductoInventarioDTODatos> recuperarPorIdProducto(IdProductoDTODatos idProductoDTODatos) 
            throws ParametroNuloException, 
            FormatoIdInvalidoException {
        
        return productosInventarioDAO.recuperarPorIdProducto(idProductoDTODatos);
    }

    @Override
    public List<ProductoInventarioDTODatos> recuperarTodosProductosInventario() {
        
        return productosInventarioDAO.recuperarTodos();
    }
    
    // Métodos de consultas de proveedores
    
    @Override
    public ProveedorDTODatos recuperarProveedorPorId(IdProveedorDTODatos idProveedorDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return proveedoresDAO.recuperarPorId(idProveedorDTODatos);
        
    }

    @Override
    public boolean existeProveedorPorId(IdProveedorDTODatos idProveedorDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return proveedoresDAO.existePorId(idProveedorDTODatos);
    }

    @Override
    public void agregarProveedor(ProveedorDTODatos proveedorDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        proveedoresDAO.agregar(proveedorDTODatos);
    }

    @Override
    public void agregarColeccionProveedores(Collection<ProveedorDTODatos> proveedorDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       proveedoresDAO.agregar(proveedorDTODatos);
    }

    @Override
    public List<ProveedorDTODatos> recuperarTodosProveedores() {
        
        return proveedoresDAO.recuperarTodos();
    }
    
    
    // Métodos de consultas de sucursales
    
    @Override
    public SucursalDTODatos recuperarSucursalPorId(IdSucursalDTODatos idSucursalDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return sucursalesDAO.recuperarPorId(idSucursalDTODatos);
        
    }

    @Override
    public boolean existeSucursalPorId(IdSucursalDTODatos idSucursalDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException{
        
        return sucursalesDAO.existePorId(idSucursalDTODatos);
    }

    @Override
    public void agregarSucursal(SucursalDTODatos sucursalDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
        sucursalesDAO.agregar(sucursalDTODatos);
    }

    @Override
    public void agregarColeccionSucursales(Collection<SucursalDTODatos> sucursalDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException{
        
       sucursalesDAO.agregar(sucursalDTODatos);
    }

    @Override
    public List<SucursalDTODatos> recuperarTodosSucursales() {
        
        return sucursalesDAO.recuperarTodos();
    }
    
    @Override
    public SucursalDTODatos obtenerSucursalMatriz() throws RegistroInexistenteException{
        
        return sucursalesDAO.obtenerSucursalMatriz();
    }

}
