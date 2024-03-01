package org.example;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.example.controladorAudio.PanelDeSonido;
import org.example.guardarPartida.Guardar;
import org.example.gui.EntradaJuego;
import org.example.gui.Gui;
import org.example.mejoras.Mejora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static org.example.utils.InformacionMejoras.customFont;

public class Main {

    public static double killsTotalesDinero;
    public static double autoKillsSecond;
    public static double aumentoClick;
    public static PanelDeSonido panelDeSonido;
    public static java.util.List<Mejora> mejoras;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
            if (customFont != null) {
                customFont = customFont.deriveFont(16f);
                UIManager.put("Button.font", customFont);
                UIManager.put("Label.font", customFont);
                UIManager.put("TextField.font", customFont);
            }
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        UIManager.put("ToolTip.font", new Font("Cascadia code", Font.PLAIN, 16));
        JFrame frame = new JFrame("Panel de control");
        EntradaJuego entradaJuego = new EntradaJuego(frame);
        if(!entradaJuego.getContinuar()){
            System.exit(0);
        }

        Guardar guardar = new Guardar();
        guardar.cargarPartida();

        frame.setContentPane(new Gui().base);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(1100, 550));
        frame.setSize(new Dimension(950, 550));
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/iconos/iconApp.png"))).getImage());
        frame.setVisible(true);
        frame.setTitle("Zombie Idle");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("GUARDAR PARTIDA");
                guardar.saveMatch();
            }
        });
    }
}