package org.example.gui;

import lombok.Getter;
import org.example.controladorAudio.MP3Reproductor;
import org.example.mejoras.Mejora;
import org.example.utils.InformacionMejoras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Main.*;
import static org.example.controladorAudio.rutas.Rutas.*;
import static org.example.gui.Gui.mejorasObjetosGui;
import static org.example.utils.Utils.truncateDouble;

public class ObjetoMejora {
    private JLabel iconoMejora;
    private JLabel nombreMejoraLabel;
    private JLabel precioMejoraLabel;
    JPanel base;
    private JButton comprarButton;
    private Mejora mejora;
    private long unidadesCompradas;
    @Getter
    private JLabel unidadesCompradasLabel;
    private JLabel produccionTotal;
    private JLabel produccionIndividual;
    private JLabel informacion;

    public ObjetoMejora(Mejora mejora, Icon icono){
        this.iconoMejora.setIcon(icono);
        this.informacion.setToolTipText(mejora.getDescripcion());
        this.nombreMejoraLabel.setText(mejora.getNombre());
        this.mejora = mejora;
        this.precioMejoraLabel.setText("Precio: " + mejora.getCoste());

        unidadesCompradas = 0;
        if(this.mejora.getNombre().equals(InformacionMejoras.SOLDADO)){
            produccionIndividual.setVisible(false);
            produccionTotal.setText("Produccion total: " + aumentoClick);
        }
        produccionIndividual.setText("Producción individual: " + mejora.getAumento());
        setProduccionTotal();
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() ->{
                    if(setComprable()){
                        MP3Reproductor mp3Reproductor = new MP3Reproductor(RUTA_AUDIO_COMPRA, panelDeSonido.getEfectosSonido().get(SONIDO_COMPRA));
                        mp3Reproductor.play();
                    }else{
                        MP3Reproductor mp3Reproductor = new MP3Reproductor(RUTA_AUDIO_COMPRA_CANCEL, panelDeSonido.getEfectosSonido().get(SONIDO_COMPRA_CANCEL));
                        mp3Reproductor.play();
                    }
                    comprar((long) killsTotalesDinero);
                    comprobarComprable();
                    unidadesCompradasLabel.setText("Unidades compradas: " + mejora.getNumeroCompras());
                    precioMejoraLabel.setText("Precio: " + mejora.getCoste());
                    setProduccionTotal();
                });
            }
        });
    }

    private void setProduccionTotal(){
        if(mejora.getNombre().equals(InformacionMejoras.SOLDADO)){
            produccionTotal.setText("Produccion total: " + aumentoClick);
        }else{
            produccionTotal.setText("Produccion total: " + mejora.getAumento() * mejora.getNumeroCompras());
        }
    }

    private void createUIComponents() {
        iconoMejora = new JLabel();
        nombreMejoraLabel = new JLabel();
        precioMejoraLabel = new JLabel();
    }
    public void comprobarComprable(){
        for(int i = 0; mejorasObjetosGui.size() > i; i++){
            ObjetoMejora objetoMejora = mejorasObjetosGui.get(i);
            objetoMejora.setComprable();
        }
    }
    public boolean setComprable(){
        unidadesCompradasLabel.setText("Unidades compradas: " + mejora.getNumeroCompras());
        if(killsTotalesDinero >= mejora.getCoste()){
            comprarButton.setForeground(Color.BLACK);
            comprarButton.setBackground(Color.GREEN);
            return true;
        }else{
            comprarButton.setForeground(Color.WHITE);
            comprarButton.setBackground(Color.RED);
            return false;
        }
    }
    public boolean comprar(long dineroJugador){
        if(dineroJugador >= this.mejora.getCoste()){
            mejora.setNumeroCompras(mejora.getNumeroCompras() + 1);
            killsTotalesDinero = dineroJugador - this.mejora.getCoste();
            switch (this.mejora.getNombre()){
                case InformacionMejoras.SOLDADO:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 4))));
                    aumentoClick = aumentoClick*2;
                    mejora.setAumento(aumentoClick);
                    break;
                case InformacionMejoras.CARGADOR_DOBLE:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.05))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                case InformacionMejoras.FUEGO_RAPIDO:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.10))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                case InformacionMejoras.SILENCIADOR:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.15))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                case InformacionMejoras.RADIO_SIMPLE:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.20))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                case InformacionMejoras.ARMA_LARGO_ALCANCE:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.25))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                case InformacionMejoras.ADIESTRAMIENTO_MILITAR:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.30))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                case InformacionMejoras.VEHICULO_BLINDADO:
                    mejora.setCoste(truncateDouble(String.valueOf((mejora.getCoste() * 1.40))));
                    autoKillsSecond = autoKillsSecond + this.mejora.getAumento();
                    break;
                default:
                    break;

            }
            return true;
        }
        return false;
    }
    public Mejora getMejora() {
        return mejora;
    }
}
