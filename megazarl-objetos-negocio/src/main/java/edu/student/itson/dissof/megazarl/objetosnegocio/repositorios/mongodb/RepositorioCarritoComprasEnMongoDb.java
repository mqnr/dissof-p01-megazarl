
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.ProductoCarritoDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasIdsRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.AgregarInformacionNulaNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
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
    public CarritoComprasIdsRelacionesDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idCarritoComprasDTONegocios.getIdCarritoCompras().getId());
        
        IdCarritoComprasDTODatos idCarritoComprasDTODatos = new IdCarritoComprasDTODatos(idEntidadGenericoDatos);
        
        CarritoComprasDTODatos carritoComprasDTODatos;
        try {
            
            carritoComprasDTODatos = administradorMongodb.recuperarCarritoComprasPorId(idCarritoComprasDTODatos);
            
             List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = new LinkedList<>();
        
            for(ProductoCarritoDTODatos productoCarritoDTODatos: carritoComprasDTODatos.getProductosCarrito()){
                listaIdsProductosCarrito.add(new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()));
            }

            CarritoComprasIdsRelacionesDTONegocios carritoComprasIdsRelacionesDTONegocios 
                    = new CarritoComprasIdsRelacionesDTONegocios(
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getId().getId()), 
                            carritoComprasDTODatos.getEsVigente(),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdCliente().getId()),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdPaqueteria().getId()),
                            listaIdsProductosCarrito);

            return carritoComprasIdsRelacionesDTONegocios;
        
        } catch (FormatoInvalidoIdConversionException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
       
        
    }

    @Override
    public boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTONegocios) 
            throws ParametroNuloException,
            ParametroNuloException,
            FormatoInvalidoIdConversionException {
        
        IdCarritoComprasDTODatos idCarritoComprasDTODatos = 
                new IdCarritoComprasDTODatos(
                        new IdEntidadGenericoDatos(idCarritoComprasDTONegocios.getIdCarritoCompras().getId()));
        
        return administradorMongodb.existeCarritoComprasPorId(idCarritoComprasDTODatos);
        
    }
    
    @Override
    public CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTONegocios) 
            throws AgregarInformacionNulaNegocioException,
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException{
        
        ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos 
                = new ActualizacionCarritoComprasDTODatos(new IdEntidadGenericoDatos(actualizacionCarritoComprasDTONegocios.getId()));
        
        actualizacionCarritoComprasDTODatos.setEsVigente(actualizacionCarritoComprasDTONegocios.getEsVigente());

        actualizacionCarritoComprasDTODatos.setIdPaqueteria(new IdEntidadGenericoDatos(actualizacionCarritoComprasDTONegocios.getIdPaqueteria()));
        
        CarritoComprasDTODatos carritoComprasDTODatos;
        try {
            
            carritoComprasDTODatos = administradorMongodb.actualizarCarritoCompras(actualizacionCarritoComprasDTODatos);
            
            List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = new LinkedList<>();
        
            for(ProductoCarritoDTODatos productoCarritoDTODatos: carritoComprasDTODatos.getProductosCarrito()){
                listaIdsProductosCarrito.add(new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()));
            }

            CarritoComprasIdsRelacionesDTONegocios carritoComprasIdsRelacionesDTONegocios 
                    = new CarritoComprasIdsRelacionesDTONegocios(
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getId()), 
                            carritoComprasDTODatos.getEsVigente(), 
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdCliente().getId()), 
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdPaqueteria().getId()),
                            listaIdsProductosCarrito);

            return carritoComprasIdsRelacionesDTONegocios;
        
        } catch (AgregarInformacionNulaException ex) {
            throw new AgregarInformacionNulaNegocioException(ex.getMessage());
        } catch (FormatoInvalidoIdConversionException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        }
        
        
        
    }

    @Override
    public void agregar(CarritoComprasDTONegocios carritoCompras) 
            throws AgregarInformacionNulaNegocioException, 
            FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException{
       
        List<ProductoCarritoDTODatos> listaProductosCarritoDTODatos = new LinkedList<>();
        
        for(ProductoCarritoDTONegocios productoCarritoDTONegocios: 
                ((CarritoComprasDatosCompletosRelacionesDTONegocios)carritoCompras).getProductosCarrito()){
        
            listaProductosCarritoDTODatos.add(
                    new ProductoCarritoDTODatos(
                            productoCarritoDTONegocios.getCantidad(),
                            new IdEntidadGenericoDatos(productoCarritoDTONegocios.getIdProducto())));   
            
        }
        
        CarritoComprasDTODatos carritoComprasDTODatos = new CarritoComprasDTODatos(
                carritoCompras.getEsVigente(), 
                new IdEntidadGenericoDatos(carritoCompras.getIdCliente().getId()),
                new IdEntidadGenericoDatos(carritoCompras.getIdPaqueteria().getId()), 
                listaProductosCarritoDTODatos);
        
        try {
            administradorMongodb.agregarCarritoCompras(carritoComprasDTODatos);
        } catch (AgregarInformacionNulaException ex) {
            throw new AgregarInformacionNulaNegocioException(ex.getMessage());
        } catch (FormatoInvalidoIdConversionException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregar(Collection<CarritoComprasDTONegocios> carritosCompras) 
            throws AgregarInformacionNulaNegocioException,
            FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException{
        
        List<CarritoComprasDTODatos> listaCarritosCompraAgregar = new LinkedList<>();
        
        for(CarritoComprasDTONegocios carritoCompras: carritosCompras){
            List<ProductoCarritoDTODatos> listaProductosCarritoDTODatos = new LinkedList<>();

            for(ProductoCarritoDTONegocios productoCarritoDTONegocios: 
                    ((CarritoComprasDatosCompletosRelacionesDTONegocios)carritoCompras).getProductosCarrito()){

                listaProductosCarritoDTODatos.add(
                        new ProductoCarritoDTODatos(
                                productoCarritoDTONegocios.getCantidad(),
                                new IdEntidadGenericoDatos(productoCarritoDTONegocios.getIdProducto())));   

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
        } catch (AgregarInformacionNulaException ex) {
            throw new AgregarInformacionNulaNegocioException(ex.getMessage());
        } catch (FormatoInvalidoIdConversionException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        }
            
    }

    @Override
    public List<CarritoComprasDTONegocios> recuperarTodos() {
        
        List<CarritoComprasDTONegocios> listaCarritosComprasDTONegocios = new LinkedList<>();
        
        List<CarritoComprasDTODatos> listaCarritosComprasDTODatos = administradorMongodb.recuperarTodosCarritoCompras();
        
        for(CarritoComprasDTODatos carritoComprasDTODatos: listaCarritosComprasDTODatos){
            
            List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = new LinkedList<>();
        
            for(ProductoCarritoDTODatos productoCarritoDTODatos: carritoComprasDTODatos.getProductosCarrito()){
                listaIdsProductosCarrito.add(new IdEntidadGenericoNegocios(productoCarritoDTODatos.getIdProducto().getId()));
            }
        
            listaCarritosComprasDTONegocios.add(
                    new CarritoComprasIdsRelacionesDTONegocios(
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getId().getId()),
                            carritoComprasDTODatos.getEsVigente(),
                            new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdCliente().getId()),
                            carritoComprasDTODatos.getIdPaqueteria() == null ? null : new IdEntidadGenericoNegocios(carritoComprasDTODatos.getIdPaqueteria().getId()),
                            listaIdsProductosCarrito
            ));
            
        }
        
        return listaCarritosComprasDTONegocios;
        
    }
    
}
