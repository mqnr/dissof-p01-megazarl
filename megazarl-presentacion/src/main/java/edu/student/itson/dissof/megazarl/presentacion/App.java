package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.Direccion;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
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

            // Creación de la dirección de envío del cliente
            DireccionDTO direccionEnvioCliente = new DireccionDTO(
                            "85000", 
                            "Ciudad Obregón Centro", 
                            "Guerrero", 
                            "1586");
            
            // Creación del DTO de cliente que ejecutará este caso de uso
            ClienteDTO cliente = new ClienteDTO(
                    "Juan",
                    "Pérez",
                    "López",
                    direccionEnvioCliente
            );
            
            // Se registra el cliente utilizando la clase envolvente Cliente
            Cliente.agregar(cliente);
            
            // Se registra la dirección de envío del cliente utilizando la clase
            // envolvente Direccion
            Direccion.agregar(direccionEnvioCliente);

            
            // Se crean los DTOs de las sucursales de la empresa y sus respectivas
            // direcciones
            DireccionDTO direccionSucursal1 = new DireccionDTO(
                                            "83118",
                                            "Parque Industrial Hermosillo Norte",
                                            "José Alberto Healy Noriega",
                                            "1000"
                                        );
            
            SucursalDTO sucursal1 = new SucursalDTO(
                                        true,
                                        direccionSucursal1
            );
            
            DireccionDTO direccionSucursal2 = new DireccionDTO(
                                            "83557", 
                                            "San Rafael",
                                            "Río Mocorito", 
                                            "123"
                                        );
            
            SucursalDTO sucursal2 = new SucursalDTO(
                                        false,
                                        direccionSucursal2
            );

            DireccionDTO direccionSucursal3 = new DireccionDTO(
                                            "84269", 
                                            "Bicentenario",
                                            "44", 
                                            "5695"
                                        );
                    
            SucursalDTO sucursal3 = new SucursalDTO(
                                        false, 
                                        direccionSucursal3
            );
            
            DireccionDTO direccionSucursal4 = new DireccionDTO(
                                            "85000",
                                            "Ciudad Obregón Centro",
                                            "Guerrero",
                                            "200"
                                        );
            
            SucursalDTO sucursal4 = new SucursalDTO(
                                        false,
                                        direccionSucursal4
            );
            
            // Se crea una lista con las sucursales creadas y se guardan utilizando la
            // clase envolvente Sucursal
            List<SucursalDTO> listaSucursales = Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4);
            Sucursal.agregar(listaSucursales);
            
            // Las direcciones de las sucursales creadas también son almacenadas
            // utilizando la clase envolvente Direccion
            List<DireccionDTO> listaDireccionesSucursales = Arrays.asList(direccionSucursal1, direccionSucursal2, direccionSucursal3, direccionSucursal4);
            Direccion.agregar(listaDireccionesSucursales);
            
            
            // Se crean los DTOs de los proveedores de la empresa junto con
            // sus respectivas direcciones
            DireccionDTO direccionProveedor1 = new DireccionDTO(
                        "81255", 
                        "Zona Industrial", 
                        "Jiquilpan",
                        "3000");
            
            ProveedorDTO proveedor1 = new ProveedorDTO(
                    "Seminis", 
                    "6441022785", 
                    "seminis@gmail.com",
                    "/seminis.png", 
                    new LinkedList<>(),
                    direccionProveedor1
            );
            
            
            DireccionDTO direccionProveedor2 = new DireccionDTO(
                        "21394", 
                        "Venustiano Carranza", 
                        "De Las Misiones Norte",
                        "168");
            
            ProveedorDTO proveedor2 = new ProveedorDTO(
                    "Harris Moran", 
                    "6442365984",
                    "hmoran@gmail.com",
                    "/harrisMoran.png", 
                    new LinkedList<>(),
                    direccionProveedor2);
            
            
            DireccionDTO direccionProveedor3 = new DireccionDTO(
                        "80393", 
                        "Parque Industrial El Trébol", 
                        "Carretera Federal 15D",
                        "450");
            
            ProveedorDTO proveedor3 =  new ProveedorDTO(
                    "Enza Zaden",
                    "enzazaden@gmail.com",
                    "6442059876",
                    "/enzaZaden.png",
                    new LinkedList<>(),
                    direccionProveedor3);
            
            
            DireccionDTO direccionProveedor4 = new DireccionDTO(
                        "31385", 
                        "Parque Industrial Chihuahua Sur", 
                        "Paseos de Aura",
                        "1485");
            
            ProveedorDTO proveedor4 =  new ProveedorDTO(
                    "Nunhems",
                    "nunhmes@gmail.com",
                    "6447856986", 
                    "/nunhems.png", 
                    new LinkedList<>(),
                    direccionProveedor4);
            
            DireccionDTO direccionProveedor5 =
                        new DireccionDTO(
                        "37150", 
                        "Lomas del Campestre", 
                        "Blvd. Campestre",
                        "102");
            
            ProveedorDTO proveedor5 =  new ProveedorDTO(
                    "Lark Seeds",
                    "larkseeds@gmail.com",
                    "6442326587", 
                    "/larkSeeds.png", 
                    new LinkedList<>(),
                    direccionProveedor5
            );
            
            // Se guarda la lista de proveedores utilizando la clase envolvente
            // Proveedor
            List<ProveedorDTO> listaProveedores = Arrays.asList(proveedor1, proveedor2, proveedor3, proveedor4, proveedor5);     
            Proveedor.agregar(listaProveedores);
            
            // Se guardan también sus direcciones con la clase envolvente Direccion
            List<DireccionDTO> listaDireccionesProveedores = Arrays.asList(direccionProveedor1, direccionProveedor2, direccionProveedor3, direccionProveedor4);
            Direccion.agregar(listaDireccionesProveedores);
            
            
            ProductoDTO producto1 = new ProductoDTO(
                        "Sandía",
                        "Summer Breeze",
                        """
                            Summer Breeze es una Sandia Triploide o sin semilla de madurez
                            intermedio precoz y buena capacidad y amarre de frutos de alta calidad para
                            el mercado de exportacion.
                        """,
                        5,
                        9400d,
                        1d,
                        "/sandiaSummerBreeze.png",
                        proveedor1,
                        new LinkedList<>(),
                        new LinkedList<>());
            
            ProductoDTO producto2 = new ProductoDTO(
                        "Chile Jalapeño",
                        "Mixteco F1",
                        """
                            Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento.
                            Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgadas. Frutos de paredes gruesas 
                            con buen llenado. Variedad con alto potencial de rendimiento, resistencia a BLS y planta vigorosa.
                        """,
                        25,
                        24300d,
                        0.7d,
                        "/chileJalapenioMixtecoF1.png",
                        proveedor2,
                        new LinkedList<>(),
                        new LinkedList<>());
            
            ProductoDTO producto3 = new ProductoDTO(
                        "Melón",
                        "Cruiser F1", 
                        """
                            Semilla de melón híbrido Cruiser F1, de amplia adaptabilidad y altos rendimientos,
                            frutos grandes y muy uniformes, de alta calidad de empaque. Mantiene tamaños en 
                            bajas temperaturas. Su pulpa es firme y crujiente de excelente color. De madurez 
                            relativa precoz.
                        """,        
                        10, 
                        7200d, 
                        2d,
                        "/melonCruiserF1.png",
                        proveedor3,
                        new LinkedList<>(),
                        new LinkedList<>());
            
            ProductoDTO producto4 =  new ProductoDTO(
                        "Chile Ancho",
                        "Commander",
                        """
                            De porte alto, fuerte y vigoroso con buena cobertura foliar y excelente concentración de frutos.
                            Commander presenta frutos verde oscuro altamente uniformes y con predominancia de dos venas, 
                            con paredes gruesas que Ie aportan mayor peso especifico a cada uno de ellos, su maduración es 
                            a color chocolate.
                        """,
                        10,
                        12000d,
                        2.5d,
                        "/chileAnchoCommander.png",
                        proveedor3,
                        new LinkedList<>(),
                        new LinkedList<>());

            ProductoDTO producto5 = new ProductoDTO(
                    "Lechuga Romana",
                    "Plutone",
                    """
                        La semilla de lechuga romana Plutone está diseñada para cultivos de alta densidad, 
                        brindando una producción eficiente y sostenible. Su resistencia a enfermedades comunes asegura 
                        plantas robustas y lechugas con una textura crujiente y sabor excepcional.
                    """,
                    13,
                    25000d,
                    3d,
                    "/lechugaRomanaPlutone.png",
                    proveedor5,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            ProductoDTO producto6 = new ProductoDTO(
                    "Calabaza",
                    "Rocio F1",
                    """
                        La Calabaza Rocío F1 es una variedad híbrida que destaca por su alta adaptabilidad y rendimiento. 
                        Su planta mediana y abierta facilita una cosecha simple y sin cicatrices, mientras que sus frutos 
                        cilíndricos exhiben una calidad extraordinaria.
                    """,
                    3,
                    6500d,
                    1d,
                    "/calabazaRocioF1.png",
                    proveedor4,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            ProductoDTO producto7 = new ProductoDTO(
                    "Tomate",
                    "Succesion",
                    """
                        Tomate híbrido bola indeterminado. Ideal para cosecha en racimo de cinco
                        frutos de tamaño mediano a grande muy uniforme; aún en bajas
                        condiciones de luz, los racimos tienen una maduración uniforme.
                        Excelente sabor con larga vida de anaquel.
                    """,
                    5,
                    7000d,
                    1d,
                    "/tomateSuccession.png",
                    proveedor1,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            ProductoDTO producto8 =  new ProductoDTO(
                    "Rábano",
                    "Red Diamond F1",
                    """
                        El Rábano Red Diamond es una variedad híbrida F1 reconocida 
                        por su intensa tonalidad roja y su textura crujiente. Gracias a su alta tasa de 
                        germinación, garantiza uniformidad en tamaño y forma, lo que lo hace ideal para 
                        productores profesionales y huertos familiares.
                    """,
                    50,
                    1890d,
                    0.45d,
                    "/rabanoRedDiamondF1.png",
                    proveedor2,
                    new LinkedList<>(),
                    new LinkedList<>());

            ProductoDTO producto9 = new ProductoDTO(
                    "Sandía",
                    "Warrior",
                    """
                        Las semillas de la variedad Warrior representan la innovación agrícola de Nunhems,
                        diseñadas para brindar a los productores una opción de alto rendimiento y calidad
                        superior. Destaca por su maduración temprana, lo que se traduce en una cosecha más 
                        rápida y eficiente, ideal para mercados que demandan una respuesta ágil y de excelente calidad.
                    """,
                    5,
                    9650d,
                    5d,
                    "/sandiaWarrior.png",
                    proveedor4,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            ProductoDTO producto10 = new ProductoDTO(
                    "Sandía",
                    "Tailgate",
                    """
                        Tailgate es una sandia triploide o sin semilla de cascara lisa con bandas verde 
                        medio y bandas verde oscuro. Es una madurez intermedio precoz con buena capacidad 
                        de amarre de frutos grandes muy uniformes y potencial de producir rendimiento alto.
                    """,
                    7,
                    9000d,
                    7d,
                    "/sandiaTailgate.png",
                    proveedor1,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            ProductoDTO producto11 = new ProductoDTO(
                    "Brócoli",
                    "Kepler F1",
                    """
                        El Brócoli Kepler F1 es un híbrido de alta calidad, diseñado para el mercado fresco 
                        y de procesamiento. Destaca por sus cabezas uniformes de vívido color verde, excelente 
                        sabor y resistencia a enfermedades, ofreciendo un rendimiento constante y óptimo para 
                        el cultivo.
                    """,
                    25,
                    3640d,
                    5d,
                    "/brocoliKeplerF1.png",
                    proveedor2,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            ProductoDTO producto12 = new ProductoDTO(
                    "Melón",
                    "Saturno",
                    """
                        Melón tipo Honeydew suave y diseñado para principios de temporada. Presenta frutos 
                        de forma redonda a ovalada con cavidad pequeña. Los productores de Saturno pueden 
                        esperar buen sabor y alto nivel de azúcar con una piel fina y alto potencial de rendimiento.
                    """,
                    15,
                    10000d,
                    3d,
                    "/melonSaturno.png",
                    proveedor1,
                    new LinkedList<>(),
                    new LinkedList<>());
            
            
            List<ProductoDTO> listaProductos = Arrays.asList(
                    producto1,
                    producto2,
                    producto3,
                    producto4,
                    producto5,
                    producto6,
                    producto7,
                    producto8,
                    producto9,
                    producto10,
                    producto11,
                    producto12
            );
            
            Producto.agregar(listaProductos);
            
            
            // Se crean e insertan los DTOs de productos en inventario disponibles,
            // se registran utilizando la clase envolvente ProductoInventario
            
            ProductoInventarioDTO productoInventario1 = new ProductoInventarioDTO(producto1, sucursal1);
            ProductoInventarioDTO productoInventario2 = new ProductoInventarioDTO(producto1, sucursal1);

            ProductoInventarioDTO productoInventario3 = new ProductoInventarioDTO(producto2, sucursal1);
            ProductoInventarioDTO productoInventario4 = new ProductoInventarioDTO(producto2, sucursal2);
            ProductoInventarioDTO productoInventario5 = new ProductoInventarioDTO(producto2, sucursal3);
            ProductoInventarioDTO productoInventario6 = new ProductoInventarioDTO(producto2, sucursal4);

            ProductoInventarioDTO productoInventario8 = new ProductoInventarioDTO(producto3, sucursal2);
            ProductoInventarioDTO productoInventario9 = new ProductoInventarioDTO(producto3, sucursal3);
            ProductoInventarioDTO productoInventario10 = new ProductoInventarioDTO(producto3, sucursal4);

            ProductoInventarioDTO productoInventario11 = new ProductoInventarioDTO(producto4, sucursal2);
            ProductoInventarioDTO productoInventario12 = new ProductoInventarioDTO(producto4, sucursal3);
            ProductoInventarioDTO productoInventario13 = new ProductoInventarioDTO(producto4, sucursal3);
            ProductoInventarioDTO productoInventario14 = new ProductoInventarioDTO(producto4, sucursal4);
            ProductoInventarioDTO productoInventario15 = new ProductoInventarioDTO(producto4, sucursal4);

            ProductoInventarioDTO productoInventario16 = new ProductoInventarioDTO(producto5, sucursal1);
            ProductoInventarioDTO productoInventario17 = new ProductoInventarioDTO(producto5, sucursal1);
            ProductoInventarioDTO productoInventario18 = new ProductoInventarioDTO(producto5, sucursal1);

            ProductoInventarioDTO productoInventario19 = new ProductoInventarioDTO(producto6, sucursal3);
            ProductoInventarioDTO productoInventario20 = new ProductoInventarioDTO(producto6, sucursal1);

            ProductoInventarioDTO productoInventario21 = new ProductoInventarioDTO(producto7, sucursal1);
            ProductoInventarioDTO productoInventario22 = new ProductoInventarioDTO(producto7, sucursal4);
            ProductoInventarioDTO productoInventario23 = new ProductoInventarioDTO(producto7, sucursal4);
            ProductoInventarioDTO productoInventario24 = new ProductoInventarioDTO(producto7, sucursal2);
            ProductoInventarioDTO productoInventario25 = new ProductoInventarioDTO(producto7, sucursal3);
            ProductoInventarioDTO productoInventario26 = new ProductoInventarioDTO(producto7, sucursal2);
            ProductoInventarioDTO productoInventario27 = new ProductoInventarioDTO(producto7, sucursal2);
            ProductoInventarioDTO productoInventario28 = new ProductoInventarioDTO(producto7, sucursal4);

            ProductoInventarioDTO productoInventario29 = new ProductoInventarioDTO(producto8, sucursal1);

            ProductoInventarioDTO productoInventario30 = new ProductoInventarioDTO(producto9, sucursal1);
            ProductoInventarioDTO productoInventario31 = new ProductoInventarioDTO(producto9, sucursal4);
            ProductoInventarioDTO productoInventario32 = new ProductoInventarioDTO(producto9, sucursal4);
            ProductoInventarioDTO productoInventario33 = new ProductoInventarioDTO(producto9, sucursal2);

            ProductoInventarioDTO productoInventario34 = new ProductoInventarioDTO(producto10, sucursal1);
            ProductoInventarioDTO productoInventario35 = new ProductoInventarioDTO(producto10, sucursal4);
            ProductoInventarioDTO productoInventario36 = new ProductoInventarioDTO(producto10, sucursal4);
            ProductoInventarioDTO productoInventario37 = new ProductoInventarioDTO(producto10, sucursal2);
            ProductoInventarioDTO productoInventario38 = new ProductoInventarioDTO(producto10, sucursal3);
            ProductoInventarioDTO productoInventario39 = new ProductoInventarioDTO(producto10, sucursal2);
            ProductoInventarioDTO productoInventario40 = new ProductoInventarioDTO(producto10, sucursal1);
            ProductoInventarioDTO productoInventario41 = new ProductoInventarioDTO(producto10, sucursal4);
            ProductoInventarioDTO productoInventario42 = new ProductoInventarioDTO(producto10, sucursal3);
            ProductoInventarioDTO productoInventario43 = new ProductoInventarioDTO(producto10, sucursal2);
            ProductoInventarioDTO productoInventario44 = new ProductoInventarioDTO(producto10, sucursal1);
            ProductoInventarioDTO productoInventario45 = new ProductoInventarioDTO(producto10, sucursal4);

            ProductoInventarioDTO productoInventario46 = new ProductoInventarioDTO(producto11, sucursal2);
            ProductoInventarioDTO productoInventario47 = new ProductoInventarioDTO(producto11, sucursal2);
            ProductoInventarioDTO productoInventario48 = new ProductoInventarioDTO(producto11, sucursal2);

            ProductoInventarioDTO productoInventario49 = new ProductoInventarioDTO(producto12, sucursal1);
            ProductoInventarioDTO productoInventario50 = new ProductoInventarioDTO(producto12, sucursal4);
            
            
            List<ProductoInventarioDTO> listaProductosInventario = Arrays.asList(
                    productoInventario1,
                    productoInventario2,

                    productoInventario3,
                    productoInventario4,
                    productoInventario5,
                    productoInventario6,

                    productoInventario8,
                    productoInventario9,
                    productoInventario10,

                    productoInventario11,
                    productoInventario12,
                    productoInventario13,
                    productoInventario14,
                    productoInventario15,

                    productoInventario16,
                    productoInventario17,
                    productoInventario18,

                    productoInventario19,
                    productoInventario20,

                    productoInventario21,
                    productoInventario22,
                    productoInventario23,
                    productoInventario24,
                    productoInventario25,
                    productoInventario26,
                    productoInventario27,
                    productoInventario28,

                    productoInventario29,

                    productoInventario30,
                    productoInventario31,
                    productoInventario32,
                    productoInventario33,

                    productoInventario34,
                    productoInventario35,
                    productoInventario36,
                    productoInventario37,
                    productoInventario38,
                    productoInventario39,
                    productoInventario40,
                    productoInventario41,
                    productoInventario42,
                    productoInventario43,
                    productoInventario44,
                    productoInventario45,

                    productoInventario46,
                    productoInventario47,
                    productoInventario48,

                    productoInventario49,
                    productoInventario50
            );
            
            
            ProductoInventario.agregar(listaProductosInventario);
            
            
            // Se crean los DTO de las paqueterías asociadas con la empresa y son almacenadas
            // utilizando la clase envolvente Paqueteria.
            PaqueteriaDTO paqueteria1 = new PaqueteriaDTO(
                    "DHL", 
                    100F, 
                    150F, 
                    "/dhl.png",
                    new DireccionDTO(
                        "83240",
                        "Fuentes del Mezquital", 
                        "Las Ceibas", 
                        "1247"
                    )
            );

            PaqueteriaDTO paqueteria2 = new PaqueteriaDTO( 
                    "Fedex", 
                    120F, 
                    125F,
                    "/fedex.png",
                    new DireccionDTO(
                        "83120",
                        "Los Viñedos",
                        "Cristobal Colón",
                        "257"
                    )
            );

            PaqueteriaDTO paqueteria3 = new PaqueteriaDTO(
                    "PCP",
                    90F, 
                    100F,
                    "/pcp.png",
                    new DireccionDTO(
                            "83200", 
                            "Villa Satélite", 
                            "Parroquia", 
                            "659")
            );
            
            PaqueteriaDTO paqueteria4  = new PaqueteriaDTO( 
                    "UPS", 
                    106F,
                    110F,
                    "/ups.png",
                    new DireccionDTO(
                        "83288", 
                        "La Verbena",
                        "De los Panaderos",
                        "708"
                    )
                    
            );
             
            PaqueteriaDTO paqueteria5 =  new PaqueteriaDTO(
                    "Estafeta", 
                    110F, 
                    105F,
                    "/estafeta.png",
                    new DireccionDTO(
                        "83280",
                        "La Candelaria",
                        "Real de San Pablo",
                        "1646"
                    )
                    
            );
            
            List<PaqueteriaDTO> listaPaqueterias = Arrays.asList(
                    paqueteria1,
                    paqueteria2,
                    paqueteria3,
                    paqueteria4,
                    paqueteria5);

            Paqueteria.agregar(listaPaqueterias);

            // Se crea una instancia del control del caso de uso compra.
            ControlCompra controlCompra = new ControlCompra();
            
            Long idCliente = cliente.getId();
            // Se crean las vistas de la clase de presentación, del tipo de una interfaz definida.
            IVista productosVenta = new ProductosVenta(controlCompra, idCliente);
            IVista informacionProducto = new InformacionProducto(controlCompra, idCliente);
            IVista seleccionPaqueteria = new SeleccionPaqueteria(controlCompra, idCliente);
            IVista carrito = new Carrito(controlCompra, idCliente);
            IMensaje mensaje = new Mensaje(controlCompra, idCliente);
            IVista direccion = new ActualizacionDireccion(controlCompra, idCliente);
            
            // Se agregan las vistas creadas como atributos del control de presentación.
            controlCompra.setVistas(
                productosVenta, 
                informacionProducto, 
                seleccionPaqueteria, 
                carrito, 
                mensaje, 
                direccion,
                150000D,
                new DireccionDTO(
                    "85136",
                    "Montecarlo",
                    "Génova",
                    "8455"));
                
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
