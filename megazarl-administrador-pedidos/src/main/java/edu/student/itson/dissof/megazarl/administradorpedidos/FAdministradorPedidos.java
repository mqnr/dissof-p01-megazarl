package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCalculoTiempoPreparacionDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import java.util.List;

public class FAdministradorPedidos implements IAdministradorPedidos {

    IAdministradorProductos administradorProductos;

    public FAdministradorPedidos(IAdministradorProductos administradorProductos) {
        this.administradorProductos = administradorProductos;
    }

    @Override
    public float obtenerTiempoEstimadoPreparacion(List<InformacionProductoCalculoTiempoPreparacionDTO> listaInformacionProductoCalculoTiempoPreparacionDTO) {
        float timepoMayorEnvioMatriz = 0;

        for (InformacionProductoCalculoTiempoPreparacionDTO informacionProductoCalculoTiempoPreparacionDTO : listaInformacionProductoCalculoTiempoPreparacionDTO) {
            Integer idProducto = informacionProductoCalculoTiempoPreparacionDTO.getIdProducto();
            Integer cantidad = informacionProductoCalculoTiempoPreparacionDTO.getCantidad();
            List<ProductoInventario> listaProductoInventario = administradorProductos.obtenerListaProductoInventario(idProducto);

            listaProductoInventario.sort((lp1, lp2) -> Float.compare(lp1.getTiempoEnvioMatriz(), lp2.getTiempoEnvioMatriz()));

            int indiceProductoMasLejanoNecesario = cantidad - 1;

            float tiempoEnvioMatrizProducto = listaProductoInventario.get(indiceProductoMasLejanoNecesario).getTiempoEnvioMatriz();

            if (tiempoEnvioMatrizProducto > timepoMayorEnvioMatriz) {
                timepoMayorEnvioMatriz = tiempoEnvioMatrizProducto;
            }
        }

        return timepoMayorEnvioMatriz;
    }
}
