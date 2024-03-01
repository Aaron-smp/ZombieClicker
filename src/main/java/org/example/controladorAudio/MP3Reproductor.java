package org.example.controladorAudio;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
import lombok.Data;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URISyntaxException;

@Data
public class MP3Reproductor implements Serializable{

    private static final long serialVersionUID = 8363203716096419894L;

    private transient Clip clip;
    private transient FloatControl fc;
    private float previousVolume;
    private float currentVolume;
    private boolean mute = false;
    private byte[] wavData;
    public MP3Reproductor(String mp3Path){
        try{
            wavData = convertMp3ToWavInMemory(mp3Path);
            AudioInputStream sound = null;
            try {
                sound = AudioSystem.getAudioInputStream(new ByteArrayInputStream(wavData));
                clip = AudioSystem.getClip();
                clip.open(sound);
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                fc.setValue(-6.9897f);
                currentVolume = fc.getValue();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
        }catch (IOException | JavaLayerException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public MP3Reproductor(String mp3Path, MP3Reproductor mp3Reproductor) {
        try{
            wavData = convertMp3ToWavInMemory(mp3Path);
            AudioInputStream sound = null;
            try {
                sound = AudioSystem.getAudioInputStream(new ByteArrayInputStream(wavData));
                clip = AudioSystem.getClip();
                clip.open(sound);
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                fc.setValue(-6.9897f);
                currentVolume = fc.getValue();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }catch (IOException | JavaLayerException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if(mp3Reproductor.isMute()){
            volumeMute();
        }
    }

    public byte[] convertMp3ToWavInMemory(String mp3FilePath) throws IOException, JavaLayerException, URISyntaxException {
        String nombreAudio = "introJuego.wav";

        Converter converter = new Converter();
        converter.convert(MP3Reproductor.class.getResourceAsStream(mp3FilePath), nombreAudio, null, new Decoder.Params());

        byte[] wavData = loadWavDataFromFile(nombreAudio);

        // Eliminar el archivo temporal
        File tempWavFile = new File(nombreAudio);
        if (tempWavFile.exists()) {
            tempWavFile.delete();
        }

        return wavData;
    }
    public byte[] loadWavDataFromFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();

        return byteArrayOutputStream.toByteArray();
    }
    public void play(){
        if (!clip.isActive()) { // Verifica si el clip está reproduciéndose
            clip.setMicrosecondPosition(0); // Reinicia la posición del clip al principio
            clip.start(); // Reproduce el audio
        }
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    public void volumeUp(){
        currentVolume += 1.0f;
        if (currentVolume > 6.0f){
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeDown(){
        currentVolume -= 1.0f;
        if (currentVolume < -80.0f){
            currentVolume = 80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeMute(){
        if(null != fc){
            if (!mute){
                previousVolume = currentVolume;
                currentVolume = -80.0f;
                fc.setValue(currentVolume);
            }else if(mute){
                currentVolume = previousVolume;
                fc.setValue(currentVolume);

            }
        }
        if (!mute){
            mute = true;
        }else if(mute){
            mute = false;
        }
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
