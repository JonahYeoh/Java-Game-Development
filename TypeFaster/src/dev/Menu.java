package dev;

import java.awt.*;
import java.io.IOException;

public class Menu extends AppObject implements CoreOperation {
    public Menu(int x, int y, TAG tag) {
        super(x, y, tag);
    }

    @Override
    public void tick() throws IOException {

    }

    @Override
    public void render(Graphics g) {
        Font fnt = new Font("Arial",1,50);
        g.setColor(new Color(172, 230, 213));
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,300,100);
        g.setFont(fnt);
        g.drawString("MENU", x+75, y+70);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+200,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+200,300,100);
        g.drawString("PLAY", x+85, y+270);
    }

}
