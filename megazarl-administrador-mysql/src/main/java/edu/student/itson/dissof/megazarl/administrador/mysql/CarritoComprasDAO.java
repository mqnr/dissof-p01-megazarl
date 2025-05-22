
package edu.student.itson.dissof.megazarl.administrador.mysql;

import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.dto.datos.CarritoComprasIdsRelacionesDTO;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.CarritoCompras;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Cliente;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.Paqueteria;
import edu.student.itson.dissof.megazarl.administrador.mysql.clasesmapeadas.ProductoCarrito;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.AgregarInformacionNulaException;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.InformacionActualizacionNuloException;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.RegistroIdNuloException;
import edu.student.itson.dissof.megazarl.administrador.mysql.excepciones.RegistroInexistenteException;
import edu.student.itson.dissof.megazarl.administrador.mysql.manejadorconexiones.ManejadorConexiones;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class CarritoComprasDAO {
    
    private String MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION = "El DTO recibido que contiene el ID para realizar la consulta del carrito de compras es nulo.";
    private String MENSAJE_ID_NULO_REGISTRO_EXCEPCION = "El ID recibido para realizar la consulta del carrito de compras es nulo.";
    private String MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION = "No existe un registro de %s con el ID: %s";
    private String MENSAJE_DTO_ACTUALIZACION_NULO_EXCEPCION = "El DTO recibido que contiene los datos de actualizacion de un carrito de compras es nulo.";
    private String MENSAJE_ID_ACTUALIZACION_NULO_EXCEPCION = "El ID del DTO con la información para actualizar un carrito de compras es nulo.";
    private String MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION = "El valor del parámetro %s del nuevo %s a registrar es nulo.";
    private String MENSAJE_DTO_AGREGAR_NULO_EXCEPCION = "El DTO que contiene los datos del nuevo %s a registrar es nulo.";
    private String MENSAJE_LISTA_AGREGAR_VACIA_NULA = "La lista de carritos de compra a agregar no puede ser nula o vacía.";
    private String MENSAJE_VARIOS_REGISTROS_INEXISTENTES = "No existen los registros de %s con los ids recibidos";
    
    private String NOMBRE_ENTIDAD_CARRITO_COMPRAS = "carrito de compras";
    private String NOMBRE_ENTIDAD_PAQUETERIA = "paqueteria";
    private String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private String NOMBRE_ENTIDAD_PRODUCTO_CARRITO = "producto en carrito";
    
    
    public CarritoComprasIdsRelacionesDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO) 
            throws RegistroIdNuloException,
            RegistroInexistenteException{
        
        if(idCarritoComprasDTO == null){
            throw new RegistroIdNuloException(MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION);
        }
        
        if(idCarritoComprasDTO.getIdCarritoCompras() == null){
            throw new RegistroIdNuloException(MENSAJE_ID_NULO_REGISTRO_EXCEPCION);
        }
        
        Long idCarritoCompras = idCarritoComprasDTO.getIdCarritoCompras();

        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            String jpqlQueryRecuperarCarritoCompras = """
                                                  SELECT DISTINCT CC           
                                                  FROM CarritoCompras CC
                                                  JOIN FETCH CC.cliente
                                                  JOIN FETCH CC.paqueteria
                                                  JOIN FETCH CC.productosCarritoCompras
                                                  WHERE CC.id = :idCarritoCompras
                                                  """;
        
            TypedQuery<CarritoCompras> queryRecuperarCarritoCompras = entityManager.createQuery(
                    jpqlQueryRecuperarCarritoCompras, 
                    CarritoCompras.class);

            queryRecuperarCarritoCompras.setParameter("idCarritoCompras", idCarritoCompras);

            CarritoCompras carritoCompras;
            try{
                carritoCompras = queryRecuperarCarritoCompras.getSingleResult();
            } catch(NoResultException ex){
                throw new RegistroInexistenteException(String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS, idCarritoCompras));
            }

            List<Long> idsProductosCarrito = new LinkedList<>();

            for(ProductoCarrito productoCarrito: carritoCompras.getProductosCarritoCompras()){
                idsProductosCarrito.add(productoCarrito.getId());
            }

            CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO = new CarritoComprasIdsRelacionesDTO(
                    carritoCompras.getId(), 
                    carritoCompras.getEsVigente(),
                    carritoCompras.getCliente().getId(),
                    idsProductosCarrito
            );

            if(!carritoCompras.getEsVigente() && carritoCompras.getPaqueteria() != null){
                Long idPaqueteriaDTO = carritoCompras.getPaqueteria().getId();

                carritoComprasIdsRelacionesDTO.setIdPaqueteria(idPaqueteriaDTO);
            }

            return carritoComprasIdsRelacionesDTO;
            
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
  
    }
    
    
    public boolean existePorId(IdCarritoComprasDTO idCarritoComprasDTO) throws RegistroIdNuloException{
        
        if(idCarritoComprasDTO == null){
            throw new RegistroIdNuloException(MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION);
        }
        
        if(idCarritoComprasDTO.getIdCarritoCompras() == null){
            throw new RegistroIdNuloException(MENSAJE_ID_NULO_REGISTRO_EXCEPCION);
        }
        
        Long idCarritoCompras = idCarritoComprasDTO.getIdCarritoCompras();

        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            String jpqlQueryRecuperarCarritoCompras = """
                                                  SELECT CC           
                                                  FROM CarritoCompras CC
                                                  WHERE CC.id = :idCarritoCompras
                                                  """;
        
            TypedQuery<CarritoCompras> queryRecuperarCarritoCompras = entityManager.createQuery(
                    jpqlQueryRecuperarCarritoCompras, 
                    CarritoCompras.class);

            queryRecuperarCarritoCompras.setParameter("idCarritoCompras", idCarritoCompras);

            
            try{
                queryRecuperarCarritoCompras.getSingleResult();
                
                return true;
            } catch(NoResultException ex){
                return false;
            }

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
  
    }
    
    public CarritoComprasIdsRelacionesDTO actualizar(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO) 
            throws InformacionActualizacionNuloException, 
            RegistroInexistenteException{
        
        if(actualizacionCarritoComprasDTO == null){
            throw new InformacionActualizacionNuloException(MENSAJE_DTO_ACTUALIZACION_NULO_EXCEPCION);
        }
        
        if(actualizacionCarritoComprasDTO.getId() == null){
            throw new InformacionActualizacionNuloException(MENSAJE_ID_ACTUALIZACION_NULO_EXCEPCION);
        }
        
        Long idCarritoCompras = actualizacionCarritoComprasDTO.getId();

        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            String jpqlQueryRecuperarCarritoCompras = """
                                                  SELECT DISTINCT CC           
                                                  FROM CarritoCompras CC
                                                  JOIN FETCH CC.cliente
                                                  JOIN FETCH CC.paqueteria
                                                  JOIN FETCH CC.productosCarritoCompras
                                                  WHERE CC.id = :idCarritoCompras
                                                  """;
        
            TypedQuery<CarritoCompras> queryRecuperarCarritoCompras = entityManager.createQuery(
                    jpqlQueryRecuperarCarritoCompras, 
                    CarritoCompras.class);

            queryRecuperarCarritoCompras.setParameter("idCarritoCompras", idCarritoCompras);

            CarritoCompras carritoCompras;
            try{
                carritoCompras = queryRecuperarCarritoCompras.getSingleResult();
            } catch(NoResultException ex){
                throw new RegistroInexistenteException(String.format(
                        MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, 
                        NOMBRE_ENTIDAD_CARRITO_COMPRAS,
                        idCarritoCompras));
            }
            
            // Se recuperan los IDs de los productos en carrito actuales
            List<Long> idsProductosCarrito = new LinkedList<>();

            for(ProductoCarrito productoCarrito: carritoCompras.getProductosCarritoCompras()){
                idsProductosCarrito.add(productoCarrito.getId());
            }
            
            // Se recupera el ID del cliente acual
            Long idCliente = carritoCompras.getCliente().getId();
            
            // Se recupera el valor actual de esVigente
            Boolean esVigente = carritoCompras.getEsVigente();
            
            // Se recupera el ID de la paquetería actual
            Long idPaqueteria = null;
            
            if(carritoCompras.getPaqueteria() != null){
                idPaqueteria = carritoCompras.getPaqueteria().getId();
            }
            
            
            CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO = new CarritoComprasIdsRelacionesDTO(
                    idCarritoCompras,
                    esVigente,
                    idCliente,
                    idPaqueteria,
                    idsProductosCarrito);
            
            entityManager.getTransaction().begin();
            
            if(actualizacionCarritoComprasDTO.tieneEsVigente()){
                
                Boolean nuevoEsVigente = actualizacionCarritoComprasDTO.getEsVigente();
                
                String jpqlActualizarEsVigenteCarritoCompras = """
                                                               UPDATE CarritoCompras CC 
                                                               SET CC.esVigente = :nuevoEsVigente 
                                                               WHERE CC.id = :idCarritoCompras
                                                               """;
                
                Query queryAtualizarEsVigenteCarrito = entityManager.createQuery(jpqlActualizarEsVigenteCarritoCompras);
                queryAtualizarEsVigenteCarrito.setParameter("nuevoEsVigente", nuevoEsVigente);
                queryAtualizarEsVigenteCarrito.setParameter("idCarritoCompras", idCarritoCompras);
                queryAtualizarEsVigenteCarrito.executeUpdate();
                
                carritoComprasIdsRelacionesDTO.setEsVigente(nuevoEsVigente);
                
            }
            
            if(actualizacionCarritoComprasDTO.tienePaqueteria()){
                
                Long idNuevaPaqueteria = actualizacionCarritoComprasDTO.getIdPaqueteria();
                
                String jpqlRecuperarPaqueteriaActualizar = """   
                                                           SELECT PA
                                                           FROM Paqueteria PA
                                                           WHERE PA.id = :idNuevaPaqueteria 
                                                           """;
                
                
                TypedQuery<Paqueteria> queryRecuperarNuevaPaqueteria = entityManager.createQuery(jpqlRecuperarPaqueteriaActualizar, Paqueteria.class);
                queryRecuperarNuevaPaqueteria.setParameter("idNuevaPaqueteria", idNuevaPaqueteria);
                
                Paqueteria nuevaPaqueteria;
                
                try{
                    nuevaPaqueteria = queryRecuperarNuevaPaqueteria.getSingleResult();
                    
                } catch(NoResultException ex){
                    throw new RegistroInexistenteException(String.format(
                            MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, 
                            NOMBRE_ENTIDAD_PAQUETERIA, 
                            idCarritoCompras));
                }
                
                String jpqlActualizarEsVigenteCarritoCompras = """
                                                               UPDATE CarritoCompras CC 
                                                               SET CC.paqueteria = :nuevaPaqueteria 
                                                               WHERE CC.id = :idCarritoCompras
                                                               """;
                
                Query queryActualizarEsVigenteCarrito = entityManager.createQuery(jpqlActualizarEsVigenteCarritoCompras);
                queryActualizarEsVigenteCarrito.setParameter("nuevaPaqueteria", nuevaPaqueteria);
                queryActualizarEsVigenteCarrito.setParameter("idCarritoCompras", idCarritoCompras);
                queryActualizarEsVigenteCarrito.executeUpdate();
                
                carritoComprasIdsRelacionesDTO.setIdPaqueteria(nuevaPaqueteria.getId());
            }
            
            entityManager.getTransaction().commit();
            
            return carritoComprasIdsRelacionesDTO;
            
        
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    
    
    public void agregar(CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO) throws AgregarInformacionNulaException, RegistroInexistenteException{
        
        if(carritoComprasIdsRelacionesDTO == null){
            throw new AgregarInformacionNulaException(String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        if(carritoComprasIdsRelacionesDTO.getId() == null){
            throw new AgregarInformacionNulaException(String.format(
                    MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                    "id", 
                    NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        if(carritoComprasIdsRelacionesDTO.getEsVigente() == null){
            throw new AgregarInformacionNulaException(String.format(
                    MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                    "esVigente",
                    NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        if(carritoComprasIdsRelacionesDTO.getIdCliente() == null){
            throw new AgregarInformacionNulaException(String.format(
                    MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                    "idCliente",
                    NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            entityManager.getTransaction().begin();

            // Recuperar cliente
            Cliente cliente = entityManager.find(Cliente.class, carritoComprasIdsRelacionesDTO.getIdCliente());
            if (cliente == null) {
                throw new RegistroInexistenteException(String.format(
                        MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, 
                        NOMBRE_ENTIDAD_CLIENTE, 
                        carritoComprasIdsRelacionesDTO.getIdCliente()));
            }

            // Recuperar productos
            List<ProductoCarrito> productosCarrito = new LinkedList<>();
            for (Long idProductoCarrito : carritoComprasIdsRelacionesDTO.getIdsProductosCarrito()) {
                ProductoCarrito productoCarrito = entityManager.find(ProductoCarrito.class, idProductoCarrito);
                if (productoCarrito == null) {
                    throw new RegistroInexistenteException(String.format(
                            MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION,
                            NOMBRE_ENTIDAD_PRODUCTO_CARRITO, 
                            idProductoCarrito));
                }
                productosCarrito.add(productoCarrito);
            }

            // Recuperar paqueteria
            Paqueteria paqueteria = null;
            if (!carritoComprasIdsRelacionesDTO.getEsVigente()) {
                paqueteria = entityManager.find(Paqueteria.class, carritoComprasIdsRelacionesDTO.getIdPaqueteria());
                if (paqueteria == null) {
                    throw new RegistroInexistenteException(String.format(
                            MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION,
                            NOMBRE_ENTIDAD_PAQUETERIA,
                            carritoComprasIdsRelacionesDTO.getIdPaqueteria()));
                }
            }

            // Se crea y persiste el carrito
            CarritoCompras nuevoCarrito = new CarritoCompras();
            nuevoCarrito.setCliente(cliente);
            nuevoCarrito.setProductosCarritoCompras(productosCarrito);
            nuevoCarrito.setEsVigente(carritoComprasIdsRelacionesDTO.getEsVigente());
            nuevoCarrito.setPaqueteria(paqueteria);

            entityManager.persist(nuevoCarrito);
            entityManager.flush();

            entityManager.getTransaction().commit();

        
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        
        
    }
    
    public void agregar(List<CarritoComprasIdsRelacionesDTO> carritosComprasIdsRelacionesDTO) throws AgregarInformacionNulaException, RegistroInexistenteException {

        if (carritosComprasIdsRelacionesDTO == null || carritosComprasIdsRelacionesDTO.isEmpty()) {
            throw new AgregarInformacionNulaException(String.format(MENSAJE_LISTA_AGREGAR_VACIA_NULA));
        }

        for (CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO : carritosComprasIdsRelacionesDTO) {
            if (carritoComprasIdsRelacionesDTO == null) {
                throw new AgregarInformacionNulaException(String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }

            if (carritoComprasIdsRelacionesDTO.getId() == null) {
                throw new AgregarInformacionNulaException(String.format(
                        MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                        "id", 
                        NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }

            if (carritoComprasIdsRelacionesDTO.getEsVigente() == null) {
                throw new AgregarInformacionNulaException(String.format(
                        MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                        "esVigente",
                        NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }

            if (carritoComprasIdsRelacionesDTO.getIdCliente() == null) {
                throw new AgregarInformacionNulaException(String.format(
                        MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                        "idCliente",
                        NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            for (CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO : carritosComprasIdsRelacionesDTO) {
                // Recuperar cliente
                Cliente cliente = entityManager.find(Cliente.class, carritoComprasIdsRelacionesDTO.getIdCliente());
                if (cliente == null) {
                    throw new RegistroInexistenteException(String.format(
                            MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, 
                            NOMBRE_ENTIDAD_CLIENTE, 
                            carritoComprasIdsRelacionesDTO.getIdCliente()));
                }

                // Recuperar productos
                List<ProductoCarrito> productosCarrito = new LinkedList<>();
                for (Long idProductoCarrito : carritoComprasIdsRelacionesDTO.getIdsProductosCarrito()) {
                    ProductoCarrito productoCarrito = entityManager.find(ProductoCarrito.class, idProductoCarrito);
                    if (productoCarrito == null) {
                        throw new RegistroInexistenteException(String.format(
                                MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION,
                                NOMBRE_ENTIDAD_PRODUCTO_CARRITO, 
                                idProductoCarrito));
                    }
                    productosCarrito.add(productoCarrito);
                }

                // Recuperar paqueteria si es necesario
                Paqueteria paqueteria = null;
                if (!carritoComprasIdsRelacionesDTO.getEsVigente()) {
                    paqueteria = entityManager.find(Paqueteria.class, carritoComprasIdsRelacionesDTO.getIdPaqueteria());
                    if (paqueteria == null) {
                        throw new RegistroInexistenteException(String.format(
                                MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION,
                                NOMBRE_ENTIDAD_PAQUETERIA,
                                carritoComprasIdsRelacionesDTO.getIdPaqueteria()));
                    }
                }

                // Crear y persistir el carrito
                CarritoCompras nuevoCarrito = new CarritoCompras();
                nuevoCarrito.setCliente(cliente);
                nuevoCarrito.setProductosCarritoCompras(productosCarrito);
                nuevoCarrito.setEsVigente(carritoComprasIdsRelacionesDTO.getEsVigente());
                nuevoCarrito.setPaqueteria(paqueteria);

                entityManager.persist(nuevoCarrito);
                entityManager.flush();
            }

            entityManager.getTransaction().commit();

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    
    
    
}
