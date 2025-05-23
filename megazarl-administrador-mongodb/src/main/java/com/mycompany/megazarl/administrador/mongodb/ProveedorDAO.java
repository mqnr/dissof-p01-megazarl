package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Proveedor;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.ProveedorDTO;
import edu.student.itson.dissof.dto.datos.ProveedorIdsRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ProveedorDAO {

    private final String COLECCION_PROVEEDORES = "Proveedores";
    private final String COLECCION_DIRECCIONES = "Direcciones";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_ID_NULO = "El ID recibido es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    
    private final String NOMBRE_ENTIDAD_PROVEEDOR = "proveedor";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";

    public boolean existePorId(IdEntidadGenericoDatos idProveedorDTO) 
        throws FormatoInvalidoIdConversionException {
        
        if (idProveedorDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idProveedor = (String) idProveedorDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idProveedor);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> proveedoresCollection = baseDatos.getCollection(COLECCION_PROVEEDORES);
            return proveedoresCollection.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public ProveedorDTO recuperarPorId(IdEntidadGenericoDatos idProveedorDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
        
        if (idProveedorDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idProveedor = (String) idProveedorDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idProveedor);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Proveedor> proveedoresCollection = baseDatos.getCollection(
                COLECCION_PROVEEDORES, 
                Proveedor.class
            );
            
            Proveedor proveedor = proveedoresCollection.find(new Document(CAMPO_ID, objectId)).first();
            
            if (proveedor == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PROVEEDOR, idProveedor)
                );
            }
            
            return convertirProveedorADTO(proveedor);
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public void agregar(ProveedorIdsRelacionesDTO nuevoProveedor) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {
        
        if (nuevoProveedor == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PROVEEDOR));
        }

        validarCamposObligatorios(nuevoProveedor);
        Direccion direccion = obtenerDireccionCompleta(nuevoProveedor.getIdDireccion());

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Proveedor> proveedoresCollection = baseDatos.getCollection(
            COLECCION_PROVEEDORES, 
            Proveedor.class
        );

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nuevoProveedor.getNombre());
        proveedor.setTelefono(nuevoProveedor.getTelefono());
        proveedor.setCorreoElectronico(nuevoProveedor.getCorreoElectronico());
        proveedor.setDireccionImagen(nuevoProveedor.getDireccionImagen());
        proveedor.setDireccion(direccion);
        
        proveedoresCollection.insertOne(proveedor);
    }

    public void agregar(List<ProveedorIdsRelacionesDTO> nuevosProveedoresDTO) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {
        
        if (nuevosProveedoresDTO == null || nuevosProveedoresDTO.isEmpty()) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PROVEEDOR));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Proveedor> proveedoresCollection = baseDatos.getCollection(
            COLECCION_PROVEEDORES, 
            Proveedor.class
        );

        List<Proveedor> proveedoresParaInsertar = new ArrayList<>();

        for (ProveedorIdsRelacionesDTO proveedorDTO : nuevosProveedoresDTO) {
            if (proveedorDTO == null) {
                throw new AgregarInformacionNulaException(
                    String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PROVEEDOR));
            }

            validarCamposObligatorios(proveedorDTO);
            Direccion direccion = obtenerDireccionCompleta(proveedorDTO.getIdDireccion());
            
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(proveedorDTO.getNombre());
            proveedor.setTelefono(proveedorDTO.getTelefono());
            proveedor.setCorreoElectronico(proveedorDTO.getCorreoElectronico());
            proveedor.setDireccionImagen(proveedorDTO.getDireccionImagen());
            proveedor.setDireccion(direccion);
            
            proveedoresParaInsertar.add(proveedor);
        }

        proveedoresCollection.insertMany(proveedoresParaInsertar);
    }

    private void validarCamposObligatorios(ProveedorIdsRelacionesDTO proveedorDTO) 
        throws AgregarInformacionNulaException {
        
        if (proveedorDTO.getNombre() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombre", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        if (proveedorDTO.getTelefono() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "teléfono", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        if (proveedorDTO.getDireccionImagen() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "dirección imagen", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        if (proveedorDTO.getIdDireccion() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "idDirección", NOMBRE_ENTIDAD_PROVEEDOR));
        }
    }

    private Direccion obtenerDireccionCompleta(IdEntidadGenericoDatos idDireccionDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
        
        String idDireccionString = (String) idDireccionDTO.getId();
        
        try {
            ObjectId idDireccion = new ObjectId(idDireccionString);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Direccion> direccionesCollection = baseDatos.getCollection(
                COLECCION_DIRECCIONES, 
                Direccion.class
            );
            
            Direccion direccion = direccionesCollection.find(new Document(CAMPO_ID, idDireccion)).first();
            
            if (direccion == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccionString)
                );
            }
            
            return direccion;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID de dirección inválido.");
        }
    }
    
    private ProveedorDTO convertirProveedorADTO(Proveedor proveedor) {
        IdEntidadGenericoDatos idProveedor = new IdEntidadGenericoDatos(proveedor.getId().toHexString());
        DireccionDTODatos direccionDTO = convertirDireccionADTO(proveedor.getDireccion());
        
        return new ProveedorDTO(
            idProveedor,
            proveedor.getNombre(),
            proveedor.getTelefono(),
            proveedor.getCorreoElectronico(),
            proveedor.getDireccionImagen(),
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