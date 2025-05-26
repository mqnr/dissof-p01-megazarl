package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.CarritoCompras;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoCarrito;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.ActualizacionProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoCarritoDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.bson.conversions.Bson;

/**
 * Clase de acceso a datos para operaciones con carritos de compras.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class CarritosComprasDAO {

    private final String COLECCION_CARRITOS = "CarritosCompras";
    private final String COLECCION_CLIENTES = "Clientes";
    private final String COLECCION_PAQUETERIAS = "Paqueterias";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_PRODUCTOS_CARRITO = "productosCarrito";
    private final String CAMPO_ID_CLIENTE = "idCliente";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO con el ID recibido para consulta de %s es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' de %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_CARRITOS_NULA = "La lista de nuevos carritos es nula.";
    private final String MENSAJE_LISTA_NUEVOS_CARRITOS_VACIA = "La lista de nuevos carritos está vacía.";
    
    private final String MENSAJE_CANTIDAD_PRODUCTO_CARRITO_NEGATIVA = "La cantidad del producto en carrito con id %s debe ser positiva";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_CARRITO_NULA = "La lista de nuevos productos en carrito es nula.";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_CARRITO_VACIA = "La lista de nuevos productos en carrito está vacía.";
    
    private final String NOMBRE_ENTIDAD_CARRITO = "carrito de compras";
    private final String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private final String NOMBRE_ENTIDAD_PAQUETERIA = "paqueteria";
    private final String NOMBRE_ENTIDAD_PRODUCTO_CARRITO = "producto en carrito";

    /**
     * Método que permite determinar si exite un registro de carrito de compras 
     * almacenado a partir de su ID.
     * @param idCarritoComprasDTODatos Objeto IdCarritoComprasDTODatos que contiene
     * el ID de carrito de compras a buscar.
     * @return Valor booleano, true si el registro de carrito existe, false en caso contrario.
     * @throws ParametroNuloException Se lanza si se determina que uno de los
     * parámetros recibidos es nulo.
     * @throws FormatoIdInvalidoException Se lanza si se determia que
     * el formato de ID recibido es inválido.
     */
    public boolean existePorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos) 
        throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        if (idCarritoComprasDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_CARRITO));
        }
        
        if(idCarritoComprasDTODatos.getIdCarritoCompras() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CARRITO));
        }
        
        String idCarrito = (String) idCarritoComprasDTODatos.getIdCarritoCompras().getId();
        
        try {
            
            ObjectId idCarritoObjectId = new ObjectId(idCarrito);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_CARRITOS).countDocuments(new Document(CAMPO_ID, idCarritoObjectId)) > 0;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CARRITO));
        }
    }

    /**
     * Método que permite obtener un objeto CarritoComprasDTODatos que representa la
     * información de un registro de carrito de compras almacenado.
     * @param idCarritoComprasDTODatos Objeto IdCarritoComprasDTODatos que contiene
     * el ID del carrito de compras a recuperar.
     * @return Objeto CarritoComprasDTODatos que representa la
     * información de un registro de carrito de compras almacenado.
     * @throws FormatoIdInvalidoException Se lanza si se comprueba que el 
     * ID de carrito a recuperar no tiene un formato válido.
     * @throws RegistroInexistenteException Se lanza si se comprueba que no existe
     * un carrito de compras registrado con el ID del parámetro.
     */
    public CarritoComprasDTODatos recuperarPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException,
            ParametroNuloException {
        
        if (idCarritoComprasDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_CARRITO));
        }
        
        if(idCarritoComprasDTODatos.getIdCarritoCompras() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CARRITO));
        }
        
        String idCarrito = (String) idCarritoComprasDTODatos.getIdCarritoCompras().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idCarrito);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<CarritoCompras> coleccionCarritosCompra = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);
            
            CarritoCompras carrito = coleccionCarritosCompra.find(new Document(CAMPO_ID, objectId)).first();
            
            if (carrito == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CARRITO, idCarrito)
                );
            }
            
            return convertirCarritoADTO(carrito);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CARRITO));
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
     * @throws FormatoIdInvalidoException Se lanza si se comprueba que un ID
     * no tiene un formato válido.
     * @throws RegistroInexistenteException Se lanza si no se encuentra un registro
     * de carrito de compras con el ID del parámetro para actualizar.
     * @throws ValorParametroInvalidoException Se lanza cuando se determina que la cantidad
     * de uno de los prodctos en carrito a actualizar es negativa.
     * @throws ParametroNuloException Se lanza cuando se comprueba que uno de los parámetros
     * necesarios para realizar la actualización es nulo.
     */
    public CarritoComprasDTODatos actualizar(ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos)
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ValorParametroInvalidoException,
               ParametroNuloException {

        if (actualizacionCarritoComprasDTODatos.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_CARRITO));
        }
        
        if(actualizacionCarritoComprasDTODatos.getId().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_CARRITO));
        }

        String idCarritoString = null;
        
        try {
            idCarritoString = (String)actualizacionCarritoComprasDTODatos.getId().getId();
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(MENSAJE_ID_FORMATO_INVALIDO);
        }
        
        
        ObjectId idCarrito = new ObjectId(idCarritoString);

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> carritosCollection = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        verificarExistenciaCarrito(idCarrito, carritosCollection);

        CarritoCompras carrito = carritosCollection.find(Filters.eq(CAMPO_ID, idCarrito)).first();
        if (carrito == null) {
            throw new RegistroInexistenteException(String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CARRITO, idCarritoString));
        }

        if (actualizacionCarritoComprasDTODatos.tieneEsVigente()) {
            carrito.setEsVigente(actualizacionCarritoComprasDTODatos.getEsVigente());
        }

        if (actualizacionCarritoComprasDTODatos.tieneIdPaqueteria()) {
            ObjectId idPaqueteria = obtenerIdPaqueteriaRegistrada(actualizacionCarritoComprasDTODatos.getIdPaqueteria());
            carrito.setIdPaqueteria(idPaqueteria);
        }

        carritosCollection.replaceOne(Filters.eq(CAMPO_ID, idCarrito), carrito);

        return convertirCarritoADTO(carrito);
    }

    /**
     * Método que permite agregar un nuevo carrito de compras a la colección
     * de carritos de compras.
     * @param nuevoCarrito Objeto CarritoComprasDTODatos que contiene los datos
     * necesarios para registrar un nuevo carrito de compras.
     * @throws FormatoIdInvalidoException Se lanza si se comprueba que 
     * un ID tiene formato inválido.
     * @throws RegistroInexistenteException Si lanza si se comprueba que no existen
     * los registros en las colecciones con las que se relaciona el nuevo
     * carrito de compras.
     * @throws ValorParametroInvalidoException Se lanza si se determina que la cantidad
     * de uno de los productos en carrito a agregar es negativa.
     * @throws ParametroNuloException Se lanza si se determina que uno o
     * más datos necesarios para el registro del nuevo carrito de compras 
     * son nulos.
     */
    public void agregar(CarritoComprasDTODatos nuevoCarrito) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ValorParametroInvalidoException,
               ParametroNuloException {
        
        if (nuevoCarrito == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_CARRITO));
        }
        
        ObjectId idClienteRegistrado = obtenerIdClienteRegistrado(nuevoCarrito.getIdCliente());
        List<ProductoCarrito> listaProductosCarrito = convertirProductosCarritoDTO(nuevoCarrito.getProductosCarrito());

        CarritoCompras carrito = new CarritoCompras(
            nuevoCarrito.getEsVigente(),
            idClienteRegistrado,
            listaProductosCarrito
        );
        
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
    * @throws FormatoIdInvalidoException Se lanza si se comprueba que un ID tiene formato inválido.
    * @throws RegistroInexistenteException Se lanza si se comprueba que no existen los registros en las
    * colecciones con las que se relaciona alguno de los nuevos carritos.
    * @throws ParametroNuloException Se lanza si se comprueba que uno de los IDs recibidos de 
    * los carritos de compra a actualizar es nulo.
     * @throws ValorParametroInvalidoException Se lanza cuando se determina que la cantidad
     * de uno de los productos en carrito a agregar es negativa.
    */
   public void agregar(Collection<CarritoComprasDTODatos> nuevosCarritos) 
            throws FormatoIdInvalidoException, 
                   RegistroInexistenteException,
                   ParametroNuloException,
                   ValorParametroInvalidoException {

        if (nuevosCarritos == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_CARRITOS_NULA);
        }
        
        if (nuevosCarritos.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_CARRITOS_VACIA);
        }

        List<CarritoCompras> carritos = new LinkedList<>();

        for (CarritoComprasDTODatos nuevoCarrito : nuevosCarritos) {
            
            if (nuevoCarrito == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_CARRITO));
            }
            
            ObjectId idClienteRegistrado = obtenerIdClienteRegistrado(nuevoCarrito.getIdCliente());
            List<ProductoCarrito> listaProductosCarrito = convertirProductosCarritoDTO(nuevoCarrito.getProductosCarrito());
            
            CarritoCompras carrito = new CarritoCompras(
                nuevoCarrito.getEsVigente(),
                idClienteRegistrado,
                listaProductosCarrito
            );

            carritos.add(carrito);
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> coleccionCarritosCompra = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        coleccionCarritosCompra.insertMany(carritos);
    }
   
   /**
    * Método que permite recuperar todos los carritos de compras registrados en el sistema.
    * @return Objeto {@literal List<CarritoComprasDTODatos>} que representa la lsita de los objetos 
    * CarritoComprasDTODatos de cada uno de los carritos registrados.
    */
    public List<CarritoComprasDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> coleccionCarritosCompra = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        return coleccionCarritosCompra.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirCarritoADTO)
            .collect(Collectors.toList());
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
    private void verificarExistenciaCarrito(ObjectId idCarrito, MongoCollection<CarritoCompras> coleccionCarritosCompra) 
            throws RegistroInexistenteException {
        
        if (coleccionCarritosCompra.countDocuments(eq(CAMPO_ID, idCarrito)) == 0) {
            
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
    private List<ProductoCarrito> convertirProductosCarritoDTO(List<ProductoCarritoDTODatos> productosCarritoDTO)
            throws ValorParametroInvalidoException {
        
        List<ProductoCarrito> productosCarrito = new ArrayList<>();

        for (ProductoCarritoDTODatos productoDTO : productosCarritoDTO) {

            if (productoDTO.getCantidad() < 0) {
                throw new ValorParametroInvalidoException(MENSAJE_CANTIDAD_PRODUCTO_CARRITO_NEGATIVA);
            }

            ObjectId idProducto = new ObjectId((String) productoDTO.getIdProducto().getId());

            
            ProductoCarrito producto = new ProductoCarrito(idProducto, productoDTO.getCantidad());


            productosCarrito.add(producto);
        }

        return productosCarrito;
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
                new IdEntidadGenericoDatos(productoCarrito.getId().toHexString()),
                new IdEntidadGenericoDatos(productoCarrito.getIdProducto().toHexString())
                
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
            carrito.getIdPaqueteria() != null ? new IdEntidadGenericoDatos(carrito.getIdPaqueteria().toHexString()): null,
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
     * @throws FormatoIdInvalidoException Se lanza si se determina que el
     * formato del ID es inválido.
     */
    private ObjectId obtenerIdClienteRegistrado(IdEntidadGenericoDatos idCliente) 
            throws RegistroInexistenteException, 
            FormatoIdInvalidoException,
            ParametroNuloException {
        
        if(idCliente == null || idCliente.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idCliente", NOMBRE_ENTIDAD_CLIENTE));
        }
        
        try {
            ObjectId idClienteRegistrado = new ObjectId((String) idCliente.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            if (baseDatos.getCollection(COLECCION_CLIENTES).countDocuments(new Document(CAMPO_ID, idClienteRegistrado)) == 0) {
                throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, (String) idCliente.getId()));
            }
            
            return idClienteRegistrado;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CLIENTE));
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
     * @throws FormatoIdInvalidoException Se lanza si se determina que el
     * formato del ID es inválido.
     */
    private ObjectId obtenerIdPaqueteriaRegistrada(IdEntidadGenericoDatos idPaqueteria) 
            throws RegistroInexistenteException, 
            FormatoIdInvalidoException,
            ParametroNuloException {
        
        if(idPaqueteria == null || idPaqueteria.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idPaqueteria", NOMBRE_ENTIDAD_PAQUETERIA));
        }

        try {
            
            ObjectId idPaqueteriaRegistrada = new ObjectId((String) idPaqueteria.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_PAQUETERIAS).countDocuments(eq(CAMPO_ID, idPaqueteriaRegistrada)) == 0) {
                
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PAQUETERIA, (String) idPaqueteria.getId()));
            }
            return idPaqueteriaRegistrada;

        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PAQUETERIA));
        }
    }
    
    // Métodos para productos en carrito

    public ProductoCarritoDTODatos recuperarProductoCarritoPorId(IdProductoCarritoDTODatos idProductoCarritoDTODatos) 
            throws RegistroInexistenteException, 
            ParametroNuloException, 
            FormatoIdInvalidoException {
        
        if (idProductoCarritoDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        if(idProductoCarritoDTODatos.getIdProductoCarrito() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        try {
            
            String idProductoCarrito = (String) idProductoCarritoDTODatos.getIdProductoCarrito().getId();
            
            ObjectId objectIdProductoCarrito = new ObjectId(idProductoCarrito);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            MongoCollection<ProductoCarrito> coleccionCarritosCompras = baseDatos.getCollection(COLECCION_CARRITOS, ProductoCarrito.class);
            
            List<Bson> tuberia = Arrays.asList(
                Aggregates.match(Filters.elemMatch(CAMPO_PRODUCTOS_CARRITO, 
                        Filters.eq(CAMPO_ID, objectIdProductoCarrito))),
                Aggregates.unwind("$" + CAMPO_PRODUCTOS_CARRITO),
                Aggregates.match(Filters.eq(CAMPO_PRODUCTOS_CARRITO + "." + CAMPO_ID, objectIdProductoCarrito)),
                Aggregates.replaceRoot(new Document("$mergeObjects", "$" + CAMPO_PRODUCTOS_CARRITO))
            );

            ProductoCarrito productoCarrito = coleccionCarritosCompras.aggregate(tuberia).first();

            if (productoCarrito == null) {
                throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO_CARRITO, idProductoCarrito)
                );
            }

            ProductoCarritoDTODatos productoCarritoDTODatos = new ProductoCarritoDTODatos(
                    new IdEntidadGenericoDatos(productoCarrito.getId()), 
                    productoCarrito.getCantidad(), 
                    new IdEntidadGenericoDatos(productoCarrito.getIdProducto()));
            
            return productoCarritoDTODatos;
    
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
    }


    public boolean existeProductoCarritoPorId(IdProductoCarritoDTODatos idProductoCarritoDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        if (idProductoCarritoDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        if(idProductoCarritoDTODatos.getIdProductoCarrito() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        String idProductoCarrito = (String) idProductoCarritoDTODatos.getIdProductoCarrito().getId();
        
        try{
            
            ObjectId objectIdProductoCarrito = new ObjectId(idProductoCarrito);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
            MongoCollection<CarritoCompras> coleccionCarritosCompras = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

            return coleccionCarritosCompras.countDocuments(
                Filters.elemMatch(CAMPO_PRODUCTOS_CARRITO, Filters.eq(CAMPO_ID, objectIdProductoCarrito))
            ) > 0;
            
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
    }

    
    public ProductoCarritoDTODatos actualizarProductoCarrito(ActualizacionProductoCarritoDTODatos actualizacionProductoCarritoDTODatos) 
            throws RegistroInexistenteException, 
            ParametroNuloException, 
            FormatoIdInvalidoException, 
            ValorParametroInvalidoException {
        
        if (actualizacionProductoCarritoDTODatos.getId() == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(actualizacionProductoCarritoDTODatos.getId().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }

        String idProductoCarritoString = null;
        
        try {
            idProductoCarritoString = (String)actualizacionProductoCarritoDTODatos.getId().getId();
        } catch (IllegalArgumentException ex) {
            
            throw new FormatoIdInvalidoException(MENSAJE_ID_FORMATO_INVALIDO);
            
        }
        
        ObjectId idProductoCarritoObjectId = new ObjectId(idProductoCarritoString);
        
        if(actualizacionProductoCarritoDTODatos.tieneCantidad()){
            
            if(actualizacionProductoCarritoDTODatos.getCantidad() < 0){ 
                throw new ValorParametroInvalidoException(MENSAJE_CANTIDAD_PRODUCTO_CARRITO_NEGATIVA);
            }     
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<ProductoCarrito> coleccionCarritosCompras = baseDatos.getCollection(COLECCION_CARRITOS, ProductoCarrito.class);

        Bson filtro = Filters.elemMatch(CAMPO_PRODUCTOS_CARRITO, Filters.eq(CAMPO_ID, idProductoCarritoObjectId));
        Bson actualizacion = Updates.set(CAMPO_PRODUCTOS_CARRITO + ".$.cantidad", actualizacionProductoCarritoDTODatos.getCantidad());
        
        ProductoCarrito productoCarrito = coleccionCarritosCompras.findOneAndUpdate(
            filtro, 
            actualizacion, 
            new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        );
        
        if (productoCarrito == null) {
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO_CARRITO, idProductoCarritoString)
            );
        }
        
        ProductoCarritoDTODatos productoCarritoDTODatos = new ProductoCarritoDTODatos(
                new IdEntidadGenericoDatos(productoCarrito.getId().toHexString()),
                productoCarrito.getCantidad(),
                new IdEntidadGenericoDatos(productoCarrito.getIdProducto().toHexString()));
        
        return productoCarritoDTODatos;
    }
    
    
    public void removerProductoCarritoPorId(IdProductoCarritoDTODatos idProdutoCarritoDTODatos) 
            throws ParametroNuloException, 
            FormatoIdInvalidoException {
        
        if (idProdutoCarritoDTODatos.getIdProductoCarrito()== null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idProdutoCarritoDTODatos.getIdProductoCarrito().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }

        String idProductoCarritoString = null;
        
        try {
            
            idProductoCarritoString = (String)idProdutoCarritoDTODatos.getIdProductoCarrito().getId();
            
            ObjectId idProductoCarritoObjectId = new ObjectId(idProductoCarritoString);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
            MongoCollection<CarritoCompras> coleccionCarritosCompras = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);
            
            coleccionCarritosCompras.updateMany(
                Filters.elemMatch(CAMPO_PRODUCTOS_CARRITO, Filters.eq(CAMPO_ID, idProductoCarritoObjectId)),
                Updates.pull(CAMPO_PRODUCTOS_CARRITO, Filters.eq(CAMPO_ID, idProductoCarritoObjectId))
            );
            
            
        } catch (IllegalArgumentException ex) {
            
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
            
        }

    }


    public void agregarProductoCarrito(ProductoCarritoDTODatos nuevoProductoCarrito) 
            throws ParametroNuloException, 
            RegistroInexistenteException,
            FormatoIdInvalidoException{
        
        if (nuevoProductoCarrito == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        if (nuevoProductoCarrito.getIdCliente() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idCliente", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        if (nuevoProductoCarrito.getIdProducto() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idProducto", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
        }
        
        ObjectId idCliente = new ObjectId((String)nuevoProductoCarrito.getIdCliente().getId());
        ObjectId idProducto = new ObjectId((String)nuevoProductoCarrito.getIdProducto().getId());

        ProductoCarrito productoCarrito = new ProductoCarrito(
            idProducto,
            nuevoProductoCarrito.getCantidad()
        );
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();

        MongoCollection<CarritoCompras> coleccionCarritosCompras = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        coleccionCarritosCompras.updateOne(
            Filters.eq(CAMPO_ID_CLIENTE, idCliente),
            Updates.push(CAMPO_PRODUCTOS_CARRITO, productoCarrito)
        );
        
        LOG.log(Level.SEVERE,"Campos modificados");
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        LOG.log(Level.SEVERE,String.valueOf(idCliente.toHexString()));
        
        
    }
    private static final Logger LOG = Logger.getLogger(CarritosComprasDAO.class.getName());
    
    public void agregarProductosCarrito(Collection<ProductoCarritoDTODatos> nuevosProductosCarrito) 
            throws ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException{
        
        if (nuevosProductosCarrito == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_CARRITO_NULA);
        }
        
        if (nuevosProductosCarrito.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_CARRITO_VACIA);
        }

        HashMap<ProductoCarrito, ObjectId> mapaProductosCarritoIdCliente = new HashMap<>();
        
        for (ProductoCarritoDTODatos nuevoProductoCarrito : nuevosProductosCarrito) {            

            if (nuevoProductoCarrito == null){
                throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
            }

            if (nuevoProductoCarrito.getIdCliente() == null) {
                throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idCliente", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
            }

            if (nuevoProductoCarrito.getIdProducto() == null) {
                throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idProducto", NOMBRE_ENTIDAD_PRODUCTO_CARRITO));
            }

            ObjectId idCliente = new ObjectId((String)nuevoProductoCarrito.getIdCliente().getId());

            ProductoCarrito productoCarrito = new ProductoCarrito(
                new ObjectId((String)nuevoProductoCarrito.getIdProducto().getId()),
                nuevoProductoCarrito.getCantidad()
            );

            mapaProductosCarritoIdCliente.put(productoCarrito, idCliente);
        
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<CarritoCompras> coleccionCarritosCompra = baseDatos.getCollection(COLECCION_CARRITOS, CarritoCompras.class);
            
        for(Map.Entry<ProductoCarrito, ObjectId> entrada: mapaProductosCarritoIdCliente.entrySet()){
            
            coleccionCarritosCompra.updateOne(
            Filters.eq(CAMPO_ID_CLIENTE, entrada.getValue()),
            Updates.push(CAMPO_PRODUCTOS_CARRITO, entrada.getValue())
        );
            
        }
        
    }

    public List<ProductoCarritoDTODatos> recuperarTodosProductosCarrito() {

        MongoCollection<CarritoCompras> coleccionCarritos = 
                ManejadorConexiones.obtenerBaseDatos().getCollection(COLECCION_CARRITOS, CarritoCompras.class);

        List<Bson> tuberia = Arrays.asList(
            Aggregates.unwind("$" + CAMPO_PRODUCTOS_CARRITO),
            Aggregates.replaceRoot("$" + CAMPO_PRODUCTOS_CARRITO)
        );

        // Aquí usamos aggregate con el pipeline y mapeamos el resultado directamente a ProductoCarritoDTONegocios.
        List<ProductoCarritoDTODatos> productosCarrito = coleccionCarritos.aggregate(tuberia, ProductoCarritoDTODatos.class).into(new ArrayList<>());

        return productosCarrito;
    }
    
    private ObjectId obtenerIdCarritoComprasRegistrado(IdEntidadGenericoDatos idCarritoCompras) 
            throws RegistroInexistenteException, 
            FormatoIdInvalidoException,
            ParametroNuloException {
        
        if(idCarritoCompras == null || idCarritoCompras.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idCarritoCompras", NOMBRE_ENTIDAD_CARRITO));
        }
        
        try {
            
            ObjectId idCarritoComprasRegistrado = new ObjectId((String) idCarritoCompras.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_CARRITOS).countDocuments(new Document(CAMPO_ID, idCarritoComprasRegistrado)) == 0) {
                throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CARRITO, (String) idCarritoCompras.getId()));
            }
            
            return idCarritoComprasRegistrado;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CARRITO));
        }
    }
    

    
}