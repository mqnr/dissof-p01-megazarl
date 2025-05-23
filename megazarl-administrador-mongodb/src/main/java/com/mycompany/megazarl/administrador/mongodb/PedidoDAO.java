//package com.mycompany.megazarl.administrador.mongodb;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Pedido;
//import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Cliente;
//import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoPedido;
//import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
//import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
//import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
//import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
//import edu.student.itson.dissof.dto.datos.PedidoDTODatos;
//
//import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
//import java.util.ArrayList;
//import java.util.List;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//public class PedidoDAO {
//
//    private final String COLECCION_PEDIDOS = "Pedidos";
//    private final String COLECCION_CLIENTES = "Clientes";
//    private final String CAMPO_ID = "_id";
//    
//    // Mensajes de excepción
//    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
//    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
//    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
//    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
//    
//    private final String NOMBRE_ENTIDAD_PEDIDO = "pedido";
//    private final String NOMBRE_ENTIDAD_CLIENTE = "cliente";
//    private final String NOMBRE_ENTIDAD_PRODUCTO = "producto";
//
//    public boolean existePorId(IdEntidadGenericoDatos idPedidoDTO) 
//        throws FormatoInvalidoIdConversionException {
//        
//        if (idPedidoDTO == null) {
//            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
//        }
//        
//        String idPedido = (String) idPedidoDTO.getId();
//        
//        try {
//            ObjectId objectId = new ObjectId(idPedido);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Document> pedidosCollection = baseDatos.getCollection(COLECCION_PEDIDOS);
//            return pedidosCollection.countDocuments(new Document(CAMPO_ID, objectId)) > 0;
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
//        }
//    }
//
//    public PedidoDTODatos recuperarPorId(IdEntidadGenericoDatos idPedidoDTO) 
//        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
//        
//        if (idPedidoDTO == null) {
//            throw new IllegalArgumentException(MENSAJE_DTO_ID_NULO);
//        }
//        
//        String idPedido = (String) idPedidoDTO.getId();
//        
//        try {
//            ObjectId objectId = new ObjectId(idPedido);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            MongoCollection<Pedido> pedidosCollection = baseDatos.getCollection(
//                COLECCION_PEDIDOS, 
//                Pedido.class
//            );
//            
//            Pedido pedido = pedidosCollection.find(new Document(CAMPO_ID, objectId)).first();
//            
//            if (pedido == null) {
//                throw new RegistroInexistenteException(
//                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PEDIDO, idPedido)
//                );
//            }
//            
//            return convertirPedidoADTO(pedido);
//            
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID inválido.");
//        }
//    }
//
//    public void agregar(PedidoDTODatos nuevoPedido) 
//        throws AgregarInformacionNulaException, 
//               FormatoInvalidoIdConversionException, 
//               RegistroInexistenteException {
//        
//        if (nuevoPedido == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PEDIDO));
//        }
//
//        validarCamposObligatorios(nuevoPedido);
//        Cliente cliente = obtenerCliente(nuevoPedido.getIdCliente());
//        List<ProductoPedido> productos = convertirProductosPedido(nuevoPedido.getProductosPedido());
//
//        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//        MongoCollection<Pedido> pedidosCollection = baseDatos.getCollection(
//            COLECCION_PEDIDOS, 
//            Pedido.class
//        );
//
//        Pedido pedido = new Pedido(cliente, productos);
//        pedidosCollection.insertOne(pedido);
//    }
//
//    public void agregar(List<PedidoDTODatos> nuevosPedidos) 
//        throws AgregarInformacionNulaException, 
//               FormatoInvalidoIdConversionException, 
//               RegistroInexistenteException {
//        
//        if (nuevosPedidos == null || nuevosPedidos.isEmpty()) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PEDIDO));
//        }
//
//        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//        MongoCollection<Pedido> pedidosCollection = baseDatos.getCollection(
//            COLECCION_PEDIDOS, 
//            Pedido.class
//        );
//
//        List<Pedido> pedidosParaInsertar = new ArrayList<>();
//
//        for (PedidoDTODatos pedidoDTO : nuevosPedidos) {
//            if (pedidoDTO == null) {
//                throw new AgregarInformacionNulaException(
//                    String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PEDIDO));
//            }
//
//            validarCamposObligatorios(pedidoDTO);
//            Cliente cliente = obtenerCliente(pedidoDTO.getCliente());
//            List<ProductoPedido> productos = convertirProductosPedido(pedidoDTO.getProductosPedido());
//            
//            Pedido pedido = new Pedido(cliente, productos);
//            pedidosParaInsertar.add(pedido);
//        }
//
//        pedidosCollection.insertMany(pedidosParaInsertar);
//    }
//
//    private void validarCamposObligatorios(PedidoDTODatos pedidoDTO) 
//        throws AgregarInformacionNulaException {
//        
//        if (pedidoDTO.getCliente() == null || pedidoDTO.getCliente().getId() == null) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_NULO, "cliente", NOMBRE_ENTIDAD_PEDIDO));
//        }
//        
//        if (pedidoDTO.getProductosPedido() == null || pedidoDTO.getProductosPedido().isEmpty()) {
//            throw new AgregarInformacionNulaException(
//                String.format(MENSAJE_PARAMETRO_NULO, "productos", NOMBRE_ENTIDAD_PEDIDO));
//        }
//        
//        validarCamposProductos(pedidoDTO.getProductosPedido());
//    }
//
//    private void validarCamposProductos(List<ProductoPedidoDTO> productosDTO) 
//        throws AgregarInformacionNulaException {
//        
//        for (ProductoPedidoDTO productoDTO : productosDTO) {
//            if (productoDTO.getProducto() == null || productoDTO.getProducto().getId() == null) {
//                throw new AgregarInformacionNulaException(
//                    "Producto en pedido no tiene ID válido");
//            }
//            
//            if (productoDTO.getCantidad() == null || productoDTO.getCantidad() <= 0) {
//                throw new AgregarInformacionNulaException("Cantidad inválida en producto pedido");
//            }
//        }
//    }
//
//    private Cliente obtenerCliente(IdEntidadGenericoDatos idClienteDTO) 
//        throws FormatoInvalidoIdConversionException, RegistroInexistenteException {
//        
//        String idCliente = (String) idClienteDTO.getId();
//        
//        try {
//            ObjectId objectId = new ObjectId(idCliente);
//            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
//            
//            // Verificar existencia del cliente
//            MongoCollection<Document> clientesCollection = baseDatos.getCollection(COLECCION_CLIENTES);
//            if (clientesCollection.countDocuments(new Document(CAMPO_ID, objectId)) == 0) {
//                throw new RegistroInexistenteException(
//                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_CLIENTE, idCliente)
//                );
//            }
//            
//            // Crear cliente solo con ID
//            Cliente cliente = new Cliente();
//            cliente.setId(objectId);
//            return cliente;
//            
//        } catch (IllegalArgumentException e) {
//            throw new FormatoInvalidoIdConversionException("Formato de ID de cliente inválido");
//        }
//    }
//
//    private List<ProductoPedido> convertirProductosPedido(List<ProductoPedidoDTO> productosDTO) {
//        List<ProductoPedido> productos = new ArrayList<>();
//        
//        for (ProductoPedidoDTO productoDTO : productosDTO) {
//            ProductoPedido productoPedido = new ProductoPedido();
//            productoPedido.setCantidad(productoDTO.getCantidad());
//            
//            // Crear producto solo con ID
//            Producto producto = new Producto();
//            producto.setId(new ObjectId((String) productoDTO.getProducto().getId()));
//            productoPedido.setProducto(producto);
//            
//            productos.add(productoPedido);
//        }
//        
//        return productos;
//    }
//    
//    private PedidoDTODatos convertirPedidoADTO(Pedido pedido) {
//        IdEntidadGenericoDatos idPedido = new IdEntidadGenericoDatos(pedido.getId().toHexString());
//        IdEntidadGenericoDatos idCliente = new IdEntidadGenericoDatos(pedido.getCliente().getId().toHexString());
//        
//        List<ProductoPedidoDTO> productosDTO = new ArrayList<>();
//        for (ProductoPedido producto : pedido.getProductosPedido()) {
//            IdEntidadGenericoDatos idProducto = new IdEntidadGenericoDatos(producto.getProducto().getId().toHexString());
//            ProductoPedidoDTO dto = new ProductoPedidoDTO();
//            dto.setProducto(new IdEntidadGenericoDatos(idProducto));
//            dto.setCantidad(producto.getCantidad());
//            productosDTO.add(dto);
//        }
//        
//        return new PedidoDTODatos(idPedido, idCliente, productosDTO);
//    }
//}