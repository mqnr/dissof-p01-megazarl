package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.FAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorsucursales.FAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorclientes.FAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.FAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpedidos.FAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorproductos.FAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritocompras.FCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;

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

            // Se crean los subsistemas a utilziar:
            List<Integer> listaCodigosProductos = Arrays.asList(1,6,2);
            List<Integer> listaCantidadProductosInventario = Arrays.asList(2,4,3);
            List<List<Boolean>> listaFechasHora = Arrays.asList(
                    Arrays.asList(false, false),
                    Arrays.asList(false, false,false, false),
                    Arrays.asList(false, false,false)
            
            );
            
            IAdministradorClientes subsistemaAdministradorClientes = new FAdministradorClientes();

            IAdministradorProductos subsistemaAdministradorProductos = new FAdministradorProductos(
                listaCodigosProductos, 
                listaCantidadProductosInventario,
                listaFechasHora);

            IAdministradorPedidos subsAdministradorPedidos = new FAdministradorPedidos(subsistemaAdministradorProductos);

            ICarritoCompras subsistemaCarritoCompras = new FCarritoCompras(
                    (double) 100000, 
                    subsistemaAdministradorClientes, 
                    subsistemaAdministradorProductos,
                    subsAdministradorPedidos);

            IAdministradorSucursales subsistemaAdministradorSucursales =  new FAdministradorSucursales();
            
            IAdministradorPaqueterias subsistemaAdministradorPaqueterias = new FAdministradorPaqueterias(
                    subsistemaAdministradorClientes,
                    subsistemaAdministradorSucursales, 
                    subsistemaAdministradorProductos);
            
            IAdministradorProveedores subsistemaAdministradorProveedores = new FAdministradorProveedores();
                
            
            ControlCompra controlCompra = new ControlCompra(
                    subsistemaAdministradorClientes, 
                    subsistemaAdministradorProductos,
                    subsistemaCarritoCompras,
                    subsistemaAdministradorPaqueterias,
                    subsistemaAdministradorSucursales,
                    subsistemaAdministradorProveedores
            );
            
            // Se crean las vistas de la clase de presentación, del tipo de una interfaz definida.
            IVista productosVenta = new ProductosVenta(controlCompra, idCliente);
            IVista informacionProducto = new InformacionProducto(controlCompra, idCliente);
            IVista seleccionPaqueteria = new SeleccionPaqueteria(controlCompra, idCliente);
            IVista carrito = new Carrito(controlCompra, idCliente);
            IMensaje mensaje = new Mensaje(controlCompra, idCliente);
            IVista direccion = new Direccion(controlCompra, idCliente);
            
            // Se agregan las vistas creadas como atributos del control de presentación.
            controlCompra.setVistas(
                productosVenta, 
                informacionProducto, 
                seleccionPaqueteria, 
                carrito, 
                mensaje, 
                direccion);
                
                controlCompra.iniciarCompra();
        });
    }
}
