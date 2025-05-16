package edu.student.itson.dissof.megazarl.configuracion;

import edu.student.itson.dissof.megazarl.configuracion.excepciones.ConfiguracionArchivoNoExiste;
import edu.student.itson.dissof.megazarl.configuracion.ini.ConfiguracionIni;
import edu.student.itson.dissof.megazarl.configuracion.ini.ParserIni;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum ConfiguracionApp {
    INSTANCIA;

    private static final String RUTA_POR_DEFECTO = "megazarl.config.ini";
    private static final String CONTENIDO_CONFIGURACION_POR_DEFECTO = """
            [fuentes]
            * = memoria
            """;

    private ConfiguracionFuentes fuentes;
    private IOException excepcionInicializacion;
    private boolean inicializado = false;

    public void inicializar() throws IOException {
        inicializar(System.getenv().getOrDefault("MEGAZARL_RUTA_CONFIGURACION", RUTA_POR_DEFECTO));
    }

    public void inicializar(String rutaArchivo) throws IOException {
        if (inicializado) {
            return;
        }

        if (excepcionInicializacion != null) {
            throw excepcionInicializacion;
        }

        try {
            asegurarseConfiguracion(rutaArchivo);
            ConfiguracionIni configuracionIni = ParserIni.parseArchivo(rutaArchivo);
            fuentes = procesarFuentes(configuracionIni);
            inicializado = true;
        } catch (IOException e) {
            excepcionInicializacion = e;
            throw e;
        }
    }

    private void asegurarseConfiguracion(String rutaArchivo) throws IOException {
        Path path = Paths.get(rutaArchivo);

        if (!Files.exists(path)) {
            if (!rutaArchivo.equals(RUTA_POR_DEFECTO)) {
                throw new ConfiguracionArchivoNoExiste(rutaArchivo);
            }
            System.out.println("No existe un archivo de configuración. Se creará uno automáticamente.");
            crearArchivoConfiguracion(RUTA_POR_DEFECTO);
            System.out.println("Archivo de configuración por defecto creado.");
        } else if (!Files.isRegularFile(path)) {
            throw new ConfiguracionArchivoNoExiste("La ruta proporcionada (" + rutaArchivo + ") no parece ser un archivo");
        }
    }

    private void crearArchivoConfiguracion(String rutaArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(CONTENIDO_CONFIGURACION_POR_DEFECTO);
        }
    }

    private ConfiguracionFuentes procesarFuentes(ConfiguracionIni configuracion) {
        ConfiguracionFuentesIntermedia fuentes = new ConfiguracionFuentesIntermedia();

        String todos = configuracion.obtenerString("fuentes", "*");
        if (todos != null) {
            fuentes.todos(FuenteDatos.de(todos));
        }
        String cliente = configuracion.obtenerString("fuentes", "cliente");
        if (cliente != null) {
            fuentes.cliente = FuenteDatos.de(cliente);
        }
        String paqueteria = configuracion.obtenerString("fuentes", "paqueteria");
        if (paqueteria != null) {
            fuentes.paqueteria = FuenteDatos.de(paqueteria);
        }
        String producto = configuracion.obtenerString("fuentes", "producto");
        if (producto != null) {
            fuentes.producto = FuenteDatos.de(producto);
        }
        String productoInventario = configuracion.obtenerString("fuentes", "productoInventario");
        if (productoInventario != null) {
            fuentes.productoInventario = FuenteDatos.de(productoInventario);
        }
        String carritoCompras = configuracion.obtenerString("fuentes", "carritoCompras");
        if (carritoCompras != null) {
            fuentes.carritoCompras = FuenteDatos.de(carritoCompras);
        }
        String pedido = configuracion.obtenerString("fuentes", "pedido");
        if (pedido != null) {
            fuentes.pedido = FuenteDatos.de(pedido);
        }
        String sucursal = configuracion.obtenerString("fuentes", "sucursal");
        if (sucursal != null) {
            fuentes.sucursal = FuenteDatos.de(sucursal);
        }
        String proveedor = configuracion.obtenerString("fuentes", "proveedor");
        if (proveedor != null) {
            fuentes.proveedor = FuenteDatos.de(proveedor);
        }   
        String direccion = configuracion.obtenerString("fuentes", "direccion");
        if (direccion != null) {
            fuentes.direccion = FuenteDatos.de(direccion);
        }
        String productoCarrito = configuracion.obtenerString("fuentes", "productoCarrito");
        if (productoCarrito != null) {
            fuentes.productoCarrito = FuenteDatos.de(direccion);
        }
        String coordinadorLogistica = configuracion.obtenerString("fuentes", "coordinadorLogistica");
        if (coordinadorLogistica != null) {
            fuentes.coordinadorLogistica = FuenteDatos.de(coordinadorLogistica);

        }
        String gerenteVentas = configuracion.obtenerString("fuentes", "gerenteVentas");
        if(gerenteVentas != null){
            fuentes.direccion = FuenteDatos.de(gerenteVentas);
            
        }
        String auxiliarVentas = configuracion.obtenerString("fuentes", "auxiliarVentas");
        if(auxiliarVentas != null){
            fuentes.direccion = FuenteDatos.de(auxiliarVentas);
            
        }
        String productoPedido = configuracion.obtenerString("fuentes", "productoPedido");
        if (productoPedido != null) {
            fuentes.productoPedido = FuenteDatos.de(productoPedido);
        }

        return new ConfiguracionFuentes(
                fuentes.cliente,
                fuentes.paqueteria,
                fuentes.producto,
                fuentes.productoInventario,
                fuentes.carritoCompras,
                fuentes.pedido,
                fuentes.sucursal,
                fuentes.proveedor,
                fuentes.direccion,
                fuentes.productoCarrito,
                fuentes.coordinadorLogistica,
                fuentes.gerenteVentas,
                fuentes.auxiliarVentas,
                fuentes.productoPedido

        );
    }

    public ConfiguracionFuentes fuentes() {
        if (!inicializado) {
            if (excepcionInicializacion != null) {
                throw new RuntimeException("Error previo al inicializar la configuración", excepcionInicializacion);
            }
            throw new IllegalStateException("La configuración no ha sido inicializada. Llama a ConfiguracionApp.INSTANCIA.inicializar() en el método main.");
        }
        return fuentes;
    }
}