/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triviaconnectClient;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 *
 * @author Lipsky
 */
public class JuegoGUI extends JFrame {

    private ClientConnection connection;
    
    private JLabel lblNumeroPregunta;
    private JLabel lblTemporizador;
    
    private JLabel lblEnunciado;
    private JRadioButton rbOpcionA;
    private JRadioButton rbOpcionB;
    private JRadioButton rbOpcionC;
    private JRadioButton rbOpcionD;
    private ButtonGroup grupoOpciones;
    private JButton btnAccion; 

    private JLabel lblNombre1, lblPuntaje1, lblIcono1;
    private JLabel lblNombre2, lblPuntaje2, lblIcono2;
    private JLabel lblNombre3, lblPuntaje3, lblIcono3;

    public JuegoGUI(ClientConnection connection) {
        this.connection = connection;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JuegoGUI() {
        this(null);
    }

    private void initComponents() {
        setTitle("Trivia Connect - Partida en Curso");
        setSize(600, 450);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 600, 82);
        header.setBackground(Color.decode("#3399FF"));

        
        lblNumeroPregunta = new JLabel("Pregunta 3 / 10", SwingConstants.CENTER);
        lblNumeroPregunta.setFont(new Font("Arial", Font.BOLD, 15));
        lblNumeroPregunta.setForeground(Color.WHITE);
        lblNumeroPregunta.setBounds(54, 9, 121, 66);
        header.add(lblNumeroPregunta);

       
        JLabel lblIconoReloj = new JLabel();
        cargarIcono(lblIconoReloj, "/cronometro.png", "⏱", 32, 36);
        lblIconoReloj.setBounds(359, 27, 36, 32);
        header.add(lblIconoReloj);

       
        lblTemporizador = new JLabel("10 segundos...", SwingConstants.CENTER);
        lblTemporizador.setFont(new Font("Arial", Font.BOLD, 15));
        lblTemporizador.setForeground(Color.WHITE);
        lblTemporizador.setBounds(404, 18, 187, 49);
        header.add(lblTemporizador);

        add(header);

        lblEnunciado = new JLabel("¿Cual es el sistema mas grande del sistema solar?");
        lblEnunciado.setFont(new Font("Arial", Font.BOLD, 14));
        lblEnunciado.setBounds(38, 106, 500, 20);
        add(lblEnunciado);

        rbOpcionA = new JRadioButton("A. Jupiter");
        rbOpcionB = new JRadioButton("B. Marte");
        rbOpcionC = new JRadioButton("C. Saturno");
        rbOpcionD = new JRadioButton("D. Tierra"); 


        rbOpcionA.setBounds(65, 139, 120, 20);
        rbOpcionB.setBounds(195, 139, 120, 20);
        rbOpcionC.setBounds(66, 174, 120, 20);
        rbOpcionD.setBounds(195, 174, 120, 20);

        rbOpcionA.setSelected(true);

        grupoOpciones = new ButtonGroup();
        grupoOpciones.add(rbOpcionA);
        grupoOpciones.add(rbOpcionB);
        grupoOpciones.add(rbOpcionC);
        grupoOpciones.add(rbOpcionD);

        add(rbOpcionA); add(rbOpcionB); add(rbOpcionC); add(rbOpcionD);
        JPanel panelSeparador = new JPanel();
        panelSeparador.setBackground(Color.decode("#3399FF"));
        panelSeparador.setBounds(0, 238, 600, 35);
        panelSeparador.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrar contenido

        JLabel lblIconoTabla = new JLabel();
        cargarIcono(lblIconoTabla, "/puntuacion-mas-alta.png", "🏆", 24, 24);
        
        JLabel lblTituloTabla = new JLabel(" Tabla de puntuaciones");
        lblTituloTabla.setForeground(Color.WHITE);
        lblTituloTabla.setFont(new Font("Arial", Font.BOLD, 14));

        panelSeparador.add(lblIconoTabla);
        panelSeparador.add(lblTituloTabla);
        add(panelSeparador);

        lblIcono1 = new JLabel();
        cargarIcono(lblIcono1, "/medalla-de-oro.png", "🥇", 32, 25);
        lblIcono1.setBounds(228, 282, 32, 25);
        add(lblIcono1);

        lblNombre1 = new JLabel("Paulina");
        lblNombre1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre1.setBounds(260, 280, 100, 30);
        add(lblNombre1);

        lblPuntaje1 = new JLabel("450 pts");
        lblPuntaje1.setFont(new Font("Arial", Font.BOLD, 20));
        lblPuntaje1.setBounds(360, 280, 100, 30); 
        add(lblPuntaje1);

        lblIcono2 = new JLabel();
        cargarIcono(lblIcono2, "/medalla-de-plata.png", "🥈", 32, 25);
        lblIcono2.setBounds(228, 318, 32, 25);
        add(lblIcono2);

        lblNombre2 = new JLabel("Randall");
        lblNombre2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNombre2.setBounds(266, 318, 100, 25);
        add(lblNombre2);

        lblPuntaje2 = new JLabel("400 pts");
        lblPuntaje2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPuntaje2.setBounds(360, 318, 100, 25);
        add(lblPuntaje2);


        lblIcono3 = new JLabel();
        cargarIcono(lblIcono3, "/medalla-de-bronce.png", "🥉", 32, 25);
        lblIcono3.setBounds(228, 355, 32, 25);
        add(lblIcono3);

        lblNombre3 = new JLabel("Snaider");
        lblNombre3.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNombre3.setBounds(269, 358, 100, 20);
        add(lblNombre3);

        lblPuntaje3 = new JLabel("350 pts");
        lblPuntaje3.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPuntaje3.setBounds(360, 358, 100, 20);
        add(lblPuntaje3);


        btnAccion = new JButton("Ver respuesta"); 
        btnAccion.setBounds(440, 355, 130, 30);
        btnAccion.setBackground(Color.decode("#33CC33"));
        btnAccion.setForeground(Color.WHITE);
        btnAccion.setFont(new Font("Arial", Font.BOLD, 12));
        btnAccion.setFocusPainted(false);
        
        btnAccion.addActionListener(e -> enviarRespuesta());
        
        add(btnAccion);
    }

