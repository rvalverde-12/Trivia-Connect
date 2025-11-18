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
    private JugadorDAO jugadorDAO;
    private String nombreJugador;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.jugadorDAO = new JugadorDAO();
        
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("Cliente conectado desde: " + socket.getInetAddress());

            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                procesarMensaje(mensaje);
            }
            
        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + nombreJugador);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void procesarMensaje(String msg) {
        String[] partes = msg.split(";");
        
        if (partes.length == 0) {
            output.println("ERROR;MENSAJE_VACIO");
            return;
        }
        
        String comando = partes[0];

        switch (comando) {
            case "LOGIN":
                if (partes.length >= 3) {
                    manejarLogin(partes[1], partes[2]);
                } else {
                    output.println("ERROR;LOGIN;Formato incorrecto");
                }
                break;
                
            case "REGISTER":
                if (partes.length >= 3) {
                    manejarRegister(partes[1], partes[2]);
                } else {
                    output.println("ERROR;REGISTER;Formato incorrecto");
                }
                break;
                
            case "NOMBRE":
// Para invitados
                if (msg.contains(":")) {
                    String nombre = msg.split(":")[1];
                    nombreJugador = nombre;
                    output.println("OK;NOMBRE_ACEPTADO");
                    System.out.println("Jugador invitado: " + nombre);
                } else {
                    output.println("ERROR;NOMBRE;Formato incorrecto");
                }
                break;
                
            default:
                output.println("ERROR;COMANDO_NO_RECONOCIDO");
                System.out.println("Comando desconocido: " + comando);
        }
    }

    private void manejarLogin(String nombre, String contrasena) {
        System.out.println("Intentando login para: " + nombre);
        
        boolean ok = jugadorDAO.login(nombre, contrasena);
        
        if (ok) {
            nombreJugador = nombre;
            output.println("OK;LOGIN");
            System.out.println("Login exitoso para: " + nombre);
        } else {
            output.println("ERROR;LOGIN;Credenciales incorrectas");
            System.out.println("Login fallido para: " + nombre);
        }
    }

    private void manejarRegister(String nombre, String contrasena) {
        System.out.println("Intentando registrar: " + nombre);
        
 //Validaciones
        if (nombre.length() < 3) {
            output.println("ERROR;REGISTER;El usuario debe tener al menos 3 caracteres");
            return;
        }
        
        if (contrasena.length() < 4) {
            output.println("ERROR;REGISTER;La contraseña debe tener al menos 4 caracteres");
            return;
        }
        
        boolean ok = jugadorDAO.registrar(nombre, contrasena);
        
        if (ok) {
            output.println("OK;REGISTER");
            System.out.println("Usuario registrado: " + nombre);
        } else {
            output.println("ERROR;REGISTER;El usuario ya existe");
            System.out.println("Registro fallido para: " + nombre);
        }
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }
}