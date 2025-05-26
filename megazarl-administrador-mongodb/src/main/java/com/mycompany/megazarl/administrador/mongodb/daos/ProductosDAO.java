package com.mycompany.megazarl.administrador.mongodb.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.Producto;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.dto.datos.ProductoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoDTODatos;
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

public class ProductosDAO {

    private final String COLECCION_PRODUCTOS = "Productos";
    private final String COLECCION_PROVEEDORES = "Proveedores";
    private final String CAMPO_ID = "_id";
    
    // Mensajes de excepción
    private final String MENSAJE_DTO_ID_NULO = "El DTO recibido para consulta es nulo.";
    private final String MENSAJE_REGISTRO_INEXISTENTE = "No existe un registro de %s con ID: %s";
    private final String MENSAJE_PARAMETRO_NULO = "El parámetro '%s' del %s es nulo.";
    private final String MENSAJE_DTO_AGREGAR_NULO = "El DTO del nuevo %s es nulo.";
    private final String MENSAJE_ID_FORMATO_INVALIDO = "El formato de ID de %s es inválido.";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_NULA = "La lista de nuevos productos es nula.";
    private final String MENSAJE_LISTA_NUEVOS_PRODUCTOS_VACIA = "La lista de nuevos productos está vacía.";
    
    // Mensajes de validación de producto
    private final String MENSAJE_FORMATO_NOMBRE_INVALIDO_CADENA_VACIA = "El nombre del producto es una cadena vacía.";
    private final String MENSAJE_FORMATO_NOMBRE_INVALIDO_MAS_CINCUENTA_CARACTERES = "El nombre del producto tiene más de cincuenta caracteres.";

    private final String MENSAJE_FORMATO_VARIEDAD_INVALIDO_CADENA_VACIA = "El nombre de la variedad del producto es una cadena vacía.";
    private final String MENSAJE_FORMATO_VARIEDAD_INVALIDO_MAS_CINCUENTA_CARACTERES = "El nombre de la variedad del producto tiene más de cincuenta caracteres.";
    
    private final String MENSAJE_FORMATO_DESCRIPCION_INVALIDO_CADENA_VACIA = "La descripción del producto no puede ser una cadena vacía";
    private final String MENSAJE_FORMATO_DESCRIPCION_INVALIDO_SUPERA_MAXIMO = "La descripción no puede superar los 50 caracteres";
  
    private final String MENSAJE_MILES_SEMILLAS_INVALIDO_CANTIDAD_NEGATIVA = "Los miles de semillas deben ser un valor positivo.";
    private final String MENSAJE_MILES_SEMILLAS_INVALIDO_CANTIDAD_SUPERA_MAXIMO = "La cantidad de miles de semillas no puede superar los 500 mil.";
    
    private final String MENSAJE_PRECIO_INVALIDO_CANTIDAD_NEGATIVA = "El precio del producto debe ser un valor positivo.";
    private final String MENSAJE_PRECIO_INVALIDO_CANTIDAD_SUPERA_MAXIMO = "El precio del producto no puede superar los $50,000 pesos.";
    
    private final String MENSAJE_PESO_INVALIDO_CANTIDAD_NEGATIVA = "El peso del producto ser un valor positivo";
    private final String MENSAJE_PESO_INVALIDO_CANTIDAD_SUPERA_MAXIMO = "El peso de un producto no puede ser mayor a 100 kg.";
    
    private final String MENSAJE_DIRECCION_IMAGEN_INVALIDA_CADENA_VACIA = "La dirección de la imagen no puede ser una cadena vacía";

    private final String NOMBRE_ENTIDAD_PRODUCTO = "producto";
    private final String NOMBRE_ENTIDAD_PROVEEDOR = "proveedor";
    
    private final int CANTIDAD_MAXIMA_CARACTERES_NOMBRE_VARIEDAD = 50;
    private final int CANTIDAD_MAXIMA_MILES_SEMILLAS = 500;
    private final int CANTIDAD_MAXIMA_CARACTERES_DESCRIPCION = 500;
    private final float PRECIO_MAXIMO = 50000.0F;
    private final double CANTIDAD_MAXIMA_PESO = 100.0D;

