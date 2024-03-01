package org.example.controladorAudio;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.example.controladorAudio.rutas.Rutas.*;

@Data
public class PanelDeSonido implements Serializable {
    private MP3Reproductor musica;
    private Map<Integer, MP3Reproductor> efectosSonido;
    private boolean muteSoundEffects;
    public PanelDeSonido(){
        cargarMusica(0.0f, 0.0f, false);
        cargarEfectosDeSonido();
        muteSoundEffects = false;
    }

    public void cargarMusica(float previousVolume, float currentVolume, boolean mute){
        musica = new MP3Reproductor(RUTA_AUDIO_MUSICA);
        if(previousVolume != 0.0f || currentVolume != 0.0f){
            musica.setCurrentVolume(currentVolume);
            musica.setPreviousVolume(previousVolume);
            musica.setMute(mute);
            musica.getFc().setValue(currentVolume);
        }
        musica.loop();
        musica.play();
    }

    private void cargarEfectosDeSonido(){
        efectosSonido = new HashMap<>();

        for(int i = 0; i < RUTAS_EFECTOS_SONIDO.length; i++){
            MP3Reproductor efectoSonido = new MP3Reproductor(RUTAS_EFECTOS_SONIDO[i]);
            efectosSonido.put(SONIDOS[i], efectoSonido);
        }
    }

    public void muteOrUnmuteSoundEffects(){
        for (Map.Entry<Integer, MP3Reproductor> entry : efectosSonido.entrySet()) {
            entry.getValue().volumeMute();
        }
        muteSoundEffects = !muteSoundEffects;
    }
}
