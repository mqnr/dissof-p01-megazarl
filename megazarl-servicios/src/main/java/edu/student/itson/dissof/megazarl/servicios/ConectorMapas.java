package edu.student.itson.dissof.megazarl.servicios;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * ConectorMapas.java
 *
 * Implementación de IConector que se encarga de comunicar el sistema con un
 * script Python externo encargado de calcular el tiempo de traslado entre
 * dos ubicaciones. Utiliza JSON como formato de intercambio de datos.
 *
 * Este conector convierte los datos internos del sistema a un formato esperado
 * por el script, y luego interpreta la respuesta para convertirla nuevamente
 * a un DTO reconocido por el sistema.
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
 */
public class ConectorMapas implements IConectorMapas {
    private final static String EJECUTABLE_PYTHON = "python3";

    private final String rutaScript;

    public ConectorMapas(String rutaScript) {
        this.rutaScript = rutaScript;
    }

    @Override
    public TiempoTrasladoDTO enviarDatosTiempoTrasladoUbicacionesDTO(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        Gson gson = new Gson();
        String entradaJson = gson.toJson(convertirDatosTiempoTrasladoUbicacionesDTO(datosTiempoTrasladoUbicacionesDTO));

        try {
            ProcessBuilder pb = new ProcessBuilder(EJECUTABLE_PYTHON, rutaScript);
            Process proceso = pb.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            writer.write(entradaJson);
            writer.newLine();
            writer.flush();

            String salidaJson = reader.readLine();

            proceso.waitFor();

            TiempoTrasladoDTO tiempoTrasladoDTO = gson.fromJson(salidaJson, TiempoTrasladoDTO.class);

            if (tiempoTrasladoDTO.tieneError()) {
                System.err.println("Error desde el script externo: " + tiempoTrasladoDTO.getError());
                return null;
            }

            return tiempoTrasladoDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    record PuntosJson(
            @SerializedName("codigo_postal_a")
            String codigoPostalA,
            @SerializedName("codigo_postal_b")
            String codigoPostalB
    ) {}

    private static PuntosJson convertirDatosTiempoTrasladoUbicacionesDTO(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        return new PuntosJson(
                datosTiempoTrasladoUbicacionesDTO.getCodigoPostalA(),
                datosTiempoTrasladoUbicacionesDTO.getCodigoPostalB()
        );
    }
}