    public boolean existePorId(IdProductoDTODatos idProductoDTODatos) 
        throws ParametroNuloException, FormatoIdInvalidoException {
        
        if (idProductoDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idProductoDTODatos.getIdProducto() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO));
        }
        
        String idProducto = (String) idProductoDTODatos.getIdProducto().getId();
        
        try {
            
            ObjectId objectId = new ObjectId(idProducto);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            return baseDatos.getCollection(COLECCION_PRODUCTOS).countDocuments(new Document(CAMPO_ID, objectId)) > 0;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO));
        }
    }

    public ProductoDTODatos recuperarPorId(IdProductoDTODatos idProductoDTODatos) 
            throws FormatoIdInvalidoException,
            RegistroInexistenteException, 
            ParametroNuloException {
        
        if (idProductoDTODatos == null) {
            throw new ParametroNuloException(MENSAJE_DTO_ID_NULO);
        }
        
        if(idProductoDTODatos.getIdProducto() == null) {
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "id", NOMBRE_ENTIDAD_PRODUCTO));
        }
        
        String idProducto = (String) idProductoDTODatos.getIdProducto().getId();
        
        try {
            ObjectId objectId = new ObjectId(idProducto);
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            MongoCollection<Producto> coleccionProductos = baseDatos.getCollection(COLECCION_PRODUCTOS, Producto.class);
            
            Producto producto = coleccionProductos.find(new Document(CAMPO_ID, objectId)).first();
            
            if (producto == null) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PRODUCTO, idProducto)
                );
            }
            
            return convertirProductoADTO(producto);
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PRODUCTO));
        }
    }

    public void agregar(ProductoDTODatos nuevoProducto) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ValorParametroInvalidoException, 
            ParametroNuloException {
        
        validarCamposObligatorios(nuevoProducto);
        
        ObjectId idProveedor = obtenerIdProveedorRegistrado(nuevoProducto.getIdProveedor());

        Producto producto = new Producto(
            nuevoProducto.getNombre(),
            nuevoProducto.getVariedad(),
            nuevoProducto.getDescripcion(),
            nuevoProducto.getMilesSemillas(),
            nuevoProducto.getPrecio(),
            nuevoProducto.getPesoKg(),
            nuevoProducto.getDireccionImagen(),
            idProveedor
        );

        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Producto> coleccionProductos = baseDatos.getCollection(COLECCION_PRODUCTOS, Producto.class);
                
        coleccionProductos.insertOne(producto);
    }
    
    public void agregar(Collection<ProductoDTODatos> nuevosProductos) 
            throws FormatoIdInvalidoException, 
            RegistroInexistenteException, 
            ValorParametroInvalidoException, 
            ParametroNuloException {
        
        if (nuevosProductos == null) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_NULA);
        }

        if (nuevosProductos.isEmpty()) {
            throw new ParametroNuloException(MENSAJE_LISTA_NUEVOS_PRODUCTOS_VACIA);
        }

        

        List<Producto> productos = new LinkedList<>();

        for(ProductoDTODatos nuevoProducto : nuevosProductos) { 
            
            validarCamposObligatorios(nuevoProducto);
            ObjectId idProveedor = obtenerIdProveedorRegistrado(nuevoProducto.getIdProveedor());
        
            Producto producto = new Producto(
                nuevoProducto.getNombre(),
                nuevoProducto.getVariedad(),
                nuevoProducto.getDescripcion(),
                nuevoProducto.getMilesSemillas(),
                nuevoProducto.getPrecio(),
                nuevoProducto.getPesoKg(),
                nuevoProducto.getDireccionImagen(),
                idProveedor
            );
            
            productos.add(producto);
        
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
        MongoCollection<Producto> coleccionProductos = baseDatos.getCollection(COLECCION_PRODUCTOS, Producto.class);
        
        coleccionProductos.insertMany(productos);
    }
    
    
    
    public List<ProductoDTODatos> recuperarTodos() {
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        MongoCollection<Producto> coleccionProductos = baseDatos.getCollection(COLECCION_PRODUCTOS, Producto.class);

        return coleccionProductos.find()
            .into(new ArrayList<>())
            .stream()
            .map(this::convertirProductoADTO)
            .collect(Collectors.toList());
    }

    private void validarCamposObligatorios(ProductoDTODatos productoDTODatos) 
            throws ValorParametroInvalidoException, 
            ParametroNuloException,
            RegistroInexistenteException,
            FormatoIdInvalidoException {
        
        if (productoDTODatos == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_DTO_AGREGAR_NULO, NOMBRE_ENTIDAD_PRODUCTO));
        }

        // Validación de nombre
        String nombre = productoDTODatos.getNombre();
        
        if (nombre == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "nombre", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }
        
        if (nombre.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRE_INVALIDO_CADENA_VACIA);
        }

        if (nombre.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_VARIEDAD) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_NOMBRE_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        
        // Validación de variedad
        String variedad = productoDTODatos.getVariedad();
        
        if (variedad == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "variedad", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }
        
        if (variedad.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_VARIEDAD_INVALIDO_CADENA_VACIA);
        }

        if (variedad.trim().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRE_VARIEDAD) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_VARIEDAD_INVALIDO_MAS_CINCUENTA_CARACTERES);
        }
        
        String descripcion = productoDTODatos.getDescripcion();
        
        if (descripcion == null) {
            throw new ParametroNuloException(
                String.format(MENSAJE_PARAMETRO_NULO, "descripcion", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }

        if (descripcion.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_DESCRIPCION_INVALIDO_CADENA_VACIA);
        }

        if (descripcion.trim().length() > CANTIDAD_MAXIMA_CARACTERES_DESCRIPCION) {
            throw new ValorParametroInvalidoException(MENSAJE_FORMATO_DESCRIPCION_INVALIDO_SUPERA_MAXIMO);
        }

        // Validación miles de semillas
        Integer milesSemillas = productoDTODatos.getMilesSemillas();
        
        if (milesSemillas == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "miles de semillas", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }
        
        if (milesSemillas <= 0) {
            throw new ValorParametroInvalidoException(MENSAJE_MILES_SEMILLAS_INVALIDO_CANTIDAD_NEGATIVA);
        }
        
        if (milesSemillas > CANTIDAD_MAXIMA_MILES_SEMILLAS) {
            throw new ValorParametroInvalidoException(MENSAJE_MILES_SEMILLAS_INVALIDO_CANTIDAD_SUPERA_MAXIMO);
        }

        Double precioProducto = productoDTODatos.getPrecio();
        
        // Validación de precio de producto
        if (precioProducto == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "precio", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }
        
        if (precioProducto < 0) {
            throw new ValorParametroInvalidoException(MENSAJE_PRECIO_INVALIDO_CANTIDAD_NEGATIVA);
        }
        
        if(precioProducto > PRECIO_MAXIMO){
            throw new ValorParametroInvalidoException(MENSAJE_PRECIO_INVALIDO_CANTIDAD_SUPERA_MAXIMO);
        }

        // Validación peso
        Double peso = productoDTODatos.getPesoKg();
        
        if (peso == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "peso", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }
        
        if (peso < 0) {
            throw new ValorParametroInvalidoException(MENSAJE_PESO_INVALIDO_CANTIDAD_NEGATIVA);
        }
        
        if(peso > CANTIDAD_MAXIMA_PESO){
            throw new ValorParametroInvalidoException(MENSAJE_PESO_INVALIDO_CANTIDAD_SUPERA_MAXIMO);
        }

        // Validación imagen
        String direccionImagen = productoDTODatos.getDireccionImagen();
        
        if (direccionImagen == null) {
            throw new ValorParametroInvalidoException(
                    String.format(MENSAJE_PARAMETRO_NULO, "direccion de imagen", NOMBRE_ENTIDAD_PRODUCTO)
            );
        }
        
        if (direccionImagen.trim().isEmpty()) {
            throw new ValorParametroInvalidoException(MENSAJE_DIRECCION_IMAGEN_INVALIDA_CADENA_VACIA);
        }

        IdEntidadGenericoDatos idProveedor = productoDTODatos.getIdProveedor();
        
        obtenerIdProveedorRegistrado(idProveedor);
    }

    private ObjectId obtenerIdProveedorRegistrado(IdEntidadGenericoDatos idProveedor) 
            throws RegistroInexistenteException, 
            FormatoIdInvalidoException, 
            ParametroNuloException {
        
        if(idProveedor == null || idProveedor.getId() == null){
            throw new ParametroNuloException(String.format(MENSAJE_PARAMETRO_NULO, "idProveedor", NOMBRE_ENTIDAD_PROVEEDOR));
        }
        
        try {
            ObjectId idProveedorRegistrado = new ObjectId((String) idProveedor.getId());
            MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
            
            if (baseDatos.getCollection(COLECCION_PROVEEDORES).countDocuments(new Document(CAMPO_ID, idProveedorRegistrado)) == 0) {
                throw new RegistroInexistenteException(
                    String.format(MENSAJE_REGISTRO_INEXISTENTE, NOMBRE_ENTIDAD_PROVEEDOR, idProveedor.getId())
                );
            }
            return idProveedorRegistrado;
            
        } catch (IllegalArgumentException ex) {
            throw new FormatoIdInvalidoException(String.format(MENSAJE_ID_FORMATO_INVALIDO, NOMBRE_ENTIDAD_PROVEEDOR));
        }
    }

    private ProductoDTODatos convertirProductoADTO(Producto producto) {
            
        return new ProductoDTODatos(
            new IdEntidadGenericoDatos(producto.getId().toHexString()),
            producto.getNombre(),
            producto.getVariedad(),
            producto.getDescripcion(),
            producto.getMilesSemillas(),
            producto.getPrecio(),
            producto.getPesoKg(),
            producto.getDireccionImagen(),
            new IdEntidadGenericoDatos(producto.getIdProveedor().toHexString())
        );
    }
    private static final Logger LOG = Logger.getLogger(ProductosDAO.class.getName());
    
    
    
    
}