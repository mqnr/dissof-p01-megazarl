
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.ActualizacionProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoInventarioDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoInventarioDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoInventario;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioProductoInventarioEnMongodb implements RepositorioProductoInventario{

    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioProductoInventarioEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    
    @Override
    public ProductoInventarioDTONegocios recuperarPorId(IdProductoInventarioDTONegocios idProductoInventarioDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idProductoInventarioDTONegocios.getIdProductoInventario().getId());
        
        IdProductoInventarioDTODatos idProductoInventarioDTODatos = new IdProductoInventarioDTODatos(idEntidadGenericoDatos);
        
        ProductoInventarioDTODatos productoInventarioDTODatos;
        
        try {
            
            productoInventarioDTODatos = administradorMongodb.recuperarProductoInventarioPorId(idProductoInventarioDTODatos);


            ProductoInventarioDTONegocios productoInventarioDTONegocios 
                    = new ProductoInventarioDTONegocios(
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getId().getId()),
                            productoInventarioDTODatos.getApartado(),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdProducto().getId()),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdSucursal().getId())
                    );

            return productoInventarioDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
    }

    @Override
    public boolean existePorId(IdProductoInventarioDTONegocios idProductoInventarioDTONegocios)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        IdProductoInventarioDTODatos idProductoInventarioDTODatos = new IdProductoInventarioDTODatos(
                        new IdEntidadGenericoDatos(idProductoInventarioDTONegocios.getIdProductoInventario().getId()));
        
        try {
            return administradorMongodb.existeProductoInventarioPorId(idProductoInventarioDTODatos);
            
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
              
    }
    
    @Override
    public ProductoInventarioDTONegocios actualizar(ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException {
        
        ActualizacionProductoInventarioDTODatos actualizacionProductoInventarioDTODatos 
                = new ActualizacionProductoInventarioDTODatos(new IdEntidadGenericoDatos(actualizacionProductoInventarioDTONegocios.getId()));
        
        actualizacionProductoInventarioDTODatos.setApartado(actualizacionProductoInventarioDTONegocios.getApartado());
        
        try {
            ProductoInventarioDTODatos productoInventarioDTODatos =
                    administradorMongodb.actualizarProductoInventario(actualizacionProductoInventarioDTODatos);
            
            ProductoInventarioDTONegocios productoInventarioDTONegocios 
                    = new ProductoInventarioDTONegocios(
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getId().getId()), 
                            productoInventarioDTODatos.getApartado(),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdProducto().getId()), 
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdSucursal().getId())
                    );
            
            return productoInventarioDTONegocios;
            
            
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }


    }

    @Override
    public void agregar(ProductoInventarioDTONegocios productoInventarioDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        
        ProductoInventarioDTODatos productoInventarioDTODatos = new ProductoInventarioDTODatos(
                productoInventarioDTONegocios.getApartado(),
                new IdEntidadGenericoDatos(productoInventarioDTONegocios.getIdProducto().getId()),
                new IdEntidadGenericoDatos(productoInventarioDTONegocios.getIdSucursal().getId()));
        
        try {
            administradorMongodb.agregarProductoInventario(productoInventarioDTODatos);
            
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
    public void agregar(Collection<ProductoInventarioDTONegocios> productosInventarioDTONegocios) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        List<ProductoInventarioDTODatos> listaProductoInventarioAgregar = new LinkedList<>();
        
        for(ProductoInventarioDTONegocios productoInventarioDTONegocios: productosInventarioDTONegocios){

            ProductoInventarioDTODatos productoInventarioDTODatos = new ProductoInventarioDTODatos(
                productoInventarioDTONegocios.getApartado(),
                new IdEntidadGenericoDatos(productoInventarioDTONegocios.getIdProducto().getId()),
                new IdEntidadGenericoDatos(productoInventarioDTONegocios.getIdSucursal().getId()));
            
            listaProductoInventarioAgregar.add(productoInventarioDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionProductosInventario(listaProductoInventarioAgregar);
            
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
    public List<ProductoInventarioDTONegocios> recuperarPorIdProducto(IdProductoDTONegocios idProductoDTONegocios) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException{
        
        IdProductoDTODatos idProductoDTODatos = new IdProductoDTODatos(new IdEntidadGenericoDatos(idProductoDTONegocios.getIdProducto().getId()));
        
        List<ProductoInventarioDTONegocios> listaProductosInventarioDTONegocios = new LinkedList<>();

        List<ProductoInventarioDTODatos> listaProductosInventarioDTODatos;
        try {
            
            listaProductosInventarioDTODatos = administradorMongodb.recuperarPorIdProducto(idProductoDTODatos);
            
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }

        for(ProductoInventarioDTODatos productoInventarioDTODatos: listaProductosInventarioDTODatos){

            listaProductosInventarioDTONegocios.add(
                    new ProductoInventarioDTONegocios(
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getId().getId()),
                            productoInventarioDTODatos.getApartado(),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdProducto().getId()),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdSucursal().getId())
                    )
            );
        }

        return listaProductosInventarioDTONegocios;
    }

    @Override
    public List<ProductoInventarioDTONegocios> recuperarTodos() {
        
        List<ProductoInventarioDTONegocios> listaProductosInventarioDTONegocios = new LinkedList<>();

        List<ProductoInventarioDTODatos> listaProductosInventarioDTODatos = administradorMongodb.recuperarTodosProductosInventario();

        for(ProductoInventarioDTODatos productoInventarioDTODatos: listaProductosInventarioDTODatos){

            listaProductosInventarioDTONegocios.add(
                    new ProductoInventarioDTONegocios(
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getId().getId()),
                            productoInventarioDTODatos.getApartado(),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdProducto().getId()),
                            new IdEntidadGenericoNegocios(productoInventarioDTODatos.getIdSucursal().getId())
                    )
            );
        }

        return listaProductosInventarioDTONegocios;
    }

    
}
