package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Proveedor;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdProveedorDTODatos;
import edu.student.itson.dissof.dto.datos.ProveedorDTODatos;
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

public class ProveedoresDAO {

    private final String COLECCION_PROVEEDORES = "Proveedores";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_LISTA_NUEVOS_PROVEEDORES_NULA = "La lista de nuevos proveedores es nula.";
    private final String MENSAJE_LISTA_NUEVOS_PROVEEDORES_VACIA = "La lista de nuevos proveedores está vacía.";
    
    // Mensajes para validación de cliente
    private final String MENSAJE_FORMATO_NOMBRE_INVALIDO_CADENA_VACIA = "El nombre de la paquetería es una cadena vacía";
    private final String MENSAJE_FORMATO_NOMBRE_INVALIDO_MAS_CINCUENTA_CARACTERES = "El nombre de la paquetería tiene más de cincuenta caracteres";
    
    private final String REGEX_DIEZ_DIGITOS = "\\d{10}";
    
    private final String MENSAJE_FORMATO_TELEFONO_INVALIDO = "El teléfono del proveedor no se compone de diez dígitos.";
    
    private String REGEX_EMAIL_VALIDO = "[A-Za-z0-9._]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$";
    
    private final String MENSAJE_FORMATO_CORREO_INVALIDO_CADENA_VACIA = "El correo electrónico del cliente es una cadena vacía";
    private final String MENSAJE_FORMATO_CORREO_INVALIDO = "El correo electrónico del cliente no sigue el formato ejemplo@dominio.com";
    private final String MENSAJE_FORMATO_CORREO_INVALIDO_MAS_TRESCIENTOS_VEINTE_CARACTERES = "El correo electrónico tiene más de 320 caracteres";
    
    private final String MENSAJE_DIRECCION_IMAGEN_INVALIDA_CADENA_VACIA = "La dirección de imagen del proveedor está vacía";
    
    // Mensajes para validación de dirección
    private final String REGEX_CINCO_DIGITOS = "\\d{5}";
    private final String REGEX_MAS_UN_DIGITO = "\\d+";
    
    private final String MENSAJE_FORMATO_CODIGO_POSTAL_INVALIDO = "El Código Postal de dirección se se compone de 5 dígitos.";
   
    private final String MENSAJE_FORMATO_COLONIA_INVALIDO_CADENA_VACIA = "La colonia está vacía";
    private final String MENSAJE_FORMATO_COLONIA_MAS_CIEN_CARACTERES = "La colonia tiene más de cien caracteres";
    
    private final String MENSAJE_FORMATO_CALLE_MAS_CIEN_CARACTERES = "La calle tiene más de cien caracteres";
    private final String MENSAJE_FORMATO_CALLE_CADENA_VACIA = "La calle es una cadena de texto vacía.";
    
    private final String MENSAJE_FORMATO_NUMERO_DIRECCION_NO_NUMERICO = "El número de dirección no es numérico";
    private final String MENSAJE_FORMATO_NUMERO_DIRECCION_MAS_CINCO_DIGITOS = "El número de dirección tiene más de cinco dígitos";
    
    private final String NOMBRE_ENTIDAD_PROVEEDOR = "proveedor";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";
    
    private final int CANTIDAD_MAXIMA_CARACTERES_NOMBRE = 50;
    private final int CANTIDAD_MAXIMA_CARACTERES_CORREO_ELECTRONICO = 320;
    private final int CANTIDAD_MAXIMA_CARACTERES_CALLE = 100;
    private final int CANTIDAD_MAXIMA_CARACTERES_COLONIA = 100;
    private final int CANTIDAD_MAXIMA_DIGITOS_NUMERO_DIRECCION = 5;

