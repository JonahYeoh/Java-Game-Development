package dev;

import java.awt.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Generator implements CoreOperation{
    protected Handler handler;
    protected Application window;
    protected TextArea textArea;
    protected ScoreArea score;
    protected FileReader fr;

    public Generator(Handler handler, Application window) {
        this.handler = handler;
        try {
            fr = new FileReader("Paragraph/textSample2.txt");
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void tick() throws IOException {
        PAGE page = Application.getPage();
        if( page == PAGE.PLAY){
            if( !handler.list.contains(score)){
                handler.removeAllObject();
                handler.addObject(new ScoreArea(62,167,TAG.ScoreArea));
                handler.addObject(new TextArea(62,250, TAG.TextArea, "testing, expect to see this everytime you come back"));
                handler.addObject(new ImageArea(62, 83, TAG.ImageArea, handler, window));
                System.out.println("Size : " + handler.list.size());
            }
            for(int i = 0; i < handler.list.size(); i++ ){
                AppObject obj = handler.list.get(i);
                if( obj.getTag() == TAG.TextArea)
                    textArea = (TextArea)obj;
                if( obj.getTag() == TAG.ScoreArea)
                    score = (ScoreArea)obj;
            }
            if( textArea.endOfLine() ){
                handler.removeObject(textArea);
                System.out.println("removing " + textArea.getTag());
                handler.addObject(new TextArea(62,250, TAG.TextArea, readNextParagraph()));
                score.setEndSec(TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS));
                score.setSpeed(50);
                String dataString = ""+score.getSCORE()+"&"+score.getCPM()+"&"+score.getERROR()+"&";
                writeRecord(dataString);
            }
        }
        else if( page == PAGE.HOME){
            if( !HomePage.getFLAG()) {
                handler.removeAllObject();
                handler.addObject(new HomePage(350, 250, TAG.HOME));
            }
        }
        else if(page == PAGE.STARTUP){
            if( !StartUp.getFLAG()) {
                handler.removeAllObject();
                handler.addObject(new StartUp(350, 250, TAG.STARTUP));
            }
        }
        else if( page == PAGE.REGISTRATION){
            if( !NewMember.getFLAG()){
                handler.removeAllObject();
                handler.addObject(new NewMember(300, 250, TAG.REGISTRATION));
            }
        }
        else if( page == PAGE.LOGIN){
            if( !LogIn.getFLAG()) {
                handler.removeAllObject();
                handler.addObject(new LogIn(300, 250, TAG.LOGIN));
            }
        }
        else if( page == PAGE.DASHBOARD){
            if( !Dashboard.getFLAG()) {
                handler.removeAllObject();
                handler.addObject(new Dashboard(0, 0, TAG.DASHBOARD));
            }
        }

    }

    @Override
    public void render(Graphics g) {

    }
    public void writeRecord(String data){
        FileWriter fw;
        try{
            fw = new FileWriter("DB/data_" + Application.getPlayer() + ".txt", true);
            fw.write(data);
            fw.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }


    }
    private String readNextParagraph() throws IOException {
        char buffer[] = new char[50];
        String str = null;
        try{
            int num = fr.read(buffer);
            if( num == -1 ) {
                //fr.close();
                fr = new FileReader("Paragraph/textSample.txt");
                num = fr.read(buffer);
                str = new String(buffer,0,num);
                str = str.toLowerCase();
                System.out.println(str);
                System.out.println(num);
            }
            else{
                str = new String(buffer,0,num);
                str = str.toLowerCase();
                System.out.println(str);
                System.out.println(num);
            }

        }catch(EOFException ex){
            ex.printStackTrace();
        }

        return str;
    }
}
