
package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.CarritoCompras;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoCarrito;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroMismoIdExisteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.dto.datos.CarritoComprasIdsRelacionesDTO;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;


public class CarritoComprasDAO {
    
    private String COLECCION_CARRITOS_COMPRA = "CarritosCompra";
    private String COLECCION_CLIENTES = "Clientes";
    private String COLECCION_PAQUETERIAS = "Paqueterias";
    
    private final String CAMPO_ID = "_id";
    
    private String MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION = "El DTO recibido que contiene el ID para realizar la consulta del carrito de compras es nulo.";
    private String MENSAJE_ID_NULO_REGISTRO_EXCEPCION = "El ID recibido para realizar la consulta del carrito de compras es nulo.";
    private String MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION = "No existe un registro de %s con el ID: %s";
    private String MENSAJE_DTO_ACTUALIZACION_NULO_EXCEPCION = "El DTO recibido que contiene los datos de actualizacion de un carrito de compras es nulo.";
    private String MENSAJE_ID_ACTUALIZACION_NULO_EXCEPCION = "El ID del DTO con la información para actualizar un carrito de compras es nulo.";
    private String MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION = "El valor del parámetro %s del nuevo %s a registrar es nulo.";
    private String MENSAJE_DTO_AGREGAR_NULO_EXCEPCION = "El DTO que contiene los datos del nuevo %s a registrar es nulo.";
    private String MENSAJE_LISTA_AGREGAR_VACIA_NULA = "La lista de carritos de compra a agregar no puede ser nula o vacía.";
    private String MENSAJE_VARIOS_REGISTROS_INEXISTENTES = "No existen los registros de %s con los ids recibidos";
    private String MENSAJE_REGISTRO_EXISTENTE_EXCEPCION = "Ya existe un registro de %s con el mismo valor de %s.";
    
    private String NOMBRE_ENTIDAD_CARRITO_COMPRAS = "carrito de compras";
    private String NOMBRE_ENTIDAD_PAQUETERIA = "paqueteria";
    private String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private String NOMBRE_ENTIDAD_PRODUCTO_CARRITO = "producto en carrito";
    
 
    public boolean existePorId(IdCarritoComprasDTO idCarritoComprasDTO) 
        throws FormatoInvalidoIdConversionException {

        if(idCarritoComprasDTO == null){
             throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION);
        }
        
        String idCarritoCompras = (String)idCarritoComprasDTO.getIdCarritoCompras().getId();
        
        if (idCarritoCompras == null) {
            throw new IllegalArgumentException(MENSAJE_ID_NULO_REGISTRO_EXCEPCION);
        }

        try {
            
            ObjectId objectId = new ObjectId(idCarritoCompras);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> carritosCollection = baseDatos.getCollection(COLECCION_CARRITOS_COMPRA);
            Document filtro = new Document(CAMPO_ID, objectId);
            return carritosCollection.countDocuments(filtro) > 0;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }
    
    public CarritoComprasIdsRelacionesDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {

        if(idCarritoComprasDTO == null){
             throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION);
        }
        
        String idCarritoCompras = (String)idCarritoComprasDTO.getIdCarritoCompras().getId();
        
        if (idCarritoCompras == null) {
            throw new IllegalArgumentException(MENSAJE_ID_NULO_REGISTRO_EXCEPCION);
        }

