package com.timerapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerWindow {
    private final TimerLogic timerLogic = new TimerLogic();
    private JLabel timerLabel;
    private Timer swingTimer;

    private int defaultFontSize = 60;
    private Dimension defaultWindowSize = new Dimension(500, 400);

    public void show() {
        // Crée une nouvelle fenêtre principale
        JFrame frame = new JFrame("Modern Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(defaultWindowSize);
        frame.setResizable(false);

        // Définir une icône/logo pour l'application
        try {
            frame.setIconImage(new ImageIcon("src/resources/logo.png").getImage());
        } catch (Exception ex) {
            System.out.println("Logo introuvable : " + ex.getMessage());
        }

        // Panel Principal avec une image réelle en arrière-plan
        JPanel mainPanel = new ImagePanel("src/resources/OIP.jpeg"); // Panneau personnalisé
        mainPanel.setLayout(new BorderLayout());
        frame.setContentPane(mainPanel);

        // Label pour afficher le temps dans une grande police
        timerLabel = new JLabel(timerLogic.getTime(), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, defaultFontSize));
        timerLabel.setForeground(Color.BLACK); // Texte en noir
        mainPanel.add(timerLabel, BorderLayout.CENTER);

        // Boutons modernisés (avec couleurs et bordures arrondies)
        JButton startPauseButton = createModernButton("Start");
        JButton resetButton = createModernButton("Reset");
        JButton resizeUpButton = createModernButton("Agrandir");
        JButton resizeDownButton = createModernButton("Rétrécir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Fond transparent pour les boutons
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startPauseButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(resizeUpButton);
        buttonPanel.add(resizeDownButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Écouteur pour Start/Pause
        startPauseButton.addActionListener(new ActionListener() {
            private boolean running = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    timerLogic.pause();
                    startPauseButton.setText("Start");
                } else {
                    timerLogic.start();
                    startPauseButton.setText("Pause");
                }
                running = !running;
            }
        });

        // Écouteur pour Reset
        resetButton.addActionListener(e -> {
            timerLogic.reset();
            startPauseButton.setText("Start");
        });

        // Écouteur pour Agrandir
        resizeUpButton.addActionListener(e -> {
            frame.setSize(700, 500);
            timerLabel.setFont(new Font("Arial", Font.BOLD, defaultFontSize + 20));
        });

        // Écouteur pour Rétrécir
        resizeDownButton.addActionListener(e -> {
            frame.setSize(defaultWindowSize);
            timerLabel.setFont(new Font("Arial", Font.BOLD, defaultFontSize));
        });

        // Swing Timer pour rafraîchir l'affichage
        swingTimer = new Timer(500, e -> timerLabel.setText(timerLogic.getTime()));
        swingTimer.start();

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setBackground(new Color(70, 130, 180)); // Couleur bleue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    public void stopSwingTimer() {
        if (swingTimer != null) {
            swingTimer.stop();
        }
    }
}

/**
 * Classe de panneau personnalisé qui affiche une image en arrière-plan
 */
class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        try {
            // Charger l'image depuis le chemin spécifié
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner l'image comme arrière-plan (redimensionnée à la taille du panneau)
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}