package dev;

import java.awt.*;

public class TextArea extends AppObject {
    private Handler handler;
    private String content;
    private char leftString[];
    private char currentChar;
    private char rightString[];
    private int currentIndex;
    private boolean endOfLine;
    public TextArea(int x, int y, TAG tag, String content) {
        super(x, y, tag);
        this.content = content;
        currentIndex = 0;
        endOfLine = false;
    }

    public void trimmer(){
        if( currentIndex != content.length()){
            leftString = content.substring(0,currentIndex).toCharArray();
            currentChar = content.charAt(currentIndex);
            rightString = content.substring(currentIndex+1,content.length()).toCharArray();
        }
        else{
            leftString = content.substring(0,currentIndex).toCharArray();
            currentChar = content.charAt(currentIndex-1);
        }
    }
    public char getCurrentChar(){
        return currentChar;
    }
    public int getCurrentIndex(){
        return currentIndex;
    }
    public void nextChar(){
        currentIndex++;
        //currentIndex = 0;
    }
    public boolean hasNext(){
        return (currentIndex < content.length());
    }

    @Override
    public void tick() {
        /*
        if(hasNext())
            nextChar();
         */
    }

    @Override
    public void render(Graphics g) {
        trimmer();
        g.setColor(Color.WHITE);
        g.fillRect(x,y,875,415);
        Font fnt = new Font("Helvetica",1,20);
        g.setFont(fnt);
            if(currentIndex == 0){
                g.setColor(Color.RED);
                g.drawString(new String(new char[]{currentChar}),125, 370);
                g.fillRect(125,373,15,3);
                g.setColor(Color.BLACK);
                g.drawString(new String(rightString),125, 400);
            }
            else if( currentIndex == content.length()){
                g.setColor(Color.BLACK);
                g.drawString(new String(leftString), 125, 340);
                g.setColor(Color.RED);
                g.drawString(new String(new char[]{currentChar}),125, 370);
                g.fillRect(125,373,15,3);
            }
            else{
                g.setColor(Color.BLACK);
                g.drawString(new String(leftString), 125, 340);
                g.setColor(Color.RED);
                g.drawString(new String(new char[]{currentChar}),125, 370);
                g.fillRect(125,373,15,3);
                g.setColor(Color.BLACK);
                g.drawString(new String(rightString),125, 400);
            }
    }
    public boolean endOfLine(){
        return ( currentIndex == content.length() );
    }
}