        try {
            
            ObjectId objectId = new ObjectId(idCarritoCompras);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(
                COLECCION_CARRITOS_COMPRA, 
                CarritoCompras.class
            );
            Document filtro = new Document(CAMPO_ID, objectId);
            CarritoCompras carrito = carritosCollection.find(filtro).first();
            
            if (carrito == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS, idCarritoCompras)
                );
            }
            
            List<IdEntidadGenerico> idsProductosGenericos = new ArrayList<>();
            if (carrito.getProductosCarrito() != null) {
                for (ProductoCarrito producto : carrito.getProductosCarrito()) {
                    if (producto.getId() != null) {
                        idsProductosGenericos.add(
                            new IdEntidadGenerico(producto.getId().toHexString())
                        );
                    }
                }
            }
      
            CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO 
                    = new CarritoComprasIdsRelacionesDTO(
                            new IdEntidadGenerico(carrito.getIdCliente()),
                            carrito.getEsVigente(),
                            new IdEntidadGenerico(carrito.getIdCliente()),
                            idsProductosGenericos);
            
            
            return carritoComprasIdsRelacionesDTO;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }
    
    public CarritoComprasIdsRelacionesDTO actualizar(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {

        if (actualizacionCarritoComprasDTO == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS)
            );
        }

        if (actualizacionCarritoComprasDTO.getId() == null || actualizacionCarritoComprasDTO.getId().getId() == null) {
            throw new AgregarInformacionNulaException("El ID del carrito no puede ser nulo.");
        }

        String idCarritoString = (String) actualizacionCarritoComprasDTO.getId().getId();
        ObjectId idCarrito;
        try {
            idCarrito = new ObjectId(idCarritoString);
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID del carrito inválido.");
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(
            COLECCION_CARRITOS_COMPRA, 
            CarritoCompras.class
        );

        // Verificar existencia del carrito
        Document filtroCarrito = new Document(CAMPO_ID, idCarrito);
        CarritoCompras carritoExistente = carritosCollection.find(filtroCarrito).first();
        if (carritoExistente == null) {
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS, idCarritoString)
            );
        }

        // Construir actualizaciones
        Document actualizaciones = new Document();
        if (actualizacionCarritoComprasDTO.tieneEsVigente()) {
            actualizaciones.append("esVigente", actualizacionCarritoComprasDTO.getEsVigente());
        }

        ObjectId idPaqueteriaActualizado = null;
        if (actualizacionCarritoComprasDTO.tienePaqueteria()) {
            String idPaqueteriaString = (String) actualizacionCarritoComprasDTO.getIdPaqueteria().getId();
            try {
                idPaqueteriaActualizado = new ObjectId(idPaqueteriaString);
            } catch (IllegalArgumentException e) {
                throw new FormatoInvalidoIdConversionException("Formato de ID de paquetería inválido.");
            }

            // Validar existencia de la nueva paquetería
            MongoCollection<Document> paqueteriasCollection = baseDatos.getCollection(COLECCION_PAQUETERIAS);
            Document filtroPaqueteria = new Document(CAMPO_ID, idPaqueteriaActualizado);
            if (paqueteriasCollection.find(filtroPaqueteria).first() == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_PAQUETERIA, idPaqueteriaString)
                );
            }
            actualizaciones.append("idPaqueteria", idPaqueteriaActualizado);
        }

        // Si no hay cambios, retornar DTO actual
        if (actualizaciones.isEmpty()) {
            return convertirCarritoADTO(carritoExistente);
        }

        // Aplicar actualización
        carritosCollection.updateOne(filtroCarrito, new Document("$set", actualizaciones));

        // Recuperar carrito actualizado
        CarritoCompras carritoActualizado = carritosCollection.find(filtroCarrito).first();

        // Convertir a DTO
        return convertirCarritoADTO(carritoActualizado);
    }
   
    public void agregar(CarritoComprasIdsRelacionesDTO nuevoCarrito) 
    throws AgregarInformacionNulaException, 
           FormatoInvalidoIdConversionException, 
           RegistroMismoIdExisteException, 
           RegistroInexistenteException {


        if (nuevoCarrito == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }

        if (nuevoCarrito.getEsVigente() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "esVigente", NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }

        if (nuevoCarrito.getIdCliente() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "idCliente", NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();

        MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(
            COLECCION_CARRITOS_COMPRA, 
            CarritoCompras.class
        );

        String idClienteString = (String) nuevoCarrito.getIdCliente().getId();
        
        ObjectId idClienteObjectId;
        try {
            idClienteObjectId = new ObjectId(idClienteString);
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID de cliente inválido.");
        }
        
        MongoCollection<Document> clientesCollection = baseDatos.getCollection(COLECCION_CLIENTES);
        Document filtroCliente = new Document(CAMPO_ID, idClienteObjectId);
        
        if (clientesCollection.find(filtroCliente).first() == null) {
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CLIENTE, nuevoCarrito.getIdCliente().getId()));
        }

        // Validar paquetería si existe
        ObjectId idPaqueteriaObjectId = null;
        if (nuevoCarrito.getIdPaqueteria() != null) {
            String idPaqueteriaString = (String) nuevoCarrito.getIdPaqueteria().getId();
            try {
                idPaqueteriaObjectId = new ObjectId(idPaqueteriaString);
            } catch (IllegalArgumentException e) {
                throw new FormatoInvalidoIdConversionException("Formato de ID de paquetería inválido.");
            }

            MongoCollection<Document> paqueteriasCollection = baseDatos.getCollection(COLECCION_PAQUETERIAS);
            Document filtroPaqueteria = new Document(CAMPO_ID, idPaqueteriaObjectId);

            if (paqueteriasCollection.find(filtroPaqueteria).first() == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_PAQUETERIA, nuevoCarrito.getIdPaqueteria().getId()));
            }
        }


        CarritoCompras carrito = new CarritoCompras(
            nuevoCarrito.getEsVigente(),
            idClienteObjectId,
            idPaqueteriaObjectId != null ? idPaqueteriaObjectId : null
        );

        carritosCollection.insertOne(carrito);
    }
    
    public void agregar(List<CarritoComprasIdsRelacionesDTO> nuevosCarritos) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroMismoIdExisteException, 
               RegistroInexistenteException {

        // Validar lista nula o vacía
        if (nuevosCarritos == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }

        if (nuevosCarritos.isEmpty()) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(
            COLECCION_CARRITOS_COMPRA, 
            CarritoCompras.class
        );

        List<CarritoCompras> carritosParaInsertar = new ArrayList<>();

        for (CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO : nuevosCarritos) {
            // Validar DTO individual
            if (carritoComprasIdsRelacionesDTO == null) {
                throw new AgregarInformacionNulaException(
                    String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }

            if (carritoComprasIdsRelacionesDTO.getEsVigente() == null) {
                throw new AgregarInformacionNulaException(
                    String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "esVigente", NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }

            if (carritoComprasIdsRelacionesDTO.getIdCliente() == null) {
                throw new AgregarInformacionNulaException(
                    String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "idCliente", NOMBRE_ENTIDAD_CARRITO_COMPRAS));
            }

            // Convertir ID cliente
            String idClienteString = (String) carritoComprasIdsRelacionesDTO.getIdCliente().getId();
            ObjectId idClienteObjectId;
            try {
                idClienteObjectId = new ObjectId(idClienteString);
            } catch (IllegalArgumentException e) {
                throw new FormatoInvalidoIdConversionException("Formato de ID de cliente inválido.");
            }

            // Verificar existencia del cliente
            MongoCollection<Document> clientesCollection = baseDatos.getCollection(COLECCION_CLIENTES);
            Document filtroCliente = new Document(CAMPO_ID, idClienteObjectId);
            if (clientesCollection.find(filtroCliente).first() == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CLIENTE, idClienteString));
            }

            // Validar paquetería si existe
            ObjectId idPaqueteriaObjectId = null;
            if (carritoComprasIdsRelacionesDTO.getIdPaqueteria() != null) {
                String idPaqueteriaString = (String) carritoComprasIdsRelacionesDTO.getIdPaqueteria().getId();
                try {
                    idPaqueteriaObjectId = new ObjectId(idPaqueteriaString);
                } catch (IllegalArgumentException e) {
                    throw new FormatoInvalidoIdConversionException("Formato de ID de paquetería inválido.");
                }

                MongoCollection<Document> paqueteriasCollection = baseDatos.getCollection(COLECCION_PAQUETERIAS);
                Document filtroPaqueteria = new Document(CAMPO_ID, idPaqueteriaObjectId);
                if (paqueteriasCollection.find(filtroPaqueteria).first() == null) {
                    throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_PAQUETERIA, idPaqueteriaString));
                }
            }

            // Crear entidad
            CarritoCompras carrito = new CarritoCompras(
                carritoComprasIdsRelacionesDTO.getEsVigente(),
                idClienteObjectId,
                idPaqueteriaObjectId
            );

            carritosParaInsertar.add(carrito);
        }

        // Insertar todos los carritos válidos
        if (!carritosParaInsertar.isEmpty()) {
            carritosCollection.insertMany(carritosParaInsertar);
        }
    }
    
    private CarritoComprasIdsRelacionesDTO convertirCarritoADTO(CarritoCompras carrito) {

        IdEntidadGenerico idCarritoGenerico = (carrito.getId() != null)
            ? new IdEntidadGenerico(carrito.getId().toHexString())
            : null;

        IdEntidadGenerico idClienteGenerico = (carrito.getIdCliente() != null)
            ? new IdEntidadGenerico(carrito.getIdCliente().toHexString())
            : null;

        IdEntidadGenerico idPaqueteriaGenerico = null;
        if (carrito.getPaqueteria() != null) {
            idPaqueteriaGenerico = new IdEntidadGenerico(carrito.getPaqueteria().toHexString());
        }

        List<IdEntidadGenerico> idsProductosGenericos = new ArrayList<>();
        if (carrito.getProductosCarrito() != null) {
            for (ProductoCarrito producto : carrito.getProductosCarrito()) {
                if (producto.getId() != null) {
                    idsProductosGenericos.add(
                        new IdEntidadGenerico(producto.getId().toHexString())
                    );
                }
            }
        }


        return new CarritoComprasIdsRelacionesDTO(
            idCarritoGenerico,
            carrito.getEsVigente(),
            idClienteGenerico,
            idPaqueteriaGenerico,
            idsProductosGenericos
        );
    }

    
}
