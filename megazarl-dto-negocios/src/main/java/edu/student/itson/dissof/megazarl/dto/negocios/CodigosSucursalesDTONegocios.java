package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;

/**
 * CodigosSucursalesDTONegocios.java

 Clase que representa un objeto de transferencia de datos que contiene
 una lista de códigos identificadores de sucursales en el sistema.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class CodigosSucursalesDTONegocios {
    private List<IdEntidadGenericoNegocios> codigosSucursales;

    public CodigosSucursalesDTONegocios(List<IdEntidadGenericoNegocios> codigosSucursales) {
        this.codigosSucursales = codigosSucursales;
    }

    public List<IdEntidadGenericoNegocios> getCodigosSucursales() {
        return codigosSucursales;
    }
}
