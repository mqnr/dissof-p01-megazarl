package edu.student.itson.dissof.megazarl.servicios;

import edu.student.itson.dissof.megazarl.dto.DetallesDerivadosDireccionDTO;

public interface RegistroCodigosPostales {

    DetallesDerivadosDireccionDTO obtenerDetalles(String codigoPostal);
}
