/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectserver;

import java.sql.*;

/**
 *
 * @author Lipsky
 */
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/trivia_game";
    private static final String USER = "root";
    private static final String PASS = "admin";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }
}
