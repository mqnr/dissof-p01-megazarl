package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.ProductoInventario;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ActualizacionProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoInventarioDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de acceso a datos para operaciones con productos de inventario.
 */
public class ProductosInventarioDAO {

    private final String COLECCION_PRODUCTOS_INVENTARIO = "ProductosInventario";
    private final String COLECCION_PRODUCTOS = "Productos";
    private final String COLECCION_SUCURSALES = "Sucursales";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_PRODUCTO = "idProducto";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO con el ID de %s recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_INVENTARIO_NULA = "La lista de nuevos productos en inventario es nula.";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_INVENTARIO_VACIA = "La lista de nuevos productos en inventario está vacía.";

    private final String NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO = "producto en inventario";
    private final String NOMBRE_ENTIDAD_PRODUCTO = "producto";
    private final String NOMBRE_ENTIDAD_SUCURSAL = "sucursal";

    /**
     * Verifica si existe un producto de inventario por su ID.
     */
    public boolean existePorId(IdProductoInventarioDTODatos idProductoInventarioDTODatos) 
            throws ParametroNuloException,
            FormatoIdInvalidoException {
        
        if (idProductoInventarioDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
        
        if(idProductoInventarioDTODatos.getIdProductoInventario() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
        
        String idProducto = (String) idProductoInventarioDTODatos.getIdProductoInventario().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idProducto);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
    }

    /**
     * Recupera un producto de inventario por su ID.
     */
    public ProductoInventarioDTODatos recuperarPorId(IdProductoInventarioDTODatos idProductoInventarioDTODatos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {
        
        if (idProductoInventarioDTODatos == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
        
        if(idProductoInventarioDTODatos.getIdProductoInventario() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
        
        String idProducto = (String) idProductoInventarioDTODatos.getIdProductoInventario().getId();
        
        try {
            ObjectId objectId = new ObjectId(idProducto);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<ProductoInventario> coleccionProductosInventario = baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO, ProductoInventario.class);
            
            ProductoInventario producto = coleccionProductosInventario.find(new Document(CAMPO_ID, objectId)).first();
            
            if (producto == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO, idProducto)
                );
            }
            
            return convertirProductoInventarioADTO(producto);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
    }

    /**
     * Actualiza un producto de inventario, incluyendo el campo 'apartado'.
     */
    public ProductoInventarioDTODatos actualizar(ActualizacionProductoInventarioDTODatos actualizacionProductoInventarioDTODatos)
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ParametroNuloException {

        if (actualizacionProductoInventarioDTODatos.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
        
        if(actualizacionProductoInventarioDTODatos.getId().getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }

        String idProductoInventarioString = validarIdActualizacion(actualizacionProductoInventarioDTODatos.getId());
        ObjectId idProductoInventario = new ObjectId(idProductoInventarioString);

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<ProductoInventario> coleccionProductosInventario = baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO, ProductoInventario.class);

        verificarExistenciaProductoInventario(idProductoInventario, coleccionProductosInventario);

        ProductoInventario productoInventario = coleccionProductosInventario.find(Filters.eq(CAMPO_ID, idProductoInventario)).first();
        if (productoInventario == null) {
            throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO, idProductoInventarioString)
            );
        }

        if (actualizacionProductoInventarioDTODatos.tieneApartado()) {
            productoInventario.setApartado(actualizacionProductoInventarioDTODatos.getApartado());
        }

        coleccionProductosInventario.replaceOne(Filters.eq(CAMPO_ID, idProductoInventario), productoInventario);

        return convertirProductoInventarioADTO(productoInventario);
    }

    /**
     * Agrega un nuevo producto de inventario.
     */
    public void agregar(ProductoInventarioDTODatos nuevoProducto) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException,
            ParametroNuloException {
        
        if (nuevoProducto == null || nuevoProducto.getIdProducto() == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }
        
        ObjectId idProductoRegistrado = obtenerIdProductoRegistrado(nuevoProducto.getIdProducto());
        ObjectId idSucursalRegistrada = obtenerIdSucursalRegistrada(nuevoProducto.getIdSucursal());

        ProductoInventario producto = new ProductoInventario(
            idProductoRegistrado,
            idSucursalRegistrada,
            nuevoProducto.getApartado()
        );
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<ProductoInventario> coleccionProductosInventario 
                = baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO, ProductoInventario.class);
        
