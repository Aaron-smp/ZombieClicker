package org.example.controladorAudio.rutas;

public class Rutas {

    /**
     * Rutas de los audios que se utilizan en el juego
     */
    public static final String RUTA_AUDIO_MUSICA = "/audios/introJuego.mp3";
    public static final String RUTA_AUDIO_DISPARO = "/audios/disparo9mm.mp3";
    public static final String RUTA_AUDIO_COMPRA = "/audios/compra.mp3";
    public static final String RUTA_AUDIO_COMPRA_CANCEL = "/audios/cancel.mp3";
    public static final String RUTA_AUDIO_COMPRA_SOLDADO = "";
    public static final String RUTA_AUDIO_COMPRA_CARGADOR_DOBLE = "";
    public static final String RUTA_AUDIO_COMPRA_FUEGO_RAPIDO = "";
    public static final String RUTA_AUDIO_COMPRA_SILENCIADOR = "";
    public static final String RUTA_AUDIO_COMPRA_RADIO_SIMPLE = "";
    public static final String RUTA_AUDIO_COMPRA_ARMA_LARGO_ALCANCE = "";
    public static final String RUTA_AUDIO_COMPRA_ARMA_VEHICULO_BLINDADO = "";
    /**
     * Lista con las rutas de los sonidos
     */
    public static final String[] RUTAS_EFECTOS_SONIDO = {RUTA_AUDIO_DISPARO, RUTA_AUDIO_COMPRA, RUTA_AUDIO_COMPRA_CANCEL};
    public static final Integer SONIDO_DISPARO = 0;
    public static final Integer SONIDO_COMPRA = 1;
    public static final Integer SONIDO_COMPRA_CANCEL = 2;
    /**
     * Lista de rutas con los indices de los sonidos
     */
    public static final Integer[] SONIDOS = {SONIDO_DISPARO, SONIDO_COMPRA, SONIDO_COMPRA_CANCEL};

}
