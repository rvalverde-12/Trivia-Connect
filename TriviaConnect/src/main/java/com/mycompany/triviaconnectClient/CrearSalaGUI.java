/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectClient;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


/**
 *
 * @author Lipsky
 */
public class CrearSalaGUI extends JFrame {

    private ClientConnection connection;
    private JTextField txtNombreSala;
    private JTextField txtRondas;
    private JTextField txtTiempo;
    private JComboBox<String> cmbTipoTrivia;
    private JRadioButton rbPrivada;
    private JRadioButton rbPublica;

 // Constructor para ver la pantalla cuando no hay conexion
    public CrearSalaGUI() {
        this(null);
    }
    
    public CrearSalaGUI(ClientConnection connection) {
        this.connection = connection;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Configurar Sala");
        setSize(600, 440);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 600, 82);
        header.setBackground(Color.decode("#3399FF")); 


        JLabel lblIcono = new JLabel();
        URL iconURL = getClass().getResource("/perno-de-tuerca.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(57, 66, Image.SCALE_SMOOTH));
            lblIcono.setIcon(icon);
        } else {
            lblIcono.setText("⚙"); 
            lblIcono.setForeground(Color.WHITE);
            lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        }
        lblIcono.setBounds(171, 10, 60, 66); 
        header.add(lblIcono);

        JLabel lblTitulo = new JLabel("Configurar / crear sala", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18)); 
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(240, 8, 250, 66);
        header.add(lblTitulo);

        add(header); 

        crearEtiqueta("Nombre de la sala:", 167, 111);
        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(277, 107, 150, 25);
        add(txtNombreSala);

        crearEtiqueta("Numero de rondas:", 165, 152);
        txtRondas = new JTextField("5"); 
        txtRondas.setBounds(275, 148, 71, 25);
        add(txtRondas);

   
        crearEtiqueta("Tiempo por pregunta:", 149, 192);
        txtTiempo = new JTextField("30"); 
        txtTiempo.setBounds(275, 188, 71, 25);
        add(txtTiempo);


        crearEtiqueta("Tipo de trivia:", 171, 229);
        String[] tipos = {"General", "Ciencia", "Historia", "Deportes"};
        cmbTipoTrivia = new JComboBox<>(tipos);
        cmbTipoTrivia.setEditable(true); 
        cmbTipoTrivia.setBounds(277, 225, 120, 25);
        add(cmbTipoTrivia);

        crearEtiqueta("Visibilidad:", 167, 269);
        
        rbPrivada = new JRadioButton("Privada");
        rbPrivada.setBounds(239, 265, 80, 25);
        
        rbPublica = new JRadioButton("Pública");
        rbPublica.setBounds(320, 265, 80, 25);
        rbPublica.setSelected(true); 

        ButtonGroup grupoVisibilidad = new ButtonGroup();
        grupoVisibilidad.add(rbPrivada);
        grupoVisibilidad.add(rbPublica);

        add(rbPrivada);
        add(rbPublica);

        JButton btnCrear = new JButton("Crear sala");
        btnCrear.setBounds(168, 322, 275, 35);
        btnCrear.setBackground(Color.decode("#33CC33")); 
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrear.setFocusPainted(false); 
        
 
        btnCrear.addActionListener(e -> manejarCreacionSala());
        
        add(btnCrear);
    }

    private void crearEtiqueta(String texto, int x, int y) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setBounds(x, y, 150, 20);
        add(label);
    }

    private void manejarCreacionSala() {
        String nombre = txtNombreSala.getText().trim();
        String rondasStr = txtRondas.getText().trim();
        String tiempoStr = txtTiempo.getText().trim();
        String categoria = (String) cmbTipoTrivia.getSelectedItem();
        boolean esPublica = rbPublica.isSelected();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La sala necesita un nombre.");
            return;
        }
        
        try {
            int rondas = Integer.parseInt(rondasStr);
            int tiempo = Integer.parseInt(tiempoStr);
            
            if (rondas < 1 || tiempo < 5) {
                 JOptionPane.showMessageDialog(this, "Valores numéricos inválidos.");
                 return;
            }

            if (connection != null) {
                String msg = String.format("CREAR_SALA;%s;%d;%d;%s;%b", 
                    nombre, rondas, tiempo, categoria, esPublica);
                
                connection.sendMessage(msg);
                JOptionPane.showMessageDialog(this, "Solicitud enviada al servidor...");
  
                dispose(); 
            } else {
                System.out.println("Modo prueba: " + nombre + " - Rondas: " + rondas);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Rondas y Tiempo deben ser números.");
        }
    }

    public static void main(String[] args) {
  
        SwingUtilities.invokeLater(() -> new CrearSalaGUI());
    }
}
