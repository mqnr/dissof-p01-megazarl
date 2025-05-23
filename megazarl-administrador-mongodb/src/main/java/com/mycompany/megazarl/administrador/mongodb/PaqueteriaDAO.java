package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Paqueteria;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.PaqueteriaDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PaqueteriaDAO {

    private final String COLECCION_PAQUETERIAS = "Paqueterias";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_ID_NULO = "El ID recibido es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    
    private final String NOMBRE_ENTIDAD_PAQUETERIA = "paquetería";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";

    public boolean existePorId(IdEntidadGenericoDatos idPaqueteriaDTO) 
        throws FormatoInvalidoIdConversionException {
        
        if (idPaqueteriaDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idPaqueteria = (String) idPaqueteriaDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idPaqueteria);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> paqueteriasCollection = baseDatos.getCollection(COLECCION_PAQUETERIAS);
            return paqueteriasCollection.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public PaqueteriaDTODatos recuperarPorId(IdEntidadGenericoDatos idPaqueteriaDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
        
        if (idPaqueteriaDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idPaqueteria = (String) idPaqueteriaDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idPaqueteria);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Paqueteria> paqueteriasCollection = baseDatos.getCollection(
                COLECCION_PAQUETERIAS, 
                Paqueteria.class
            );
            
            Paqueteria paqueteria = paqueteriasCollection.find(new Document(CAMPO_ID, objectId)).first();
            
            if (paqueteria == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PAQUETERIA, idPaqueteria)
                );
            }
            
            return convertirPaqueteriaADTO(paqueteria);
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public void agregar(PaqueteriaDTODatos nuevaPaqueteria) 
        throws AgregarInformacionNulaException {
        
        if (nuevaPaqueteria == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PAQUETERIA));
        }

        validarCamposObligatorios(nuevaPaqueteria);
        Direccion direccion = convertirDireccionDTOAEntidad(nuevaPaqueteria.getDireccion());

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Paqueteria> paqueteriasCollection = baseDatos.getCollection(
            COLECCION_PAQUETERIAS, 
            Paqueteria.class
        );

        Paqueteria paqueteria = new Paqueteria(
            nuevaPaqueteria.getNombre(),
            nuevaPaqueteria.getCobroKg(),
            nuevaPaqueteria.getCobroHora(),
            nuevaPaqueteria.getDireccionImagen(),
            direccion
        );

        paqueteriasCollection.insertOne(paqueteria);
    }

    public void agregar(List<PaqueteriaDTODatos> nuevasPaqueterias) 
        throws AgregarInformacionNulaException {
        
        if (nuevasPaqueterias == null || nuevasPaqueterias.isEmpty()) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PAQUETERIA));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Paqueteria> paqueteriasCollection = baseDatos.getCollection(
            COLECCION_PAQUETERIAS, 
            Paqueteria.class
        );

        List<Paqueteria> paqueteriasParaInsertar = new ArrayList<>();

        for (PaqueteriaDTODatos paqueteriaDTO : nuevasPaqueterias) {
            if (paqueteriaDTO == null) {
                throw new AgregarInformacionNulaException(
                    String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PAQUETERIA));
            }

            validarCamposObligatorios(paqueteriaDTO);
            Direccion direccion = convertirDireccionDTOAEntidad(paqueteriaDTO.getDireccion());
            
            Paqueteria paqueteria = new Paqueteria(
                paqueteriaDTO.getNombre(),
                paqueteriaDTO.getCobroKg(),
                paqueteriaDTO.getCobroHora(),
                paqueteriaDTO.getDireccionImagen(),
                direccion
            );
            
            paqueteriasParaInsertar.add(paqueteria);
        }

        paqueteriasCollection.insertMany(paqueteriasParaInsertar);
    }

    private void validarCamposObligatorios(PaqueteriaDTODatos paqueteriaDTO) 
        throws AgregarInformacionNulaException {
        
        if (paqueteriaDTO.getNombre() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombre", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        if (paqueteriaDTO.getCobroKg() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "cobro por kg", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        if (paqueteriaDTO.getCobroHora() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "cobro por hora", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        if (paqueteriaDTO.getDireccionImagen() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "dirección imagen", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        if (paqueteriaDTO.getDireccion() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "dirección", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        validarCamposObligatoriosDireccion(paqueteriaDTO.getDireccion());
    }

    private void validarCamposObligatoriosDireccion(DireccionDTODatos direccionDTO) 
        throws AgregarInformacionNulaException {
        
        if (direccionDTO.getCodigoPostal() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "código postal", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (direccionDTO.getColonia() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "colonia", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (direccionDTO.getCalle() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "calle", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (direccionDTO.getNumero() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "número", NOMBRE_ENTIDAD_DIRECCION));
        }
    }
    
    private Direccion convertirDireccionDTOAEntidad(DireccionDTODatos direccionDTO) {
        Direccion direccion = new Direccion();
        direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
        direccion.setColonia(direccionDTO.getColonia());
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumero(direccionDTO.getNumero());
        return direccion;
    }
    
    private PaqueteriaDTODatos convertirPaqueteriaADTO(Paqueteria paqueteria) {
        IdEntidadGenericoDatos idPaqueteria = new IdEntidadGenericoDatos(paqueteria.getId().toHexString());
        DireccionDTODatos direccionDTO = convertirDireccionADTO(paqueteria.getDireccion());
        
        return new PaqueteriaDTODatos(
            idPaqueteria,
            paqueteria.getNombre(),
            paqueteria.getCobroKg(),
            paqueteria.getCobroHora(),
            paqueteria.getDireccionImagen(),
            direccionDTO
        );
    }

    private DireccionDTODatos convertirDireccionADTO(Direccion direccion) {
        return new DireccionDTODatos(
            direccion.getCodigoPostal(),
            direccion.getColonia(),
            direccion.getCalle(),
            direccion.getNumero()
        );
    }
}