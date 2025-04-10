package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.FAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorsucursales.FAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorpedidos.FAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorproductos.FAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritocompras.FAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.IAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventarioON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProveedorON;
import edu.student.itson.dissof.megazarl.objetosnegocio.SucursalON;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.repositorio.entidades.Cliente;
import edu.student.itson.dissof.megazarl.repositorio.entidades.Paqueteria;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

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
            inicializarConfiguracion(args);

            Integer idCliente = 3;

            // Creación de objetos a utilizar
            Cliente.guardar(
                    3,
                    "Juan",
                    "Pérez",
                    "González",
                    "85000",
                    "Guerrero",
                    "1586"
            );

            SucursalON sucursal1 = new SucursalON(
                                        1, 
                                        false, 
                                        4.5F, 
                                        "83566", 
                                        "Jardín de Agave", 
                                        "123");

            SucursalON sucursal2 = new SucursalON(
                                        2, 
                                        false, 
                                        3F, 
                                        "84303", 
                                        "Libertad y Progreso", 
                                        "5695");
            
            SucursalON sucursal3 = new SucursalON(
                                        3, 
                                        true, 
                                        0F, 
                                        "83380", 
                                        "Blvd. Fco. Eusebio Kino",
                                        "1000");
            
            SucursalON sucursal4 = new SucursalON(
                                        4, 
                                        false, 
                                        7.4F, 
                                        "85000", 
                                        "Guerrero",
                                        "200");
            
            List<SucursalON> listaSucursales = Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4);

            ProveedorON proveedor1 = new ProveedorON(10, "Seminis", "/seminis.png");
            ProveedorON proveedor2 = new ProveedorON(20, "Harris Moran", "/harrisMoran.png");
            ProveedorON proveedor3 =  new ProveedorON(30, "Enza Zaden","/enzaZaden.png");
            
            List<ProveedorON> listaProveedores = Arrays.asList(proveedor1, proveedor2, proveedor3);
            
            List<ProductoON> listaProductos = Arrays.asList(
                                                new ProductoON(
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
                                                            new ProductoInventarioON(1, sucursal1, false),
                                                            new ProductoInventarioON(2, sucursal1, false))),
            
                                                new ProductoON(
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
                                                            new ProductoInventarioON(6,sucursal1,  false),
                                                            new ProductoInventarioON(7,sucursal2,  false),
                                                            new ProductoInventarioON(8,sucursal3, false),
                                                            new ProductoInventarioON(9,sucursal4, false))),
                                                
                                                new ProductoON(
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
                                                            new ProductoInventarioON(16,sucursal1, false),
                                                            new ProductoInventarioON(17,sucursal2, false),
                                                            new ProductoInventarioON(18,sucursal2, false)))
                                                );

            for (ProductoON producto : listaProductos) {
                for (ProductoInventarioON productoInventario : producto.getListaProductoInventario()) {
                    productoInventario.setProducto(producto);
                }
            }

            Paqueteria.guardarMuchos(
                    List.of(
                            new PaqueteriaDTO(1, "DHL", 10F, 15F, "/dhl.png"),
                            new PaqueteriaDTO(2, "Fedex", 13F, 16F, "/fedex.png"),
                            new PaqueteriaDTO(3, "PCP", 8F, 7F, "/pcp.png"),
                            new PaqueteriaDTO(4, "UPS", 20F, 21F, "/ups.png"),
                            new PaqueteriaDTO(5, "Estafeta", 11F, 17F, "/estafeta.png")
                    )
            );

            // Se crean los subsistemas a utilziar:
            IAdministradorProductos subsistemaAdministradorProductos = new FAdministradorProductos(listaProductos);

            IAdministradorSucursales subsistemaAdministradorSucursales =  new FAdministradorSucursales(listaSucursales);

            IAdministradorPedidos subsistemaAdministradorPedidos = new FAdministradorPedidos(
                    subsistemaAdministradorProductos,
                    subsistemaAdministradorSucursales);
             
            IAdministradorCarritoCompras subsistemaAdministradorCarritoCompras = new FAdministradorCarritoCompras(
                    (double) 100000,
                    subsistemaAdministradorProductos,
                    subsistemaAdministradorPedidos);
            
            
            IAdministradorProveedores subsistemaAdministradorProveedores = new FAdministradorProveedores(listaProveedores);
                
            
            ControlCompra controlCompra = new ControlCompra(
                    subsistemaAdministradorProductos,
                    subsistemaAdministradorCarritoCompras,
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

    private static void inicializarConfiguracion(String[] argumentos) {
        try {
            ConfiguracionBandera bandera = resolverRutaConfiguracion(argumentos);
            // Si no se pasó la bandera
            if (bandera.forma == null) {
                ConfiguracionApp.INSTANCIA.inicializar();
            }
            else if (bandera.valor.isEmpty()) { // Si se pasó la bandera sin argumento
                logger.severe("La bandera \"" + bandera.forma + "\" necesita un argumento");
                System.exit(1);
            } else {
                ConfiguracionApp.INSTANCIA.inicializar(bandera.valor);
            }
        } catch (IOException e) {
            logger.severe("No se pudo inicializar la configuración: " + e.getMessage());
            System.exit(1);
        }
    }

    private record ConfiguracionBandera(String forma, String valor) {}

    private static ConfiguracionBandera resolverRutaConfiguracion(String[] argumentos) {
        for (int i = 0; i < argumentos.length; i++) {
            if (argumentos[i].equals("-c") || argumentos[i].equals("--configuracion")) {
                if (i == argumentos.length - 1) {
                    return new ConfiguracionBandera(null, "");
                }
                return new ConfiguracionBandera(argumentos[i], argumentos[i + 1]);
            }
        }
        return new ConfiguracionBandera(null, null);
    }
}
