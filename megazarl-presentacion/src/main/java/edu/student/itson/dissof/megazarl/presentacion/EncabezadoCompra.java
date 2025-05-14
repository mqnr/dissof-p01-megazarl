package edu.student.itson.dissof.megazarl.presentacion;


import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.presentacion.utils.ButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class EncabezadoCompra extends JPanel {

    private IVista vistaPadre;
    private JPanel panelFila1;
    private JPanel panelFila2;
    private JPanel panelImagenNombreUsuario;
    private JPanel panelLogotipo;
    private JPanel panelCarrito;
    private JPanel panelDireccion;
    private JPanel panelBusqueda;
    private JPanel panelSalir;

    private JPanel panelImagenNombreUsuario2;
    private JPanel panelLogotipo2;
    private JPanel panelCarrito2;
    private JPanel panelDireccion2;
    private JPanel panelBusqueda2;
    private JPanel panelSalir2;

    private JLabel etqNombreUsuario;
    private JLabel etqImagenUsuario;

    private JLabel etqLogotipoEmpresa;
    
    private JLabel direccionUbicacion;

    private JTextField campoBusquedaProductos;
    
    private Color COLOR_FONDO = new Color(78, 122, 64);
    
    private Color COLOR_BOTONES = new Color(255, 247, 190);
    
    private final int ANCHO_LOGOTIPO_EMPRESA = 220;
    private final int ALTO_LOGOTIPO_EMPRESA = 100;
    
    private final int ANCHO_BOTON_CARRITO = 100;
    private final int ALTO_BOTON_CARRITO = 40;
    
    private final int ANCHO_BOTON_ACTUALIZAR_DIRECCION = 170;
    private final int ALTO_BOTON_ACTUALIZAR_DIRECCION = 40;
    
    private final int ANCHO_BOTON_BUSCAR = 70;
    private final int ALTO_BOTON_BUSCAR = 30;
    
    private final int ANCHO_BOTON_SALIR = 80;
    private final int ALTO_BOTON_SALIR = 30;

    private final int MARGEN_VERTICAL_COMPONENTES = 40;
    
    private final Font FUENTE_BOTONES = new Font("Segoe UI Emoji", Font.BOLD, 16);
    
    private final String EMOJI_CARRITO = new String(Character.toChars(0x1F6D2));
    private final String EMOJI_LUPA = new String(Character.toChars(0x1F50D));
    
    private Long idCliente;
    
    JButton btnActualizarDireccionEnvio;
    JButton btnCarritoCompras;
    JButton botonBusqueda;
    JButton btnSalir;

    private ControlCompra control;

    public EncabezadoCompra(ControlCompra control, Long idCliente, IVista vistaPadre) {
        this.control = control;
        this.idCliente = idCliente;
        this.vistaPadre = vistaPadre;
        this.initCompoents();
    }

    private void initCompoents() {
        this.setBackground(COLOR_FONDO);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panelFila1 = new JPanel();
        panelFila2 = new JPanel();
        panelFila1.setLayout(new GridLayout(1, 3));
        panelFila2.setLayout(new GridLayout(1, 3));
        panelFila1.setOpaque(false);
        panelFila2.setOpaque(false);
        this.add(panelFila1);
        this.add(panelFila2);

        panelImagenNombreUsuario = new JPanel();
        panelLogotipo = new JPanel();
        panelCarrito = new JPanel();
        panelDireccion = new JPanel();
        panelBusqueda = new JPanel();
        panelSalir = new JPanel();

        panelImagenNombreUsuario.setOpaque(false);
        panelLogotipo.setOpaque(false);
        panelCarrito.setOpaque(false);
        panelDireccion.setOpaque(false);
        panelBusqueda.setOpaque(false);
        panelSalir.setOpaque(false);

        panelImagenNombreUsuario.setLayout(new BoxLayout(panelImagenNombreUsuario, BoxLayout.Y_AXIS));
        panelLogotipo.setLayout(new BoxLayout(panelLogotipo, BoxLayout.Y_AXIS));
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        panelDireccion.setLayout(new BoxLayout(panelDireccion, BoxLayout.Y_AXIS));
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.Y_AXIS));

        panelImagenNombreUsuario.add(Box.createVerticalStrut(MARGEN_VERTICAL_COMPONENTES));
        panelCarrito.add(Box.createVerticalStrut(MARGEN_VERTICAL_COMPONENTES));

        panelFila1.add(panelImagenNombreUsuario);
        panelFila1.add(panelLogotipo);
        panelFila1.add(panelCarrito);
        panelFila2.add(panelDireccion);
        panelFila2.add(panelBusqueda);
        panelFila2.add(panelSalir);

        panelImagenNombreUsuario2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogotipo2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelDireccion2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBusqueda2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCarrito2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSalir2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelImagenNombreUsuario2.setOpaque(false);
        panelLogotipo2.setOpaque(false);
        panelDireccion2.setOpaque(false);
        panelCarrito2.setOpaque(false);
        panelBusqueda2.setOpaque(false);
        panelSalir2.setOpaque(false);

        panelImagenNombreUsuario.add(panelImagenNombreUsuario2);
        panelLogotipo.add(panelLogotipo2);
        panelDireccion.add(panelDireccion2);
        panelBusqueda.add(panelBusqueda2);
        panelCarrito.add(panelCarrito2);
        panelSalir.add(panelSalir2);

        panelCarrito2.add(cargarBtnCarrito());
        
        // Imagen e icono de usuario
        ImageIcon iconoUsuario = new ImageIcon(this.getClass().getResource("/usuarioIcono.png"));
        Image imagenUsuario = iconoUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        ImageIcon nuevoInconoUsuario = new ImageIcon(imagenUsuario);

        etqImagenUsuario = new JLabel(nuevoInconoUsuario);
        etqNombreUsuario = new JLabel();
        Font fuenteEtqNombreUsuario = new Font("Arial", Font.BOLD, 20);
        etqNombreUsuario.setFont(fuenteEtqNombreUsuario);
        etqNombreUsuario.setForeground(Color.WHITE);

        panelImagenNombreUsuario2.add(etqImagenUsuario);
        panelImagenNombreUsuario2.add(etqNombreUsuario);

        // Logotipo de empresa
        ImageIcon iconoLogoEmpresa = new ImageIcon(this.getClass().getResource("/logotipoEmpresa.png"));
        Image imagenLogoEmpresa = iconoLogoEmpresa.getImage().getScaledInstance(ANCHO_LOGOTIPO_EMPRESA, ALTO_LOGOTIPO_EMPRESA, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoLogoEmpresa = new ImageIcon(imagenLogoEmpresa);

        etqLogotipoEmpresa = new JLabel(nuevoIconoLogoEmpresa);

        panelLogotipo2.add(etqLogotipoEmpresa);

        // Botón para cambiar de dirección de envío:
        JPanel panelUbicacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUbicacion.setOpaque(false);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setOpaque(false);

        panelDireccion2.add(cargarBtnActualizarDireccionEnvio());
        
        JLabel tituloUbicacion = new JLabel("Ubicación de envío");
        tituloUbicacion.setForeground(Color.WHITE);
        tituloUbicacion.setFont(new Font("Arial", Font.BOLD, 12));
        tituloUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        
        direccionUbicacion = new JLabel();
        direccionUbicacion.setForeground(Color.WHITE);
        direccionUbicacion.setFont(new Font("Arial", Font.PLAIN, 10));
        direccionUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        tituloUbicacion.setForeground(Color.BLACK);
        direccionUbicacion.setForeground(Color.BLACK);
        
        panelTexto.add(tituloUbicacion);
        panelTexto.add(direccionUbicacion);
        
        btnActualizarDireccionEnvio.add(panelTexto);
        
        // Campo de texto para búsqueda:
        campoBusquedaProductos = new JTextField();

        Font fuenteCampoBusqueda = new Font("Arial", Font.PLAIN, 20);

        campoBusquedaProductos.setFont(fuenteCampoBusqueda);

        campoBusquedaProductos.setColumns(16);
        
        panelBusqueda2.add(campoBusquedaProductos);
        
        panelBusqueda2.add(cargarBtnBuscar());
        
        panelSalir2.add(cargarBtnSalir());

    }

    private JButton cargarBtnCarrito() {
        // Botón de Carrito de Compras:    
        btnCarritoCompras = new ButtonBuilder()
                .withText(EMOJI_CARRITO)
                .withFont(FUENTE_BOTONES)
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_CARRITO, ALTO_BOTON_CARRITO)
                .onClick(e -> control.mostrarCarritoCompras(idCliente, vistaPadre))
                .build();
        
        return btnCarritoCompras;

    }
    
    private JButton cargarBtnActualizarDireccionEnvio() {
        
        btnActualizarDireccionEnvio = new ButtonBuilder()
                .withFont(FUENTE_BOTONES)
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_ACTUALIZAR_DIRECCION, ALTO_BOTON_ACTUALIZAR_DIRECCION)
                .onClick(e -> control.mostrarActualizacionDireccionEnvio(idCliente, vistaPadre))
                .build();
        
        return btnActualizarDireccionEnvio;

    }
    
    public void ocultarBtnActualizarDireccionEnvio() {
        btnActualizarDireccionEnvio.setVisible(false);
    }
    
    private JButton cargarBtnSalir(){

        btnSalir = new ButtonBuilder()
                .withText("Salir")
                .withFont(FUENTE_BOTONES)
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_SALIR, ALTO_BOTON_SALIR)
                .onClick(e -> control.finalizarCasoUso(vistaPadre))
                .build();

        return btnSalir;
    }
    
    private JButton cargarBtnBuscar(){
        
        botonBusqueda = new ButtonBuilder()
                .withText("Buscar " + EMOJI_LUPA)
                .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 12))
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_BUSCAR, ALTO_BOTON_BUSCAR)
                .withEmptyMargin()
                .onClick(e -> control.mostrarProductosBusquedaNombre(campoBusquedaProductos.getText()))
                .build();
        
        
        return botonBusqueda;
    }

    public void mostrarBarraBusqueda(){
        panelBusqueda2.setVisible(true);
        botonBusqueda.setVisible(true);
        campoBusquedaProductos.setText("");
    }
        
    public void ocultarBarraBusqueda(){
        panelBusqueda2.setVisible(false);
        botonBusqueda.setVisible(false);
    }
    
    public String getTextoCampoBusqueda() {
        return this.campoBusquedaProductos.getText();
    }
    
    public void mostrarDireccionCliente(){
        
        String[] datosDireccionCliente = control.recuperarDatosDireccionCliente(idCliente);
        String direccionCliente  = "Calle " 
                + datosDireccionCliente[0] + ", #" 
                + datosDireccionCliente[1] + ", " 
                + datosDireccionCliente[2] + ", CP." 
                + datosDireccionCliente[3]; 
        
        direccionUbicacion.setText(direccionCliente);
        
        btnActualizarDireccionEnvio.setVisible(true);
    }
    
    public void ocultarDireccionCliente(){
        btnActualizarDireccionEnvio.setVisible(false);
    }

    public void mostrarNombreApellidoCliente() {
        String[] datosApellidoNombreCliente = this.control.obtenerNombreApellidoCliente(this.idCliente);
        String nombreApellidoCliente = datosApellidoNombreCliente[0] + " " + datosApellidoNombreCliente[1];
        this.etqNombreUsuario.setText(nombreApellidoCliente);
    }
    
    public void mostrarBtnNumeroCarritoCompras() {
        int cantidadProductosCarrito = control.obtenerNumeroProductosCarrito(idCliente);
        btnCarritoCompras.setVisible(true);
        btnCarritoCompras.setText(cantidadProductosCarrito + "  " + EMOJI_CARRITO);
    }
    
    public void ocultarBtnNumeroCarritoCompras() {
        btnCarritoCompras.setVisible(false);
    }
    
    public void ocultarBtnSalir() {
        btnSalir.setVisible(false);
    }

}
