
package edu.student.itson.dissof.megazarl.negocio;

import edu.student.itson.dissof.administradorproveedores.FAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas.FAdministradorAuxiliaresVentas;
import edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas.IAdministradorAuxiliaresVentas;
import edu.student.itson.dissof.megazarl.administradorclientes.FAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.FAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpedidos.FAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorproductos.FAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorsucursales.FAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.carritocompras.FAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.IAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.direcciones.FAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.mapas.FAdministradorMapas;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;


public class FabricaSubsistemas {

    
    public static IAdministradorDirecciones obtenerAdministradorDirecciones(){
        
        IAdministradorDirecciones administradorDirecciones = new FAdministradorDirecciones();
        
        return administradorDirecciones;
    }
    
    public static IAdministradorSucursales obtenerAdministradorSucursales(){
        
        IAdministradorDirecciones administradorDirecciones = obtenerAdministradorDirecciones();
        IAdministradorSucursales administradorSucursales = new FAdministradorSucursales(administradorDirecciones);
        
        return administradorSucursales;
    }
    
    public static IAdministradorProveedores obtenerAdministradorProveedores(){
        
        IAdministradorProveedores administradorProveedores = new FAdministradorProveedores();
        
        return administradorProveedores;
    }
    
    public static IAdministradorPaqueterias obtenerAdministradorPaqueterias(DireccionDTO direccionDefectoPaqueteria){
        
        IAdministradorClientes administradorClientes = obtenerAdministradorClientes();
        IAdministradorSucursales administradorSucursales = obtenerAdministradorSucursales();
        IAdministradorProveedores administradorProveedores = obtenerAdministradorProveedores();
        IAdministradorDirecciones administradorDirecciones = obtenerAdministradorDirecciones();
        IAdministradorMapas administradorMapas = obtenerAdministradorMapas();
        
        IAdministradorPaqueterias administradorPaqueterias = new FAdministradorPaqueterias(
                administradorClientes, 
                administradorSucursales,
                administradorProveedores,
                administradorDirecciones,
                administradorMapas,
                direccionDefectoPaqueteria);
        
        return administradorPaqueterias;
     
    }
    
    public static IAdministradorProductos obtenerAdministradorProductos(){
        
        IAdministradorProveedores administradorProveedores = obtenerAdministradorProveedores();
        IAdministradorProductos administradorProductos = new FAdministradorProductos(administradorProveedores);
        
        return administradorProductos;
    }

    
    public static IAdministradorClientes obtenerAdministradorClientes(){
        
        IAdministradorDirecciones administradorDirecciones  = obtenerAdministradorDirecciones();
        IAdministradorClientes administradorClientes = new FAdministradorClientes(administradorDirecciones);
        
        return administradorClientes;
    }
    
    public static IAdministradorPedidos obtenerAdministradorPedidos(DireccionDTO direccionDefectoPaqueteria){
        
        IAdministradorProductos administradorProductos = obtenerAdministradorProductos();
        IAdministradorSucursales administradorSucursales = obtenerAdministradorSucursales();
        IAdministradorClientes administradorClientes = obtenerAdministradorClientes();
        IAdministradorPaqueterias administradorPaqueterias = obtenerAdministradorPaqueterias(direccionDefectoPaqueteria);
        IAdministradorProveedores administradorProveedores = obtenerAdministradorProveedores();
        IAdministradorDirecciones administradorDirecciones = obtenerAdministradorDirecciones();
        IAdministradorMapas administradorMapas = obtenerAdministradorMapas();
        
        IAdministradorPedidos administradorPedidos = new FAdministradorPedidos(
                administradorProductos,
                administradorSucursales,
                administradorClientes,
                administradorPaqueterias,
                administradorProveedores,
                administradorDirecciones,
                administradorMapas);
        
        return administradorPedidos;
    }
    
    public static IAdministradorCarritoCompras obtenerAdministradorCarritoCompras(Double montoMinimoEnvioPedido, DireccionDTO direccionDefectoPaqueteria){
        
        IAdministradorProductos administradorProductos = obtenerAdministradorProductos();
        IAdministradorPedidos administradorPedidos = obtenerAdministradorPedidos(direccionDefectoPaqueteria);
        IAdministradorClientes administradorClientes = obtenerAdministradorClientes();
        IAdministradorPaqueterias administradorPaqueterias = obtenerAdministradorPaqueterias(direccionDefectoPaqueteria);
        
        IAdministradorCarritoCompras administradorCarritoCompras = 
                new FAdministradorCarritoCompras(
                        administradorProductos, 
                        administradorPedidos, 
                        administradorClientes, 
                        administradorPaqueterias,
                        montoMinimoEnvioPedido);
        
          
        return administradorCarritoCompras;
    }
    
    public static IAdministradorAuxiliaresVentas obtenerAdministradorAuxiliaresVentas(){
        
        IAdministradorAuxiliaresVentas administradorAuxiliaresVentas = new FAdministradorAuxiliaresVentas();
        
        return administradorAuxiliaresVentas;
        
    }
    
    
    public static IAdministradorMapas obtenerAdministradorMapas(){
        
        IAdministradorMapas administradorMapas = new FAdministradorMapas();
        
        return administradorMapas;
        
    }
    
    
}
