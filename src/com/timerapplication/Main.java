package com.timerapplication;

public class Main {
    public static void main(String[] args) {
        // Lancer l'interface utilisateur de la minuterie
        javax.swing.SwingUtilities.invokeLater(() -> new TimerWindow().show());
    }
}