package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IDireccion;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Direccion extends JFrame implements IVista, IDireccion {

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
    
    
    
    public Direccion(ControlCompra control, Integer idCliente) {
        initComponents();
        configurarCampoTextoCodigoPostal();
        configurarCampoTextoNumero();
        configurarCampoTextoCalle();
        setIconImage(iconoPropio);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Actualizar de usuario");
        this.control = control;
        this.idCliente = idCliente;
        encabezado = new Encabezado(control, idCliente, this);
        panelPrincipal.add(encabezado, BorderLayout.NORTH);
        
    }
    
    public void configurarCampoTextoCodigoPostal(){
        
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
                if(!primerCambioCodigoPostalRealizado){
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

                            } else{
                                txtCodigoPostal.setForeground(Color.BLACK);
                                etqMensajeValidacionCodigoPostal.setVisible(false);

                                txtColonia.setText(datosDerivadosDireccion[0]);
                                txtCiudad.setText(datosDerivadosDireccion[1]);
                                txtEstado.setText(datosDerivadosDireccion[2]);
                                
                                codigoPostalEnvio = txtCodigoPostal.getText();
                                
                                codigoPostalValido = true;
                            } 


                    } else if(nuevoCodigoPostal.isBlank()){
                        txtCodigoPostal.setForeground(Color.RED);
                        etqMensajeValidacionCodigoPostal.setText("Este campo es obligatorio");
                        etqMensajeValidacionCodigoPostal.setVisible(true);
                        txtColonia.setText("");
                        txtCiudad.setText("");
                        txtEstado.setText("");
                        codigoPostalValido = false;
                    } else{
                        
                        txtCodigoPostal.setForeground(Color.RED);
                        etqMensajeValidacionCodigoPostal.setText("El código postal debe componerse de 5 dígitos");
                        etqMensajeValidacionCodigoPostal.setVisible(true);
                        codigoPostalValido = false;
                        txtColonia.setText("");
                        txtCiudad.setText("");
                        txtEstado.setText("");
                    }
                    
                    habilitarBotonGuardar();
                } else{
                    primerCambioCodigoPostalRealizado = true;
                }
                
            }
        });
    }
    
    public void configurarCampoTextoNumero(){
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
                    
                } else if(nuevoNumero.isBlank()){
                    etqMensajeValidacionNumero.setText("Este campo es obligatorio");
                    etqMensajeValidacionNumero.setVisible(true);
                    btnGuardar.setEnabled(false);
                    numeroValido = false;
                    
                } else{
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
    
    public void configurarCampoTextoCalle(){
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
            
            public void validarCalle(){
                String calle = txtCalle.getText();
                
                if(!calle.isBlank()){
                    etqMensajeValidacionCalle.setVisible(false);
                    calleValida = true;
                    calleEnvio = txtCalle.getText();
                    
                } else{
                    etqMensajeValidacionCalle.setText("Este campo es obligatorio");
                    etqMensajeValidacionCalle.setVisible(true);
                    calleValida = false;
                    
                }
                habilitarBotonGuardar();
            }
        
        });
    }
    
    private void habilitarBotonGuardar(){
        if(codigoPostalValido && calleValida && numeroValido){
            btnGuardar.setEnabled(true);
        } else{
            btnGuardar.setEnabled(false);
        }
    }
    
    
    public void guardarDatosDireccionCliente(){
        
        control.actualizarDatosDireccionCliente(
                Arrays.asList(idCliente, numeroEnvio, calleEnvio, codigoPostalEnvio), 
                this);
    }
    
    @Override
    public void setCodigoPostalEnvio(String codigoPostalEnvio){
        this.primerCambioCodigoPostalRealizado = false;
        this.codigoPostalEnvio = codigoPostalEnvio;
        this.txtCodigoPostal.setText(codigoPostalEnvio);
    }
    
    @Override
    public void setCalleEnvio(String calleEnvio){
        this.calleEnvio = calleEnvio;
        this.txtCalle.setText(calleEnvio);
    }
    
    @Override
    public void setNumeroEnvio(String numeroEnvio){
        this.numeroEnvio = numeroEnvio;
        this.txtNumero.setText(numeroEnvio);
    }
    
    @Override
    public void setEstadoEnvio(String estadoEnvio){
        this.estadoEnvio = estadoEnvio;
        this.txtEstado.setText(estadoEnvio);
    }
    
    @Override
    public void setCiudadEnvio(String ciudadEnvio){
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelContenedorFormulario = new javax.swing.JPanel();
        panelDatosDireccion = new RoundedPanel(50, new Color(226, 234, 206));
        etqCodigoPostal = new javax.swing.JLabel();
        etqEstado = new javax.swing.JLabel();
        etqCiudad = new javax.swing.JLabel();
        etqColonia = new javax.swing.JLabel();
        etqNumero = new javax.swing.JLabel();
        etqCalle = new javax.swing.JLabel();
        txtCodigoPostal = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtColonia = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtCalle = new javax.swing.JTextField();
        etqMensajeValidacionNumero = new javax.swing.JLabel();
        etqMensajeValidacionCodigoPostal = new javax.swing.JLabel();
        etqMensajeValidacionCalle = new javax.swing.JLabel();
        etqActualizarDireccionEnvio = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(217, 217, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelDatosDireccion.setBackground(new java.awt.Color(238, 238, 238));
        panelDatosDireccion.setPreferredSize(new java.awt.Dimension(1188, 789));

        etqCodigoPostal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqCodigoPostal.setText("Código Postal:");

        etqEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqEstado.setText("Estado:");

        etqCiudad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqCiudad.setText("Ciudad:");

        etqColonia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqColonia.setText("Colonia:");

        etqNumero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqNumero.setText("Número:");

        etqCalle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqCalle.setText("Calle:");

        txtCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigoPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoPostalActionPerformed(evt);
            }
        });

        txtEstado.setEditable(false);
        txtEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEstado.setEnabled(false);

        txtCiudad.setEditable(false);
        txtCiudad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCiudad.setEnabled(false);

        txtColonia.setEditable(false);
        txtColonia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtColonia.setEnabled(false);

        txtNumero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCalle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalleActionPerformed(evt);
            }
        });

        etqMensajeValidacionNumero.setBackground(new java.awt.Color(255, 51, 51));
        etqMensajeValidacionNumero.setForeground(new java.awt.Color(255, 0, 0));
        etqMensajeValidacionNumero.setText("El número de su doimicilio debe ser un número entero");
        etqMensajeValidacionNumero.setVisible(false);
        etqMensajeValidacionNumero.setForeground(Color.RED);

        etqMensajeValidacionCodigoPostal.setBackground(new java.awt.Color(0, 0, 0));
        etqMensajeValidacionCodigoPostal.setForeground(new java.awt.Color(255, 51, 51));
        etqMensajeValidacionCodigoPostal.setText("El código postal debe componerse de 5 dígitos");
        etqMensajeValidacionCodigoPostal.setVisible(false);
        etqMensajeValidacionCodigoPostal.setForeground(Color.RED);

        etqMensajeValidacionCalle.setForeground(new java.awt.Color(255, 0, 0));
        etqMensajeValidacionCalle.setText("Este campo es obligatorio");

        javax.swing.GroupLayout panelDatosDireccionLayout = new javax.swing.GroupLayout(panelDatosDireccion);
        panelDatosDireccion.setLayout(panelDatosDireccionLayout);
        panelDatosDireccionLayout.setHorizontalGroup(
            panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosDireccionLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosDireccionLayout.createSequentialGroup()
                        .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etqEstado)
                            .addComponent(etqCodigoPostal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etqMensajeValidacionCodigoPostal)))
                    .addGroup(panelDatosDireccionLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etqColonia)
                            .addComponent(etqCiudad)
                            .addComponent(etqNumero)
                            .addComponent(etqCalle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etqMensajeValidacionNumero)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etqMensajeValidacionCalle))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panelDatosDireccionLayout.setVerticalGroup(
            panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosDireccionLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqCodigoPostal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqMensajeValidacionCodigoPostal)
                .addGap(24, 24, 24)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqEstado))
                .addGap(29, 29, 29)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqCiudad))
                .addGap(30, 30, 30)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqColonia))
                .addGap(27, 27, 27)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqMensajeValidacionNumero)
                .addGap(26, 26, 26)
                .addGroup(panelDatosDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqCalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqMensajeValidacionCalle)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        etqActualizarDireccionEnvio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqActualizarDireccionEnvio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etqActualizarDireccionEnvio.setText("Actualizar dirección de envío");

        btnCancelar.setBackground(new java.awt.Color(246, 255, 197));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(246, 255, 197));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContenedorFormularioLayout = new javax.swing.GroupLayout(panelContenedorFormulario);
        panelContenedorFormulario.setLayout(panelContenedorFormularioLayout);
        panelContenedorFormularioLayout.setHorizontalGroup(
            panelContenedorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorFormularioLayout.createSequentialGroup()
                .addGroup(panelContenedorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorFormularioLayout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(etqActualizarDireccionEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContenedorFormularioLayout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(panelDatosDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContenedorFormularioLayout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        panelContenedorFormularioLayout.setVerticalGroup(
            panelContenedorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorFormularioLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(etqActualizarDireccionEnvio)
                .addGap(60, 60, 60)
                .addComponent(panelDatosDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(panelContenedorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelContenedorFormulario, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatosDireccionCliente();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        control.mostrarProductosVenta(this);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCodigoPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoPostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPostalActionPerformed

    private void txtCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel etqActualizarDireccionEnvio;
    private javax.swing.JLabel etqCalle;
    private javax.swing.JLabel etqCiudad;
    private javax.swing.JLabel etqCodigoPostal;
    private javax.swing.JLabel etqColonia;
    private javax.swing.JLabel etqEstado;
    private javax.swing.JLabel etqMensajeValidacionCalle;
    private javax.swing.JLabel etqMensajeValidacionCodigoPostal;
    private javax.swing.JLabel etqMensajeValidacionNumero;
    private javax.swing.JLabel etqNumero;
    private javax.swing.JPanel panelContenedorFormulario;
    private javax.swing.JPanel panelDatosDireccion;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarNombreApellidoCliente();
        encabezado.ocultarBarraBusqueda();
        encabezado.ocultarDireccionCliente();
        encabezado.ocultarBtnNumeroCarritoCompras();
    }
    
    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 10;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;

        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());

        }
    }

    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
}
