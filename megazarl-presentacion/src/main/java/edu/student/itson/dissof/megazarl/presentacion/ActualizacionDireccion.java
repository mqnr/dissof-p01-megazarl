package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IDireccion;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Clase que permite al cliente modificar su dirección de envìo de pedido.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 * 
 */
public class ActualizacionDireccion extends JFrame implements IVista, IDireccion {
    private JPanel panelPrincipal;
    private JPanel panelGeneral;
    private JPanel panelContenedorFormulario;
    private JPanel panelDatosDireccion;

    private JLabel etqActualizarDireccionEnvio;
    private JLabel etqCodigoPostal;
    private JLabel etqEstado;
    private JLabel etqCiudad;
    private JLabel etqColonia;
    private JLabel etqNumero;
    private JLabel etqCalle;
    private JLabel etqMensajeValidacionNumero;
    private JLabel etqMensajeValidacionCodigoPostal;
    private JLabel etqMensajeValidacionCalle;

    private JTextField txtCodigoPostal;
    private JTextField txtEstado;
    private JTextField txtCiudad;
    private JComboBox<String> comboBoxColonia;
    private JTextField txtNumero;
    private JTextField txtCalle;

    private JButton btnCancelar;
    private JButton btnGuardar;

    private EncabezadoCompra encabezado;
    private ControlCompra control;
    private Long idCliente;

    private String codigoPostalEnvio;
    private String numeroEnvio;
    private String calleEnvio;
    private String coloniaEnvio;
    private boolean codigoPostalValido;
    private boolean numeroValido;
    private boolean calleValida;
    
    private Color COLOR_FONDO = new Color(88, 69, 50);
    
    private Font FUENTE_TEXTO_BOTONES = new Font("Segoe UI", Font.BOLD, 14);

