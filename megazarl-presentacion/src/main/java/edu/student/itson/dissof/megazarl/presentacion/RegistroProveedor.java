/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.student.itson.dissof.megazarl.presentacion;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 */
public class RegistroProveedor extends JFrame implements IVista{
    private JPanel panelPrincipal;
    private JPanel panelGeneral;
    private JPanel panelContenedorFormulario;
    private JPanel panelDatosProveedor;

    private JLabel etqAgregarProveedor;
    private JLabel etqNombre;
    private JLabel etqGmail;
    private JLabel etqTelefono;
    private JLabel etqCodigoPostal;
    private JLabel etqCalle;
    private JLabel etqColonia;
    private JLabel etqNumero;
    private JLabel etqImagen;
        
    private JLabel JImagen;
    private File arch;
    private BufferedImage imagen;
    private ImageIcon logo;
    
    private JLabel etqMensajeValidacionGmail;
    private JLabel etqMensajeValidacionCodigoPostal;
    private JLabel etqMensajeValidacionTelefono;
    
    private JTextField txtNombre;
    private JTextField txtGmail;
    private JTextField txtTelefono;
    private JTextField txtCodigoPostal;
    private JTextField txtCalle;
    private JComboBox<String> comboBoxColonia;
    private JTextField txtNumero;
    

    private JButton btnCancelar;
    private JButton btnGuardar;
    private JButton btnLogo;
    
    private EncabezadoRegistroProveedor encabezado;
    private ControlRegistroProveedor control;
    
    private String numeroEnvio;
    private String codigoPostalEnvio;
    private String coloniaEnvio;
    private String GmailEnvio;
    private String telefonoEnvio;
    private String calleEnvio;
    private String nombreEnvio;
    private String imagenEnvio;
    
    
    private boolean nombreValido;
    private boolean calleValida;
    private boolean numeroValido;
    private boolean codigoPostalValido;
    private boolean telefonoValido;
    private boolean gmailValida;
    private boolean imagenValido = false;
    
    private Color COLOR_FONDO = new Color(88, 69, 50);
    
    private Font FUENTE_TEXTO_BOTONES = new Font("Segoe UI", Font.BOLD, 14);

    public RegistroProveedor(ControlRegistroProveedor control) {
        this.control = control;
        setTitle("Semillas MEGAZARL - Agregar Proveedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
        initComponents();

    }
    
    private void initComponents(){
        getContentPane().setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        
        encabezado = new EncabezadoRegistroProveedor(this);
        actualizarDatosEncabezado();
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
        
        etqAgregarProveedor = new JLabel("Registrar Proveedor");
        etqAgregarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 18));
        etqAgregarProveedor.setHorizontalAlignment(SwingConstants.CENTER);
        etqAgregarProveedor.setAlignmentX(Component.CENTER_ALIGNMENT);
        etqAgregarProveedor.setForeground(Color.WHITE);

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setOpaque(false);
        panelTitulo.add(etqAgregarProveedor);
        panelContenedorFormulario.add(panelTitulo);

        panelContenedorFormulario.add(Box.createVerticalStrut(30));

        panelDatosProveedor = new RegistroProveedor.PanelRedondeado(10, new Color(226, 234, 206));
        panelDatosProveedor.setPreferredSize(new Dimension(400, 650));
        panelDatosProveedor.setLayout(new BoxLayout(panelDatosProveedor, BoxLayout.Y_AXIS));
        panelDatosProveedor.setBorder(new EmptyBorder(20,20,20,20));
        
        panelContenedorFormulario.add(panelDatosProveedor);

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
        
