package edu.student.itson.dissof.megazarl.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Direccion extends JFrame{
    
    private ControlCompra control;
    
    private Encabezado encabezado;
    
    private JPanel panelGeneral;
    private JPanel panelFormulario;
    private JButton btnCancelar;
    private JButton btnGuardar;

    public Direccion(ControlCompra control) {
        initComponents();
        this.control = control;
    }

    private void initComponents() {
        
        setTitle("Dirección del usuario");
        setSize(937, 676);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        encabezado = new Encabezado();
        this.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel();
        panelGeneral.setLayout(new BorderLayout());
        panelGeneral.setBackground(new Color(217, 217, 255));
        this.add(panelGeneral);

        JLabel lblUsuario = new JLabel("Nombre Usuario");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblTitulo = new JLabel("Semillas MEGAZARL");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        panelFormulario = new JPanel();
        panelFormulario.setBackground(new Color(226, 234, 206));
        panelFormulario.setLayout(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        String[] labels = {"Código postal:", "Estado:", "Ciudad:", "Colonia:", "Número:", "Calle:"};
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panelFormulario.add(lbl);
            panelFormulario.add(new JTextField());
        }

        panelGeneral.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(217, 217, 255));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(235, 255, 197));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(235, 255, 197));
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);
        panelGeneral.add(panelBotones, BorderLayout.SOUTH);
    }
     
}