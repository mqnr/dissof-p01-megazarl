package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Cliente;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Direccion;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionClienteDTODatos;
import edu.student.itson.dissof.dto.datos.ClienteDTODatos;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase de acceso a datos que permite realizar operaciones sobre registros de cliente.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class ClienteDAO {

    
    private final String COLECCION_CLIENTES = "Clientes";
    private final String COLECCION_DIRECCIONES = "Direcciones";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_ID_NULO = "El ID recibido es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_DTO_ACTUALIZACION_NULO = "DTO de actualización nulo.";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    
    private final String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private final String NOMBRE_ENTIDAD_DIRECCION = "dirección";

    /**
     * Método que permite determinar si existe un cliente registrado con el ID del 
     * parámetro.
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID buscado.
     * @return Valor boolean, true si existe un cliente con el ID, false en caso
     * contrario.
     * @throws FormatoInvalidoIdConversionException Se lanza si se comprueba que el 
     * ID del parámetro tiene un formato inválido.
     */
    public boolean existePorId(IdEntidadGenericoDatos idClienteDTO) 
        throws FormatoInvalidoIdConversionException {
        
        if (idClienteDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idCliente = (String) idClienteDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idCliente);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> clientesCollection = baseDatos.getCollection(COLECCION_CLIENTES);
            return clientesCollection.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public ClienteDTODatos recuperarPorId(IdEntidadGenericoDatos idClienteDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
        
        if (idClienteDTO == null) {
            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
        }
        
        String idCliente = (String) idClienteDTO.getId();
        
        try {
            ObjectId objectId = new ObjectId(idCliente);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Cliente> clientesCollection = baseDatos.getCollection(
                COLECCION_CLIENTES, 
                Cliente.class
            );
            
            Cliente cliente = clientesCollection.find(new Document(CAMPO_ID, objectId)).first();
            
            if (cliente == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, idCliente)
                );
            }
            
            return convertirClienteADTO(cliente);
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public ClienteDTODatos actualizar(ActualizacionClienteDTODatos actualizacionClienteDTO) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {
        
        if (actualizacionClienteDTO == null) {
            throw new AgregarInformacionNulaException(MENSAJE_DTO_ACTUALIZACION_NULO);
        }
        
        if (actualizacionClienteDTO.getId() == null || actualizacionClienteDTO.getId().getId() == null) {
            throw new AgregarInformacionNulaException("ID de cliente nulo.");
        }

        String idClienteString = (String) actualizacionClienteDTO.getId().getId();
        ObjectId idCliente;
        
        try {
            idCliente = new ObjectId(idClienteString);
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Cliente> clientesCollection = baseDatos.getCollection(
            COLECCION_CLIENTES, 
            Cliente.class
        );

        // Verificar existencia del cliente
        Cliente clienteExistente = clientesCollection.find(new Document(CAMPO_ID, idCliente)).first();
        if (clienteExistente == null) {
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, idClienteString)
            );
        }

        Document actualizaciones = new Document();
        
        if (actualizacionClienteDTO.tieneNombres()) {
            actualizaciones.append("nombres", actualizacionClienteDTO.getNombres());
        }
        if (actualizacionClienteDTO.tieneApellidoPaterno()) {
            actualizaciones.append("apellidoPaterno", actualizacionClienteDTO.getApellidoPaterno());
        }
        if (actualizacionClienteDTO.tieneApellidoMaterno()) {
            actualizaciones.append("apellidoMaterno", actualizacionClienteDTO.getApellidoMaterno());
        }
        
        if (actualizacionClienteDTO.tieneDireccionEnvio()) {
            ObjectId idDireccion = validarYConvertirDireccion(actualizacionClienteDTO.getId());
            Direccion direccion = new Direccion();
            direccion.setId(idDireccion);
            actualizaciones.append("direccionEnvio", direccion);
        }

        if (!actualizaciones.isEmpty()) {
            clientesCollection.updateOne(
                new Document(CAMPO_ID, idCliente),
                new Document("$set", actualizaciones)
            );
        }

        return convertirClienteADTO(clientesCollection.find(new Document(CAMPO_ID, idCliente)).first());
    }

    public void agregar(ClienteDTODatos nuevoCliente) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {
        
        if (nuevoCliente == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_CLIENTE));
        }

        if (nuevoCliente.getNombres() == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombres", NOMBRE_ENTIDAD_CLIENTE));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Cliente> clientesCollection = baseDatos.getCollection(COLECCION_CLIENTES, Cliente.class);

        // Validar y construir dirección
        Direccion direccionEnvio = null;
        if (nuevoCliente.getDireccionEnvio() != null) {
            ObjectId idDireccion = validarYConvertirDireccion(nuevoCliente.getDireccionEnvio().getId());
            direccionEnvio = new Direccion();
            direccionEnvio.setId(idDireccion);
        }

        Cliente cliente = new Cliente(
            nuevoCliente.getNombres(),
            nuevoCliente.getApellidoMaterno(),
            nuevoCliente.getApellidoPaterno(),
            nuevoCliente.getTelefono(),
            nuevoCliente.getCorreoElectronico(),
            direccionEnvio
        );

        clientesCollection.insertOne(cliente);
    }

    private ObjectId validarYConvertirDireccion(IdEntidadGenericoDatos idDireccionDTO) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
        
        if (idDireccionDTO == null || idDireccionDTO.getId() == null) {
            return null;
        }

        String idDireccionString = (String) idDireccionDTO.getId();
        
        try {
            ObjectId idDireccion = new ObjectId(idDireccionString);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Document> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES);
            
            if (direccionesCollection.countDocuments(new Document(CAMPO_ID, idDireccion)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccionString)
                );
            }
            
            return idDireccion;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID de dirección inválido.");
        }
    }

    private ClienteDTODatos convertirClienteADTO(Cliente cliente) throws RegistroInexistenteException {
        IdEntidadGenericoDatos idCliente = new IdEntidadGenericoDatos(cliente.getId().toHexString());
        
        DireccionDTODatos direccionDTO = null;
        if (cliente.getDireccionEnvio() != null && cliente.getDireccionEnvio().getId() != null) {
            direccionDTO = obtenerDireccionCompleta(cliente.getDireccionEnvio().getId());
        }

        return new ClienteDTODatos(
            idCliente,
            cliente.getNombres(),
            cliente.getApellidoPaterno(),
            cliente.getApellidoMaterno(),
            cliente.getTelefono(),
            cliente.getCorreoElectronico(),
            direccionDTO
        );
    }


    private DireccionDTODatos obtenerDireccionCompleta(ObjectId idDireccion) 
        throws RegistroInexistenteException {
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Direccion> direccionesCollection = baseDatos.getCollection(
            COLECCION_DIRECCIONES, 
            Direccion.class
        );
        
        Direccion direccion = direccionesCollection.find(new Document(CAMPO_ID, idDireccion)).first();
        
        if (direccion == null) {
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccion.toHexString())
            );
        }

        return new DireccionDTODatos(
            direccion.getCodigoPostal(),
            direccion.getColonia(),
            direccion.getCalle(),
            direccion.getNumero()
        );
    }
}