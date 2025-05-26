
package edu.student.itson.dissof.megazarl.objetosnegocio;

import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCarritoCompras;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioCarritoComprasEnMongodb;
import java.util.Collection;
import java.util.List;


public class CarritoCompras {
    
    private static final RepositorioCarritoCompras repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().carritoCompras()) {
            case MEMORIA -> new RepositorioCarritoComprasEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static CarritoComprasDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idCarritoComprasDTO);
    }
    
    public static boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTO)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException,
            ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existePorId(idCarritoComprasDTO);
    }
    
    public static CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO)
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        return repositorio.actualizar(actualizacionCarritoComprasDTO);
    }
     
    public static void agregar(CarritoComprasDTONegocios carritoCompras) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        repositorio.agregar(carritoCompras);
    }
    
    public static void agregar(Collection<CarritoComprasDTONegocios> carritosCompras)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(carritosCompras);
    }
     
    public static List<CarritoComprasDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static ProductoCarritoDTONegocios recuperarProductoCarritoPorId(IdProductoCarritoDTONegocios idProductoCarritoDTONegocios)
            throws RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException{
        
        return repositorio.recuperarProductoCarritoPorId(idProductoCarritoDTONegocios);
    }
    
    public static boolean existeProductoCarritoPorId(IdProductoCarritoDTONegocios idProductoCarritoDTONegocios)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existeProductoCarritoPorId(idProductoCarritoDTONegocios);
    }
    
    public static ProductoCarritoDTONegocios actualizarProductoCarrito(ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTONegocios)
            throws RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException, 
            ValorParametroInvalidoNegocioException{
            
        return repositorio.actualizarProductoCarrito(actualizacionProductoCarritoDTONegocios);

    }
    
    public static void removerProductoCarritoPorId(IdProductoCarritoDTONegocios idProdutoCarritoDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException{
        
        repositorio.removerProductoCarritoPorId(idProdutoCarritoDTONegocios);
        
    }
    
    public static void agregarProductoCarrito(ProductoCarritoDTONegocios nuevoProductoCarritoNegocios)
            throws ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException{
        
        repositorio.agregarProductoCarrito(nuevoProductoCarritoNegocios);
        
    }
    
    public void agregarProductosCarrito(Collection<ProductoCarritoDTONegocios> nuevosProductosCarrito)  
            throws ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException{
        
        repositorio.agregarProductosCarrito(nuevosProductosCarrito);
        
    }
    
    public List<ProductoCarritoDTONegocios> recuperarTodosProductosCarrito(){
        
        return repositorio.recuperarTodosProductosCarrito();
        
    }

}
