package org.example.gui;

import org.example.controladorAudio.MP3Reproductor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

public class EntradaJuego extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel textoBienvenida;
    private Boolean continuar;

    public EntradaJuego(JFrame parent) {
        super(parent, "Bienvenida", true);
        continuar = false;
        setContentPane(contentPane);
        setSize(new Dimension(750, 550));
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.setBackground(new Color(66, 73, 73));
        buttonOK.setForeground(Color.WHITE);
        buttonOK.setVisible(false);
        escribirModoTeclado();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setVisible(true);
    }

    public void escribirModoTeclado() {
        String intro = "<br>";
        String linea1 = "Bienvenido al apocalipsis, dispara a los zombies";
        String linea2 =  "(botón de zombie) y compra mejoras para avanzar";
        String linea3 =  "en la aventura";

        Thread h1 = new Thread(() -> {
            MP3Reproductor reproductor = new MP3Reproductor("/audios/tecleo.mp3");
            reproductor.volumeUp();
            reproductor.volumeUp();
            reproductor.volumeUp();
            reproductor.play();
            while(true){
                for(int i = 1; i <= linea1.length(); i++){
                    textoBienvenida.setText("<html>" + linea1.substring(0, i) + "</html>");
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for(int i = 1; i <= linea2.length(); i++){
                    textoBienvenida.setText("<html>" + linea1 + intro + linea2.substring(0, i) + "</html>");
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for(int i = 1; i <= linea3.length(); i++){
                    textoBienvenida.setText("<html>" + linea1 + intro + linea2 + intro + linea3.substring(0, i) + "</html>");
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                buttonOK.setVisible(true);
                reproductor.stop();
                break;
            }
        });
        h1.start();
    }
    private void onOK() {
        continuar = true;
        dispose();
    }


    private void onCancel() {
        dispose();
    }

    public Boolean getContinuar() {
        return continuar;
    }
}
