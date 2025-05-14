package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IOrdenCompra;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * OrdenCompra.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
//public class OrdenCompra extends JFrame implements IOrdenCompra, IVista{ //TODO implements IVista
//    
//    private final ControlOrdenCompra control;
//    private Long idGerenteVentas;
//    
//    private EncabezadoOrdenCompra encabezado;
//    
//    private JPanel panelGeneral;
//    
//    public OrdenCompra(ControlOrdenCompra control, Long idGerenteVentas){
//        setTitle("Semillas MEGAZARL - Realizar Orden de Compra");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1200, 800);
//        setLocationRelativeTo(null);
//        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
//        setIconImage(iconoPropio);
//        this.control = control;
//        this.idGerenteVentas = idGerenteVentas;
//        this.initComponents();
//    }
//    
//    private void initComponents() {
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        this.setSize(1200, 800);
//        this.setLocationRelativeTo(null);
//        this.setLayout(new BorderLayout());
//        
//        encabezado = new EncabezadoOrdenCompra(control, idGerenteVentas, this);
//        this.add(encabezado, BorderLayout.NORTH);
//
//        panelGeneral = new JPanel(new BorderLayout());
//        this.add(panelGeneral, BorderLayout.CENTER);
//
//    }
//    
//}