    public boolean existePorId(IdProveedorDTODatos idProveedorDTODatos) 
            throws FormatoIdInvalidoException,
            ParametroNuloException{
        
        if (idProveedorDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idProveedorDTODatos.getIdProveedor() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        
        String idProveedor = (String) idProveedorDTODatos.getIdProveedor().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idProveedor);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> coleccionProveedores = baseDatos.getCollection(COLECCION_PROVEEDORES);
            return coleccionProveedores.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PROVEEDOR));
        }
    }

    public ProveedorDTODatos recuperarPorId(IdProveedorDTODatos idProveedorDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException {
        
        if (idProveedorDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idProveedorDTODatos.getIdProveedor() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        
        String idProveedor = (String) idProveedorDTODatos.getIdProveedor().getId();

        try {
            ObjectId objectId = new ObjectId(idProveedor);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Proveedor> coleccionProveedores = baseDatos.getCollection(COLECCION_PROVEEDORES,Proveedor.class);
            
            Proveedor proveedor = coleccionProveedores.find(new Document(CAMPO_ID, objectId)).first();
            
            if (proveedor == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PROVEEDOR, idProveedor)
                );
            }
            
            return convertirProveedorADTO(proveedor);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PROVEEDOR));
        }
    }

    public void agregar(ProveedorDTODatos nuevoProveedor) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ParametroNuloException,
               ValorParametroInvalidoException {

        validarCamposObligatorios(nuevoProveedor);

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Proveedor> coleccionProveedores = baseDatos.getCollection(COLECCION_PROVEEDORES, Proveedor.class);

        Proveedor proveedor = new Proveedor(
                nuevoProveedor.getNombre(),
                nuevoProveedor.getTelefono(),
                nuevoProveedor.getCorreoElectronico(),
                nuevoProveedor.getDireccionImagen(),
                new Direccion(
                        nuevoProveedor.getDireccion().getCodigoPostal(),
                        nuevoProveedor.getDireccion().getColonia(),
                        nuevoProveedor.getDireccion().getCalle(),
                        nuevoProveedor.getDireccion().getNumero()
                )     
        );
        
        coleccionProveedores.insertOne(proveedor);
    }

    public void agregar(Collection<ProveedorDTODatos> nuevosProveedores) 
            throws FormatoIdInvalidoException, 
                   RegistroInexistenteException,
                   ValorParametroInvalidoException,
                   ParametroNuloException {
        
        if (nuevosProveedores == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PROVEEDORES_NULA);
        }

        if (nuevosProveedores.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PROVEEDORES_VACIA);
        }
        

        List<Proveedor> proveedores = new LinkedList<>();

        for(ProveedorDTODatos nuevoProveedor: nuevosProveedores){
            
            validarCamposObligatorios(nuevoProveedor);

            Proveedor proveedor = new Proveedor(
                nuevoProveedor.getNombre(),
                nuevoProveedor.getTelefono(),
                nuevoProveedor.getCorreoElectronico(),
                nuevoProveedor.getDireccionImagen(),
                nuevoProveedor.getDireccion() != null ? 
                new Direccion(
                        nuevoProveedor.getDireccion().getCodigoPostal(),
                        nuevoProveedor.getDireccion().getColonia(),
                        nuevoProveedor.getDireccion().getCalle(),
                        nuevoProveedor.getDireccion().getNumero()
                ) : null       
            );

            proveedores.add(proveedor);
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        baseDatos.getCollection(COLECCION_PROVEEDORES, Proveedor.class).insertMany(proveedores);
    }

    public List<ProveedorDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Proveedor> coleccionCarritosCompra = baseDatos.getCollection(COLECCION_PROVEEDORES, Proveedor.class);

        return coleccionCarritosCompra.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirProveedorADTO)
            .collect(Collectors.toList());
    }

    private void validarCamposObligatorios(ProveedorDTODatos proveedorDTODatos) 
            throws ParametroNuloException,
            ValorParametroInvalidoException{

        
        if (proveedorDTODatos == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PROVEEDOR));
        }
         
        String nombre = proveedorDTODatos.getNombre();
        
        if (nombre == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombres", NOMBRE_ENTIDAD_PROVEEDOR)
            );
        }
        
        if (nombre.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRE_INVALIDO_CADENA_VACIA);
        }

        if (nombre.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRE_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        
        // Validación de teléfono
        String telefono = proveedorDTODatos.getTelefono();
        
        if (telefono == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "telefono", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        
        if (!telefono.trim().matches(REGEX_DIEZ_DIGITOS)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_TELEFONO_INVALIDO);
        }
        
        // Validar correo electrónico
        String correoElectronico = proveedorDTODatos.getCorreoElectronico();
        
        if (correoElectronico == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "correo electronico", NOMBRE_ENTIDAD_PROVEEDOR));
        }

        if (correoElectronico.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CORREO_INVALIDO_CADENA_VACIA);
        }

        if (correoElectronico.length() >= CANTIDAD_MAXIMA_CARACTERES_CORREO_ELECTRONICO) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CORREO_INVALIDO_MAS_TRESCIENTOS_VEINTE_CARACTERES);
        }
        
        // Validar dirección de imagen
        String direccionImagen = proveedorDTODatos.getDireccionImagen();
        
        if (direccionImagen == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "dirección de imagen", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        
        if(direccionImagen.trim().isEmpty()){
            throw new ValorParametroInvalidoException(MENSAJE_DIRECCION_IMAGEN_INVALIDA_CADENA_VACIA);
        }
        
        validarCamposDireccion(proveedorDTODatos.getDireccion());
        
    }
    
    private void validarCamposDireccion(DireccionDTODatos direccionDTODatos) 
            throws ValorParametroInvalidoException, 
            ParametroNuloException{
        
        if(direccionDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_DIRECCION));
        }

        if(direccionDTODatos.getCodigoPostal() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "codigo postal", NOMBRE_ENTIDAD_DIRECCION));
        }

        if (!direccionDTODatos.getCodigoPostal().matches(REGEX_CINCO_DIGITOS)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CODIGO_POSTAL_INVALIDO);
        }

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
    
    private ProveedorDTODatos convertirProveedorADTO(Proveedor proveedor) {

        return new ProveedorDTODatos(
            new IdEntidadGenericoDatos(proveedor.getId().toHexString()),
            proveedor.getNombre(),
            proveedor.getTelefono(),
            proveedor.getCorreoElectronico(),
            proveedor.getDireccionImagen(),
            convertirDireccionADTO(proveedor.getDireccion())
        );
    }

    private DireccionDTODatos convertirDireccionADTO(Direccion direccion) {
        return new DireccionDTODatos(
            new IdEntidadGenericoDatos(direccion.getId().toHexString()),
            direccion.getCodigoPostal(),
            direccion.getColonia(),
            direccion.getCalle(),
            direccion.getNumero()
        );
    }
}