package dev;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NewMember extends AppObject {
    protected Font fnt;
    protected Color color;
    protected ArrayList<Character> name;
    protected ArrayList<Character> pwd;
    protected boolean selector;
    private static boolean FLAG = false;
    public NewMember(int x, int y, TAG tag) {
        super(x, y, tag);
        fnt = new Font("Arial",1,50);
        color = new Color(172, 230, 213);
        name = new ArrayList<>();
        pwd = new ArrayList<>();
        selector = true;
        NewMember.FLAG = true;
        System.out.println("Endless");
    }
    public static boolean getFLAG(){
        return NewMember.FLAG;
    }
    public static void clearFLAG(){
        NewMember.FLAG = false;
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
    public void writeThrough(){
        FileWriter fw;
        String n = "";
        String p = "";
        for( int i = 0; i < name.size(); i++){
            n += name.get(i);
            p += pwd.get(i);
        }
        Application.setPlayer(n);
        try{
            fw = new FileWriter("DB/Personal/"+n+".txt");
            fw.write(n+"&"+p);
            Application.setPlayer(n);
            fw.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void textBoxSelector(boolean NAME){
        selector = NAME;
    }
    public boolean selectedNAME(){
        return selector;
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
        g.drawString("REGISTRATION", x+10, y-100);
        g.setColor(Color.BLACK);
        g.drawString("NAME : ", x, y);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y - 50, 200, 60);
        g.setColor(Color.BLACK);
        if( name.size() != 0){
            String n = "";
            for(int i = 0; i < name.size(); i++)
                n += name.get(i);
            g.drawString(n, x+200, y);
        }
        g.drawString("PWD   : ", x, y+100);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y + 50, 200, 60);
        g.setColor(Color.BLACK);
        if( pwd.size()!=0){
            String p = "";
            for(int i = 0; i < pwd.size(); i++)
                p += pwd.get(i);
            g.drawString(p, x+200, y+100);
        }
        g.setColor(Color.CYAN);
        g.fillRect(x+50,y+200,300,80);
        g.setColor(Color.BLACK);
        g.drawString("CONFIRM",x+80,y+260);
    }
}
