package dev;

import java.awt.*;
import java.io.IOException;

public class HomePage extends AppObject {
    protected Font fnt;
    protected Color color;
    private static boolean FLAG = false;
    public HomePage(int x, int y, TAG tag) {
        super(x, y, tag);
        fnt = new Font("Arial",1,50);
        color = new Color(172, 230, 213);
        HomePage.FLAG = true;
    }

    @Override
    public void tick() throws IOException {

    }

    @Override
    public void render(Graphics g) {
        //int x = 350; int y = 250;
        g.setColor(color);
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,300,100);
        g.setFont(fnt);
        g.drawString("STAT", x+75, y+70);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+150,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+150,300,100);
        g.drawString("PLAY", x+85, y+220);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+300,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+300,300,100);
        g.drawString("QUIT", x+85, y+370);
    }
    public static boolean getFLAG(){
        return HomePage.FLAG;
    }
    public static void clearFLAG(){
        HomePage.FLAG = false;
    }
}
