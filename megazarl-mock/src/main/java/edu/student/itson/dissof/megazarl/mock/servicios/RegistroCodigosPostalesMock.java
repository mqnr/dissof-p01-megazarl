package edu.student.itson.dissof.megazarl.mock.servicios;

import edu.student.itson.dissof.megazarl.dto.DetallesDerivadosDireccionDTO;
import edu.student.itson.dissof.megazarl.servicios.RegistroCodigosPostales;

public class RegistroCodigosPostalesMock implements RegistroCodigosPostales {
    @Override
    public DetallesDerivadosDireccionDTO obtenerDetalles(String codigoPostal) {
        return new DetallesDerivadosDireccionDTO("A", "B", "C");
    }
}
