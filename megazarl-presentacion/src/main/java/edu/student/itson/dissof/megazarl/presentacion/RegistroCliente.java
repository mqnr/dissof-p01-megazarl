
package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IRegistroCliente;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * RegistroCliente.java
 * 
 * Clase que representa un formulario de registro que permite registrar un cliente
 * en el sistema con los datos ingresados.
 * 
 * @author Romo López, Manuel
 * ID: 00000253080
 * 
 * 
 */
public class RegistroCliente extends JFrame implements IVista, IRegistroCliente{
    
    private JPanel panelPrincipal;
    private JPanel panelGeneral;
    private JPanel panelContenedorFormulario;
    private JPanel panelDatosCliente;

    private JLabel etqNombres;
    private JLabel etqApellidoPaterno;
    private JLabel etqApellidoMaterno;
    private JLabel etqTelefono;
    private JLabel etqCorreoElectronico;

    private JLabel etqMensajeValidacionNombres;
    private JLabel etqMensajeValidacionApellidoPaterno;
    private JLabel etqMensajeValidacionApellidoMaterno;
    private JLabel etqMensajeValidacionTelefono;
    private JLabel etqMensajeValidacionCorreoElectronico;
    
    private JTextField txtNombres;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JTextField txtTelefono;
    private JTextField txtCorreoElectronico;

    private JButton btnCancelar;
    private JButton btnGuardar;

    private EncabezadoRegistroCliente encabezado;
    private ControlRegistroCliente control;
    private Object idUsuario;

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    
    private boolean nombresValido;
    private boolean apellidoPaternoValido;
    private boolean apellidoMaternoValido;
    private boolean telefonoValido;
    private boolean correoElectronicoValido;
    
    private boolean usuarioEsAuxiliarVentas;

    
    private Color COLOR_FONDO = new Color(88, 69, 50);
    
    private Font FUENTE_TEXTO_BOTONES = new Font("Segoe UI", Font.BOLD, 14);
    
    private String REGEX_EMAIL_VALIDO = "[A-Za-z0-9._]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$";
    
