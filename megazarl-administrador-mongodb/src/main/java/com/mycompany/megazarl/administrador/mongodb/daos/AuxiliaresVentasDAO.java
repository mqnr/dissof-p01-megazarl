
package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.AuxiliarVentas;
import com.mycompany.megazarl.administrador.mongodb.excepciones.*;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.*;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.bson.Document;
import org.bson.types.ObjectId;

public class AuxiliaresVentasDAO {

    private final String COLECCION_AUXILIARES_VENTAS = "AuxiliaresVentas";
    private final String COLECCION_SUCURSALES = "Sucursales";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_AUXILIARES_NULA = "La lista de nuevos auxiliares es nula.";
    private final String MENSAJE_LISTA_NUEVOS_AUXILIARES_VACIA = "La lista de nuevos auxiliares está vacía.";
    
    // Mensajes de validación
    private final String MENSAJE_FORMATO_NOMBRES_INVALIDO_CADENA_VACIA = "Los nombres no pueden estar vacíos";
    private final String MENSAJE_FORMATO_NOMBRES_INVALIDO_LONGITUD = "Los nombres exceden los 50 caracteres";
    
    private final String MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_CADENA_VACIA = "El apellido paterno no puede estar vacío";
    private final String MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_LONGITUD = "El apellido paterno excede los 50 caracteres";
    
    private final String MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_CADENA_VACIA = "El apellido materno no puede estar vacío";
    private final String MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_LONGITUD = "El apellido materno excede los 50 caracteres";
    
    private final String NOMBRE_ENTIDAD_AUXILIAR_VENTAS = "auxiliar de ventas";
    private final String NOMBRE_ENTIDAD_SUCURSAL = "sucursal";
    
    private final int CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS = 50;

