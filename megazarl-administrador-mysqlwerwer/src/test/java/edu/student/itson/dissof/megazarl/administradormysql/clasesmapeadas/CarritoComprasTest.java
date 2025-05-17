
package edu.student.itson.dissof.megazarl.administradormysql.clasesmapeadas;

import edu.student.itson.dissof.megazarl.administradormysql.CarritoComprasDAO;
import edu.student.itson.dissof.megazarl.administradormysql.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDatosCompletosRelacionesDTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarritoComprasTest {
    
//    private static List<CarritoComprasDTO> listaCarritosComprasDTORegistrados = new LinkedList<>();
//    
//    public CarritoComprasTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//        ManejadorConexiones.setConexionTest(true);
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//        ManejadorConexiones.setConexionTest(false);
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
//        Paqueteria paqueteria1 = new Paqueteria(
//                "DHL", 
//                100F, 
//                120F, 
//                "direccionImagenEjemploPaqueteria1", 
//                direccionPaqueteria1);
//        
//        Direccion direccionPaqueteria2 = new Direccion(
//                "85123",
//                "ColoniaEjemplo2", 
//                "CalleEjemplo2", 
//                "6548");             
//        
//        Paqueteria paqueteria2 = new Paqueteria(
//                "Estafeta", 
//                90F, 
//                110F, 
//                "direccionImagenEjemploPaqueteria2", 
//                direccionPaqueteria2);
//        
//        Direccion direccionPaqueteria3 = new Direccion(
//                "80546",
//                "ColoniaEjemplo3", 
//                "CalleEjemplo3", 
//                "2288");             
//        
//        Paqueteria paqueteria3 = new Paqueteria(
//                "Castores", 
//                80F, 
//                100F, 
//                "direccionImagenEjemploPaqueteria3", 
//                direccionPaqueteria3);
//        
//        
//        // Creación de clientes
//        Direccion direccionCliente1 = new Direccion(
//            "83600",
//            "ColoniaEjemplo4",
//            "CalleEjemplo4",
//            "457"
//        ); 
//        Cliente cliente1 = new Cliente(
//            "José", 
//            "Murrieta", 
//            "Gutiérrez", 
//            "6231112233", 
//            "jose.murrieta@sonora.com.mx", 
//            direccionCliente1
//        );
//
//        Direccion direccionCliente2 = new Direccion(
//            "85000",
//            "ColoniaEjemplo2",
//            "CalleEjemplo2",
//            "123"
//        );
//        Cliente cliente2 = new Cliente(
//            "María", 
//            "López", 
//            "Ramírez", 
//            "6232223344", 
//            "maria.lopez@sonora.com.mx", 
//            direccionCliente2
//        );
//
//        Direccion direccionCliente3 = new Direccion(
//            "84000",
//            "ColoniaEjemplo3",
//            "CalleEjemplo3",
//            "789"
//        );
//        Cliente cliente3 = new Cliente(
//            "Carlos", 
//            "Pérez", 
//            "Hernández", 
//            "6233334455", 
//            "carlos.perez@sonora.com.mx", 
//            direccionCliente3
//        );
//
//        Direccion direccionCliente4 = new Direccion(
//            "83000",
//            "ColoniaEjemplo5",
//            "CalleEjemplo5",
//            "321"
//        );
//        Cliente cliente4 = new Cliente(
//            "Ana", 
//            "García", 
//            "Sánchez", 
//            "6234445566", 
//            "ana.garcia@sonora.com.mx", 
//            direccionCliente4
//        );
//
//        Direccion direccionCliente5 = new Direccion(
//            "85400",
//            "ColoniaEjemplo6",
//            "CalleEjemplo6",
//            "555"
//        );
//        Cliente cliente5 = new Cliente(
//            "Luis", 
//            "Martínez", 
//            "Fernández", 
//            "6235556677", 
//            "luis.martinez@sonora.com.mx", 
//            direccionCliente5
//        );
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
//        Proveedor proveedor1 = new Proveedor(
//            "Harris Moran",
//            "6445122556",
//            "direccionImagenEjemploProveedor1",
//            direccionProveedor1
//        );
//
//        Direccion direccionProveedor2 = new Direccion(
//            "85400",
//            "ColoniaEjemplo8",
//            "CalleEjemplo8",
//            "1974"
//        );
//        Proveedor proveedor2 = new Proveedor(
//            "Syngenta",
//            "6445122557",
//            "direccionImagenEjemploProveedor2",
//            direccionProveedor2
//        );
//
//        Direccion direccionProveedor3 = new Direccion(
//            "85400",
//            "ColoniaEjemplo9",
//            "CalleEjemplo9",
//            "1974"
//        );
//        Proveedor proveedor3 = new Proveedor(
//            "Nunhems",
//            "6445122558",
//            "direccionImagenEjemploProveedor3",
//            direccionProveedor3
//        );
//        
//        
//        
//        Producto producto1 = new Producto(
//            "Sandía",
//            "Warrior",
//            """
//                Las semillas de la variedad Warrior representan la innovación agrícola de Nunhems,
//                diseñadas para brindar a los productores una opción de alto rendimiento y calidad
//                superior. Destaca por su maduración temprana, lo que se traduce en una cosecha más 
//                rápida y eficiente, ideal para mercados que demandan una respuesta ágil y de excelente calidad.
//            """,
//            5,
//            9650d,
//            5d,
//            "/sandiaWarrior.png",
//            proveedor3);
//
//        Producto producto2 = new Producto(
//                "Sandía",
//                "Tailgate",
//                """
//                    Tailgate es una sandia triploide o sin semilla de cascara lisa con bandas verde 
//                    medio y bandas verde oscuro. Es una madurez intermedio precoz con buena capacidad 
//                    de amarre de frutos grandes muy uniformes y potencial de producir rendimiento alto.
//                """,
//                7,
//                9000d,
//                7d,
//                "/sandiaTailgate.png",
//                proveedor2);
//
//        Producto producto3 = new Producto(
//                "Brócoli",
//                "Kepler F1",
//                """
//                    El Brócoli Kepler F1 es un híbrido de alta calidad, diseñado para el mercado fresco 
//                    y de procesamiento. Destaca por sus cabezas uniformes de vívido color verde, excelente 
//                    sabor y resistencia a enfermedades, ofreciendo un rendimiento constante y óptimo para 
//                    el cultivo.
//                """,
//                25,
//                3640d,
//                5d,
//                "/brocoliKeplerF1.png",
//                proveedor1);
//
//        Producto producto4 = new Producto(
//                "Melón",
//                "Saturno",
//                """
//                    Melón tipo Honeydew suave y diseñado para principios de temporada. Presenta frutos 
//                    de forma redonda a ovalada con cavidad pequeña. Los productores de Saturno pueden 
//                    esperar buen sabor y alto nivel de azúcar con una piel fina y alto potencial de rendimiento.
//                """,
//                15,
//                10000d,
//                3d,
//                "/melonSaturno.png",
//                proveedor1);
//        
//        
//        // Se crean los productos en carrito
//        CarritoCompras carritoCompras1 = new CarritoCompras(
//                false, 
//                cliente1, 
//                paqueteria1);
//        
//        CarritoCompras carritoCompras2 = new CarritoCompras(
//                true, 
//                cliente1);
//        
//        CarritoCompras carritoCompras3 = new CarritoCompras(
//                true, 
//                cliente2);
//        
//        CarritoCompras carritoCompras4 = new CarritoCompras(
//                false, 
//                cliente3, 
//                paqueteria2);
//        
//        CarritoCompras carritoCompras5 = new CarritoCompras(
//                true, 
//                cliente3);
//        
//        CarritoCompras carritoCompras6 = new CarritoCompras(
//                true, 
//                cliente3);
//        
//        CarritoCompras carritoCompras7 = new CarritoCompras(
//                false, 
//                cliente4, 
//                paqueteria3);
//        
//        CarritoCompras carritoCompras8 = new CarritoCompras(
//                true, 
//                cliente4);
//        
//        CarritoCompras carritoCompras9 = new CarritoCompras(
//                false, 
//                cliente5, 
//                paqueteria3);      
//        
//        CarritoCompras carritoCompras10 = new CarritoCompras(
//                true, 
//                cliente5);
//        
//        
//        
//        ProductoCarrito productoCarrito1 = new ProductoCarrito(4, carritoCompras1, producto1);
//        ProductoCarrito productoCarrito2 = new ProductoCarrito(7, carritoCompras1, producto2);
//        ProductoCarrito productoCarrito3 = new ProductoCarrito(5, carritoCompras1, producto3);
//        ProductoCarrito productoCarrito4 = new ProductoCarrito(8, carritoCompras1, producto4);
//        ProductoCarrito productoCarrito5 = new ProductoCarrito(6, carritoCompras1, producto1);
//        ProductoCarrito productoCarrito6 = new ProductoCarrito(9, carritoCompras1, producto2);
//        ProductoCarrito productoCarrito7 = new ProductoCarrito(10, carritoCompras1, producto3);
//
//        // CarritoCompras2: 3 productos en carrito
//        ProductoCarrito productoCarrito8 = new ProductoCarrito(4, carritoCompras2, producto4);
//        ProductoCarrito productoCarrito9 = new ProductoCarrito(5, carritoCompras2, producto1);
//        ProductoCarrito productoCarrito10 = new ProductoCarrito(6, carritoCompras2, producto2);
//
//        // CarritoCompras3: 6 productos en carrito
//        ProductoCarrito productoCarrito11 = new ProductoCarrito(7, carritoCompras3, producto3);
//        ProductoCarrito productoCarrito12 = new ProductoCarrito(8, carritoCompras3, producto4);
//        ProductoCarrito productoCarrito13 = new ProductoCarrito(9, carritoCompras3, producto1);
//        ProductoCarrito productoCarrito14 = new ProductoCarrito(10, carritoCompras3, producto2);
//        ProductoCarrito productoCarrito15 = new ProductoCarrito(11, carritoCompras3, producto3);
//        ProductoCarrito productoCarrito16 = new ProductoCarrito(12, carritoCompras3, producto4);
//
//        // CarritoCompras4: 5 productos en carrito
//        ProductoCarrito productoCarrito17 = new ProductoCarrito(13, carritoCompras4, producto1);
//        ProductoCarrito productoCarrito18 = new ProductoCarrito(14, carritoCompras4, producto2);
//        ProductoCarrito productoCarrito19 = new ProductoCarrito(15, carritoCompras4, producto3);
//        ProductoCarrito productoCarrito20 = new ProductoCarrito(16, carritoCompras4, producto4);
//        ProductoCarrito productoCarrito21 = new ProductoCarrito(17, carritoCompras4, producto1);
//
//        // CarritoCompras5: 2 productos en carrito
//        ProductoCarrito productoCarrito22 = new ProductoCarrito(18, carritoCompras5, producto2);
//        ProductoCarrito productoCarrito23 = new ProductoCarrito(19, carritoCompras5, producto3);
//
//        // CarritoCompras6: 4 productos en carrito
//        ProductoCarrito productoCarrito24 = new ProductoCarrito(20, carritoCompras6, producto4);
//        ProductoCarrito productoCarrito25 = new ProductoCarrito(21, carritoCompras6, producto1);
//        ProductoCarrito productoCarrito26 = new ProductoCarrito(22, carritoCompras6, producto2);
//        ProductoCarrito productoCarrito27 = new ProductoCarrito(23, carritoCompras6, producto3);
//
//        // CarritoCompras7: 5 productos en carrito
//        ProductoCarrito productoCarrito28 = new ProductoCarrito(24, carritoCompras7, producto4);
//        ProductoCarrito productoCarrito29 = new ProductoCarrito(25, carritoCompras7, producto1);
//        ProductoCarrito productoCarrito30 = new ProductoCarrito(26, carritoCompras7, producto2);
//        ProductoCarrito productoCarrito31 = new ProductoCarrito(27, carritoCompras7, producto3);
//        ProductoCarrito productoCarrito32 = new ProductoCarrito(28, carritoCompras7, producto4);
//
//        // CarritoCompras8: 4 productos en carrito
//        ProductoCarrito productoCarrito33 = new ProductoCarrito(29, carritoCompras8, producto1);
//        ProductoCarrito productoCarrito34 = new ProductoCarrito(30, carritoCompras8, producto2);
//        ProductoCarrito productoCarrito35 = new ProductoCarrito(31, carritoCompras8, producto3);
//        ProductoCarrito productoCarrito36 = new ProductoCarrito(32, carritoCompras8, producto4);
//
//        // CarritoCompras9: 3 productos en carrito
//        ProductoCarrito productoCarrito37 = new ProductoCarrito(33, carritoCompras9, producto1);
//        ProductoCarrito productoCarrito38 = new ProductoCarrito(34, carritoCompras9, producto2);
//        ProductoCarrito productoCarrito39 = new ProductoCarrito(35, carritoCompras9, producto3);
//
//        // CarritoCompras10: 1 producto en carrito
//        ProductoCarrito productoCarrito40 = new ProductoCarrito(36, carritoCompras10, producto4);
//                
//
//        entityManager.persist(paqueteria1);
//        entityManager.persist(paqueteria2);
//        entityManager.persist(paqueteria3);
//        
//        entityManager.persist(proveedor1);
//        entityManager.persist(proveedor2);
//        entityManager.persist(proveedor3);
//        
//        entityManager.persist(cliente1);
//        entityManager.persist(cliente2);
//        entityManager.persist(cliente3);
//        entityManager.persist(cliente4);
//        entityManager.persist(cliente5);
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
//        entityManager.getTransaction().commit();
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras1.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras1.getCliente().getNombres(), 
//                                carritoCompras1.getCliente().getApellidoPaterno(), 
//                                carritoCompras1.getCliente().getApellidoMaterno(), 
//                                carritoCompras1.getCliente().getTelefono(), 
//                                carritoCompras1.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras1.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras1.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras1.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras1.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        ),
//                        new PaqueteriaDatosCompletosRelacionesDTO(
//                                carritoCompras1.getPaqueteria().getNombre(), 
//                                carritoCompras1.getPaqueteria().getCobroHora(),
//                                carritoCompras1.getPaqueteria().getCobroKg(),
//                                carritoCompras1.getPaqueteria().getDireccionImagenPaqueteria(), 
//                                new DireccionDTO(
//                                    carritoCompras1.getPaqueteria().getDireccion().getCodigoPostal(),
//                                    carritoCompras1.getPaqueteria().getDireccion().getColonia(),
//                                    carritoCompras1.getPaqueteria().getDireccion().getCalle(),
//                                    carritoCompras1.getPaqueteria().getDireccion().getNumero()
//                                )
//                       
//                       )
//                )
//        );
//        
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras2.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras2.getCliente().getNombres(), 
//                                carritoCompras2.getCliente().getApellidoPaterno(), 
//                                carritoCompras2.getCliente().getApellidoMaterno(), 
//                                carritoCompras2.getCliente().getTelefono(), 
//                                carritoCompras2.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras2.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras2.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras2.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras2.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras3.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras3.getCliente().getNombres(), 
//                                carritoCompras3.getCliente().getApellidoPaterno(), 
//                                carritoCompras3.getCliente().getApellidoMaterno(), 
//                                carritoCompras3.getCliente().getTelefono(), 
//                                carritoCompras3.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras3.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras3.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras3.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras3.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras4.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras4.getCliente().getNombres(), 
//                                carritoCompras4.getCliente().getApellidoPaterno(), 
//                                carritoCompras4.getCliente().getApellidoMaterno(), 
//                                carritoCompras4.getCliente().getTelefono(), 
//                                carritoCompras4.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras4.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras4.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras4.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras4.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        ),
//                        new PaqueteriaDatosCompletosRelacionesDTO(
//                                carritoCompras4.getPaqueteria().getNombre(), 
//                                carritoCompras4.getPaqueteria().getCobroHora(),
//                                carritoCompras4.getPaqueteria().getCobroKg(),
//                                carritoCompras4.getPaqueteria().getDireccionImagenPaqueteria(), 
//                                new DireccionDTO(
//                                    carritoCompras4.getPaqueteria().getDireccion().getCodigoPostal(),
//                                    carritoCompras4.getPaqueteria().getDireccion().getColonia(),
//                                    carritoCompras4.getPaqueteria().getDireccion().getCalle(),
//                                    carritoCompras4.getPaqueteria().getDireccion().getNumero()
//                                )
//                       
//                       )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras5.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras5.getCliente().getNombres(), 
//                                carritoCompras5.getCliente().getApellidoPaterno(), 
//                                carritoCompras5.getCliente().getApellidoMaterno(), 
//                                carritoCompras5.getCliente().getTelefono(), 
//                                carritoCompras5.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras5.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras5.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras5.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras5.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras6.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras6.getCliente().getNombres(), 
//                                carritoCompras6.getCliente().getApellidoPaterno(), 
//                                carritoCompras6.getCliente().getApellidoMaterno(), 
//                                carritoCompras6.getCliente().getTelefono(), 
//                                carritoCompras6.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras6.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras6.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras6.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras6.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras7.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras7.getCliente().getNombres(), 
//                                carritoCompras7.getCliente().getApellidoPaterno(), 
//                                carritoCompras7.getCliente().getApellidoMaterno(), 
//                                carritoCompras7.getCliente().getTelefono(), 
//                                carritoCompras7.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras7.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras7.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras7.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras7.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        ),
//                        new PaqueteriaDatosCompletosRelacionesDTO(
//                                carritoCompras7.getPaqueteria().getNombre(), 
//                                carritoCompras7.getPaqueteria().getCobroHora(),
//                                carritoCompras7.getPaqueteria().getCobroKg(),
//                                carritoCompras7.getPaqueteria().getDireccionImagenPaqueteria(), 
//                                new DireccionDTO(
//                                    carritoCompras7.getPaqueteria().getDireccion().getCodigoPostal(),
//                                    carritoCompras7.getPaqueteria().getDireccion().getColonia(),
//                                    carritoCompras7.getPaqueteria().getDireccion().getCalle(),
//                                    carritoCompras7.getPaqueteria().getDireccion().getNumero()
//                                )
//                       
//                       )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras8.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras8.getCliente().getNombres(), 
//                                carritoCompras8.getCliente().getApellidoPaterno(), 
//                                carritoCompras8.getCliente().getApellidoMaterno(), 
//                                carritoCompras8.getCliente().getTelefono(), 
//                                carritoCompras8.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras8.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras8.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras8.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras8.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras9.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras9.getCliente().getNombres(), 
//                                carritoCompras9.getCliente().getApellidoPaterno(), 
//                                carritoCompras9.getCliente().getApellidoMaterno(), 
//                                carritoCompras9.getCliente().getTelefono(), 
//                                carritoCompras9.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras9.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras9.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras9.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras9.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        ),
//                        new PaqueteriaDatosCompletosRelacionesDTO(
//                                carritoCompras9.getPaqueteria().getNombre(), 
//                                carritoCompras9.getPaqueteria().getCobroHora(),
//                                carritoCompras9.getPaqueteria().getCobroKg(),
//                                carritoCompras7.getPaqueteria().getDireccionImagenPaqueteria(), 
//                                new DireccionDTO(
//                                    carritoCompras9.getPaqueteria().getDireccion().getCodigoPostal(),
//                                    carritoCompras9.getPaqueteria().getDireccion().getColonia(),
//                                    carritoCompras9.getPaqueteria().getDireccion().getCalle(),
//                                    carritoCompras9.getPaqueteria().getDireccion().getNumero()
//                                )
//                       
//                       )
//                )
//        );
//        
//        listaCarritosComprasDTORegistrados.add(
//                new CarritoComprasDatosCompletosRelacionesDTO(  
//                        carritoCompras10.getEsVigente(),    
//                        new ClienteDatosCompletosRelacionesDTO(
//                                carritoCompras10.getCliente().getNombres(), 
//                                carritoCompras10.getCliente().getApellidoPaterno(), 
//                                carritoCompras10.getCliente().getApellidoMaterno(), 
//                                carritoCompras10.getCliente().getTelefono(), 
//                                carritoCompras10.getCliente().getCorreoElectronico(),
//                                new DireccionDTO(
//                                        carritoCompras10.getCliente().getDireccionEnvio().getCodigoPostal(),
//                                        carritoCompras10.getCliente().getDireccionEnvio().getColonia(),
//                                        carritoCompras10.getCliente().getDireccionEnvio().getCalle(),
//                                        carritoCompras10.getCliente().getDireccionEnvio().getNumero()
//                                )
//                        )
//                )
//        );
//        
//        
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
//        String jpqlQueryBorrarDirecciones = "DELETE FROM Direccion";
//        String jpqlQueryBorrarPaqueterias = "DELETE FROM Paqueteria";
//        String jpqlQueryBorrarProveedores = "DELETE FROM Proveedor";
//        String jpqlQueryBorrarClientes = "DELETE FROM Cliente";
//        String jpqlQueryBorrarCarritosCompras = "DELETE FROM CarritoCompras";
//        String jpqlQueryBorrarProductosCarritosCompras = "DELETE FROM CarritCompras";
//        
//        entityManager.createQuery(jpqlQueryBorrarDirecciones).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarPaqueterias).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarProveedores).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarClientes).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarCarritosCompras).executeUpdate();
//        entityManager.createQuery(jpqlQueryBorrarProductosCarritosCompras).executeUpdate();
//        
//        listaCarritosComprasDTORegistrados = new ArrayList<>();
//        
//        entityManager.getTransaction().commit();
//    }
//
//    /**
//     * Test of getId method, of class CarritoCompras.
//     */
//    @Test
//    public void testObtenerCarritComprasPorIdOk() {
//        
//        final Integer ID_CARRITO_COMPRAS = 2;
//        
//        final IdCarritoComprasDTO ID_CARRITO_COMPRAS_DTO = new IdCarritoComprasDTO((long)ID_CARRITO_COMPRAS);
//        
//        CarritoComprasDAO carritoComprasDAO = new CarritoComprasDAO();
//        
//        final CarritoComprasDTO CARRITO_COMPRAS_DTO_ESPERADO = listaCarritosComprasDTORegistrados.get(ID_CARRITO_COMPRAS);
//        
//        CarritoComprasDTO carritoComprasDTOResultado = assertDoesNotThrow(() -> carritoComprasDAO.recuperarPorId(ID_CARRITO_COMPRAS_DTO));
//        
//        assertNotNull(carritoComprasDTOResultado);
//        
//        assertEquals(CARRITO_COMPRAS_DTO_ESPERADO.getId(), carritoComprasDTOResultado.getId());
//        assertEquals(CARRITO_COMPRAS_DTO_ESPERADO.getEsVigente(), carritoComprasDTOResultado.getEsVigente());
//        assertEquals(CARRITO_COMPRAS_DTO_ESPERADO.getIdCliente(), carritoComprasDTOResultado.getIdCliente());
//        
//        if(CARRITO_COMPRAS_DTO_ESPERADO.getEsVigente()){
//            assertEquals(CARRITO_COMPRAS_DTO_ESPERADO.getIdPaqueteria(), carritoComprasDTOResultado.getIdPaqueteria());
//        } else{
//            assertNull(carritoComprasDTOResultado.getIdPaqueteria());
//        }
//        
//        for(int i = 0; i < CARRITO_COMPRAS_DTO_ESPERADO.getIdsProductosCarrito().size(); i++){
//            
//            IdProductoCarritoDTO idProductoCarritoEsperado = CARRITO_COMPRAS_DTO_ESPERADO.getIdsProductosCarrito().get(i);
//            IdProductoCarritoDTO idProductoCarritoResultado = carritoComprasDTOResultado.getIdsProductosCarrito().get(i);
//            
//            assertNotNull(idProductoCarritoEsperado.getIdProductoCarrito());
//            assertEquals(idProductoCarritoEsperado.getIdProductoCarrito(), idProductoCarritoResultado.getIdProductoCarrito());
//            
//        }
//        
//
//    }
//
//    /**
//     * Test of setId method, of class CarritoCompras.
//     */
//    @Test
//    public void testSetId() {
//        System.out.println("setId");
//        Long id = null;
//        CarritoCompras instance = new CarritoCompras();
//        instance.setId(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEsVigente method, of class CarritoCompras.
//     */
//    @Test
//    public void testGetEsVigente() {
//        System.out.println("getEsVigente");
//        CarritoCompras instance = new CarritoCompras();
//        Boolean expResult = null;
//        Boolean result = instance.getEsVigente();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCliente method, of class CarritoCompras.
//     */
//    @Test
//    public void testGetCliente() {
//        System.out.println("getCliente");
//        CarritoCompras instance = new CarritoCompras();
//        Cliente expResult = null;
//        Cliente result = instance.getCliente();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCliente method, of class CarritoCompras.
//     */
//    @Test
//    public void testSetCliente() {
//        System.out.println("setCliente");
//        Cliente cliente = null;
//        CarritoCompras instance = new CarritoCompras();
//        instance.setCliente(cliente);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getProductosCarritoCompras method, of class CarritoCompras.
//     */
//    @Test
//    public void testGetProductosCarritoCompras() {
//        System.out.println("getProductosCarritoCompras");
//        CarritoCompras instance = new CarritoCompras();
//        List<ProductoCarrito> expResult = null;
//        List<ProductoCarrito> result = instance.getProductosCarritoCompras();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of agregarProductoCarritoCompras method, of class CarritoCompras.
//     */
//    @Test
//    public void testAgregarProductoCarritoCompras() {
//        System.out.println("agregarProductoCarritoCompras");
//        ProductoCarrito productoCarritoCompras = null;
//        CarritoCompras instance = new CarritoCompras();
//        instance.agregarProductoCarritoCompras(productoCarritoCompras);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class CarritoCompras.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        CarritoCompras instance = new CarritoCompras();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class CarritoCompras.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object object = null;
//        CarritoCompras instance = new CarritoCompras();
//        boolean expResult = false;
//        boolean result = instance.equals(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class CarritoCompras.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        CarritoCompras instance = new CarritoCompras();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
