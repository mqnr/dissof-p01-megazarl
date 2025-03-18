package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.utilgui.ButtonBuilder;
import javax.swing.*;
import java.awt.*;

public class CarritoCompras extends JFrame {
    // Colores
    private final Color CABECERA_VERDE = new Color(58, 184, 124);
    private final Color GRIS_CLARO = new Color(240, 240, 240);
    private final Color BOTON_AMARILLO = new Color(248, 241, 132);

    // Emojis
    private final String emojiLupa = new String(Character.toChars(0x1F50D));
    private final String emojiCarrito = new String(Character.toChars(0x1F6D2));
    private final String emojiUbicacion = new String(Character.toChars(0x1F4CD));
    private final String emojiBasura = new String(Character.toChars(0x1F5D1));

    public CarritoCompras() {
        setTitle("Semillas MEGAZARL - Carrito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        panelPrincipal.add(crearPanelCabecera(), BorderLayout.NORTH);
        panelPrincipal.add(crearPanelCarrito(), BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private JPanel crearPanelCabecera() {
        // Panel principal de cabecera con 2 filas
        JPanel panelCabecera = new JPanel(new GridLayout(2, 1));
        panelCabecera.setBackground(CABECERA_VERDE);

        // Primera fila: Perfil a la izquierda, Logo en el centro
        JPanel filaSuperior = new JPanel(new BorderLayout());
        filaSuperior.setBackground(CABECERA_VERDE);
        filaSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));

        // Panel de perfil (lado izquierdo de la fila superior)
        JPanel panelPerfil = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPerfil.setOpaque(false);

        JLabel labelIconoPerfil = new JLabel();
        // Cargar perfil de usuario de recursos
        ImageIcon iconoPerfil = new ImageIcon(getClass().getResource("/logoUsuario.png"));
        // Redimensionar
        Image img = iconoPerfil.getImage();
        Image redimensionada = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        labelIconoPerfil.setIcon(new ImageIcon(redimensionada));
        labelIconoPerfil.setPreferredSize(new Dimension(60, 60));

        JLabel labelNombreUsuario = new JLabel("Juan Pérez");
        labelNombreUsuario.setForeground(Color.WHITE);
        labelNombreUsuario.setFont(new Font("Arial", Font.BOLD, 16));

        panelPerfil.add(labelIconoPerfil);
        panelPerfil.add(labelNombreUsuario);

        // Panel del logo (centro de la fila superior)
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.setOpaque(false);

        JLabel labelLogo = new JLabel();
        // Cargar logo
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/banner.png"));
        labelLogo.setIcon(iconLogo);
        panelLogo.add(labelLogo);

        // Panel vacío para el lado derecho de la fila superior para equilibrar el diseño
        JPanel panelDerechoVacio = new JPanel();
        panelDerechoVacio.setOpaque(false);
        panelDerechoVacio.setPreferredSize(new Dimension(panelPerfil.getPreferredSize().width, 60));

        filaSuperior.add(panelPerfil, BorderLayout.WEST);
        filaSuperior.add(panelLogo, BorderLayout.CENTER);
        filaSuperior.add(panelDerechoVacio, BorderLayout.EAST);

        // Segunda fila: Ubicación, Búsqueda, Carrito
        JPanel filaInferior = new JPanel(new BorderLayout());
        filaInferior.setBackground(CABECERA_VERDE);
        filaInferior.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));

        JPanel panelUbicacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUbicacion.setOpaque(false);

        JLabel iconoUbicacion = new JLabel(emojiUbicacion);
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

        // Panel central con barra de búsqueda
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentral.setOpaque(false);

        JTextField campoBusqueda = new JTextField("Sandía");
        campoBusqueda.setPreferredSize(new Dimension(200, 30));

