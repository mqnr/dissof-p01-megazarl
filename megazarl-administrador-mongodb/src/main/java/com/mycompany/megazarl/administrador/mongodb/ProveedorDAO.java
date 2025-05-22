
package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoCarrito;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Proveedor;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroMismoIdExisteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTO;
import edu.student.itson.dissof.dto.datos.IdProveedorDTO;
import edu.student.itson.dissof.dto.datos.ProveedorIdsRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;


public class ProveedorDAO {
//    private String COLECCION_PROVEEDORES = "Proveedores";
//    private String COLECCION_PRODUCTOS = "Productos";
//    private String COLECCION_DIRECCIONES = "Direcciones";
//    
//    private final String CAMPO_ID = "_id";
//    
//    private String MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION = "El DTO recibido que contiene el ID para realizar la consulta del proveedor es nulo.";
//    private String MENSAJE_ID_NULO_REGISTRO_EXCEPCION = "El ID recibido para realizar la consulta del proveedor es nulo.";
//    private String MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION = "No existe un registro de %s con el ID: %s";
//    private String MENSAJE_DTO_ACTUALIZACION_NULO_EXCEPCION = "El DTO recibido que contiene los datos de actualizacion de un proveedor es nulo.";
//    private String MENSAJE_ID_ACTUALIZACION_NULO_EXCEPCION = "El ID del DTO con la información para actualizar un proveedor es nulo.";
//    private String MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION = "El valor del parámetro %s del nuevo %s a registrar es nulo.";
//    private String MENSAJE_DTO_AGREGAR_NULO_EXCEPCION = "El DTO que contiene los datos del nuevo %s a registrar es nulo.";
//    private String MENSAJE_LISTA_AGREGAR_VACIA_NULA = "La lista de carritos de compra a agregar no puede ser nula o vacía.";
//    private String MENSAJE_VARIOS_REGISTROS_INEXISTENTES = "No existen los registros de %s con los ids recibidos";
//    private String MENSAJE_REGISTRO_EXISTENTE_EXCEPCION = "Ya existe un registro de %s con el mismo valor de %s.";
//    
//    private String NOMBRE_ENTIDAD_PRODUCTO = "producto";
//    private String NOMBRE_ENTIDAD_PROVEEDOR = "proveedor";
//    
// 
//    public boolean existePorId(IdProveedorDTO idProveedorDTO) 
//        throws FormatoInvalidoIdConversionException {
//
//        if(idProveedorDTO == null){
//             throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION);
//        }
//        
//        String idProveedor = (String)idProveedorDTO.getIdProveedor().getId();
//        
//        if (idProveedor == null) {
//            throw new IllegalArgumentException(MENSAJE_ID_NULO_REGISTRO_EXCEPCION);
//        }
//
//        try {
//            
//            ObjectId objectId = new ObjectId(idProveedor);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Document> carritosCollection = baseDatos.getCollection(COLECCION_PROVEEDORES);
//            Document filtro = new Document(CAMPO_ID, objectId);
//            return carritosCollection.countDocuments(filtro) > 0;
//            
//        } catch (IllegalArgumentException ex) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
//        }
//    }
//    
//    public ProveedorIdsRelacionesDTO recuperarPorId(IdProveedorDTO idProveedorDTO) 
//        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
//
//        if(idProveedorDTO == null){
//             throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION);
//        }
//        
//        String idProveedor = (String)idProveedorDTO.getIdProveedor().getId();
//        
//        if (idProveedor == null) {
//            throw new IllegalArgumentException(MENSAJE_ID_NULO_REGISTRO_EXCEPCION);
//        }
//
//        try {
//            
//            ObjectId objectId = new ObjectId(idProveedor);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Proveedor> proveedoresCollection = baseDatos.getCollection(
//                COLECCION_PROVEEDORES, 
//                Proveedor.class
//            );
//            Document filtro = new Document(CAMPO_ID, objectId);
//            Proveedor proveedor = proveedoresCollection.find(filtro).first();
//            
//            if (proveedor == null) {
//                throw new RegistroInexistenteException(
//                    String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_PROVEEDOR, idProveedor)
//                );
//            }
//            
//            List<IdEntidadGenerico> idsProductosGenericos = new ArrayList<>();
//            if (proveedor.get() != null) {
//                for (ProductoCarrito producto : carrito.getProductoCarrito()) {
//                    if (producto.getId() != null) {
//                        idsProductosGenericos.add(
//                            new IdEntidadGenerico(producto.getId().toHexString())
//                        );
//                    }
//                }
//            }
//            
//            ProveedorIdsRelacionesDTO proveedorIdsRelacionesDTO = new ProveedorIdsRelacionesDTO(
//                    new IdEntidadGenerico(proveedor.getId()), 
//                    proveedor.getNombre(), 
//                    proveedor.getTelefono(), 
//                    proveedor.getCorreoElectronico(), 
//                    proveedor.getDireccionImagen(),
//                    new IdEntidadGenerico(proveedor.getIdDireccion()));
//            
//            return proveedorIdsRelacionesDTO;
//            
//        } catch (IllegalArgumentException ex) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
//        }
//    }
//    
//    public void agregar(ProveedorIdsRelacionesDTO nuevoProveedor) 
//    throws AgregarInformacionNulaException, 
//           FormatoInvalidoIdConversionException, 
//           RegistroMismoIdExisteException, 
//           RegistroInexistenteException {
//
//
//        if (nuevoProveedor == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_PROVEEDOR));
//        }
//
//        if (nuevoProveedor.getNombre() == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "nombre", NOMBRE_ENTIDAD_PROVEEDOR));
//        }
//
//        if (nuevoProveedor.getTelefono() == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "telefono", NOMBRE_ENTIDAD_PROVEEDOR));
//        }
//        
//        if (nuevoProveedor.getCorreoElectronico() == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "correoElectronico", NOMBRE_ENTIDAD_PROVEEDOR));
//        }
//        
//        if (nuevoProveedor.getIdDireccion() == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "idCliente", NOMBRE_ENTIDAD_PROVEEDOR));
//        }
//        
//        
//        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//
//        MongoCollection<Proveedor> proveedoresCollection = baseDatos.getCollection(
//            COLECCION_PROVEEDORES, 
//            Proveedor.class
//        );
//
//        String idProveedorString = (String) nuevoProveedor.getId().getId();
//        
//        ObjectId idClienteObjectId;
//        try {
//            idClienteObjectId = new ObjectId(idProveedorString);
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID de proveedor inválido.");
//        }
//        
//        Document filtroProveedor = new Document(CAMPO_ID, idClienteObjectId);
//        
//        if (proveedoresCollection.find(filtroProveedor).first() == null) {
//            throw new RegistroInexistenteException(
//                String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_PROVEEDOR, nuevoProveedor.getId().getId()));
//        }
//
//        List<IdEntidadGenerico> idsProductosGenericos = new ArrayList<>();
//        if (proveedor.getProductoCarrito() != null) {
//            for (ProductoCarrito producto : carrito.getProductoCarrito()) {
//                if (producto.getId() != null) {
//                    idsProductosGenericos.add(
//                        new IdEntidadGenerico(producto.getId().toHexString())
//                    );
//                }
//            }
//        }
//
//        Proveedor proveedor = new Proveedor(
//            new IdEntidadGenerico(proveedor.getId()),
//            nuevoProveedor.getNombre(),
//            nuevoProveedor.getCorreoElectronico(),
//            nuevoProveedor.getDireccionImagen(),
//            nuevoProveedor.getIdDireccion(),
//            
//                
//        );
//
//        carritosCollection.insertOne(carrito);
//    }
//    
//    public void agregar(List<CarritoComprasIdsRelacionesDTO> nuevosCarritos) 
//        throws AgregarInformacionNulaException, 
//               FormatoInvalidoIdConversionException, 
//               RegistroMismoIdExisteException, 
//               RegistroInexistenteException {
//
//        // Validar lista nula o vacía
//        if (nuevosCarritos == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
//        }
//
//        if (nuevosCarritos.isEmpty()) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
//        }
//
//        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//        MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(
//            COLECCION_CARRITOS_COMPRA, 
//            CarritoCompras.class
//        );
//
//        List<CarritoCompras> carritosParaInsertar = new ArrayList<>();
//
//        for (CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO : nuevosCarritos) {
//            // Validar DTO individual
//            if (carritoComprasIdsRelacionesDTO == null) {
//                throw new AgregarInformacionNulaException(
//                    String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
//            }
//
//            if (carritoComprasIdsRelacionesDTO.getEsVigente() == null) {
//                throw new AgregarInformacionNulaException(
//                    String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "esVigente", NOMBRE_ENTIDAD_CARRITO_COMPRAS));
//            }
//
//            if (carritoComprasIdsRelacionesDTO.getIdCliente() == null) {
//                throw new AgregarInformacionNulaException(
//                    String.format(MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, "idCliente", NOMBRE_ENTIDAD_CARRITO_COMPRAS));
//            }
//
//            // Convertir ID cliente
//            String idClienteString = (String) carritoComprasIdsRelacionesDTO.getIdCliente().getId();
//            ObjectId idClienteObjectId;
//            try {
//                idClienteObjectId = new ObjectId(idClienteString);
//            } catch (IllegalArgumentException e) {
//                throw new FormatoInvalidoIdConversionException("Formato de ID de cliente inválido.");
//            }
//
//            // Verificar existencia del cliente
//            MongoCollection<Document> clientesCollection = baseDatos.getCollection(COLECCION_CLIENTES);
//            Document filtroCliente = new Document(CAMPO_ID, idClienteObjectId);
//            if (clientesCollection.find(filtroCliente).first() == null) {
//                throw new RegistroInexistenteException(
//                    String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CLIENTE, idClienteString));
//            }
//
//            // Validar paquetería si existe
//            ObjectId idPaqueteriaObjectId = null;
//            if (carritoComprasIdsRelacionesDTO.getIdPaqueteria() != null) {
//                String idPaqueteriaString = (String) carritoComprasIdsRelacionesDTO.getIdPaqueteria().getId();
//                try {
//                    idPaqueteriaObjectId = new ObjectId(idPaqueteriaString);
//                } catch (IllegalArgumentException e) {
//                    throw new FormatoInvalidoIdConversionException("Formato de ID de paquetería inválido.");
//                }
//
//                MongoCollection<Document> paqueteriasCollection = baseDatos.getCollection(COLECCION_PAQUETERIAS);
//                Document filtroPaqueteria = new Document(CAMPO_ID, idPaqueteriaObjectId);
//                if (paqueteriasCollection.find(filtroPaqueteria).first() == null) {
//                    throw new RegistroInexistenteException(
//                        String.format(MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_PAQUETERIA, idPaqueteriaString));
//                }
//            }
//
//            // Crear entidad
//            CarritoCompras carrito = new CarritoCompras(
//                carritoComprasIdsRelacionesDTO.getEsVigente(),
//                idClienteObjectId,
//                idPaqueteriaObjectId
//            );
//
//            carritosParaInsertar.add(carrito);
//        }
//
//        // Insertar todos los carritos válidos
//        if (!carritosParaInsertar.isEmpty()) {
//            carritosCollection.insertMany(carritosParaInsertar);
//        }
//    }
//    
//    private ProveedorIdsRelacionesDTO convertirProveedorADTO(Proveedor proveedor) {
//
//        IdEntidadGenerico idCarritoGenerico = (proveedor.getId() != null)
//            ? new IdEntidadGenerico(proveedor.getId().toHexString())
//            : null;
//
//        IdEntidadGenerico idClienteGenerico = (proveedor.getCliente() != null)
//            ? new IdEntidadGenerico(carrito.getCliente().toHexString())
//            : null;
//
//        IdEntidadGenerico idPaqueteriaGenerico = null;
//        if (carrito.getPaqueteria() != null) {
//            idPaqueteriaGenerico = new IdEntidadGenerico(carrito.getPaqueteria().toHexString());
//        }
//
//        List<IdEntidadGenerico> idsProductosGenericos = new ArrayList<>();
//        if (carrito.getProductoCarrito() != null) {
//            for (ProductoCarrito producto : carrito.getProductoCarrito()) {
//                if (producto.getId() != null) {
//                    idsProductosGenericos.add(
//                        new IdEntidadGenerico(producto.getId().toHexString())
//                    );
//                }
//            }
//        }
//
//
//        return new CarritoComprasIdsRelacionesDTO(
//            idCarritoGenerico,
//            carrito.getEsVigente(),
//            idClienteGenerico,
//            idPaqueteriaGenerico,
//            idsProductosGenericos
//        );
//    }
}
