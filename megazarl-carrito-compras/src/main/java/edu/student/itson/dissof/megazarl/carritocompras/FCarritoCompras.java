package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCalculoTiempoPreparacionDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FCarritoCompras implements ICarritoCompras {

    private IAdministradorClientes administradorClientes;
    private IAdministradorProductos administradorProductos;
    private IAdministradorPedidos administradorPedidos;
    private HashMap<Integer, HashMap<Integer, Integer>> mapaClientesProductos = new HashMap<>();
    private Double montoMinimoEnvioGratuito;

    public FCarritoCompras(Double montoMinimoEnvioGratuito, IAdministradorClientes administradorClientes, IAdministradorProductos administradorProductos,
            IAdministradorPedidos administradorPedidos) {
        this.montoMinimoEnvioGratuito = montoMinimoEnvioGratuito;
        this.administradorClientes = administradorClientes;
        this.administradorProductos = administradorProductos;
        this.administradorPedidos = administradorPedidos;
    }

    @Override
    public MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito() {
        MontoMinimoEnvioGratuitoDTO montoMinimoEnvioGratuitoDTO
                = new MontoMinimoEnvioGratuitoDTO(this.montoMinimoEnvioGratuito, this.obtenerMontoTotalCarrito());

        return montoMinimoEnvioGratuitoDTO;
    }

    @Override
    public List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente) {

        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();

        Boolean clienteTieneCarrito = mapaClientesProductos.containsKey(idCliente);

        if (clienteTieneCarrito) {
            HashMap<Integer, Integer> mapaProductosCliente = mapaClientesProductos.get(idCliente);

            for (HashMap.Entry<Integer, Integer> productoCantidad : mapaProductosCliente.entrySet()) {
                listaInformacionProductoCarritoDTO.add(
                        new InformacionProductoCarritoDTO(
                                productoCantidad.getKey(),
                                productoCantidad.getValue()
                        )
                );
            }
        }

        return listaInformacionProductoCarritoDTO;
    }

    @Override
    public void agregarProducto(Integer idCliente, Integer idProducto, int cantidad) {
        mapaClientesProductos.putIfAbsent(idCliente, new HashMap<>());

        int cantidadPrevia = 0;
        if (mapaClientesProductos.get(idCliente).get(idProducto) != null) {
            cantidadPrevia = mapaClientesProductos.get(idCliente).get(idProducto);
        }

        mapaClientesProductos.get(idCliente).put(idProducto, cantidadPrevia + cantidad);
    }

    @Override
    public void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad) {
        if (mapaClientesProductos.get(idCliente) != null && !mapaClientesProductos.get(idCliente).isEmpty()) {
            int cantidadPrevia = mapaClientesProductos.get(idCliente).get(idProducto);

            if (cantidad <= cantidadPrevia) {
                mapaClientesProductos.get(idCliente).put(idProducto, cantidadPrevia - cantidad);
            }
        }
    }

    @Override
    public int obtenerNumeroProductos(Integer idCliente) throws ClienteNoExisteException {
        if (!administradorClientes.validarIdCliente(idCliente)) {
            throw new ClienteNoExisteException("El Id del cliente es inválido.");
        }

        int numeroProductos = 0;

        if (mapaClientesProductos.get(idCliente) == null || mapaClientesProductos.get(idCliente).isEmpty()) {
            return 0;
        }

        HashMap<Integer, Integer> mapaProductosCliente = mapaClientesProductos.get(idCliente);

        for (HashMap.Entry<Integer, Integer> productoCantidad : mapaProductosCliente.entrySet()) {
            numeroProductos += productoCantidad.getValue();
        }

        return numeroProductos;
    }

    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente) {
        // Añadir excepción
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO = null;

        Boolean clienteTieneCarrito = mapaClientesProductos.containsKey(idCliente);

        if (clienteTieneCarrito) {
            List<InformacionProductoCalculoTiempoPreparacionDTO> listaInformacionProductoCalculoTiempoPreparacionDTO = new LinkedList<>();
            HashMap<Integer, Integer> mapaProductosCliente = mapaClientesProductos.get(idCliente);

            for (HashMap.Entry<Integer, Integer> productoCantidad : mapaProductosCliente.entrySet()) {

                listaInformacionProductoCalculoTiempoPreparacionDTO.add(
                        new InformacionProductoCalculoTiempoPreparacionDTO(
                                productoCantidad.getKey(),
                                productoCantidad.getValue()
                        )
                );
            }

            double horasEstimadas = administradorPedidos.obtenerTiempoEstimadoPreparacion(listaInformacionProductoCalculoTiempoPreparacionDTO);

            double diasEstimados = horasEstimadas / 24;

            tiempoEstimadoPreparacionEnvioPedidoDTO
                    = new TiempoEstimadoPreparacionEnvioPedidoDTO((int) Math.ceil(diasEstimados), (int) Math.ceil(diasEstimados + 5));
        }

        return tiempoEstimadoPreparacionEnvioPedidoDTO;
    }

    private double obtenerMontoTotalCarrito() {
        double montoTotal = 0;
        for (HashMap.Entry<Integer, HashMap<Integer, Integer>> mapaProductosCliente : mapaClientesProductos.entrySet()) {
            for (HashMap.Entry<Integer, Integer> productoCantidad : mapaProductosCliente.getValue().entrySet()) {
                double costoProducto = administradorProductos.obtenerCostoProducto(productoCantidad.getKey());
                double costoTotalProducto = costoProducto * productoCantidad.getValue();
                montoTotal += costoTotalProducto;
            }
        }

        return montoTotal;
    }
}