        coleccionProductosInventario.insertOne(producto);
        
    }
    
    public void agregar(Collection<ProductoInventarioDTODatos> nuevosProductosInventario) 
            throws FormatoIdInvalidoException, 
                   RegistroInexistenteException,
                   ParametroNuloException,
                   ValorParametroInvalidoException {

        if (nuevosProductosInventario == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_INVENTARIO_NULA);
        }
        
        if (nuevosProductosInventario.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_INVENTARIO_VACIA);
        }

        List<ProductoInventario> productosInventario = new LinkedList<>();

        for (ProductoInventarioDTODatos nuevoProductoInventario : nuevosProductosInventario) {
            

            ObjectId idProductoRegistrado = obtenerIdProductoRegistrado(nuevoProductoInventario.getIdProducto());
            ObjectId idSucursalRegistrada = obtenerIdSucursalRegistrada(nuevoProductoInventario.getIdSucursal());
            
            ProductoInventario productoInventario = new ProductoInventario(
                idProductoRegistrado,
                idSucursalRegistrada,
                nuevoProductoInventario.getApartado()
            );

            productosInventario.add(productoInventario);
        }

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<ProductoInventario> coleccionProductosInventario 
                = baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO, ProductoInventario.class);

        coleccionProductosInventario.insertMany(productosInventario);
    }
    
    public List<ProductoInventarioDTODatos> recuperarPorIdProducto(IdProductoDTODatos idProductoDTODatos)
            throws ParametroNuloException, 
            FormatoIdInvalidoException {
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();

        if (idProductoDTODatos == null || idProductoDTODatos.getIdProducto() == null){
            throw new ParametroNuloException(String.format(MENSAJE_DTO_ID_NULO, NOMBRE_ENTIDAD_PRODUCTO));
        }

        try{
            String idProductoString = (String)idProductoDTODatos.getIdProducto().getId();
            
            ObjectId idProductoObjectId = new ObjectId(idProductoString);
             
            MongoCollection<ProductoInventario> coleccionProductosInventario 
                = baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO, ProductoInventario.class);
            
            List<ProductoInventario> listaProductosInventario 
                    = coleccionProductosInventario.find(Filters.eq(CAMPO_PRODUCTO, idProductoObjectId)).into(new ArrayList<>());

            
            List<ProductoInventarioDTODatos> listaProductosInventarioDTODatos = new LinkedList<>();
            
            for(ProductoInventario productoInventario: listaProductosInventario){
                listaProductosInventarioDTODatos.add(convertirProductoInventarioADTO(productoInventario));
            }
            
            
            return listaProductosInventarioDTODatos;
            
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO));
        }
        
    }
    
    public List<ProductoInventarioDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<ProductoInventario> coleccionProductosInventario 
                = baseDatos.getCollection(COLECCION_PRODUCTOS_INVENTARIO, ProductoInventario.class);

        return coleccionProductosInventario.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirProductoInventarioADTO)
            .collect(Collectors.toList());
    }
    
    private String validarIdActualizacion(IdEntidadGenericoDatos idEntidadGenericoDatos) throws FormatoIdInvalidoException{
            
        try {
            
            return new ObjectId((String) idEntidadGenericoDatos.getId()).toHexString();
            
        } catch (IllegalArgumentException e) {
            throw new FormatoIdInvalidoException(MENSAJE_ID_FORMATO_INVALIDO);
        }
    }
    
    private void verificarExistenciaProductoInventario(ObjectId idProductoInventario, MongoCollection<ProductoInventario> coleccionProductosInventario) 
            throws RegistroInexistenteException {
        
        if (coleccionProductosInventario.countDocuments(eq(CAMPO_ID, idProductoInventario)) == 0) {
            
            throw new RegistroInexistenteException(
                String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO, idProductoInventario.toHexString())
            );
        }
    }


    private ProductoInventarioDTODatos convertirProductoInventarioADTO(ProductoInventario productoInventario) {
        return new ProductoInventarioDTODatos(
            new IdEntidadGenericoDatos(productoInventario.getId().toHexString()),
            productoInventario.getApartado(),
            new IdEntidadGenericoDatos(productoInventario.getIdProducto().toHexString()),
            new IdEntidadGenericoDatos(productoInventario.getIdSucursal().toHexString())
        );
    }
    
    private ObjectId obtenerIdProductoRegistrado(IdEntidadGenericoDatos idProducto) 
            throws RegistroInexistenteException,
            FormatoIdInvalidoException, 
            ParametroNuloException {
        
        if(idProducto == null || idProducto.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idProducto", NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }

        try {
            
            ObjectId idProductoRegistrado = new ObjectId((String) idProducto.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_PRODUCTOS).countDocuments(eq(CAMPO_ID, idProductoRegistrado)) == 0) {
                
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO, (String) idProducto.getId()));
            }
            return idProductoRegistrado;

        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO));
        }
    }
    
    private ObjectId obtenerIdSucursalRegistrada(IdEntidadGenericoDatos idSucursal) 
        throws RegistroInexistenteException, FormatoIdInvalidoException, ParametroNuloException {
        
        if(idSucursal == null || idSucursal.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idSucursal", NOMBRE_ENTIDAD_PRODUCTO_INVENTARIO));
        }

        try {
            
            ObjectId idSucursalRegistrada = new ObjectId((String) idSucursal.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_SUCURSALES).countDocuments(eq(CAMPO_ID, idSucursalRegistrada)) == 0) {
                
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_SUCURSAL, (String) idSucursal.getId()));
            }
            return idSucursalRegistrada;

        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_SUCURSAL));
        }
    }
}