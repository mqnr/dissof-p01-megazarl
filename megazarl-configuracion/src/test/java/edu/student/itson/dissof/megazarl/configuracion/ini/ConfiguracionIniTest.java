package edu.student.itson.dissof.megazarl.configuracion.ini;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ConfiguracionIniTest {

    @Test
    @DisplayName("El constructor debe crear una copia inmutable de los mapas")
    public void constructorDebeCrearCopiasInmutables() {
        // Preparar
        Map<String, String> seccionDB = new HashMap<>();
        seccionDB.put("host", "localhost");
        seccionDB.put("puerto", "5432");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", seccionDB);

        // Ejecutar
        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Verificar inmutabilidad
        assertThrows(UnsupportedOperationException.class, () ->
                config.secciones().put("nueva", Collections.emptyMap()));

        assertThrows(UnsupportedOperationException.class, () ->
                config.secciones().get("database").put("nueva", "valor"));

        // Modificar el mapa original no debe afectar al config
        seccionDB.put("nuevo", "valor");
        assertFalse(config.tieneClave("database", "nuevo"));
    }

    @Test
    @DisplayName("obtenerSeccion debe devolver el mapa de la sección especificada")
    public void obtenerSeccionDebeRetornarMapaSeccion() {
        // Preparar
        Map<String, String> seccionDB = new HashMap<>();
        seccionDB.put("host", "localhost");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", seccionDB);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        assertEquals("localhost", config.obtenerSeccion("database").get("host"));
        // Verificar insensibilidad a mayúsculas/minúsculas
        assertEquals("localhost", config.obtenerSeccion("DATABASE").get("host"));
        // Verificar mapa vacío para sección inexistente
        assertTrue(config.obtenerSeccion("inexistente").isEmpty());
    }

    @Test
    @DisplayName("tieneSeccion debe verificar correctamente si una sección existe")
    public void tieneSeccionDebeVerificarExistenciaSeccion() {
        // Preparar
        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", new HashMap<>());

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        assertTrue(config.tieneSeccion("database"));
        assertTrue(config.tieneSeccion("DATABASE")); // Insensible a mayúsculas/minúsculas
        assertFalse(config.tieneSeccion("inexistente"));
    }

    @Test
    @DisplayName("tieneClave debe verificar correctamente si una clave existe en una sección")
    public void tieneClaveDebeVerificarExistenciaClave() {
        // Preparar
        Map<String, String> seccionDB = new HashMap<>();
        seccionDB.put("host", "localhost");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", seccionDB);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        assertTrue(config.tieneClave("database", "host"));
        assertTrue(config.tieneClave("DATABASE", "HOST")); // Insensible a mayúsculas/minúsculas
        assertFalse(config.tieneClave("database", "inexistente"));
        assertFalse(config.tieneClave("inexistente", "clave"));
    }

    @Test
    @DisplayName("obtenerString debe retornar el valor string correcto")
    public void obtenerStringDebeRetornarValorCorrecto() {
        // Preparar
        Map<String, String> seccionDB = new HashMap<>();
        seccionDB.put("host", "localhost");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", seccionDB);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        assertEquals("localhost", config.obtenerString("database", "host"));
        assertEquals("localhost", config.obtenerString("DATABASE", "HOST")); // Insensible a mayúsculas/minúsculas
        assertNull(config.obtenerString("database", "inexistente"));
        assertEquals("valor_default", config.obtenerString("database", "inexistente", "valor_default"));
        assertNull(config.obtenerString("inexistente", "clave"));
        assertEquals("otro_default", config.obtenerString("inexistente", "clave", "otro_default"));
    }

    @Test
    @DisplayName("obtenerInt debe retornar el valor entero correcto")
    public void obtenerIntDebeRetornarValorCorrecto() {
        // Preparar
        Map<String, String> seccion = new HashMap<>();
        seccion.put("puerto", "5432");
        seccion.put("timeout", "no_es_numero");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", seccion);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        assertEquals(5432, config.obtenerInt("database", "puerto"));
        assertNull(config.obtenerInt("database", "inexistente"));
        assertEquals(3000, config.obtenerInt("database", "inexistente", 3000));
        assertNull(config.obtenerInt("database", "timeout")); // No es un número
        assertEquals(1000, config.obtenerInt("database", "timeout", 1000)); // No es un número, usa default
    }

    @Test
    @DisplayName("obtenerDouble debe retornar el valor double correcto")
    public void obtenerDoubleDebeRetornarValorCorrecto() {
        // Preparar
        Map<String, String> seccion = new HashMap<>();
        seccion.put("factor", "2.5");
        seccion.put("invalid", "no_es_numero");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("config", seccion);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        assertEquals(2.5, config.obtenerDouble("config", "factor"));
        assertNull(config.obtenerDouble("config", "inexistente"));
        assertEquals(1.5, config.obtenerDouble("config", "inexistente", 1.5));
        assertNull(config.obtenerDouble("config", "invalid")); // No es un número
        assertEquals(0.5, config.obtenerDouble("config", "invalid", 0.5)); // No es un número, usa default
    }

    @ParameterizedTest
    @DisplayName("obtenerBoolean debe reconocer diferentes valores booleanos")
    @CsvSource({
            "true, true",
            "TRUE, true",
            "yes, true",
            "si, true",
            "sí, true",
            "verdadero, true",
            "1, true",
            "false, false",
            "FALSE, false",
            "no, false",
            "falso, false",
            "0, false",
            "otro," // Null en valores no booleanos
    })
    public void obtenerBooleanDebeReconocerDiferentesValores(String valor, String esperado) {
        // Preparar
        Map<String, String> seccion = new HashMap<>();
        seccion.put("opcional", valor);

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("configuracion", seccion);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar y verificar
        Boolean valorEsperado = esperado == null ? null : Boolean.parseBoolean(esperado);
        assertEquals(valorEsperado, config.obtenerBoolean("configuracion", "opcional"));

        // Probar con valor por defecto
        Boolean valorPorDefecto = Boolean.TRUE;
        assertEquals(valorEsperado != null ? valorEsperado : valorPorDefecto,
                config.obtenerBoolean("configuracion", "opcional", valorPorDefecto));
    }

    @Test
    @DisplayName("toString debe generar representación correcta del archivo INI")
    public void toStringDebeGenerarRepresentacionIni() {
        // Preparar
        Map<String, String> seccionDB = new HashMap<>();
        seccionDB.put("host", "localhost");
        seccionDB.put("puerto", "5432");

        Map<String, String> seccionApp = new HashMap<>();
        seccionApp.put("nombre", "MiApp");
        seccionApp.put("version", "1.0");

        Map<String, Map<String, String>> secciones = new HashMap<>();
        secciones.put("database", seccionDB);
        secciones.put("aplicacion", seccionApp);

        ConfiguracionIni config = new ConfiguracionIni(secciones);

        // Ejecutar
        String resultado = config.toString();

        // Verificar: contiene ambas secciones con sus claves y valores
        assertTrue(resultado.contains("[database]"));
        assertTrue(resultado.contains("host = localhost"));
        assertTrue(resultado.contains("puerto = 5432"));

        assertTrue(resultado.contains("[aplicacion]"));
        assertTrue(resultado.contains("nombre = MiApp"));
        assertTrue(resultado.contains("version = 1.0"));
    }
}
