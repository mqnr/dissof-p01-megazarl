package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Pedido;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoPedido;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.IdPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.PedidoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoPedidoDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase de acceso a datos para operaciones con pedidos.
 */
public class PedidosDAO {

    private final String COLECCION_PEDIDOS = "Pedidos";
    private final String COLECCION_CLIENTES = "Clientes";
    private final String COLECCION_PAQUETERIAS = "Paqueterias";
    private final String COLECCION_PRODUCTOS = "Productos";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_PRODUCTOS_PEDIDO = "productosPedido";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO con el ID de %s recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_PEDIDOS_NULA = "La lista de nuevos pedidos es nula.";
    private final String MENSAJE_LISTA_NUEVOS_PEDIDOS_VACIA = "La lista de nuevos pedidos está vacía.";
    private final String MENSAJE_CANTIDAD_PRODUCTO_PEDIDO_INVALIDA = "La cantidad del producto en pedido co el ID %s es inválida";
    
    private final String MENSAJE_CANTIDAD_PRODUCTO_PEDIDO_NEGATIVA = "La cantidad del producto en pedido con id %s debe ser positiva";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_PEDIDO_NULA = "La lista de nuevos productos en pedido es nula.";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_PEDIDO_VACIA = "La lista de nuevos productos en pedido está vacía.";
    
    private final String NOMBRE_ENTIDAD_PEDIDO = "pedido";
    private final String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private final String NOMBRE_ENTIDAD_PAQUETERIA = "paqueteria";
    private final String NOMBRE_ENTIDAD_PRODUCTO_PEDIDO = "producto en pedido";
    private final String NOMBRE_ENTIDAD_PRODUCTO = "producto";

