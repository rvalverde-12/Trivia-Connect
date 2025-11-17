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
public class JugadorDAO {
    
    public boolean login(String nombre, String contrasena) {
        String sql = "SELECT * FROM Jugador WHERE correo = ? AND contrasena = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrar(String nombre, String contrasena) {
        String sql = "INSERT INTO Jugador(nombre_usuario, contrasena) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            //correo duplicado
            return false;
        }
    }
}


