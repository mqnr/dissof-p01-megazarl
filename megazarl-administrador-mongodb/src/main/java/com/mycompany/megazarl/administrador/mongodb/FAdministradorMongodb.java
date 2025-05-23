
package com.mycompany.megazarl.administrador.mongodb;

import com.mycompany.megazarl.administrador.mongodb.excepciones.AgregarInformacionNulaException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import edu.student.itson.dissof.dto.datos.ActualizacionCarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.CarritoComprasDTODatos;
import edu.student.itson.dissof.dto.datos.IdCarritoComprasDTODatos;
import java.util.Collection;
import java.util.List;


public class FAdministradorMongodb implements IAdministradorMongodb{

    private final CarritoComprasDAO carritoComprasDAO;
    
    public FAdministradorMongodb(){
        carritoComprasDAO = new CarritoComprasDAO();
    }
    
    @Override
    public CarritoComprasDTODatos recuperarCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos)
            throws FormatoInvalidoIdConversionException,
            RegistroInexistenteException,
            ParametroNuloException{
        
        return carritoComprasDAO.recuperarPorId(idCarritoComprasDTODatos);
        
    }

    @Override
    public boolean existeCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos) 
            throws ParametroNuloException,
            FormatoInvalidoIdConversionException{
        
        return carritoComprasDAO.existePorId(idCarritoComprasDTODatos);
    }

    @Override
    public CarritoComprasDTODatos actualizarCarritoCompras(ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos) 
            throws AgregarInformacionNulaException,
            FormatoInvalidoIdConversionException,
            FormatoInvalidoIdConversionException,
            RegistroInexistenteException{
        
        return carritoComprasDAO.actualizar(actualizacionCarritoComprasDTODatos);
    }

    @Override
    public void agregarCarritoCompras(CarritoComprasDTODatos carritoCompras) 
            throws AgregarInformacionNulaException,
            FormatoInvalidoIdConversionException,
            RegistroInexistenteException{
        
        carritoComprasDAO.agregar(carritoCompras);
    }

    @Override
    public void agregarColeccionCarritosCompra(Collection<CarritoComprasDTODatos> carritosCompras) 
            throws AgregarInformacionNulaException, 
            FormatoInvalidoIdConversionException,
            RegistroInexistenteException{
        
       carritoComprasDAO.agregar(carritosCompras);
    }

    @Override
    public List<CarritoComprasDTODatos> recuperarTodosCarritoCompras() {
        
        return carritoComprasDAO.recuperarTodos();
    }
    
}
