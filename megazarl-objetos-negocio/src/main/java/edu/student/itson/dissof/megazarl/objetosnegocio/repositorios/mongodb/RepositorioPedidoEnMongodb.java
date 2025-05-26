
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.IdPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.IdProductoPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.PedidoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoPedidoDTODatos;
import edu.student.itson.dissof.dto.datos.enumeradores.EstadoPedidoDatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.enumeradores.EstadoPedidoNegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPedido;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioPedidoEnMongodb implements RepositorioPedido{

    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioPedidoEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public PedidoDTONegocios recuperarPorId(IdPedidoDTONegocios idPedidoDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idPedidoDTONegocios.getIdPedido().getId());
        
        IdPedidoDTODatos idPedidoDTODatos = new IdPedidoDTODatos(idEntidadGenericoDatos);
        
        PedidoDTODatos pedidoDTODatos;
        
        try {
            
            pedidoDTODatos = administradorMongodb.recuperarPedidoPorId(idPedidoDTODatos);
            
            List<ProductoPedidoDTONegocios> listaProductoPedidoDTONegocios = new LinkedList<>();

            for(ProductoPedidoDTODatos producoPedidoDTODatos: pedidoDTODatos.getProductosPedido()){
                
                ProductoPedidoDTONegocios productoPedidoDTONegocios 
                        = new ProductoPedidoDTONegocios(
                                new IdEntidadGenericoNegocios(producoPedidoDTODatos.getId().getId()),
                                producoPedidoDTODatos.getCantidad()
                );
                
                listaProductoPedidoDTONegocios.add(productoPedidoDTONegocios); 
               
            }

            PedidoDTONegocios pedidoDTONegocios 
                    = new PedidoDTONegocios(
                            new IdEntidadGenericoNegocios(pedidoDTODatos.getId().getId()), 
                            EstadoPedidoNegocios.valueOf(pedidoDTODatos.getEstado().name()),
                            new IdEntidadGenericoNegocios(pedidoDTODatos.getIdCliente().getId()),
                            new IdEntidadGenericoNegocios(pedidoDTODatos.getIdPaqueteria().getId()),
                            listaProductoPedidoDTONegocios);

            return pedidoDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public boolean existePorId(IdPedidoDTONegocios idPedidoDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        IdPedidoDTODatos idPedidoDTODatos = new IdPedidoDTODatos(new IdEntidadGenericoDatos(idPedidoDTONegocios.getIdPedido().getId()));
        
        try {
            return administradorMongodb.existePedidoPorId(idPedidoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregar(PedidoDTONegocios pedidoDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        List<ProductoPedidoDTODatos> listaProductosPedidoDTODatos = new LinkedList<>();
        
        for(ProductoPedidoDTONegocios productoPedidoDTONegocios: pedidoDTONegocios.getProductosPedido()){
            
            ProductoPedidoDTODatos productoPedidoDTODatos = new ProductoPedidoDTODatos(       
                    new IdEntidadGenericoDatos(productoPedidoDTONegocios.getIdPedido().getId()),
                    new IdEntidadGenericoDatos(productoPedidoDTONegocios.getIdProducto().getId()), 
                    productoPedidoDTONegocios.getCantidadRequerida());
            
            listaProductosPedidoDTODatos.add(productoPedidoDTODatos);
        }
        
        PedidoDTODatos pedidoDTODatos = new PedidoDTODatos(
                EstadoPedidoDatos.valueOf(pedidoDTONegocios.getEstado().name()),
                new IdEntidadGenericoDatos(pedidoDTONegocios.getIdCliente()),
                new IdEntidadGenericoDatos(pedidoDTONegocios.getIdPaqueteria()),
                listaProductosPedidoDTODatos);
        
        try {
            administradorMongodb.agregarPedido(pedidoDTODatos);
            
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
    public void agregar(Collection<PedidoDTONegocios> pedidosDTONegocios) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException, 
            ValorParametroInvalidoNegocioException {
        
        List<PedidoDTODatos> listaPedidosAgregar = new LinkedList<>();
        
        for(PedidoDTONegocios pedidoDTONegocios: pedidosDTONegocios){

            List<ProductoPedidoDTODatos> listaProductosPedidoDTODatos = new LinkedList<>();
        
            for(ProductoPedidoDTONegocios productoPedidoDTONegocios: pedidoDTONegocios.getProductosPedido()){

                ProductoPedidoDTODatos productoPedidoDTODatos = new ProductoPedidoDTODatos(       
                        new IdEntidadGenericoDatos(productoPedidoDTONegocios.getIdPedido().getId()), 
                        new IdEntidadGenericoDatos(productoPedidoDTONegocios.getIdProducto().getId()), 
                        productoPedidoDTONegocios.getCantidadRequerida());

                listaProductosPedidoDTODatos.add(productoPedidoDTODatos);
            }

            PedidoDTODatos pedidoDTODatos = new PedidoDTODatos(
                    EstadoPedidoDatos.valueOf(pedidoDTONegocios.getEstado().name()),
                    new IdEntidadGenericoDatos(pedidoDTONegocios.getIdCliente()),
                    new IdEntidadGenericoDatos(pedidoDTONegocios.getIdPaqueteria()),
                    listaProductosPedidoDTODatos);
            
            listaPedidosAgregar.add(pedidoDTODatos);

            }

        try {
            administradorMongodb.agregarColeccionPedidos(listaPedidosAgregar);
            
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
    public List<PedidoDTONegocios> recuperarTodos() {
        
        List<PedidoDTONegocios> listaPedidosDTONegocios = new LinkedList<>();

        List<PedidoDTODatos> listaPedidosDTODatos = administradorMongodb.recuperarTodosPedidos();

        for(PedidoDTODatos pedidoDTODatos: listaPedidosDTODatos){
            
            List<ProductoPedidoDTONegocios> listaProductosPedidoDTONegocios = new LinkedList<>();
            
            for(ProductoPedidoDTODatos productoPediodDTODatos: pedidoDTODatos.getProductosPedido()){
                
                ProductoPedidoDTONegocios productoPedidoDTONegocios = new ProductoPedidoDTONegocios(
                        new IdEntidadGenericoNegocios(productoPediodDTODatos.getId().getId()),
                        productoPediodDTODatos.getCantidad()
                );
                
                listaProductosPedidoDTONegocios.add(productoPedidoDTONegocios);
                
            }
            
            
            listaPedidosDTONegocios.add(
                    new PedidoDTONegocios(
                            new IdEntidadGenericoNegocios(pedidoDTODatos.getId().getId()),
                            EstadoPedidoNegocios.valueOf(pedidoDTODatos.getEstado().name()),
                            new IdEntidadGenericoNegocios(pedidoDTODatos.getIdCliente().getId()),
                            new IdEntidadGenericoNegocios(pedidoDTODatos.getIdPaqueteria().getId()),
                            listaProductosPedidoDTONegocios
                    )
            );

        }

        return listaPedidosDTONegocios;
    }

    @Override
    public boolean existeProductoPedidoPorId(IdProductoPedidoDTONegocios idProductoPedidoDTONegocio)
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        IdProductoPedidoDTODatos idProductoPedidoDatos = new IdProductoPedidoDTODatos(
                new IdEntidadGenericoDatos(idProductoPedidoDTONegocio.getIdProductoPedido().getId())
        );
        
        try {
            return administradorMongodb.existeProductoPedidoPorId(idProductoPedidoDatos);
        } catch (ParametroNuloException ex) {
            
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            
             throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregarProductoPedido(ProductoPedidoDTONegocios nuevoProductoPedido) 
            throws ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException {
        
        ProductoPedidoDTODatos productoPedidoDTODatos = new ProductoPedidoDTODatos(
                new IdEntidadGenericoDatos(nuevoProductoPedido.getIdPedido().getId()), 
                new IdEntidadGenericoDatos(nuevoProductoPedido.getIdProducto().getId()), 
                nuevoProductoPedido.getCantidadRequerida());
        
        try {
            administradorMongodb.agregarProductoPedido(productoPedidoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregarProductoPedido(Collection<ProductoPedidoDTONegocios> nuevosProductosPedido) 
            throws ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        List<ProductoPedidoDTODatos> listaProductosPedidoDTODatos = new LinkedList<>();
        
        for(ProductoPedidoDTONegocios productoPedidoDTONegocios: nuevosProductosPedido){
            
            ProductoPedidoDTODatos productoPedidoDTODatos = new ProductoPedidoDTODatos(
                    new IdEntidadGenericoDatos(productoPedidoDTONegocios.getIdPedido().getId()), 
                new IdEntidadGenericoDatos(productoPedidoDTONegocios.getIdProducto().getId()), 
                productoPedidoDTONegocios.getCantidadRequerida()
            );
            
            listaProductosPedidoDTODatos.add(productoPedidoDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarProductoPedido(listaProductosPedidoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }

    }

    @Override
    public List<ProductoPedidoDTONegocios> recuperarTodosProductosPedido() {
        
        List<ProductoPedidoDTODatos> listaProductosPedidoDTODatos = administradorMongodb.recuperarTodosProductosPedido();
        
        List<ProductoPedidoDTONegocios> listaProductosPedidoDTONegocios = new LinkedList<>();
        
        for(ProductoPedidoDTODatos productoPedidoDTODatos: listaProductosPedidoDTODatos){
            
            ProductoPedidoDTONegocios productoPedidoDTONegocios =
                    new ProductoPedidoDTONegocios(
                            new IdEntidadGenericoNegocios(productoPedidoDTODatos.getId().getId()), 
                            new IdEntidadGenericoNegocios(productoPedidoDTODatos.getIdProducto().getId()),
                            productoPedidoDTODatos.getCantidad());
            
            listaProductosPedidoDTONegocios.add(productoPedidoDTONegocios);
        }
        
        
        return listaProductosPedidoDTONegocios;
        
    }

    
    
    
    
}
