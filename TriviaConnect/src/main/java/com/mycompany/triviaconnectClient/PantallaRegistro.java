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

public class PantallaRegistro extends JFrame {
    private ClientConnection connection;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JPasswordField campoConfirmar;
    private JButton botonCrearCuenta;
    private JButton botonVolver;

    public PantallaRegistro(ClientConnection connection) {
        this.connection = connection;
        iniciarComponentes();
        setTitle("Registro de Usuario");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void iniciarComponentes() {
        setLayout(null);

        JLabel etiquetaTitulo = new JLabel("Crear nueva cuenta");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaTitulo.setBounds(130, 20, 250, 30);
        add(etiquetaTitulo);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(60, 80, 100, 25);
        add(etiquetaUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(160, 80, 200, 25);
        add(campoUsuario);

        JLabel etiquetaContrasena = new JLabel("Contrasena:");
        etiquetaContrasena.setBounds(60, 120, 100, 25);
        add(etiquetaContrasena);

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(160, 120, 200, 25);
        add(campoContrasena);

        JLabel etiquetaConfirmar = new JLabel("Confirmar:");
        etiquetaConfirmar.setBounds(60, 160, 100, 25);
        add(etiquetaConfirmar);

        campoConfirmar = new JPasswordField();
        campoConfirmar.setBounds(160, 160, 200, 25);
        add(campoConfirmar);

        botonCrearCuenta = new JButton("Crear Cuenta");
        botonCrearCuenta.setBounds(70, 220, 130, 40);
        add(botonCrearCuenta);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(230, 220, 130, 40);
        add(botonVolver);

        // Eventos
        botonCrearCuenta.addActionListener(e -> manejarRegistro());
        botonVolver.addActionListener(e -> dispose());
    }

    private void manejarRegistro() {
        String usuario = campoUsuario.getText().trim();
        String contrasena = new String(campoContrasena.getPassword());
        String confirmar = new String(campoConfirmar.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contrasenas no coinciden");
            return;
        }

        if (contrasena.length() < 4) {
            JOptionPane.showMessageDialog(this, "La contrasena debe tener al menos 4 caracteres");
            return;
        }

//Enviar al servidor
        connection.sendMessage("REGISTER;" + usuario + ";" + contrasena);

 //Leer respuesta en hilo separado
        new Thread(() -> {
            try {
                String respuesta = connection.readMessage();
                SwingUtilities.invokeLater(() -> procesarRespuesta(respuesta));
            } catch (IOException ex) {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(this, "Error de conexion: " + ex.getMessage())
                );
            }
        }).start();
    }

    private void procesarRespuesta(String respuesta) {
        if (respuesta == null) {
            JOptionPane.showMessageDialog(this, "El servidor no respondio");
            return;
        }

        String[] partes = respuesta.split(";");
        if (partes[0].equals("OK")) {
            JOptionPane.showMessageDialog(this, "Cuenta creada exitosamente!");
            dispose();
        } else {
            String mensaje = partes.length > 2 ? partes[2] : "Error al crear cuenta";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}