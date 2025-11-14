/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnect;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 *
 * @author Lipsky
 */
public class InicioSesion {

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnIniciar;

    @FXML
    private void onIniciarClick() {
        System.out.println("Usuario: " + txtUsuario.getText());
        System.out.println("Password: " + txtPassword.getText());
    }

}  

