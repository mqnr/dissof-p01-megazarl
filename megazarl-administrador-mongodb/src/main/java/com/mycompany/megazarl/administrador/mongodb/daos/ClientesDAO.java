package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Cliente;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionClienteDTODatos;
import edu.student.itson.dissof.dto.datos.ClienteDTODatos;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdClienteDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase de acceso a datos para operaciones con clientes.
 */
public class ClientesDAO {

    private final String COLECCION_CLIENTES = "Clientes";
    private final String COLECCION_DIRECCIONES = "Direcciones";
    private final String CAMPO_ID = "_id";

    
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_CLIENTES_NULA = "La lista de nuevos carritos es nula.";
    private final String MENSAJE_LISTA_NUEVOS_CLIENTES_VACIA = "La lista de nuevos carritos está vacía.";
    
    // Mensajes para validación de cliente
    private final String MENSAJE_FORMATO_NOMBRES_INVALIDO_CADENA_VACIA = "El nombre del cliente es una cadena vacía";
    private final String MENSAJE_FORMATO_NOMBRES_INVALIDO_MAS_CINCUENTA_CARACTERES = "El nombre del cliente tiene más de cincuenta caracteres";
    
    private final String MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_CADENA_VACIA = "El apellido paterno del cliente es una cadena vacía";
    private final String MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_MAS_CINCUENTA_CARACTERES = "El apellido paterno del cliente tiene más de cincuenta caracteres";
    
    private final String MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_CADENA_VACIA = "El apellido materno del cliente es una cadena vacía";
    private final String MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_MAS_CINCUENTA_CARACTERES = "El apellido materno del cliente tiene más de cincuenta caracteres";
    
    private final String REGEX_DIEZ_DIGITOS = "\\d{10}";
    
    private final String MENSAJE_FORMATO_TELEFONO_INVALIDO = "El teléfono del cliente no se compone de diez dígitos.";
    
    private String REGEX_EMAIL_VALIDO = "[A-Za-z0-9._]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$";
    
    private final String MENSAJE_FORMATO_CORREO_INVALIDO_CADENA_VACIA = "El correo electrónico del cliente es una cadena vacía";
    private final String MENSAJE_FORMATO_CORREO_INVALIDO = "El correo electrónico del cliente no sigue el formato ejemplo@dominio.com";
    private final String MENSAJE_FORMATO_CORREO_INVALIDO_MAS_TRESCIENTOS_VEINTE_CARACTERES = "El correo electrónico tiene más de 320 caracteres";
    
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
    
    
    private final String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";
    
    private final int CANTIDAD_MAXIMA_CARACTERES_NOMBRES_APELLIDOS = 50;
    private final int CANTIDAD_MAXIMA_CARACTERES_CORREO_ELECTRONICO = 320;
    private final int CANTIDAD_MAXIMA_CARACTERES_CALLE = 100;
    private final int CANTIDAD_MAXIMA_CARACTERES_COLONIA = 100;
    private final int CANTIDAD_MAXIMA_DIGITOS_NUMERO_DIRECCION = 5;

