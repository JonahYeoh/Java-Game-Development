package dev;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            fr = new FileReader("Paragraph/textSample.txt");
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void tick() throws IOException {
        PAGE page = Application.getPage();
        if( page == PAGE.PLAY){
            if( handler.list.size() != 3){
                handler.removeAllObject();
                handler.addObject(new ScoreArea(62,167,TAG.ScoreArea));
                handler.addObject(new TextArea(62,250, TAG.TextArea, "somewhere"));
                handler.addObject(new ImageArea(62, 83, TAG.ImageArea, handler, window));
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
                String dataString = "Score="+score.getSCORE()+"&CPM="+score.getCPM()+"&ERROR="+score.getERROR();
                writeRecord(dataString);
            }
        }
        else if( page == PAGE.HOME){
            handler.removeAllObject();
            handler.addObject(new HomePage(350, 250, TAG.HOME));
        }
        else if(page == PAGE.STARTUP){
            handler.removeAllObject();
            handler.addObject(new StartUp(350, 250, TAG.STARTUP));
            System.out.println("generating menu");
        }
        else if( page == PAGE.REGISTRATION){
            handler.removeAllObject();
            handler.addObject(new NewMember(300, 250, TAG.REGISTRATION));
        }
        else if( page == PAGE.LOGIN){
            handler.removeAllObject();
            handler.addObject(new LogIn(300, 250, TAG.LOGIN));
        }
        else if( page == PAGE.DASHBOARD){
            handler.removeAllObject();
            System.out.println("Feature not ready");
            // TODO
        }

    }

    @Override
    public void render(Graphics g) {

    }
    public void writeRecord(String data){
        FileWriter fw;
        try{
            fw = new FileWriter("DB/record.txt",true);
            fw.write(data);
            fw.write("\r\n");
            fw.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }


    }
    private String readNextParagraph() throws IOException {
        char buffer[] = new char[50];
        int num = fr.read(buffer);
        String str = new String(buffer,0,num);
        str = str.toLowerCase();
        System.out.println(str);
        System.out.println(num);
        return str;
    }
}
