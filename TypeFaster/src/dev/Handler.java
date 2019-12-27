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
}
