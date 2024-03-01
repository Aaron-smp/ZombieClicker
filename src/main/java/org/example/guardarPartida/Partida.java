package org.example.guardarPartida;

import lombok.Data;
import org.example.controladorAudio.PanelDeSonido;
import org.example.mejoras.Mejora;

import java.io.Serializable;
import java.util.List;

@Data
public class Partida implements Serializable {
    private double killsTotalesDinero;
    private double autoKillsSecond;
    private double aumentoClick;
    private PanelDeSonido panelDeSonido;
    private List<Mejora> mejoras;
}
