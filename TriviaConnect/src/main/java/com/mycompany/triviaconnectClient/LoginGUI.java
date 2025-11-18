/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectClient;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 *
 * @author Lipsky
 */
public class LoginGUI extends JFrame {
    
    private ClientConnection connection;
    private JTextField txtNombre;
    private JPasswordField txtContrasena;
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
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel header = new JPanel();
        header.setBackground(new Color(51, 153, 255));
        header.setBounds(0, 0, 600, 80);
        header.setLayout(null);
        add(header);

        JLabel lblTitulo = new JLabel("JUEGO MULTIJUGADOR DE TRIVIA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(130, 20, 350, 40);
        header.add(lblTitulo);

        JLabel lblNombre = new JLabel("Usuario:");
        lblNombre.setBounds(100, 120, 150, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(250, 120, 200, 25);
        add(txtNombre);

        JLabel lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setBounds(100, 160, 150, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(250, 160, 200, 25);
        add(txtContrasena);

        rbInvitado = new JRadioButton("Ingresar como invitado");
        rbInvitado.setBounds(200, 200, 200, 25);
        add(rbInvitado);

        rbCuenta = new JRadioButton("Iniciar sesion con cuenta");
        rbCuenta.setBounds(200, 230, 220, 25);
        rbCuenta.setSelected(true);
        add(rbCuenta);

        ButtonGroup group = new ButtonGroup();
        group.add(rbInvitado);
        group.add(rbCuenta);

        btnRegistrarCuenta = new JButton("Registrar cuenta");
        btnRegistrarCuenta.setBounds(200, 270, 150, 25);
        add(btnRegistrarCuenta);

        btnCrearSala = new JButton("Crear sala");
        btnCrearSala.setBounds(120, 330, 130, 30);
        add(btnCrearSala);

        btnUnirseSala = new JButton("Unirse a sala");
        btnUnirseSala.setBounds(330, 330, 130, 30);
        add(btnUnirseSala);

        // Eventos
        rbInvitado.addActionListener(e -> {
            txtContrasena.setEnabled(false);
            lblContrasena.setEnabled(false);
        });

        rbCuenta.addActionListener(e -> {
            txtContrasena.setEnabled(true);
            lblContrasena.setEnabled(true);
        });

        btnUnirseSala.addActionListener(e -> manejarLogin());
        btnCrearSala.addActionListener(e -> manejarLogin());
        btnRegistrarCuenta.addActionListener(e -> {
            try {
                System.out.println("Boton Registrar presionado");
                System.out.println("Connection es null? " + (connection == null));
                PantallaRegistro registro = new PantallaRegistro(connection);
                System.out.println("PantallaRegistro creada exitosamente");
            } catch (Exception ex) {
                System.err.println("Error al crear PantallaRegistro:");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Error al abrir ventana de registro: " + ex.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void manejarLogin() {
        String nombre = txtNombre.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre de usuario");
            return;
        }

        if (rbInvitado.isSelected()) {
            connection.sendMessage("NOMBRE:" + nombre);
            abrirSalaEspera(nombre);
        } else {
            String contrasena = new String(txtContrasena.getPassword());
            if (contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una contrasena");
                return;
            }
            
            connection.sendMessage("LOGIN;" + nombre + ";" + contrasena);
            
            // Leer respuesta del servidor en un hilo separado
            new Thread(() -> {
                try {
                    String respuesta = connection.readMessage();
                    SwingUtilities.invokeLater(() -> procesarRespuestaLogin(respuesta, nombre));
                } catch (IOException ex) {
                    SwingUtilities.invokeLater(() -> 
                        JOptionPane.showMessageDialog(this, "Error de conexion: " + ex.getMessage())
                    );
                }
            }).start();
        }
    }

    private void procesarRespuestaLogin(String respuesta, String nombre) {
        if (respuesta == null) {
            JOptionPane.showMessageDialog(this, "El servidor no respondio");
            return;
        }

        String[] partes = respuesta.split(";");
        if (partes[0].equals("OK")) {
            JOptionPane.showMessageDialog(this, "Login exitoso!");
            abrirSalaEspera(nombre);
        } else {
            String mensaje = partes.length > 2 ? partes[2] : "Error desconocido";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirSalaEspera(String nombre) {
       
        JOptionPane.showMessageDialog(this, "Bienvenido, " + nombre + "!\n(Implementar ventana de sala)");
        // dispose(); // Cerrar ventana de login
    }
}