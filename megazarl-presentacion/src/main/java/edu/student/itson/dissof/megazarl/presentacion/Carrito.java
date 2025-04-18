package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.presentacion.utilgui.ButtonBuilder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Carrito extends JFrame implements ICarrito, IVista {

    private ControlCompra control;
    private Integer idCliente;
    private Encabezado encabezado;
    private JPanel panelPrincipal;
    private JPanel panelContenedorCarrito;
    private boolean envioGratis = false;

    // Colores
    private final Color COLOR_GRIS_CLARO = new Color(240, 240, 240);
    private final Color COLOR_BOTON_AMARILLO = new Color(248, 241, 132);
    private final Color COLOR_TIEMPO_ESTIMADO = new Color(1, 88, 142);

    // Emojis
    private final String emojiBasura = new String(Character.toChars(0x1F5D1));

    public Carrito(ControlCompra control, Integer idCliente) {
        setTitle("Semillas MEGAZARL - Carrito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);

        this.control = control;
        this.idCliente = idCliente;

        
        encabezado = new Encabezado(control, idCliente, this);
        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout());

        panelPrincipal.add(this.encabezado, BorderLayout.NORTH);

        panelContenedorCarrito = new JPanel(new BorderLayout());
        panelPrincipal.add(panelContenedorCarrito, BorderLayout.CENTER);

        this.setContentPane(panelPrincipal);
    }

    @Override
    public void setProductos(List<Map<String, Object>> listaInformacionProductos) {
        panelContenedorCarrito.removeAll();

        panelContenedorCarrito.setBackground(COLOR_GRIS_CLARO);
        panelContenedorCarrito.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de contenido principal (del carrito)
        JPanel panelCarrito = new JPanel(new BorderLayout());
        panelCarrito.setBackground(Color.WHITE);
        panelCarrito.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Cabecera de carrito
        JPanel panelCarritoCabecera = new JPanel(new GridLayout(1, 2));
        panelCarritoCabecera.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel labelCarritoTitulo = new JLabel("Carrito");
        labelCarritoTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelCarritoTitulo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));

        JLabel labelPrecio = new JLabel("Precio");
        labelPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        labelPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPrecio.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));

        panelCarritoCabecera.add(labelCarritoTitulo);
        panelCarritoCabecera.add(labelPrecio);

        JPanel panelItemsCarrito = new JPanel();
        panelItemsCarrito.setLayout(new BoxLayout(panelItemsCarrito, BoxLayout.Y_AXIS));

        JScrollPane scrollPaneItemsCarrito = new JScrollPane(panelItemsCarrito, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        int numeroTotalProductos = 0;
        for (Map<String, Object> informacionProducto : listaInformacionProductos) {

            if(informacionProducto != null){
                Integer cantidadProducto = (Integer) informacionProducto.get("Cantidad");

                if (cantidadProducto > 0) {
                    numeroTotalProductos += cantidadProducto;

                    JLabel cantidadLabel = new JLabel(String.valueOf(cantidadProducto));

                    // Item del carrito
                    JPanel panelItemCarrito = new JPanel(new BorderLayout());
                    panelItemCarrito.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                    // Imagen del producto
                    JLabel imagenProducto = new JLabel();
                    ImageIcon iconProducto = new ImageIcon(getClass().getResource((String) informacionProducto.get("DireccionImagenProducto")));
                    // Redimensionar
                    Image imagen = iconProducto.getImage();
                    Image redimensionada = imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imagenProducto.setIcon(new ImageIcon(redimensionada));

                    imagenProducto.setPreferredSize(new Dimension(150, 150));
                    imagenProducto.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));

                    // Detalles de producto
                    JPanel panelDetallesProducto = new JPanel();
                    panelDetallesProducto.setLayout(new BoxLayout(panelDetallesProducto, BoxLayout.Y_AXIS));
                    panelDetallesProducto.setAlignmentX(Component.LEFT_ALIGNMENT);
                    panelDetallesProducto.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

                    JLabel labelNombreProducto = new JLabel((String) informacionProducto.get("Nombre") + " " + informacionProducto.get("Variedad"));
                    labelNombreProducto.setFont(new Font("Arial", Font.BOLD, 16));
                    labelNombreProducto.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JLabel labelProveedor = new JLabel("Proveedor: " + informacionProducto.get("NombreProveedor"));
                    labelProveedor.setFont(new Font("Arial", Font.PLAIN, 14));
                    labelProveedor.setAlignmentX(Component.LEFT_ALIGNMENT);

                    // Checkbox y cantidad
                    JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                    panelControles.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JLabel avisoMaximoProductos = new JLabel();
                    avisoMaximoProductos.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    avisoMaximoProductos.setForeground(new Color(225, 49, 12));
                    
                    JButton btnMenos = new ButtonBuilder()
                            .withText("-")
                            .withPreferredSize(30, 30)
                            .withEmptyMargin()
                            .build();

                    JButton btnMas = new ButtonBuilder()
                            .withText("+")
                            .withPreferredSize(30, 30)
                            .withEmptyMargin()
                            .build();

                    JButton btnEliminar = new ButtonBuilder()
                            .withText(emojiBasura)
                            .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 14))
                            .withPreferredSize(40, 30)
                            .withEmptyMargin()
                            .build();

                    btnEliminar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            control.eliminarProductoCarrito(idCliente, (Integer) informacionProducto.get("Id"),
                                    (Integer) informacionProducto.get("Cantidad"));
                        }
                    });

                    btnMas.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
 
                            
                            int disponibilidadProducto = control.verificarExistenciasProducto((Integer) informacionProducto.get("Id"));
                            control.agregarProductoCarrito(idCliente, (Integer) informacionProducto.get("Id"), 1, Carrito.this);
                            
                            if(disponibilidadProducto < Integer.parseInt(cantidadLabel.getText())){
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

                    btnMenos.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                              
                            control.eliminarProductoCarrito(idCliente, (Integer) informacionProducto.get("Id"), 1);
                            
                            
                            if(control.verificarExistenciasProducto((Integer) informacionProducto.get("Id")) > Integer.parseInt(cantidadLabel.getText())){
                                btnMas.setEnabled(true);
                                avisoMaximoProductos.setText("");
                            }

                            if(Integer.parseInt(cantidadLabel.getText()) == 0){
                                btnMenos.setEnabled(false);
                            }  
                        }
                    });

                    panelControles.add(btnMenos);
                    panelControles.add(cantidadLabel);
                    panelControles.add(btnMas);
                    panelControles.add(btnEliminar);
                    panelControles.add(avisoMaximoProductos);

                    panelDetallesProducto.add(labelNombreProducto);
                    panelDetallesProducto.add(Box.createVerticalStrut(5));
                    panelDetallesProducto.add(labelProveedor);
                    panelDetallesProducto.add(Box.createVerticalStrut(10));
                    panelDetallesProducto.add(panelControles);

                    // Precio
                    JPanel panelPrecio = new JPanel();
                    panelPrecio.setLayout(new BoxLayout(panelPrecio, BoxLayout.Y_AXIS));
                    panelPrecio.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

                    Double precioProducto = (Double) informacionProducto.get("Precio");

                    JLabel labelPrecioItem = new JLabel(String.format("$%,.2f",
                            precioProducto * cantidadProducto
                    ));

                    labelPrecioItem.setFont(new Font("Arial", Font.BOLD, 16));
                    labelPrecioItem.setAlignmentX(Component.RIGHT_ALIGNMENT);

                    panelPrecio.add(labelPrecioItem);

                    // Añadir componentes a panel de ítems de carrito
                    panelItemCarrito.add(imagenProducto, BorderLayout.WEST);
                    panelItemCarrito.add(panelDetallesProducto, BorderLayout.CENTER);
                    panelItemCarrito.add(panelPrecio, BorderLayout.EAST);

                    panelItemsCarrito.add(panelItemCarrito);
                }

            }
        }

        // Botones de abajo
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton botonSeguirComprando = new ButtonBuilder()
                .withFont(new Font("Arial", Font.BOLD, 14))
                .withBackground(COLOR_BOTON_AMARILLO)
                .withPreferredSize(180, 40)
                .withText("Seguir comprando")
                .onClick(e -> control.mostrarProductosVenta(Carrito.this))
                .build();

        JButton botonFinalizarCompra = new ButtonBuilder()
                .withFont(new Font("Arial", Font.BOLD, 14))
                .withBackground(COLOR_BOTON_AMARILLO)
                .withPreferredSize(180, 40)
                .withText("Finalizar la compra")
                .onClick(e -> control.mostrarSeleccionPaqueteria(idCliente, envioGratis, Carrito.this))
                .build();

        panelBotones.add(botonSeguirComprando);
        panelBotones.add(botonFinalizarCompra);

        // Añadir todos al panel del carrito
        panelCarrito.add(panelCarritoCabecera, BorderLayout.NORTH);
        panelCarrito.add(scrollPaneItemsCarrito, BorderLayout.CENTER);
        panelCarrito.add(panelBotones, BorderLayout.SOUTH);

        // Barra lateral para resumen
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBackground(Color.WHITE);
        panelLateral.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelLateral.setPreferredSize(new Dimension(250, 0));

        // Banner de precio
        JPanel panelBannerPrecio = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBannerPrecio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        Double[] informacionMontoEnvioGratuito = control.obtenerInformacionMontoEnvioGratuito(idCliente);
        Double montoActual = informacionMontoEnvioGratuito[0];
        Double montoEnvioGratuito = informacionMontoEnvioGratuito[1];

        JLabel labelPrecioTotal = new JLabel(String.format("$%,.2f / $%,.2f", montoActual, montoEnvioGratuito));
        labelPrecioTotal.setFont(new Font("Arial", Font.BOLD, 18));

        panelBannerPrecio.add(labelPrecioTotal);

        // Nota sobre envío gratis
        JPanel panelEnvio = new JPanel();
        panelEnvio.setLayout(new BoxLayout(panelEnvio, BoxLayout.Y_AXIS));
        panelEnvio.setBackground(Color.WHITE);
        panelEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEnvio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelLabelEnvio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelLabelEnvioGratis = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLabelEnvio.setOpaque(false);
        panelLabelEnvioGratis.setOpaque(false);

        JLabel labelEnvio = new JLabel();
        JLabel labelEnvioGratis = new JLabel();

        if (montoActual >= montoEnvioGratuito) {
            this.envioGratis = true;
            labelEnvio.setText("El pedido califica para");
            panelBannerPrecio.setBackground(new Color(50, 220, 150));

        } else {
            this.envioGratis = false;
            labelEnvio.setText("El pedido no califica para");
            panelBannerPrecio.setBackground(new Color(255, 164, 133));
        }

        labelEnvioGratis.setText("envío GRATIS");
        labelEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEnvioGratis.setFont(new Font("Arial", Font.BOLD, 14));
        labelEnvioGratis.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelLabelEnvio.add(labelEnvio);
        panelLabelEnvioGratis.add(labelEnvioGratis);

        panelEnvio.add(panelLabelEnvio);
        panelEnvio.add(panelLabelEnvioGratis);

        // Subtotal
        JPanel panelSubtotal = new JPanel();
        JPanel panelLabelSubtotal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelLabelCantidadSubtotal = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelSubtotal.setLayout(new BoxLayout(panelSubtotal, BoxLayout.Y_AXIS));
        panelSubtotal.setBackground(Color.WHITE);
        panelSubtotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelSubtotal = new JLabel("Subtotal (" + numeroTotalProductos + " producto/s):");
        labelSubtotal.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel labelCantidadSubtotal = new JLabel(String.format("$%.2f", montoActual));
        labelCantidadSubtotal.setFont(new Font("Arial", Font.BOLD, 14));
        labelCantidadSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);

        panelLabelSubtotal.add(labelSubtotal);
        panelLabelCantidadSubtotal.add(labelCantidadSubtotal);

        panelLabelSubtotal.setOpaque(false);
        panelLabelCantidadSubtotal.setOpaque(false);

        panelSubtotal.add(panelLabelSubtotal, BorderLayout.WEST);
        panelSubtotal.add(panelLabelCantidadSubtotal, BorderLayout.EAST);

        // Sección de tiempo estimado de preparación:
        JPanel tiempoEstimadoPreparacion = new JPanel();
        tiempoEstimadoPreparacion.setLayout(new BorderLayout());
        tiempoEstimadoPreparacion.setBackground(Color.WHITE);

        JLabel tiempoEstimadoPreparacionLabel1 = new JLabel("Tiempo estimado de");
        JLabel tiempoEstimadoPreparacionLabel2 = new JLabel("preparación de pedido: ");
        tiempoEstimadoPreparacionLabel1.setFont(new Font("Arial", Font.BOLD, 14));
        tiempoEstimadoPreparacionLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tiempoEstimadoPreparacionLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        tiempoEstimadoPreparacionLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        tiempoEstimadoPreparacionLabel1.setAlignmentY(Component.CENTER_ALIGNMENT);
        tiempoEstimadoPreparacionLabel2.setAlignmentY(Component.CENTER_ALIGNMENT);

        
        int[] rangoDias = this.control.obtenerRangoDiasFechaEstimadaPreparacion(idCliente);
        
        JLabel rangoDiasLabel = new JLabel("Su pedido llegará entre " + rangoDias[0] + "y " + rangoDias[1] + " días.");
        
        rangoDiasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        rangoDiasLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rangoDiasLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        rangoDiasLabel.setForeground(COLOR_TIEMPO_ESTIMADO);
 
        
        JPanel panelAux = new JPanel (new FlowLayout(FlowLayout.CENTER));
        panelAux.setOpaque(false);
        
        tiempoEstimadoPreparacion.add(panelAux);
        panelAux.add(tiempoEstimadoPreparacionLabel1);
        panelAux.add(tiempoEstimadoPreparacionLabel2);
        panelAux.add(rangoDiasLabel);
        panelAux.add(rangoDiasLabel);

        panelLateral.add(panelBannerPrecio);
        panelLateral.add(panelEnvio);
        panelLateral.add(new JSeparator());
        panelLateral.add(panelSubtotal);
        panelLateral.add(new JSeparator());
        panelLateral.add(tiempoEstimadoPreparacion);

        panelContenedorCarrito.add(panelCarrito, BorderLayout.CENTER);
        panelContenedorCarrito.add(panelLateral, BorderLayout.EAST);

        panelPrincipal.add(panelContenedorCarrito, BorderLayout.CENTER);
    }

    
    @Override
    public void hacerVisible(boolean visible) {
        setVisible(visible);
    }
    
    @Override
    public void cerrar(){
        dispose();
    }

    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarDireccionCliente();
        encabezado.mostrarNombreApellidoCliente();
        encabezado.mostrarBtnNumeroCarritoCompras(); 
        encabezado.ocultarBarraBusqueda();
    }
    

}
