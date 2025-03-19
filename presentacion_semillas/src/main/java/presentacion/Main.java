
package presentacion;

import administradorProductos.FAdministradorProductos;
import administradorProductos.IAdministradorProductos;
import carritoCompras.FCarritoCompras;
import carritoCompras.ICarritoCompras;
import presentacion.interfaces.IInformacionProducto;
import presentacion.interfaces.IProductosVenta;
import presentacion.interfaces.ISeleccionPaqueteria;
import presentacion.interfaces.ICarrito;
import presentacion.interfaces.IMensaje;

public class Main {

    public static void main(String[] args) {
        
        Integer idCliente = 3;
        // Se crea el control con el contructor por defecto.
        ControlCompra controlCompra = new ControlCompra();
        
        // Se crean las vistas de la clase de presentación, del tipo de una interfaz definida.
        IProductosVenta productosVenta = new ProductosVenta(controlCompra, idCliente);
        IInformacionProducto informacionProducto = new InformacionProducto(controlCompra, idCliente);
        ISeleccionPaqueteria seleccionPaqueteria = new SeleccionPaqueteria(controlCompra);
        ICarrito carrito = new Carrito(controlCompra, idCliente);
        IMensaje mensaje = new Mensaje(controlCompra);
        
        // Se crean los subsistemas a utilziar:
        IAdministradorProductos subsistemaAdministradorProductos = new FAdministradorProductos();
        ICarritoCompras subsistemaCarritoCompras = new FCarritoCompras();
        
        // Se agregan las vistas creadas como atributos del 
        controlCompra.setVistas(productosVenta, informacionProducto, seleccionPaqueteria, carrito, mensaje, subsistemaAdministradorProductos, subsistemaCarritoCompras);
        controlCompra.iniciarCompra();
        
    }
}
