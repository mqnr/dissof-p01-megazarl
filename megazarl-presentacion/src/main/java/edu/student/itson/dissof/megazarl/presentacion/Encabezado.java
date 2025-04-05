package edu.student.itson.dissof.megazarl.presentacion;


import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.presentacion.utilgui.ButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class Encabezado extends JPanel {

    private IVista vistaPadre;
    private JPanel panelFila1;
    private JPanel panelFila2;
    private JPanel panelImagenNombreUsuario;
    private JPanel panelLogotipo;
    private JPanel panelBtnCarrito;
    private JPanel panelBtnDireccion;
    private JPanel panelBusqueda;
    private JPanel panelEspacio;

    private JPanel panelImagenNombreUsuario2;
    private JPanel panelLogotipo2;
    private JPanel panelBtnCarrito2;
    private JPanel panelBtnDireccion2;
    private JPanel panelBusqueda2;

    private JLabel etqNombreUsuario;
    private JLabel etqImagenUsuario;

    private JLabel etqLogotipoEmpresa;
    
    private JLabel direccionUbicacion;

    private JTextField campoBusquedaProductos;
    
    private Color COLOR_BTNS_CARRITO_DIRECCION = new Color(255, 247, 190);

    private final int MARGEN_VERTICAL_COMPONENTES = 40;
    private final String EMOJI_CARRITO = new String(Character.toChars(0x1F6D2));
    private final String EMOJI_LUPA = new String(Character.toChars(0x1F50D));

    private Integer idCliente;
    
    JButton btnActualizarDireccionEnvio;
    JButton btnCarritoCompras;
    JButton botonBusqueda;

    private ControlCompra control;

    public Encabezado(ControlCompra control, Integer idCliente, IVista vistaPadre) {
        this.control = control;
        this.idCliente = idCliente;
        this.vistaPadre = vistaPadre;
        this.initCompoents();
    }

    private void initCompoents() {
        this.setBackground(new Color(43, 189, 126));
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
        panelBtnCarrito = new JPanel();
        panelBtnDireccion = new JPanel();
        panelBusqueda = new JPanel();
        panelEspacio = new JPanel();

        panelImagenNombreUsuario.setOpaque(false);
        panelLogotipo.setOpaque(false);
        panelBtnCarrito.setOpaque(false);
        panelBtnDireccion.setOpaque(false);
        panelBusqueda.setOpaque(false);
        panelEspacio.setOpaque(false);

        panelImagenNombreUsuario.setLayout(new BoxLayout(panelImagenNombreUsuario, BoxLayout.Y_AXIS));
        panelLogotipo.setLayout(new BoxLayout(panelLogotipo, BoxLayout.Y_AXIS));
        panelBtnCarrito.setLayout(new BoxLayout(panelBtnCarrito, BoxLayout.Y_AXIS));
        panelBtnDireccion.setLayout(new BoxLayout(panelBtnDireccion, BoxLayout.Y_AXIS));
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.Y_AXIS));

        panelImagenNombreUsuario.add(Box.createVerticalStrut(MARGEN_VERTICAL_COMPONENTES));
        panelBtnCarrito.add(Box.createVerticalStrut(MARGEN_VERTICAL_COMPONENTES));

        panelFila1.add(panelImagenNombreUsuario);
        panelFila1.add(panelLogotipo);
        panelFila1.add(panelBtnCarrito);
        panelFila2.add(panelBtnDireccion);
        panelFila2.add(panelBusqueda);
        panelFila2.add(panelEspacio);

        panelImagenNombreUsuario2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogotipo2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtnDireccion2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBusqueda2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtnCarrito2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelImagenNombreUsuario2.setOpaque(false);
        panelLogotipo2.setOpaque(false);
        panelBtnDireccion2.setOpaque(false);
        panelBtnCarrito2.setOpaque(false);
        panelBusqueda2.setOpaque(false);

        panelImagenNombreUsuario.add(panelImagenNombreUsuario2);
        panelLogotipo.add(panelLogotipo2);
        panelBtnDireccion.add(panelBtnDireccion2);
        panelBusqueda.add(panelBusqueda2);
        panelBtnCarrito.add(panelBtnCarrito2);

        panelBtnCarrito2.add(cargarBtnCarrito());
        
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
        Image imagenLogoEmpresa = iconoLogoEmpresa.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoLogoEmpresa = new ImageIcon(imagenLogoEmpresa);

        etqLogotipoEmpresa = new JLabel(nuevoIconoLogoEmpresa);

        panelLogotipo2.add(etqLogotipoEmpresa);

        // Botón para cambiar de dirección de envío:
        JPanel panelUbicacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUbicacion.setOpaque(false);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setOpaque(false);

        panelBtnDireccion2.add(cargarBtnActualizarDireccionEnvio());
        
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

        campoBusquedaProductos.setColumns(20);
        
        panelBusqueda2.add(campoBusquedaProductos);
        
        panelBusqueda2.add(cargarBtnBusqueda());

    }

    private JButton cargarBtnCarrito() {
        // Botón de Carrito de Compras:    
        btnCarritoCompras = new ButtonBuilder()
                .withText(EMOJI_CARRITO)
                .withFont(new Font("Segoe UI Emoji", Font.BOLD, 16))
                .withBackground(COLOR_BTNS_CARRITO_DIRECCION)
                .withPreferredSize(80, 40)
                .onClick(e -> control.mostrarCarritoCompras(idCliente, vistaPadre))
                .build();
        
        return btnCarritoCompras;

    }
    
    private JButton cargarBtnActualizarDireccionEnvio() {
        
        btnActualizarDireccionEnvio = new ButtonBuilder()
                .withFont(new Font("Segoe UI Emoji", Font.BOLD, 16))
                .withBackground(COLOR_BTNS_CARRITO_DIRECCION)
                .withPreferredSize(170, 50)
                .onClick(e -> control.mostrarActualizacionDireccionEnvio(idCliente, vistaPadre))
                .build();
        
        return btnActualizarDireccionEnvio;

    }
    
    public void ocultarBtnActualizarDireccionEnvio() {
        btnActualizarDireccionEnvio.setVisible(false);
    }
    
     private JButton cargarBtnBusqueda(){
        
        botonBusqueda = new ButtonBuilder()
                .withText(EMOJI_LUPA)
                .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 12))
                .withBackground(COLOR_BTNS_CARRITO_DIRECCION)
                .withPreferredSize(30, 30)
                .withEmptyMargin()
                .onClick(e -> control.mostrarProductosBusqueda(campoBusquedaProductos.getText()))
                .build();
        
        
        return botonBusqueda;
    }

    public void mostrarBarraBusqueda(){
        panelBusqueda2.setVisible(true);
        botonBusqueda.setVisible(true);
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

}
