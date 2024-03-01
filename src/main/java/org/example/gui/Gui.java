package org.example.gui;

import org.example.controladorAudio.MP3Reproductor;
import org.example.mejoras.Mejora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.Main.*;
import static org.example.controladorAudio.rutas.Rutas.RUTA_AUDIO_DISPARO;
import static org.example.controladorAudio.rutas.Rutas.SONIDO_DISPARO;
import static org.example.utils.InformacionMejoras.*;

public class Gui {
    private JScrollPane listaMejoras;
    private JButton iconoZombieQueAumentaButton;
    public JPanel base;
    private JPanel panelBaseScroll;
    private JLabel nKillsTotales;
    private JLabel killsPorSecond;
    private JButton ajustesButton;
    private JLabel aumentoClickText;
    private JProgressBar progresoSegundo;
    public static java.util.List<ObjetoMejora> mejorasObjetosGui;

    public Gui()  {
        listaMejoras.getVerticalScrollBar().setUnitIncrement(20);
        panelBaseScroll.setLayout(new GridLayout(100, 1));

        panelBaseScroll.setLayout(new GridLayout(0, 1));
        panelBaseScroll.setSize(new Dimension(100, 200));
        listaMejoras.setViewportView(panelBaseScroll);


        mejorasObjetosGui = new ArrayList<>();
        crearListaMejoras();

        killsPorSecond.setText("Kills por segundo: " + autoKillsSecond);

        iniciarJuego();


        panelDeSonido.cargarMusica(panelDeSonido.getMusica().getPreviousVolume(), panelDeSonido.getMusica().getCurrentVolume(), panelDeSonido.getMusica().isMute());
        actualizarUiMejora();

        iconoZombieQueAumentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                killsTotalesDinero = killsTotalesDinero + aumentoClick;
                SwingUtilities.invokeLater( () -> {
                    actualizarUiMejora();
                    MP3Reproductor mp3Reproductor = new MP3Reproductor(RUTA_AUDIO_DISPARO, panelDeSonido.getEfectosSonido().get(SONIDO_DISPARO));
                    mp3Reproductor.play();
                });
            }
        });

        iconoZombieQueAumentaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                iconoZombieQueAumentaButton.setIcon(new ImageIcon(getClass().getResource("/iconos/zombi_sinBorde.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                iconoZombieQueAumentaButton.setIcon(new ImageIcon(getClass().getResource("/iconos/zombi.png")));
            }
        });
        ajustesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ajustes ajustes = new Ajustes();
                ajustes.setPreferredSize(new Dimension(200, 300));
                ajustes.setLocationRelativeTo(base);
                ajustes.setVisible(true);
            }
        });
    }

    private void actualizarUiMejora(){
        nKillsTotales.setText("Muertes totales: " + (long) killsTotalesDinero);
        killsPorSecond.setText("Kills por segundo: " + autoKillsSecond);
        mejorasObjetosGui.get(0).comprobarComprable();
    }
    private void iniciarJuego(){
        Timer timer = new Timer(30, e -> {
            SwingUtilities.invokeLater( () -> {
                nKillsTotales.setText("Muertes totales: " + (long) killsTotalesDinero);
                killsPorSecond.setText("Kills por segundo: " + autoKillsSecond);
                aumentoClickText.setText("+" + aumentoClick);
            });
        });
        timer.start();

        AtomicReference<Float> numBarra = new AtomicReference<>(0f);
        Timer rellenarBarra = new Timer(10, i -> {

        });
        rellenarBarra.start();

        Timer incrementKills = new Timer(1000, e -> {
            Thread h1 = new Thread(() -> {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(progresoSegundo.getValue() >= 100){
                        numBarra.set(0f);
                        progresoSegundo.setValue(numBarra.get().intValue());
                        break;
                    }else{
                        progresoSegundo.setValue(numBarra.get().intValue());
                        numBarra.set(numBarra.get() + 1.6666666f);
                    }
                }
                killsTotalesDinero += autoKillsSecond;
                mejorasObjetosGui.get(0).comprobarComprable();
            });
            h1.start();
        });
        incrementKills.start();
    }


    private void crearListaMejoras(){
        if(null == mejoras){
            mejoras = new ArrayList<>();
            mejoras.add(new Mejora(SOLDADO, 10, 0.5, DESCRIPCION_SOLDADO, 0));
            mejoras.add(new Mejora(CARGADOR_DOBLE, 10, 0.5, DESCRIPCION_CARGADOR_DOBLE, 0));
            mejoras.add(new Mejora(FUEGO_RAPIDO, 100, 5, DESCRIPCION_FUEGO_RAPIDO, 0));
            mejoras.add(new Mejora(SILENCIADOR, 250, 10, DESCRIPCION_SILENCIADOR, 0));
            mejoras.add(new Mejora(RADIO_SIMPLE, 500, 20, DESCRIPCION_RADIO_SIMPLE, 0));
            mejoras.add(new Mejora(ARMA_LARGO_ALCANCE, 800, 30, DESCRIPCION_ARMA_LARGO_ALCANCE, 0));
            mejoras.add(new Mejora(ADIESTRAMIENTO_MILITAR, 1000, 50, DESCRIPCION_ADIESTRAMIENTO_MILITAR, 0));
            mejoras.add(new Mejora(VEHICULO_BLINDADO, 10000, 150, DESCRIPCION_VEHICULO_BLINDADO, 0));
        }

        for(int i = 0; mejoras.size() > i; i++){
            ObjetoMejora objetoMejora = new ObjetoMejora(mejoras.get(i), new ImageIcon(getClass().getResource(RUTAS[i])));
            objetoMejora.setComprable();
            mejorasObjetosGui.add(objetoMejora);
            panelBaseScroll.add(objetoMejora.base);
        }
        listaMejoras.repaint();
    }


}
