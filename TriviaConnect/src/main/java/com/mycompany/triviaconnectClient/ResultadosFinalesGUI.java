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
public class ResultadosFinalesGUI extends JFrame {

    private ClientConnection connection;

    private JLabel lblGanador;
    private JLabel lblNombre1, lblPuntaje1;
    private JLabel lblNombre2, lblPuntaje2;
    private JLabel lblNombre3, lblPuntaje3;
    private JLabel lblEstadisticaAciertos;
    private JLabel lblEstadisticaTiempo;
    
    private JButton btnNuevaSala;
    private JButton btnVolverLobby;

    public ResultadosFinalesGUI(ClientConnection connection) {
        this.connection = connection;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Constructor para pruebas
    public ResultadosFinalesGUI() {
        this(null);
    }

    private void initComponents() {
        setTitle("Fin del Juego - Resultados");
        setSize(600, 450);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 600, 82);
        header.setBackground(Color.decode("#3399FF"));

        JLabel lblTitulo = new JLabel("FIN DEL JUEGO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(161, 12, 120, 66);
        header.add(lblTitulo);

        lblGanador = new JLabel("GANADOR: PAULINA", SwingConstants.CENTER);
        lblGanador.setFont(new Font("Arial", Font.BOLD, 16));
        lblGanador.setForeground(Color.WHITE);
        lblGanador.setBounds(300, 21, 200, 49);
        header.add(lblGanador);

        // Trofeos
        JLabel trofeo1 = new JLabel(); cargarIcono(trofeo1, "/puntuacion-mas-alta.png", "🏆", 48, 49);
        trofeo1.setBounds(14, 21, 49, 48); header.add(trofeo1);
        
        JLabel trofeo2 = new JLabel(); cargarIcono(trofeo2, "/puntuacion-mas-alta.png", "🏆", 48, 49);
        trofeo2.setBounds(72, 21, 49, 48); header.add(trofeo2);

  
        JLabel trofeo3 = new JLabel(); cargarIcono(trofeo3, "/puntuacion-mas-alta.png", "🏆", 48, 49);
        trofeo3.setBounds(478, 21, 49, 48); header.add(trofeo3);

        JLabel trofeo4 = new JLabel(); cargarIcono(trofeo4, "/puntuacion-mas-alta.png", "🏆", 48, 49);
        trofeo4.setBounds(538, 21, 49, 48); header.add(trofeo4);

        add(header);

        //RANKING FINAL
        JLabel lblRankingTitulo = new JLabel("Ranking final:");
        lblRankingTitulo.setFont(new Font("Arial", Font.BOLD, 28)); // Tamaño 31 en FX ~ 28 en Swing
        lblRankingTitulo.setBounds(208, 94, 250, 40);
        add(lblRankingTitulo);

        //1er 
        JLabel iconOro = new JLabel(); cargarIcono(iconOro, "/medalla-de-oro.png", "🥇", 32, 25);
        iconOro.setBounds(215, 142, 32, 25); add(iconOro);

        lblNombre1 = new JLabel("Paulina");
        lblNombre1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre1.setBounds(247, 140, 100, 25); add(lblNombre1);

        lblPuntaje1 = new JLabel("950 pts");
        lblPuntaje1.setFont(new Font("Arial", Font.BOLD, 20));
        lblPuntaje1.setBounds(321, 140, 100, 25); add(lblPuntaje1);

        //2do 
        JLabel iconPlata = new JLabel(); cargarIcono(iconPlata, "/medalla-de-plata.png", "🥈", 32, 25);
        iconPlata.setBounds(215, 178, 32, 25); add(iconPlata);

        lblNombre2 = new JLabel("Randall");
        lblNombre2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNombre2.setBounds(253, 178, 100, 25); add(lblNombre2);

        lblPuntaje2 = new JLabel("870 pts");
        lblPuntaje2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPuntaje2.setBounds(321, 178, 100, 25); add(lblPuntaje2);

        //3er 
        JLabel iconBronce = new JLabel(); cargarIcono(iconBronce, "/medalla-de-bronce.png", "🥉", 32, 25);
        iconBronce.setBounds(215, 215, 32, 25); add(iconBronce);

        lblNombre3 = new JLabel("Snaider");
        lblNombre3.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNombre3.setBounds(256, 218, 100, 25); add(lblNombre3);

        lblPuntaje3 = new JLabel("700 pts");
        lblPuntaje3.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPuntaje3.setBounds(321, 218, 100, 25); add(lblPuntaje3);

        JPanel panelStats = new JPanel();
        panelStats.setBackground(Color.decode("#3399FF"));
        panelStats.setBounds(0, 247, 600, 35);
        panelStats.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel iconStats = new JLabel(); cargarIcono(iconStats, "/medida.png", "📊", 36, 32);
        JLabel lblStatsTitulo = new JLabel(" Estadisticas");
        lblStatsTitulo.setForeground(Color.WHITE);
        lblStatsTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        panelStats.add(iconStats);
        panelStats.add(lblStatsTitulo);
        add(panelStats);

        lblEstadisticaAciertos = new JLabel("1. Preguntas acertadas : 8 / 10");
        lblEstadisticaAciertos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEstadisticaAciertos.setBounds(49, 300, 300, 20);
        add(lblEstadisticaAciertos);

        lblEstadisticaTiempo = new JLabel("2. Tiempo promedio de respuestas: 3.0s");
        lblEstadisticaTiempo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEstadisticaTiempo.setBounds(49, 339, 350, 20);
        add(lblEstadisticaTiempo);

        btnNuevaSala = new JButton("INICIAR UNA NUEVA SALA");
        btnNuevaSala.setBounds(392, 300, 180, 25);
        add(btnNuevaSala);

        btnVolverLobby = new JButton("VOLVER AL LOBBY");
        btnVolverLobby.setBounds(392, 339, 180, 25);
        add(btnVolverLobby);

        //EVENTOS 
        btnVolverLobby.addActionListener(e -> {
            dispose(); 
            if (connection != null) {
            }
        });
        
        btnNuevaSala.addActionListener(e -> {
            dispose();
        });
    }


    private void cargarIcono(JLabel label, String ruta, String fallbackEmoji, int w, int h) {
        URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            label.setIcon(icon);
        } else {
            label.setText(fallbackEmoji);
            label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }


    public void mostrarResultados(String ganador, String[] top3Nombres, int[] top3Puntajes, int aciertos, double tiempoPromedio) {
        lblGanador.setText("GANADOR: " + ganador.toUpperCase());
        
        lblNombre1.setText(top3Nombres[0]); lblPuntaje1.setText(top3Puntajes[0] + " pts");
        lblNombre2.setText(top3Nombres[1]); lblPuntaje2.setText(top3Puntajes[1] + " pts");
        lblNombre3.setText(top3Nombres[2]); lblPuntaje3.setText(top3Puntajes[2] + " pts");

        lblEstadisticaAciertos.setText("1. Preguntas acertadas : " + aciertos + " / 10");
        lblEstadisticaTiempo.setText("2. Tiempo promedio de respuestas: " + tiempoPromedio + "s");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResultadosFinalesGUI());
    }
}