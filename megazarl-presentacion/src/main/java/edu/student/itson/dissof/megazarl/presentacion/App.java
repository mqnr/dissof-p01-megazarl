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
import edu.student.itson.dissof.megazarl.carritocompras.FAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.IAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.direcciones.FDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.IDirecciones;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;
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
            
            //Creación de objetos a utilizar:
            List<Cliente> listaClientes = Arrays.asList(
                                new Cliente(
                                        3,
                                        "Juan", 
                                        "Pérez", 
                                        "González", 
                                        "85000", 
                                        "Guerrero", 
                                        "1586"));
            
            Sucursal sucursal1 = new Sucursal(
                                        1, 
                                        false, 
                                        4.5F, 
                                        "83566", 
                                        "Jardín de Agave", 
                                        "123");

            Sucursal sucursal2 = new Sucursal(
                                        2, 
                                        false, 
                                        3F, 
                                        "84303", 
                                        "Libertad y Progreso", 
                                        "5695");
            
            Sucursal sucursal3 = new Sucursal(
                                        3, 
                                        true, 
                                        0F, 
                                        "83380", 
                                        "Blvd. Fco. Eusebio Kino",
                                        "1000");
            
            Sucursal sucursal4 = new Sucursal(
                                        4, 
                                        false, 
                                        7.4F, 
                                        "85000", 
                                        "Guerrero",
                                        "200");
            
            List<Sucursal> listaSucursales = Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4);
            
            
            Proveedor proveedor1 = new Proveedor(10, "Seminis", "/seminis.png");
            Proveedor proveedor2 = new Proveedor(20, "Harris Moran", "/harrisMoran.png");
            Proveedor proveedor3 =  new Proveedor(30, "Enza Zaden","/enzaZaden.png");
            
            List<Proveedor> listaProveedores = Arrays.asList(proveedor1, proveedor2, proveedor3);
            
            List<Producto> listaProductos = Arrays.asList(
                                                new Producto(
                                                    1,
                                                    "Sandía",
                                                    "Summer Breeze",
                                                    """
                                                        Summer Breeze es una Sandia Triploide o sin semilla de madurez
                                                        intermedio precoz y buena capacidad y amarre de frutos de alta calidad para
                                                        el mercado de exportacion.
                                                    """,
                                                    5,
                                                    9400d,
                                                    5d,
                                                    "/sandiaSummerBreeze.png",
                                                    proveedor1,
                                                    Arrays.asList(
                                                            new ProductoInventario(1, sucursal1, false),
                                                            new ProductoInventario(2, sucursal1, false))),
            
                                                new Producto(
                                                    6,
                                                    "Chile",
                                                    "Mixteco",
                                                    """
                                                        Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento.
                                                        Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgada. Frutos de paredes gruesas 
                                                        con buen llenado. Variedad con alto potencial de rendimiento, resistencia a BLS y planta vigorosa.
                                                    """,
                                                    25,
                                                    24300d,
                                                    1d,
                                                    "/chileMixteco.png",
                                                    proveedor2,
                                                    Arrays.asList(
                                                            new ProductoInventario(6,sucursal1,  false),
                                                            new ProductoInventario(7,sucursal2,  false),
                                                            new ProductoInventario(8,sucursal3, false),
                                                            new ProductoInventario(9,sucursal4, false))),
                                                
                                                new Producto(
                                                    2,
                                                    "Melón",
                                                    "Híbrido Cruiser", 
                                                    """
                                                        Semilla de melón híbrido Cruiser F1, de amplia adaptabilidad y altos rendimientos,
                                                        frutos grandes y muy uniformes, de alta calidad de empaque. Mantiene tamaños en 
                                                        bajas temperaturas. Su pulpa es firme y crujiente de excelente color. De madurez 
                                                        relativa precoz.
                                                    """,        
                                                    10, 
                                                    7200d, 
                                                    2d,
                                                    "/melonHibridoCruiser.png",
                                                    proveedor3,
                                                    Arrays.asList(
                                                            new ProductoInventario(16,sucursal1, false),
                                                            new ProductoInventario(17,sucursal2, false),
                                                            new ProductoInventario(18,sucursal2, false)))
                                                );
            
            
            for(Producto producto: listaProductos){
                for(ProductoInventario productoInventario: producto.getListaProductoInventario()){
                    productoInventario.setProducto(producto);
                }
            }
            
            Paqueteria paqueteria1 = new Paqueteria(1, "DHL", 10F, 15F, "/dhl.png");
            Paqueteria paqueteria2 = new Paqueteria(2, "Fedex", 13F, 16F, "/fedex.png");
            Paqueteria paqueteria3 = new Paqueteria(3, "PCP", 8F, 7F, "/pcp.png");
            Paqueteria paqueteria4 = new Paqueteria(4, "UPS", 20F, 21F, "/ups.png");
            Paqueteria paqueteria5 = new Paqueteria(5, "Estafeta", 11F, 17F, "/estafeta.png");
            
            List<Paqueteria> listaPaqueterias = Arrays.asList(paqueteria1, paqueteria2, paqueteria3, paqueteria4, paqueteria5);

            // Se crean los subsistemas a utilziar:
            IDirecciones subsistemaDirecciones = new FDirecciones();
            
            IAdministradorClientes subsistemaAdministradorClientes = new FAdministradorClientes(subsistemaDirecciones, listaClientes);

            IAdministradorProductos subsistemaAdministradorProductos = new FAdministradorProductos(listaProductos);

            IAdministradorSucursales subsistemaAdministradorSucursales =  new FAdministradorSucursales(listaSucursales);
            
            IAdministradorPaqueterias subsistemaAdministradorPaqueterias = new FAdministradorPaqueterias(
                   listaPaqueterias,
                   subsistemaAdministradorClientes,
                   subsistemaAdministradorSucursales, 
                   subsistemaAdministradorProductos);
             
            
             IAdministradorPedidos subsistemaAdministradorPedidos = new FAdministradorPedidos(
                    subsistemaAdministradorProductos, 
                    subsistemaAdministradorClientes,
                    subsistemaAdministradorPaqueterias,
                    subsistemaAdministradorSucursales);
             
            IAdministradorCarritoCompras subsistemaAdministradorCarritoCompras = new FAdministradorCarritoCompras(
                    (double) 100000, 
                    subsistemaAdministradorClientes, 
                    subsistemaAdministradorProductos,
                    subsistemaAdministradorPedidos,
                    subsistemaAdministradorPaqueterias);
            
            
            IAdministradorProveedores subsistemaAdministradorProveedores = new FAdministradorProveedores(listaProveedores);
                
            
            ControlCompra controlCompra = new ControlCompra(
                    subsistemaAdministradorClientes, 
                    subsistemaAdministradorProductos,
                    subsistemaAdministradorCarritoCompras,
                    subsistemaAdministradorPaqueterias,
                    subsistemaAdministradorSucursales,
                    subsistemaAdministradorProveedores,
                    subsistemaDirecciones
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
