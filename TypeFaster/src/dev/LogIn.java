package dev;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogIn extends AppObject {
    protected Font fnt;
    protected Color color;
    protected ArrayList<Character> name;
    protected ArrayList<Character> pwd;
    public LogIn(int x, int y, TAG tag) {
        super(x, y, tag);
        fnt = new Font("Arial",1,50);
        color = new Color(172, 230, 213);
        name = new ArrayList<>();
        pwd = new ArrayList<>();
    }
    public void backSpace(boolean NAME){
        if( NAME ){
            name.remove(name.size()-1);
        }
        else{
            pwd.remove(pwd.size()-1);
        }
    }
    public void addCharacter(boolean NAME, Character c){
        if( NAME ){
            name.add(c);
        }
        else{
            pwd.add(c);
        }
    }
    public boolean filled(boolean NAME){
        if( NAME ){
            return ( name.size() == 4 );
        }
        else{
            return ( pwd.size() == 4);
        }
    }
    @Override
    public void tick() throws IOException {

    }

    @Override
    public void render(Graphics g) {
        //int x = 300; int y = 250;

        g.setFont(fnt);
        g.setColor(color);
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(Color.BLUE);
        g.drawString("LOG-IN", x+100, y-100);
        g.setColor(Color.BLACK);
        g.drawString("NAME : ", x, y);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y - 50, 200, 60);

        g.setColor(Color.BLACK);
        g.drawString("PWD   : ", x, y+100);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y + 50, 200, 60);
        g.setColor(Color.CYAN);
        g.fillRect(x+50,y+200,300,80);
        g.setColor(Color.BLACK);
        g.drawString("CONFIRM",x+80,y+260);
    }

    public String query() throws IOException {
        String n = "";
        String p = "";
        for( int i = 0; i < name.size(); i++){
            n += name.get(i);
            p += pwd.get(i);
        }
        FileReader fr = new FileReader("DB/Personal/"+n+".txt");
        char buffer[] = new char[9];
        int num = fr.read(buffer);
        String data = new String(buffer,0,num);
        fr.close();
        return data;
    }
}
