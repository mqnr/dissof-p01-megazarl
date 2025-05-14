package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.presentacion.utils.ImagenesUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SeleccionPaqueteria extends JFrame implements ISeleccionPaqueteria, IVista {

    private EncabezadoCompra encabezado;

    private JPanel panelCentral;
    private JPanel panelInstrucciones;
    private JPanel panelDireccionEnvio;
    private JScrollPane scrollPanePaqueterias;
    private JPanel panelPaqueterias;
    private JPanel panelCostoEnvio;
    private JPanel panelBotones;
    private JLabel labelCosto;
    private JLabel labelError;
    private Long idCliente;
    private boolean envioGratis;
    
    private String calleEnvio;
    private String numeroEnvio;
    private String coloniaEnvio;
    private String codigoPostalEnvio;

    private int ANCHO_IMAGEN_PAQUETERIA = 160;
    private int ALTO_IMAGEN_PAQUETERIA = 110;

    private int MARGEN_VERTICAL_IMAGENES_PAQUETERIA = 10;
    private int MARGEN_HORIZONTAL_IMAGENES_PAQUETERIA = 10;
    
    private Color COLOR_FONDO = new Color(88, 69, 50);
    private Color COLOR_FONDO_DIRECCION_PAQUETERIAS = new Color(255, 251, 242);
    
    private Font FUENTE_TEXTO_BOTONES = new Font("Segoe UI", Font.BOLD, 14);

    private final ControlCompra control;
    
    private Long idPaqueteriaSeleccionada;

    public SeleccionPaqueteria(ControlCompra control, Long idCliente) {
        setTitle("Semillas MEGAZARL - Seleccionar paquetería");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
        this.control = control;
        this.idCliente = idCliente;
        initComponents();
    }

    private void initComponents() {
        
        encabezado = new EncabezadoCompra(control, idCliente, this);
                
        this.add(encabezado, BorderLayout.NORTH);

        panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(COLOR_FONDO);

        this.add(panelCentral, BorderLayout.CENTER);

        panelInstrucciones = new JPanel();
        panelInstrucciones.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelInstrucciones.setLayout(new BoxLayout(panelInstrucciones, BoxLayout.Y_AXIS));
        panelInstrucciones.setOpaque(false);

        JLabel etqTituloInstrucciones = new JLabel("Selección de Paquetería");
        etqTituloInstrucciones.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel etqInstrucciones = new JLabel("Seleccione la paqueteria que realizará el envío de sus productos");
        etqInstrucciones.setFont(new Font("Arial", Font.PLAIN, 18));
        
        etqTituloInstrucciones.setForeground(Color.WHITE);
        etqInstrucciones.setForeground(Color.WHITE);

        panelInstrucciones.add(etqTituloInstrucciones);
        panelInstrucciones.add(etqInstrucciones);

        JPanel panelInstruccionesFila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInstruccionesFila.add(panelInstrucciones);
        panelInstruccionesFila.setOpaque(false);

        panelCentral.add(panelInstruccionesFila);

        panelDireccionEnvio = new JPanel();
        panelDireccionEnvio.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelDireccionEnvio.setLayout(new BoxLayout(panelDireccionEnvio, BoxLayout.Y_AXIS));
        panelDireccionEnvio.setBackground(COLOR_FONDO_DIRECCION_PAQUETERIAS);

        String[] datosDireccionCliente = control.recuperarDatosDireccionCliente(idCliente);
        JLabel etqTituloDireccionEnvio = new JLabel("Dirección de envío: ");
        etqTituloDireccionEnvio.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel etqDireccion = new JLabel("Dirección de Envío: "+ datosDireccionCliente[0] + " " + datosDireccionCliente[1] + " " 
                + datosDireccionCliente[2] + " , C.P." + datosDireccionCliente[3]);
        etqDireccion.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panelDireccionEnvioFila = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelDireccionEnvioFila.setOpaque(false);

        panelDireccionEnvio.add(etqTituloDireccionEnvio);
        panelDireccionEnvio.add(etqDireccion);
        panelDireccionEnvioFila.add(panelDireccionEnvio);
        panelCentral.add(panelDireccionEnvioFila);

        panelPaqueterias = new JPanel();
        panelPaqueterias.setOpaque(false);
        
        panelCostoEnvio = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCostoEnvio.setOpaque(false);
        
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setOpaque(false);
    }

    @Override
    public void setPaqueterias(HashMap<Long, String> datosPaqueterias) {
        
        
        panelPaqueterias.removeAll();
        panelCostoEnvio.removeAll();
        panelBotones.removeAll();
        
        if(scrollPanePaqueterias != null){
            panelCentral.remove(scrollPanePaqueterias);
        }

        ButtonGroup grupoPaqueterias = new ButtonGroup();

        panelPaqueterias.setLayout(new GridLayout(Math.ceilDiv(datosPaqueterias.size(), 5), 5));

        for (HashMap.Entry<Long, String> datosPaqueteria : datosPaqueterias.entrySet()) {

            String direccionImagenPaqueteria = datosPaqueteria.getValue();
            
            JPanel panelRadioImagenPaqueteria = new JPanel(new BorderLayout());
            panelRadioImagenPaqueteria.setBackground(COLOR_FONDO_DIRECCION_PAQUETERIAS);

            ImageIcon iconoImagenPaqueteria = ImagenesUtils.obtenerImagen(direccionImagenPaqueteria);
                    
            ImageIcon nuevoIconoImagenPaqueteria = ImagenesUtils.redimensionarImagen(
                    iconoImagenPaqueteria, 
                    ANCHO_IMAGEN_PAQUETERIA, 
                    ALTO_IMAGEN_PAQUETERIA);

            JLabel etqImagenPaqueteria = new JLabel(nuevoIconoImagenPaqueteria);
            JRadioButton radioPaqueteria = new JRadioButton();
            radioPaqueteria.setOpaque(false);
            grupoPaqueterias.add(radioPaqueteria);

            JPanel panelRadioPaquteria = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelRadioPaquteria.setOpaque(false);
            panelRadioPaquteria.add(radioPaqueteria);
            
            radioPaqueteria.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!envioGratis){
                        Float costoEnvio = control.obtenerCostoEnvioPaqueteria(idCliente, datosPaqueteria.getKey());
                        if(costoEnvio != null){
                            labelCosto.setText("Costo de envío: $" + String.format("%,.2f",costoEnvio));
                        }
                        labelError.setVisible(false);
                        
                    }
                    idPaqueteriaSeleccionada = datosPaqueteria.getKey();
                }
            });

            panelRadioImagenPaqueteria.add(etqImagenPaqueteria, BorderLayout.NORTH);
            panelRadioImagenPaqueteria.add(panelRadioPaquteria, BorderLayout.CENTER);

            panelPaqueterias.add(panelRadioImagenPaqueteria);

        }
        
        scrollPanePaqueterias = new JScrollPane(
                panelPaqueterias, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelCentral.add(scrollPanePaqueterias);

        
        labelCosto = new JLabel();
        if(envioGratis){
            labelCosto.setText("Costo de envío: Gratuito");
        }
        labelCosto.setFont(new Font("Arial", Font.BOLD, 18));
        labelCosto.setForeground(Color.WHITE);
        
        panelCostoEnvio.add(labelCosto);

        labelError = new JLabel("Debes seleccionar una paquetería");
        labelError.setFont(new Font("Arial", Font.BOLD, 14));
        labelError.setForeground(Color.RED);
        labelError.setVisible(false);

        JPanel panelError = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelError.setOpaque(false);
        panelError.add(labelError);
        panelCentral.add(panelCostoEnvio);
        panelCentral.add(panelError);

        JButton btnCancelar = new JButton("Cancelar pedido");
        JButton btnContinuar = new JButton("Continuar");
        
        btnCancelar.setFont(FUENTE_TEXTO_BOTONES);
        btnContinuar.setFont(FUENTE_TEXTO_BOTONES);
        
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
                if (idPaqueteriaSeleccionada == null) {
                    labelError.setVisible(true);
                    return;
                }
                control.asignarPaqueteriaCarritoCliente(idCliente, idPaqueteriaSeleccionada, SeleccionPaqueteria.this);
            }
        });
    }

    
    @Override
    public void setEnvioGratis(boolean envioGratis){
        this.envioGratis = envioGratis;
    }
    
    @Override
    public void setNumeroEnvio(String numeroEnvio){
        this.numeroEnvio = numeroEnvio;
    }
    
    @Override
    public void setColoniaEnvio(String coloniaEnvio){
        this.coloniaEnvio = coloniaEnvio;
    }
    
    @Override
    public void setCodigoPostalEnvio(String codigoPostalEnvio){
        this.codigoPostalEnvio = codigoPostalEnvio;
    }
    
    @Override
    public void setCalleEnvio(String coloniaEnvio){
        this.coloniaEnvio = coloniaEnvio;
    }

    
    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarDireccionCliente();
        encabezado.mostrarNombreApellidoCliente();
        encabezado.ocultarBarraBusqueda();
        encabezado.ocultarBtnNumeroCarritoCompras();
        encabezado.ocultarBtnSalir();
    }
    
    @Override
    public void hacerVisible(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void cerrar() {
        idPaqueteriaSeleccionada = null;
        dispose();
    }
}
