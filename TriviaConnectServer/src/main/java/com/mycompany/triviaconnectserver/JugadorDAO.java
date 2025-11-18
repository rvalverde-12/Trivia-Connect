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
        // CORREGIDO: Cambiar 'correo' por 'nombre_usuario'
        String sql = "SELECT * FROM Jugador WHERE nombre_usuario = ? AND contrasena = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrar(String nombre, String contrasena) {
        // CORREGIDO: Solo 2 placeholders para 2 columnas
        String sql = "INSERT INTO Jugador(nombre_usuario, contrasena) VALUES (?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            // Usuario duplicado u otro error
            System.err.println("Error en registro: " + e.getMessage());
            return false;
        }
    }
    
    // Método adicional para verificar si un usuario existe
    public boolean existeUsuario(String nombre) {
        String sql = "SELECT COUNT(*) FROM Jugador WHERE nombre_usuario = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error verificando usuario: " + e.getMessage());
            return false;
        }
    }
}

