package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.GerenteVentas;
import com.mycompany.megazarl.administrador.mongodb.excepciones.*;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.*;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class GerentesVentasDAO {

    private final String COLECCION_GERENTES_VENTAS = "GerentesVentas";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_GERENTES_NULA = "La lista de nuevos gerentes es nula.";
    private final String MENSAJE_LISTA_NUEVOS_GERENTES_VACIA = "La lista de nuevos gerentes está vacía.";
    
    // Mensajes de validación
    private final String MENSAJE_FORMATO_NOMBRE_INVALIDO_CADENA_VACIA = "El nombre no puede estar vacío";
    private final String MENSAJE_FORMATO_NOMBRE_INVALIDO_SUPERA_MAXIMO_CARACTERES = "El nombre excede los 50 caracteres";
    
    private final String MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_CADENA_VACIA = "El apellido paterno no puede estar vacío";
    private final String MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_SUPERA_MAXIMO_CARACTERES = "El apellido paterno excede los 50 caracteres";
    
    private final String MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_CADENA_VACIA = "El apellido materno no puede estar vacío";
    private final String MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_SUPERA_MAXIMO_CARACTERES = "El apellido materno excede los 50 caracteres";
    
    private final String NOMBRE_ENTIDAD_GERENTE_VENTAS = "gerente de ventas";
    private final int CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS = 50;

    public boolean existePorId(IdGerenteVentasDTODatos idGerenteVentasDTODatos) 
            throws ParametroNuloException, 
            FormatoIdInvalidoException {
        
        if (idGerenteVentasDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idGerenteVentasDTODatos.getIdGerenteVentas() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_GERENTE_VENTAS));
        }
        
        String idGerenteVentasString = (String) idGerenteVentasDTODatos.getIdGerenteVentas().getId();
        
        try {
            ObjectId idGerenteVentasObjectId = new ObjectId(idGerenteVentasString);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_GERENTES_VENTAS).countDocuments(new Document(CAMPO_ID, idGerenteVentasObjectId)) > 0;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_GERENTE_VENTAS));
        }
    }

    public GerenteVentasDTODatos recuperarPorId(IdGerenteVentasDTODatos idGerenteVentasDTODatos) 
        throws FormatoIdInvalidoException, RegistroInexistenteException, ParametroNuloException {
        
        if (idGerenteVentasDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idGerenteVentasDTODatos.getIdGerenteVentas() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_GERENTE_VENTAS));
        }
        
        try {
            
            String idGerenteVentasString = (String) idGerenteVentasDTODatos.getIdGerenteVentas().getId();
            ObjectId idGerenteVentasObjectId = new ObjectId(idGerenteVentasString);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<GerenteVentas> coleccionGerentesVentas = baseDatos.getCollection(COLECCION_GERENTES_VENTAS, GerenteVentas.class);
            
            GerenteVentas gerenteVentas = coleccionGerentesVentas.find(new Document(CAMPO_ID, idGerenteVentasObjectId)).first();
            
            if (gerenteVentas == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_GERENTE_VENTAS, idGerenteVentasString)
                );
            }
            
            GerenteVentasDTODatos gerenteVentasDTODatos =  new GerenteVentasDTODatos(
                new IdEntidadGenericoDatos(gerenteVentas.getId().toHexString()),
                gerenteVentas.getNombre(),
                gerenteVentas.getApellidoPaterno(),
                gerenteVentas.getApellidoMaterno()
            );
            
            
            return gerenteVentasDTODatos;
            

        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_GERENTE_VENTAS));
        }
    }

    public void agregar(GerenteVentasDTODatos nuevoGerenteVentas) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException, 
            ValorParametroInvalidoException, 
            ParametroNuloException {
        
        if (nuevoGerenteVentas == null || nuevoGerenteVentas.getId() == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }

        if(nuevoGerenteVentas.getId().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_GERENTE_VENTAS));
        }

        validarCamposObligatorios(nuevoGerenteVentas);

        GerenteVentas gerenteVentas = new GerenteVentas(
            nuevoGerenteVentas.getNombres(),
            nuevoGerenteVentas.getApellidoPaterno(),
            nuevoGerenteVentas.getApellidoMaterno()
        );

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection coleccionGerentesVentas = baseDatos.getCollection(COLECCION_GERENTES_VENTAS, GerenteVentas.class);
        coleccionGerentesVentas.insertOne(gerenteVentas);
    }
    
    public void agregar(Collection<GerenteVentasDTODatos> nuevosGerentesVentas) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ValorParametroInvalidoException,
            ParametroNuloException {
        
        if (nuevosGerentesVentas == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_GERENTES_NULA);
        }
        
        if (nuevosGerentesVentas.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_GERENTES_VACIA);
        }

        List<GerenteVentas> gerentesVentas = new LinkedList<>();
        
        for (GerenteVentasDTODatos nuevoGerenteVentas : nuevosGerentesVentas) {
            
            if (nuevoGerenteVentas == null || nuevoGerenteVentas.getId() == null){
                throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
            }

            if(nuevoGerenteVentas.getId().getId() == null){
                throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_GERENTE_VENTAS));
            }
        
            validarCamposObligatorios(nuevoGerenteVentas);
            
            GerenteVentas gerenteVentas = new GerenteVentas(
                nuevoGerenteVentas.getNombres(),
                nuevoGerenteVentas.getApellidoPaterno(),
                nuevoGerenteVentas.getApellidoMaterno()
            );
            
            gerentesVentas.add(gerenteVentas);
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<GerenteVentas> coleccionGerentesVentas = baseDatos.getCollection(COLECCION_GERENTES_VENTAS, GerenteVentas.class);
        coleccionGerentesVentas.insertMany(gerentesVentas);
    }

    public List<GerenteVentasDTODatos> recuperarTodos() {

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<GerenteVentas> coleccionGerentesVentas = baseDatos.getCollection(COLECCION_GERENTES_VENTAS, GerenteVentas.class);

        List<GerenteVentas> listaGerentesVentas = new ArrayList<>();
        coleccionGerentesVentas.find().into(listaGerentesVentas);

        List<GerenteVentasDTODatos> listaGerenteVentasDTODatos = new ArrayList<>();


        for (GerenteVentas gerenteVentas : listaGerentesVentas) {
            
            GerenteVentasDTODatos gerenteVentasDTODatos =  new GerenteVentasDTODatos(
                new IdEntidadGenericoDatos(gerenteVentas.getId().toHexString()),
                gerenteVentas.getNombre(),
                gerenteVentas.getApellidoPaterno(),
                gerenteVentas.getApellidoMaterno()
            );
            
            listaGerenteVentasDTODatos.add(gerenteVentasDTODatos);
        }

        return listaGerenteVentasDTODatos;
    }

    private void validarCamposObligatorios(GerenteVentasDTODatos gerenteVentasDTODatos) 
            throws ValorParametroInvalidoException,
            ParametroNuloException {
        
        if (gerenteVentasDTODatos == null) {
            throw new ParametroNuloException(String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_GERENTE_VENTAS));
        }

        // Validación de nombre
        String nombre = gerenteVentasDTODatos.getNombres();
        
        if (nombre == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "nombre", NOMBRE_ENTIDAD_GERENTE_VENTAS)
            );
        }
        
        if (nombre.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRE_INVALIDO_CADENA_VACIA);
        }
        
        if (nombre.length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRE_INVALIDO_SUPERA_MAXIMO_CARACTERES);
        }
        
        // Validación de apellido paterno
        String apellidoPaterno = gerenteVentasDTODatos.getApellidoPaterno();
        
        if (apellidoPaterno == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "apellido paterno", NOMBRE_ENTIDAD_GERENTE_VENTAS)
            );
        }
        
        if (apellidoPaterno.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_CADENA_VACIA);
        }
        
        if (apellidoPaterno.length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_SUPERA_MAXIMO_CARACTERES);
        }

        // Validación de apellido materno
        String apellidoMaterno = gerenteVentasDTODatos.getApellidoMaterno();
        
        if (apellidoMaterno == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "apellido materno", NOMBRE_ENTIDAD_GERENTE_VENTAS)
            );
        }
        
        if (apellidoMaterno.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_CADENA_VACIA);
        }
        
        if (apellidoMaterno.length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_SUPERA_MAXIMO_CARACTERES);
        }
    }

}