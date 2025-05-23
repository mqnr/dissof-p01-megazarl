package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.List;

/**
 * InformacionDerivadaCPDireccionEnvioDTONegocios.java

 Clase que representa un objeto de transferencia de datos que contiene
 la información derivada del código postal de una dirección de envío,
 incluyendo colonia, ciudad y estado, que no es ingresada directamente
 por el usuario sino obtenida a partir del código postal.
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
public class InformacionDerivadaCPDireccionEnvioDTONegocios {
    private List<String> colonias;
    private String ciudad;
    private String estado;

    public InformacionDerivadaCPDireccionEnvioDTONegocios(List<String> colonias, String ciudad, String estado) {
        this.colonias = colonias;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public List<String> getColonias() {
        return colonias;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }
}
