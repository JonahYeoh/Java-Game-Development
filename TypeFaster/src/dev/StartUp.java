package dev;

import java.awt.*;
import java.io.IOException;

public class StartUp extends AppObject {
    protected Font fnt;
    protected Color color;
    public StartUp(int x, int y, TAG tag) {
        super(x, y, tag);
        fnt = new Font("Arial",1,50);
        color = new Color(172, 230, 213);
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
        g.drawString("LOGIN", x+75, y+70);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+150,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+150,300,100);
        g.drawString("REGISTER", x+23, y+220);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+300,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+300,300,100);
        g.drawString("QUIT", x+85, y+370);
    }
}
