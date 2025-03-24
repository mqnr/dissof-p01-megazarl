package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCalculoTiempoPreparacionDTO;
import java.util.List;

public class FAdministradorPedidos implements IAdministradorPedidos {

    IAdministradorProductos administradorProductos;

    public FAdministradorPedidos(IAdministradorProductos administradorProductos) {
        this.administradorProductos = administradorProductos;
    }

    @Override
    public float obtenerTiempoEstimadoPreparacion(List<InformacionProductoCalculoTiempoPreparacionDTO> listaInformacionProductoCalculoTiempoPreparacionDTO) {

        return 12.3f;
    }
}