    private void cargarIcono(JLabel label, String ruta, String fallbackEmoji, int w, int h) {
        URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            label.setIcon(icon);
        } else {
            label.setText(fallbackEmoji);
            label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        }
    }

    private void enviarRespuesta() {
        String seleccion = "";
        if (rbOpcionA.isSelected()) seleccion = "A";
        else if (rbOpcionB.isSelected()) seleccion = "B";
        else if (rbOpcionC.isSelected()) seleccion = "C";
        else if (rbOpcionD.isSelected()) seleccion = "D";

        if (connection != null) {

            connection.sendMessage("RESPUESTA;" + seleccion);
            JOptionPane.showMessageDialog(this, "Respuesta enviada: " + seleccion);
            btnAccion.setEnabled(false); 
        } else {
            JOptionPane.showMessageDialog(this, "Modo prueba: Seleccionaste " + seleccion);
        }
    }

    public void actualizarPregunta(String texto, String[] opciones, int numPregunta) {
        lblEnunciado.setText(texto);
        rbOpcionA.setText("A. " + opciones[0]);
        rbOpcionB.setText("B. " + opciones[1]);
        rbOpcionC.setText("C. " + opciones[2]);
        rbOpcionD.setText("D. " + opciones[3]);
        lblNumeroPregunta.setText("Pregunta " + numPregunta + " / 10");
        btnAccion.setEnabled(true);
        grupoOpciones.clearSelection();
    }

    public void actualizarTiempo(int segundos) {
        lblTemporizador.setText(segundos + " segundos...");
        if (segundos < 5) lblTemporizador.setForeground(Color.RED);
        else lblTemporizador.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoGUI());
    }
}