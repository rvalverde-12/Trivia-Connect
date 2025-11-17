/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.triviaconnectserver;

import java.io.*;
import java.net.*;
/**
 *
 * @author Lipsky
 */
public class TriviaConnectServer {

    
    private static final int puerto = 6000;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en puerto " + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                ClientHandler handler = new ClientHandler(socket);
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
   