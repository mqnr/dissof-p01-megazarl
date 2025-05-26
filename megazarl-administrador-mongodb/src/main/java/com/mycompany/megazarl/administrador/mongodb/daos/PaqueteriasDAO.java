package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Paqueteria;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.PaqueteriaDTODatos;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdPaqueteriaDTODatos;
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

/**
 * Clase de acceso a datos para operaciones con paqueterías.
 */
public class PaqueteriasDAO {

    private final String COLECCION_PAQUETERIAS = "Paqueterias";
    private final String COLECCION_DIRECCIONES = "Direcciones";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVAS_PAQUETERIAS_NULA = "La lista de nuevas paqueterías es nula.";
    private final String MENSAJE_LISTA_NUEVAS_PAQUETERIAS_VACIA = "La lista de nuevas paqueterías está vacía.";
    
    // Mensajes validación datos paquetería
    private final String MENSAJE_NOMBRE_INVALIDO_CADENA_VACIA = "El nombre de la paquetería es una cadena vacía";
    private final String MENSAJE_NOMBRE_INVALIDO_MAS_CINCUENTA_CARACTERES = "El nombre de la paquetería tiene más de 50 caracteres";
    
    private final String MENSAJE_COBRO_KG_INVALIDO_NEGATIVO = "El cobro por kiogramo trasladado de la paquetería no puede ser negativo.";
    private final String MENSAJE_COBRO_KG_INVALIDO_MAYOR_MAXIMO = "El cobro por kilogramo trasladado de la paquetería no puede superar los $1000.00 pesos.";
    
    private final String MENSAJE_COBRO_HORA_INVALIDO_NEGATIVO = "El cobro por hora de traslado de la paquetería no puede ser negativo.";
    private final String MENSAJE_COBRO_HORA_INVALIDO_MAYOR_MAXIMO = "El cobro por hora de la paquetería no puede superar los $1000.00 pesos.";
    
    private final String MENSAJE_DIRECCION_IMAGEN_INVALIDA_CADENA_VACIA = "La dirección de imagen de la paquetería está vacía";
    
    // Mensajes para validación de dirección
    private final String REGEX_CINCO_DIGITOS = "\\d{5}";
    private final String REGEX_MAS_UN_DIGITO = "\\d+";
    
    private final String MENSAJE_FORMATO_CODIGO_POSTAL_INVALIDO = "El Código Postal de dirección se se compone de 5 dígitos.";
   
    private final String MENSAJE_FORMATO_COLONIA_INVALIDO_CADENA_VACIA = "La colonia está vacía";
    private final String MENSAJE_FORMATO_COLONIA_MAS_CIEN_CARACTERES = "La colonia tiene más de cien caracteres";
    
    private final String MENSAJE_FORMATO_CALLE_MAS_CIEN_CARACTERES = "La calle tiene más de cien caracteres";
    private final String MENSAJE_FORMATO_CALLE_CADENA_VACIA = "La calle es una cadena de texto vacía.";
    
    private final String MENSAJE_FORMATO_NUMERO_DIRECCION_NO_NUMERICO = "El número de dirección no es numérico";
    private final String MENSAJE_FORMATO_NUMERO_DIRECCION_MAS_CIEN_DIGITOS = "El número de dirección tiene más de cinco dígitos";
    
    private final String NOMBRE_ENTIDAD_PAQUETERIA = "paquetería";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";
    
    private final double COBRO_MAXIMO_KG = 1000D;
    private final double COBRO_MAXIMO_HORA = 1000D;
    
    private final int CANTIDAD_MAXIMA_CARACTERES_NOMBRE = 50;
    private final int CANTIDAD_MAXIMA_CARACTERES_CALLE = 100;
    private final int CANTIDAD_MAXIMA_CARACTERES_COLONIA = 100;
    private final int CANTIDAD_MAXIMA_DIGITOS_NUMERO_DIRECCION = 5;