    public ActualizacionDireccion(ControlCompra control, Long idCliente) {
        this.control = control;
        this.idCliente = idCliente;

        initComponents();
        
        setTitle("Semillas MEGAZARL - Actualizar dirección");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);

    }

    private void initComponents() {
        
        getContentPane().setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        encabezado = new EncabezadoCompra(control, idCliente, this);
        panelPrincipal.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel();
        panelGeneral.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelGeneral.setBackground(COLOR_FONDO);
        panelPrincipal.add(panelGeneral, BorderLayout.CENTER);

        panelContenedorFormulario = new JPanel();
        panelContenedorFormulario.setOpaque(false);
        panelContenedorFormulario.setLayout(new BoxLayout(panelContenedorFormulario, BoxLayout.Y_AXIS));
        panelGeneral.add(panelContenedorFormulario);

        panelContenedorFormulario.add(new JLabel(" "));
        
        etqActualizarDireccionEnvio = new JLabel("Actualizar dirección de envío");
        etqActualizarDireccionEnvio.setFont(new Font("Segoe UI", Font.BOLD, 18));
        etqActualizarDireccionEnvio.setHorizontalAlignment(SwingConstants.CENTER);
        etqActualizarDireccionEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        etqActualizarDireccionEnvio.setForeground(Color.WHITE);

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setOpaque(false);
        panelTitulo.add(etqActualizarDireccionEnvio);
        panelContenedorFormulario.add(panelTitulo);

        panelContenedorFormulario.add(Box.createVerticalStrut(30));

        panelDatosDireccion = new PanelRedondeado(10, new Color(226, 234, 206));
        panelDatosDireccion.setPreferredSize(new Dimension(400, 400));
        panelDatosDireccion.setLayout(new BoxLayout(panelDatosDireccion, BoxLayout.Y_AXIS));
        panelDatosDireccion.setBorder(new EmptyBorder(20,20,20,20));
        
        panelContenedorFormulario.add(panelDatosDireccion);

        crearComponentesPanelDatos();

        panelContenedorFormulario.add(Box.createVerticalStrut(30));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelBotones.setOpaque(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(246, 255, 197));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(246, 255, 197));
        btnGuardar.setEnabled(false);

        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        panelContenedorFormulario.add(panelBotones);
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                control.mostrarProductosVenta(ActualizacionDireccion.this);
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardarDatosDireccionCliente();
            }
        });
        
        configurarCampoTextoCodigoPostal();
        configurarComboBoxColonia();
        configurarCampoTextoNumero();
        configurarCampoTextoCalle();
    }

    private void crearComponentesPanelDatos() {
        // Código Postal
        JPanel panelCodigoPostal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCodigoPostal.setOpaque(false);

        etqCodigoPostal = new JLabel("Código Postal:");
        etqCodigoPostal.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCodigoPostal = new JTextField(20);
        txtCodigoPostal.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelCodigoPostal.add(etqCodigoPostal);
        panelCodigoPostal.add(txtCodigoPostal);
        
        JPanel panelSeparador = new JPanel();
        
        panelSeparador.setOpaque(false);
        
        panelDatosDireccion.add(panelSeparador);

        panelDatosDireccion.add(panelCodigoPostal);

        // Mensaje validación código postal
        JPanel panelMensajeCodigoPostal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeCodigoPostal.setOpaque(false);

        etqMensajeValidacionCodigoPostal = new JLabel(" ");
        etqMensajeValidacionCodigoPostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionCodigoPostal.setForeground(Color.RED);

        panelMensajeCodigoPostal.add(etqMensajeValidacionCodigoPostal);
        panelDatosDireccion.add(panelMensajeCodigoPostal);

        // Estado
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEstado.setOpaque(false);

        etqEstado = new JLabel("Estado:");
        etqEstado.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtEstado = new JTextField(20);
        txtEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEstado.setEditable(false);
        txtEstado.setEnabled(false);

        panelEstado.add(etqEstado);
        panelEstado.add(txtEstado);

        panelDatosDireccion.add(panelEstado);

        // Ciudad
        JPanel panelCiudad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCiudad.setOpaque(false);

        etqCiudad = new JLabel("Ciudad:");
        etqCiudad.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCiudad = new JTextField(20);
        txtCiudad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCiudad.setEditable(false);
        txtCiudad.setEnabled(false);

        panelCiudad.add(etqCiudad);
        panelCiudad.add(txtCiudad);

        panelDatosDireccion.add(panelCiudad);

        // Colonia
        JPanel panelColonia = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelColonia.setOpaque(false);

        etqColonia = new JLabel("Colonia:");
        etqColonia.setFont(new Font("Segoe UI", Font.BOLD, 14));

        comboBoxColonia = new JComboBox();
        comboBoxColonia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBoxColonia.setEditable(false);
        comboBoxColonia.setPreferredSize(new Dimension(246, 26));

        panelColonia.add(etqColonia);
        panelColonia.add(comboBoxColonia);

        panelDatosDireccion.add(panelColonia);

        // Número
        JPanel panelNumero = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelNumero.setOpaque(false);

        etqNumero = new JLabel("Número:");
        etqNumero.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtNumero = new JTextField(20);
        txtNumero.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelNumero.add(etqNumero);
        panelNumero.add(txtNumero);

        panelDatosDireccion.add(panelNumero);

        // Mensaje validación número
        JPanel panelMensajeNumero = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeNumero.setOpaque(false);

        etqMensajeValidacionNumero = new JLabel("El número de su domicilio debe ser un número entero");
        etqMensajeValidacionNumero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionNumero.setForeground(Color.RED);

        panelMensajeNumero.add(etqMensajeValidacionNumero);
        panelDatosDireccion.add(panelMensajeNumero);

        // Calle
        JPanel panelCalle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCalle.setOpaque(false);

        etqCalle = new JLabel("Calle:");
        etqCalle.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCalle = new JTextField(20);
        txtCalle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        panelCalle.add(etqCalle);
        panelCalle.add(txtCalle);
        panelCalle.add(new JLabel());

        panelDatosDireccion.add(panelCalle);

        // Mensaje validación calle
        JPanel panelMensajeCalle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeCalle.setOpaque(false);

        etqMensajeValidacionCalle = new JLabel("Este campo es obligatorio");
        etqMensajeValidacionCalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionCalle.setForeground(Color.RED);

        panelMensajeCalle.add(etqMensajeValidacionCalle);
        panelDatosDireccion.add(panelMensajeCalle);
    }

    public void configurarCampoTextoCodigoPostal() {
        txtCodigoPostal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarCodigoPostal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarCodigoPostal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarCodigoPostal();
            }

            private void validarCodigoPostal() {
                String nuevoCodigoPostal = txtCodigoPostal.getText();

                if (nuevoCodigoPostal.matches("\\d{5}")) {
                    Object[] datosDerivadosDireccion = control.obtenerDatosDireccionEnvioDerivadosCP(nuevoCodigoPostal);

                    if (datosDerivadosDireccion == null) {
                        txtCodigoPostal.setForeground(Color.RED);
                        etqMensajeValidacionCodigoPostal.setText("El Código Postal ingresado no existe.");
                        comboBoxColonia.setModel(new DefaultComboBoxModel(new Object[0]));
                        txtCiudad.setText("");
                        txtEstado.setText("");
                        codigoPostalValido = false;
                    } else {
                        txtCodigoPostal.setForeground(Color.BLACK);
                        etqMensajeValidacionCodigoPostal.setText(" ");

                        List<String> listaColonias = ((List<String>)datosDerivadosDireccion[0]);

                        comboBoxColonia.setModel(new DefaultComboBoxModel(listaColonias.toArray()));
                        txtCiudad.setText((String)datosDerivadosDireccion[1]);
                        txtEstado.setText((String)datosDerivadosDireccion[2]);

                        codigoPostalEnvio = txtCodigoPostal.getText();
                        codigoPostalValido = true;
                    }
                } else if(nuevoCodigoPostal.isBlank()) {
                    txtCodigoPostal.setForeground(Color.RED);
                    etqMensajeValidacionCodigoPostal.setText("Este campo es obligatorio");
                    comboBoxColonia.setModel(new DefaultComboBoxModel(new Object[0]));
                    txtCiudad.setText("");
                    txtEstado.setText("");
                    codigoPostalValido = false;
                } else {
                    txtCodigoPostal.setForeground(Color.RED);
                    etqMensajeValidacionCodigoPostal.setText("El código postal debe componerse de 5 dígitos");
                    codigoPostalValido = false;
                    comboBoxColonia.setModel(new DefaultComboBoxModel(new Object[0]));
                    txtCiudad.setText("");
                    txtEstado.setText("");
                }

                habilitarBotonGuardar();
            }
        });
    }
    
    public void configurarComboBoxColonia(){
        
 
        comboBoxColonia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                coloniaEnvio = (String)comboBoxColonia.getSelectedItem();
            }
        });
    }

    public void configurarCampoTextoNumero() {
        txtNumero.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarNumero();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarNumero();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarNumero();
            }

            private void validarNumero() {
                String nuevoNumero = txtNumero.getText();

                if (nuevoNumero.matches("\\d+")) {
                    txtNumero.setForeground(Color.BLACK);
                    etqMensajeValidacionNumero.setText(" ");
                    numeroValido = true;
                    numeroEnvio = txtNumero.getText();
                } else if(nuevoNumero.isBlank()) {
                    etqMensajeValidacionNumero.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    numeroValido = false;
                } else {
                    txtNumero.setForeground(Color.RED);
                    etqMensajeValidacionNumero.setText("Debe ingresar un número entero");
                    btnGuardar.setEnabled(false);
                    numeroValido = false;
                }
                habilitarBotonGuardar();
            }
        });
    }

    public void configurarCampoTextoCalle() {
        txtCalle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarCalle();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarCalle();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarCalle();
            }

            public void validarCalle() {
                String calle = txtCalle.getText();

                if(!calle.isBlank()) {
                    etqMensajeValidacionCalle.setText(" ");
                    calleValida = true;
                    calleEnvio = txtCalle.getText();
                } else {
                    etqMensajeValidacionCalle.setText("Este campo es obligatorio");
                    calleValida = false;
                }
                habilitarBotonGuardar();
            }
        });
    }

    private void habilitarBotonGuardar() {
        if(codigoPostalValido && calleValida && numeroValido) {
            btnGuardar.setEnabled(true);
        } else {
            btnGuardar.setEnabled(false);
        }
    }

    public void guardarDatosDireccionCliente() {
        control.actualizarDatosDireccionCliente(
                Arrays.asList(idCliente, codigoPostalEnvio, coloniaEnvio, calleEnvio, numeroEnvio),
                this);
    }

    @Override
    public void setCodigoPostalEnvio(String codigoPostalEnvio) {
        this.codigoPostalEnvio = codigoPostalEnvio;
        this.txtCodigoPostal.setText(codigoPostalEnvio);
    }

    @Override
    public void setCalleEnvio(String calleEnvio) {
        this.calleEnvio = calleEnvio;
        this.txtCalle.setText(calleEnvio);
    }

    @Override
    public void setNumeroEnvio(String numeroEnvio) {
        this.numeroEnvio = numeroEnvio;
        this.txtNumero.setText(numeroEnvio);
    }

    @Override
    public void setEstadoEnvio(String estadoEnvio) {
        this.txtEstado.setText(estadoEnvio);
    }

    @Override
    public void setCiudadEnvio(String ciudadEnvio) {
        this.txtCiudad.setText(ciudadEnvio);
    }
    
    
    @Override
    public void setColoniaEnvio(String coloniaEnvio) {
        this.coloniaEnvio = coloniaEnvio;
        this.comboBoxColonia.setSelectedItem(coloniaEnvio);
    }

    @Override
    public void hacerVisible(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void cerrar() {
        dispose();
    }

    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarNombreApellidoCliente();
        encabezado.ocultarBarraBusqueda();
        encabezado.ocultarDireccionCliente();
        encabezado.ocultarBtnNumeroCarritoCompras();
        encabezado.ocultarBtnSalir();
    }

    class PanelRedondeado extends JPanel {
        private int radioEsquinas;
        private Color colorFondo;

        public PanelRedondeado(int radio, Color bgColor) {
            radioEsquinas = radio;
            colorFondo = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(colorFondo);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radioEsquinas, radioEsquinas);

            g2d.dispose();
        }
    }
}