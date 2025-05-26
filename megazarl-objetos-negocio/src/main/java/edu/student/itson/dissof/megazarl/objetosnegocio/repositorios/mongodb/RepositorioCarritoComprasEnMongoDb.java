
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.ActualizacionProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoCarritoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoCarritoDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCarritoCompras;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioCarritoComprasEnMongodb implements RepositorioCarritoCompras{
    
    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioCarritoComprasEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public CarritoComprasDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idCarritoComprasDTONegocios.getIdCarritoCompras().getId());
        
        IdCarritoComprasDTODatos idCarritoComprasDTODatos = new IdCarritoComprasDTODatos(idEntidadGenericoDatos);
        
        CarritoComprasDTODatos carritoComprasDTODatos;
        try {
            
            carritoComprasDTODatos = administradorMongodb.recuperarCarritoComprasPorId(idCarritoComprasDTODatos);
            
            List<ProductoCarritoDTONegocios> listaProductosCarritoDTONegocios = new LinkedList<>();
        
            for(ProductoCarritoDTODatos productoCarritoDTODatos: carritoComprasDTODatos.getProductosCarrito()){
                listaProductosCarritoDTONegocios.add(
                        new ProductoCarritoDTONegocios(
                                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                                productoCarritoDTODatos.getCantidad())
                        
                );
            }

            CarritoComprasDTONegocios carritoComprasDTONegocios 
                    = new CarritoComprasDTONegocios(
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getId().getId()), 
                            carritoComprasDTODatos.getEsVigente(),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdCliente().getId()),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdPaqueteria().getId()),
                            listaProductosCarritoDTONegocios);

            return carritoComprasDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
       
        
    }

    @Override
    public boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTONegocios) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        IdCarritoComprasDTODatos idCarritoComprasDTODatos = 
                new IdCarritoComprasDTODatos(
                        new IdEntidadGenericoDatos(idCarritoComprasDTONegocios.getIdCarritoCompras().getId()));
        
        try {
            return administradorMongodb.existeCarritoComprasPorId(idCarritoComprasDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
    }
    
    @Override
    public CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
        ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos 
                = new ActualizacionCarritoComprasDTODatos(new IdEntidadGenericoDatos(actualizacionCarritoComprasDTONegocios.getId()));
        
        actualizacionCarritoComprasDTODatos.setEsVigente(actualizacionCarritoComprasDTONegocios.getEsVigente());

        actualizacionCarritoComprasDTODatos.setIdPaqueteria(new IdEntidadGenericoDatos(actualizacionCarritoComprasDTONegocios.getIdPaqueteria()));
        
        CarritoComprasDTODatos carritoComprasDTODatos;
        try {
            
            carritoComprasDTODatos = administradorMongodb.actualizarCarritoCompras(actualizacionCarritoComprasDTODatos);
            
            List<ProductoCarritoDTONegocios> listaProductosCarritoDTONegocios = new LinkedList<>();
        
            for(ProductoCarritoDTODatos productoCarritoDTODatos: carritoComprasDTODatos.getProductosCarrito()){
                
                listaProductosCarritoDTONegocios.add(
                        new ProductoCarritoDTONegocios(
                                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                                productoCarritoDTODatos.getCantidad())
                        
                );
                
            }
            
            CarritoComprasDTONegocios carritoComprasDTONegocios 
                    = new CarritoComprasDTONegocios(
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getId().getId()), 
                            carritoComprasDTODatos.getEsVigente(),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdCliente().getId()),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdPaqueteria().getId()),
                            listaProductosCarritoDTONegocios);


            return carritoComprasDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ValorParametroInvalidoException ex) {
            throw new ValorParametroInvalidoNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
        
        
    }

    @Override
    public void agregar(CarritoComprasDTONegocios carritoCompras) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
       
        List<ProductoCarritoDTODatos> listaProductosCarritoDTODatos = new LinkedList<>();
        
        for(ProductoCarritoDTONegocios productoCarritoDTONegocios: carritoCompras.getProductosCarrito()){
        
            listaProductosCarritoDTODatos.add(
                    new ProductoCarritoDTODatos(
                            productoCarritoDTONegocios.getCantidad(),
                            new IdEntidadGenericoDatos(productoCarritoDTONegocios.getId().getId())));
            
        }
        
        CarritoComprasDTODatos carritoComprasDTODatos;
        if(carritoCompras.getIdPaqueteria() == null){
            
            carritoComprasDTODatos = new CarritoComprasDTODatos(
                carritoCompras.getEsVigente(), 
                new IdEntidadGenericoDatos(carritoCompras.getIdCliente().getId()),
                listaProductosCarritoDTODatos);
            
        } else{
            carritoComprasDTODatos = new CarritoComprasDTODatos(
                carritoCompras.getEsVigente(), 
                new IdEntidadGenericoDatos(carritoCompras.getIdCliente().getId()),
                new IdEntidadGenericoDatos(carritoCompras.getIdPaqueteria().getId()), 
                listaProductosCarritoDTODatos);
        }
        
        
        
        
        try {
            administradorMongodb.agregarCarritoCompras(carritoComprasDTODatos);
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ValorParametroInvalidoException ex) {
            throw new ValorParametroInvalidoNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregar(Collection<CarritoComprasDTONegocios> carritosCompras) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException{
        
        List<CarritoComprasDTODatos> listaCarritosCompraAgregar = new LinkedList<>();
        
        for(CarritoComprasDTONegocios carritoCompras: carritosCompras){
            
            List<ProductoCarritoDTODatos> listaProductosCarritoDTODatos = new LinkedList<>();
        
            for(ProductoCarritoDTONegocios productoCarritoDTONegocios: carritoCompras.getProductosCarrito()){

                listaProductosCarritoDTODatos.add(
                        new ProductoCarritoDTODatos(
                                productoCarritoDTONegocios.getCantidad(),
                                new IdEntidadGenericoDatos(productoCarritoDTONegocios.getId().getId())));

            }

            CarritoComprasDTODatos carritoComprasDTODatos = new CarritoComprasDTODatos(
                    carritoCompras.getEsVigente(), 
                    new IdEntidadGenericoDatos(carritoCompras.getIdCliente().getId()),
                    new IdEntidadGenericoDatos(carritoCompras.getIdPaqueteria().getId()), 
                    listaProductosCarritoDTODatos);
            
            listaCarritosCompraAgregar.add(carritoComprasDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionCarritosCompra(listaCarritosCompraAgregar);
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (ValorParametroInvalidoException ex) {
            throw new ValorParametroInvalidoNegocioException(ex.getMessage());
        }
            
    }

    @Override
    public List<CarritoComprasDTONegocios> recuperarTodos() {
        
        List<CarritoComprasDTONegocios> listaCarritosComprasDTONegocios = new LinkedList<>();
        
        List<CarritoComprasDTODatos> listaCarritosComprasDTODatos = administradorMongodb.recuperarTodosCarritoCompras();
        
        for(CarritoComprasDTODatos carritoComprasDTODatos: listaCarritosComprasDTODatos){
            
            List<ProductoCarritoDTONegocios> listaProductosCarritoDTONegocios = new LinkedList<>();
        
            for(ProductoCarritoDTODatos productoCarritoDTODatos: carritoComprasDTODatos.getProductosCarrito()){
                
                
                listaProductosCarritoDTONegocios.add(
                        new ProductoCarritoDTONegocios(
                                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                                productoCarritoDTODatos.getCantidad())
                        
                );
            }
        
            CarritoComprasDTONegocios carritoComprasDTONegocios 
                    = new CarritoComprasDTONegocios(
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getId().getId()), 
                            carritoComprasDTODatos.getEsVigente(),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdCliente().getId()),
                            carritoComprasDTODatos.getIdPaqueteria() != null ? new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdPaqueteria().getId()): null,
                            listaProductosCarritoDTONegocios);
            
            listaCarritosComprasDTONegocios.add(carritoComprasDTONegocios);
            
        }
        
        return listaCarritosComprasDTONegocios;
        
    }

    @Override
    public ProductoCarritoDTONegocios recuperarProductoCarritoPorId(IdProductoCarritoDTONegocios idProductoCarritoDTONegocios) 
            throws RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        
        IdProductoCarritoDTODatos idProductoCarritoDTODatos 
                = new IdProductoCarritoDTODatos(new IdEntidadGenericoDatos(idProductoCarritoDTONegocios.getIdProductoCarrito().getId()));

        
        ProductoCarritoDTODatos productoCarritoDTODatos;
        try {
            productoCarritoDTODatos = administradorMongodb.recuperarProductoCarritoPorId(idProductoCarritoDTODatos);
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
        ProductoCarritoDTONegocios productoCarritoDTONegocios = new ProductoCarritoDTONegocios(
                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getId().getId()),
                 new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                productoCarritoDTODatos.getCantidad());
        
        return productoCarritoDTONegocios;
        
    }

    @Override
    public boolean existeProductoCarritoPorId(IdProductoCarritoDTONegocios idProductoCarritoDTONegocios) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        IdProductoCarritoDTODatos idProductoCarritoDTODatos 
                = new IdProductoCarritoDTODatos(new IdEntidadGenericoDatos(idProductoCarritoDTONegocios.getIdProductoCarrito().getId()));
        
        try {
            return administradorMongodb.existeProductoCarritoPorId(idProductoCarritoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public ProductoCarritoDTONegocios actualizarProductoCarrito(ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTONegocios) 
            throws RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException,
            ValorParametroInvalidoNegocioException {
        
        ActualizacionProductoCarritoDTODatos actualizacionProductoCarritoDTODatos 
                = new ActualizacionProductoCarritoDTODatos(
                        new IdEntidadGenericoDatos(actualizacionProductoCarritoDTONegocios.getId().getId()));
        
        actualizacionProductoCarritoDTODatos.setCantidad(actualizacionProductoCarritoDTONegocios.getCantidad());
        
        try {
            ProductoCarritoDTODatos productoCarritoDTODatos = administradorMongodb.actualizarProductoCarrito(actualizacionProductoCarritoDTODatos);
            
            ProductoCarritoDTONegocios productoCarritoDTONegocios =  new ProductoCarritoDTONegocios(
                    new IdEntidadGenericoNegocios(productoCarritoDTODatos.getId().getId()),
                    new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                    productoCarritoDTODatos.getCantidad()
            );
            
            return productoCarritoDTONegocios;
            
            
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (ValorParametroInvalidoException ex) {
            throw new ValorParametroInvalidoNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void removerProductoCarritoPorId(IdProductoCarritoDTONegocios idProdutoCarritoDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        IdProductoCarritoDTODatos idProductoCarritoDTODatos = new IdProductoCarritoDTODatos(
                new IdEntidadGenericoDatos(idProdutoCarritoDTONegocios.getIdProductoCarrito().getId())
        );
        
        try {
            administradorMongodb.removerProductoCarritoPorId(idProductoCarritoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
       
    }

    @Override
    public void agregarProductoCarrito(ProductoCarritoDTONegocios nuevoProductoCarritoNegocios) 
            throws ParametroNuloNegocioException,
            RegistroInexistenteNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        ProductoCarritoDTODatos productoCarritoDTODatos = 
                new ProductoCarritoDTODatos(
                        nuevoProductoCarritoNegocios.getCantidad(),
                        new IdEntidadGenericoDatos(nuevoProductoCarritoNegocios.getIdCliente().getId()),
                        new IdEntidadGenericoDatos(nuevoProductoCarritoNegocios.getIdProducto().getId()));
        
        try {
            administradorMongodb.agregarProductoCarrito(productoCarritoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }

    }
    

    @Override
    public void agregarProductosCarrito(Collection<ProductoCarritoDTONegocios> nuevosProductosCarrito) 
            throws ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        
        List<ProductoCarritoDTODatos> listaProductosCarritoDTODatos = new LinkedList<>();
        
        for(ProductoCarritoDTONegocios productoCarritoDTONegocios: nuevosProductosCarrito){
            
            ProductoCarritoDTODatos productoCarritoDTODatos = 
                new ProductoCarritoDTODatos(
                        new IdEntidadGenericoDatos(productoCarritoDTONegocios.getIdProducto().getId()),
                        productoCarritoDTONegocios.getCantidad(),
                        new IdEntidadGenericoDatos(productoCarritoDTONegocios.getIdCliente().getId()));
            
            listaProductosCarritoDTODatos.add(productoCarritoDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarProductosCarrito(listaProductosCarritoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
     
    }

    @Override
    public List<ProductoCarritoDTONegocios> recuperarTodosProductosCarrito() {
        
        List<ProductoCarritoDTODatos> listaProductosCarritoDTODatos = administradorMongodb.recuperarTodosProductosCarrito();
        
        List<ProductoCarritoDTONegocios> listaProductosCarritoDTONegocios = new LinkedList<>();
        
        for(ProductoCarritoDTODatos productoCarritoDTODatos: listaProductosCarritoDTODatos){
            
            ProductoCarritoDTONegocios productoCarritoDTONegocios = new ProductoCarritoDTONegocios(
                new IdEntidadGenericoNegocios(productoCarritoDTODatos.getId().getId()),
                 new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()),
                productoCarritoDTODatos.getCantidad());
            
            listaProductosCarritoDTONegocios.add(productoCarritoDTONegocios);
        }
        
        return listaProductosCarritoDTONegocios;
        
    }
    
}
