
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


public interface IAdministradorMongodb {
    
    public abstract CarritoComprasDTODatos recuperarCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos)
            throws FormatoInvalidoIdConversionException,
            RegistroInexistenteException,
            ParametroNuloException;
    
    public abstract boolean existeCarritoComprasPorId(IdCarritoComprasDTODatos idCarritoComprasDTODatos)
            throws ParametroNuloException,
            FormatoInvalidoIdConversionException;
    
    public abstract CarritoComprasDTODatos actualizarCarritoCompras(ActualizacionCarritoComprasDTODatos actualizacionCarritoComprasDTODatos)
            throws AgregarInformacionNulaException,
            FormatoInvalidoIdConversionException,
            FormatoInvalidoIdConversionException,
            RegistroInexistenteException;
    
    public abstract void agregarCarritoCompras(CarritoComprasDTODatos carritoCompras) 
            throws AgregarInformacionNulaException,
            FormatoInvalidoIdConversionException,
            RegistroInexistenteException;
    
    public abstract void agregarColeccionCarritosCompra(Collection<CarritoComprasDTODatos> carritosCompras)
            throws AgregarInformacionNulaException, 
            FormatoInvalidoIdConversionException,
            RegistroInexistenteException;
    
    public abstract List<CarritoComprasDTODatos> recuperarTodosCarritoCompras();
    
}
