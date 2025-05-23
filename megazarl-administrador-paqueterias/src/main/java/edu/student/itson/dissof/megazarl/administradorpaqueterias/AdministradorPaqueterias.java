package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import java.util.LinkedList;

import java.util.List;

class AdministradorPaqueterias implements IAdministradorPaqueterias {
    
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorProveedores administradorProveedores;
    private final IAdministradorSucursales administradorSucursales;
    private final IAdministradorDirecciones administradorDirecciones;
    private final IAdministradorMapas administradorMapas;
    
    private final DireccionDTONegocios DIRECCION_DEFECTO_PAQUETERIA;
    
    public AdministradorPaqueterias(
            IAdministradorClientes administradorClientes,
            IAdministradorSucursales administradorSucursales,
            IAdministradorProveedores administradorProveedores,
            IAdministradorDirecciones administradorDirecciones,
            IAdministradorMapas administradorMapas,
            DireccionDTONegocios direccionDefectoPaqueteria){
        
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProveedores = administradorProveedores;
        this.administradorMapas = administradorMapas;
        this.administradorDirecciones = administradorDirecciones;
        this.DIRECCION_DEFECTO_PAQUETERIA = direccionDefectoPaqueteria;
        
    }

    @Override
    public PaqueteriaDTONegocios obtenerPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTONegocios) {
        return Paqueteria.recuperarPorId(idPaqueteriaDTONegocios);
    }

    @Override
    public List<InformacionSeleccionPaqueteriaDTONegocios> obtenerPaqueterias() {
        
        List<InformacionSeleccionPaqueteriaDTONegocios> listaInformacionSeleccionPaqueteriaDTONegocios = new LinkedList<>();
        
        for(PaqueteriaDTONegocios paqueteria : Paqueteria.recuperarTodos()){
            listaInformacionSeleccionPaqueteriaDTONegocios.add(
                    new InformacionSeleccionPaqueteriaDTONegocios(
                                paqueteria.getId(),
                                paqueteria.getDireccionImagenPaqueteria()
                    )   
            );
        }
        
        return listaInformacionSeleccionPaqueteriaDTONegocios;
    }

    @Override
    public float obtenerCostoEnvioSucursalMatriz(InformacionEnvioProductoSucursalMatrizDTONegocios informacionEnvioProductoSucursalMatrizDTONegocios)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdSucursalInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida la paqueteria
        IdEntidadGenericoNegocios idPaqueteria = informacionEnvioProductoSucursalMatrizDTONegocios.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTONegocios paqueteria = obtenerPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida la sucursal
        IdEntidadGenericoNegocios idSucursal = informacionEnvioProductoSucursalMatrizDTONegocios.getIdSucursal();
        
        if(!administradorSucursales.validarSucursal(new IdSucursalDTONegocios(idSucursal))){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTONegocios sucursal = administradorSucursales.obtenerSucursal(new IdSucursalDTONegocios(idSucursal));
        
        if(sucursal == null){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoSucursalMatrizDTONegocios.getPesoKgTotal();
        
        // Se valida el ID de la dirección de la sucursal
        IdDireccionDTONegocios idDireccionSucursal = new IdDireccionDTONegocios(sucursal.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la sucursal es inválido.");
        }
        
        DireccionDTONegocios direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la sucursal es inválido.");
        }
        
        // Se valida el ID de la dirección de Matriz
        IdDireccionDTONegocios idDireccionMatriz = new IdDireccionDTONegocios(sucursalMatriz.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTONegocios direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        
        String numeroSucursal = direccionSucursal.getNumero();
        String calleSucursal = direccionSucursal.getCalle();
        String coloniaSucursal = direccionSucursal.getColonia();
        String codigoPostalSucursal = direccionSucursal.getCodigoPostal();
        
        String numeroMatriz = direccionMatriz.getNumero();
        String calleMatriz = direccionMatriz.getCalle();
        String coloniaMatriz = direccionMatriz.getColonia();
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroSucursal, 
                        calleSucursal,
                        coloniaSucursal,
                        codigoPostalSucursal,
                        numeroMatriz, 
                        calleMatriz, 
                        coloniaMatriz,
                        codigoPostalMatriz);
        
        TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
        
        float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();

        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoTraslado);
    }
    
    @Override
    public float obtenerCostoEnvioMatrizCliente(InformacionEnvioProductoMatrizClienteDTONegocios informacionEnvioProductoMatrizClienteDTONegocios)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdClienteInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida la paqueteria
        IdEntidadGenericoNegocios idPaqueteria = informacionEnvioProductoMatrizClienteDTONegocios.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTONegocios paqueteria = obtenerPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        IdEntidadGenericoNegocios idCliente = informacionEnvioProductoMatrizClienteDTONegocios.getIdCliente();
        
        if(!administradorClientes.validarCliente(new IdClienteDTONegocios(idCliente))){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios cliente = administradorClientes.obtenerCliente(new IdClienteDTONegocios(idCliente));
        
        if(cliente == null){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoMatrizClienteDTONegocios.getPesoKgTotal();
        
        // Se valida de la dirección de envío el ID del cliente
        IdDireccionDTONegocios idDireccionEnvioCliente = new IdDireccionDTONegocios(cliente.getIdDireccionEnvio());
        
        if(!administradorDirecciones.validarDireccion(idDireccionEnvioCliente)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de envío del cliente es inválido.");
        }
        
        DireccionDTONegocios direccionEnvioCliente = administradorDirecciones.obtenerDireccion(idDireccionEnvioCliente);
        
        if(direccionEnvioCliente == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de envío del cliente es inválido.");
        }
        
        // Se valida el ID de la dirección de la Matriz
        IdDireccionDTONegocios idDireccionMatriz = new IdDireccionDTONegocios(sucursalMatriz.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTONegocios direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        String numeroCliente = direccionEnvioCliente.getNumero();
        String calleCliente = direccionEnvioCliente.getCalle();
        String coloniaCliente = direccionEnvioCliente.getColonia();
        String codigoPostalCliente = direccionEnvioCliente.getCodigoPostal();
        
        String numeroMatriz = direccionMatriz.getNumero();
        String calleMatriz = direccionMatriz.getCalle();
        String coloniaMatriz = direccionMatriz.getColonia();
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroCliente, 
                        calleCliente,
                        coloniaCliente,
                        codigoPostalCliente,
                        numeroMatriz, 
                        calleMatriz, 
                        coloniaMatriz,
                        codigoPostalMatriz);
        
        TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
        
        float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();

        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoTraslado);
    }
    
    
    @Override
    public float obtenerCostoEnvioProveedorMatriz(InformacionEnvioProductoProveedorMatrizDTONegocios informacionEnvioProductoProveedorMatrizDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida la paqueteria
        IdEntidadGenericoNegocios idPaqueteria = informacionEnvioProductoProveedorMatrizDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTONegocios paqueteria = obtenerPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        IdEntidadGenericoNegocios idProveedor = informacionEnvioProductoProveedorMatrizDTO.getIdProveedor();
        
        if(!administradorProveedores.validarProveedor(new IdProveedorDTONegocios(idProveedor))){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        ProveedorDTONegocios proveedor = administradorProveedores.obtenerProveedor(new IdProveedorDTONegocios(idProveedor));
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoProveedorMatrizDTO.getPesoKgTotal();
        
        // Se valida de la dirección del ID del proveedor
        IdDireccionDTONegocios idDireccionProveedor = new IdDireccionDTONegocios(proveedor.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionProveedor)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        DireccionDTONegocios direccionProveedor = administradorDirecciones.obtenerDireccion(idDireccionProveedor);
        
        if(direccionProveedor == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        // Se valida el ID de la dirección de la Matriz
        IdDireccionDTONegocios idDireccionMatriz = new IdDireccionDTONegocios(sucursalMatriz.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTONegocios direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        // Se valida el ID de la dirección de la paqueteria
        IdDireccionDTONegocios idDireccionPaqueteria = new IdDireccionDTONegocios(paqueteria.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionPaqueteria)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
        }
        
        DireccionDTONegocios direccionPaqueteria = administradorDirecciones.obtenerDireccion(idDireccionPaqueteria);
        
        if(direccionPaqueteria == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
        }
        
        String numeroProveedor = direccionProveedor.getNumero();
        String calleProveedor = direccionProveedor.getCalle();
        String coloniaProveedor = direccionProveedor.getColonia();
        String codigoPostalProveedor = direccionProveedor.getCodigoPostal();
        
        String numeroMatriz = direccionMatriz.getNumero();
        String calleMatriz = direccionMatriz.getCalle();
        String coloniaMatriz = direccionMatriz.getColonia();
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoPaqueteriaProveedorDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroProveedor, 
                        calleProveedor,
                        coloniaProveedor,
                        codigoPostalProveedor,
                        numeroMatriz, 
                        calleMatriz, 
                        coloniaMatriz,
                        codigoPostalMatriz);
        
        TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoPaqueteriaProveedorDTO);
        
        float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();
        
        String numeroPaqueteria = direccionPaqueteria.getNumero();
        String callePaqueteria = direccionPaqueteria.getCalle();
        String coloniaPaqueteria = direccionPaqueteria.getColonia();
        String codigoPostalPaqueteria = direccionPaqueteria.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoProveedorMatrizDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroPaqueteria, 
                        callePaqueteria,
                        coloniaPaqueteria,
                        codigoPostalPaqueteria,
                        numeroProveedor, 
                        calleProveedor, 
                        coloniaProveedor,
                        codigoPostalProveedor);
        
        tiempoTraslado +=
                administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoProveedorMatrizDTO).getTiempoTraslado();
        
        
        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoTraslado);
    }
    
    /**
     * Implementación del método obtenerTiempoEnvioMatrizEstimado(), de la interfaz
     * {@link IAdministradorPaqueterias}.
     * @param idProveedorPaqueteriaDTO Objeto IdProveedorPaqueteriaDTO que contiene el ID del
     * proveedor que ofrece los productos y la paquetería que envía los productos.
     * @return Objeto Float que representa el tiempo máximo de envío a Matriz en horas
     * de las paqueterías registradas, null si no existen paqueterías registradas.
     */
    @Override
    public Float obtenerTiempoEnvioMatrizEstimado(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida el ID de proveedor.
        if(!administradorProveedores.validarProveedor(idProveedorDTONegocios)){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        ProveedorDTONegocios proveedor = administradorProveedores.obtenerProveedor(idProveedorDTONegocios);
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        // Se valida de la dirección del ID del proveedor
        IdDireccionDTONegocios idDireccionProveedor = new IdDireccionDTONegocios(proveedor.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionProveedor)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        DireccionDTONegocios direccionProveedor = administradorDirecciones.obtenerDireccion(idDireccionProveedor);
        
        if(direccionProveedor == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        // Se valida el ID de la dirección de la Matriz
        IdDireccionDTONegocios idDireccionMatriz = new IdDireccionDTONegocios(sucursalMatriz.getIdDireccion());
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTONegocios direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        
        String codigoPostalProveedor = direccionProveedor.getCodigoPostal();
        String coloniaProveedor = direccionProveedor.getColonia();
        String calleProveedor = direccionProveedor.getCalle();
        String numeroProveedor = direccionProveedor.getNumero();
        
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        String coloniaMatriz = direccionMatriz.getColonia();
        String calleMatriz = direccionMatriz.getCalle();
        String numeroMatriz = direccionMatriz.getNumero();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoProveedorMatriz
                = new DatosTiempoTrasladoUbicacionesDTO(
                        numeroProveedor, 
                        calleProveedor, 
                        coloniaProveedor, 
                        codigoPostalProveedor, 
                        numeroMatriz,
                        calleMatriz, 
                        coloniaMatriz, 
                        codigoPostalMatriz);
                
        float tiempoTrasladoProveedorMatriz = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoProveedorMatriz).getTiempoTraslado();
        
        List<PaqueteriaDTONegocios> listaPaqueterias = Paqueteria.recuperarTodos();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoPaqueteriaProveedor;
        
        float tiempoTrasladoMaximoPaqueteriaProveedor = 0;
        
        if(listaPaqueterias.isEmpty()){
            String codigoPostalPaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getCodigoPostal();
            String coloniaPaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getColonia();
            String callePaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getCalle();
            String numeroPaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getNumero();
            
            datosTiempoTrasladoPaqueteriaProveedor 
                    = new DatosTiempoTrasladoUbicacionesDTO(
                            numeroPaqueteria, 
                            callePaqueteria,
                            coloniaPaqueteria,
                            codigoPostalPaqueteria,
                            numeroProveedor,
                            calleProveedor,
                            coloniaProveedor,
                            codigoPostalProveedor);
            
            float tiempoTrasladoPaqueteriaProveedor 
                    = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoPaqueteriaProveedor).getTiempoTraslado();
            
            tiempoTrasladoMaximoPaqueteriaProveedor = tiempoTrasladoPaqueteriaProveedor;
            
        } else{
            
            for(PaqueteriaDTONegocios paqueteria: listaPaqueterias){
            
                // Se valida el ID de la dirección de la paqueteria
                IdDireccionDTONegocios idDireccionPaqueteria = new IdDireccionDTONegocios(paqueteria.getIdDireccion());
                
                if(!administradorDirecciones.validarDireccion(idDireccionPaqueteria)){
                    throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
                }

                DireccionDTONegocios direccionPaqueteria = administradorDirecciones.obtenerDireccion(idDireccionPaqueteria);

                if(direccionPaqueteria == null){
                    throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
                }
        
                String codigoPostalPaqueteria = direccionPaqueteria.getCodigoPostal();
                String coloniaPaqueteria = direccionPaqueteria.getColonia();
                String callePaqueteria = direccionPaqueteria.getCalle();
                String numeroPaqueteria = direccionPaqueteria.getNumero();

                 datosTiempoTrasladoPaqueteriaProveedor 
                        = new DatosTiempoTrasladoUbicacionesDTO(
                                numeroPaqueteria, 
                                callePaqueteria,
                                coloniaPaqueteria,
                                codigoPostalPaqueteria,
                                numeroProveedor,
                                calleProveedor,
                                coloniaProveedor,
                                codigoPostalProveedor);

                float tiempoTrasladoPaqueteriaProveedor 
                        = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoPaqueteriaProveedor).getTiempoTraslado();

                

                if(tiempoTrasladoPaqueteriaProveedor > tiempoTrasladoMaximoPaqueteriaProveedor){
                    tiempoTrasladoMaximoPaqueteriaProveedor = tiempoTrasladoPaqueteriaProveedor;
                }
            
            }
            
        }
        
        return tiempoTrasladoMaximoPaqueteriaProveedor + tiempoTrasladoProveedorMatriz;
        
    }
    
    @Override
    public boolean validarPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTO) {
        
        if(idPaqueteriaDTO == null || idPaqueteriaDTO.getIdPaqueteria() == null || !Paqueteria.existePorId(idPaqueteriaDTO)){
            return false;
        }
        
        return true;
    }

}
