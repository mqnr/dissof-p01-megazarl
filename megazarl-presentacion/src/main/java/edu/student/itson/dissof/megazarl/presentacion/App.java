package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;

import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.AuxiliarVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;
import edu.student.itson.dissof.megazarl.objetosnegocio.Sucursal;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private static final String MENSAJE_ERROR_INICIAR_APLICACION = "Ha ocurrido un error al iniciar la aplicación";

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
                    ClienteDTONegocios cliente = new ClienteDTONegocios(
                            "Juan",
                            "Pérez",
                            "López",
                            "6441234567",
                            "juanperez@gmail.com",
                            "2a3c4e67b5198610d43fe9ab07cbd4ec8a6f1d203e47bfa2176cf9eb0345acde",
                            direccionEnvioCliente
                    );


                    try {
                        // Se registra el cliente utilizando la clase envolvente Cliente
                        Cliente.agregar(cliente);
                    } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException |
                            ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                        LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                        LOG.log(Level.SEVERE, ex.getMessage());
                    }
                    

                    // Se crean los DTOs de las sucursales de la empresa y sus respectivas
                    // direcciones
                    DireccionDTONegocios direccionSucursal1 = new DireccionDTONegocios(
                                                    "83118",
                                                    "Parque Industrial Hermosillo Norte",
                                                    "José Alberto Healy Noriega",
                                                    "1000"
                    );

                    SucursalDTONegocios sucursal1 = new SucursalDTONegocios(
                                                true,
                                                direccionSucursal1
                    );

                    DireccionDTONegocios direccionSucursal2 = new DireccionDTONegocios(
                                                    "83557", 
                                                    "San Rafael",
                                                    "Río Mocorito", 
                                                    "123"
                    );

                    SucursalDTONegocios sucursal2 = new SucursalDTONegocios(
                                                false,
                                                direccionSucursal2
                    );

                    DireccionDTONegocios direccionSucursal3 = new DireccionDTONegocios(
                                                    "84269", 
                                                    "Bicentenario",
                                                    "44", 
                                                    "5695"
                   );

                    SucursalDTONegocios sucursal3 = new SucursalDTONegocios(
                                                false, 
                                                direccionSucursal3
                    );

                    DireccionDTONegocios direccionSucursal4 = new DireccionDTONegocios(
                                                    "85000",
                                                    "Ciudad Obregón Centro",
                                                    "Guerrero",
                                                    "200"
                    );

                    SucursalDTONegocios sucursal4 = new SucursalDTONegocios(
                                                false,
                                                direccionSucursal4
                    );

                    // Se crea una lista con las sucursales creadas y se guardan utilizando la
                    // clase envolvente Sucursal
                    List<SucursalDTONegocios> listaSucursales = Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4);
                    {
                        try {
                            Sucursal.agregar(listaSucursales);
                        } catch (RegistroInexistenteNegocioException | FormatoIdInvalidoNegocioException | 
                                ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                        }
                    }
                    
                    
                    List<SucursalDTONegocios> listaSucursalesRegistradas = Sucursal.recuperarTodos();
                   
                    // Se crean los DTOs de los proveedores de la empresa junto con
                    // sus respectivas direcciones
                    DireccionDTONegocios direccionProveedor1 = new DireccionDTONegocios(
                                "81255", 
                                "Zona Industrial", 
                                "Jiquilpan",
                                "3000");

                    ProveedorDTONegocios proveedor1 = new ProveedorDTONegocios(
                            "Seminis", 
                            "6441022785", 
                            "seminis@gmail.com",
                            "/seminis.png",
                            direccionProveedor1
                    );


                    DireccionDTONegocios direccionProveedor2 = new DireccionDTONegocios(
                                "21394", 
                                "Venustiano Carranza", 
                                "De Las Misiones Norte",
                                "168");

                    ProveedorDTONegocios proveedor2 = new ProveedorDTONegocios(
                            "Harris Moran", 
                            "6442365984",
                            "hmoran@gmail.com",
                            "/harrisMoran.png", 
                            direccionProveedor2);


                    DireccionDTONegocios direccionProveedor3 = new DireccionDTONegocios(
                                "80393", 
                                "Parque Industrial El Trébol", 
                                "Carretera Federal 15D",
                                "450");

                    ProveedorDTONegocios proveedor3 =  new ProveedorDTONegocios(
                            "Enza Zaden",
                            "6442059876",
                            "enzazaden@gmail.com",
                            "/enzaZaden.png",
                            direccionProveedor3);


                    DireccionDTONegocios direccionProveedor4 = new DireccionDTONegocios(
                                "31385", 
                                "Parque Industrial Chihuahua Sur", 
                                "Paseos de Aura",
                                "1485");

                    ProveedorDTONegocios proveedor4 =  new ProveedorDTONegocios(
                            "Nunhems",
                            "6447856986", 
                            "nunhmes@gmail.com",
                            "/nunhems.png", 
                            direccionProveedor4);

                    DireccionDTONegocios direccionProveedor5 =
                                new DireccionDTONegocios(
                                "37150", 
                                "Lomas del Campestre", 
                                "Blvd. Campestre",
                                "102");

                    ProveedorDTONegocios proveedor5 =  new ProveedorDTONegocios(
                            "Lark Seeds",
                            "6442326587", 
                            "larkseeds@gmail.com",
                            "/larkSeeds.png", 
                            direccionProveedor5
                    );

                    // Se guarda la lista de proveedores utilizando la clase envolvente
                    // Proveedor
                    List<ProveedorDTONegocios> listaProveedores = Arrays.asList(proveedor1, proveedor2, proveedor3, proveedor4, proveedor5);     
                    {
                        try {
                            Proveedor.agregar(listaProveedores);
                        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                                ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                            LOG.log(Level.SEVERE, ex.getMessage());
                        }
                    }
                    
                    List<ProveedorDTONegocios> listaProveedoresRegistrados = Proveedor.recuperarTodos();

                    ProductoDTONegocios producto1 = new ProductoDTONegocios(
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
                                listaProveedoresRegistrados.get(0).getId());

                    ProductoDTONegocios producto2 = new ProductoDTONegocios(
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
                                listaProveedoresRegistrados.get(1).getId());

                    ProductoDTONegocios producto3 = new ProductoDTONegocios(
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
                                listaProveedoresRegistrados.get(2).getId());

                    ProductoDTONegocios producto4 = new ProductoDTONegocios(
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
                                listaProveedoresRegistrados.get(2).getId());

                    ProductoDTONegocios producto5 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(4).getId());

                    ProductoDTONegocios producto6 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(3).getId());

                    ProductoDTONegocios producto7 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(0).getId());

                    ProductoDTONegocios producto8 =  new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(1).getId());

                    ProductoDTONegocios producto9 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(3).getId());

                    ProductoDTONegocios producto10 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(0).getId());

                    ProductoDTONegocios producto11 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(1).getId());

                    ProductoDTONegocios producto12 = new ProductoDTONegocios(
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
                            listaProveedoresRegistrados.get(0).getId());


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

                    {
                        try {

                            Producto.agregar(listaProductos);
                        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                                ParametroNuloNegocioException | ValorParametroInvalidoNegocioException ex) {
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                            LOG.log(Level.SEVERE, ex.getMessage());
                        }
                    }
                    
                    List<ProductoDTONegocios> listaProductosRegistrados = Producto.recuperarTodos();

                    // Se crean e insertan los DTOs de productos en inventario disponibles,
                    // se registran utilizando la clase envolvente ProductoInventario

                    ProductoInventarioDTONegocios productoInventario1 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(0).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario2 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(0).getId(), listaSucursalesRegistradas.get(0).getId());

                    ProductoInventarioDTONegocios productoInventario3 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(1).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario4 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(1).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario5 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(1).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario6 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(1).getId(), listaSucursalesRegistradas.get(3).getId());

                    ProductoInventarioDTONegocios productoInventario8 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(2).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario9 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(2).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario10 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(2).getId(), listaSucursalesRegistradas.get(3).getId());

                    ProductoInventarioDTONegocios productoInventario11 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(3).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario12 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(3).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario13 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(3).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario14 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(3).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario15 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(3).getId(), listaSucursalesRegistradas.get(3).getId());

                    ProductoInventarioDTONegocios productoInventario16 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(4).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario17 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(4).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario18 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(4).getId(), listaSucursalesRegistradas.get(0).getId());

                    ProductoInventarioDTONegocios productoInventario19 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(5).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario20 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(5).getId(), listaSucursalesRegistradas.get(0).getId());

                    ProductoInventarioDTONegocios productoInventario21 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario22 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario23 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario24 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario25 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario26 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario27 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario28 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(6).getId(), listaSucursalesRegistradas.get(3).getId());

                    ProductoInventarioDTONegocios productoInventario29 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(7).getId(), listaSucursalesRegistradas.get(0).getId());

                    ProductoInventarioDTONegocios productoInventario30 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(8).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario31 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(8).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario32 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(8).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario33 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(8).getId(), listaSucursalesRegistradas.get(1).getId());

                    ProductoInventarioDTONegocios productoInventario34 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario35 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario36 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario37 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario38 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario39 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario40 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario41 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(3).getId());
                    ProductoInventarioDTONegocios productoInventario42 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(2).getId());
                    ProductoInventarioDTONegocios productoInventario43 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario44 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario45 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(9).getId(), listaSucursalesRegistradas.get(3).getId());

                    ProductoInventarioDTONegocios productoInventario46 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(10).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario47 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(10).getId(), listaSucursalesRegistradas.get(1).getId());
                    ProductoInventarioDTONegocios productoInventario48 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(10).getId(), listaSucursalesRegistradas.get(1).getId());

                    ProductoInventarioDTONegocios productoInventario49 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(11).getId(), listaSucursalesRegistradas.get(0).getId());
                    ProductoInventarioDTONegocios productoInventario50 = new ProductoInventarioDTONegocios(false, listaProductosRegistrados.get(11).getId(), listaSucursalesRegistradas.get(3).getId());



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


                    {
                        try {
                            ProductoInventario.agregar(listaProductosInventario);
                        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                                ParametroNuloNegocioException | ValorParametroInvalidoNegocioException ex) {
                            
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                        }
                    }


                    DireccionDTONegocios direccionPaqueteria1 = new DireccionDTONegocios(
                                "83240",
                                "Fuentes del Mezquital", 
                                "Las Ceibas", 
                                "1247"
                    );
                    
                    // Se crean los DTO de las paqueterías asociadas con la empresa y son almacenadas
                    // utilizando la clase envolvente Paqueteria.
                    PaqueteriaDTONegocios paqueteria1 = new PaqueteriaDTONegocios(
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
                    
                    PaqueteriaDTONegocios paqueteria2 = new PaqueteriaDTONegocios( 
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
                    
                    PaqueteriaDTONegocios paqueteria3 = new PaqueteriaDTONegocios(
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
                    
                    PaqueteriaDTONegocios paqueteria4  = new PaqueteriaDTONegocios( 
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

                    PaqueteriaDTONegocios paqueteria5 =  new PaqueteriaDTONegocios(
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
                    
                    try {
                        Paqueteria.agregar(listaPaqueterias);
                    } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                            ParametroNuloNegocioException | ValorParametroInvalidoNegocioException ex) {
                        LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                        LOG.log(Level.SEVERE, ex.getMessage());
                    }
                    
                    // Se crea una instancia del control del caso de uso compra.
                    ControlCompra controlCompra = new ControlCompra();

                    
                    List<ClienteDTONegocios> clientesRegistrados = Cliente.recuperarTodos();
                    
                    
                    Object idCliente = clientesRegistrados.get(0).getId().getId();
                    
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
                        
                        DireccionDTONegocios direccionSucursalAuxiliarVentas = new DireccionDTONegocios(
                                                    "83118",
                                                    "Parque Industrial Hermosillo Norte",
                                                    "José Alberto Healy Noriega",
                                                    "1000"
                        );

                        SucursalDTONegocios sucursalAuxiliarVentas = new SucursalDTONegocios(
                                                    true,
                                                    direccionSucursalAuxiliarVentas
                        );
                        
                        try {
                            Sucursal.agregar(sucursalAuxiliarVentas);
                        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                                ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                            
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                            LOG.log(Level.SEVERE, ex.getMessage());
                        }
                        
                        List<SucursalDTONegocios> listaSucursalDTONegocios = Sucursal.recuperarTodos();
    
                        AuxiliarVentasDTONegocios auxiliarVentas1 = new AuxiliarVentasDTONegocios(
                                "María", 
                                "González",
                                "Juárez",
                                new IdEntidadGenericoNegocios(listaSucursalDTONegocios.get(0).getId().getId())
                        );
                        
                        try {
                            AuxiliarVentas.agregar(auxiliarVentas1);
                        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                                ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                            LOG.log(Level.SEVERE, ex.getMessage());
                        }
                        
                        List<AuxiliarVentasDTONegocios> listaAuxiliaresVentasRegistrados = AuxiliarVentas.recuperarTodos();
                        
                        
                        ClienteDTONegocios clienteRegistrado = new ClienteDTONegocios(
                                "Rodolfo Felix", 
                                "González", 
                                "Valle",
                                "6441234567", 
                                "rodolfoortiz@gmail.com",
                                "d8ee21560dbae03bfdd530710caf7d0358b6814efbf6b0b36293986edc80eacb");
                        
                        
                    try {
                        Cliente.agregar(clienteRegistrado);
                    } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                            ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                        LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                    }
                        
                        ControlRegistroCliente controlRegistroCliente = new ControlRegistroCliente(usuarioEsAuxiliarVentas);
                         
                        IVista registroCliente = new RegistroCliente(
                                controlRegistroCliente, 
                                listaAuxiliaresVentasRegistrados.get(0).getId().getId(), 
                                usuarioEsAuxiliarVentas);
                        
                        IMensaje mensajeRegistroUsuario = new Mensaje(); 
                        
                        controlRegistroCliente.setVistas(mensajeRegistroUsuario, registroCliente);
                        
                        controlRegistroCliente.iniciarRegistroCliente(usuarioEsAuxiliarVentas);
                        
                        
                    } else if (opcionActor == 2) {

                        ClienteDTONegocios clienteRegistrado = new ClienteDTONegocios(
                                "Rodolfo Felix", 
                                "González", 
                                "Valle",
                                "6441234567", 
                                "rodolfoortiz@gmail.com",
                                "d8ee21560dbae03bfdd530710caf7d0358b6814efbf6b0b36293986edc80eacb");
                        
                        try {
                            Cliente.agregar(clienteRegistrado);
                        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException |
                                ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                            
                            LOG.log(Level.SEVERE, MENSAJE_ERROR_INICIAR_APLICACION);
                        }
                        
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
                LOG.severe("La bandera \"" + bandera.forma + "\" necesita un argumento");
                System.exit(1);
            } else {
                ConfiguracionApp.INSTANCIA.inicializar(bandera.valor);
            }
        } catch (IOException e) {
            LOG.severe("No se pudo inicializar la configuración: " + e.getMessage());
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
