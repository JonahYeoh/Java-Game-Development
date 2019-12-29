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

        for(int j = 0; j < handler.list.size(); j++){
            AppObject obj = handler.list.get(j);
                if( obj.getTag() == TAG.ScoreArea){
                    score = (ScoreArea)obj;
                }
        }

        PAGE page = Application.getPage();
        if( page == PAGE.HOME)
            clickHome(keyCode);
        else if( page == PAGE.STARTUP)
            clickStartUp(keyCode);
        else if( page == PAGE.REGISTRATION)
            clickRegistration(keyCode);
        else if( page == PAGE.LOGIN)
            clickLogIn(keyCode);
        else if( page == PAGE.PLAY)
            clickPlay(keyCode);
        else if( page == PAGE.DASHBOARD)
            clickDashboard(keyCode);
    }

    public void keyReleased(KeyEvent e){
        // PENDING
    }
    private void clickHome(int key){

    }
    private void clickStartUp(int key){

    }
    private void clickRegistration(int key){
        NewMember obj =  (NewMember) handler.list.get(0);
        if( key != KeyEvent.VK_BACK_SPACE){
            if( obj.selectedNAME() && !obj.filled(true)){
                obj.addCharacter(true,(char)key);
            }
            else if (!obj.filled(false)){
                obj.addCharacter(false,(char)key);
            }
        }
        else{
            if( obj.selectedNAME()){
                obj.backSpace(true);
            }
            else{
                obj.backSpace(false);
            }
        }

    }
    private void clickLogIn(int key){

    }
    private void clickDashboard(int key){
        if( key == KeyEvent.VK_ESCAPE)
        {
            Application.setPage(PAGE.HOME);
            return;
        }
    }
    private void clickPlay(int key){
        if( key == KeyEvent.VK_ESCAPE)
        {
            Application.setPage(PAGE.HOME);
            return;
        }
        if(score.getStartSec() == 0){
            score.setStartSec(TimeUnit.SECONDS.convert(System.nanoTime(),TimeUnit.NANOSECONDS));
            System.out.println("Started");
        }
        for(int i = 0; i < handler.list.size(); i++){
            AppObject obj = handler.list.get(i);
            if(obj.getTag() == TAG.TextArea){
                if( key == (int)((TextArea)obj).getCurrentChar()){
                    AudioReader.PlayMusic("AudioFile/192272__lebaston100__click.wav");
                    if( ((TextArea)obj).hasNext()) {
                        ((TextArea) obj).nextChar();
                        System.out.println(key);
                    }
                    break;
                }
                else{
                    AudioReader.PlayMusic("AudioFile/316901__jaz-the-man-2__do-octave.wav");
                    score.setError(score.getError()+1);
                    System.out.println("Pressed " + (char)key + " instead of " + ((TextArea) obj).getCurrentChar());
                    break;
                }
            }
        }

    }
}
