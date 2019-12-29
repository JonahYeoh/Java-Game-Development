package dev;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Handler implements CoreOperation {
    protected ArrayList<AppObject> list = new ArrayList<AppObject>();
    @Override
    public void tick() throws IOException {
        for(int i = 0; i < list.size(); i++ ){
            AppObject obj = list.get(i);
            obj.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0; i < list.size(); i++ ){
            AppObject obj = list.get(i);
            obj.render(g);
        }
    }
    public void addObject(AppObject item){
        this.list.add(item);
    }
    public void removeObject(AppObject item){
        this.list.remove(item);
    }
    public void removeAllObject(){
        for(int i = 0; i < list.size(); i++) {
            AppObject obj = list.get(i);
            if( obj.getTag() == TAG.DASHBOARD)
                Dashboard.clearFLAG();
            else if ( obj.getTag() == TAG.LOGIN)
                LogIn.clearFLAG();
            else if( obj.getTag() == TAG.REGISTRATION)
                NewMember.clearFLAG();
            else if ( obj.getTag() == TAG.STARTUP)
                StartUp.clearFLAG();
            else if( obj.getTag() == TAG.HOME)
                HomePage.clearFLAG();
            System.out.println(obj.getTag());
            removeObject(obj);
        }
    }
}
