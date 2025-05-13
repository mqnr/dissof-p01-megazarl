package edu.student.itson.dissof.megazarl.mapas;

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
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public final class ConectorMapas implements IConectorMapas, Closeable {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;

    public ConectorMapas(String host, int port) throws IOException {
        socket = new Socket(host, port);                // throws if worker down
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(),  StandardCharsets.UTF_8)
        );
        out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
        );
    }

    @Override
    public String calcularTiempoTraslado(String datosTiempoTrasladoUbicaciones) throws IOException {
        out.write(datosTiempoTrasladoUbicaciones);
        out.newLine();
        out.flush();

        return in.readLine();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
