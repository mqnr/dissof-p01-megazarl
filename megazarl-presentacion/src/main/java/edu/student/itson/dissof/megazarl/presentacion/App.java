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
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.util.Arrays;
import java.util.HashMap;
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
            IVista productosVenta = new ProductosVenta(controlCompra, idCliente);
            IVista informacionProducto = new InformacionProducto(controlCompra, idCliente);
            IVista seleccionPaqueteria = new SeleccionPaqueteria(controlCompra, idCliente);
            IVista carrito = new Carrito(controlCompra, idCliente);
            IVista mensaje = new Mensaje(controlCompra, idCliente);

            // Se crean los subsistemas a utilziar:
            HashMap<Integer, Integer> mapaProductosDisponibilidad = new HashMap<>();
            
            mapaProductosDisponibilidad.put(1, 2);
            mapaProductosDisponibilidad.put(6, 4);
            mapaProductosDisponibilidad.put(2, 3);
            

            IAdministradorProductos subsistemaAdministradorProductos = new FAdministradorProductos(mapaProductosDisponibilidad);

            List<Cliente> listaClientes = Arrays.asList(
                new Cliente(
                        1, 
                        "Juan", 
                        "Pérez", 
                        "Hernández", 
                        "85000", 
                        "Guerrero", 
                        "200"),
                new Cliente(
                        2, 
                        "José", 
                        "Juárez", 
                        "Gastélum", 
                        "70000", 
                        "Tamaulipas", 
                        "150"),
                new Cliente(
                        3, 
                        "María", 
                        "Ibarra", 
                        "García", 
                        "24000", 
                        "Morelos", 
                        "1000"),
                new Cliente(
                        4, 
                        "Ana", 
                        "Martínez", 
                        "López", 
                        "30000", 
                        "Tabasco", 
                        "5034"),
                new Cliente(
                        5, 
                        "Ricardo", 
                        "González", 
                        "Gómez", 
                        "10000", 
                        "Miguel Alemán", 
                        "431")
            );
            
            IAdministradorClientes subsistemaAdministradorClientes = new FAdministradorClientes();

            IAdministradorPedidos subsAdministradorPedidos = new FAdministradorPedidos(subsistemaAdministradorProductos);

            ICarritoCompras subsistemaCarritoCompras = new FCarritoCompras((double) 100000, subsistemaAdministradorClientes, subsistemaAdministradorProductos,
                    subsAdministradorPedidos);

            // Se agregan las vistas creadas como atributos del control de presentación.
            controlCompra.setVistas(
                productosVenta, 
                informacionProducto, 
                seleccionPaqueteria, 
                carrito, 
                mensaje, 
                subsistemaAdministradorProductos, 
                subsistemaCarritoCompras, 
                subsistemaAdministradorClientes);
                controlCompra.iniciarCompra();
        });
    }
}
