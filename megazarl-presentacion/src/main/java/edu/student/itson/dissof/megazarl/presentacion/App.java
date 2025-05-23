package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.AuxiliarVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.Direccion;
import edu.student.itson.dissof.megazarl.objetosnegocio.GerenteVentas;
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
import java.util.Scanner;
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
        
            
            Scanner scanner = new Scanner(System.in);
        
            System.out.println("Selecciona una opción:");
            System.out.println("1. Comprar");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Registrar proveedor");
            System.out.println("4. Realizar orden de compra");
            
            System.out.print("Ingresa el número de la opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Creación de la dirección de envío del cliente
                    DireccionDTONegocios direccionEnvioCliente = new DireccionDTONegocios(
                                    "85000", 
                                    "Ciudad Obregón Centro", 
                                    "Guerrero", 
                                    "1586");

                    // Creación del DTO de cliente que ejecutará este caso de uso
                    ClienteDTONegocios cliente = new ClienteDatosCompletosRelacionesDTONegocios(
                            "Juan",
                            "Pérez",
                            "López",
                            "6441234567",
                            "juanperez@gmail.com",
                            direccionEnvioCliente
                    );

                    // Se registra el cliente utilizando la clase envolvente Cliente
                    Cliente.agregar(cliente);

                    // Se registra la dirección de envío del cliente utilizando la clase
                    // envolvente Direccion
                    Direccion.agregar(direccionEnvioCliente);


                    // Se crean los DTOs de las sucursales de la empresa y sus respectivas
                    // direcciones
                    DireccionDTONegocios direccionSucursal1 = new DireccionDTONegocios(
                                                    "83118",
                                                    "Parque Industrial Hermosillo Norte",
                                                    "José Alberto Healy Noriega",
                                                    "1000"
                    );

                    SucursalDTONegocios sucursal1 = new SucursalDatosCompletosRelacionesDTONegocios(
                                                true,
                                                direccionSucursal1,
                                                new LinkedList<>()
                    );

                    DireccionDTONegocios direccionSucursal2 = new DireccionDTONegocios(
                                                    "83557", 
                                                    "San Rafael",
                                                    "Río Mocorito", 
                                                    "123"
                    );

                    SucursalDTONegocios sucursal2 = new SucursalDatosCompletosRelacionesDTONegocios(
                                                false,
                                                direccionSucursal2,
                                                new LinkedList<>()
                    );

                    DireccionDTONegocios direccionSucursal3 = new DireccionDTONegocios(
                                                    "84269", 
                                                    "Bicentenario",
                                                    "44", 
                                                    "5695"
                   );

                    SucursalDTONegocios sucursal3 = new SucursalDatosCompletosRelacionesDTONegocios(
                                                false, 
                                                direccionSucursal3,
                                                new LinkedList<>()
                    );

                    DireccionDTONegocios direccionSucursal4 = new DireccionDTONegocios(
                                                    "85000",
                                                    "Ciudad Obregón Centro",
                                                    "Guerrero",
                                                    "200"
                    );

                    SucursalDTONegocios sucursal4 = new SucursalDatosCompletosRelacionesDTONegocios(
                                                false,
                                                direccionSucursal4,
                                                new LinkedList<>()
                    );

                    // Se crea una lista con las sucursales creadas y se guardan utilizando la
                    // clase envolvente Sucursal
                    List<SucursalDTONegocios> listaSucursales = Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4);
                    Sucursal.agregar(listaSucursales);

                    // Las direcciones de las sucursales creadas también son almacenadas
                    // utilizando la clase envolvente Direccion
                    List<DireccionDTONegocios> listaDireccionesSucursales = Arrays.asList(direccionSucursal1, direccionSucursal2, direccionSucursal3, direccionSucursal4);
                    Direccion.agregar(listaDireccionesSucursales);


                    // Se crean los DTOs de los proveedores de la empresa junto con
                    // sus respectivas direcciones
                    DireccionDTONegocios direccionProveedor1 = new DireccionDTONegocios(
                                "81255", 
                                "Zona Industrial", 
                                "Jiquilpan",
                                "3000");

                    ProveedorDTONegocios proveedor1 = new ProveedorDatosCompletosRelacionesDTONegocios(
                            "Seminis", 
                            "6441022785", 
                            "seminis@gmail.com",
                            "/seminis.png", 
                            new LinkedList<>(),
                            direccionProveedor1
                    );


                    DireccionDTONegocios direccionProveedor2 = new DireccionDTONegocios(
                                "21394", 
                                "Venustiano Carranza", 
                                "De Las Misiones Norte",
                                "168");

                    ProveedorDTONegocios proveedor2 = new ProveedorDatosCompletosRelacionesDTONegocios(
                            "Harris Moran", 
                            "6442365984",
                            "hmoran@gmail.com",
                            "/harrisMoran.png", 
                            new LinkedList<>(),
                            direccionProveedor2);


                    DireccionDTONegocios direccionProveedor3 = new DireccionDTONegocios(
                                "80393", 
                                "Parque Industrial El Trébol", 
                                "Carretera Federal 15D",
                                "450");

                    ProveedorDTONegocios proveedor3 =  new ProveedorDatosCompletosRelacionesDTONegocios(
                            "Enza Zaden",
                            "enzazaden@gmail.com",
                            "6442059876",
                            "/enzaZaden.png",
                            new LinkedList<>(),
                            direccionProveedor3);


                    DireccionDTONegocios direccionProveedor4 = new DireccionDTONegocios(
                                "31385", 
                                "Parque Industrial Chihuahua Sur", 
                                "Paseos de Aura",
                                "1485");

                    ProveedorDTONegocios proveedor4 =  new ProveedorDatosCompletosRelacionesDTONegocios(
                            "Nunhems",
                            "nunhmes@gmail.com",
                            "6447856986", 
                            "/nunhems.png", 
                            new LinkedList<>(),
                            direccionProveedor4);

                    DireccionDTONegocios direccionProveedor5 =
                                new DireccionDTONegocios(
                                "37150", 
                                "Lomas del Campestre", 
                                "Blvd. Campestre",
                                "102");

                    ProveedorDTONegocios proveedor5 =  new ProveedorDatosCompletosRelacionesDTONegocios(
                            "Lark Seeds",
                            "larkseeds@gmail.com",
                            "6442326587", 
                            "/larkSeeds.png", 
                            new LinkedList<>(),
                            direccionProveedor5
                    );

                    // Se guarda la lista de proveedores utilizando la clase envolvente
                    // Proveedor
                    List<ProveedorDTONegocios> listaProveedores = Arrays.asList(proveedor1, proveedor2, proveedor3, proveedor4, proveedor5);     
                    Proveedor.agregar(listaProveedores);

                    // Se guardan también sus direcciones con la clase envolvente Direccion
                    List<DireccionDTONegocios> listaDireccionesProveedores = Arrays.asList(
                            direccionProveedor1,
                            direccionProveedor2, 
                            direccionProveedor3, 
                            direccionProveedor4,
                            direccionProveedor5);
                    Direccion.agregar(listaDireccionesProveedores);


                    ProductoDTONegocios producto1 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                                new LinkedList<>(),
                                new LinkedList<>());

                    ProductoDTONegocios producto2 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                                new LinkedList<>(),
                                new LinkedList<>());

                    ProductoDTONegocios producto3 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                                new LinkedList<>(),
                                new LinkedList<>());

                    ProductoDTONegocios producto4 =  new ProductoDatosCompletosRelacionesDTONegocios(
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
                                new LinkedList<>(),
                                new LinkedList<>());

                    ProductoDTONegocios producto5 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto6 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto7 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto8 =  new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto9 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto10 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto11 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());

                    ProductoDTONegocios producto12 = new ProductoDatosCompletosRelacionesDTONegocios(
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
                            new LinkedList<>(),
                            new LinkedList<>());


                    List<ProductoDTONegocios> listaProductos = Arrays.asList(
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

                    ProductoInventarioDTONegocios productoInventario1 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto1, sucursal1);
                    ProductoInventarioDTONegocios productoInventario2 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto1, sucursal1);

                    ProductoInventarioDTONegocios productoInventario3 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto2, sucursal1);
                    ProductoInventarioDTONegocios productoInventario4 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto2, sucursal2);
                    ProductoInventarioDTONegocios productoInventario5 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto2, sucursal3);
                    ProductoInventarioDTONegocios productoInventario6 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto2, sucursal4);

                    ProductoInventarioDTONegocios productoInventario8 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto3, sucursal2);
                    ProductoInventarioDTONegocios productoInventario9 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto3, sucursal3);
                    ProductoInventarioDTONegocios productoInventario10 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto3, sucursal4);

                    ProductoInventarioDTONegocios productoInventario11 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto4, sucursal2);
                    ProductoInventarioDTONegocios productoInventario12 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto4, sucursal3);
                    ProductoInventarioDTONegocios productoInventario13 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto4, sucursal3);
                    ProductoInventarioDTONegocios productoInventario14 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto4, sucursal4);
                    ProductoInventarioDTONegocios productoInventario15 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto4, sucursal4);

                    ProductoInventarioDTONegocios productoInventario16 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto5, sucursal1);
                    ProductoInventarioDTONegocios productoInventario17 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto5, sucursal1);
                    ProductoInventarioDTONegocios productoInventario18 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto5, sucursal1);

                    ProductoInventarioDTONegocios productoInventario19 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto6, sucursal3);
                    ProductoInventarioDTONegocios productoInventario20 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto6, sucursal1);

                    ProductoInventarioDTONegocios productoInventario21 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal1);
                    ProductoInventarioDTONegocios productoInventario22 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal4);
                    ProductoInventarioDTONegocios productoInventario23 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal4);
                    ProductoInventarioDTONegocios productoInventario24 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal2);
                    ProductoInventarioDTONegocios productoInventario25 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal3);
                    ProductoInventarioDTONegocios productoInventario26 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal2);
                    ProductoInventarioDTONegocios productoInventario27 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal2);
                    ProductoInventarioDTONegocios productoInventario28 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto7, sucursal4);

                    ProductoInventarioDTONegocios productoInventario29 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto8, sucursal1);

                    ProductoInventarioDTONegocios productoInventario30 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto9, sucursal1);
                    ProductoInventarioDTONegocios productoInventario31 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto9, sucursal4);
                    ProductoInventarioDTONegocios productoInventario32 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto9, sucursal4);
                    ProductoInventarioDTONegocios productoInventario33 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto9, sucursal2);

                    ProductoInventarioDTONegocios productoInventario34 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal1);
                    ProductoInventarioDTONegocios productoInventario35 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal4);
                    ProductoInventarioDTONegocios productoInventario36 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal4);
                    ProductoInventarioDTONegocios productoInventario37 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal2);
                    ProductoInventarioDTONegocios productoInventario38 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal3);
                    ProductoInventarioDTONegocios productoInventario39 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal2);
                    ProductoInventarioDTONegocios productoInventario40 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal1);
                    ProductoInventarioDTONegocios productoInventario41 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal4);
                    ProductoInventarioDTONegocios productoInventario42 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal3);
                    ProductoInventarioDTONegocios productoInventario43 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal2);
                    ProductoInventarioDTONegocios productoInventario44 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal1);
                    ProductoInventarioDTONegocios productoInventario45 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto10, sucursal4);

                    ProductoInventarioDTONegocios productoInventario46 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto11, sucursal2);
                    ProductoInventarioDTONegocios productoInventario47 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto11, sucursal2);
                    ProductoInventarioDTONegocios productoInventario48 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto11, sucursal2);

                    ProductoInventarioDTONegocios productoInventario49 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto12, sucursal1);
                    ProductoInventarioDTONegocios productoInventario50 = new ProductoInventarioDatosCompletosRelacionesDTONegocios(false, producto12, sucursal4);


                    List<ProductoInventarioDTONegocios> listaProductosInventario = Arrays.asList(
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


                    DireccionDTONegocios direccionPaqueteria1 = new DireccionDTONegocios(
                                "83240",
                                "Fuentes del Mezquital", 
                                "Las Ceibas", 
                                "1247"
                    );
                    
                    // Se crean los DTO de las paqueterías asociadas con la empresa y son almacenadas
                    // utilizando la clase envolvente Paqueteria.
                    PaqueteriaDTONegocios paqueteria1 = new PaqueteriaDatosCompletosRelacionesDTONegocios(
                            "DHL", 
                            100F, 
                            150F, 
                            "/dhl.png",
                            direccionPaqueteria1
                    );

                    
                    DireccionDTONegocios direccionPaqueteria2 = new DireccionDTONegocios(
                                "83120",
                                "Los Viñedos",
                                "Cristobal Colón",
                                "257"
                    );
                    
                    PaqueteriaDTONegocios paqueteria2 = new PaqueteriaDatosCompletosRelacionesDTONegocios( 
                            "Fedex", 
                            120F, 
                            125F,
                            "/fedex.png",
                            direccionPaqueteria2     
                    );

                    DireccionDTONegocios direccionPaqueteria3 = new DireccionDTONegocios(
                                    "83200", 
                                    "Villa Satélite", 
                                    "Parroquia", 
                                    "659");
                    
                    PaqueteriaDTONegocios paqueteria3 = new PaqueteriaDatosCompletosRelacionesDTONegocios(
                            "PCP",
                            90F, 
                            100F,
                            "/pcp.png",
                            direccionPaqueteria3
                    );

                    
                    DireccionDTONegocios direccionPaqueteria4 = new DireccionDTONegocios(
                                "83288", 
                                "La Verbena",
                                "De los Panaderos",
                                "708"
                    );
                    
                    PaqueteriaDTONegocios paqueteria4  = new PaqueteriaDatosCompletosRelacionesDTONegocios( 
                            "UPS", 
                            106F,
                            110F,
                            "/ups.png",
                            direccionPaqueteria4

                    );
                    
                    DireccionDTONegocios dieccionPaqueteria5 = new DireccionDTONegocios(
                                "83280",
                                "La Candelaria",
                                "Real de San Pablo",
                                "1646"
                    );

                    PaqueteriaDTONegocios paqueteria5 =  new PaqueteriaDatosCompletosRelacionesDTONegocios(
                            "Estafeta", 
                            110F, 
                            105F,
                            "/estafeta.png",
                            dieccionPaqueteria5

                    );

                    List<PaqueteriaDTONegocios> listaPaqueterias = Arrays.asList(
                            paqueteria1,
                            paqueteria2,
                            paqueteria3,
                            paqueteria4,
                            paqueteria5);
                    
                    List<DireccionDTONegocios> listaDireccionesPaqueterias = Arrays.asList(
                            direccionPaqueteria1, 
                            direccionPaqueteria2, 
                            direccionPaqueteria3,
                            direccionPaqueteria4,
                            dieccionPaqueteria5);

                    Paqueteria.agregar(listaPaqueterias);
                    
                    Direccion.agregar(listaDireccionesPaqueterias);

                    // Se crea una instancia del control del caso de uso compra.
                    ControlCompra controlCompra = new ControlCompra();

                    Object idCliente = cliente.getId().getId();
                    // Se crean las vistas de la clase de presentación, del tipo de una interfaz definida.
                    IVista productosVenta = new ProductosVenta(controlCompra, idCliente);
                    IVista informacionProducto = new InformacionProducto(controlCompra, idCliente);
                    IVista seleccionPaqueteria = new SeleccionPaqueteria(controlCompra, idCliente);
                    IVista carrito = new Carrito(controlCompra, idCliente);
                    IMensaje mensaje = new Mensaje();
                    IVista direccion = new ActualizacionDireccion(controlCompra, idCliente);

                    // Se agregan las vistas creadas como atributos del control de presentación.
                    controlCompra.setVistas(productosVenta, 
                        informacionProducto, 
                        seleccionPaqueteria, 
                        carrito, 
                        mensaje, 
                        direccion,
                        150000D,
                        new DireccionDTONegocios(
                            "85136",
                            "Montecarlo",
                            "Génova",
                            "8455"));

                        controlCompra.iniciarCompra();

                    break;
                case 2:
                    
                    System.out.println("Elige quien ejecutará el Caso de Uso:");
                    System.out.println("1. Auxiliar de ventas");
                    System.out.println("2. Nuevo cliente");

                    System.out.print("Ingresa 1 o 2: ");
                    int opcionActor = scanner.nextInt();
                    
                    boolean usuarioEsAuxiliarVentas;
                    
                    if (opcionActor == 1) {
                        usuarioEsAuxiliarVentas = true;
                        
    
                        AuxiliarVentasDTONegocios auxiliarVentas1 = new AuxiliarVentasDTONegocios(
                                "María", 
                                "González",
                                "Juárez");

                        AuxiliarVentas.agregar(auxiliarVentas1);
                        
                        ClienteDTONegocios clienteRegistrado = new ClienteDatosCompletosRelacionesDTONegocios(
                                "Rodolfo Felix", 
                                "González", 
                                "Valle",
                                "6441234567", 
                                "rodolfoortiz@gmail.com",
                                null);
                        
                        Cliente.agregar(clienteRegistrado);
                        
                        ControlRegistroCliente controlRegistroCliente = new ControlRegistroCliente(usuarioEsAuxiliarVentas);
                         
                        IVista registroCliente = new RegistroCliente(
                                controlRegistroCliente, 
                                auxiliarVentas1.getId(), 
                                usuarioEsAuxiliarVentas);
                        
                        IMensaje mensajeRegistroUsuario = new Mensaje(); 
                        
                        controlRegistroCliente.setVistas(mensajeRegistroUsuario, registroCliente);
                        
                        controlRegistroCliente.iniciarRegistroCliente(usuarioEsAuxiliarVentas);
                        
                        
                    } else if (opcionActor == 2) {

                        ClienteDTONegocios clienteRegistrado = new ClienteDatosCompletosRelacionesDTONegocios(
                                "Rodolfo Felix", 
                                "González", 
                                "Valle",
                                "6441234567", 
                                "rodolfoortiz@gmail.com",
                                null);
                        
                        Cliente.agregar(clienteRegistrado);
                        
                        usuarioEsAuxiliarVentas = false;
                        
                        ControlRegistroCliente controlRegistroCliente = new ControlRegistroCliente(usuarioEsAuxiliarVentas);
                         
                        IVista registroCliente = new RegistroCliente(
                                controlRegistroCliente, 
                                null, 
                                usuarioEsAuxiliarVentas);
                        
                        IMensaje mensajeRegistroUsuario = new Mensaje(); 
                        
                        controlRegistroCliente.setVistas(mensajeRegistroUsuario, registroCliente);
                        
                        controlRegistroCliente.iniciarRegistroCliente(usuarioEsAuxiliarVentas);
                        
                    } else {
                        System.out.println("Opción no válida.");
                    }  
                    
                    
                    
                    break;
                case 3:
                    
                    ControlRegistroProveedor controlRegistroProveedor = new ControlRegistroProveedor();
                    IVista registroProveedor = new RegistroProveedor(controlRegistroProveedor);
                    IMensaje mensajeRegistroProveedor = new Mensaje();
 
                    controlRegistroProveedor.setVistas(registroProveedor, mensajeRegistroProveedor);
                    
                    controlRegistroProveedor.iniciarRegistroProveedor();
                    
                    break;
                    
                case 4:
                    
                    System.out.println("Ejecutando Registro de orden de compra");
    
                    GerenteVentasDTONegocios gerenteVentas1 = new GerenteVentasDTONegocios(
                                "Andrés", 
                                "Gutiérrez",
                                "Mendoza");
                        GerenteVentas.agregar(gerenteVentas1);
                    
                    ControlOrdenCompra controlOrdenCompra = new ControlOrdenCompra();
                    
                    IVista ordenCompra = new OrdenCompra(controlOrdenCompra, gerenteVentas1.getId());
                    
                    IMensaje mensajeOrdenCompra = new Mensaje();
                    
                    controlOrdenCompra.setVistas(mensajeOrdenCompra, ordenCompra);
                    
                    controlOrdenCompra.iniciarOrdenCompra();
                    
                    break;
                    
                case 5:
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        
            scanner.close();
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
