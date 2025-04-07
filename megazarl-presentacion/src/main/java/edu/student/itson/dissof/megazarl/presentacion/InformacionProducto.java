package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class InformacionProducto extends JFrame implements IInformacionProducto, IVista {

    private ControlCompra control;
    private int cantidad;
    private JLabel cantidadLabel;
    private Integer idCliente;
    private Integer idProducto;

    private Encabezado encabezado;

    private JPanel panelGeneral;
    private JPanel panelProducto;
    private JPanel panelContador;
    private JPanel panelImagen;
    private JPanel panelProducto2;
    private JPanel panelDetalles;
    private JLabel avisoMaximoProductos;

    private JButton botonAgregarCarrito;
    private JButton btnMenos;
    private JButton btnMas;

    private final int MARGEN_VERTICAL_PANELS = 250;

    public InformacionProducto(ControlCompra control, Integer idCliente) {
        setTitle("Semillas MEGAZARL - Información de producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
        
        this.idCliente = idCliente;
        this.control = control;
        initComponents();
    }

    private void initComponents() {
    
        encabezado = new Encabezado(control, idCliente, this);
        this.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel();
        panelGeneral.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(panelGeneral, BorderLayout.CENTER);

        // Panel principal con borde redondeado
        panelProducto = new JPanel();
        panelProducto.setLayout(new BoxLayout(panelProducto, BoxLayout.Y_AXIS));

        panelGeneral.add(panelProducto);

        panelProducto2 = new PanelRedondeado(10, Color.WHITE);;
        panelProducto2.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelProducto.add(panelProducto2);

        panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.Y_AXIS));
        panelImagen.setPreferredSize(new Dimension(450, 520));
        panelProducto2.add(panelImagen);

        panelDetalles = new JPanel();
        panelDetalles.setOpaque(false);
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelProducto2.add(panelDetalles);

        cantidadLabel = new JLabel();
    }

    @Override
    public void setProducto(Map<String, Object> informacionProducto) {
        panelImagen.removeAll();
        panelDetalles.removeAll();
        cantidad = 0;
        cantidadLabel.setText("0");

        // Imagen principal
        ImageIcon iconoImagenProducto = new ImageIcon(getClass().getResource((String) informacionProducto.get("DireccionImagenProducto")));
        Image imagenProducto = iconoImagenProducto.getImage().getScaledInstance(440, 400, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoImagenProducto = new ImageIcon(imagenProducto);
        JLabel etqImagenProducto = new JLabel(nuevoIconoImagenProducto);

        JPanel panelImagenProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelImagenProducto.add(etqImagenProducto);
        panelImagen.add(panelImagenProducto);

        // Imagen proveedor
        ImageIcon iconoImagenProveedor = new ImageIcon(getClass().getResource((String) informacionProducto.get("DireccionImagenProveedor")));
        Image imagenProveedor = iconoImagenProveedor.getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoImagenProveedor = new ImageIcon(imagenProveedor);
        JLabel etqImagenProveedor = new JLabel(nuevoIconoImagenProveedor);

        JPanel panelEtqImagenProveedor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEtqImagenProveedor.add(etqImagenProveedor);
        panelImagen.add(panelEtqImagenProveedor);

        // Nombre y variedad
        JLabel etqNombreVariedad = new JLabel((String) informacionProducto.get("Nombre") + " " + informacionProducto.get("Variedad"));
        Font fuenteEtqNombreVariedad = new Font("Segoe UI", Font.BOLD, 24);
        etqNombreVariedad.setFont(fuenteEtqNombreVariedad);

        JPanel panelNombreVariedad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNombreVariedad.setOpaque(false);
        panelNombreVariedad.add(etqNombreVariedad);

        panelDetalles.add(panelNombreVariedad);

        // Panel separador
        JPanel panelSeparador1 = new JPanel();
        panelSeparador1.setOpaque(false);
        panelDetalles.add(panelSeparador1);

        // Precio de producto.
        String cantidadSemillasProducto = informacionProducto.get("MilesSemillas") + "k semillas";

        String precioFormato = String.format("%,.2f", informacionProducto.get("Precio"));

        String partesPrecio[] = precioFormato.split("\\.");
        String parteEnteraPrecioProdcuto = partesPrecio[0];
        String parteDecimalPrecioProducto = partesPrecio[1];

        JLabel etqParteEnteraPrecioProducto = new JLabel("$" + parteEnteraPrecioProdcuto + ".");
        Font fuenteEtqParteEnteraPrecioProducto = new Font("Arial", Font.BOLD, 25);
        etqParteEnteraPrecioProducto.setFont(fuenteEtqParteEnteraPrecioProducto);

        JLabel etqParteDecimalPrecioProducto = new JLabel(parteDecimalPrecioProducto);
        Font fuenteEtqParteDecimalPrecioProducto = new Font("Arial", Font.BOLD, 20);
        etqParteDecimalPrecioProducto.setFont(fuenteEtqParteDecimalPrecioProducto);

        JLabel petqPrecioCantSemillas = new JLabel(" (" + cantidadSemillasProducto + "k semillas)");
        Font fuenteEtqCantSemillas = new Font("Arial", Font.BOLD, 20);
        petqPrecioCantSemillas.setFont(fuenteEtqCantSemillas);

        JPanel panelPrecioCantSemillas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPrecioCantSemillas.setOpaque(false);

        panelPrecioCantSemillas.add(etqParteEnteraPrecioProducto);
        panelPrecioCantSemillas.add(etqParteDecimalPrecioProducto);
        panelPrecioCantSemillas.add(petqPrecioCantSemillas);
        panelDetalles.add(panelPrecioCantSemillas);

        // Descripción
        String descripcionProducto = "<html>";

        String[] caracteresDescripcion = ((String) informacionProducto.get("Descripcion")).split("");

        Pattern patron = Pattern.compile("^[\\s]");
        Matcher matcher;

        int caracteresRenglon = 0;
        for (int i = 0; i < caracteresDescripcion.length; i++) {
            matcher = patron.matcher(caracteresDescripcion[i]);

            if (caracteresRenglon >= 48 && matcher.matches()) {
                descripcionProducto += "<br>";
                caracteresRenglon = 0;
            } else {
                descripcionProducto += caracteresDescripcion[i];
                caracteresRenglon++;
            }
        }
        descripcionProducto += "</html>";

        JLabel etqDescripcion = new JLabel(descripcionProducto);
        Font fuenteEtqDescripcion = new Font("Segoe UI", Font.PLAIN, 20);
        etqDescripcion.setFont(fuenteEtqDescripcion);

        JPanel panelDescripcion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDescripcion.setOpaque(false);
        panelDescripcion.add(etqDescripcion);
        panelDetalles.add(panelDescripcion);

        // Nombre de Proveedor
        JLabel etqNombreProveedor = new JLabel("Proveedor: " + (String) informacionProducto.get("NombreProveedor"));
        Font fuenteEtqNombreProveedor = new Font("Segoe UI", Font.BOLD, 17);
        etqNombreProveedor.setFont(fuenteEtqNombreProveedor);

        JPanel panelProveedor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelProveedor.setOpaque(false);
        panelProveedor.add(etqNombreProveedor);
        panelDetalles.add(panelProveedor);

        // Panel separador
        JPanel panelSeparador2 = new JPanel();
        panelSeparador2.setPreferredSize(new Dimension(panelDetalles.getWidth(), 50));
        panelSeparador2.setOpaque(false);
        panelDetalles.add(panelSeparador2);

        // Seleccion de cantidad
        JPanel panelSeleccionCantidad = new JPanel();
        panelSeleccionCantidad.setOpaque(false);
        panelSeleccionCantidad.setLayout(new BoxLayout(panelSeleccionCantidad, BoxLayout.Y_AXIS));
        panelDetalles.add(panelSeleccionCantidad);

        JPanel panelEtqSeleccionarCantidad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEtqSeleccionarCantidad.setOpaque(false);

        JLabel etqSeleccionarCantidad = new JLabel("Seleccionar cantidad");
        Font fuenteEtqSeleccionarCantidad = new Font("Segoe UI", Font.BOLD, 19);
        etqSeleccionarCantidad.setFont(fuenteEtqSeleccionarCantidad);
        etqSeleccionarCantidad.setForeground(new Color(40, 101, 162));
        panelEtqSeleccionarCantidad.add(etqSeleccionarCantidad);

        panelSeleccionCantidad.add(panelEtqSeleccionarCantidad);

        JPanel panelSeleccionCantidad2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccionCantidad2.setOpaque(false);

        panelContador = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelContador.setOpaque(false);

        btnMenos = new JButton("-");

        btnMenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCantidad(-1);
                
                if(control.verificarExistenciasProducto(idProducto) > Integer.parseInt(cantidadLabel.getText())){
                    btnMas.setEnabled(true);
                    avisoMaximoProductos.setText("");
                }
                
                if(Integer.parseInt(cantidadLabel.getText()) == 0){
                    btnMenos.setEnabled(false);
                }  
            }
        });

        
        btnMas = new JButton("+");
        
        avisoMaximoProductos = new JLabel();
        avisoMaximoProductos.setFont(new Font("Segoe UI", Font.BOLD, 15));
        avisoMaximoProductos.setForeground(new Color(225, 49, 12));
                
        btnMas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCantidad(1);
                
                if(control.verificarExistenciasProducto(idProducto) <= Integer.parseInt(cantidadLabel.getText())){
                    btnMas.setEnabled(false);
                    avisoMaximoProductos.setText("No hay más existencias");
                    
                } else{
                    avisoMaximoProductos.setText("");
                }
                
                if(Integer.parseInt(cantidadLabel.getText()) > 0){
                    btnMenos.setEnabled(true);
                }  
            }
        });

        panelContador.add(btnMenos);
        panelContador.add(cantidadLabel);
        panelContador.add(btnMas);
        panelContador.add(avisoMaximoProductos);

        panelSeleccionCantidad2.add(panelContador);

        panelDetalles.add(panelSeleccionCantidad);
        panelDetalles.add(panelSeleccionCantidad2);

        // Panel separador
        JPanel panelSeparador3 = new JPanel();
        panelSeparador3.setPreferredSize(new Dimension(panelDetalles.getWidth(), 50));
        panelSeparador3.setOpaque(false);
        panelDetalles.add(panelSeparador3);

        // Botones finales
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelBotones.setBackground(Color.WHITE);

        JButton botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonVolver.setBackground(new Color(0, 204, 0));
        botonVolver.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(botonVolver);

        botonAgregarCarrito = new JButton("Agregar al carrito");
        botonAgregarCarrito.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonAgregarCarrito.setBackground(new Color(0, 204, 0));
        botonAgregarCarrito.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(botonAgregarCarrito);
        botonAgregarCarrito.setEnabled(false);

        panelDetalles.add(panelBotones);

        this.idProducto = (Integer) informacionProducto.get("Id");

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.mostrarProductosVenta(InformacionProducto.this);
            }
        });

        botonAgregarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.agregarProductoCarrito(idCliente, InformacionProducto.this.idProducto,
                        Integer.parseInt(cantidadLabel.getText()), InformacionProducto.this);
            }
        });
    }

    private void actualizarCantidad(int cambio) {
        cantidad = Math.max(0, cantidad + cambio);
        cantidadLabel.setText(String.valueOf(cantidad));
        if (cantidad > 0) {
            botonAgregarCarrito.setEnabled(true);
        } else {
            botonAgregarCarrito.setEnabled(false);
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

    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarDireccionCliente();
        encabezado.mostrarNombreApellidoCliente();
        encabezado.mostrarBtnNumeroCarritoCompras();
        encabezado.ocultarBarraBusqueda();
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