    public boolean existePorId(IdPaqueteriaDTODatos idPaqueteriaDTODatos) 
        throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        if (idPaqueteriaDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idPaqueteriaDTODatos.getIdPaqueteria() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        String idPaqueteria = (String) idPaqueteriaDTODatos.getIdPaqueteria().getId();
        
        try {
            ObjectId objectId = new ObjectId(idPaqueteria);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_PAQUETERIAS).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PAQUETERIA));
        }
    }

    public PaqueteriaDTODatos recuperarPorId(IdPaqueteriaDTODatos idPaqueteriaDTODatos) 
        throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {
        
        if (idPaqueteriaDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idPaqueteriaDTODatos.getIdPaqueteria() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        String idPaqueteria = (String) idPaqueteriaDTODatos.getIdPaqueteria().getId();
        
        try {
            ObjectId objectId = new ObjectId(idPaqueteria);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Paqueteria> coleccionPaqueterias = baseDatos.getCollection(COLECCION_PAQUETERIAS, Paqueteria.class);
            
            Paqueteria paqueteria = coleccionPaqueterias.find(new Document(CAMPO_ID, objectId)).first();
            
            if (paqueteria == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PAQUETERIA, idPaqueteria)
                );
            }
            
            return convertirPaqueteriaADTO(paqueteria);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PAQUETERIA));
        }
    }

    public void agregar(PaqueteriaDTODatos nuevaPaqueteria) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ParametroNuloException,
               ValorParametroInvalidoException {
        
        validarCamposObligatorios(nuevaPaqueteria);

        Paqueteria paqueteria = new Paqueteria(
            nuevaPaqueteria.getNombre(),
            nuevaPaqueteria.getCobroKg(),
            nuevaPaqueteria.getCobroHora(),
            nuevaPaqueteria.getDireccionImagen(),
            nuevaPaqueteria.getDireccion() != null ? 
                new Direccion(
                    nuevaPaqueteria.getDireccion().getCodigoPostal(),
                    nuevaPaqueteria.getDireccion().getColonia(),
                    nuevaPaqueteria.getDireccion().getCalle(),
                    nuevaPaqueteria.getDireccion().getNumero()
                ) : null
        );

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Paqueteria> coleccionPaqueterias = baseDatos.getCollection(COLECCION_PAQUETERIAS, Paqueteria.class);
        
        coleccionPaqueterias.insertOne(paqueteria);;
    }
    
    public void agregar(Collection<PaqueteriaDTODatos> nuevasPaqueterias) 
            throws FormatoIdInvalidoException, 
                   RegistroInexistenteException,
                   ParametroNuloException,
                   ValorParametroInvalidoException {
        
        if (nuevasPaqueterias == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVAS_PAQUETERIAS_NULA);
        }

        if (nuevasPaqueterias.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVAS_PAQUETERIAS_VACIA);
        }

        List<Paqueteria> paqueterias = new LinkedList<>();
        for(PaqueteriaDTODatos nuevaPaqueteria: nuevasPaqueterias){
            validarCamposObligatorios(nuevaPaqueteria);
            
            paqueterias.add(new Paqueteria(
                nuevaPaqueteria.getNombre(),
                nuevaPaqueteria.getCobroKg(),
                nuevaPaqueteria.getCobroHora(),
                nuevaPaqueteria.getDireccionImagen(),
                convertirDireccionDesdeDTO(nuevaPaqueteria.getDireccion())
            ));
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Paqueteria> coleccionPaqueterias = baseDatos.getCollection(COLECCION_PAQUETERIAS, Paqueteria.class);
        
        coleccionPaqueterias.insertMany(paqueterias);
    }
    
    public List<PaqueteriaDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Paqueteria> coleccionPaqueterias = baseDatos.getCollection(COLECCION_PAQUETERIAS, Paqueteria.class);

        return coleccionPaqueterias.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirPaqueteriaADTO)
            .collect(Collectors.toList());
    }

    private PaqueteriaDTODatos convertirPaqueteriaADTO(Paqueteria paqueteria) {
        return new PaqueteriaDTODatos(
            new IdEntidadGenericoDatos(paqueteria.getId().toHexString()),
            paqueteria.getNombre(),
            paqueteria.getCobroKg(),
            paqueteria.getCobroHora(),
            paqueteria.getDireccionImagen(),
            convertirDireccionADTO(paqueteria.getDireccion())
        );
    }

    private DireccionDTODatos convertirDireccionADTO(Direccion direccion) {
        return direccion == null ? null : new DireccionDTODatos(
            direccion.getCodigoPostal(),
            direccion.getColonia(),
            direccion.getCalle(),
            direccion.getNumero()
        );
    }
    
    private Direccion convertirDireccionDesdeDTO(DireccionDTODatos dto) {
        return dto == null ? null : new Direccion(
            dto.getCodigoPostal(),
            dto.getColonia(),
            dto.getCalle(),
            dto.getNumero()
        );
    }
    
    private void validarCamposObligatorios(PaqueteriaDTODatos paqueteriaDTODatos)
            throws ParametroNuloException,
            ValorParametroInvalidoException
            {
        
        if (paqueteriaDTODatos == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        // Validación de nombre
        String nombre = paqueteriaDTODatos.getNombre();
        if (nombre == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombre", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        if (nombre.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_NOMBRE_INVALIDO_CADENA_VACIA);
        }

        if (nombre.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE) {
            throw new ValorParametroInvalidoException(MENSAJE_NOMBRE_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        
        // Validación de cobro por Kg
        Float cobroKg = paqueteriaDTODatos.getCobroKg();
        
        if (cobroKg == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "cobro por kg", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        if (cobroKg < 0) {
            throw new ValorParametroInvalidoException(MENSAJE_COBRO_KG_INVALIDO_NEGATIVO);
        }
        
        if (cobroKg > COBRO_MAXIMO_KG) {
            throw new ValorParametroInvalidoException(MENSAJE_COBRO_KG_INVALIDO_MAYOR_MAXIMO);
        }
        
        // Validación cobro por hora
        Float cobroHora = paqueteriaDTODatos.getCobroHora();
        
        if (cobroHora == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "cobro por hora", NOMBRE_ENTIDAD_PAQUETERIA)); 
        }
        
        if (cobroHora < 0) {
            throw new ValorParametroInvalidoException(MENSAJE_COBRO_HORA_INVALIDO_NEGATIVO);
        }
        
        if (cobroHora > COBRO_MAXIMO_HORA) {
            throw new ValorParametroInvalidoException(MENSAJE_COBRO_HORA_INVALIDO_MAYOR_MAXIMO);
        }
        
        // Validación de dirección de imagen
        String direccionImagen = paqueteriaDTODatos.getDireccionImagen();
        
        if (direccionImagen == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "dirección de imagen", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        if(direccionImagen.trim().isEmpty()){
            throw new ValorParametroInvalidoException(MENSAJE_DIRECCION_IMAGEN_INVALIDA_CADENA_VACIA);
        }

        // Validación de dirección.
        DireccionDTODatos direccion = paqueteriaDTODatos.getDireccion();
        if (direccion == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "dirección", NOMBRE_ENTIDAD_PAQUETERIA));
        }
        
        // Validación de Código Postal
        String codigoPostal = direccion.getCodigoPostal();

        if(codigoPostal == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "codigo postal", NOMBRE_ENTIDAD_DIRECCION));
        }
 
        if (!codigoPostal.matches(REGEX_CINCO_DIGITOS)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CODIGO_POSTAL_INVALIDO);
        }

        // Validación de colonia
        String coloniaDireccion = direccion.getColonia();

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
        String calleDireccion = direccion.getCalle();

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
        String numeroDireccion = direccion.getNumero();

        if(numeroDireccion == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "numero", NOMBRE_ENTIDAD_DIRECCION));
        }

        if (!numeroDireccion.matches(REGEX_MAS_UN_DIGITO)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NUMERO_DIRECCION_NO_NUMERICO);
        }

        if (numeroDireccion.length() > CANTIDAD_MAXIMA_DIGITOS_NUMERO_DIRECCION) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NUMERO_DIRECCION_MAS_CIEN_DIGITOS);
        }
    }
    private static final Logger LOG = Logger.getLogger(PaqueteriasDAO.class.getName());
}