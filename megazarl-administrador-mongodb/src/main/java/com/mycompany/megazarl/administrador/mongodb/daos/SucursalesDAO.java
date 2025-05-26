package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Sucursal;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.SucursalDTODatos;
import edu.student.itson.dissof.dto.datos.IdSucursalDTODatos;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;
import org.bson.types.ObjectId;

public class SucursalesDAO {

    private final String COLECCION_SUCURSALES = "Sucursales";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVAS_SUCURSALES_NULA = "La lista de nuevas sucursales es nula.";
    private final String MENSAJE_LISTA_NUEVAS_SUCURSALES_VACIA = "La lista de nuevas sucursales está vacía.";
    private final String MENSAJE_NO_EXISTE_SUCURSAL_MATRIZ = "No existe una sucursal cuyo atributo esMatriz sea verdadero";
    
    // Mensajes para validación de sucursal
    private final String MENSAJE_ES_MATRIZ_NULO = "El campo 'esMatriz' no puede ser nulo";
    
    // Mensajes de validación de dirección
    private final String REGEX_CINCO_DIGITOS = "\\d{5}";
    private final String REGEX_MAS_UN_DIGITO = "\\d+";
    private final String MENSAJE_FORMATO_CODIGO_POSTAL_INVALIDO = "El Código Postal de dirección se compone de 5 dígitos.";
    private final String MENSAJE_FORMATO_COLONIA_INVALIDO_CADENA_VACIA = "La colonia está vacía";
    private final String MENSAJE_FORMATO_COLONIA_MAS_CIEN_CARACTERES = "La colonia tiene más de cien caracteres";
    private final String MENSAJE_FORMATO_CALLE_MAS_CIEN_CARACTERES = "La calle tiene más de cien caracteres";
    private final String MENSAJE_FORMATO_CALLE_CADENA_VACIA = "La calle es una cadena de texto vacía.";
    private final String MENSAJE_FORMATO_NUMERO_DIRECCION_NO_NUMERICO = "El número de dirección no es numérico";
    private final String MENSAJE_FORMATO_NUMERO_DIRECCION_MAS_CINCO_DIGITOS = "El número de dirección tiene más de cinco dígitos";
    
    private final String NOMBRE_ENTIDAD_SUCURSAL = "sucursal";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";

    private final int CANTIDAD_MAXIMA_CARACTERES_CALLE = 100;
    private final int CANTIDAD_MAXIMA_CARACTERES_COLONIA = 100;
    private final int CANTIDAD_MAXIMA_DIGITOS_NUMERO_DIRECCION = 5;
    
