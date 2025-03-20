package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administradorProductos.FAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorProductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritoCompras.FCarritoCompras;
import edu.student.itson.dissof.megazarl.carritoCompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;

public class App {

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
