package edu.student.itson.dissof.megazarl.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class Mensaje extends JFrame {

    // Colores
    private final Color VERDE_CABECERA = new Color(43, 189, 126);
    private final Color FONDO_MENSAJE = new Color(235, 255, 229);
    private final Color BOTON_AMARILLO = new Color(235, 255, 197);

    // Recursos
    private final Image iconoPropio = Toolkit.getDefaultToolkit()
            .getImage(getClass().getResource("/logoApp.png"))
            .getScaledInstance(90, 90, Image.SCALE_SMOOTH);

    public Mensaje() {
        configurarVentana();
        initUI();
    }

    private void configurarVentana() {
        setTitle("Mensaje");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(937, 676);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(iconoPropio);
    }

    private void initUI() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(217, 217, 255));

        panelPrincipal.add(crearCabecera(), BorderLayout.NORTH);
        panelPrincipal.add(crearCuerpoMensaje(), BorderLayout.CENTER);
        panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private JPanel crearCabecera() {
        // Panel principal de cabecera
        JPanel panelCabecera = new JPanel(new BorderLayout());
        panelCabecera.setBackground(VERDE_CABECERA);
        panelCabecera.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Ajuste de padding

        // Fila con perfil y logo
        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(VERDE_CABECERA);

        // Panel de perfil (izquierda)
        JPanel panelPerfil = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelPerfil.setOpaque(false);

        JLabel labelIconoPerfil = new JLabel();
        ImageIcon iconoPerfil = new ImageIcon(getClass().getResource("/logoUsuario.png"));
        labelIconoPerfil.setIcon(new ImageIcon(iconoPerfil.getImage()
            .getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        labelIconoPerfil.setPreferredSize(new Dimension(60, 60));

        JLabel labelNombreUsuario = new JLabel("Juan Pérez");
        labelNombreUsuario.setForeground(Color.WHITE);
        labelNombreUsuario.setFont(new Font("Arial", Font.BOLD, 16));

        panelPerfil.add(labelIconoPerfil);
        panelPerfil.add(labelNombreUsuario);

        // Panel del logo (centro)
        JPanel panelLogo = new JPanel();
        panelLogo.setOpaque(false);

        JLabel labelLogo = new JLabel(new ImageIcon(getClass().getResource("/banner.png")));
        panelLogo.add(labelLogo);

        // Componentes en la fila
        fila.add(panelPerfil, BorderLayout.WEST);
        fila.add(panelLogo, BorderLayout.CENTER);

        panelCabecera.add(fila, BorderLayout.NORTH);

        return panelCabecera;
    }

    private JPanel crearCuerpoMensaje() {
        // Panel contenedor principal con márgenes
        JPanel contenedorPrincipal = new JPanel(new BorderLayout());
        contenedorPrincipal.setBackground(new Color(217, 217, 255)); // Color del fondo principal
        contenedorPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80)); // Margen superior, izquierdo, inferior, derecho

        // Panel del mensaje
        JPanel panelMensaje = new JPanel(new BorderLayout(30, 0));
        panelMensaje.setBackground(FONDO_MENSAJE);
        panelMensaje.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Padding interno

        // Icono de verificación (centrado verticalmente)
        JPanel panelIcono = new JPanel(new GridBagLayout());
        panelIcono.setOpaque(false);
        panelIcono.add(new JLabel(redimensionarIcono("/checksquare.png", 100, 100)));

        // Texto del mensaje (centrado)
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setOpaque(false);

        JLabel lblLinea1 = new JLabel("Pedido realizado con éxito, puede consultar su estatus desde");
        JLabel lblLinea2 = new JLabel("el apartado Mis Pedidos, en el menú principal");

        for (JLabel lbl : new JLabel[]{lblLinea1, lblLinea2}) {
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado horizontal
        }

        // Añadir espacio vertical flexible arriba y abajo
        panelTexto.add(Box.createVerticalGlue());
        panelTexto.add(lblLinea1);
        panelTexto.add(lblLinea2);
        panelTexto.add(Box.createVerticalGlue());

        // Ensamblar componentes
        panelMensaje.add(panelIcono, BorderLayout.WEST);
        panelMensaje.add(panelTexto, BorderLayout.CENTER);

        // Centrar el panel del mensaje dentro del contenedor
        contenedorPrincipal.add(panelMensaje, BorderLayout.CENTER);

        return contenedorPrincipal;
    }

    private JPanel crearPanelBoton() {
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(217, 217, 255));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnAceptar.setBackground(BOTON_AMARILLO);
        btnAceptar.setPreferredSize(new Dimension(150, 40));
        panelBoton.add(btnAceptar);

        return panelBoton;
    }

    private ImageIcon redimensionarIcono(String recurso, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(recurso));
        Image imagenRedimensionada = iconoOriginal.getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

    // Clase interna para paneles redondeados
    class RoundedPanel extends JPanel {
        private final int cornerRadius;
        private Color backgroundColor;

        public RoundedPanel(int radius, Color bgColor) {
            cornerRadius = radius;
            backgroundColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();

            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        }
    }
}