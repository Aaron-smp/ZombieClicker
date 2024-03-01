package org.example.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static org.example.Main.panelDeSonido;

public class Ajustes extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSlider volumenMusica;
    private JButton siButton;
    private JLabel porcentajeBarra;
    private float volumenPrevio;
    public Ajustes() {
        setTitle("Ajustes");
        setContentPane(contentPane);
        setModal(true);
        setResizable(false);
        setSize(new Dimension(400, 150));
        getRootPane().setDefaultButton(buttonOK);

        volumenPrevio = panelDeSonido.getMusica().getFc().getValue();
        int volumenBarra = (int) Math.round(((panelDeSonido.getMusica().getFc().getValue() + 20) / 26.0206) * 100);
        volumenMusica.setValue(volumenBarra);
        porcentajeBarra.setText(volumenMusica.getValue() + "%");

        siButton.setText(panelDeSonido.isMuteSoundEffects() ? "No" : "Si");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDeSonido.muteOrUnmuteSoundEffects();
                if(panelDeSonido.isMuteSoundEffects()){
                    siButton.setText("No");
                }else{
                    siButton.setText("Si");
                }
            }
        });
        volumenMusica.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                porcentajeBarra.setText(volumenMusica.getValue() + "%");
                SwingUtilities.invokeLater(() -> {
                    panelDeSonido.getMusica().setPreviousVolume(panelDeSonido.getMusica().getCurrentVolume());
                    float volumen = (float) (volumenMusica.getValue() * (26.0206/100) - 20);
                    if(volumenMusica.getValue() == 0){
                        volumen = -80.0f;
                    }
                    panelDeSonido.getMusica().getFc().setValue(volumen);
                    panelDeSonido.getMusica().setCurrentVolume(volumen);
                });
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        panelDeSonido.getMusica().getFc().setValue(volumenPrevio);
        dispose();
    }
}