    public boolean existePorId(IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos) 
        throws ParametroNuloException, FormatoIdInvalidoException {
        
        if (idAuxiliarVentasDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idAuxiliarVentasDTODatos.getIdAuxiliarVentas() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_AUXILIAR_VENTAS));
        }
        
        String id = (String) idAuxiliarVentasDTODatos.getIdAuxiliarVentas().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(id);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_AUXILIARES_VENTAS).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_AUXILIAR_VENTAS));
        }
    }

    public AuxiliarVentasDTODatos recuperarPorId(IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos) 
        throws FormatoIdInvalidoException, RegistroInexistenteException, ParametroNuloException {
        
        if (idAuxiliarVentasDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idAuxiliarVentasDTODatos.getIdAuxiliarVentas() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_AUXILIAR_VENTAS));
        }
        
        try {
            
            String idAuxiliarVentasString = (String) idAuxiliarVentasDTODatos.getIdAuxiliarVentas().getId();
            ObjectId idAuxiliarVentasObjectId = new ObjectId(idAuxiliarVentasString);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<AuxiliarVentas> coleccionAuxiliaresVentas = baseDatos.getCollection(COLECCION_AUXILIARES_VENTAS, AuxiliarVentas.class);
            
            AuxiliarVentas auxiliarVentas = coleccionAuxiliaresVentas.find(new Document(CAMPO_ID, idAuxiliarVentasObjectId)).first();
            
            if (auxiliarVentas == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_AUXILIAR_VENTAS, idAuxiliarVentasString)
                );
            }
            
            return convertirADTO(auxiliarVentas);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_AUXILIAR_VENTAS));
        }
    }

    public void agregar(AuxiliarVentasDTODatos nuevoAuxiliarVentas) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException, 
            ValorParametroInvalidoException, 
            ParametroNuloException {
        
        if (nuevoAuxiliarVentas == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }

        validarCamposObligatorios(nuevoAuxiliarVentas);
        ObjectId idSucursalRegistrada = obtenerIdSucursalRegistrada(nuevoAuxiliarVentas.getIdSucursal());

        AuxiliarVentas auxiliarVentas = new AuxiliarVentas(
            nuevoAuxiliarVentas.getNombres(),
            nuevoAuxiliarVentas.getApellidoPaterno(),
            nuevoAuxiliarVentas.getApellidoMaterno(),
            idSucursalRegistrada
        );

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection coleccionAuxiliaresVentas = baseDatos.getCollection(COLECCION_AUXILIARES_VENTAS, AuxiliarVentas.class);
        
        coleccionAuxiliaresVentas.insertOne(auxiliarVentas);
        
    }
    
    
    public void agregar(Collection<AuxiliarVentasDTODatos> nuevosAuxiliaresVentas) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ValorParametroInvalidoException,
            ParametroNuloException {
        
        if (nuevosAuxiliaresVentas == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_AUXILIARES_NULA);
        }
        
        if (nuevosAuxiliaresVentas.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_AUXILIARES_VACIA);
        }

        List<AuxiliarVentas> auxiliaresVentas = new LinkedList<>();
        
        for (AuxiliarVentasDTODatos nuevoAuxiliarVentas : nuevosAuxiliaresVentas) {
            
            if (nuevoAuxiliarVentas == null || nuevoAuxiliarVentas.getId() == null){
                throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
            }

            if(nuevoAuxiliarVentas.getId().getId() == null){
                throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_AUXILIAR_VENTAS));
            }
        
            validarCamposObligatorios(nuevoAuxiliarVentas);
            ObjectId idSucursalRegistrada = obtenerIdSucursalRegistrada(nuevoAuxiliarVentas.getIdSucursal());
            
            AuxiliarVentas auxiliarVentas = new AuxiliarVentas(
                nuevoAuxiliarVentas.getNombres(),
                nuevoAuxiliarVentas.getApellidoPaterno(),
                nuevoAuxiliarVentas.getApellidoMaterno(),
                idSucursalRegistrada
            );
            
            auxiliaresVentas.add(auxiliarVentas);
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<AuxiliarVentas> coleccionAuxiliarVentas = baseDatos.getCollection(COLECCION_AUXILIARES_VENTAS, AuxiliarVentas.class);
        coleccionAuxiliarVentas.insertMany(auxiliaresVentas);
    }

    public List<AuxiliarVentasDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<AuxiliarVentas> coleccionAuxiliaresVentas = baseDatos.getCollection(COLECCION_AUXILIARES_VENTAS, AuxiliarVentas.class);
        
        return coleccionAuxiliaresVentas.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    private void validarCamposObligatorios(AuxiliarVentasDTODatos auxiliarVentasDTODatos) 
            throws ValorParametroInvalidoException,
            ParametroNuloException {
        
        if (auxiliarVentasDTODatos == null) {
            throw new ParametroNuloException(String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_AUXILIAR_VENTAS));
        }

        // Validación de nombres
        String nombres = auxiliarVentasDTODatos.getNombres();
        
        if (nombres == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_AUXILIAR_VENTAS)
            );
        }
        
        if (nombres.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRES_INVALIDO_CADENA_VACIA);
        }
        
        if (nombres.length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRES_INVALIDO_LONGITUD);
        }
        
        // Validación de apellido paterno
        String apellidoPaterno = auxiliarVentasDTODatos.getApellidoPaterno();
        
        if (apellidoPaterno == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "apellido paterno",NOMBRE_ENTIDAD_AUXILIAR_VENTAS)
            );
        }
        
        if (apellidoPaterno.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_CADENA_VACIA);
        }
        
        if (apellidoPaterno.length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_LONGITUD);
        }

        // Validación de apellido materno
        String apellidoMaterno = auxiliarVentasDTODatos.getApellidoMaterno();
        
        if (apellidoMaterno == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "apellido materno",NOMBRE_ENTIDAD_AUXILIAR_VENTAS)
            );
        }
        
        if (apellidoMaterno.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_CADENA_VACIA);
        }
        
        if (apellidoMaterno.length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_LONGITUD);
        }
    }
    
    private ObjectId obtenerIdSucursalRegistrada(IdEntidadGenericoDatos idSucursal) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {
        
        if (idSucursal == null || idSucursal.getId() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idSucursal", NOMBRE_ENTIDAD_SUCURSAL));
        }
        
        try {
            
            ObjectId idSucursalRegistrada = new ObjectId((String) idSucursal.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_SUCURSALES).countDocuments(eq(CAMPO_ID, idSucursalRegistrada)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_SUCURSAL, idSucursal.getId())
                );
            }
            
            return idSucursalRegistrada;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_SUCURSAL));
        }
    }

    private AuxiliarVentasDTODatos convertirADTO(AuxiliarVentas auxiliar) {
        return new AuxiliarVentasDTODatos(
            new IdEntidadGenericoDatos(auxiliar.getId().toHexString()),
            auxiliar.getNombres(),
            auxiliar.getApellidoPaterno(),
            auxiliar.getApellidoMaterno(),
            new IdEntidadGenericoDatos(auxiliar.getIdSucursal().toHexString())
        );
    }
}