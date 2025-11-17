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

public class PantallaRegistro extends JFrame {
  
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JPasswordField campoConfirmar;
    private JButton botonCrearCuenta;
    private JButton botonVolver;

    public PantallaRegistro() {
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

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
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
    }

    public String getUsuario() {
        return campoUsuario.getText();
    }

    public String getContrasena() {
        return new String(campoContrasena.getPassword());
    }

    public String getConfirmar() {
        return new String(campoConfirmar.getPassword());
    }

    public JButton getBotonCrearCuenta() {
        return botonCrearCuenta;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }
  
}