    /**
     * Método que permite determinar si exite un registro de carrito de compras 
     * almacenado a partir de su ID.
     * @param idClienteDTODatos Objeto IdClienteDTODatos que contiene
     * el ID de carrito de compras a buscar.
     * @return Valor booleano, true si el registro de carrito existe, false en caso contrario.
     * @throws ParametroNuloException Se lanza si se determina que uno de los
     * parámetros recibidos es nulo.
     * @throws FormatoIdInvalidoException Se lanza si se determia que
     * el formato de ID recibido es inválido.
     */
    public boolean existePorId(IdClienteDTODatos idClienteDTODatos) 
        throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        if (idClienteDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idClienteDTODatos.getIdCliente() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CLIENTE));
        }
        
        String idCarrito = (String) idClienteDTODatos.getIdCliente().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idCarrito);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_CLIENTES).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CLIENTE));
        }
    }

    public ClienteDTODatos recuperarPorId(IdClienteDTODatos idClienteDTODatos) 
        throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {
        
        if (idClienteDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idClienteDTODatos.getIdCliente() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CLIENTE));
        }
        
        String idCliente = (String) idClienteDTODatos.getIdCliente().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idCliente);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Cliente> coleccionClientes = baseDatos.getCollection(COLECCION_CLIENTES, Cliente.class);
            
            Cliente cliente = coleccionClientes.find(new Document(CAMPO_ID, objectId)).first();
            
            if (cliente == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, idCliente)
                );
            }
            
            return convertirClienteADTO(cliente);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CLIENTE));
        }
    }

    public ClienteDTODatos actualizar(ActualizacionClienteDTODatos actualizacionClienteDTODatos) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ParametroNuloException,
               ValorParametroInvalidoException {
        
        if (actualizacionClienteDTODatos.getId() == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(actualizacionClienteDTODatos.getId().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CLIENTE));
        }

        String idClienteString = validarIdActualizacion(actualizacionClienteDTODatos.getId().getId());
        ObjectId idCliente = new ObjectId(idClienteString);

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Cliente> coleccionClientes = baseDatos.getCollection(COLECCION_CLIENTES, Cliente.class);

        verificarExistenciaCliente(idCliente, coleccionClientes);

        Cliente cliente = coleccionClientes.find(eq(CAMPO_ID, idCliente)).first();

        if(cliente == null){
            throw new RegistroInexistenteException(String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, idClienteString));
        }
        
        if (actualizacionClienteDTODatos.tieneDireccionEnvio()) {
            
            ObjectId idDireccionRegistrada = obtenerIdDireccionRegistrada(actualizacionClienteDTODatos.getId());
            
            validarCamposDireccion(actualizacionClienteDTODatos.getDireccionEnvio());
            
            cliente.setDireccionEnvio(
                    new Direccion(
                            idDireccionRegistrada,
                            actualizacionClienteDTODatos.getDireccionEnvio().getCodigoPostal(),
                            actualizacionClienteDTODatos.getDireccionEnvio().getColonia(),
                            actualizacionClienteDTODatos.getDireccionEnvio().getCalle(),
                            actualizacionClienteDTODatos.getDireccionEnvio().getNumero()
                    )
            );
        }

        coleccionClientes.replaceOne(eq(CAMPO_ID, idCliente), cliente);
        
        return convertirClienteADTO(cliente);
    }

    public void agregar(ClienteDTODatos nuevoCliente) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ValorParametroInvalidoException,
               ParametroNuloException {
        
        validarCamposObligatorios(nuevoCliente);

        Cliente cliente = new Cliente(
            nuevoCliente.getNombres(),
            nuevoCliente.getApellidoMaterno(),
            nuevoCliente.getApellidoPaterno(),
            nuevoCliente.getTelefono(),
            nuevoCliente.getCorreoElectronico(),
            nuevoCliente.getDireccionEnvio() != null ? 
                new Direccion(
                        nuevoCliente.getDireccionEnvio().getCodigoPostal(),
                        nuevoCliente.getDireccionEnvio().getColonia(),
                        nuevoCliente.getDireccionEnvio().getCalle(),
                        nuevoCliente.getDireccionEnvio().getNumero()
                ) : null
        );

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Cliente> coleccioClientes = baseDatos.getCollection(COLECCION_CLIENTES, Cliente.class);
        
        coleccioClientes.insertOne(cliente);
    }
    
    public void agregar(Collection<ClienteDTODatos> nuevosClientes) 
            throws FormatoIdInvalidoException, 
                   RegistroInexistenteException,
                   ValorParametroInvalidoException,
                   ParametroNuloException {
        
        if (nuevosClientes == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_CLIENTES_NULA);
        }

        if (nuevosClientes.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_CLIENTES_VACIA);
        }

        List<Cliente> clientes = new LinkedList<>();

        for(ClienteDTODatos nuevoCliente: nuevosClientes){
            
            validarCamposObligatorios(nuevoCliente);

            Cliente cliente = new Cliente(
                nuevoCliente.getNombres(),
                nuevoCliente.getApellidoMaterno(),
                nuevoCliente.getApellidoPaterno(),
                nuevoCliente.getTelefono(),
                nuevoCliente.getCorreoElectronico(),
                nuevoCliente.getDireccionEnvio() != null ? 
                    new Direccion(
                            nuevoCliente.getDireccionEnvio().getCodigoPostal(),
                            nuevoCliente.getDireccionEnvio().getColonia(),
                            nuevoCliente.getDireccionEnvio().getCalle(),
                            nuevoCliente.getDireccionEnvio().getNumero()
                    ) : null
            );

            clientes.add(cliente);
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();

        MongoCollection<Cliente> coleccionClinetes = baseDatos.getCollection(COLECCION_CLIENTES, Cliente.class);
        
        coleccionClinetes.insertMany(clientes);
        
    }
    
    
    public List<ClienteDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Cliente> coleccionClientes = baseDatos.getCollection(COLECCION_CLIENTES, Cliente.class);

        return coleccionClientes.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirClienteADTO)
            .collect(Collectors.toList());
    }


    private String validarIdActualizacion(Object id) throws FormatoIdInvalidoException {
        
        try {
            
            return new ObjectId((String) id).toHexString();
            
        } catch (IllegalArgumentException e) {
            
            throw new FormatoIdInvalidoException(
                String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CLIENTE));
            
        }
    }

    private void verificarExistenciaCliente(ObjectId idCliente, MongoCollection<Cliente> coleccionClientes) 
        throws RegistroInexistenteException {
        
        if (coleccionClientes.countDocuments(eq(CAMPO_ID, idCliente)) == 0) {
            
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, idCliente.toHexString())
            );
            
        }
    }

    private ObjectId obtenerIdDireccionRegistrada(IdEntidadGenericoDatos idDireccionDTO) 
        throws FormatoIdInvalidoException, RegistroInexistenteException {
        
        if (idDireccionDTO == null){
            return null;
        }
        
        try {
            
            ObjectId id = new ObjectId((String) idDireccionDTO.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            if (baseDatos.getCollection(COLECCION_DIRECCIONES).countDocuments(eq(CAMPO_ID, id)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, id.toHexString())
                );
            }
            return id;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(
                String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_DIRECCION));
        }
    }


    private ClienteDTODatos convertirClienteADTO(Cliente cliente) {
        return new ClienteDTODatos(
                
            new IdEntidadGenericoDatos(cliente.getId().toHexString()),
            cliente.getNombres(),
            cliente.getApellidoPaterno(),
            cliente.getApellidoMaterno(),
            cliente.getTelefono(),
            cliente.getCorreoElectronico(),
            convertirDireccionADTO(cliente.getDireccionEnvio())
                
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
    
    private void validarCamposObligatorios(ClienteDTODatos clienteDTODatos)
            throws ValorParametroInvalidoException,
            ParametroNuloException {
        
        
        if (clienteDTODatos == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_CLIENTE));
        }

        // Validación de nombres
        String nombres = clienteDTODatos.getNombres();
        
        if (nombres == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombres", NOMBRE_ENTIDAD_CLIENTE)
            );
        }
        
        if (nombres.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRES_INVALIDO_CADENA_VACIA);
        }

        if (nombres.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRES_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRES_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        

        // Validación de apellido paterno
        String apellidoPaterno = clienteDTODatos.getApellidoMaterno();
        
        if (apellidoPaterno == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "apellido paterno", NOMBRE_ENTIDAD_CLIENTE));
        }
        
        if (apellidoPaterno.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_CADENA_VACIA);
        }

        if (apellidoPaterno.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRES_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_PATERNO_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        
        // Validaciónd de apellido paterno
        String apellidoMaterno = clienteDTODatos.getApellidoMaterno();
        
        if (apellidoMaterno == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "apellido materno", NOMBRE_ENTIDAD_CLIENTE));
        }
        
        if (apellidoMaterno.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_CADENA_VACIA);
        }

        if (apellidoMaterno.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRES_APELLIDOS) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_APELLIDO_MATERNO_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        
        // Validación de teléfono
        String telefono = clienteDTODatos.getTelefono();
        
        if (telefono == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "telefono", NOMBRE_ENTIDAD_CLIENTE));
        }
        
        if (!telefono.trim().matches(REGEX_DIEZ_DIGITOS)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_TELEFONO_INVALIDO);
        }
        
        // Validación de correo electrónico
        String correoElectronico = clienteDTODatos.getCorreoElectronico();
        
        if (correoElectronico == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "correo electronico", NOMBRE_ENTIDAD_CLIENTE));
        }

        if (correoElectronico.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CORREO_INVALIDO_CADENA_VACIA);
        }

        if (correoElectronico.length() >= CANTIDAD_MAXIMA_CARACTERES_CORREO_ELECTRONICO) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CORREO_INVALIDO_MAS_TRESCIENTOS_VEINTE_CARACTERES);
        }

        // Validar que el correo electrónico cumpla con el formato especificado
        if (!correoElectronico.matches(REGEX_EMAIL_VALIDO)) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_CORREO_INVALIDO);
        }
        
        validarCamposDireccion(clienteDTODatos.getDireccionEnvio());
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
}