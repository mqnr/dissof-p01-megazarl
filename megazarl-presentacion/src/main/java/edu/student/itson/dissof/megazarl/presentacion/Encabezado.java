package edu.student.itson.dissof.megazarl.presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import edu.student.itson.dissof.megazarl.presentacion.utilgui.ButtonBuilder;

public class Encabezado extends JPanel {

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

    private JTextField campoBusquedaProductos;

    private final int MARGEN_VERTICAL_COMPONENTES = 40;
    private final String EMOJI_CARRITO = new String(Character.toChars(0x1F6D2));
    private final String EMOJI_UBICACION = new String(Character.toChars(0x1F4CD));
    private final String EMOJI_LUPA = new String(Character.toChars(0x1F50D));
    private final Color BOTON_AMARILLO = new Color(248, 241, 132);

    JButton btnActualizarDireccionEnvio;

    JButton btnCarritoCompras;

    public Encabezado() {
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

        // Imagen e icono de usuario
        ImageIcon iconoUsuario = new ImageIcon(this.getClass().getResource("/usuarioIcono.png"));
        Image imagenUsuario = iconoUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        ImageIcon nuevoInconoUsuario = new ImageIcon(imagenUsuario);

        this.etqImagenUsuario = new JLabel(nuevoInconoUsuario);
        this.etqNombreUsuario = new JLabel("Juan Pérez");
        Font fuenteEtqNombreUsuario = new Font("Arial", Font.BOLD, 20);
        etqNombreUsuario.setFont(fuenteEtqNombreUsuario);
        etqNombreUsuario.setForeground(Color.WHITE);

        panelImagenNombreUsuario2.add(etqImagenUsuario);
        panelImagenNombreUsuario2.add(etqNombreUsuario);

        // Logotipo de empresa
        ImageIcon iconoLogoEmpresa = new ImageIcon(this.getClass().getResource("/logotipoEmpresa.png"));
        Image imagenLogoEmpresa = iconoLogoEmpresa.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoLogoEmpresa = new ImageIcon(imagenLogoEmpresa);

        this.etqLogotipoEmpresa = new JLabel(nuevoIconoLogoEmpresa);

        this.panelLogotipo2.add(etqLogotipoEmpresa);

        // Botón de Carrito de Compras:    
        btnCarritoCompras = new ButtonBuilder()
                .withText(EMOJI_CARRITO + " 0")
                .withFont(new Font("Segoe UI Emoji", Font.BOLD, 16))
                .withBackground(BOTON_AMARILLO)
                .withPreferredSize(80, 40)
                .build();

        panelBtnCarrito2.add(btnCarritoCompras);

        // Botón para cambiar de dirección de envío:
        JPanel panelUbicacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUbicacion.setOpaque(false);

        JLabel iconoUbicacion = new JLabel(EMOJI_UBICACION);
        iconoUbicacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setOpaque(false);

        JLabel tituloUbicacion = new JLabel("Ubicación de envío");
        tituloUbicacion.setForeground(Color.WHITE);
        tituloUbicacion.setFont(new Font("Arial", Font.BOLD, 12));
        tituloUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel direccionUbicacion = new JLabel("Antonio Caso S/N y E. Kino...");
        direccionUbicacion.setForeground(Color.WHITE);
        direccionUbicacion.setFont(new Font("Arial", Font.PLAIN, 10));
        direccionUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelTexto.add(tituloUbicacion);
        panelTexto.add(direccionUbicacion);

        panelUbicacion.add(iconoUbicacion);
        panelUbicacion.add(panelTexto);

        panelBtnDireccion2.add(panelUbicacion);
        // Campo de texto para búsqueda:
        campoBusquedaProductos = new JTextField();

        Font fuenteCampoBusqueda = new Font("Arial", Font.PLAIN, 20);

        campoBusquedaProductos.setFont(fuenteCampoBusqueda);

        campoBusquedaProductos.setColumns(20);

        JButton botonBusqueda = new ButtonBuilder()
                .withText(EMOJI_LUPA)
                .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 12))
                .withPreferredSize(30, 30)
                .withEmptyMargin()
                .build();

        panelBusqueda2.add(campoBusquedaProductos);
        panelBusqueda2.add(botonBusqueda);
    }
}
