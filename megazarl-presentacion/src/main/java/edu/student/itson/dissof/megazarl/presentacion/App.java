package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.SucursalDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
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

            Long idCliente = 3L;

            // Creación de objetos a utilizar
            ClienteDTO cliente = new ClienteDTO(
                    idCliente,
                    "Juan",
                    "Pérez",
                    "González",
                    new DireccionDTO(
                            "85000", 
                            "Ciudad Obregón Centro", 
                            "Guerrero", 
                            "1586")
            );
            
            Cliente.agregar(cliente);


            SucursalDTO sucursal1 = new SucursalDTO(
                                        1L, 
                                        true,
                                        new DireccionDTO(
                                            "83118",
                                            "Parque Industrial Hermosillo Norte",
                                            "José Alberto Healy Noriega",
                                            "1000"
                                        )
            );
            
            SucursalDTO sucursal2 = new SucursalDTO(
                                        2L, 
                                        false,
                                        new DireccionDTO(
                                            "83557", 
                                            "San Rafael",
                                            "Río Mocorito", 
                                            "123"
                                        )
            );

            SucursalDTO sucursal3 = new SucursalDTO(
                                        3L, 
                                        false, 
                                        new DireccionDTO(
                                            "84269", 
                                            "Bicentenario",
                                            "44", 
                                            "5695"
                                        )
            );
            
            SucursalDTO sucursal4 = new SucursalDTO(
                                        4L, 
                                        false,
                                        new DireccionDTO(
                                            "85000",
                                            "Ciudad Obregón Centro",
                                            "Guerrero",
                                            "200"
                                        )
            );
            
            List<SucursalDTO> listaSucursales = Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4);

            Sucursal.agregar(listaSucursales);
            
            ProveedorDTO proveedor1 = new ProveedorDTO(
                    10L, 
                    "Seminis", 
                    "6441022785", 
                    "/seminis.png", 
                    new DireccionDTO(
                        "81255", 
                        "Zona Industrial", 
                        "Jiquilpan",
                        "3000")
            );
            
            ProveedorDTO proveedor2 = new ProveedorDTO(
                    20L, 
                    "Harris Moran", 
                    "6442365984", 
                    "/harrisMoran.png", 
                    new DireccionDTO(
                        "21394", 
                        "Venustiano Carranza", 
                        "De Las Misiones Norte",
                        "168")
            );
            
            ProveedorDTO proveedor3 =  new ProveedorDTO(
                    30L, 
                    "Enza Zaden",
                    "6442059876",
                    "/enzaZaden.png", 
                    new DireccionDTO(
                        "80393", 
                        "Parque Industrial El Trébol", 
                        "Carretera Federal 15D",
                        "450"));
            
            ProveedorDTO proveedor4 =  new ProveedorDTO(
                    40L, 
                    "Nunhems",
                    "6447856986", 
                    "/nunhems.png", 
                    new DireccionDTO(
                        "31385", 
                        "Parque Industrial Chihuahua Sur", 
                        "Paseos de Aura",
                        "1485"));
            
            ProveedorDTO proveedor5 =  new ProveedorDTO(
                    50L, 
                    "Lark Seeds",
                    "6442326587", 
                    "/larkSeeds.png", 
                    new DireccionDTO(
                        "37150", 
                        "Lomas del Campestre", 
                        "Blvd. Campestre",
                        "102")
            );
            
            List<ProveedorDTO> listaProveedores = Arrays.asList(proveedor1, proveedor2, proveedor3, proveedor4, proveedor5);
            
            Proveedor.agregar(listaProveedores);
            
            ProductoDTO producto1 = new ProductoDTO(
                        1L,
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
                        new LinkedList<>());
            
            ProductoDTO producto2 = new ProductoDTO(
                        6L,
                        "Chile Jalapeño",
                        "Mixteco F1",
                        """
                            Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento.
                            Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgada. Frutos de paredes gruesas 
                            con buen llenado. Variedad con alto potencial de rendimiento, resistencia a BLS y planta vigorosa.
                        """,
                        25,
                        24300d,
                        1d,
                        "/chileJalapenioMixtecoF1.png",
                        proveedor2,
                        new LinkedList<>());
            
            ProductoDTO producto3 = new ProductoDTO(
                        2L,
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
                        new LinkedList<>());
            
            ProductoDTO producto4 =  new ProductoDTO(
                        10L,
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
                        new LinkedList<>());

            ProductoDTO producto5 = new ProductoDTO(
                    12L,
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
                    new LinkedList<>());
            
            ProductoDTO producto6 = new ProductoDTO(
                    15L,
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
                    new LinkedList<>());
            
            ProductoDTO producto7 = new ProductoDTO(
                    18L,
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
                    new LinkedList<>());
            
            ProductoDTO producto8 =  new ProductoDTO(
                    20L,
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
                    new LinkedList<>());

            ProductoDTO producto9 = new ProductoDTO(
                    21L,
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
                    new LinkedList<>());
            
            ProductoDTO producto10 = new ProductoDTO(
                    23L,
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
                    new LinkedList<>());
            
            ProductoDTO producto11 = new ProductoDTO(
                    25L,
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
                    new LinkedList<>());
            
            ProductoDTO producto12 = new ProductoDTO(
                    28L,
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
            
            
            ProductoInventarioDTO productoInventario1 = new ProductoInventarioDTO(1L, producto1, sucursal1);
            ProductoInventarioDTO productoInventario2 = new ProductoInventarioDTO(2L, producto1, sucursal1);

            ProductoInventarioDTO productoInventario3 = new ProductoInventarioDTO(6L, producto2, sucursal1);
            ProductoInventarioDTO productoInventario4 = new ProductoInventarioDTO(7L, producto2, sucursal2);
            ProductoInventarioDTO productoInventario5 = new ProductoInventarioDTO(8L, producto2, sucursal3);
            ProductoInventarioDTO productoInventario6 = new ProductoInventarioDTO(9L, producto2, sucursal4);

            ProductoInventarioDTO productoInventario8 = new ProductoInventarioDTO(16L, producto3, sucursal2);
            ProductoInventarioDTO productoInventario9 = new ProductoInventarioDTO(17L, producto3, sucursal3);
            ProductoInventarioDTO productoInventario10 = new ProductoInventarioDTO(18L, producto3, sucursal4);

            ProductoInventarioDTO productoInventario11 = new ProductoInventarioDTO(20L, producto4, sucursal2);
            ProductoInventarioDTO productoInventario12 = new ProductoInventarioDTO(21L, producto4, sucursal3);
            ProductoInventarioDTO productoInventario13 = new ProductoInventarioDTO(22L, producto4, sucursal3);
            ProductoInventarioDTO productoInventario14 = new ProductoInventarioDTO(23L, producto4, sucursal4);
            ProductoInventarioDTO productoInventario15 = new ProductoInventarioDTO(24L, producto4, sucursal4);

            ProductoInventarioDTO productoInventario16 = new ProductoInventarioDTO(25L, producto5, sucursal1);
            ProductoInventarioDTO productoInventario17 = new ProductoInventarioDTO(26L, producto5, sucursal1);
            ProductoInventarioDTO productoInventario18 = new ProductoInventarioDTO(27L, producto5, sucursal1);

            ProductoInventarioDTO productoInventario19 = new ProductoInventarioDTO(30L, producto6, sucursal3);
            ProductoInventarioDTO productoInventario20 = new ProductoInventarioDTO(31L, producto6, sucursal1);

            ProductoInventarioDTO productoInventario21 = new ProductoInventarioDTO(35L, producto7, sucursal1);
            ProductoInventarioDTO productoInventario22 = new ProductoInventarioDTO(36L, producto7, sucursal4);
            ProductoInventarioDTO productoInventario23 = new ProductoInventarioDTO(37L, producto7, sucursal4);
            ProductoInventarioDTO productoInventario24 = new ProductoInventarioDTO(38L, producto7, sucursal2);
            ProductoInventarioDTO productoInventario25 = new ProductoInventarioDTO(39L, producto7, sucursal3);
            ProductoInventarioDTO productoInventario26 = new ProductoInventarioDTO(40L, producto7, sucursal2);
            ProductoInventarioDTO productoInventario27 = new ProductoInventarioDTO(41L, producto7, sucursal2);
            ProductoInventarioDTO productoInventario28 = new ProductoInventarioDTO(42L, producto7, sucursal4);

            ProductoInventarioDTO productoInventario29 = new ProductoInventarioDTO(45L, producto8, sucursal1);

            ProductoInventarioDTO productoInventario30 = new ProductoInventarioDTO(48L, producto9, sucursal1);
            ProductoInventarioDTO productoInventario31 = new ProductoInventarioDTO(49L, producto9, sucursal4);
            ProductoInventarioDTO productoInventario32 = new ProductoInventarioDTO(50L, producto9, sucursal4);
            ProductoInventarioDTO productoInventario33 = new ProductoInventarioDTO(51L, producto9, sucursal2);

            ProductoInventarioDTO productoInventario34 = new ProductoInventarioDTO(55L, producto10, sucursal1);
            ProductoInventarioDTO productoInventario35 = new ProductoInventarioDTO(56L, producto10, sucursal4);
            ProductoInventarioDTO productoInventario36 = new ProductoInventarioDTO(57L, producto10, sucursal4);
            ProductoInventarioDTO productoInventario37 = new ProductoInventarioDTO(58L, producto10, sucursal2);
            ProductoInventarioDTO productoInventario38 = new ProductoInventarioDTO(59L, producto10, sucursal3);
            ProductoInventarioDTO productoInventario39 = new ProductoInventarioDTO(60L, producto10, sucursal2);
            ProductoInventarioDTO productoInventario40 = new ProductoInventarioDTO(61L, producto10, sucursal1);
            ProductoInventarioDTO productoInventario41 = new ProductoInventarioDTO(62L, producto10, sucursal4);
            ProductoInventarioDTO productoInventario42 = new ProductoInventarioDTO(63L, producto10, sucursal3);
            ProductoInventarioDTO productoInventario43 = new ProductoInventarioDTO(64L, producto10, sucursal2);
            ProductoInventarioDTO productoInventario44 = new ProductoInventarioDTO(65L, producto10, sucursal1);
            ProductoInventarioDTO productoInventario45 = new ProductoInventarioDTO(66L, producto10, sucursal4);

            ProductoInventarioDTO productoInventario46 = new ProductoInventarioDTO(68L, producto11, sucursal2);
            ProductoInventarioDTO productoInventario47 = new ProductoInventarioDTO(69L, producto11, sucursal2);
            ProductoInventarioDTO productoInventario48 = new ProductoInventarioDTO(70L, producto11, sucursal2);

            ProductoInventarioDTO productoInventario49 = new ProductoInventarioDTO(71L, producto12, sucursal1);
            ProductoInventarioDTO productoInventario50 = new ProductoInventarioDTO(72L, producto12, sucursal4);
            
            
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
            
            Producto.agregar(listaProductos);
            
            PaqueteriaDTO paqueteria1 = new PaqueteriaDTO(
                    1L, 
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
                    2L, 
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
                    3L,
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
                    4L, 
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
                    5L, 
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

            
            ControlCompra controlCompra = new ControlCompra();
            
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
                direccion,
                100000D,
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
