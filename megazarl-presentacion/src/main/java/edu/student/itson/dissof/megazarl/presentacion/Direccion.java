package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IDireccion;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Direccion extends JFrame implements IVista, IDireccion {
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
    private JTextField txtColonia;
    private JTextField txtNumero;
    private JTextField txtCalle;

    private JButton btnCancelar;
    private JButton btnGuardar;

    private Encabezado encabezado;
    private ControlCompra control;
    private Integer idCliente;

    private String codigoPostalEnvio;
    private String numeroEnvio;
    private String calleEnvio;
    private String ciudadEnvio;
    private String estadoEnvio;
    private boolean primerCambioCodigoPostalRealizado;
    private boolean codigoPostalValido;
    private boolean numeroValido;
    private boolean calleValida;

    private Image iconoPropio;

    public Direccion(ControlCompra control, Integer idCliente) {
        this.control = control;
        this.idCliente = idCliente;

        initComponents();
        configurarCampoTextoCodigoPostal();
        configurarCampoTextoNumero();
        configurarCampoTextoCalle();

        setTitle("Actualizar dirección de usuario");
        setSize(1200, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(217, 217, 255));
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        encabezado = new Encabezado(control, idCliente, this);
        panelPrincipal.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel();
        panelGeneral.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal.add(panelGeneral, BorderLayout.CENTER);

        panelContenedorFormulario = new JPanel();
        panelContenedorFormulario.setLayout(new BoxLayout(panelContenedorFormulario, BoxLayout.Y_AXIS));
        panelGeneral.add(panelContenedorFormulario);

        etqActualizarDireccionEnvio = new JLabel("Actualizar dirección de envío");
        etqActualizarDireccionEnvio.setFont(new Font("Segoe UI", Font.BOLD, 18));
        etqActualizarDireccionEnvio.setHorizontalAlignment(SwingConstants.CENTER);
        etqActualizarDireccionEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setOpaque(false);
        panelTitulo.add(etqActualizarDireccionEnvio);
        panelContenedorFormulario.add(panelTitulo);

        panelContenedorFormulario.add(Box.createVerticalStrut(30));

        panelDatosDireccion = new PanelRedondeado(50, new Color(226, 234, 206));
        panelDatosDireccion.setPreferredSize(new Dimension(472, 400));
        panelDatosDireccion.setLayout(new BoxLayout(panelDatosDireccion, BoxLayout.Y_AXIS));
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
                control.mostrarProductosVenta(Direccion.this);
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardarDatosDireccionCliente();
            }
        });
    }

    private void crearComponentesPanelDatos() {
        // Código Postal
        JPanel panelCodigoPostal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCodigoPostal.setOpaque(false);

        etqCodigoPostal = new JLabel("Código Postal:");
        etqCodigoPostal.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCodigoPostal = new JTextField(20);
        txtCodigoPostal.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelCodigoPostal.add(etqCodigoPostal);
        panelCodigoPostal.add(txtCodigoPostal);

        panelDatosDireccion.add(panelCodigoPostal);

        // Mensaje validación código postal
        JPanel panelMensajeCodigoPostal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMensajeCodigoPostal.setOpaque(false);

        etqMensajeValidacionCodigoPostal = new JLabel("El código postal debe componerse de 5 dígitos");
        etqMensajeValidacionCodigoPostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionCodigoPostal.setForeground(Color.RED);
        etqMensajeValidacionCodigoPostal.setVisible(false);

        panelMensajeCodigoPostal.add(etqMensajeValidacionCodigoPostal);
        panelDatosDireccion.add(panelMensajeCodigoPostal);

        // Estado
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        JPanel panelCiudad = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        JPanel panelColonia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelColonia.setOpaque(false);

        etqColonia = new JLabel("Colonia:");
        etqColonia.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtColonia = new JTextField(20);
        txtColonia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtColonia.setEditable(false);
        txtColonia.setEnabled(false);

        panelColonia.add(etqColonia);
        panelColonia.add(txtColonia);

        panelDatosDireccion.add(panelColonia);

        // Número
        JPanel panelNumero = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNumero.setOpaque(false);

        etqNumero = new JLabel("Número:");
        etqNumero.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtNumero = new JTextField(20);
        txtNumero.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelNumero.add(etqNumero);
        panelNumero.add(txtNumero);

        panelDatosDireccion.add(panelNumero);

        // Mensaje validación número
        JPanel panelMensajeNumero = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMensajeNumero.setOpaque(false);

        etqMensajeValidacionNumero = new JLabel("El número de su domicilio debe ser un número entero");
        etqMensajeValidacionNumero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionNumero.setForeground(Color.RED);
        etqMensajeValidacionNumero.setVisible(false);

        panelMensajeNumero.add(etqMensajeValidacionNumero);
        panelDatosDireccion.add(panelMensajeNumero);

        // Calle
        JPanel panelCalle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCalle.setOpaque(false);

        etqCalle = new JLabel("Calle:");
        etqCalle.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCalle = new JTextField(20);
        txtCalle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelCalle.add(etqCalle);
        panelCalle.add(txtCalle);

        panelDatosDireccion.add(panelCalle);

        // Mensaje validación calle
        JPanel panelMensajeCalle = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
                if(!primerCambioCodigoPostalRealizado) {
                    String nuevoCodigoPostal = txtCodigoPostal.getText();

                    if (nuevoCodigoPostal.matches("\\d{5}")) {
                        String[] datosDerivadosDireccion = control.obtenerDatosDireccionDerivados(nuevoCodigoPostal);

                        if (datosDerivadosDireccion == null) {
                            txtCodigoPostal.setForeground(Color.RED);
                            etqMensajeValidacionCodigoPostal.setText("El Código Postal ingresado no existe.");
                            etqMensajeValidacionCodigoPostal.setVisible(true);
                            txtColonia.setText("");
                            txtCiudad.setText("");
                            txtEstado.setText("");
                            codigoPostalValido = false;
                        } else {
                            txtCodigoPostal.setForeground(Color.BLACK);
                            etqMensajeValidacionCodigoPostal.setVisible(false);

                            txtColonia.setText(datosDerivadosDireccion[0]);
                            txtCiudad.setText(datosDerivadosDireccion[1]);
                            txtEstado.setText(datosDerivadosDireccion[2]);

                            codigoPostalEnvio = txtCodigoPostal.getText();
                            codigoPostalValido = true;
                        }
                    } else if(nuevoCodigoPostal.isBlank()) {
                        txtCodigoPostal.setForeground(Color.RED);
                        etqMensajeValidacionCodigoPostal.setText("Este campo es obligatorio");
                        etqMensajeValidacionCodigoPostal.setVisible(true);
                        txtColonia.setText("");
                        txtCiudad.setText("");
                        txtEstado.setText("");
                        codigoPostalValido = false;
                    } else {
                        txtCodigoPostal.setForeground(Color.RED);
                        etqMensajeValidacionCodigoPostal.setText("El código postal debe componerse de 5 dígitos");
                        etqMensajeValidacionCodigoPostal.setVisible(true);
                        codigoPostalValido = false;
                        txtColonia.setText("");
                        txtCiudad.setText("");
                        txtEstado.setText("");
                    }

                    habilitarBotonGuardar();
                } else {
                    primerCambioCodigoPostalRealizado = true;
                }
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
                    etqMensajeValidacionNumero.setVisible(false);
                    numeroValido = true;
                    numeroEnvio = txtNumero.getText();
                } else if(nuevoNumero.isBlank()) {
                    etqMensajeValidacionNumero.setText("Este campo es obligatorio");
                    etqMensajeValidacionNumero.setVisible(true);
                    btnGuardar.setEnabled(false);
                    numeroValido = false;
                } else {
                    txtNumero.setForeground(Color.RED);
                    etqMensajeValidacionNumero.setText("Debe ingresar un número entero");
                    etqMensajeValidacionNumero.setVisible(true);
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
                    etqMensajeValidacionCalle.setVisible(false);
                    calleValida = true;
                    calleEnvio = txtCalle.getText();
                } else {
                    etqMensajeValidacionCalle.setText("Este campo es obligatorio");
                    etqMensajeValidacionCalle.setVisible(true);
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
                Arrays.asList(idCliente, numeroEnvio, calleEnvio, codigoPostalEnvio),
                this);
    }

    @Override
    public void setCodigoPostalEnvio(String codigoPostalEnvio) {
        this.primerCambioCodigoPostalRealizado = false;
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
        this.estadoEnvio = estadoEnvio;
        this.txtEstado.setText(estadoEnvio);
    }

    @Override
    public void setCiudadEnvio(String ciudadEnvio) {
        this.ciudadEnvio = ciudadEnvio;
        this.txtCiudad.setText(ciudadEnvio);
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