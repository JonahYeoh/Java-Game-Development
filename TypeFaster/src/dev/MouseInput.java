package dev;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseInput extends MouseAdapter {
    private Handler handler;

    public MouseInput(Handler handler){
        this.handler = handler;
    }
    @Override public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        System.out.println("Pressed " + x + " , " + y);
        PAGE page = Application.getPage();
        if( page == PAGE.HOME)
            clickHome(x,y);
        else if( page == PAGE.STARTUP)
            clickStartUp(x,y);
        else if( page == PAGE.REGISTRATION)
            clickRegistration(x,y);
        else if( page == PAGE.LOGIN) {
            try {
                clickLogIn(x,y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public void clickHome(int x, int y){
        if( x >= 300 && x <= 650){
            if( y >= 250 && y <= 350){
                Application.setPage(PAGE.DASHBOARD);
            }
            else if( y >= 400 && y <= 500){
                Application.setPage(PAGE.PLAY);
            }
            else if( y >= 550 && y <= 650){
                System.exit(1);
            }
        }
    }
    public void clickStartUp(int x, int y){
        if( x >= 300 && x <= 650){
            if( y >= 250 && y <= 350){
                Application.setPage(PAGE.LOGIN);
            }
            else if( y >= 400 && y <= 500){
                Application.setPage(PAGE.REGISTRATION);
            }
            else if( y >= 550 && y <= 650){
                System.exit(1);
            }
        }
    }
    public void clickLogIn(int x, int y) throws IOException {
        LogIn obj = (LogIn)handler.list.get(0);
        if( x >= 500 && x <= 700){
            if( y >= 200 && y <= 260){
                System.out.println("NAME");
                obj.textBoxSelector(true);
            }
            else if( y >= 300 && y <= 360){
                System.out.println("PWD");
                obj.textBoxSelector(false);
            }
        }
        if( x >= 350 && x <= 650){
            if( y >= 450 && y <= 530){
                if( obj.filled(true) && obj.filled(false)) {
                    System.out.println("Confirm");
                    if (((LogIn) handler.list.get(0)).query() == QUERY_STAT.MATCH)
                        Application.setPage(PAGE.PLAY);
                    else if (((LogIn) handler.list.get(0)).query() == QUERY_STAT.NF)
                        Application.setPage(PAGE.REGISTRATION);
                }
            }
        }
    }
    public void clickRegistration(int x,int y){
        NewMember obj = (NewMember)handler.list.get(0);
        if( x >= 500 && x <= 700){
            if( y >= 200 && y <= 260){
                System.out.println("NAME");
                obj.textBoxSelector(true);
            }
            else if( y >= 300 && y <= 360){
                System.out.println("PWD");
                obj.textBoxSelector(false);
            }
        }
        if( x >= 350 && x <= 650){
            if( y >= 450 && y <= 530){
                if( obj.filled(true) && obj.filled(false)){
                    System.out.println("Confirm");
                    obj.writeThrough();
                    Application.setPage(PAGE.PLAY);
                }
            }
        }
    }
}
