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
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class AdministradorPaqueterias implements IAdministradorPaqueterias {
    
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorProveedores administradorProveedores;
    private final IAdministradorSucursales administradorSucursales;
    private final IAdministradorDirecciones administradorDirecciones;
    private final IAdministradorMapas administradorMapas;
    
    private final DireccionDTO DIRECCION_DEFECTO_PAQUETERIA;
    
    public AdministradorPaqueterias(
            IAdministradorClientes administradorClientes,
            IAdministradorSucursales administradorSucursales,
            IAdministradorProveedores administradorProveedores,
            IAdministradorDirecciones administradorDirecciones,
            IAdministradorMapas administradorMapas,
            DireccionDTO direccionDefectoPaqueteria){
        
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProveedores = administradorProveedores;
        this.administradorMapas = administradorMapas;
        this.administradorDirecciones = administradorDirecciones;
        this.DIRECCION_DEFECTO_PAQUETERIA = direccionDefectoPaqueteria;
        
    }

    @Override
    public PaqueteriaDTO obtenerPaqueteria(IdPaqueteriaDTO idPaqueteriaDTO) {
        return Paqueteria.recuperarPorId(idPaqueteriaDTO);
    }

    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        return Paqueteria.stream().map(
                paqueteria ->
                        new InformacionSeleccionPaqueteriaDTO(
                                paqueteria.getId(),
                                paqueteria.getDireccionImagenPaqueteria()
                        )
        ).toList();
    }

    @Override
    public float obtenerCostoEnvioSucursalMatriz(InformacionEnvioProductoSucursalMatrizDTO informacionEnvioProductoSucursalMatrizDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdSucursalInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida la paqueteria
        Long idPaqueteria = informacionEnvioProductoSucursalMatrizDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTO(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTO paqueteria = obtenerPaqueteria(new IdPaqueteriaDTO(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida la sucursal
        Long idSucursal = informacionEnvioProductoSucursalMatrizDTO.getIdSucursal();
        
        if(!administradorSucursales.validarSucursal(new IdSucursalDTO(idSucursal))){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTO sucursal = administradorSucursales.obtenerSucursal(new IdSucursalDTO(idSucursal));
        
        if(sucursal == null){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoSucursalMatrizDTO.getPesoKgTotal();
        
        // Se valida el ID de la dirección de la sucursal
        IdDireccionDTO idDireccionSucursal = sucursal.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionSucursal)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la sucursal es inválido.");
        }
        
        DireccionDTO direccionSucursal = administradorDirecciones.obtenerDireccion(idDireccionSucursal);
        
        if(direccionSucursal == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la sucursal es inválido.");
        }
        
        // Se valida el ID de la dirección de Matriz
        IdDireccionDTO idDireccionMatriz = sucursalMatriz.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTO direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
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
    public float obtenerCostoEnvioMatrizCliente(InformacionEnvioProductoMatrizClienteDTO informacionEnvioProductoMatrizClienteDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdClienteInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida la paqueteria
        Long idPaqueteria = informacionEnvioProductoMatrizClienteDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTO(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTO paqueteria = obtenerPaqueteria(new IdPaqueteriaDTO(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        Long idCliente = informacionEnvioProductoMatrizClienteDTO.getIdCliente();
        
        if(!administradorClientes.validarCliente(new IdClienteDTO(idCliente))){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = administradorClientes.obtenerCliente(new IdClienteDTO(idCliente));
        
        if(cliente == null){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoMatrizClienteDTO.getPesoKgTotal();
        
        // Se valida de la dirección de envío el ID del cliente
        IdDireccionDTO idDireccionEnvioCliente = cliente.getIdDireccionEnvio();
        
        if(!administradorDirecciones.validarDireccion(idDireccionEnvioCliente)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de envío del cliente es inválido.");
        }
        
        DireccionDTO direccionEnvioCliente = administradorDirecciones.obtenerDireccion(idDireccionEnvioCliente);
        
        if(direccionEnvioCliente == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de envío del cliente es inválido.");
        }
        
        // Se valida el ID de la dirección de la Matriz
        IdDireccionDTO idDireccionMatriz = sucursalMatriz.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTO direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
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
    public float obtenerCostoEnvioProveedorMatriz(InformacionEnvioProductoProveedorMatrizDTO informacionEnvioProductoProveedorMatrizDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida la paqueteria
        Long idPaqueteria = informacionEnvioProductoProveedorMatrizDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTO(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTO paqueteria = obtenerPaqueteria(new IdPaqueteriaDTO(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        Long idProveedor = informacionEnvioProductoProveedorMatrizDTO.getIdProveedor();
        
        if(!administradorProveedores.validarProveedor(new IdProveedorDTO(idProveedor))){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        ProveedorDTO proveedor = administradorProveedores.obtenerProveedor(new IdProveedorDTO(idProveedor));
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoProveedorMatrizDTO.getPesoKgTotal();
        
        // Se valida de la dirección del ID del proveedor
        IdDireccionDTO idDireccionProveedor = proveedor.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionProveedor)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        DireccionDTO direccionProveedor = administradorDirecciones.obtenerDireccion(idDireccionProveedor);
        
        if(direccionProveedor == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        // Se valida el ID de la dirección de la Matriz
        IdDireccionDTO idDireccionMatriz = sucursalMatriz.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTO direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        // Se valida el ID de la dirección de la paqueteria
        IdDireccionDTO idDireccionPaqueteria = paqueteria.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionPaqueteria)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
        }
        
        DireccionDTO direccionPaqueteria = administradorDirecciones.obtenerDireccion(idDireccionPaqueteria);
        
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
    public Float obtenerTiempoEnvioMatrizEstimado(IdProveedorDTO idProveedorDTO) 
            throws PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException{
        
        // Se valida el ID de proveedor.
        if(!administradorProveedores.validarProveedor(idProveedorDTO)){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        ProveedorDTO proveedor = administradorProveedores.obtenerProveedor(idProveedorDTO);
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        // Se valida de la dirección del ID del proveedor
        IdDireccionDTO idDireccionProveedor = proveedor.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionProveedor)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        DireccionDTO direccionProveedor = administradorDirecciones.obtenerDireccion(idDireccionProveedor);
        
        if(direccionProveedor == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección del proveedor es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        // Se valida el ID de la dirección de la Matriz
        IdDireccionDTO idDireccionMatriz = sucursalMatriz.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionMatriz)){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        DireccionDTO direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionMatriz);
        
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
        
        List<PaqueteriaDTO> listaPaqueterias = Paqueteria.recuperarTodos();
        
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
            
            for(PaqueteriaDTO paqueteria: listaPaqueterias){
            
                // Se valida el ID de la dirección de la paqueteria
                IdDireccionDTO idDireccionPaqueteria = paqueteria.getIdDireccion();
                
                if(!administradorDirecciones.validarDireccion(idDireccionPaqueteria)){
                    throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
                }

                DireccionDTO direccionPaqueteria = administradorDirecciones.obtenerDireccion(idDireccionPaqueteria);

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
    private static final Logger LOG = Logger.getLogger(AdministradorPaqueterias.class.getName());
    
    @Override
    public boolean validarPaqueteria(IdPaqueteriaDTO idPaqueteriaDTO) {
        
        if(idPaqueteriaDTO == null || idPaqueteriaDTO.getIdPaqueteria() == null || !Paqueteria.existePorId(idPaqueteriaDTO)){
            return false;
        }
        
        return true;
    }

}
