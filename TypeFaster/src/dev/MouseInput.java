package dev;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private Handler handler;
    protected int x, y;
    public MouseInput(Handler handler){
        this.handler = handler;
    }
    @Override public void mousePressed(MouseEvent me){
        System.out.println("Pressed " + me.getX() + " , " + me.getY());

        if( Application.getPage() == PAGE.MENU){
            x = me.getX();
            y = me.getY();
            if( x >= 350 && x <= 650 && y >= 300 && y <= 400 ) {
                System.out.println("Clicked Menu");

            }
            else if( x >= 350 && x <= 650 && y >= 500 && y <= 600 )
                System.out.println("Clicked Play");
        }
    }
}