        btnLogo = new JButton("Logo");
        btnLogo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogo.setBackground(new Color(246, 255, 197));
        
        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLogo);

        panelContenedorFormulario.add(panelBotones);
            btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cerrar();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardarDatosProveedor();
                txtNombre.setText("");
                txtTelefono.setText("");
                txtGmail.setText("");
                txtCodigoPostal.setText("");
                txtCalle.setText("");
                txtNumero.setText("");
                
            }
        });
        
        btnLogo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    imagenValido = true;
                    arch = new File(obtenerDireccionLogo());
                    imagen = ImageIO.read(arch);
                    logo = new ImageIcon(imagen);
                    JImagen.setIcon(logo);
                } catch(IOException e){
                    System.out.print(e);
                }
            }
        });
        
        configurarCampoTextoCodigoPostal();
        configurarComboBoxColonia();
        configurarCampoTextoTelefono();
        configurarCampoTextoGmail();
        configurarCampoTextoNumero();
        configurarCampoTextoCalle();
        configurarCampoTextoNombre();
        
    }
    private void crearComponentesPanelDatos() {
            // Nombre
            JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelNombre.setOpaque(false);

            etqNombre = new JLabel("Nombre:");
            etqNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));

            txtNombre = new JTextField(20);
            txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtNombre.setEditable(true);
            txtNombre.setEnabled(true);
            txtNombre.setText("rafael");

            panelNombre.add(etqNombre);
            panelNombre.add(txtNombre);

            panelDatosProveedor.add(panelNombre);

            // Gmail
            JPanel panelGmail = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelGmail.setOpaque(false);

            etqGmail = new JLabel("Gmail:");
            etqGmail.setFont(new Font("Segoe UI", Font.BOLD, 14));

            txtGmail = new JTextField(20);
            txtGmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtGmail.setEditable(true);
            txtGmail.setEnabled(true);
            txtGmail.setText("luis@gmail.com");

            panelGmail.add(etqGmail);
            panelGmail.add(txtGmail);

            panelDatosProveedor.add(panelGmail);

            // Mensaje validación Gmail
            JPanel panelMensajeGmail = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelMensajeGmail.setOpaque(false);

            etqMensajeValidacionGmail = new JLabel("tiene que tener el formato Correcto");
            etqMensajeValidacionGmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            etqMensajeValidacionGmail.setForeground(Color.RED);

            panelMensajeGmail.add(etqMensajeValidacionGmail);
            panelDatosProveedor.add(panelMensajeGmail);

            // Telefono
            JPanel panelTelefono = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelTelefono.setOpaque(false);

            etqTelefono = new JLabel("Telefono:");
            etqTelefono.setFont(new Font("Segoe UI", Font.BOLD, 14));
           
            txtTelefono = new JTextField(20);
            txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtTelefono.setText("6441500616");
            
            panelTelefono.add(etqTelefono);
            panelTelefono.add(txtTelefono);

            panelDatosProveedor.add(panelTelefono);

            // Mensaje validación Telefono
            JPanel panelMensajeTelefono = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelMensajeTelefono.setOpaque(false);

            etqMensajeValidacionTelefono = new JLabel("debe contener 10 digitos");
            etqMensajeValidacionTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            etqMensajeValidacionTelefono.setForeground(Color.RED);

            panelMensajeTelefono.add(etqMensajeValidacionTelefono);
            panelDatosProveedor.add(panelMensajeTelefono);
            // Código Postal
            JPanel panelCodigoPostal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelCodigoPostal.setOpaque(false);

            etqCodigoPostal = new JLabel("Código Postal:");
            etqCodigoPostal.setFont(new Font("Segoe UI", Font.BOLD, 14));

            txtCodigoPostal = new JTextField(20);
            txtCodigoPostal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtCodigoPostal.setText("85000");

            panelCodigoPostal.add(etqCodigoPostal);
            panelCodigoPostal.add(txtCodigoPostal);

            JPanel panelSeparador = new JPanel();

            panelSeparador.setOpaque(false);

            panelDatosProveedor.add(panelSeparador);

            panelDatosProveedor.add(panelCodigoPostal);

            // Mensaje validación código postal
            JPanel panelMensajeCodigoPostal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelMensajeCodigoPostal.setOpaque(false);

            etqMensajeValidacionCodigoPostal = new JLabel("debe de existir");
            etqMensajeValidacionCodigoPostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            etqMensajeValidacionCodigoPostal.setForeground(Color.RED);

            panelMensajeCodigoPostal.add(etqMensajeValidacionCodigoPostal);
            panelDatosProveedor.add(panelMensajeCodigoPostal);

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

            panelDatosProveedor.add(panelColonia);

            // Calle
            JPanel panelCalle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelCalle.setOpaque(false);

            etqCalle = new JLabel("Calle:");
            etqCalle.setFont(new Font("Segoe UI", Font.BOLD, 14));

            txtCalle = new JTextField(20);
            txtCalle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtCalle.setText("san marino");

            panelCalle.add(etqCalle);
            panelCalle.add(txtCalle);
            panelCalle.add(new JLabel());

            panelDatosProveedor.add(panelCalle);

            // Numero
            JPanel panelNumero = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelNumero.setOpaque(false);

            etqNumero = new JLabel("Numero:");
            etqNumero.setFont(new Font("Segoe UI", Font.BOLD, 14));

            txtNumero = new JTextField(20);
            txtNumero.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtNumero.setEditable(true);
            txtNumero.setEnabled(true);
            txtNumero.setText("654");

            panelNumero.add(etqNumero);
            panelNumero.add(txtNumero);

            panelDatosProveedor.add(panelNumero);
            
            // imagen
            JPanel panelImagen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelImagen.setOpaque(false);
          
            etqImagen = new JLabel("Logo:");
            etqImagen.setFont(new Font("Segoe UI", Font.BOLD, 14));
            try{
                arch = new File("src/main/logo/imagenNulo.png");
                imagen = ImageIO.read(arch);
                logo = new ImageIcon(imagen);
                JImagen = new JLabel(logo); 
            } catch(IOException e){
                System.out.print(e);
            }
            
            
            
            panelImagen.add(etqImagen);
            panelImagen.add(JImagen);
            panelDatosProveedor.add(panelImagen);

        }


        private void habilitarBotonGuardar() {
            if(codigoPostalValido && gmailValida && telefonoValido && imagenValido
                    && nombreValido && calleValida && numeroValido ) {
                btnGuardar.setEnabled(true);
            } else {
                btnGuardar.setEnabled(false);
            }
        }

        public void guardarDatosProveedor() {
            System.out.println(nombreEnvio); 
            System.out.println(telefonoEnvio);
            System.out.println(GmailEnvio);
            System.out.println(imagenEnvio);
            System.out.println(codigoPostalEnvio);
            System.out.println(coloniaEnvio); 
            System.out.println(calleEnvio);
            System.out.println(numeroEnvio);
            control.guardarDatosProveedor(nombreEnvio, telefonoEnvio,GmailEnvio, 
                    imagenEnvio, codigoPostalEnvio,
                    coloniaEnvio, calleEnvio,numeroEnvio);      
        }
        
        public String obtenerDireccionLogo(){
            JFileChooser selector = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo imágenes PNG", "png");
            selector.setFileFilter(filtro);
            int resultado = selector.showOpenDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = selector.getSelectedFile();
                try {
                    imagen = ImageIO.read(archivo);
                    if (imagen == null) {
                        JOptionPane.showMessageDialog(null, "El archivo seleccionado no es una imagen válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    String rutaArchivo = archivo.getAbsolutePath();
                    String rutaProyecto = new File("src").getAbsoluteFile().getParentFile().getAbsolutePath();
                    if (rutaArchivo.startsWith(rutaProyecto)) {
                        String rutaRelativa = rutaArchivo.substring(rutaProyecto.length() + 1); 
                        rutaRelativa = rutaRelativa.replace(File.separatorChar, '/');
                        return rutaRelativa;
                    } else {
                        imagenEnvio = "src/main/logo/" + archivo.getName();
                        return "src/main/logo/" + archivo.getName();
                    }

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }

            return null; 
        }
    @Override
    public void actualizarDatosEncabezado() {
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
         @Override
        public void hacerVisible(boolean visible) {
            setVisible(visible);
        }

    @Override
    public void cerrar() {
        dispose();
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
                        codigoPostalValido = false;
                    } else {
                        txtCodigoPostal.setForeground(Color.BLACK);
                        etqMensajeValidacionCodigoPostal.setText(" ");

                        List<String> listaColonias = ((List<String>)datosDerivadosDireccion[0]);

                        comboBoxColonia.setModel(new DefaultComboBoxModel(listaColonias.toArray()));

                        codigoPostalEnvio = txtCodigoPostal.getText();
                        codigoPostalValido = true;
                    }
                } else if(nuevoCodigoPostal.isBlank()) {
                    txtCodigoPostal.setForeground(Color.RED);
                    etqMensajeValidacionCodigoPostal.setText("Este campo es obligatorio");
                    comboBoxColonia.setModel(new DefaultComboBoxModel(new Object[0]));
                    codigoPostalValido = false;
                } else {
                    txtCodigoPostal.setForeground(Color.RED);
                    etqMensajeValidacionCodigoPostal.setText("El código postal debe componerse de 5 dígitos");
                    codigoPostalValido = false;
                    comboBoxColonia.setModel(new DefaultComboBoxModel(new Object[0]));
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
                    numeroValido = true;
                    numeroEnvio = txtNumero.getText();
                } else if(nuevoNumero.isBlank()) {

                    btnGuardar.setEnabled(false);
                    numeroValido = false;
                } else {
                    txtNumero.setForeground(Color.RED);
                    btnGuardar.setEnabled(false);
                    numeroValido = false;
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

                if (nuevoTelefono.matches("\\d{10}")) {
                    etqMensajeValidacionTelefono.setText(" ");
                    telefonoValido = true;
                    telefonoEnvio = txtTelefono.getText();
                    txtTelefono.setForeground(Color.BLACK);
                } else if(nuevoTelefono.isBlank()) {
                    etqMensajeValidacionTelefono.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    telefonoValido = false;
                } else {
                    txtTelefono.setForeground(Color.RED);
                    etqMensajeValidacionTelefono.setText("Debe ingresar un número de telefono");
                    btnGuardar.setEnabled(false);
                    telefonoValido = false;
                }
                habilitarBotonGuardar();
            }
        });
    }
    
    public void configurarCampoTextoGmail() {
        txtGmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarGmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarGmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarGmail();
            }

            private void validarGmail(){
                String nuevoGmail = txtGmail.getText();

                if (nuevoGmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    etqMensajeValidacionGmail.setText("");
                    gmailValida = true;
                    GmailEnvio = txtGmail.getText();
                    txtGmail.setForeground(Color.BLACK);
                } else if(nuevoGmail.isBlank()) {
                    etqMensajeValidacionGmail.setText("Este campo es obligatorio");
                    btnGuardar.setEnabled(false);
                    gmailValida = false;
                } else {
                    txtGmail.setForeground(Color.RED);
                    etqMensajeValidacionGmail.setText("Debe ingresar un correos");
                    btnGuardar.setEnabled(false);
                    gmailValida = false;
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
                    calleValida = true;
                    calleEnvio = txtCalle.getText();
                } else {
                    calleValida = false;
                }
                habilitarBotonGuardar();
            }
        });
    }
    
     public void configurarCampoTextoNombre() {
        txtNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarNombre();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarNombre();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarNombre();
            }

            public void validarNombre() {
                String nombre = txtNombre.getText();

                if(!nombre.isBlank()) {
                    nombreValido = true;
                    nombreEnvio = txtNombre.getText();
                } else {
                    nombreValido = false;
                }
                habilitarBotonGuardar();
            }
        });
    }
     
}
