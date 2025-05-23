//package com.mycompany.megazarl.administrador.mongodb;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
//import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Sucursal;
//import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
//import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
//import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
//import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
//import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
//import java.util.ArrayList;
//import java.util.List;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//public class SucursalDAO {
//
//    private final String COLECCION_SUCURSALES = "Sucursales";
//    private final String COLECCION_DIRECCIONES = "Direcciones";
//    private final String CAMPO_ID = "_id";
//    
//    // Mensajes de excepción
//    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
//    private final String MENSAJE_ID_NULO = "El ID recibido es nulo.";
//    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
//    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
//    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
//    
//    private final String NOMBRE_ENTIDAD_SUCURSAL = "sucursal";
//    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";
//
//    public boolean existePorId(IdEntidadGenericoDatos idSucursalDTO) 
//        throws FormatoInvalidoIdConversionException {
//        
//        if (idSucursalDTO == null) {
//            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
//        }
//        
//        String idSucursal = (String) idSucursalDTO.getId();
//        
//        try {
//            ObjectId objectId = new ObjectId(idSucursal);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Document> sucursalesCollection = baseDatos.getCollection(COLECCION_SUCURSALES);
//            return sucursalesCollection.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
//        }
//    }
//
//    public SucursalIdsRelacionesDTO recuperarPorId(IdEntidadGenericoDatos idSucursalDTO) 
//        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
//        
//        if (idSucursalDTO == null) {
//            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
//        }
//        
//        String idSucursal = (String) idSucursalDTO.getId();
//        
//        try {
//            ObjectId objectId = new ObjectId(idSucursal);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Sucursal> sucursalesCollection = baseDatos.getCollection(
//                COLECCION_SUCURSALES, 
//                Sucursal.class
//            );
//            
//            Sucursal sucursal = sucursalesCollection.find(new Document(CAMPO_ID, objectId)).first();
//            
//            if (sucursal == null) {
//                throw new RegistroInexistenteException(
//                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_SUCURSAL, idSucursal)
//                );
//            }
//            
//            return convertirSucursalADTO(sucursal);
//            
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
//        }
//    }
//
//    public void agregar(SucursalIdsRelacionesDTO nuevaSucursal) 
//        throws AgregarInformacionNulaException, 
//               FormatoInvalidoIdConversionException, 
//               RegistroInexistenteException {
//        
//        if (nuevaSucursal == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_SUCURSAL));
//        }
//
//        if (nuevaSucursal.esMatriz() == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_NULO, "esMatriz", NOMBRE_ENTIDAD_SUCURSAL));
//        }
//
//        // Validar dirección
//        ObjectId idDireccion = validarYConvertirDireccion(nuevaSucursal.getIdDireccion());
//
//        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//        MongoCollection<Sucursal> sucursalesCollection = baseDatos.getCollection(
//            COLECCION_SUCURSALES, 
//            Sucursal.class
//        );
//
//        Sucursal sucursal = new Sucursal();
//        sucursal.setEsMatriz(nuevaSucursal.esMatriz());
//        sucursal.setDireccion(new Direccion()); // Asignar dirección con ID válido
//        sucursal.getDireccion().setId(idDireccion);
//        
//        sucursalesCollection.insertOne(sucursal);
//    }
//
//    public void agregar(List<SucursalIdsRelacionesDTO> nuevasSucursales) 
//        throws AgregarInformacionNulaException, 
//               FormatoInvalidoIdConversionException, 
//               RegistroInexistenteException {
//        
//        if (nuevasSucursales == null || nuevasSucursales.isEmpty()) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_SUCURSAL));
//        }
//
//        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//        MongoCollection<Sucursal> sucursalesCollection = baseDatos.getCollection(
//            COLECCION_SUCURSALES, 
//            Sucursal.class
//        );
//
//        List<Sucursal> sucursalesParaInsertar = new ArrayList<>();
//        
//        for (SucursalIdsRelacionesDTO sucursalIdsRelacionesDTO : nuevasSucursales) {
//            if (sucursalIdsRelacionesDTO == null) {
//                throw new AgregarInformacionNulaException(
//                    String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_SUCURSAL));
//            }
//
//            if (sucursalIdsRelacionesDTO.esMatriz() == null) {
//                throw new AgregarInformacionNulaException(
//                    String.format(MENSAJE_PARAMETRO_NULO, "esMatriz", NOMBRE_ENTIDAD_SUCURSAL));
//            }
//
//            ObjectId idDireccion = validarYConvertirDireccion(sucursalIdsRelacionesDTO.getIdDireccion());
//            
//            Sucursal sucursal = new Sucursal();
//            sucursal.setEsMatriz(sucursalIdsRelacionesDTO.esMatriz());
//            sucursal.setDireccion(new Direccion());
//            sucursal.getDireccion().setId(idDireccion);
//            
//            sucursalesParaInsertar.add(sucursal);
//        }
//
//        sucursalesCollection.insertMany(sucursalesParaInsertar);
//    }
//
//    private ObjectId validarYConvertirDireccion(IdEntidadGenericoDatos idDireccionDTO) 
//        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
//        
//        if (idDireccionDTO == null) {
//            return null;
//        }
//
//        String idDireccionString = (String) idDireccionDTO.getId();
//        
//        try {
//            ObjectId idDireccion = new ObjectId(idDireccionString);
//            
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Document> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES);
//            
//            if (direccionesCollection.countDocuments(new Document(CAMPO_ID, idDireccion)) == 0) {
//                throw new RegistroInexistenteException(
//                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccionString)
//                );
//            }
//            
//            return idDireccion;
//            
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID de dirección inválido.");
//        }
//    }
//
//    private SucursalIdsRelacionesDTO convertirSucursalADTO(Sucursal sucursal) {
//        IdEntidadGenericoDatos idSucursal = new IdEntidadGenericoDatos(sucursal.getId().toHexString());
//        
//        IdEntidadGenericoDatos idDireccion = null;
//        if (sucursal.getDireccion() != null && sucursal.getDireccion().getId() != null) {
//            idDireccion = new IdEntidadGenericoDatos(sucursal.getDireccion().getId().toHexString());
//        }
//        
//        return new SucursalIdsRelacionesDTO(
//            idSucursal,
//            sucursal.getEsMatriz(),
//            idDireccion
//        );
//    }
//}