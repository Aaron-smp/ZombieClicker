package org.example.guardarPartida;

import lombok.NoArgsConstructor;
import org.example.Main;
import org.example.controladorAudio.PanelDeSonido;

import java.io.File;

@NoArgsConstructor
public class Guardar {
    public static final String ARCHIVO_BIN_PARTIDA = "ClickerSave.bin";
    private LeerBinario leerBinario = new LeerBinario();
    private Partida partida;

    private void cargarObjetoPartida(){
        partida = new Partida();
        this.partida.setAumentoClick(Main.aumentoClick);
        this.partida.setAutoKillsSecond(Main.autoKillsSecond);
        this.partida.setKillsTotalesDinero(Main.killsTotalesDinero);
        this.partida.setPanelDeSonido(Main.panelDeSonido);
        this.partida.setMejoras(Main.mejoras);
    }

    private boolean existBin(){
        File partidaGuardada = new File(ARCHIVO_BIN_PARTIDA);
        if(partidaGuardada.exists()) {
            return true;
        }
        return false;
    }
    public void saveMatch(){
        cargarObjetoPartida();
        leerBinario.save(partida);
    }

    public void cargarPartida(){
        if(existBin()){
            partida = leerBinario.readSave();
            if(null != partida){
                Main.autoKillsSecond = partida.getAutoKillsSecond();
                Main.aumentoClick = partida.getAumentoClick();
                Main.killsTotalesDinero = partida.getKillsTotalesDinero();
                Main.panelDeSonido = partida.getPanelDeSonido();
                Main.mejoras = partida.getMejoras();
            }else{
                Main.panelDeSonido = new PanelDeSonido();
                Main.aumentoClick = 1;
                Main.autoKillsSecond = 0.4;
                Main.killsTotalesDinero = 0;
            }
        }else{
            Main.panelDeSonido = new PanelDeSonido();
            Main.aumentoClick = 1;
            Main.autoKillsSecond = 0.4;
            Main.killsTotalesDinero = 0;
        }
    }
}
