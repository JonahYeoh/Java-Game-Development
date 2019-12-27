package dev;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
public class AudioReader {

    public static void PlayMusic(String FilePath){
        InputStream music;
        try{
            music = new FileInputStream(new File(FilePath));
            int data = music.available();
            System.out.println(data);
            AudioStream audio =new AudioStream(music);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error\n");
            e.printStackTrace();
        }
    }

}