    public RegistroCliente(
            ControlRegistroCliente control,
            Object idUsuario,
            boolean usuarioEsAuxiliarVentas) {
        
        this.control = control;
        this.idUsuario = idUsuario;
        this.usuarioEsAuxiliarVentas = usuarioEsAuxiliarVentas;

        initComponents();
        
        setTitle("Semillas MEGAZARL - Registrar cliente");
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

        encabezado = new EncabezadoRegistroCliente(control, idUsuario, this);
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

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setOpaque(false);
        panelContenedorFormulario.add(panelTitulo);

        panelContenedorFormulario.add(Box.createVerticalStrut(30));

        panelDatosCliente = new PanelRedondeado(10, new Color(238, 237, 185));
        panelDatosCliente.setPreferredSize(new Dimension(460, 400));
        panelDatosCliente.setLayout(new BoxLayout(panelDatosCliente, BoxLayout.Y_AXIS));
        panelDatosCliente.setBorder(new EmptyBorder(20,20,20,20));
        
        panelContenedorFormulario.add(panelDatosCliente);

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
                control.finalizarCasoUso(RegistroCliente.this);
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardarDatosCliente();
            }
        });
        
        configurarCampoTextoNombres();
        configurarCampoTextoApellidoPaterno();
        configurarCampoTextoApellidoMaterno();
        configurarCampoTextoTelefono();
        configurarCampoTextoCorreoElectronico();
    }
    
    private void crearComponentesPanelDatos() {
        
        // Nombres
        JPanel panelNombres = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelNombres.setOpaque(false);

        etqNombres = new JLabel("Nombres:");
        etqNombres.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtNombres = new JTextField(20);
        txtNombres.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelNombres.add(etqNombres);
        panelNombres.add(txtNombres);

        panelDatosCliente.add(panelNombres);

        // Mensaje validación de nombre
        JPanel panelMensajeNombres = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeNombres.setOpaque(false);

        etqMensajeValidacionNombres = new JLabel(" ");
        etqMensajeValidacionNombres.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionNombres.setForeground(Color.RED);

        panelMensajeNombres.add(etqMensajeValidacionNombres);
        panelDatosCliente.add(panelMensajeNombres);

        // Apellido paterno
        JPanel panelApellidoPaterno = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelApellidoPaterno.setOpaque(false);

        etqApellidoPaterno = new JLabel("Apellido paterno:");
        etqApellidoPaterno.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtApellidoPaterno = new JTextField(20);
        txtApellidoPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelApellidoPaterno.add(etqApellidoPaterno);
        panelApellidoPaterno.add(txtApellidoPaterno);
        
        panelDatosCliente.add(panelApellidoPaterno);
        
        // Mensaje validación apellido paterno
        JPanel panelMensajeApellidoPaterno = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeApellidoPaterno.setOpaque(false);

        etqMensajeValidacionApellidoPaterno = new JLabel(" ");
        etqMensajeValidacionApellidoPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionApellidoPaterno.setForeground(Color.RED);

        panelMensajeApellidoPaterno.add(etqMensajeValidacionApellidoPaterno);
        
        panelDatosCliente.add(panelMensajeApellidoPaterno);

        // Apellido materno
        JPanel panelApellidoMaterno = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelApellidoMaterno.setOpaque(false);

        etqApellidoMaterno = new JLabel("Apellido materno:");
        etqApellidoMaterno.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtApellidoMaterno = new JTextField(20);
        txtApellidoMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelApellidoMaterno.add(etqApellidoMaterno);
        panelApellidoMaterno.add(txtApellidoMaterno);

        panelDatosCliente.add(panelApellidoMaterno);
        
        // Mensaje validación apellido materno
        JPanel panelMensajeApellidoMaterno= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeApellidoMaterno.setOpaque(false);

        etqMensajeValidacionApellidoMaterno = new JLabel(" ");
        etqMensajeValidacionApellidoMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionApellidoMaterno.setForeground(Color.RED);

        panelMensajeApellidoMaterno.add(etqMensajeValidacionApellidoMaterno);
        
        panelDatosCliente.add(panelMensajeApellidoMaterno);

        // Teléfono
        JPanel panelTelefono = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTelefono.setOpaque(false);

        etqTelefono = new JLabel("Teléfono:");
        etqTelefono.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtTelefono = new JTextField(20);
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelTelefono.add(etqTelefono);
        panelTelefono.add(txtTelefono);

        panelDatosCliente.add(panelTelefono);
        
        // Mensaje validación correo teléfono
        JPanel panelMensajeTelefono = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeTelefono.setOpaque(false);

        etqMensajeValidacionTelefono = new JLabel(" ");
        etqMensajeValidacionTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionTelefono.setForeground(Color.RED);

        panelMensajeTelefono.add(etqMensajeValidacionTelefono);
        
        panelDatosCliente.add(panelMensajeTelefono);

        // Correo electrónico
        JPanel panelCorreoElectronico = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCorreoElectronico.setOpaque(false);

        etqCorreoElectronico = new JLabel("Correo electrónico:");
        etqCorreoElectronico.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCorreoElectronico = new JTextField(20);
        txtCorreoElectronico.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelCorreoElectronico.add(etqCorreoElectronico);
        panelCorreoElectronico.add(txtCorreoElectronico);

        panelDatosCliente.add(panelCorreoElectronico);

        // Mensaje validación correo electrónico
        JPanel panelMensajeCorreoElectronico = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMensajeCorreoElectronico.setOpaque(false);

        etqMensajeValidacionCorreoElectronico = new JLabel(" ");
        etqMensajeValidacionCorreoElectronico.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        etqMensajeValidacionCorreoElectronico.setForeground(Color.RED);

        panelMensajeCorreoElectronico.add(etqMensajeValidacionCorreoElectronico);
        
        panelDatosCliente.add(panelMensajeCorreoElectronico);

    }

    public void configurarCampoTextoNombres() {
        txtNombres.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarNombres();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarNombres();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarNombres();
            }

            private void validarNombres() {
                String nuevoNombres = txtNombres.getText();

                if(nuevoNombres.isBlank()) {
                    etqMensajeValidacionNombres.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    nombresValido = false;
                } else{
                    etqMensajeValidacionNombres.setText(" ");
                    nombresValido = true;
                    nombres = txtNombres.getText();
                }
                habilitarBotonGuardar();
            }
        });
    }


    public void configurarCampoTextoApellidoPaterno() {
        txtApellidoPaterno.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarApellidoPaterno();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarApellidoPaterno();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarApellidoPaterno();
            }

            private void validarApellidoPaterno() {
                String nuevoApellidoPaterno = txtApellidoPaterno.getText();

                if(nuevoApellidoPaterno.isBlank()) {
                    etqMensajeValidacionApellidoPaterno.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    apellidoPaternoValido = false;
                } else{
                    etqMensajeValidacionApellidoPaterno.setText(" ");
                    apellidoPaternoValido = true;
                    apellidoPaterno = txtApellidoPaterno.getText();
                }
                habilitarBotonGuardar();
            }
        });
    }

    public void configurarCampoTextoApellidoMaterno() {
        txtApellidoMaterno.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarApellidoMaterno();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarApellidoMaterno();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarApellidoMaterno();
            }

            private void validarApellidoMaterno() {
                String nuevoApellidoMaterno = txtApellidoMaterno.getText();

                if(nuevoApellidoMaterno.isBlank()) {
                    etqMensajeValidacionApellidoMaterno.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    apellidoMaternoValido = false;
                } else{
                    etqMensajeValidacionApellidoMaterno.setText(" ");
                    apellidoMaternoValido = true;
                    apellidoMaterno = txtApellidoMaterno.getText();
                }
                habilitarBotonGuardar();
            }
        });
    }
    
    public void configurarCampoTextoTelefono() {
        txtTelefono.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarTelefono();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarTelefono();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarTelefono();
            }

            private void validarTelefono() {
                String nuevoTelefono = txtTelefono.getText();

                if (!nuevoTelefono.matches("\\d+")) {
                    etqMensajeValidacionTelefono.setText("Debe ingresar un número entero");
                    btnGuardar.setEnabled(false);
                    telefonoValido = false;
                    
                } else if (nuevoTelefono.length() != 10){
                    etqMensajeValidacionTelefono.setText("Debe ingresar 10 dígitos");
                    btnGuardar.setEnabled(false);
                    telefonoValido = false;
                    
                } else if(nuevoTelefono.isBlank()) {
                    etqMensajeValidacionTelefono.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    telefonoValido = false;
                } else {
                    txtTelefono.setForeground(Color.BLACK);
                    etqMensajeValidacionTelefono.setText(" ");
                    telefonoValido = true;
                    telefono = txtTelefono.getText();
                }
                habilitarBotonGuardar();
            }
        });
    }
    
    public void configurarCampoTextoCorreoElectronico() {
        txtCorreoElectronico.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarCorreoElectronico();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarCorreoElectronico();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarCorreoElectronico();
            }

            private void validarCorreoElectronico() {
                String nuevoCorreoElectronico = txtCorreoElectronico.getText();

                if (!nuevoCorreoElectronico.matches(REGEX_EMAIL_VALIDO)) {
                    etqMensajeValidacionCorreoElectronico.setText("El correo debe tener el formato ejemplo@dominio.com");
                    btnGuardar.setEnabled(false);
                    correoElectronicoValido = false;
                    
                } else {
                    txtCorreoElectronico.setForeground(Color.BLACK);
                    etqMensajeValidacionCorreoElectronico.setText(" ");
                    correoElectronicoValido = true;
                    correoElectronico = txtCorreoElectronico.getText();
                }
                habilitarBotonGuardar();
            }
        });
    }

    private void habilitarBotonGuardar() {
        if(nombresValido && apellidoPaternoValido && apellidoMaternoValido && telefonoValido && correoElectronicoValido) {
            btnGuardar.setEnabled(true);
        } else {
            btnGuardar.setEnabled(false);
        }
    }

    public void guardarDatosCliente() {
        control.registrarCliente(
                Arrays.asList(nombres, apellidoPaterno, apellidoMaterno, telefono, correoElectronico));
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
        if(usuarioEsAuxiliarVentas){
            encabezado.mostrarNombreApellidoAuxiliarVentas();
        }
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
