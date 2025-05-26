
package com.mycompany.megazarl.administrador.mongodb;

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


public interface IAdministradorMongodb {
    
    // Métodos de consultas de auxiliares de ventas
    
    public abstract AuxiliarVentasDTODatos recuperarAuxiliarVentasPorId(IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeAuxiliarVentasPorId(IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarAuxiliarVentas(AuxiliarVentasDTODatos auxiliarVentas) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract void agregarColeccionAuxiliaresVentas(Collection<AuxiliarVentasDTODatos> auxiliaresVentas)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<AuxiliarVentasDTODatos> recuperarTodosAuxiliaresVentas();
    
    // Métodos de consultas de carritos de compras
    
    public abstract CarritoComprasDTODatos recuperarCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract CarritoComprasDTODatos actualizarCarritoCompras(ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract void agregarCarritoCompras(CarritoComprasDTODatos carritoCompras) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract void agregarColeccionCarritosCompra(Collection<CarritoComprasDTODatos> carritosCompras)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<CarritoComprasDTODatos> recuperarTodosCarritoCompras();
    
    public abstract ProductoCarritoDTODatos recuperarProductoCarritoPorId(IdProductoCarritoDTODatos idProductoCarritoDTODatos)
            throws RegistroInexistenteException, 
            ParametroNuloException, 
            FormatoIdInvalidoException ;
    
    public abstract boolean existeProductoCarritoPorId(IdProductoCarritoDTODatos idProductoCarritoDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract ProductoCarritoDTODatos actualizarProductoCarrito(ActualizacionProductoCarritoDTODatos actualizacionProductoCarritoDTODatos)
            throws RegistroInexistenteException, 
            ParametroNuloException, 
            FormatoIdInvalidoException, 
            ValorParametroInvalidoException;
    
    public abstract void removerProductoCarritoPorId(IdProductoCarritoDTODatos idProdutoCarritoDTODatos) 
            throws ParametroNuloException, 
            FormatoIdInvalidoException;
    
    public abstract void agregarProductoCarrito(ProductoCarritoDTODatos nuevoProductoCarrito)
            throws ParametroNuloException, 
            RegistroInexistenteException,
            FormatoIdInvalidoException;
    
    public void agregarProductosCarrito(Collection<ProductoCarritoDTODatos> nuevosProductosCarrito)  
            throws ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException;
    
    public List<ProductoCarritoDTODatos> recuperarTodosProductosCarrito();
    
    // Métodos de consultas de clientes
    public abstract ClienteDTODatos recuperarClientePorId(IdClienteDTODatos idClienteDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeClientePorId(IdClienteDTODatos idClienteDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract ClienteDTODatos actualizarCliente(ActualizacionClienteDTODatos actualizacionClienteDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarCliente(ClienteDTODatos clienteDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract void agregarColeccionClientes(Collection<ClienteDTODatos> clientesDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract List<ClienteDTODatos> recuperarTodosClientes();
    
    
    // Métodos de consultas de gerentes de ventas
    public abstract GerenteVentasDTODatos recuperarGerenteVentasPorId(IdGerenteVentasDTODatos idGerenteVentasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeGerenteVentasPorId(IdGerenteVentasDTODatos idGerenteVentasDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarGerenteVentas(GerenteVentasDTODatos gerenteVentas) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract void agregarColeccionGerentesVentas(Collection<GerenteVentasDTODatos> gerentesVentasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException;
    
    public abstract List<GerenteVentasDTODatos> recuperarTodosGerentesVentas();
    
    // Métodos de consultas de paqueterías
    public abstract PaqueteriaDTODatos recuperarPaqueteriaPorId(IdPaqueteriaDTODatos idPaquteriaDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existePaqueteriaPorId(IdPaqueteriaDTODatos idPaqueteriaDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarPaqueteria(PaqueteriaDTODatos paqueteriaDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarColeccionPaqueterias(Collection<PaqueteriaDTODatos> paqueteriasDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<PaqueteriaDTODatos> recuperarTodosPaqueterias();
    
    // Métodos de consultas de pedidos
    public abstract PedidoDTODatos recuperarPedidoPorId(IdPedidoDTODatos idPedidoDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existePedidoPorId(IdPedidoDTODatos idPedidoDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarPedido(PedidoDTODatos pedidoDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarColeccionPedidos(Collection<PedidoDTODatos> pedidosDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<PedidoDTODatos> recuperarTodosPedidos();
    
    public abstract boolean existeProductoPedidoPorId(IdProductoPedidoDTODatos idProductoPedidoDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarProductoPedido(ProductoPedidoDTODatos nuevoProductoPedido)
            throws ParametroNuloException, 
            RegistroInexistenteException,
            FormatoIdInvalidoException;
    
    public abstract void agregarProductoPedido(Collection<ProductoPedidoDTODatos> nuevosProductosPedido)
            throws ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException ;
    
    public abstract List<ProductoPedidoDTODatos> recuperarTodosProductosPedido();
    
    // Métodos de consultas de productos
    public abstract ProductoDTODatos recuperarProductoPorId(IdProductoDTODatos idProductoDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeProductoPorId(IdProductoDTODatos idProductoDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarProducto(ProductoDTODatos productoDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarColeccionProductos(Collection<ProductoDTODatos> productosDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<ProductoDTODatos> recuperarTodosProductos();
    
    // Métodos de consultas de productos en inventairo
    public abstract ProductoInventarioDTODatos recuperarProductoInventarioPorId(IdProductoInventarioDTODatos idProductoInventarioDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeProductoInventarioPorId(IdProductoInventarioDTODatos idProductoInventarioDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract ProductoInventarioDTODatos actualizarProductoInventario(ActualizacionProductoInventarioDTODatos atualizacionProductoInventarioDTODatos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException;
    
    public abstract void agregarProductoInventario(ProductoInventarioDTODatos productoInventarioDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarColeccionProductosInventario(Collection<ProductoInventarioDTODatos> productosInventarioDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<ProductoInventarioDTODatos> recuperarPorIdProducto(IdProductoDTODatos idProductoDTODatos)
            throws ParametroNuloException, 
            FormatoIdInvalidoException;
    
    public abstract List<ProductoInventarioDTODatos> recuperarTodosProductosInventario();
    
    // Métodos de consultas de proveedores
    
    public abstract ProveedorDTODatos recuperarProveedorPorId(IdProveedorDTODatos idProveedorDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeProveedorPorId(IdProveedorDTODatos idProveedorDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarProveedor(ProveedorDTODatos proveedorDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarColeccionProveedores(Collection<ProveedorDTODatos> proveedoresDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<ProveedorDTODatos> recuperarTodosProveedores();
    
    
    // Métodos de consultas de sucursales
    
    public abstract SucursalDTODatos recuperarSucursalPorId(IdSucursalDTODatos idSucursalDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeSucursalPorId(IdSucursalDTODatos idSucursalDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException;
    
    public abstract void agregarSucursal(SucursalDTODatos sucursalDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract void agregarColeccionSucursales(Collection<SucursalDTODatos> sucursalesDTODatos)
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException,
            ValorParametroInvalidoException;
    
    public abstract List<SucursalDTODatos> recuperarTodosSucursales();
    
    public abstract SucursalDTODatos obtenerSucursalMatriz() throws RegistroInexistenteException;
    
}
