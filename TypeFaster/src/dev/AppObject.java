package dev;

public abstract class AppObject implements CoreOperation{
    protected int x, y;
    protected TAG tag;
    public AppObject(int x, int y, TAG tag){
        this.x = x;
        this.y = y;
        this.tag = tag;
    }
    public TAG getTag(){
        return tag;
    }

}
