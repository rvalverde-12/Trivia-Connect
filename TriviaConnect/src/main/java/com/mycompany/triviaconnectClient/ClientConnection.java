/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectClient;

import java.io.*;
import java.net.*;
/**
 *
 * @author Lipsky
 */
public class ClientConnection {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public boolean connect(String host, int port) {
        try {
            socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connected to server!");
            return true;

        } catch (IOException e) {
            System.out.println("Could not connect to server: " + e.getMessage());
            return false;
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public String readMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
}