
package edu.student.itson.dissof.megazarl.administrador.mysql;

import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTO;
import edu.student.itson.dissof.dto.datos.CarritoComprasIdsRelacionesDTO;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.CarritoCompras;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Cliente;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Direccion;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Paqueteria;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Producto;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.ProductoCarrito;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Proveedor;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.AgregarInformacionNulaException;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.InformacionActualizacionNuloException;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.RegistroIdNuloException;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.RegistroInexistenteException;
import edu.student.itson.dissof.megazarl.administrador.mysql.manejadorconexiones.ManejadorConexiones;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author romom
 */
public class CarritoComprasDAOTest {
   
//    private static List<CarritoComprasIdsRelacionesDTO> listaCarritosComprasDTORegistrados = new LinkedList<>();
//    private static List<Long> listaIdsPaqueteriasRegistradas = new LinkedList<>();
//    private static List<Long> listaIdsClientesRegistrados = new LinkedList<>();
//    private static List<Long> listaIdsProductosCarritoRegistrados = new LinkedList<>();
//
//    
//    public CarritoComprasDAOTest() {
//    }
//
//    @org.junit.jupiter.api.BeforeAll
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterAll
//    public static void tearDownClass() throws Exception {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//        
//        EntityManager entityManager = ManejadorConexiones.getEntityManager();
//        entityManager.getTransaction().begin();
//        
//        // Creación de paqueterías
//        Direccion direccionPaqueteria1 = new Direccion(
//                "85000",
//                "ColoniaEjemplo1", 
//                "CalleEjemplo1", 
//                "1234");        
//        
//        Direccion direccionPaqueteria2 = new Direccion(
//                "85123",
//                "ColoniaEjemplo2", 
//                "CalleEjemplo2", 
//                "6548");       
//
//         Direccion direccionPaqueteria3 = new Direccion(
//                "80546",
//                "ColoniaEjemplo3", 
//                "CalleEjemplo3", 
//                "2288");       
//        
//        entityManager.persist(direccionPaqueteria1);
//        entityManager.persist(direccionPaqueteria2);
//        entityManager.persist(direccionPaqueteria3);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(direccionPaqueteria1);
//        entityManager.refresh(direccionPaqueteria2);
//        entityManager.refresh(direccionPaqueteria3);
//        
//        Paqueteria paqueteria1 = new Paqueteria(
//                "DHL", 
//                100F, 
//                120F, 
//                "direccionImagenEjemploPaqueteria1", 
//                direccionPaqueteria1); 
//        
//        Paqueteria paqueteria2 = new Paqueteria(
//                "Estafeta", 
//                90F, 
//                110F, 
//                "direccionImagenEjemploPaqueteria2", 
//                direccionPaqueteria2);
//
//        Paqueteria paqueteria3 = new Paqueteria(
//                "Castores", 
//                80F, 
//                100F, 
//                "direccionImagenEjemploPaqueteria3", 
//                direccionPaqueteria3);
//        
//        entityManager.persist(paqueteria1);
//        entityManager.persist(paqueteria2);
//        entityManager.persist(paqueteria3);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(paqueteria1);
//        entityManager.refresh(paqueteria2);
//        entityManager.refresh(paqueteria3);
//        
//        listaIdsPaqueteriasRegistradas.add(paqueteria1.getId());
//        listaIdsPaqueteriasRegistradas.add(paqueteria2.getId());
//        listaIdsPaqueteriasRegistradas.add(paqueteria3.getId());
//        
//        // Creación de clientes
//        Direccion direccionCliente1 = new Direccion(
//            "83600",
//            "ColoniaEjemplo4",
//            "CalleEjemplo4",
//            "457"
//        ); 
//        
//        Direccion direccionCliente2 = new Direccion(
//            "85000",
//            "ColoniaEjemplo2",
//            "CalleEjemplo2",
//            "123"
//        );
//        
//        Direccion direccionCliente3 = new Direccion(
//            "84000",
//            "ColoniaEjemplo3",
//            "CalleEjemplo3",
//            "789"
//        );
//        
//        Direccion direccionCliente4 = new Direccion(
//            "83000",
//            "ColoniaEjemplo5",
//            "CalleEjemplo5",
//            "321"
//        );
//        
//        Direccion direccionCliente5 = new Direccion(
//            "85400",
//            "ColoniaEjemplo6",
//            "CalleEjemplo6",
//            "555"
//        );
//        
//        entityManager.persist(direccionCliente1);
//        entityManager.persist(direccionCliente2);
//        entityManager.persist(direccionCliente3);
//        entityManager.persist(direccionCliente4);
//        entityManager.persist(direccionCliente5);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(direccionCliente1);
//        entityManager.refresh(direccionCliente2);
//        entityManager.refresh(direccionCliente3);
//        entityManager.refresh(direccionCliente4);
//        entityManager.refresh(direccionCliente5);
//        
//        Cliente cliente1 = new Cliente(
//            "José", 
//            "Murrieta", 
//            "Gutiérrez", 
//            "6231112233", 
//            "jose.murrieta@sonora.com.mx", 
//            direccionCliente1
//        );
//        
//        Cliente cliente2 = new Cliente(
//            "María", 
//            "López", 
//            "Ramírez", 
//            "6232223344", 
//            "maria.lopez@sonora.com.mx", 
//            direccionCliente2
//        );
//
//        Cliente cliente3 = new Cliente(
//            "Carlos", 
//            "Pérez", 
//            "Hernández", 
//            "6233334455", 
//            "carlos.perez@sonora.com.mx", 
//            direccionCliente3
//        );
//
//        Cliente cliente4 = new Cliente(
//            "Ana", 
//            "García", 
//            "Sánchez", 
//            "6234445566", 
//            "ana.garcia@sonora.com.mx", 
//            direccionCliente4
//        );
//
//        Cliente cliente5 = new Cliente(
//            "Luis", 
//            "Martínez", 
//            "Fernández", 
//            "6235556677", 
//            "luis.martinez@sonora.com.mx", 
//            direccionCliente5
//        );
//        
//        entityManager.persist(cliente1);
//        entityManager.persist(cliente2);
//        entityManager.persist(cliente3);
//        entityManager.persist(cliente4);
//        entityManager.persist(cliente5);
//
//        entityManager.flush();
//        
//        entityManager.refresh(cliente1);
//        entityManager.refresh(cliente2);
//        entityManager.refresh(cliente3);
//        entityManager.refresh(cliente4);
//        entityManager.refresh(cliente5);
//        
//        listaIdsClientesRegistrados.add(cliente1.getId());
//        listaIdsClientesRegistrados.add(cliente2.getId());
//        listaIdsClientesRegistrados.add(cliente3.getId());
//        listaIdsClientesRegistrados.add(cliente4.getId());
//        listaIdsClientesRegistrados.add(cliente5.getId());
//
//        
//        // Se crean los proveedores de productos
//        
//        Direccion direccionProveedor1 = new Direccion(
//            "85400",
//            "ColoniaEjemplo7",
//            "CalleEjemplo7",
//            "1974"
//        );
//        
//        Direccion direccionProveedor2 = new Direccion(
//            "85400",
//            "ColoniaEjemplo8",
//            "CalleEjemplo8",
//            "1974"
//        );
//        
//        Direccion direccionProveedor3 = new Direccion(
//            "85400",
//            "ColoniaEjemplo9",
//            "CalleEjemplo9",
//            "1974"
//        );
//        
//        entityManager.persist(direccionProveedor1);
//        entityManager.persist(direccionProveedor2);
//        entityManager.persist(direccionProveedor3);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(direccionProveedor1);
//        entityManager.refresh(direccionProveedor2);
//        entityManager.refresh(direccionProveedor3);
//        
//        Proveedor proveedor1 = new Proveedor(
//            "Harris Moran",
//            "6445122556",
//            "direccionImagenEjemploProveedor1",
//            direccionProveedor1
//        );
//
//        Proveedor proveedor2 = new Proveedor(
//            "Syngenta",
//            "6445122557",
//            "direccionImagenEjemploProveedor2",
//            direccionProveedor2
//        );
//
//        Proveedor proveedor3 = new Proveedor(
//            "Nunhems",
//            "6445122558",
//            "direccionImagenEjemploProveedor3",
//            direccionProveedor3
//        );
//        
//        entityManager.persist(proveedor1);
//        entityManager.persist(proveedor2);
//        entityManager.persist(proveedor3);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(proveedor1);
//        entityManager.refresh(proveedor2);
//        entityManager.refresh(proveedor3);
//        
//        Producto producto1 = new Producto(
//            "Sandía",
//            "Warrior",
//            """
//                Las semillas de la variedad Warrior representan la innovación agrícola de Nunhems,
//                diseñadas para brindar a los productores una opción de alto rendimiento y calidad
//                superior.
//            """,
//            5,
//            9650d,
//            5d,
//            "/sandiaWarrior.png");
//        
//        producto1.setProveedor(proveedor3);
//
//        Producto producto2 = new Producto(
//                "Sandía",
//                "Tailgate",
//                """
//                    Tailgate es una sandia triploide o sin semilla de cascara lisa con bandas verde 
//                    medio y bandas verde oscuro.
//                """,
//                7,
//                9000d,
//                7d,
//                "/sandiaTailgate.png");
//
//        producto2.setProveedor(proveedor2);
//        
//        Producto producto3 = new Producto(
//                "Brócoli",
//                "Kepler F1",
//                """
//                    El Brócoli Kepler F1 es un híbrido de alta calidad, diseñado para el mercado fresco 
//                    y de procesamiento.
//                """,
//                25,
//                3640d,
//                5d,
//                "/brocoliKeplerF1.png");
//        
//        producto3.setProveedor(proveedor1);
//
//        Producto producto4 = new Producto(
//                "Melón",
//                "Saturno",
//                """
//                    Melón tipo Honeydew suave y diseñado para principios de temporada. Presenta frutos 
//                    de forma redonda a ovalada con cavidad pequeña.
//                """,
//                15,
//                10000d,
//                3d,
//                "/melonSaturno.png");
//        
//        producto4.setProveedor(proveedor1);
//        
//        entityManager.persist(producto1);
//        entityManager.persist(producto2);
//        entityManager.persist(producto3);
//        entityManager.persist(producto4);
//
//        entityManager.flush();
//        
//        entityManager.refresh(producto1);
//        entityManager.refresh(producto2);
//        entityManager.refresh(producto3);
//        entityManager.refresh(producto4);
//        
//        // Se crean los productos en carrito
//        CarritoCompras carritoCompras1 = new CarritoCompras(  false);
//        
//        carritoCompras1.setCliente(cliente1);
//        carritoCompras1.setPaqueteria(paqueteria1);
//        
//        CarritoCompras carritoCompras2 = new CarritoCompras(true);
//        
//        carritoCompras2.setCliente(cliente1);
//        
//        CarritoCompras carritoCompras3 = new CarritoCompras(true);
//        
//        carritoCompras3.setCliente(cliente2);
//        
//        CarritoCompras carritoCompras4 = new CarritoCompras(false);
//                
//        carritoCompras4.setCliente(cliente3);
//        carritoCompras4.setPaqueteria(paqueteria2);
//        
//        CarritoCompras carritoCompras5 = new CarritoCompras(true);
//        
//        carritoCompras5.setCliente(cliente3);
//        
//        CarritoCompras carritoCompras6 = new CarritoCompras(true);
//        
//        carritoCompras6.setCliente(cliente3);
//        
//        CarritoCompras carritoCompras7 = new CarritoCompras(false);
//        
//        carritoCompras7.setCliente(cliente4);
//        carritoCompras7.setPaqueteria(paqueteria3);
//        
//        CarritoCompras carritoCompras8 = new CarritoCompras(true);
//        
//        carritoCompras8.setCliente(cliente4);
//        
//        CarritoCompras carritoCompras9 = new CarritoCompras(false);  
//        
//        carritoCompras9.setCliente(cliente5);
//        carritoCompras9.setPaqueteria(paqueteria3);
//                
//        CarritoCompras carritoCompras10 = new CarritoCompras(true);
//        
//        carritoCompras10.setCliente(cliente5);
//        
//        entityManager.persist(carritoCompras1);
//        entityManager.persist(carritoCompras2);
//        entityManager.persist(carritoCompras3);
//        entityManager.persist(carritoCompras4);
//        entityManager.persist(carritoCompras5);
//        entityManager.persist(carritoCompras6);
//        entityManager.persist(carritoCompras7);
//        entityManager.persist(carritoCompras8);
//        entityManager.persist(carritoCompras9);
//        entityManager.persist(carritoCompras10);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(carritoCompras1);
//        entityManager.refresh(carritoCompras2);
//        entityManager.refresh(carritoCompras3);
//        entityManager.refresh(carritoCompras4);
//        entityManager.refresh(carritoCompras5);
//        entityManager.refresh(carritoCompras6);
//        entityManager.refresh(carritoCompras7);
//        entityManager.refresh(carritoCompras8);
//        entityManager.refresh(carritoCompras9);
//        entityManager.refresh(carritoCompras10);
//
//        ProductoCarrito productoCarrito1 = new ProductoCarrito(4);
//        ProductoCarrito productoCarrito2 = new ProductoCarrito(7);
//        ProductoCarrito productoCarrito3 = new ProductoCarrito(5);
//        ProductoCarrito productoCarrito4 = new ProductoCarrito(8);
//        ProductoCarrito productoCarrito5 = new ProductoCarrito(6);
//        ProductoCarrito productoCarrito6 = new ProductoCarrito(9);
//        ProductoCarrito productoCarrito7 = new ProductoCarrito(10);
//        ProductoCarrito productoCarrito8 = new ProductoCarrito(4);
//        ProductoCarrito productoCarrito9 = new ProductoCarrito(5);
//        ProductoCarrito productoCarrito10 = new ProductoCarrito(6);
//        ProductoCarrito productoCarrito11 = new ProductoCarrito(7);
//        ProductoCarrito productoCarrito12 = new ProductoCarrito(8);
//        ProductoCarrito productoCarrito13 = new ProductoCarrito(9);
//        ProductoCarrito productoCarrito14 = new ProductoCarrito(10);
//        ProductoCarrito productoCarrito15 = new ProductoCarrito(11);
//        ProductoCarrito productoCarrito16 = new ProductoCarrito(12);
//        ProductoCarrito productoCarrito17 = new ProductoCarrito(13);
//        ProductoCarrito productoCarrito18 = new ProductoCarrito(14);
//        ProductoCarrito productoCarrito19 = new ProductoCarrito(15);
//        ProductoCarrito productoCarrito20 = new ProductoCarrito(16);
//        ProductoCarrito productoCarrito21 = new ProductoCarrito(17);
//        ProductoCarrito productoCarrito22 = new ProductoCarrito(18);
//        ProductoCarrito productoCarrito23 = new ProductoCarrito(19);
//        ProductoCarrito productoCarrito24 = new ProductoCarrito(20);
//        ProductoCarrito productoCarrito25 = new ProductoCarrito(21);
//        ProductoCarrito productoCarrito26 = new ProductoCarrito(22);
//        ProductoCarrito productoCarrito27 = new ProductoCarrito(23);
//        ProductoCarrito productoCarrito28 = new ProductoCarrito(24);
//        ProductoCarrito productoCarrito29 = new ProductoCarrito(25);
//        ProductoCarrito productoCarrito30 = new ProductoCarrito(26);
//        ProductoCarrito productoCarrito31 = new ProductoCarrito(27);
//        ProductoCarrito productoCarrito32 = new ProductoCarrito(28);
//        ProductoCarrito productoCarrito33 = new ProductoCarrito(29);
//        ProductoCarrito productoCarrito34 = new ProductoCarrito(30);
//        ProductoCarrito productoCarrito35 = new ProductoCarrito(31);
//        ProductoCarrito productoCarrito36 = new ProductoCarrito(32);
//        ProductoCarrito productoCarrito37 = new ProductoCarrito(33);
//        ProductoCarrito productoCarrito38 = new ProductoCarrito(34);
//        ProductoCarrito productoCarrito39 = new ProductoCarrito(35);
//        ProductoCarrito productoCarrito40 = new ProductoCarrito(36);
//
//        // CarritoCompras1: 7 productos en carrito
//        carritoCompras1.agregarProductoCarrito(productoCarrito1);
//        carritoCompras1.agregarProductoCarrito(productoCarrito2);
//        carritoCompras1.agregarProductoCarrito(productoCarrito3);
//        carritoCompras1.agregarProductoCarrito(productoCarrito4);
//        carritoCompras1.agregarProductoCarrito(productoCarrito5);
//        carritoCompras1.agregarProductoCarrito(productoCarrito6);
//        carritoCompras1.agregarProductoCarrito(productoCarrito7);
//
//        // CarritoCompras2: 3 productos en carrito
//        carritoCompras2.agregarProductoCarrito(productoCarrito8);
//        carritoCompras2.agregarProductoCarrito(productoCarrito9);
//        carritoCompras2.agregarProductoCarrito(productoCarrito10);
//
//        // CarritoCompras3: 6 productos en carrito
//        carritoCompras3.agregarProductoCarrito(productoCarrito11);
//        carritoCompras3.agregarProductoCarrito(productoCarrito12);
//        carritoCompras3.agregarProductoCarrito(productoCarrito13);
//        carritoCompras3.agregarProductoCarrito(productoCarrito14);
//        carritoCompras3.agregarProductoCarrito(productoCarrito15);
//        carritoCompras3.agregarProductoCarrito(productoCarrito16);
//
//        // CarritoCompras4: 5 productos en carrito
//        carritoCompras4.agregarProductoCarrito(productoCarrito17);
//        carritoCompras4.agregarProductoCarrito(productoCarrito18);
//        carritoCompras4.agregarProductoCarrito(productoCarrito19);
//        carritoCompras4.agregarProductoCarrito(productoCarrito20);
//        carritoCompras4.agregarProductoCarrito(productoCarrito21);
//
//        // CarritoCompras5: 2 productos en carrito
//        carritoCompras5.agregarProductoCarrito(productoCarrito22);
//        carritoCompras5.agregarProductoCarrito(productoCarrito23);
//
//        // CarritoCompras6: 4 productos en carrito
//        carritoCompras6.agregarProductoCarrito(productoCarrito24);
//        carritoCompras6.agregarProductoCarrito(productoCarrito25);
//        carritoCompras6.agregarProductoCarrito(productoCarrito26);
//        carritoCompras6.agregarProductoCarrito(productoCarrito27);
//
//        // CarritoCompras7: 5 productos en carrito
//        carritoCompras7.agregarProductoCarrito(productoCarrito28);
//        carritoCompras7.agregarProductoCarrito(productoCarrito29);
//        carritoCompras7.agregarProductoCarrito(productoCarrito30);
//        carritoCompras7.agregarProductoCarrito(productoCarrito31);
//        carritoCompras7.agregarProductoCarrito(productoCarrito32);
//
//        // CarritoCompras8: 4 productos en carrito
//        carritoCompras8.agregarProductoCarrito(productoCarrito33);
//        carritoCompras8.agregarProductoCarrito(productoCarrito34);
//        carritoCompras8.agregarProductoCarrito(productoCarrito35);
//        carritoCompras8.agregarProductoCarrito(productoCarrito36);
//
//        // CarritoCompras9: 3 productos en carrito
//        carritoCompras9.agregarProductoCarrito(productoCarrito37);
//        carritoCompras9.agregarProductoCarrito(productoCarrito38);
//        carritoCompras9.agregarProductoCarrito(productoCarrito39);
//
//        // CarritoCompras10: 1 producto en carrito
//        carritoCompras10.agregarProductoCarrito(productoCarrito40);
//
//        // Asociaciones para Producto1:
//        producto1.agregarProductoCarrito(productoCarrito1);
//        producto1.agregarProductoCarrito(productoCarrito5);
//        producto1.agregarProductoCarrito(productoCarrito9);
//        producto1.agregarProductoCarrito(productoCarrito13);
//        producto1.agregarProductoCarrito(productoCarrito17);
//        producto1.agregarProductoCarrito(productoCarrito21);
//        producto1.agregarProductoCarrito(productoCarrito25);
//        producto1.agregarProductoCarrito(productoCarrito29);
//        producto1.agregarProductoCarrito(productoCarrito33);
//        producto1.agregarProductoCarrito(productoCarrito37);
//        
//        // Asociaciones para Producto2:
//        producto2.agregarProductoCarrito(productoCarrito2);
//        producto2.agregarProductoCarrito(productoCarrito6);
//        producto2.agregarProductoCarrito(productoCarrito10);
//        producto2.agregarProductoCarrito(productoCarrito14);
//        producto2.agregarProductoCarrito(productoCarrito18);
//        producto2.agregarProductoCarrito(productoCarrito22);
//        producto2.agregarProductoCarrito(productoCarrito26);
//        producto2.agregarProductoCarrito(productoCarrito30);
//        producto2.agregarProductoCarrito(productoCarrito34);
//        producto2.agregarProductoCarrito(productoCarrito38);
//
//        // Asociaciones para Producto3:
//        producto3.agregarProductoCarrito(productoCarrito3);
//        producto3.agregarProductoCarrito(productoCarrito7);
//        producto3.agregarProductoCarrito(productoCarrito11);
//        producto3.agregarProductoCarrito(productoCarrito15);
//        producto3.agregarProductoCarrito(productoCarrito19);
//        producto3.agregarProductoCarrito(productoCarrito23);
//        producto3.agregarProductoCarrito(productoCarrito27);
//        producto3.agregarProductoCarrito(productoCarrito31);
//        producto3.agregarProductoCarrito(productoCarrito35);
//        producto3.agregarProductoCarrito(productoCarrito39);
//
//        // Asociaciones para Producto4:
//        producto4.agregarProductoCarrito(productoCarrito4);
//        producto4.agregarProductoCarrito(productoCarrito8);
//        producto4.agregarProductoCarrito(productoCarrito12);
//        producto4.agregarProductoCarrito(productoCarrito16);
//        producto4.agregarProductoCarrito(productoCarrito20);
//        producto4.agregarProductoCarrito(productoCarrito24);
//        producto4.agregarProductoCarrito(productoCarrito28);
//        producto4.agregarProductoCarrito(productoCarrito32);
//        producto4.agregarProductoCarrito(productoCarrito36);
//        producto4.agregarProductoCarrito(productoCarrito40);
//        
//
//        entityManager.persist(productoCarrito1);
//        entityManager.persist(productoCarrito2);
//        entityManager.persist(productoCarrito3);
//        entityManager.persist(productoCarrito4);
//        entityManager.persist(productoCarrito5);
//        entityManager.persist(productoCarrito6);
//        entityManager.persist(productoCarrito7);
//        entityManager.persist(productoCarrito8);
//        entityManager.persist(productoCarrito9);
//        entityManager.persist(productoCarrito10);
//        entityManager.persist(productoCarrito11);
//        entityManager.persist(productoCarrito12);
//        entityManager.persist(productoCarrito13);
//        entityManager.persist(productoCarrito14);
//        entityManager.persist(productoCarrito15);
//        entityManager.persist(productoCarrito16);
//        entityManager.persist(productoCarrito17);
//        entityManager.persist(productoCarrito18);
//        entityManager.persist(productoCarrito19);
//        entityManager.persist(productoCarrito20);
//        entityManager.persist(productoCarrito21);
//        entityManager.persist(productoCarrito22);
//        entityManager.persist(productoCarrito23);
//        entityManager.persist(productoCarrito24);
//        entityManager.persist(productoCarrito25);
//        entityManager.persist(productoCarrito26);
//        entityManager.persist(productoCarrito27);
//        entityManager.persist(productoCarrito28);
//        entityManager.persist(productoCarrito29);
//        entityManager.persist(productoCarrito30);
//        entityManager.persist(productoCarrito31);
//        entityManager.persist(productoCarrito32);
//        entityManager.persist(productoCarrito33);
//        entityManager.persist(productoCarrito34);
//        entityManager.persist(productoCarrito35);
//        entityManager.persist(productoCarrito36);
//        entityManager.persist(productoCarrito37);
//        entityManager.persist(productoCarrito38);
//        entityManager.persist(productoCarrito39);
//        entityManager.persist(productoCarrito40);
//        
//        entityManager.flush();
//        
//        entityManager.refresh(productoCarrito1);
//        entityManager.refresh(productoCarrito2);
//        entityManager.refresh(productoCarrito3);
//        entityManager.refresh(productoCarrito4);
//        entityManager.refresh(productoCarrito5);
//        entityManager.refresh(productoCarrito6);
//        entityManager.refresh(productoCarrito7);
//        entityManager.refresh(productoCarrito8);
//        entityManager.refresh(productoCarrito9);
//        entityManager.refresh(productoCarrito10);
//        entityManager.refresh(productoCarrito11);
//        entityManager.refresh(productoCarrito12);
//        entityManager.refresh(productoCarrito13);
//        entityManager.refresh(productoCarrito14);
//        entityManager.refresh(productoCarrito15);
//        entityManager.refresh(productoCarrito16);
//        entityManager.refresh(productoCarrito17);
//        entityManager.refresh(productoCarrito18);
//        entityManager.refresh(productoCarrito19);
//        entityManager.refresh(productoCarrito20);
//        entityManager.refresh(productoCarrito21);
//        entityManager.refresh(productoCarrito22);
//        entityManager.refresh(productoCarrito23);
//        entityManager.refresh(productoCarrito24);
//        entityManager.refresh(productoCarrito25);
//        entityManager.refresh(productoCarrito26);
//        entityManager.refresh(productoCarrito27);
//        entityManager.refresh(productoCarrito28);
//        entityManager.refresh(productoCarrito29);
//        entityManager.refresh(productoCarrito30);
//        entityManager.refresh(productoCarrito31);
//        entityManager.refresh(productoCarrito32);
//        entityManager.refresh(productoCarrito33);
//        entityManager.refresh(productoCarrito34);
//        entityManager.refresh(productoCarrito35);
//        entityManager.refresh(productoCarrito36);
//        entityManager.refresh(productoCarrito37);
//        entityManager.refresh(productoCarrito38);
//        entityManager.refresh(productoCarrito39);
//        entityManager.refresh(productoCarrito40);
//        
//        
//        listaIdsProductosCarritoRegistrados.add(productoCarrito1.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito2.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito3.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito4.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito5.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito6.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito7.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito8.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito9.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito10.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito11.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito12.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito13.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito14.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito15.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito16.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito17.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito18.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito19.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito20.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito21.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito22.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito23.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito24.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito25.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito26.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito27.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito28.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito29.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito30.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito31.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito32.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito33.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito34.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito35.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito36.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito37.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito38.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito39.getId());
//        listaIdsProductosCarritoRegistrados.add(productoCarrito40.getId());
//        
//        entityManager.refresh(carritoCompras1);
//        entityManager.refresh(carritoCompras2);
//        entityManager.refresh(carritoCompras3);
//        entityManager.refresh(carritoCompras4);
//        entityManager.refresh(carritoCompras5);
//        entityManager.refresh(carritoCompras6);
//        entityManager.refresh(carritoCompras7);
//        entityManager.refresh(carritoCompras8);
//        entityManager.refresh(carritoCompras9);
//        entityManager.refresh(carritoCompras10);
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO(  
//                        carritoCompras1.getId(),
//                        carritoCompras1.getEsVigente(),    
//                        carritoCompras1.getCliente().getId(), 
//                        carritoCompras1.getPaqueteria().getId(),
//                        Arrays.asList(
//                                    productoCarrito1.getId(),
//                                    productoCarrito2.getId(),
//                                    productoCarrito3.getId(),
//                                    productoCarrito4.getId(),
//                                    productoCarrito5.getId(),
//                                    productoCarrito6.getId(),
//                                    productoCarrito7.getId()
//                    )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO(  
//                        carritoCompras2.getId(),
//                        carritoCompras2.getEsVigente(),   
//                        carritoCompras2.getCliente().getId(),
//                        Arrays.asList(
//                                    productoCarrito8.getId(),
//                                    productoCarrito9.getId(),
//                                    productoCarrito10.getId()
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras3.getId(),
//                        carritoCompras3.getEsVigente(),    
//                        carritoCompras3.getCliente().getId(),
//                        Arrays.asList(
//                                    productoCarrito11.getId(),
//                                    productoCarrito12.getId(),
//                                    productoCarrito13.getId(),
//                                    productoCarrito14.getId(),
//                                    productoCarrito15.getId(),
//                                    productoCarrito16.getId()
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras4.getId(),
//                        carritoCompras4.getEsVigente(),    
//                        carritoCompras4.getCliente().getId(),
//                        carritoCompras4.getPaqueteria().getId(),
//                        Arrays.asList(
//                                    productoCarrito17.getId(),
//                                    productoCarrito18.getId(),
//                                    productoCarrito19.getId(),
//                                    productoCarrito20.getId(),
//                                    productoCarrito21.getId()
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras5.getId(),
//                        carritoCompras5.getEsVigente(),    
//                        carritoCompras5.getCliente().getId(),
//                        Arrays.asList(
//                                    productoCarrito22.getId(),
//                                    productoCarrito23.getId()
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras6.getId(),
//                        carritoCompras6.getEsVigente(),    
//                        carritoCompras6.getCliente().getId(),
//                        Arrays.asList(
//                                    productoCarrito24.getId(),
//                                    productoCarrito25.getId(),
//                                    productoCarrito26.getId(),
//                                    productoCarrito27.getId()
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras7.getId(),
//                        carritoCompras7.getEsVigente(),    
//                        carritoCompras7.getCliente().getId(),
//                        carritoCompras7.getPaqueteria().getId(),
//                        Arrays.asList(
//                                    productoCarrito28.getId(),
//                                    productoCarrito29.getId(),
//                                    productoCarrito30.getId(),
//                                    productoCarrito31.getId(),
//                                    productoCarrito32.getId()
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras8.getId(),
//                        carritoCompras8.getEsVigente(),    
//                        carritoCompras8.getCliente().getId(),
//                        Arrays.asList(
//                                    productoCarrito33.getId(),
//                                    productoCarrito34.getId(),
//                                    productoCarrito35.getId(),
//                                    productoCarrito36.getId()
//                        )
//                )
//        );
//        
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras9.getId(),
//                        carritoCompras9.getEsVigente(),    
//                        carritoCompras9.getCliente().getId(),
//                        carritoCompras9.getPaqueteria().getId(),
//                        Arrays.asList(
//                                    productoCarrito37.getId(),
//                                    productoCarrito38.getId(),
//                                    productoCarrito39.getId()
//                        )
//                )
//        );
//        
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasIdsRelacionesDTO( 
//                        carritoCompras10.getId(),
//                        carritoCompras10.getEsVigente(),    
//                        carritoCompras10.getCliente().getId(),
//                        Arrays.asList(
//                                    productoCarrito40.getId()
//                        )
//                )
//        );
//
//
//        entityManager.getTransaction().commit();
//        
//    }
//    
//    @AfterEach
//    public void tearDown() {
//        
//        EntityManager entityManager = ManejadorConexiones.getEntityManager();
//        
//        entityManager.getTransaction().begin();
//       
//        String jpqlQueryBorrarProductosCarritosCompras = "DELETE FROM ProductoCarrito";
//        String jpqlQueryBorrarProductosInventario = "DELETE FROM ProductoInventario";
//        String jpqlQueryBorrarProductos = "DELETE FROM Producto";
//        String jpqlQueryBorrarCarritosCompras = "DELETE FROM CarritoCompras";
//        String jpqlQueryBorrarPaqueterias = "DELETE FROM Paqueteria";
//        String jpqlQueryBorrarProveedores = "DELETE FROM Proveedor";
//        String jpqlQueryBorrarClientes = "DELETE FROM Cliente";
//        String jpqlQueryBorrarDirecciones = "DELETE FROM Direccion";
//        
//        entityManager.createQuery(jpqlQueryBorrarProductosCarritosCompras).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarProductosInventario).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarProductos).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarCarritosCompras).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarPaqueterias).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarProveedores).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarClientes).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarDirecciones).executeUpdate();
//        
//        listaCarritosComprasDTORegistrados = new LinkedList<>();
//        listaIdsPaqueteriasRegistradas = new LinkedList<>();
//        listaIdsClientesRegistrados = new LinkedList<>();
//        listaIdsProductosCarritoRegistrados = new LinkedList<>();
//        
//        entityManager.getTransaction().commit();
//    }
//
//
//    @Test
//    public void testObtenerCarritoComprasPorIdOk() {
//        
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        Long ID_CARRITO_COMPRAS_MENOR = listaCarritosComprasDTORegistrados.get(0).getId();
//        
//        long rango = ID_CARRITO_COMPRAS_MAYOR - ID_CARRITO_COMPRAS_MENOR + 1;
//
//        long NUMERO_ALEATORIO_RANGO = (long)(Math.random() * rango);
//
//        long idAleatorioCarritoComprasDTO = ID_CARRITO_COMPRAS_MENOR + NUMERO_ALEATORIO_RANGO;
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        CarritoComprasDTO carritoComprasDTOEsperado = null;
//        
//        for(CarritoComprasDTO carrito: listaCarritosComprasDTORegistrados){
//            if(carrito.getId() == idAleatorioCarritoComprasDTO){
//                
//                carritoComprasDTOEsperado = carrito;
//                
//            }
//        }
//        
//        IdCarritoComprasDTO idCarritoComprasDTO = new IdCarritoComprasDTO(idAleatorioCarritoComprasDTO);
//
//        CarritoComprasDTO carritoComprasDTOResultado = assertDoesNotThrow(() -> carritoComprasDAO.recuperarPorId(idCarritoComprasDTO));
//
//        assertNotNull(carritoComprasDTOResultado);
//   
//        assertEquals(carritoComprasDTOEsperado.getId(), carritoComprasDTOResultado.getId());
//        assertEquals(carritoComprasDTOEsperado.getEsVigente(), carritoComprasDTOResultado.getEsVigente());
//        assertEquals(carritoComprasDTOEsperado.getIdCliente(), carritoComprasDTOResultado.getIdCliente());
//        
//        if(carritoComprasDTOEsperado.getEsVigente()){
//            assertNull(carritoComprasDTOResultado.getIdPaqueteria());
//        } else{
//            assertNotNull(carritoComprasDTOResultado.getIdPaqueteria());
//            assertEquals(carritoComprasDTOEsperado.getIdPaqueteria(), carritoComprasDTOResultado.getIdPaqueteria());
//        }
//        
//        carritoComprasDTOEsperado.getIdsProductosCarrito().sort(Comparator.naturalOrder());
//        carritoComprasDTOResultado.getIdsProductosCarrito().sort(Comparator.naturalOrder());
//        
//        for(int i = 0; i < carritoComprasDTOEsperado.getIdsProductosCarrito().size(); i++){
//            
//            Long idProductoCarritoEsperado = carritoComprasDTOEsperado.getIdsProductosCarrito().get(i);
//            Long idProductoCarritoResultado = carritoComprasDTOResultado.getIdsProductosCarrito().get(i);
//            
//            assertNotNull(idProductoCarritoEsperado);
//            
//            assertEquals(idProductoCarritoEsperado, idProductoCarritoResultado);
//            
//        }
//        
//
//    }
//    
//    @Test
//    public void testObtenerCarritoComprasPorIdNoExiste() {
//
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        long ID_BUSCAR = ID_CARRITO_COMPRAS_MAYOR + 1;
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "No existe un registro de carrito de compras con el ID: " + ID_BUSCAR;
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        IdCarritoComprasDTO idCarritoComprasDTO = new IdCarritoComprasDTO(ID_BUSCAR);
//
//        RegistroInexistenteException ex = assertThrows(RegistroInexistenteException.class, () -> carritoComprasDAO.recuperarPorId(idCarritoComprasDTO));
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//    
//    @Test
//    public void testObtenerCarritoComprasPorIdNulo() {
//
//        Long ID_BUSCAR = null;
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "El ID recibido para realizar la consulta del carrito de compras es nulo.";
//       
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        IdCarritoComprasDTO idCarritoComprasDTO = new IdCarritoComprasDTO(ID_BUSCAR);
//
//        RegistroIdNuloException ex = assertThrows(RegistroIdNuloException.class, () -> carritoComprasDAO.recuperarPorId(idCarritoComprasDTO));
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//    
//    @Test
//    public void testObtenerCarritoComprasPorIdDTONulo() {
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "El DTO recibido que contiene el ID para realizar la consulta del carrito de compras es nulo.";
//       
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        IdCarritoComprasDTO idCarritoComprasDTO = null;
//
//        RegistroIdNuloException ex = assertThrows(RegistroIdNuloException.class, () -> carritoComprasDAO.recuperarPorId(idCarritoComprasDTO));
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//    
//    @Test
//    public void testCarritoComprasExistePorIdOk() {
//        
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        Long ID_CARRITO_COMPRAS_MENOR = listaCarritosComprasDTORegistrados.get(0).getId();
//        
//        long rango = ID_CARRITO_COMPRAS_MAYOR - ID_CARRITO_COMPRAS_MENOR + 1;
//
//        long NUMERO_ALEATORIO_RANGO = (long)(Math.random() * rango);
//
//        long idAleatorioCarritoComprasDTO = ID_CARRITO_COMPRAS_MENOR + NUMERO_ALEATORIO_RANGO;
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        IdCarritoComprasDTO idCarritoComprasDTO = new IdCarritoComprasDTO(idAleatorioCarritoComprasDTO);
//
//        Boolean carritoExiste = assertDoesNotThrow(() -> carritoComprasDAO.existePorId(idCarritoComprasDTO));
//
//        assertTrue(carritoExiste);
//   
//    }
//    
//    @Test
//    public void testCarritoComprasExistePorIdNoExiste() {
//        
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        long ID_BUSCAR = ID_CARRITO_COMPRAS_MAYOR + 1;
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        IdCarritoComprasDTO idCarritoComprasDTO = new IdCarritoComprasDTO(ID_BUSCAR);
//
//        Boolean carritoExiste = assertDoesNotThrow(() -> carritoComprasDAO.existePorId(idCarritoComprasDTO));
//
//        assertFalse(carritoExiste);
//   
//    }
//    
//    @Test
//    public void testActualizarCarritoComprasEsVigenteOk(){
//        
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        Long ID_CARRITO_COMPRAS_MENOR = listaCarritosComprasDTORegistrados.get(0).getId();
//        
//        long rango = ID_CARRITO_COMPRAS_MAYOR - ID_CARRITO_COMPRAS_MENOR + 1;
//
//        long NUMERO_ALEATORIO_RANGO = (long)(Math.random() * rango);
//
//        long idAleatorioCarritoComprasDTO = ID_CARRITO_COMPRAS_MENOR + NUMERO_ALEATORIO_RANGO;
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        CarritoComprasIdsRelacionesDTO carritoComprasDTOEsperado = null;
//        
//        for(CarritoComprasIdsRelacionesDTO carrito: listaCarritosComprasDTORegistrados){
//            
//            if(carrito.getId() == idAleatorioCarritoComprasDTO){
//                
//                carritoComprasDTOEsperado = carrito;
//                
//            }
//        }
//        
//
//        ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO = new ActualizacionCarritoComprasDTO(idAleatorioCarritoComprasDTO);
//        
//        Boolean esVigenteAnterior = carritoComprasDTOEsperado.getEsVigente();
//        Boolean nuevoEsVigente = !esVigenteAnterior;
//        actualizacionCarritoComprasDTO.setEsVigente(nuevoEsVigente);
//        carritoComprasDTOEsperado.setEsVigente(nuevoEsVigente);
//        
//        
//        CarritoComprasIdsRelacionesDTO carritoComprasActualizado = assertDoesNotThrow(() -> carritoComprasDAO.actualizar(actualizacionCarritoComprasDTO));
//        
//        assertNotNull(carritoComprasActualizado);
//        
//        assertNotNull(carritoComprasActualizado.getId());
//        
//        assertEquals(carritoComprasDTOEsperado.getId(), carritoComprasActualizado.getId());
//        
//        assertNotNull(carritoComprasActualizado.getEsVigente());
//        
//        assertEquals(carritoComprasDTOEsperado.getEsVigente(), carritoComprasActualizado.getEsVigente());
//        
//        if(carritoComprasDTOEsperado.getIdPaqueteria() == null){
//            assertNull(carritoComprasActualizado.getIdPaqueteria());
//        } else{
//            assertNotNull(carritoComprasActualizado.getIdPaqueteria());
//            assertEquals(carritoComprasDTOEsperado.getIdPaqueteria(), carritoComprasActualizado.getIdPaqueteria());  
//        }
//        
//        carritoComprasDTOEsperado.getIdsProductosCarrito().sort(Comparator.naturalOrder());
//        carritoComprasActualizado.getIdsProductosCarrito().sort(Comparator.naturalOrder());
//        
//        for(int i = 0; i < carritoComprasDTOEsperado.getIdsProductosCarrito().size(); i++){
//            
//            Long idProductoCarritoEsperado = carritoComprasDTOEsperado.getIdsProductosCarrito().get(i);
//            Long idProductoCarritoResultado = carritoComprasActualizado.getIdsProductosCarrito().get(i);
//            
//            assertNotNull(idProductoCarritoEsperado);
//            assertEquals(idProductoCarritoEsperado, idProductoCarritoResultado);
//            
//        }
//    }
//    
//    @Test
//    public void testActualizarCarritoComprasPaqueteriaOk(){
//        
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        Long ID_CARRITO_COMPRAS_MENOR = listaCarritosComprasDTORegistrados.get(0).getId();
//        
//        long rango = ID_CARRITO_COMPRAS_MAYOR - ID_CARRITO_COMPRAS_MENOR + 1;
//
//        long NUMERO_ALEATORIO_RANGO = (long)(Math.random() * rango);
//
//        long idAleatorioCarritoComprasDTO = ID_CARRITO_COMPRAS_MENOR + NUMERO_ALEATORIO_RANGO;
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        CarritoComprasIdsRelacionesDTO carritoComprasDTOEsperado = null;
//        
//        for(CarritoComprasIdsRelacionesDTO carrito: listaCarritosComprasDTORegistrados){
//            
//            if(carrito.getId() == idAleatorioCarritoComprasDTO){
//                
//                carritoComprasDTOEsperado = carrito;
//                
//            }
//        }
//        
//        ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO = new ActualizacionCarritoComprasDTO(idAleatorioCarritoComprasDTO);
//        
//        Long idPaqueteriaAnterior = carritoComprasDTOEsperado.getIdPaqueteria();
//        Long idNuevaPaqueteria = null;
//        
//        if(idPaqueteriaAnterior == null){
//  
//            Random random = new Random();
//            idNuevaPaqueteria = listaIdsPaqueteriasRegistradas.get(random.nextInt(listaIdsPaqueteriasRegistradas.size()));
//            
//        } else {
//            
//            for(CarritoComprasIdsRelacionesDTO carritoCompras: listaCarritosComprasDTORegistrados){
//                
//                if(carritoCompras.getIdPaqueteria() != null && !carritoCompras.getIdPaqueteria().equals(idPaqueteriaAnterior)){
//                    idNuevaPaqueteria = carritoCompras.getIdPaqueteria();
//                    
//                    break;
//                }
//                
//            }
//        }
//        
//        carritoComprasDTOEsperado.setIdPaqueteria(idNuevaPaqueteria);
//        actualizacionCarritoComprasDTO.setIdPaqueteria(idNuevaPaqueteria);
//        
//        CarritoComprasIdsRelacionesDTO carritoComprasActualizado = assertDoesNotThrow(() -> carritoComprasDAO.actualizar(actualizacionCarritoComprasDTO));
//        
//        assertNotNull(carritoComprasActualizado);
//        
//        assertNotNull(carritoComprasActualizado.getId());
//        
//        assertEquals(carritoComprasDTOEsperado.getId(), carritoComprasActualizado.getId());
//        
//        assertEquals(carritoComprasDTOEsperado.getEsVigente(), carritoComprasActualizado.getEsVigente());
//        
//        if(carritoComprasDTOEsperado.getIdPaqueteria() == null){
//            assertNull(carritoComprasActualizado.getIdPaqueteria());
//        } else{
//            assertNotNull(carritoComprasActualizado.getIdPaqueteria());
//            assertEquals(carritoComprasDTOEsperado.getIdPaqueteria(), carritoComprasActualizado.getIdPaqueteria());  
//        }
//        
//        carritoComprasDTOEsperado.getIdsProductosCarrito().sort(Comparator.naturalOrder());
//        carritoComprasActualizado.getIdsProductosCarrito().sort(Comparator.naturalOrder());
//        
//        for(int i = 0; i < carritoComprasDTOEsperado.getIdsProductosCarrito().size(); i++){
//            
//            Long idProductoCarritoEsperado = carritoComprasDTOEsperado.getIdsProductosCarrito().get(i);
//            Long idProductoCarritoResultado = carritoComprasActualizado.getIdsProductosCarrito().get(i);
//            
//            assertNotNull(idProductoCarritoEsperado);
//            assertEquals(idProductoCarritoEsperado, idProductoCarritoResultado);
//            
//        }
//    }
//    
//    @Test
//    public void testActualizarCarritoComprasIDInexistente(){
//        
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//
//        long ID_BUSCAR = ID_CARRITO_COMPRAS_MAYOR + 1;
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "No existe un registro de carrito de compras con el ID: " + ID_BUSCAR;
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO = new ActualizacionCarritoComprasDTO(ID_BUSCAR);
//        
//        Random random = new Random();
//        Long idNuevaPaqueteria = listaIdsPaqueteriasRegistradas.get(random.nextInt(listaIdsPaqueteriasRegistradas.size()));
//        
//        actualizacionCarritoComprasDTO.setIdPaqueteria(idNuevaPaqueteria);
//        
//        RegistroInexistenteException ex 
//                = assertThrows(RegistroInexistenteException.class, () -> carritoComprasDAO.actualizar(actualizacionCarritoComprasDTO));
//        
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//    
//    @Test
//    public void testActualizarCarritoComprasIDNulo(){
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "El ID del DTO con la información para actualizar un carrito de compras es nulo.";
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO = new ActualizacionCarritoComprasDTO(null);
//        
//        Random random = new Random();
//        
//        InformacionActualizacionNuloException ex 
//                = assertThrows(InformacionActualizacionNuloException.class, () -> carritoComprasDAO.actualizar(actualizacionCarritoComprasDTO));
//        
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//    
//    @Test
//    public void testActualizarCarritoComprasIDDTONulo(){
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "El DTO recibido que contiene los datos de actualizacion de un carrito de compras es nulo.";
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO = null;
//        
//        InformacionActualizacionNuloException ex 
//                = assertThrows(InformacionActualizacionNuloException.class, () -> carritoComprasDAO.actualizar(actualizacionCarritoComprasDTO));
//        
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//    
//    @Test
//    public void testAgregarCarritoComprasOk() {
//
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        
//        Long nuevoId = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId() + 1;
//        Long idCliente = listaCarritosComprasDTORegistrados.get(0).getIdCliente();
//        List<Long> idsProductos = listaCarritosComprasDTORegistrados.get(0).getIdsProductosCarrito().subList(0, 2);
//
//        CarritoComprasIdsRelacionesDTO nuevoCarrito = new CarritoComprasIdsRelacionesDTO(
//            nuevoId,
//            true,
//            idCliente,
//            null,
//            idsProductos
//        );
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        assertDoesNotThrow(() -> carritoComprasDAO.agregar(nuevoCarrito));
//
//        IdCarritoComprasDTO idDTO = new IdCarritoComprasDTO(nuevoId);
//        Boolean existeCarritoAgregado = assertDoesNotThrow(() -> carritoComprasDAO.existePorId(idDTO));
//        
//        assertTrue(existeCarritoAgregado);
//    }
//
//    @Test
//    public void testAgregarCarritoComprasIdNulo() {
//
//        CarritoComprasIdsRelacionesDTO nuevoCarrito = new CarritoComprasIdsRelacionesDTO(
//            null,
//            true,
//            1L,
//            null,
//            List.of(1L)
//        );
//        
//        String MENSAJE_EXCEPCION_ESPERADO = "El valor del parámetro id del nuevo carrito de compras a registrar es nulo.";
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//
//        AgregarInformacionNulaException ex = assertThrows(AgregarInformacionNulaException.class,() -> carritoComprasDAO.agregar(nuevoCarrito) );
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//
//    @Test
//    public void testAgregarCarritoComprasDTONulo() {
//
//        String MENSAJE_EXCEPCION_ESPERADO =  "El DTO que contiene los datos del nuevo carrito de compras a registrar es nulo.";
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        CarritoComprasIdsRelacionesDTO carritoComprasAgregar = null;
//        
//        AgregarInformacionNulaException ex = assertThrows( AgregarInformacionNulaException.class, () -> carritoComprasDAO.agregar(carritoComprasAgregar));
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//
//    @Test
//    public void testAgregarCarritoComprasClienteInexistente() {
//        
//        listaIdsClientesRegistrados.sort(Comparator.naturalOrder());
//        Long ID_CLIENTE_MAYOR = listaIdsClientesRegistrados.get(listaIdsClientesRegistrados.size() - 1);
//        Long ID_CLIENTE_INEXISTENTE = ID_CLIENTE_MAYOR + 1;
//
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//        Long ID_CARRITO_COMPRAS_NUEVO = ID_CARRITO_COMPRAS_MAYOR + 1;
//
//        CarritoComprasIdsRelacionesDTO nuevoCarrito = new CarritoComprasIdsRelacionesDTO(
//            ID_CARRITO_COMPRAS_NUEVO,
//            true,
//            ID_CLIENTE_INEXISTENTE,
//            null,
//            listaIdsProductosCarritoRegistrados.subList(0, 2)
//        );
//
//        String MENSAJE_EXCEPCION_ESPERADO = String.format("No existe un registro de cliente con el ID: " + ID_CLIENTE_INEXISTENTE);
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        RegistroInexistenteException ex = assertThrows(RegistroInexistenteException.class, () -> carritoComprasDAO.agregar(nuevoCarrito));
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//
//    @Test
//    public void testAgregarCarritoComprasProductoInexistente() {
//        
//        listaIdsProductosCarritoRegistrados.sort(Comparator.naturalOrder());
//        Long ID_PRODUCTO_CARRITO_MAYOR = listaIdsProductosCarritoRegistrados.get(listaIdsProductosCarritoRegistrados.size() - 1);
//        Long ID_PRODUCTO_CARRITO_INEXISTENTE = ID_PRODUCTO_CARRITO_MAYOR + 1;
//
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//        Long ID_CARRITO_COMPRAS_NUEVO = ID_CARRITO_COMPRAS_MAYOR + 1;
//
//        CarritoComprasIdsRelacionesDTO nuevoCarrito = new CarritoComprasIdsRelacionesDTO(
//            ID_CARRITO_COMPRAS_NUEVO,
//            true,
//            listaCarritosComprasDTORegistrados.get(0).getIdCliente(),
//            null,
//            List.of(ID_PRODUCTO_CARRITO_INEXISTENTE)
//        );
//
//        String MENSAJE_EXCEPCION_ESPERADO = String.format("No existe un registro de producto en carrito con el ID: " + ID_PRODUCTO_CARRITO_INEXISTENTE);
//
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        RegistroInexistenteException ex = assertThrows(RegistroInexistenteException.class, () -> carritoComprasDAO.agregar(nuevoCarrito));
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }
//
//    @Test
//    public void testAgregarCarritoComprasPaqueteriaInexistente() {
//        listaIdsPaqueteriasRegistradas.sort(Comparator.naturalOrder());
//        Long ID_PAQUETERIA_MAYOR = listaIdsPaqueteriasRegistradas.get(listaIdsPaqueteriasRegistradas.size() - 1);
//        Long ID_PAQUETERIA_INEXISTENTE = ID_PAQUETERIA_MAYOR + 1;
//
//        listaCarritosComprasDTORegistrados.sort(Comparator.comparing(CarritoComprasDTO::getId));
//        Long ID_CARRITO_COMPRAS_MAYOR = listaCarritosComprasDTORegistrados.get(listaCarritosComprasDTORegistrados.size() - 1).getId();
//        Long ID_CARRITO_COMPRAS_NUEVO = ID_CARRITO_COMPRAS_MAYOR + 1;
//
//        CarritoComprasIdsRelacionesDTO nuevoCarrito = new CarritoComprasIdsRelacionesDTO(
//            ID_CARRITO_COMPRAS_NUEVO,
//            false,
//            listaCarritosComprasDTORegistrados.get(0).getIdCliente(),
//            ID_PAQUETERIA_INEXISTENTE,
//            listaCarritosComprasDTORegistrados.get(0).getIdsProductosCarrito().subList(0, 2)
//        );
//
//        String MENSAJE_EXCEPCION_ESPERADO = String.format("No existe un registro de paqueteria con el ID: " + ID_PAQUETERIA_INEXISTENTE);
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//
//        RegistroInexistenteException ex = assertThrows(
//                RegistroInexistenteException.class, () -> carritoComprasDAO.agregar(nuevoCarrito)
//        );
//
//        assertEquals(ex.getMessage(), MENSAJE_EXCEPCION_ESPERADO);
//    }


}
