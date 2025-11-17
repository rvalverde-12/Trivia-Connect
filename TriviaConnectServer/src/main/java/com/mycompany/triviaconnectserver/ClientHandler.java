/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectserver;


import java.io.*;
import java.net.*;
/**
 *
 * @author Lipsky
 */
public class ClientHandler extends Thread {
    
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                procesarMensaje(mensaje);
            }

        } catch (IOException e) {
            System.out.println("Cliente desconectado");
        }
    }

    private void procesarMensaje(String msg) {
        String[] partes = msg.split(";");

        String comando = partes[0];

        switch (comando) {
            case "LOGIN":
                manejarLogin(partes);
                break;

            case "REGISTER":
                manejarRegister(partes);
                break;

            default:
                output.println("ERROR;COMANDO_NO_RECONOCIDO");
        }
    }

    private JugadorDAO jugadorDAO = new JugadorDAO();

    private void manejarLogin(String[] datos) {
        String nombre = datos[1];
        String contrasena = datos[2];

        boolean ok = jugadorDAO.login(nombre, contrasena);

        if (ok) {
            output.println("OK;LOGIN");
        } else {
            output.println("ERROR;LOGIN;Credenciales incorrectas");
        }
    }

    private void manejarRegister(String[] datos) {
        String nombre = datos[1];
        String contrasena = datos[2];

        boolean ok = jugadorDAO.registrar(nombre, contrasena);

        if (ok) {
            output.println("OK;REGISTER");
        } else {
            output.println("ERROR;REGISTER;Correo ya registrado");
        }
    }
}