package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Mensaje extends JFrame implements IMensaje{

    private ControlCompra control;
    
    private Long idCliente;

    private JPanel panelPrincipal;
    
    private JPanel panelMensaje;

    private JLabel lblLinea;

    private JPanel panelIcono;

    JPanel panelTexto;

    // Colores

    private final Color BOTON_AMARILLO = new Color(235, 255, 197);

    // Recursos
    private final Image iconoPropio = Toolkit.getDefaultToolkit()
            .getImage(getClass().getResource("/iconoApp.png"))
            .getScaledInstance(90, 90, Image.SCALE_SMOOTH);

    public Mensaje(ControlCompra control, Long idCliente) {
        
        this.control = control;
        this.idCliente = idCliente;
        configurarVentana();
        initComponents();
    }

    private void configurarVentana() {
        setTitle("Mensaje");
        setSize(937, 676);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(iconoPropio);
    }
    
    private void initComponents() {
        panelPrincipal = new JPanel(new BorderLayout());
        
        panelPrincipal.add(crearCuerpoMensaje(), BorderLayout.CENTER);
        panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private JPanel crearCuerpoMensaje() {
        // Panel contenedor principal con márgenes
        JPanel contenedorPrincipal = new JPanel(new BorderLayout());
        contenedorPrincipal.setOpaque(false);
        contenedorPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80)); // Margen superior, izquierdo, inferior, derecho

        // Panel del mensaje
        panelMensaje = new JPanel(new BorderLayout(30, 0));
        panelMensaje.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Padding interno
        panelMensaje.setOpaque(false);

        // Icono de verificación (centrado verticalmente)
        panelIcono = new JPanel(new GridBagLayout());
        panelIcono.setOpaque(false);

        // Texto del mensaje (centrado)
        panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setOpaque(false);

        lblLinea = new JLabel();
        lblLinea.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblLinea.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir espacio vertical flexible arriba y abajo
        panelTexto.add(Box.createVerticalGlue());
        panelTexto.add(lblLinea);
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
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelBoton.setOpaque(false);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnAceptar.setBackground(BOTON_AMARILLO);
        btnAceptar.setPreferredSize(new Dimension(150, 40));
        panelBoton.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarMensaje();
            }
        });

        return panelBoton;
    }

    private ImageIcon redimensionarIcono(String recurso, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(recurso));
        Image imagenRedimensionada = iconoOriginal.getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

    @Override
    public void setColorFondo(Color colorFondo) {
        panelPrincipal.setBackground(colorFondo);
    }

    @Override
    public void setImagen(String direccionImagen) {
        panelIcono.removeAll();
        panelIcono.add(new JLabel(redimensionarIcono(direccionImagen, 100, 100)));
    }

    @Override
    public void setTexto(String texto) {
        lblLinea.setText(texto);
    }

    @Override
    public void mostrarMensaje() {
        setVisible(true);
    }

    @Override
    public void cerrarMensaje() {
        dispose();
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
