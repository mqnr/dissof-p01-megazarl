package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class DireccionDAO {
    
    private final String COLECCION_DIRECCIONES = "Direcciones";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_ID_NULO = "El ID recibido es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";

    public boolean existePorId(IdEntidadGenerico idDireccionDTO) 
        throws FormatoInvalidoIdConversionException {
        
        if (idDireccionDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idDireccion = (String) idDireccionDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idDireccion);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES);
            return direccionesCollection.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public DireccionDTO recuperarPorId(IdEntidadGenerico idDireccionDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
        
        if (idDireccionDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idDireccion = (String) idDireccionDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idDireccion);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Direccion> direccionesCollection = baseDatos.getCollection(
                COLECCION_DIRECCIONES, 
                Direccion.class
            );
            
            Direccion direccion = direccionesCollection.find(new Document(CAMPO_ID, objectId)).first();
            
            if (direccion == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccion)
                );
            }
            
            return convertirDireccionADTO(direccion);
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public void agregar(DireccionDTO nuevaDireccion) 
        throws FormatoInvalidoIdConversionException {
        
        if (nuevaDireccion == null) {
            throw new IllegalArgumentException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_DIRECCION));
        }

        // Validar campos obligatorios
        if (nuevaDireccion.getCodigoPostal() == null) {
            throw new IllegalArgumentException(
                String.format(MENSAJE_PARAMETRO_NULO, "código postal", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (nuevaDireccion.getColonia() == null) {
            throw new IllegalArgumentException(
                String.format(MENSAJE_PARAMETRO_NULO, "colonia", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (nuevaDireccion.getCalle() == null) {
            throw new IllegalArgumentException(
                String.format(MENSAJE_PARAMETRO_NULO, "calle", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (nuevaDireccion.getNumero() == null) {
            throw new IllegalArgumentException(
                String.format(MENSAJE_PARAMETRO_NULO, "número", NOMBRE_ENTIDAD_DIRECCION));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Direccion> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES, Direccion.class);

        Direccion direccion = new Direccion(
            nuevaDireccion.getCodigoPostal(),
            nuevaDireccion.getColonia(),
            nuevaDireccion.getCalle(),
            nuevaDireccion.getNumero()
        );

        direccionesCollection.insertOne(direccion);
    }

    public void agregar(List<DireccionDTO> nuevasDirecciones) {
        
        if (nuevasDirecciones == null || nuevasDirecciones.isEmpty()) {
            throw new IllegalArgumentException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_DIRECCION));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Direccion> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES, Direccion.class);

        List<Direccion> direccionesParaInsertar = new ArrayList<>();

        for (DireccionDTO dto : nuevasDirecciones) {
            if (dto == null) {
                throw new IllegalArgumentException(
                    String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_DIRECCION));
            }

            // Validar campos obligatorios
            if (dto.getCodigoPostal() == null || dto.getColonia() == null 
                || dto.getCalle() == null || dto.getNumero() == null) {
                throw new IllegalArgumentException("Todos los campos de dirección son obligatorios.");
            }

            Direccion direccion = new Direccion(
                dto.getCodigoPostal(),
                dto.getColonia(),
                dto.getCalle(),
                dto.getNumero()
            );

            direccionesParaInsertar.add(direccion);
        }

        direccionesCollection.insertMany(direccionesParaInsertar);
    }

    private DireccionDTO convertirDireccionADTO(Direccion direccion) {
        IdEntidadGenerico idDireccion = new IdEntidadGenerico(direccion.getId().toHexString());
        
        return new DireccionDTO(
            idDireccion,
            direccion.getCodigoPostal(),
            direccion.getColonia(),
            direccion.getCalle(),
            direccion.getNumero()
        );
    }
}
