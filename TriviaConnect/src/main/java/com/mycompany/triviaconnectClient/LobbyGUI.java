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
public class LobbyGUI extends JFrame {

    private ClientConnection connection;
    private String nombreSala;

    // Componentes dinámicos
    private DefaultListModel<String> modeloJugadores;
    private JList<String> listaJugadores;
    private JTextArea txtChat;
    private JLabel lblContadorJugadores;

    // Constructor para pruebas
    public LobbyGUI() {
        this(null, "Sala: 7K2FQ", "Admin");
    }

    public LobbyGUI(ClientConnection connection, String nombreSala, String usuario) {
        this.connection = connection;
        this.nombreSala = nombreSala;
        initComponents(usuario);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(String usuarioLocal) {
        setTitle("Lobby - " + nombreSala);
        setSize(600, 450);
        setLayout(null); // Diseño absoluto para replicar el FXML
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- HEADER AZUL ---
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 600, 82);
        header.setBackground(Color.decode("#3399FF"));

        // Icono Jugadores
        JLabel lblIcono = new JLabel();
        URL iconURL = getClass().getResource("/jugador-contra-jugador.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(57, 66, Image.SCALE_SMOOTH));
            lblIcono.setIcon(icon);
        } else {
            lblIcono.setText("👥"); // Emoji fallback
            lblIcono.setForeground(Color.WHITE);
            lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        }
        lblIcono.setBounds(171, 13, 60, 66);
        header.add(lblIcono);

        // Título de la Sala
        JLabel lblTitulo = new JLabel(nombreSala, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(240, 8, 233, 66);
        header.add(lblTitulo);

        lblContadorJugadores = new JLabel("Jugadores: 3 / 6", SwingConstants.CENTER);
        lblContadorJugadores.setFont(new Font("Arial", Font.BOLD, 15));
        lblContadorJugadores.setForeground(Color.WHITE);
        lblContadorJugadores.setBounds(460, 17, 120, 49);
        header.add(lblContadorJugadores);

        add(header);

        JLabel lblLabelJugadores = new JLabel("Jugadores conectados:");
        lblLabelJugadores.setBounds(159, 100, 150, 20);
        add(lblLabelJugadores);

        JLabel lblLabelEstado = new JLabel("Estado");
        lblLabelEstado.setBounds(353, 100, 100, 20);
        add(lblLabelEstado);

        modeloJugadores.addElement("1. Snaider   -   Listo ✅");
        modeloJugadores.addElement("2. Randall   -   Esperando... ⏳");
        modeloJugadores.addElement("3. Paulina   -   Listo ✅");
        
        listaJugadores = new JList<>(modeloJugadores);
        listaJugadores.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JScrollPane scrollJugadores = new JScrollPane(listaJugadores);
        scrollJugadores.setBounds(159, 123, 300, 130); 
        add(scrollJugadores);

        JButton btnListo = new JButton("Marcar como listo");
        btnListo.setBounds(177, 274, 150, 30);
        btnListo.setBackground(Color.decode("#F3F311")); 
        btnListo.setForeground(Color.BLACK); 
        btnListo.setFocusPainted(false);
        add(btnListo);

        JButton btnIniciar = new JButton("Iniciar partida");
        btnIniciar.setBounds(334, 274, 150, 30);
        btnIniciar.setBackground(Color.decode("#33CC33")); 
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 12));
        btnIniciar.setFocusPainted(false);
        add(btnIniciar);

        JLabel lblChat = new JLabel("Mensajes en el chat:");
        lblChat.setBounds(38, 310, 150, 20);
        add(lblChat);

        txtChat = new JTextArea();
        txtChat.setEditable(false);
        txtChat.setFont(new Font("Arial", Font.PLAIN, 12));
        txtChat.setBackground(new Color(240, 240, 240));
        txtChat.append("-> Randall: Listo cuando quieran!\n");
        txtChat.append("-> Paulina: Cambiemos la categoría a Ciencia\n");

        JScrollPane scrollChat = new JScrollPane(txtChat);
        scrollChat.setBounds(38, 330, 500, 70);
        scrollChat.setBorder(null); // Para que parezca labels transparentes como en FXML
        add(scrollChat);


        //Eventos
        btnListo.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Has marcado que estás listo.");
            modeloJugadores.addElement("4. " + usuarioLocal + " - Listo ✅");
        });

        btnIniciar.addActionListener(e -> {
            if (connection != null) {
                connection.sendMessage("INICIAR_PARTIDA");
            } else {
                JOptionPane.showMessageDialog(this, "Iniciando partida (Demo)...");
            }
        });
    }

    public void actualizarListaJugadores(String[] jugadores) {
        modeloJugadores.clear();
        for (String j : jugadores) {
            modeloJugadores.addElement(j);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LobbyGUI());
    }
}