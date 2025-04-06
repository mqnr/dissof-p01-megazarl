package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.dto.*;
import edu.student.itson.dissof.megazarl.objetosnegocio.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class AdministradorPedidos implements IAdministradorPedidos {
    private final IAdministradorProductos administradorProductos;
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorPaqueterias administradorPaqueterias;
    private final IAdministradorSucursales administradorSucursales;

    public AdministradorPedidos(
            IAdministradorProductos administradorProductos,
            IAdministradorClientes administradorClientes,
            IAdministradorPaqueterias administradorPaqueterias,
            IAdministradorSucursales administradorSucursales) {

        this.administradorProductos = administradorProductos;
        this.administradorClientes = administradorClientes;
        this.administradorPaqueterias = administradorPaqueterias;
        this.administradorSucursales = administradorSucursales;
    }

    @Override
    public float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaIdProductoCantidadCarritoDTO)  {
        return 4.5F;
    }

    @Override
    public float calcularCostoEnvioProductosPaqueteria(InformacionCalculoCostoPedidoDTO informacionCalculoCostoPedidoDTO)
            throws PedidosIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            ProductosIdProductoInvalidoException{

        Integer idCliente = informacionCalculoCostoPedidoDTO.getIdCliente();

        if (!administradorClientes.validarIdCliente(idCliente)) {
            throw new PedidosIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        Integer idPaqueteria = informacionCalculoCostoPedidoDTO.getIdPaqueteria();

        if (!administradorPaqueterias.validarPaqueteria(idPaqueteria)) {
            throw new PedidosIdPaqueteriaInvalidoException("El ID de paqueteria: " + idPaqueteria + " es inválido.");
        }

        HashMap<Integer, Integer> mapaProductosCantidades = informacionCalculoCostoPedidoDTO.getMapaProductosCantidades();

        for (Integer idProducto: mapaProductosCantidades.keySet()) {
            if (!administradorProductos.validarProducto(idProducto)) {
                throw new PedidosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
            }
        }

        try {
            InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO
                    = administradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(idCliente);

            String codigoPostalCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
            String calleCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
            String numeroCliente =  informacionNoDerivadaCPDireccionEnvioDTO.getNumero();

            DireccionMatrizDTO direccionMatrizDTO = administradorSucursales.obtenerDireccionMatriz();

            String codigoPostalMatriz = direccionMatrizDTO.getCodigoPostalMatriz();
            String calleMatriz = direccionMatrizDTO.getCalleMatriz();
            String numeroMatriz = direccionMatrizDTO.getNumeroMatriz();

            Float costoTotalEnvioProductos = 0F;

            for (Integer idProducto: mapaProductosCantidades.keySet()) {
                int cantidadProductoSolicitado = mapaProductosCantidades.get(idProducto);

                ProductoON producto = administradorProductos.obtenerProducto(idProducto);

                List<ProductoInventarioON> listaProductosInventario = producto.getListaProductoInventario();

                Collections.sort(listaProductosInventario,
                        (p1, p2) -> Float.compare(p1.getSucursal().getTiempoMatriz(), p2.getSucursal().getTiempoMatriz()));

                Double pesoKgProductoInventario = producto.getPesoKg();

                for (ProductoInventarioON productoInventario: listaProductosInventario) {
                    Float tiempoEnvioMatrizHorasProductoInventario = productoInventario.getSucursal().getTiempoMatriz();

                    if (tiempoEnvioMatrizHorasProductoInventario >= 0) {
                        DireccionClientePesoTiempoProductoInventarioDTO direccionClientePesoTiempoProductoInventarioDTO =
                                new DireccionClientePesoTiempoProductoInventarioDTO(
                                        idPaqueteria,
                                        codigoPostalCliente,
                                        calleCliente,
                                        numeroCliente,
                                        codigoPostalMatriz,
                                        calleMatriz,
                                        numeroMatriz,
                                        pesoKgProductoInventario,
                                        tiempoEnvioMatrizHorasProductoInventario);
                        costoTotalEnvioProductos +=
                                administradorPaqueterias.obtenerCostoEnvioProducto(direccionClientePesoTiempoProductoInventarioDTO);

                        cantidadProductoSolicitado--;
                    }

                    if (cantidadProductoSolicitado <= 0) {
                        break;
                    }
                }
            }
            return costoTotalEnvioProductos;

        } catch (ClientesIdClienteInvalidoException ex) {
            throw new PedidosIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

    }

    @Override
    public boolean realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO)
            throws PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException{

        Integer idCliente = informacionCrearPedidoDTO.getIdCliente();

        if (!administradorClientes.validarIdCliente(idCliente)) {
            throw new PedidosIdProductoInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        Integer idPaqueteria = informacionCrearPedidoDTO.getIdPaqueteria();

        if (!administradorPaqueterias.validarPaqueteria(idCliente)) {
            throw new PedidosIdProductoInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }

        PaqueteriaON paqueteria = administradorPaqueterias.obtenerPaqueteria(idPaqueteria);

        if (paqueteria == null) {
            throw new PedidosIdProductoInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }

        HashMap<Integer,Integer> mapaProductosCantidadPedido = informacionCrearPedidoDTO.getMapaIdsProductosCantidad();

        for (Integer idProducto: mapaProductosCantidadPedido.keySet()) {
            if (!administradorProductos.validarProducto(idProducto)) {
                throw new PedidosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
            }
        }

        // Se obtienen los productos que se tomarán del inventario.
        List<ProductoInventarioON> productosSolicitados = new LinkedList<>();

        for (Integer idProducto: mapaProductosCantidadPedido.keySet()) {
            int cantidadProductoSolicitado = mapaProductosCantidadPedido.get(idProducto);

            ProductoON producto = administradorProductos.obtenerProducto(idProducto);

            List<ProductoInventarioON> listaProductosInventario = producto.getListaProductoInventario();

            Collections.sort(listaProductosInventario,
                    (p1, p2) -> Float.compare(p1.getSucursal().getTiempoMatriz(), p2.getSucursal().getTiempoMatriz()));

            for (ProductoInventarioON productoInventario: listaProductosInventario) {
                productosSolicitados.add(productoInventario);

                cantidadProductoSolicitado--;

                if (cantidadProductoSolicitado <= 0) {
                    break;
                }
            }
        }

        // HashMap que contiene a cada ProductoInventario junto con un valor Booleano que
        // indica si ya llegó a la matriz.
        HashMap<ProductoInventarioON, Boolean> mapaProductosRequeridos = new HashMap<>();

        for (ProductoInventarioON productoInventario: productosSolicitados) {
            mapaProductosRequeridos.put(productoInventario, false);
        }

        // Se crea el objeto Pedido.
        PedidoON pedidoSucursalMatriz = new PedidoON(
                mapaProductosRequeridos,
                paqueteria,
                EstadoPedido.PENDIENTE
        );

        return true;
    }
}
