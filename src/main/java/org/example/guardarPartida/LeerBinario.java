package org.example.guardarPartida;

import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.*;

import static org.example.guardarPartida.Guardar.ARCHIVO_BIN_PARTIDA;

@NoArgsConstructor
public class LeerBinario {
    public void save(Partida partida){
        try {
            crearArchivoBin();

            FileOutputStream fileOut = new FileOutputStream(ARCHIVO_BIN_PARTIDA);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(partida);

            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Partida readSave(){
        Partida partida = null;
        try {
            FileInputStream fileIn = new FileInputStream(ARCHIVO_BIN_PARTIDA);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            partida = (Partida) objectIn.readObject();

            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return partida;
    }

    public void crearArchivoBin() throws IOException {
        File archivoBin = new File(ARCHIVO_BIN_PARTIDA);
        if(!archivoBin.exists()) archivoBin.createNewFile();
    }
}
