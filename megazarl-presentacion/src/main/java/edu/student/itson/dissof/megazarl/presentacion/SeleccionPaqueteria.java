package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SeleccionPaqueteria extends JFrame implements ISeleccionPaqueteria, IVista {

    private Encabezado encabezado;

    private JPanel panelCentral;
    private JPanel panelInstrucciones;
    private JPanel panelDireccionEnvio;
    private JScrollPane scrollPanePaqueterias;
    private JPanel panelPaqueterias;
    private JPanel panelCostoEnvio;
    private JPanel panelBotones;

    private Integer idCliente;

    private int ANCHO_IMAGEN_PAQUETERIA = 160;
    private int ALTO_IMAGEN_PAQUETERIA = 110;

    private int MARGEN_VERTICAL_IMAGENES_PAQUETERIA = 10;
    private int MARGEN_HORIZONTAL_IMAGENES_PAQUETERIA = 10;

    private final ControlCompra control;

    public SeleccionPaqueteria(ControlCompra control, Integer idCliente, Encabezado encabezado) {
        this.encabezado = encabezado;
        initComponents();
        this.control = control;
        this.idCliente = idCliente;
    }

    private void initComponents() {
        this.setTitle("Selección de Paquetería");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.add(encabezado, BorderLayout.NORTH);

        panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        this.add(panelCentral, BorderLayout.CENTER);

        panelInstrucciones = new JPanel();
        panelInstrucciones.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelInstrucciones.setLayout(new BoxLayout(panelInstrucciones, BoxLayout.Y_AXIS));

        JLabel etqTituloInstrucciones = new JLabel("Selección de Paquetería");
        etqTituloInstrucciones.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel etqInstrucciones = new JLabel("Seleccione la paqueteria que realizará el envío de sus productos");
        etqInstrucciones.setFont(new Font("Arial", Font.PLAIN, 18));

        panelInstrucciones.add(etqTituloInstrucciones);
        panelInstrucciones.add(etqInstrucciones);

        JPanel panelInstruccionesFila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInstruccionesFila.add(panelInstrucciones);

        panelCentral.add(panelInstruccionesFila);

        panelDireccionEnvio = new JPanel();
        panelDireccionEnvio.setBackground(Color.WHITE);
        panelDireccionEnvio.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelDireccionEnvio.setLayout(new BoxLayout(panelDireccionEnvio, BoxLayout.Y_AXIS));

        JLabel etqTituloDireccionEnvio = new JLabel("Dirección de envío: ");
        etqTituloDireccionEnvio.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel etqDireccion = new JLabel("Dirección de Envío: Antonio Caso 2266, Villa Itson, C.P. 85130");
        etqDireccion.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panelDireccionEnvioFila = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelDireccionEnvio.add(etqTituloDireccionEnvio);
        panelDireccionEnvio.add(etqDireccion);
        panelDireccionEnvioFila.add(panelDireccionEnvio);
        panelCentral.add(panelDireccionEnvioFila);

        panelPaqueterias = new JPanel();
        panelCostoEnvio = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
    }

    @Override
    public void setPaqueterias(List<String> direccionesImagenesPaqueteria) {
        panelPaqueterias.removeAll();
        panelCostoEnvio.removeAll();
        panelBotones.removeAll();

        ButtonGroup grupoPaqueterias = new ButtonGroup();

        panelPaqueterias.setLayout(new GridLayout(Math.ceilDiv(direccionesImagenesPaqueteria.size(), 5), 5));

        for (String direccionImagenPaqueteria : direccionesImagenesPaqueteria) {

            JPanel panelRadioImagenPaqueteria = new JPanel(new BorderLayout());

            ImageIcon iconoImagenPaqueteria = new ImageIcon(this.getClass().getResource(direccionImagenPaqueteria));
            Image imagenPaqueteria = iconoImagenPaqueteria.getImage().getScaledInstance(ANCHO_IMAGEN_PAQUETERIA, ALTO_IMAGEN_PAQUETERIA, Image.SCALE_SMOOTH);
            ImageIcon nuevoIconoImagenPaqueteria = new ImageIcon(imagenPaqueteria);

            JLabel etqImagenPaqueteria = new JLabel(nuevoIconoImagenPaqueteria);
            JRadioButton radioPaqueteria = new JRadioButton();
            grupoPaqueterias.add(radioPaqueteria);

            JPanel panelRadioPaquteria = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelRadioPaquteria.add(radioPaqueteria);

            panelRadioImagenPaqueteria.add(etqImagenPaqueteria, BorderLayout.NORTH);
            panelRadioImagenPaqueteria.add(panelRadioPaquteria, BorderLayout.CENTER);

            panelPaqueterias.add(panelRadioImagenPaqueteria);

        }
        scrollPanePaqueterias = new JScrollPane(panelPaqueterias, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelCentral.add(scrollPanePaqueterias);

        // Costo de envío
        JLabel labelCosto = new JLabel("Costo de envío: Gratuito");
        labelCosto.setFont(new Font("Arial", Font.PLAIN, 18));
        panelCostoEnvio.add(labelCosto);

        panelCentral.add(panelCostoEnvio);

        JButton btnCancelar = new JButton("Cancelar pedido");
        JButton btnContinuar = new JButton("Continuar");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnContinuar);
        panelCentral.add(panelBotones);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.mostrarProductosVenta(SeleccionPaqueteria.this);
            }
        });

        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.mostrarConfirmacionPedido(SeleccionPaqueteria.this);
            }
        });
    }

    @Override
    public void hacerVisible(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void cerrar() {
        dispose();
    }
}
