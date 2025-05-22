
package com.mycompany.megazarl.administrador.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.megazarl.administrador.mongodb.clasesmapeadas.CarritoCompras;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroMismoIdExisteException;
import com.mycompany.megazarl.administrador.mongodb.manejadorconexiones.ManejadorConexiones;
import com.mycompany.megazarl.administrador.mongodb.utils.ConversorIds;
import edu.student.itson.dissof.dto.datos.CarritoComprasIdsRelacionesDTO;
import org.bson.Document;
import org.bson.types.ObjectId;


public class CarritoComprasDAO {
    
    private String COLECCION_CARRITOS_COMPRA = "CarritosCompra";
    private String COLECCION_CLIENTES = "Clietes";
    private String COLECCION_PAQUETERIAS = "Paqeterias";
    
    private final String CAMPO_NOMBRE = "nombres";
    private final String CAMPO_EDAD = "edad";
    private final String CAMPO_ID = "_Id";
    
    private String MENSAJE_DTO_ID_NULO_REGISTRO_EXCEPCION = "El DTO recibido que contiene el ID para realizar la consulta del carrito de compras es nulo.";
    private String MENSAJE_ID_NULO_REGISTRO_EXCEPCION = "El ID recibido para realizar la consulta del carrito de compras es nulo.";
    private String MENSAJE_REGISTRO_INEXISTENTE_EXCEPCION = "No existe un registro de %s con el ID: %s";
    private String MENSAJE_DTO_ACTUALIZACION_NULO_EXCEPCION = "El DTO recibido que contiene los datos de actualizacion de un carrito de compras es nulo.";
    private String MENSAJE_ID_ACTUALIZACION_NULO_EXCEPCION = "El ID del DTO con la información para actualizar un carrito de compras es nulo.";
    private String MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION = "El valor del parámetro %s del nuevo %s a registrar es nulo.";
    private String MENSAJE_DTO_AGREGAR_NULO_EXCEPCION = "El DTO que contiene los datos del nuevo %s a registrar es nulo.";
    private String MENSAJE_LISTA_AGREGAR_VACIA_NULA = "La lista de carritos de compra a agregar no puede ser nula o vacía.";
    private String MENSAJE_VARIOS_REGISTROS_INEXISTENTES = "No existen los registros de %s con los ids recibidos";
    private String MENSAJE_REGISTRO_EXISTENTE_EXCEPCION = "Ya existe un registro de %s con el mismo valor de %s.";
    
    private String NOMBRE_ENTIDAD_CARRITO_COMPRAS = "carrito de compras";
    private String NOMBRE_ENTIDAD_PAQUETERIA = "paqueteria";
    private String NOMBRE_ENTIDAD_CLIENTE = "cliente";
    private String NOMBRE_ENTIDAD_PRODUCTO_CARRITO = "producto en carrito";
    
    public void existePorId(){
        
       
        
    }
    
    public void recuperarPorId(Long id){
        
    }
    
    public void actualizar(){
        
    }
    
    public void agregar(CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO) throws AgregarInformacionNulaException, FormatoInvalidoIdConversionException, RegistroMismoIdExisteException{
        
        if(carritoComprasIdsRelacionesDTO == null){
            throw new AgregarInformacionNulaException(String.format(MENSAJE_DTO_AGREGAR_NULO_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        if(carritoComprasIdsRelacionesDTO.getId() == null){
            throw new AgregarInformacionNulaException(String.format(
                    MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                    "id", 
                    NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        if(carritoComprasIdsRelacionesDTO.getEsVigente() == null){
            throw new AgregarInformacionNulaException(String.format(
                    MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                    "esVigente",
                    NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        if(carritoComprasIdsRelacionesDTO.getIdCliente() == null){
            throw new AgregarInformacionNulaException(String.format(
                    MENSAJE_PARAMETRO_AGREGAR_NULO_EXCEPCION, 
                    "idCliente",
                    NOMBRE_ENTIDAD_CARRITO_COMPRAS));
        }
        
        MongoDatabase baseDatos = ManejadorConexiones.obtenerBaseDatos();
        
//        MongoCollection<CarritoCompras> coleccion = baseDatos.getCollection(COLECCION, CarritoCompras.class);
//        
//        Document filtrosBusquedaCarrito = new Document();
//        
//        String idHexadecimal = ConversorIds.convertirIdLongAHex(carritoComprasIdsRelacionesDTO.getId());
//        
//        filtrosBusquedaCarrito.append(CAMPO_ID, new ObjectId(idHexadecimal));
//        
//        coleccion.find(filtrosBusquedaCarrito);
//
//        FindIterable<CarritoCompras> resultados = coleccion.find(filtrosBusquedaCarrito);
//        
//        CarritoCompras carritoCompras = resultados.first();
//        
//        if(carritoCompras != null){
//            throw new RegistroMismoIdExisteException(String.format(MENSAJE_REGISTRO_EXISTENTE_EXCEPCION, NOMBRE_ENTIDAD_CARRITO_COMPRAS, CAMPO_ID));
//        }
//        
//        // Recuperación de cliente asociado
//        
//        // Recuperacion de paqueteria asociada
//        
        
        
        
//        
//        CarritoCompras nuevoCarritoCompras = new CarritoCompras(
//                carritoComprasIdsRelacionesDTO.getEsVigente(),
//                carritoComprasIdsRelacionesDTO,
//                null);
//        
//        // Tipo Document - Objeto JSON - Es como un hashMap
//        coleccion.insertOne(nuevoCarritoCompras);
        
    }
    
    
}