    public boolean existePorId(IdPedidoDTODatos idPedidoDTODatos) 
        throws ParametroNuloException, FormatoIdInvalidoException {
        
        if (idPedidoDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idPedidoDTODatos.getIdPedido() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PEDIDO));
        }
        
        String idPedido = (String) idPedidoDTODatos.getIdPedido().getId();
        
        try {
            ObjectId objectId = new ObjectId(idPedido);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_PEDIDOS).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PEDIDO));
        }
    }

    public PedidoDTODatos recuperarPorId(IdPedidoDTODatos idPedidoDTODatos) 
        throws FormatoIdInvalidoException, RegistroInexistenteException, ParametroNuloException {
        
        if (idPedidoDTODatos == null){
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idPedidoDTODatos.getIdPedido() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PEDIDO));
        }
        
        String idPedido = (String) idPedidoDTODatos.getIdPedido().getId();
        
        try {
            ObjectId objectId = new ObjectId(idPedido);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Pedido> pedidosCollection = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);
            
            Pedido pedido = pedidosCollection.find(new Document(CAMPO_ID, objectId)).first();
            
            if (pedido == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PEDIDO, idPedido)
                );
            }
            
            return convertirPedidoADTO(pedido);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PEDIDO));
        }
    }

    public void agregar(PedidoDTODatos nuevoPedido) 
        throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ValorParametroInvalidoException,
            ParametroNuloException{
        
        validarCamposObligatorios(nuevoPedido);
        
        ObjectId idClienteRegistrado = obtenerIdClienteRegistrado(nuevoPedido.getIdCliente());
        ObjectId idPaqueteriaRegistrada = obtenerIdPaqueteriaRegistrada(nuevoPedido.getIdPaqueteria());

        Pedido pedido = new Pedido(
            nuevoPedido.getEstado(),
            idClienteRegistrado,
            idPaqueteriaRegistrada,
            convertirProductosPedidoDTO(nuevoPedido.getProductosPedido())
        );

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Pedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);
        
        coleccionPedidos.insertOne(pedido);
    }
    
    public void agregar(Collection<PedidoDTODatos> nuevosPedidos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException,
            ValorParametroInvalidoException,
            ParametroNuloException {
        
        if (nuevosPedidos == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PEDIDOS_NULA);
        }

        if (nuevosPedidos.isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_LISTA_NUEVOS_PEDIDOS_VACIA);
        }

        List<Pedido> pedidos = new LinkedList<>();

        for (PedidoDTODatos nuevoPedido : nuevosPedidos) {
            
            validarCamposObligatorios(nuevoPedido);
            
            ObjectId idClienteRegistrado = obtenerIdClienteRegistrado(nuevoPedido.getIdCliente());
            ObjectId idPaqueteriaRegistrada = obtenerIdPaqueteriaRegistrada(nuevoPedido.getIdPaqueteria());

            pedidos.add(new Pedido(
                nuevoPedido.getEstado(),
                idClienteRegistrado,
                idPaqueteriaRegistrada,
                convertirProductosPedidoDTO(nuevoPedido.getProductosPedido())
            ));
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Pedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);
        
        coleccionPedidos.insertMany(pedidos);
    }
   
    public List<PedidoDTODatos> recuperarTodos() {
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Pedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);

        return coleccionPedidos.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirPedidoADTO)
            .collect(Collectors.toList());
    }

    private void validarCamposObligatorios(PedidoDTODatos pedidoDTODatos) 
        throws FormatoIdInvalidoException, 
               RegistroInexistenteException,
               ValorParametroInvalidoException,
               ParametroNuloException{

        if (pedidoDTODatos == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PEDIDO));
        }

        if (pedidoDTODatos.getIdCliente() == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "id cliente", NOMBRE_ENTIDAD_PEDIDO));
        }

        if (pedidoDTODatos.getEstado() == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "estado", NOMBRE_ENTIDAD_PEDIDO));
        }

        if (pedidoDTODatos.getProductosPedido() == null || pedidoDTODatos.getProductosPedido().isEmpty()) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "productos pedido", NOMBRE_ENTIDAD_PEDIDO));
        }

        // Validación de productos de pedido
        for (ProductoPedidoDTODatos producto : pedidoDTODatos.getProductosPedido()) {

            if (producto.getIdProducto() == null) {
                throw new ParametroNuloException(
                    String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
            }
            
            if (producto.getIdProducto().getId() == null) {
                throw new ParametroNuloException(
                    String.format(MENSAJE_PARAMETRO_NULO, "id producto", NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
            }

            // Validar cantidad
            if (producto.getCantidad() == null || producto.getCantidad() <= 0) {
                throw new ParametroNuloException(
                        String.format(MENSAJE_PARAMETRO_NULO,"cantidad" ,NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
            }
            
            if (producto.getCantidad() <= 0) {
                throw new ValorParametroInvalidoException(
                        String.format(MENSAJE_CANTIDAD_PRODUCTO_PEDIDO_INVALIDA, producto.getIdProducto().getId()));
            }

            String idProducto = (String) producto.getIdProducto().getId();
            
            try {
                ObjectId productId = new ObjectId(idProducto);
                MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
                long numeroProductos = baseDatos.getCollection(COLECCION_PRODUCTOS).countDocuments(new Document(CAMPO_ID, productId));

                if (numeroProductos == 0) {
                    throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO, idProducto));
                }

            } catch (IllegalArgumentException e) {
                throw new FormatoIdInvalidoException(
                    String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO));
            }
        }
    }

    private List<ProductoPedido> convertirProductosPedidoDTO(List<ProductoPedidoDTODatos> productosPedidoDTO) {
        return productosPedidoDTO.stream()
            .map(productoPedido -> new ProductoPedido(
                new ObjectId((String) productoPedido.getIdProducto().getId()),
                productoPedido.getCantidad()
            ))
            .collect(Collectors.toList());
    }

    private PedidoDTODatos convertirPedidoADTO(Pedido pedido) {
        return new PedidoDTODatos(
            new IdEntidadGenericoDatos(pedido.getId().toHexString()),
            pedido.getEstado(),
            new IdEntidadGenericoDatos(pedido.getIdCliente().toHexString()),
            new IdEntidadGenericoDatos(pedido.getIdPaqueteria().toHexString()),
            convertirProductosEntidad(pedido.getProductosPedido())
        );
    }
    
    private List<ProductoPedidoDTODatos> convertirProductosEntidad(List<ProductoPedido> productosPedido) {
        return productosPedido.stream()
            .map(productoPedido -> new ProductoPedidoDTODatos(
                new IdEntidadGenericoDatos(productoPedido.getId().toHexString()),
                new IdEntidadGenericoDatos(productoPedido.getIdProducto().toHexString()),
                productoPedido.getCantidad()
            ))
            .collect(Collectors.toList());
    }

    private ObjectId obtenerIdClienteRegistrado(IdEntidadGenericoDatos idCliente) 
        throws RegistroInexistenteException, FormatoIdInvalidoException {
        
        try {
            ObjectId idClienteRegistrado = new ObjectId((String) idCliente.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            if (baseDatos.getCollection(COLECCION_CLIENTES).countDocuments(new Document(CAMPO_ID, idCliente)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, (String) idCliente.getId()));
            }
            
            return idClienteRegistrado;
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_CLIENTE));
        }
    }
    
    private ObjectId obtenerIdPaqueteriaRegistrada(IdEntidadGenericoDatos idPaqueteria) 
        throws RegistroInexistenteException, FormatoIdInvalidoException {
        
        try {
            ObjectId idPaqueteriaRegistrada = new ObjectId((String) idPaqueteria.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            if (baseDatos.getCollection(COLECCION_PAQUETERIAS).countDocuments(new Document(CAMPO_ID, idPaqueteria)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PAQUETERIA, (String) idPaqueteria.getId()));
            }
            
            return idPaqueteriaRegistrada;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PAQUETERIA));
        }
    }
    
    public ProductoPedidoDTODatos recuperarProductoPedidoPorId(IdProductoPedidoDTODatos idProductoPedidoDTODatos) 
            throws 
            RegistroInexistenteException, 
            ParametroNuloException, 
            FormatoIdInvalidoException {
        
        if (idProductoPedidoDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
        if(idProductoPedidoDTODatos.getIdProductoPedido() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
        try {
            
            String idProductoPedido = (String) idProductoPedidoDTODatos.getIdProductoPedido().getId();
            
            ObjectId objectIdProductoPedido = new ObjectId(idProductoPedido);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            MongoCollection<ProductoPedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, ProductoPedido.class);
            
            List<Bson> tuberia = Arrays.asList(
                Aggregates.match(Filters.elemMatch(CAMPO_PRODUCTOS_PEDIDO, 
                        Filters.eq(CAMPO_ID, objectIdProductoPedido))),
                Aggregates.unwind("$" + CAMPO_PRODUCTOS_PEDIDO),
                Aggregates.match(Filters.eq(CAMPO_PRODUCTOS_PEDIDO + "." + CAMPO_ID, objectIdProductoPedido)),
                Aggregates.replaceRoot(new Document("$mergeObjects", "$" + CAMPO_PRODUCTOS_PEDIDO))
            );

            ProductoPedido productoPedido = coleccionPedidos.aggregate(tuberia).first();

            if (productoPedido == null) {
                throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO, idProductoPedido)
                );
            }

            ProductoPedidoDTODatos productoPedidoDTODatos = new ProductoPedidoDTODatos(
                    new IdEntidadGenericoDatos(productoPedido.getId()),
                    new IdEntidadGenericoDatos(productoPedido.getIdProducto()),
                    productoPedido.getCantidad());
            
            return productoPedidoDTODatos;
    
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
    }


    public boolean existeProductoPedidoPorId(IdProductoPedidoDTODatos idProductoPedidoDTODatos)
            throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        if (idProductoPedidoDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
        if(idProductoPedidoDTODatos.getIdProductoPedido() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
        String idProductoPedido = (String) idProductoPedidoDTODatos.getIdProductoPedido().getId();
        
        try{
            
            ObjectId objectIdProductoPedido = new ObjectId(idProductoPedido);
            
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
            MongoCollection<Pedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);

            return coleccionPedidos.countDocuments(
                Filters.elemMatch(CAMPO_PRODUCTOS_PEDIDO, Filters.eq(CAMPO_ID, objectIdProductoPedido))
            ) > 0;
            
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
    }


    public void agregarProductoPedido(ProductoPedidoDTODatos nuevoProductoPedido) 
            throws ParametroNuloException, 
            RegistroInexistenteException,
            FormatoIdInvalidoException{
        
        if (nuevoProductoPedido == null || nuevoProductoPedido.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
        if(nuevoProductoPedido.getId().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
        }
        
        ObjectId idPedidoRegistrado = obtenerIdPedidoRegistrado(nuevoProductoPedido.getIdPedido());

        ProductoPedido productoPedido = new ProductoPedido(
            idPedidoRegistrado,
            nuevoProductoPedido.getCantidad()
        );
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();

        MongoCollection<Pedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);
        
        coleccionPedidos.updateOne(
            Filters.eq(CAMPO_ID, idPedidoRegistrado),
            Updates.push(CAMPO_PRODUCTOS_PEDIDO, productoPedido)
        );
        
    }
    
    public void agregarProductoPedido(Collection<ProductoPedidoDTODatos> nuevosProductosPedido) 
            throws ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException{
        
        if (nuevosProductosPedido == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_PEDIDO_NULA);
        }
        
        if (nuevosProductosPedido.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_PEDIDO_VACIA);
        }

        HashMap<ProductoPedido, ObjectId> mapaProductosPedidoIdPedido = new HashMap<>();
        
        for (ProductoPedidoDTODatos nuevoProductoPedido : nuevosProductosPedido) {            

            if (nuevoProductoPedido == null || nuevoProductoPedido.getId() == null){
                throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
            }

            if(nuevoProductoPedido.getId().getId() == null){
                throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_PEDIDO));
            }
        
            ObjectId idPedidoRegistrado = obtenerIdPedidoRegistrado(nuevoProductoPedido.getIdPedido());

            ProductoPedido productoPedido = new ProductoPedido(
                idPedidoRegistrado,
                nuevoProductoPedido.getCantidad()
            );

            mapaProductosPedidoIdPedido.put(productoPedido, idPedidoRegistrado);
        
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Pedido> coleccionPedidos = baseDatos.getCollection(COLECCION_PEDIDOS, Pedido.class);
            
        for(Map.Entry<ProductoPedido, ObjectId> entrada: mapaProductosPedidoIdPedido.entrySet()){
            
            coleccionPedidos.updateOne(
                Filters.eq(CAMPO_ID, entrada.getValue()),
                Updates.push(CAMPO_PRODUCTOS_PEDIDO, entrada.getKey())
            );
            
        }
        
    }

    public List<ProductoPedidoDTODatos> recuperarTodosProductosPedido() {

        MongoCollection<Pedido> coleccionPedidos = 
                ManejadorConexiones.obtenerBaseDatos().getCollection(COLECCION_PEDIDOS, Pedido.class);

        List<Bson> tuberia = Arrays.asList(
            Aggregates.unwind("$" + CAMPO_PRODUCTOS_PEDIDO),
            Aggregates.replaceRoot("$" + CAMPO_PRODUCTOS_PEDIDO)
        );

        List<ProductoPedidoDTODatos> productosPedido = coleccionPedidos.aggregate(tuberia, ProductoPedidoDTODatos.class).into(new ArrayList<>());

        return productosPedido;
    }
    
    private ObjectId obtenerIdPedidoRegistrado(IdEntidadGenericoDatos idPedido) 
            throws RegistroInexistenteException, 
            FormatoIdInvalidoException,
            ParametroNuloException {
        
        if(idPedido == null || idPedido.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idPedido", NOMBRE_ENTIDAD_PEDIDO));
        }
        
        try {
            
            ObjectId idPedidoRegistrado = new ObjectId((String) idPedido.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_PEDIDOS).countDocuments(new Document(CAMPO_ID, idPedidoRegistrado)) == 0) {
                throw new RegistroInexistenteException(
                        String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PEDIDO, (String) idPedido.getId()));
            }
            
            return idPedidoRegistrado;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PEDIDO));
        }
    }

}