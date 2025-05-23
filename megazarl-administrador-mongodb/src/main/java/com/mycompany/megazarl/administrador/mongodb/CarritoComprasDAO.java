package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.CarritoCompras;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoCarrito;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoCarritoDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Collection;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de acceso a datos para operaciones con carritos de compras.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class CarritoComprasDAO {

    private final String COLECCION_CARRITOS = "CarritosCompras";
    private final String COLECCION_CLIENTES = "Clientes";
    private final String COLECCION_PAQUETERIAS = "Paqueterias";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_DTO_ACTUALIZACION_NULO = "DTO de actualización nulo.";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    
    private final String NOMBRE_ENTIDAD_CARRITO = "carrito de compras";
    private final String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private final String NOMBRE_ENTIDAD_PAQUETERIA = "paqueteria";

    /**
     * Método que permite determinar si exite un registro de carrito de compras 
     * almacenado a partir de su ID.
     * @param idCarritoComprasDTODatos Objeto IdCarritoComprasDTODatos que contiene
     * el ID de carrito de compras a buscar.
     * @return Valor booleano, true si el registro de carrito existe, false en caso contrario.
     * @throws ParametroNuloException Se lanza si se determina que uno de los
     * parámetros recibidos es nulo.
     * @throws FormatoInvalidoIdConversionException Se lanza si se determia que
     * el formato de ID recibido es inválido.
     */
    public boolean existePorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos) 
        throws ParametroNuloException,
            FormatoInvalidoIdConversionException {
        
        if (idCarritoComprasDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idCarritoComprasDTODatos.getIdCarritoCompras() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CARRITO));
        }
        
        String idCarrito = (String) idCarritoComprasDTODatos.getIdCarritoCompras().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idCarrito);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_CARRITOS).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CARRITO));
        }
    }

    /**
     * Método que permite obtener un objeto CarritoComprasDTODatos que representa la
     * información de un registro de carrito de compras almacenado.
     * @param idCarritoComprasDTODatos Objeto IdCarritoComprasDTODatos que contiene
     * el ID del carrito de compras a recuperar.
     * @return Objeto CarritoComprasDTODatos que representa la
     * información de un registro de carrito de compras almacenado.
     * @throws FormatoInvalidoIdConversionException Se lanza si se comprueba que el 
     * ID de carrito a recuperar no tiene un formato válido.
     * @throws RegistroInexistenteException Se lanza si se comprueba que no existe
     * un carrito de compras registrado con el ID del parámetro.
     */
    public CarritoComprasDTODatos recuperarPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos) 
        throws FormatoInvalidoIdConversionException, RegistroInexistenteException, ParametroNuloException {
        
        if (idCarritoComprasDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idCarritoComprasDTODatos.getIdCarritoCompras() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CARRITO));
        }
        
        String idCarrito = (String) idCarritoComprasDTODatos.getIdCarritoCompras().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idCarrito);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);
            
            CarritoCompras carrito = carritosCollection.find(new Document(CAMPO_ID, objectId)).first();
            
            if (carrito == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CARRITO, idCarrito)
                );
            }
            
            return convertirCarritoADTO(carrito);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoInvalidoIdConversionException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CARRITO));
        }
    }

    /**
     * Método que permite actualizar los datos de un registro de carrito de compras
     * dentro de la colección de carritos de compras almacenados.
     * @param actualizacionCarritoComprasDTODatos Objeto ActualizacionCarritoComprasDTODatos que contiene
     * la información a actualizar para el carrito de compras a actualizar, incluyendo 
     * su ID para identificarlo.
     * @return Objeto CarritoComprasDTODatos que contiene los datos del carrito de compras
     * actualizado de la base de datos.
     * @throws AgregarInformacionNulaException Se lanza si se comprueba que algún dato
     * dentro del DTO con la información del carrito de compras a actualizar es nulo.
     * @throws FormatoInvalidoIdConversionException Se lanza si se comprueba que un ID
     * no tiene un formato válido.
     * @throws RegistroInexistenteException Se lanza si no se encuentra un registro
     * de carrito de compras con el ID del parámetro para actualizar.
     */
    public CarritoComprasDTODatos actualizar(ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos)
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {

        if (actualizacionCarritoComprasDTODatos == null) {
            throw new AgregarInformacionNulaException(MENSAJE_DTO_ACTUALIZACION_NULO);
        }

        String idCarritoString = validarIdActualizacion(actualizacionCarritoComprasDTODatos.getId());
        ObjectId idCarrito = new ObjectId(idCarritoString);

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> carritosCollection = 
                baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        verificarExistenciaCarrito(idCarrito, carritosCollection);

        CarritoCompras carrito = carritosCollection.find(Filters.eq(CAMPO_ID, idCarrito)).first();
        if (carrito == null) {
            throw new RegistroInexistenteException(String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CARRITO, idCarritoString));
        }

        if (actualizacionCarritoComprasDTODatos.tieneEsVigente()) {
            carrito.setEsVigente(actualizacionCarritoComprasDTODatos.getEsVigente());
        }

        if (actualizacionCarritoComprasDTODatos.tieneIdPaqueteria()) {
            ObjectId idPaqueteria = validarExistenciaPaqueteria(actualizacionCarritoComprasDTODatos.getIdPaqueteria());
            carrito.setIdPaqueteria(idPaqueteria);
        }

        if (actualizacionCarritoComprasDTODatos.tieneIdsProductosCarrito()) {
            carrito.setProductosCarrito(
                convertirProductosCarritoDTO(actualizacionCarritoComprasDTODatos.getIdsProductosCarrito())
            );
        }

        carritosCollection.replaceOne(Filters.eq(CAMPO_ID, idCarrito), carrito);

        return convertirCarritoADTO(carrito);
    }

    /**
     * Método que permite agregar un nuevo carrito de compras a la colección
     * de carritos de compras.
     * @param nuevoCarrito Objeto CarritoComprasDTODatos que contiene los datos
     * necesarios para registrar un nuevo carrito de compras.
     * @throws AgregarInformacionNulaException Se lanza si se determina que uno o
     * más datos necesarios para el registro del nuevo carrito de compras 
     * son nulos.
     * @throws FormatoInvalidoIdConversionException Se lanza si se comprueba que 
     * un ID tiene formato inválido.
     * @throws RegistroInexistenteException Si lanza si se comprueba que no existen
     * los registros en las colecciones con las que se relaciona el nuevo
     * carrito de compras.
     */
    public void agregar(CarritoComprasDTODatos nuevoCarrito) 
        throws AgregarInformacionNulaException, 
               FormatoInvalidoIdConversionException, 
               RegistroInexistenteException {
        
        if (nuevoCarrito == null) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_CARRITO));
        }

        if (nuevoCarrito.getIdCliente() == null) {
            throw new AgregarInformacionNulaException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CLIENTE));
            
        }
        
        validarExistenciaCliente(nuevoCarrito.getIdCliente());
        validarExistenciaPaqueteria(nuevoCarrito.getIdPaqueteria());

        CarritoCompras carrito = new CarritoCompras(
            nuevoCarrito.getEsVigente(),
            new ObjectId((String)nuevoCarrito.getIdCliente().getId()),
            new ObjectId((String)nuevoCarrito.getIdPaqueteria().getId())
        );
        
        carrito.setProductosCarrito(convertirProductosCarritoDTO(nuevoCarrito.getProductosCarrito()));

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class).insertOne(carrito);
    }
    
    /**
    * Método que permite agregar una colección de nuevos carritos de compras a la
    * colección de carritos de compras.
    * @param nuevosCarritos Colección de objetos CarritoComprasDTODatos que contienen 
    * los datos necesarios para registrar nuevos carritos de compras.
    * @throws AgregarInformacionNulaException Se lanza si se determina que la colección
    * o uno o más datos necesarios para el registro de alguno de los nuevos carritos son nulos.
    * @throws FormatoInvalidoIdConversionException Se lanza si se comprueba que un ID tiene formato inválido.
    * @throws RegistroInexistenteException Se lanza si se comprueba que no existen los registros en las
    * colecciones con las que se relaciona alguno de los nuevos carritos.
    */
   public void agregar(Collection<CarritoComprasDTODatos> nuevosCarritos) 
            throws AgregarInformacionNulaException, 
                   FormatoInvalidoIdConversionException, 
                   RegistroInexistenteException {

        if (nuevosCarritos == null || nuevosCarritos.isEmpty()) {
            throw new AgregarInformacionNulaException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_CARRITO)
            );
        }

        List<CarritoCompras> carritos = new ArrayList<>();

        for (CarritoComprasDTODatos nuevoCarrito : nuevosCarritos) {
            if (nuevoCarrito.getIdCliente() == null) {
                throw new AgregarInformacionNulaException(
                    String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CLIENTE)
                );
            }

            validarExistenciaCliente(nuevoCarrito.getIdCliente());
            validarExistenciaPaqueteria(nuevoCarrito.getIdPaqueteria());

            CarritoCompras carrito = new CarritoCompras(
                nuevoCarrito.getEsVigente(),
                new ObjectId((String) nuevoCarrito.getIdCliente().getId()),
                new ObjectId((String) nuevoCarrito.getIdPaqueteria().getId())
            );

            carrito.setProductosCarrito(
                convertirProductosCarritoDTO(nuevoCarrito.getProductosCarrito())
            );

            carritos.add(carrito);
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> collection = 
             baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        collection.insertMany(carritos);
    }
   
   /**
    * Método que permite recuperar todos los carritos de compras registrados en el sistema.
    * @return Objeto {@literal List<CarritoComprasDTODatos>} que representa la lsita de los objetos 
    * CarritoComprasDTODatos de cada uno de los carritos registrados.
    */
   public List<CarritoComprasDTODatos> recuperarTodos() {
       MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
       MongoCollection<CarritoCompras> carritosCollection = 
           baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

       return carritosCollection.find()
           .into(new ArrayList<>())
           .stream()
           .map(this::convertirCarritoADTO)
           .collect(Collectors.toList());
   }

    /**
     * Método auxiliar que permite validar el ID del registro que quiere actuaizarse.
     * Se devuelve el ID pero como una cadena de texto que reprsenta un número
     * hexadecimal de 24 dígitos.
     * @param idEntidadGenericoDatos Objeto IdEntidadGenericoDatos que contiene
     * el ID del registro que quiere actualizarse.
     * @return Objeto String que representa el ID en una cadena de texto, en 
     * formato hexadecimal de 24 dígitos.
     * @throws FormatoInvalidoIdConversionException Se lanza si se comprueba que
     * el formato del ID es inválido.
     */
    private String validarIdActualizacion(IdEntidadGenericoDatos idEntidadGenericoDatos) 
        throws FormatoInvalidoIdConversionException {
        if (idEntidadGenericoDatos == null || idEntidadGenericoDatos.getId() == null) throw new IllegalArgumentException("ID nulo");
        try {
            return new ObjectId((String) idEntidadGenericoDatos.getId()).toHexString();
        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoIdConversionException(MENSAJE_ID_FORMATO_INVALIDO);
        }
    }

    /**
     * Método auxiliar que permite verificar si existe el registro de un carrito de compras
     * con el ID del parámetro en la colección del parámetro.
     * @param idCarrito Objeto ObjectId que representa el ID del registro a buscar.
     * @param collection Objeto {@literal MongoCollection<CarritoCompras>} que representa
     * la colección en la que será buscado el registro.
     * @throws RegistroInexistenteException Se lanza si se determina que no existe el registro
     * en la colección.
     */
    private void verificarExistenciaCarrito(ObjectId idCarrito, MongoCollection<CarritoCompras> collection) 
            throws RegistroInexistenteException {
        
        if (collection.countDocuments(eq(CAMPO_ID, idCarrito)) == 0) {
            
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CARRITO, idCarrito.toHexString())
            );
        }
    }

    /**
     * Método que permite convertir una lista de objetos ProductoCarritoDTODatos a
     * una lista de objetos ProductoCarrito.
     * @param productosCarritoDTO Objeto {@literal List<ProductoCarritoDTODatos>} que representa
     * la lista de objeto de tipo ProductoCarritoDTODatos que serán convertidos.
     * @return Objeto {@literal List<ProductoCarrito>} Representa la lsita resultante con
     * los objetos ProductoCarrito.
     */
    private List<ProductoCarrito> convertirProductosCarritoDTO(List<ProductoCarritoDTODatos> productosCarritoDTO) {
       
        return productosCarritoDTO.stream()     
            .map(productoCarrito -> new ProductoCarrito(
                new ObjectId((String)productoCarrito.getIdProducto().getId()),
                productoCarrito.getCantidad()
            ))
            .collect(Collectors.toList());
        
    }
    
    /**
     * Método que permite covertir una lista de objetos ProductoCarrito a una lista de
     * objetos ProductoCarritoDTODatos.
     * @param productos Objeto {@literal List<ProductoCarrito>} que represeta 
     * la lista de objetos ProductoCarrito a convertir.
     * @return Objeto {@literal List<ProductoCarritoDTODatos>} que representa la 
     * lista de objetos ProductoCarritoDTODatos resultante.
     */
    private List<ProductoCarritoDTODatos> convertirProductosEntidad(List<ProductoCarrito> productos) {
        return productos.stream()
            .map(productoCarrito -> new ProductoCarritoDTODatos(
                productoCarrito.getCantidad(),
                new IdEntidadGenericoDatos(productoCarrito.getId().toHexString())
                
            ))
            .collect(Collectors.toList());
    }

    /**
     * Método que permite realizar la conversión de un objeto CarritoCompras
     * a un DTO de tipo CarritoComprasDTODatos.
     * @param carrito Objeto CarritoCompras del que se obtendrán los datos para
     * armar al DTO.
     * @return Objeto CarritoComprasDTODatos que representa el DTO que contiene
     * los datos del carrito de compras.
     */
    private CarritoComprasDTODatos convertirCarritoADTO(CarritoCompras carrito) {
        
        return new CarritoComprasDTODatos(
            new IdEntidadGenericoDatos(carrito.getId().toHexString()),
            carrito.getEsVigente(),
            new IdEntidadGenericoDatos(carrito.getIdCliente().toHexString()),
            new IdEntidadGenericoDatos(carrito.getIdPaqueteria().toHexString()),
            convertirProductosEntidad(carrito.getProductosCarrito())
        );
        
    }

    /**
     * Método que permite verficar si existe un registro de un cliente con el ID
     * del parámetro.
     * @param idEntidadGenericoDatos Objeto IdEntidadGenericoDatos que contiene
     * el ID del cliente buscado.
     * @return Objeto ObjectId que representa el ID del registro en la base de datos.
     * @throws RegistroInexistenteException Se lanza si se comprueba que no existe un registro
     * del cliente con el ID del parámetro.
     * @throws FormatoInvalidoIdConversionException Se lanza si se determina que el
     * formato del ID es inválido.
     */
    private void validarExistenciaCliente(IdEntidadGenericoDatos idCliente) 
        throws RegistroInexistenteException, FormatoInvalidoIdConversionException {
        
        try {
            ObjectId id = new ObjectId((String) idCliente.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            if (baseDatos.getCollection(COLECCION_CLIENTES).countDocuments(new Document(CAMPO_ID, id)) == 0) {
                throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, (String) idCliente.getId()));
            }
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoInvalidoIdConversionException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CLIENTE));
        }
    }
    
    /**
     * Método que permite verficar si existe un registro de paquetería con el ID
     * del parámetro.
     * @param idEntidadGenericoDatos Objeto IdEntidadGenericoDatos que contiene
     * el ID de la paquetería buscada.
     * @return Objeto ObjectId que representa el ID del registro en la base de datos.
     * @throws RegistroInexistenteException Se lanza si se comprueba que no existe un registro
     * de la paquetería con el ID del parámetor.
     * @throws FormatoInvalidoIdConversionException Se lanza si se determina que el
     * formato del ID es inválido.
     */
    private ObjectId validarExistenciaPaqueteria(IdEntidadGenericoDatos idPaqueteria) 
        throws RegistroInexistenteException, FormatoInvalidoIdConversionException {

        try {
            
            ObjectId id = new ObjectId((String) idPaqueteria.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_PAQUETERIAS).countDocuments(eq(CAMPO_ID, id)) == 0) {
                
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, (String) idPaqueteria.getId()));
            }
            return id;

        } catch (IllegalArgumentException ex) {
            throw new FormatoInvalidoIdConversionException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PAQUETERIA));
        }
    }
}