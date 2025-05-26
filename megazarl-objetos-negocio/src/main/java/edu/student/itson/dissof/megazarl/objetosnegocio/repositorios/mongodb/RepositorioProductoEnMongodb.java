
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.IdProductoDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProducto;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioProductoEnMongodb implements RepositorioProducto{

    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioProductoEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public ProductoDTONegocios recuperarPorId(IdProductoDTONegocios idProductoDTONegocios) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException {
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idProductoDTONegocios.getIdProducto().getId());
        
        IdProductoDTODatos idProductoDTODatos = new IdProductoDTODatos(idEntidadGenericoDatos);
        
        ProductoDTODatos productoDTODatos;
        
        try {
            
            productoDTODatos = administradorMongodb.recuperarProductoPorId(idProductoDTODatos);
            
            ProductoDTONegocios prodcutoDTONegocios 
                    = new ProductoDTONegocios(
                            new IdEntidadGenericoNegocios(productoDTODatos.getId().getId()), 
                            productoDTODatos.getNombre(),
                            productoDTODatos.getVariedad(),
                            productoDTODatos.getDescripcion(),
                            productoDTODatos.getMilesSemillas(),
                            productoDTODatos.getPrecio(),
                            productoDTODatos.getPesoKg(),
                            productoDTODatos.getDireccionImagen(),
                            new IdEntidadGenericoNegocios(productoDTODatos.getIdProveedor().getId()));


            return prodcutoDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
    }
    
    @Override
    public boolean existePorId(IdProductoDTONegocios idProductoDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException{
        
        IdProductoDTODatos idProductoDTODatos = new IdProductoDTODatos(new IdEntidadGenericoDatos(idProductoDTO.getIdProducto().getId()));
        
        try {
            return administradorMongodb.existeProductoPorId(idProductoDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
           throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregar(ProductoDTONegocios productoDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        ProductoDTODatos productoDTODatos = new ProductoDTODatos(
                productoDTONegocios.getNombre(),
                productoDTONegocios.getVariedad(),
                productoDTONegocios.getDescripcion(),
                productoDTONegocios.getMilesSemillas(),
                productoDTONegocios.getPrecio(),
                productoDTONegocios.getPesoKg(),
                productoDTONegocios.getDireccionImagen(),
                new IdEntidadGenericoDatos(productoDTONegocios.getIdProveedor().getId()));
        
        try {
            administradorMongodb.agregarProducto(productoDTODatos);
            
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
    public void agregar(Collection<ProductoDTONegocios> productos) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException{
        
        List<ProductoDTODatos> listaProductosAgregar = new LinkedList<>();
        
        for(ProductoDTONegocios productoDTONegocios: productos){
            
            ProductoDTODatos productoDTODatos = new ProductoDTODatos(
                productoDTONegocios.getNombre(),
                productoDTONegocios.getVariedad(),
                productoDTONegocios.getDescripcion(),
                productoDTONegocios.getMilesSemillas(),
                productoDTONegocios.getPrecio(),
                productoDTONegocios.getPesoKg(),
                productoDTONegocios.getDireccionImagen(),
                new IdEntidadGenericoDatos(productoDTONegocios.getIdProveedor().getId()));
            
            listaProductosAgregar.add(productoDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionProductos(listaProductosAgregar);
            
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
    public List<ProductoDTONegocios> recuperarTodos() {
        List<ProductoDTONegocios> listaProductosDTONegocios = new LinkedList<>();

        List<ProductoDTODatos> listaProductosDTODatos = administradorMongodb.recuperarTodosProductos();

        for(ProductoDTODatos productoDTODatos: listaProductosDTODatos){

            listaProductosDTONegocios.add(
                    new ProductoDTONegocios(
                            new IdEntidadGenericoNegocios(productoDTODatos.getId().getId()), 
                            productoDTODatos.getNombre(),
                            productoDTODatos.getVariedad(),
                            productoDTODatos.getDescripcion(),
                            productoDTODatos.getMilesSemillas(),
                            productoDTODatos.getPrecio(),
                            productoDTODatos.getPesoKg(),
                            productoDTODatos.getDireccionImagen(),
                            new IdEntidadGenericoNegocios(productoDTODatos.getIdProveedor().getId())
                    )
            );
        }

        return listaProductosDTONegocios;
    }

}
