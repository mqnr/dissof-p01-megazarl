package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administradorproductos.FAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritocompras.FCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo cargar el tema del sistema. Se usará el tema por defecto.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
        SwingUtilities.invokeLater(() -> {
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
        });
    }
}
