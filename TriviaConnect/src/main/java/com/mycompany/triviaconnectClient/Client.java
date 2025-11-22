/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectClient;


import javax.swing.SwingUtilities;

/**
 *
 * @author Lipsky
 */
public class Client {
    
public static void main(String[] args) {
     
  ClientConnection connection = new ClientConnection();

        boolean ok = connection.connect("localhost", 6000);

        if (!ok) {
            System.out.println("No se pudo conectar al servidor");
        }

        SwingUtilities.invokeLater(() -> new LoginGUI(connection));
    }
}


