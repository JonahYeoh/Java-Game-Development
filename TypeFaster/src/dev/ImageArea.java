package dev;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageArea extends AppObject implements CoreOperation{
    protected Handler handler;
    protected TextArea textArea;
    protected Application window;
    public ImageArea(int x, int y, TAG tag, Handler handler, Application window) {
        super(x, y, tag);
        this.handler = handler;
        this.window = window;
    }

    @Override
    public void tick() throws IOException {
        for(int i = 0; i < handler.list.size(); i++){
            AppObject obj = handler.list.get(i);
            if(obj.getTag() == TAG.TextArea) {
                textArea = (TextArea) obj;
                break;
            }
        }
        x = 62 + 875*textArea.getCurrentIndex()/50;
        //System.out.println(x);
    }

    @Override
    public void render(Graphics g) {
        ImageIcon icon = new ImageIcon("Animation/external-content.duckduckgo.com.png");
        Image img = icon.getImage();
        g.drawImage(img,x,y,83,83,window);
    }
}
