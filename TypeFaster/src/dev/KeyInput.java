package dev;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private ScoreArea score;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if( keyCode >= 0x41)
            keyCode += 32;
        for(int i = 0; i < handler.list.size(); i++ ){
            AppObject obj = handler.list.get(i);
            if(score == null){
                if( obj.getTag() == TAG.ScoreArea){
                    score = (ScoreArea)obj;
                }
            }
            if(score.getStartSec() == 0){
                score.setStartSec(TimeUnit.SECONDS.convert(System.nanoTime(),TimeUnit.NANOSECONDS));
                System.out.println("Started");
            }
            if(obj.getTag() == TAG.TextArea){
                if( keyCode == (int)((TextArea)obj).getCurrentChar()){
                    AudioReader.PlayMusic("AudioFile/192272__lebaston100__click.wav");
                    if( ((TextArea)obj).hasNext()) {
                        ((TextArea) obj).nextChar();
                        System.out.println(keyCode);
                    }
                    break;
                }
                else{
                    AudioReader.PlayMusic("AudioFile/316901__jaz-the-man-2__do-octave.wav");
                    score.setError(score.getError()+1);
                    System.out.println("Pressed " + (char)keyCode + " instead of " + ((TextArea) obj).getCurrentChar());
                    break;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        // PENDING
    }
}
