package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administradorclientes.FAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpedidos.FAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorproductos.FAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritocompras.FCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import java.util.Arrays;
import java.util.List;
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
            ISeleccionPaqueteria seleccionPaqueteria = new SeleccionPaqueteria(controlCompra, idCliente);
            ICarrito carrito = new Carrito(controlCompra, idCliente);
            IMensaje mensaje = new Mensaje(controlCompra);

            // Se crean los subsistemas a utilziar:
            List<Producto> listaProductos
                    = Arrays.asList(
                            new Producto(1, 3, "Sandía", "Summer Breeze", "Summer Breeze es una Sandia Triploide o sin semilla de madurez "
                                    + "intermedio precoz y buena capacidad y amarre de frutos de alta calidad para el mercado de exportacion.",
                                    5000, 9400d, 5d, "Seminis", "/sandiaSummerBreeze.png", "/seminis.png",
                                    Arrays.asList(new ProductoInventario(1, 1, 1, 4.5f, 10),
                                            new ProductoInventario(2, 1, 2, 2f, 3),
                                            new ProductoInventario(3, 1, 3, 10f, 0),
                                            new ProductoInventario(1, 1, 4, 3f, 0))),
                            new Producto(6, 1, "Chile", "Mixteco", "Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento."
                                    + " Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgada. Frutos de paredes gruesas con buen llenado. Variedad con "
                                    + "alto potencial de rendimiento, resistencia a BLS y planta vigorosa.", 25, 24300d, 7d, "Harris Moran",
                                    "/chileMixteco.png", "/harrisMoran.png", Arrays.asList(new ProductoInventario(6, 6, 1, 12.3f, 15),
                                            new ProductoInventario(7, 6, 2, 5.4f, 20), new ProductoInventario(8, 6, 3, 15f, 2),
                                            new ProductoInventario(9, 6, 4, 5.6f, 0))),
                            new Producto(2, 4, "Melón", "Híbrido Cruiser", "Semilla de melón híbrido Cruiser F1, de amplia adaptabilidad y altos rendimientos, "
                                    + "frutos grandes (9) y muy uniformes, de alta calidad de empaque. Mantiene tamaños en bajas temperaturas. Su pulpa es firme y crujiente "
                                    + "de excelente color. De madurez relativa precoz.", 10, 7200d, 2d, "Enza Zaden",
                                    "/melonHibridoCruiser.png", "/enzaZaden.png", Arrays.asList(new ProductoInventario(15, 2, 1, 10.4f, 0),
                                            new ProductoInventario(16, 2, 2, 5.5f, 1), new ProductoInventario(17, 2, 3, 4.5f, 3),
                                            new ProductoInventario(18, 2, 4, 2.3f, 12))));

            IAdministradorProductos subsistemaAdministradorProductos = new FAdministradorProductos(listaProductos);

            List<Cliente> listaClientes = Arrays.asList(
                    new Cliente(1, "Juan", "Pérez", "Hernández", "85000", "Guerrero", "200"),
                    new Cliente(2, "José", "Juárez", "Gastélum", "70000", "Tamaulipas", "150"),
                    new Cliente(3, "María", "Ibarra", "García", "24000", "Morelos", "1000"),
                    new Cliente(4, "Ana", "Martínez", "López", "30000", "Tabasco", "5034"),
                    new Cliente(5, "Ricardo", "González", "Gómez", "10000", "Miguel Alemán", "431"));
            IAdministradorClientes subsistemaAdministradorClientes = new FAdministradorClientes(listaClientes);

            IAdministradorPedidos subsAdministradorPedidos = new FAdministradorPedidos(subsistemaAdministradorProductos);

            ICarritoCompras subsistemaCarritoCompras = new FCarritoCompras((double) 100000, subsistemaAdministradorClientes, subsistemaAdministradorProductos,
                    subsAdministradorPedidos);

            // Se agregan las vistas creadas como atributos del 
            controlCompra.setVistas(productosVenta, informacionProducto, seleccionPaqueteria, carrito, mensaje,
                    subsistemaAdministradorProductos, subsistemaCarritoCompras, subsistemaAdministradorClientes);
            controlCompra.iniciarCompra();
        });
    }
}
