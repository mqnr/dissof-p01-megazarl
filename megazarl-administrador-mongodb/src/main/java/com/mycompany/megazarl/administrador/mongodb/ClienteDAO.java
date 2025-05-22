package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Cliente;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionClienteDTO;
import edu.student.itson.dissof.dto.datos.ClienteIdsRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public boolean existePorId(IdEntidadGenerico idClienteDTO) 
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

    public ClienteIdsRelacionesDTO recuperarPorId(IdEntidadGenerico idClienteDTO) 
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
            
            ClienteIdsRelacionesDTO clienteIdsRelacionesDTO = new ClienteIdsRelacionesDTO(
                    new IdEntidadGenerico(cliente.getId()), 
                    cliente.getNombres(), 
                    cliente.getApellidoPaterno(), 
                    cliente.getApellidoMaterno(), 
                    cliente.getTelefono(), 
                    cliente.getCorreoElectronico(),
                    new IdEntidadGenerico(cliente.getIdDireccionEnvio()));
            
            return clienteIdsRelacionesDTO;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
        }
    }

    public ClienteIdsRelacionesDTO actualizar(ActualizacionClienteDTO dto) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {
        
        if (dto == null) {
            throw new AgregarInformacionNulaException(MENSAJE_DTO_ACTUALIZACION_NULO);
        }
        
        if (dto.getId() == null || dto.getId().getId() == null) {
            throw new AgregarInformacionNulaException("ID de cliente nulo.");
        }

        String idClienteString = (String) dto.getId().getId();
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
        
        if (dto.tieneNombres()) {
            actualizaciones.append("nombres", dto.getNombres());
        }
        if (dto.tieneApellidoPaterno()) {
            actualizaciones.append("apellidoPaterno", dto.getApellidoPaterno());
        }
        if (dto.tieneApellidoMaterno()) {
            actualizaciones.append("apellidoMaterno", dto.getApellidoMaterno());
        }
        
        ObjectId idDireccion = null;
        if (dto.tieneDireccionEnvio()) {
            String idDireccionString = String.valueOf(dto.getIdDireccionEnvio());
            try {
                idDireccion = new ObjectId(idDireccionString);
            } catch (IllegalArgumentException e) {
                throw new FormatoInvalidoIdConversionException("Formato de ID de dirección inválido.");
            }
            
            // Validar existencia de dirección
            MongoCollection<Document> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES);
            if (direccionesCollection.countDocuments(new Document(CAMPO_ID, idDireccion)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccionString)
                );
            }
            actualizaciones.append("idDireccionEnvio", idDireccion);
        }

        if (!actualizaciones.isEmpty()) {
            clientesCollection.updateOne(
                new Document(CAMPO_ID, idCliente),
                new Document("$set", actualizaciones)
            );
        }

        return convertirClienteADTO(clientesCollection.find(new Document(CAMPO_ID, idCliente)).first());
    }

    public void agregar(ClienteIdsRelacionesDTO nuevoCliente) 
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

        // Validar dirección si existe
        ObjectId idDireccion = null;
        if (nuevoCliente.getIdDireccionEnvio() != null) {
            String idDireccionString = (String) nuevoCliente.getIdDireccionEnvio().getId();
            try {
                idDireccion = new ObjectId(idDireccionString);
            } catch (IllegalArgumentException e) {
                throw new FormatoInvalidoIdConversionException("Formato de ID de dirección inválido.");
            }

            MongoCollection<Document> direccionesCollection = baseDatos.getCollection(COLECCION_DIRECCIONES);
            if (direccionesCollection.countDocuments(new Document(CAMPO_ID, idDireccion)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_DIRECCION, idDireccionString)
                );
            }
        }

        Cliente cliente = new Cliente(
            nuevoCliente.getNombres(),
            nuevoCliente.getApellidoMaterno(),
            nuevoCliente.getApellidoPaterno(),
            nuevoCliente.getTelefono(),
            nuevoCliente.getCorreoElectronico(),
            idDireccion
        );

        clientesCollection.insertOne(cliente);
    }

    private ClienteIdsRelacionesDTO convertirClienteADTO(Cliente cliente) {
        IdEntidadGenerico idCliente = new IdEntidadGenerico(cliente.getId().toHexString());
        
        IdEntidadGenerico idDireccion = null;
        if (cliente.getIdDireccionEnvio() != null) {
            idDireccion = new IdEntidadGenerico(cliente.getIdDireccionEnvio().toHexString());
        }

        return new ClienteIdsRelacionesDTO(
            idCliente,
            cliente.getNombres(),
            cliente.getApellidoPaterno(),
            cliente.getApellidoMaterno(),
            cliente.getTelefono(),
            cliente.getCorreoElectronico(),
            idDireccion
        );
    }
}