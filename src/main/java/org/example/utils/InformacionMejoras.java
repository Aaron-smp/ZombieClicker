package org.example.utils;

import sun.font.CreatedFontTracker;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Objects;

public class InformacionMejoras {

    /**
     * Mejoras que dispone el juego
     */
    public static final String SOLDADO = "Soldado";
    public static final String CARGADOR_DOBLE = "Cargador Doble";
    public static final String FUEGO_RAPIDO = "Fuego Rapido";
    public static final String SILENCIADOR = "Silenciador";
    public static final String RADIO_SIMPLE = "Radio Simple";
    public static final String ARMA_LARGO_ALCANCE = "Fusil de Asalto";
    public static final String ADIESTRAMIENTO_MILITAR = "Adiestramiento Militar";
    public static final String VEHICULO_BLINDADO = "Vehiculo Blindado";

    /**
     * Descripciones de las mejoras en el juego
     */
    public static final String DESCRIPCION_SOLDADO = "Compra soldados si quieres machacar a esos zombies," +
            " esto aumentara al doble el ataque a los zombies";
    public static final String DESCRIPCION_CARGADOR_DOBLE = "Pilla cargadores y nunca dejes de disparar," +
            " !Vamos a reventar a esos sin vida!";
    public static final String DESCRIPCION_FUEGO_RAPIDO = "Entre mas balas salgan del cañon, ¿mejor no? " +
            "¡No les dejes avanzar!";
    public static final String DESCRIPCION_SILENCIADOR = "Si no te escuchan no vendran a por ti," +
            " ¡Compra silenciadores para no dejar de reventarlos!";
    public static final String DESCRIPCION_RADIO_SIMPLE = "La logistica en una guerra es lo mas importante, y esto es una ¡GUERRAAA!";
    public static final String DESCRIPCION_ARMA_LARGO_ALCANCE = "Si ampliamos el rango de aniquilamiento de zombies, dime si se acercaran";
    public static final String DESCRIPCION_ADIESTRAMIENTO_MILITAR = "Hay que ponerse en forma quitar la panza y aprender nuevas tacticas militares," +
            " ¡Ponte en marcha!";
    public static final String DESCRIPCION_VEHICULO_BLINDADO = "Lo último en vehículos de guerra postapocalipticos, dejarlos con vida nunca fue una opción, " +
            "solo podras comprar uno cada 4 soldados en tu ejercito";

    /**
     * Rutas de los iconos de las mejoras
     */
    public static final String RUTA_ICONO_SOLDADO = "/iconos/soldado.png";
    public static final String RUTA_ICONO_CARGADOR_DOBLE = "/iconos/municion.png";
    public static final String RUTA_ICONO_FUEGO_RAPIDO = "/iconos/rapido.png";
    public static final String RUTA_ICONO_SILENCIADOR = "/iconos/silenciador.png";
    public static final String RUTA_ICONO_RADIO_SIMPLE = "/iconos/radio.png";
    public static final String RUTA_ICONO_ARMA_LARGO_ALCANCE = "/iconos/fusil.png";
    public static final String RUTA_ICONO_ADIESTRAMIENTO_MILITAR = "/iconos/entrenamiento.png";
    public static final String RUTA_ICONO_VEHICULO_BLINDADO = "/iconos/furgoneta.png";
    public static final String[] RUTAS= {RUTA_ICONO_SOLDADO, RUTA_ICONO_CARGADOR_DOBLE, RUTA_ICONO_FUEGO_RAPIDO, RUTA_ICONO_SILENCIADOR, RUTA_ICONO_RADIO_SIMPLE,
            RUTA_ICONO_ARMA_LARGO_ALCANCE, RUTA_ICONO_ADIESTRAMIENTO_MILITAR, RUTA_ICONO_VEHICULO_BLINDADO};

    public static Font customFont;

    static {
        try {
            InputStream is = InformacionMejoras.class.getResourceAsStream("/fuentesTexto/CascadiaCode.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
