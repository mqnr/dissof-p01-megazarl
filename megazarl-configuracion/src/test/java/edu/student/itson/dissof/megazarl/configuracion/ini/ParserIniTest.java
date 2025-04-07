package edu.student.itson.dissof.megazarl.configuracion.ini;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ParserIniTest {

    @Test
    @DisplayName("parseCadena debe parsear correctamente una cadena INI simple")
    public void parseCadenaDebeParsearIniSimple() throws IOException {
        // Preparar
        String contenido = "[seccion1]\nclave1 = valor1\nclave2 = valor2\n";

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertTrue(config.tieneSeccion("seccion1"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
        assertEquals("valor2", config.obtenerString("seccion1", "clave2"));
    }

    @Test
    @DisplayName("parseCadena debe manejar múltiples secciones")
    public void parseCadenaDebeManejarmultiplesSecciones() throws IOException {
        // Preparar
        String contenido =
                """
                        [seccion1]
                        clave1 = valor1
                        
                        [seccion2]
                        clave2 = valor2
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertTrue(config.tieneSeccion("seccion1"));
        assertTrue(config.tieneSeccion("seccion2"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
        assertEquals("valor2", config.obtenerString("seccion2", "clave2"));
    }

    @Test
    @DisplayName("parseCadena debe ignorar líneas de comentarios")
    public void parseCadenaDebeIgnorarLineasComentarios() throws IOException {
        // Preparar
        String contenido =
                """
                        ; Comentario de cabecera
                        # Otro comentario
                        [seccion1]
                        clave1 = valor1
                        # Comentario en medio
                        clave2 = valor2
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertTrue(config.tieneSeccion("seccion1"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
        assertEquals("valor2", config.obtenerString("seccion1", "clave2"));
    }

    @Test
    @DisplayName("El texto después de = se considera parte del valor, no un comentario")
    public void caracteresComentarioEnValoresSonParteDelValor() throws IOException {
        // Preparar
        String contenido =
                """
                        [seccion]
                        descripcion = Este no es un comentario; es parte del valor
                        url = http://ejemplo.com/ruta#fragmento
                        """; // El # no es comentario aquí

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertEquals("Este no es un comentario; es parte del valor",
                config.obtenerString("seccion", "descripcion"));
        assertEquals("http://ejemplo.com/ruta#fragmento",
                config.obtenerString("seccion", "url"));
    }

    @Test
    @DisplayName("parseCadena debe ignorar líneas vacías")
    public void parseCadenaDebeIgnorarLineasVacias() throws IOException {
        // Preparar
        String contenido =
                """
                        
                        [seccion1]
                        
                        clave1 = valor1
                        
                        clave2 = valor2
                        
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertTrue(config.tieneSeccion("seccion1"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
        assertEquals("valor2", config.obtenerString("seccion1", "clave2"));
    }

    @Test
    @DisplayName("parseCadena debe manejar espacios en blanco")
    public void parseCadenaDebeManejarespaciosEnBlanco() throws IOException {
        // Preparar
        String contenido =
                """
                          [  seccion1  ] \s
                          clave1  =  valor1 \s
                        clave2=valor2
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertTrue(config.tieneSeccion("seccion1"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
        assertEquals("valor2", config.obtenerString("seccion1", "clave2"));
    }

    @Test
    @DisplayName("parseCadena debe ser insensible a mayúsculas y minúsculas")
    public void parseCadenaDebeSerInsensibleAMayusculasMinusculas() throws IOException {
        // Preparar
        String contenido =
                """
                        [SECCION1]
                        CLAVE1 = valor1
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertTrue(config.tieneSeccion("seccion1"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
        // Verificar usando mayúsculas en la consulta
        assertEquals("valor1", config.obtenerString("SECCION1", "CLAVE1"));
    }

    @Test
    @DisplayName("parseCadena debe ignorar claves sin sección")
    public void parseCadenaDebeIgnorarClavesSinSeccion() throws IOException {
        // Preparar
        String contenido =
                """
                        clave_global = valor_global
                        [seccion1]
                        clave1 = valor1
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertFalse(config.tieneSeccion(""));
        assertTrue(config.tieneSeccion("seccion1"));
        assertEquals("valor1", config.obtenerString("seccion1", "clave1"));
    }

    @Test
    @DisplayName("parseCadena debe manejar contenido vacío")
    public void parseCadenaDebeManejarmanejarContenidoVacio() throws IOException {
        // Preparar
        String contenido = "";

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertNotNull(config);
        assertTrue(config.secciones().isEmpty());
    }

    @Test
    @DisplayName("parseArchivo debe leer y parsear un archivo correctamente")
    public void parseArchivoDebe(@TempDir Path tempDir) throws IOException {
        // Preparar: Crear un archivo temporal con contenido INI
        Path archivo = tempDir.resolve("config.ini");
        String contenido =
                """
                        [database]
                        host = localhost
                        port = 5432
                        
                        [app]
                        name = TestApp
                        version = 1.0
                        """;

        Files.writeString(archivo, contenido);

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseArchivo(archivo.toString());

        // Verificar
        assertTrue(config.tieneSeccion("database"));
        assertTrue(config.tieneSeccion("app"));
        assertEquals("localhost", config.obtenerString("database", "host"));
        assertEquals(5432, config.obtenerInt("database", "port"));
        assertEquals("TestApp", config.obtenerString("app", "name"));
        assertEquals("1.0", config.obtenerString("app", "version"));
    }

    @Test
    @DisplayName("parseArchivo debe lanzar IOException cuando el archivo no existe")
    public void parseArchivoDebeLanzarIOExceptionCuandoArchivoNoExiste() {
        // Verificar que se lanza IOException para un archivo que no existe
        assertThrows(IOException.class, () ->
                ParserIni.parseArchivo("/ruta/inexistente/archivo.ini"));
    }

    @Test
    @DisplayName("parseCadena debe conservar valores con signos igual")
    public void parseCadenaDebeConservarValoresConSignosIgual() throws IOException {
        // Preparar
        String contenido =
                """
                        [seccion]
                        clave = valor con = en medio
                        url = http://ejemplo.com?param1=valor1&param2=valor2
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertEquals("valor con = en medio", config.obtenerString("seccion", "clave"));
        assertEquals("http://ejemplo.com?param1=valor1&param2=valor2", config.obtenerString("seccion", "url"));
    }

    @Test
    @DisplayName("parseCadena debe manejar distintos tipos de datos correctamente")
    public void parseCadenaDebeManejarmanejarDistintosTiposDeDatos() throws IOException {
        // Preparar
        String contenido =
                """
                        [datos]
                        entero = 42
                        decimal = 3.14
                        booleano = true
                        texto = Hola Mundo
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertEquals(42, config.obtenerInt("datos", "entero"));
        assertEquals(3.14, config.obtenerDouble("datos", "decimal"));
        assertTrue(config.obtenerBoolean("datos", "booleano"));
        assertEquals("Hola Mundo", config.obtenerString("datos", "texto"));
    }

    @Test
    @DisplayName("parseCadena debe sobrescribir claves duplicadas con el último valor")
    public void parseCadenaDebeSobrescribirClavesDuplicadas() throws IOException {
        // Preparar
        String contenido =
                """
                        [seccion]
                        clave = valor1
                        clave = valor2
                        """;

        // Ejecutar
        ConfiguracionIni config = ParserIni.parseCadena(contenido);

        // Verificar
        assertEquals("valor2", config.obtenerString("seccion", "clave"));
    }

    @Test
    @DisplayName("El parser debe rechazar secciones mal formadas")
    public void parseCadenaDebeRechazarSeccionesMalFormadas() {
        // Contenido INI con sección malformada (texto después del corchete de cierre)
        String contenido = """
                [seccion1] ; comentario inválido
                clave1 = valor1
                """;

        // Verificar que se lanza IllegalArgumentException al parsear
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ParserIni.parseCadena(contenido));

        // Verificar que el mensaje menciona la sección malformada
        assertTrue(exception.getMessage().contains("seccion1"));
    }

    @Test
    @DisplayName("El parser debe rechazar claves mal formadas")
    public void parseCadenaDebeRechazarClavesMalFormadas() {
        // Contenido INI con clave-valor malformado (sin signo igual)
        String contenido = """
                [seccion1]
                clave1 valor1
                """;

        // Verificar que se lanza IllegalArgumentException al parsear
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ParserIni.parseCadena(contenido));

        // Verificar que el mensaje menciona la línea problemática
        assertTrue(exception.getMessage().contains("clave1"));
    }
}