    public boolean existePorId(IdSucursalDTODatos idSucursalDTODatos) 
            throws ParametroNuloException, 
            FormatoIdInvalidoException {
        
        if (idSucursalDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idSucursalDTODatos.getIdSucursal() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_SUCURSAL));
        }
        
        String idSucursal = (String) idSucursalDTODatos.getIdSucursal().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idSucursal);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_SUCURSALES).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_SUCURSAL));
        }
    }

    public SucursalDTODatos recuperarPorId(IdSucursalDTODatos idSucursalDTODatos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {
        
        if (idSucursalDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idSucursalDTODatos.getIdSucursal() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_SUCURSAL));
        }
        
        String idSucursal = (String) idSucursalDTODatos.getIdSucursal().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idSucursal);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Sucursal> coleccionSucursales = baseDatos.getCollection(COLECCION_SUCURSALES, Sucursal.class);
            
            Sucursal sucursal = coleccionSucursales.find(new Document(CAMPO_ID, objectId)).first();
            
            if (sucursal == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_SUCURSAL, idSucursal)
                );
            }
            
            return convertirSucursalADTO(sucursal);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_SUCURSAL));
        }
    }

    public void agregar(SucursalDTODatos nuevaSucursal) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException, 
               ValorParametroInvalidoException, 
               ParametroNuloException {
        
        validarCamposObligatorios(nuevaSucursal);

        Sucursal sucursal = new Sucursal();
        
        sucursal.setEsMatriz(nuevaSucursal.getEsMatriz());
        
        if(nuevaSucursal.getDireccion() != null) {
            sucursal.setDireccion(new Direccion(
                nuevaSucursal.getDireccion().getCodigoPostal(),
                nuevaSucursal.getDireccion().getColonia(),
                nuevaSucursal.getDireccion().getCalle(),
                nuevaSucursal.getDireccion().getNumero()
            ));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        baseDatos.getCollection(COLECCION_SUCURSALES, Sucursal.class).insertOne(sucursal);
    }
    
    public void agregar(Collection<SucursalDTODatos> nuevasSucursales) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ValorParametroInvalidoException, 
            ParametroNuloException {
        
        if (nuevasSucursales == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVAS_SUCURSALES_NULA);
        }

        if (nuevasSucursales.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVAS_SUCURSALES_VACIA);
        }

        List<Sucursal> sucursales = new LinkedList<>();

        for(SucursalDTODatos nuevaSucursal : nuevasSucursales) {
            
            validarCamposObligatorios(nuevaSucursal);

            Sucursal sucursal = new Sucursal(
                nuevaSucursal.getEsMatriz(),
                new Direccion(
                        nuevaSucursal.getDireccion().getCodigoPostal(),
                        nuevaSucursal.getDireccion().getColonia(),
                        nuevaSucursal.getDireccion().getCalle(),
                        nuevaSucursal.getDireccion().getNumero()
                )
            
            );
            
            sucursales.add(sucursal);
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        baseDatos.getCollection(COLECCION_SUCURSALES, Sucursal.class).insertMany(sucursales);
    }
    
    public List<SucursalDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Sucursal> coleccionSucursales = baseDatos.getCollection(COLECCION_SUCURSALES, Sucursal.class);

        return coleccionSucursales.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirSucursalADTO)
            .collect(Collectors.toList());
    }
    
    public SucursalDTODatos obtenerSucursalMatriz() throws RegistroInexistenteException{
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Sucursal> coleccionSucursales = baseDatos.getCollection(COLECCION_SUCURSALES, Sucursal.class);
        
        Sucursal sucursalMatriz = coleccionSucursales.find(Filters.eq("esMatriz", true)).first();
        
        if(sucursalMatriz == null){
            throw new RegistroInexistenteException(MENSAJE_NO_EXISTE_SUCURSAL_MATRIZ);
        }
        
        return convertirSucursalADTO(sucursalMatriz);
        
    }
            

    private void validarCamposObligatorios(SucursalDTODatos sucursalDTODatos) 
        throws ValorParametroInvalidoException, ParametroNuloException {
        
        if (sucursalDTODatos == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_SUCURSAL));
        }

        // Validación campo esMatriz
        if (sucursalDTODatos.getEsMatriz() == null) {
            throw new ParametroNuloException(MENSAJE_ES_MATRIZ_NULO);
        }
        
        // Validación de dirección
        validarCamposDireccion(sucursalDTODatos.getDireccion());

    }


    private void validarCamposDireccion(DireccionDTODatos direccionDTODatos) 
            throws ValorParametroInvalidoException, 
            ParametroNuloException {
        
        if(direccionDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "dirección", NOMBRE_ENTIDAD_SUCURSAL));
        }

        // Validación de Código Postal
        if(direccionDTODatos.getCodigoPostal() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "codigo postal", NOMBRE_ENTIDAD_DIRECCION));
        }

        if (!direccionDTODatos.getCodigoPostal().matches(REGEX_CINCO_DIGITOS)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CODIGO_POSTAL_INVALIDO);
        }

        // Validación de colonia
        String coloniaDireccion = direccionDTODatos.getColonia();
        if(coloniaDireccion == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "colonia", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (coloniaDireccion.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_COLONIA_INVALIDO_CADENA_VACIA);
        }
        if (coloniaDireccion.length() > CANTIDAD_MAXIMA_CARACTERES_COLONIA) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_COLONIA_MAS_CIEN_CARACTERES);
        }

        // Validación de calle
        String calleDireccion = direccionDTODatos.getCalle();
        if(calleDireccion == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "calle", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (calleDireccion.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CALLE_CADENA_VACIA);
        }
        if (calleDireccion.length() > CANTIDAD_MAXIMA_CARACTERES_CALLE) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CALLE_MAS_CIEN_CARACTERES);
        }

        // Validación de número de dirección
        String numeroDireccion = direccionDTODatos.getNumero();
        if(numeroDireccion == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "numero", NOMBRE_ENTIDAD_DIRECCION));
        }
        if (!numeroDireccion.matches(REGEX_MAS_UN_DIGITO)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NUMERO_DIRECCION_NO_NUMERICO);
        }
        if (numeroDireccion.length() > CANTIDAD_MAXIMA_DIGITOS_NUMERO_DIRECCION) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NUMERO_DIRECCION_MAS_CINCO_DIGITOS);
        }
    }

    
    private SucursalDTODatos convertirSucursalADTO(Sucursal sucursal) {
        return new SucursalDTODatos(
            new IdEntidadGenericoDatos(sucursal.getId().toHexString()),
            sucursal.getEsMatriz(),
            convertirDireccionADTO(sucursal.getDireccion())
        );
    }

    private DireccionDTODatos convertirDireccionADTO(Direccion direccion) {
        
        return direccion == null ? null : new DireccionDTODatos(
            new IdEntidadGenericoDatos(direccion.getId().toHexString()),
            direccion.getCodigoPostal(),
            direccion.getColonia(),
            direccion.getCalle(),
            direccion.getNumero()
        );
        
    }
}