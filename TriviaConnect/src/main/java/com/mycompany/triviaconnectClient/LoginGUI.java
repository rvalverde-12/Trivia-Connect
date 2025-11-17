/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectClient;


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Lipsky
 */
public class LoginGUI extends JFrame {
 
    private ClientConnection connection = new ClientConnection();
    private JTextField txtNombre;
    private JRadioButton rbInvitado;
    private JRadioButton rbCuenta;
    private JButton btnCrearSala;
    private JButton btnUnirseSala;
    private JButton btnRegistrarCuenta;

    public LoginGUI(ClientConnection connection) {
        this.connection = connection;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Juego Multijugador de Trivia");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        //Boton Registrar cuenta
        btnRegistrarCuenta = new JButton("Registrar cuenta");
        btnRegistrarCuenta.setBounds(200, 225, 150, 25);
        add(btnRegistrarCuenta);

        JPanel header = new JPanel();
        header.setBackground(new Color(51, 153, 255));
        header.setBounds(0, 0, 600, 80);
        header.setLayout(null);
        add(header);

        JLabel lblTitulo = new JLabel("JUEGO MULTIJUGADOR DE TRIVIA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(150, 20, 300, 40);
        header.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre de jugador:");
        lblNombre.setBounds(100, 120, 150, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(250, 120, 200, 25);
        add(txtNombre);

        rbInvitado = new JRadioButton("Ingresar como invitado");
        rbInvitado.setBounds(200, 170, 200, 25);
        add(rbInvitado);

        rbCuenta = new JRadioButton("Iniciar sesión con cuenta");
        rbCuenta.setBounds(200, 200, 220, 25);
        add(rbCuenta);

        ButtonGroup group = new ButtonGroup();
        group.add(rbInvitado);
        group.add(rbCuenta);

        btnCrearSala = new JButton("Crear sala");
        btnCrearSala.setBounds(120, 280, 130, 25);
        add(btnCrearSala);

        btnUnirseSala = new JButton("Unirse a sala");
        btnUnirseSala.setBounds(330, 280, 130, 25);
        add(btnUnirseSala);

        // Eventos
        btnUnirseSala.addActionListener(e -> enviarNombre());
        btnCrearSala.addActionListener(e -> enviarNombre());

        // Abrir pantalla de registro
        btnRegistrarCuenta.addActionListener(e -> new PantallaRegistro());
    }


    // Getters
    public String getNombre() {
        return txtNombre.getText();
    }

    public JButton getBtnRegistrarCuenta() {
        return btnRegistrarCuenta;
    }
    
    private void enviarNombre() {
    String nombre = getNombre();
    connection.sendMessage("NOMBRE:" + nombre);
}
}