        JButton botonBusqueda = new ButtonBuilder()
                .withText(emojiLupa)
                .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 12))
                .withPreferredSize(30, 30)
                .withEmptyMargin()
                .build();

        JPanel panelBusqueda = new JPanel(new BorderLayout());
        panelBusqueda.add(campoBusqueda, BorderLayout.CENTER);
        panelBusqueda.add(botonBusqueda, BorderLayout.EAST);

        panelCentral.add(panelBusqueda);

        // Panel de carrito (lado derecho de la fila inferior)
        JPanel panelCarrito = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCarrito.setOpaque(false);

        JButton botonCarrito = new ButtonBuilder()
                .withText(emojiCarrito + " 0")
                .withFont(new Font("Segoe UI Emoji", Font.BOLD, 16))
                .withBackground(BOTON_AMARILLO)
                .withPreferredSize(80, 40)
                .build();

        panelCarrito.add(botonCarrito);

        // Añadir componentes a la fila inferior
        filaInferior.add(panelUbicacion, BorderLayout.WEST);
        filaInferior.add(panelCentral, BorderLayout.CENTER);
        filaInferior.add(panelCarrito, BorderLayout.EAST);

        // Añadir ambas filas al panel de cabecera
        panelCabecera.add(filaSuperior);
        panelCabecera.add(filaInferior);

        return panelCabecera;
    }

    private JPanel crearPanelCarrito() {
        JPanel panelContenedorCarrito = new JPanel(new BorderLayout());
        panelContenedorCarrito.setBackground(GRIS_CLARO);
        panelContenedorCarrito.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de contenido principal (del carrito)
        JPanel panelCarrito = new JPanel(new BorderLayout());
        panelCarrito.setBackground(Color.WHITE);
        panelCarrito.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Cabecera de carrito
        JPanel panelCarritoCabecera = new JPanel(new GridLayout(1, 2));
        panelCarritoCabecera.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel labelCarritoTitulo = new JLabel("Carrito");
        labelCarritoTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelCarritoTitulo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));

        JLabel labelPrecio = new JLabel("Precio");
        labelPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        labelPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPrecio.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));

        panelCarritoCabecera.add(labelCarritoTitulo);
        panelCarritoCabecera.add(labelPrecio);

        // Item del carrito
        JPanel panelItemCarrito = new JPanel(new BorderLayout());
        panelItemCarrito.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Imagen del producto
        JLabel imagenProducto = new JLabel();
        ImageIcon iconProducto = new ImageIcon(getClass().getResource("/sandia.png"));
        // Redimensionar
        Image imagen = iconProducto.getImage();
        Image redimensionada = imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagenProducto.setIcon(new ImageIcon(redimensionada));

        imagenProducto.setPreferredSize(new Dimension(150, 150));
        imagenProducto.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));

        // Detalles de producto
        JPanel panelDetallesProducto = new JPanel();
        panelDetallesProducto.setLayout(new BoxLayout(panelDetallesProducto, BoxLayout.Y_AXIS));
        panelDetallesProducto.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDetallesProducto.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel labelNombreProducto = new JLabel("Semillas de Sandía Summer Breeze");
        labelNombreProducto.setFont(new Font("Arial", Font.BOLD, 16));
        labelNombreProducto.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelProveedor = new JLabel("Proveedor: Seminis");
        labelProveedor.setFont(new Font("Arial", Font.PLAIN, 14));
        labelProveedor.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Checkbox y cantidad
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelControles.setAlignmentX(Component.LEFT_ALIGNMENT);

        JCheckBox checkboxSeleccion = new JCheckBox();
        checkboxSeleccion.setSelected(true);

        JButton botonDecrementar = new ButtonBuilder()
                .withText("-")
                .withPreferredSize(30, 30)
                .withEmptyMargin()
                .build();

        JTextField campoCantidad = new JTextField("1");
        campoCantidad.setPreferredSize(new Dimension(40, 30));
        campoCantidad.setHorizontalAlignment(JTextField.CENTER);

        JButton botonIncrementar = new ButtonBuilder()
                .withText("+")
                .withPreferredSize(30, 30)
                .withEmptyMargin()
                .build();

        JButton botonEliminar = new ButtonBuilder()
                .withText(emojiBasura)
                .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 14))
                .withPreferredSize(40, 30)
                .withEmptyMargin()
                .build();

        panelControles.add(checkboxSeleccion);
        panelControles.add(botonDecrementar);
        panelControles.add(campoCantidad);
        panelControles.add(botonIncrementar);
        panelControles.add(botonEliminar);

        panelDetallesProducto.add(labelNombreProducto);
        panelDetallesProducto.add(Box.createVerticalStrut(5));
        panelDetallesProducto.add(labelProveedor);
        panelDetallesProducto.add(Box.createVerticalStrut(10));
        panelDetallesProducto.add(panelControles);

        // Precio
        JPanel panelPrecio = new JPanel();
        panelPrecio.setLayout(new BoxLayout(panelPrecio, BoxLayout.Y_AXIS));
        panelPrecio.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel labelPrecioItem = new JLabel("$9,400.00");
        labelPrecioItem.setFont(new Font("Arial", Font.BOLD, 16));
        labelPrecioItem.setAlignmentX(Component.RIGHT_ALIGNMENT);

        panelPrecio.add(labelPrecioItem);

        // Añadir componentes a panel de ítems de carrito
        panelItemCarrito.add(imagenProducto, BorderLayout.WEST);
        panelItemCarrito.add(panelDetallesProducto, BorderLayout.CENTER);
        panelItemCarrito.add(panelPrecio, BorderLayout.EAST);

        // Botones de abajo
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        ButtonBuilder btn = new ButtonBuilder()
                .withFont(new Font("Arial", Font.BOLD, 14))
                .withBackground(BOTON_AMARILLO)
                .withPreferredSize(180, 40);

        panelBotones.add(btn.withText("Seguir comprando").build());
        panelBotones.add(btn.withText("Finalizar la compra").build());

        // Añadir todos al panel del carrito
        panelCarrito.add(panelCarritoCabecera, BorderLayout.NORTH);
        panelCarrito.add(panelItemCarrito, BorderLayout.CENTER);
        panelCarrito.add(panelBotones, BorderLayout.SOUTH);

        // Barra lateral para resumen
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBackground(Color.WHITE);
        panelLateral.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelLateral.setPreferredSize(new Dimension(250, 0));

        // Banner de precio
        JPanel panelBannerPrecio = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBannerPrecio.setBackground(new Color(150, 220, 150));
        panelBannerPrecio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel labelPrecioTotal = new JLabel("$300.00");
        labelPrecioTotal.setFont(new Font("Arial", Font.BOLD, 18));

        panelBannerPrecio.add(labelPrecioTotal);

        // Nota sobre envío gratis
        JPanel panelEnvio = new JPanel();
        panelEnvio.setLayout(new BoxLayout(panelEnvio, BoxLayout.Y_AXIS));
        panelEnvio.setBackground(Color.WHITE);
        panelEnvio.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEnvio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelEnvio = new JLabel("El pedido califica para");
        labelEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelEnvioGratis = new JLabel("envío GRATIS");
        labelEnvioGratis.setFont(new Font("Arial", Font.BOLD, 14));
        labelEnvioGratis.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelEnvio.add(labelEnvio);
        panelEnvio.add(labelEnvioGratis);

        // Subtotal
        JPanel panelSubtotal = new JPanel(new BorderLayout());
        panelSubtotal.setBackground(Color.WHITE);
        panelSubtotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelSubtotal = new JLabel("Subtotal (1 producto/s):");
        labelSubtotal.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel labelCantidadSubtotal = new JLabel("$300.00");
        labelCantidadSubtotal.setFont(new Font("Arial", Font.BOLD, 14));
        labelCantidadSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);

        panelSubtotal.add(labelSubtotal, BorderLayout.WEST);
        panelSubtotal.add(labelCantidadSubtotal, BorderLayout.EAST);

        // Sección de "También compraron..."
        JPanel tambienCompraronPanel = new JPanel();
        tambienCompraronPanel.setLayout(new BoxLayout(tambienCompraronPanel, BoxLayout.Y_AXIS));
        tambienCompraronPanel.setBackground(Color.WHITE);
        tambienCompraronPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel tambienCompraronLabel = new JLabel("Los clientes también compraron:");
        tambienCompraronLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tambienCompraronLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Recomendaciones de productos
        JPanel panelRecomendaciones = new JPanel(new BorderLayout());
        panelRecomendaciones.setBackground(Color.WHITE);
        panelRecomendaciones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel imagenProductoRecomendado = new JLabel();
        // TODO: CAMBIAR
        ImageIcon iconRecomendado = new ImageIcon(getClass().getResource("/sandia_verde_grande.png"));
        // Redimensionar
        imagen = iconRecomendado.getImage();
        redimensionada = imagen.getScaledInstance(120, 70, Image.SCALE_SMOOTH);
        imagenProductoRecomendado.setIcon(new ImageIcon(redimensionada));
        imagenProductoRecomendado.setPreferredSize(new Dimension(120, 70));

        JPanel panelDetallesRecomendaciones = new JPanel();
        panelDetallesRecomendaciones.setLayout(new BoxLayout(panelDetallesRecomendaciones, BoxLayout.Y_AXIS));
        panelDetallesRecomendaciones.setBackground(Color.WHITE);

        JLabel nombre = new JLabel("Sandía Verde Grande");
        nombre.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel infoRecomendacionProducto = new JLabel("5k semillas");
        infoRecomendacionProducto.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel precioRecomendacionProducto = new JLabel("$4,627.00");
        precioRecomendacionProducto.setFont(new Font("Arial", Font.BOLD, 14));

        panelDetallesRecomendaciones.add(nombre);
        panelDetallesRecomendaciones.add(infoRecomendacionProducto);
        panelDetallesRecomendaciones.add(precioRecomendacionProducto);

        panelRecomendaciones.add(imagenProductoRecomendado, BorderLayout.WEST);
        panelRecomendaciones.add(panelDetallesRecomendaciones, BorderLayout.CENTER);

        tambienCompraronPanel.add(tambienCompraronLabel);
        tambienCompraronPanel.add(panelRecomendaciones);

        panelLateral.add(panelBannerPrecio);
        panelLateral.add(panelEnvio);
        panelLateral.add(new JSeparator());
        panelLateral.add(panelSubtotal);
        panelLateral.add(new JSeparator());
        panelLateral.add(tambienCompraronPanel);

        panelContenedorCarrito.add(panelCarrito, BorderLayout.CENTER);
        panelContenedorCarrito.add(panelLateral, BorderLayout.EAST);

        return panelContenedorCarrito;
    }
}
