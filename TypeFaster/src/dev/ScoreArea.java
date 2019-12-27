package dev;

import java.awt.*;
import java.util.*;

public class ScoreArea extends AppObject {
    private int error, characterPerMinute;
    double score;
    private long startSec, endSec;
    protected Deque<Integer> CPM;
    protected Deque<Double> SCORE;
    protected Deque<Integer> ERROR;
    public ScoreArea(int x, int y, TAG tag) {
        super(x, y, tag);
        CPM = new LinkedList<>();
        SCORE = new LinkedList<>();
        ERROR = new LinkedList<>();
        score = 0;
        error = 0;
        characterPerMinute = 0;
        startSec = 0;
        endSec = 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Font fnt = new Font("Helvetica",1,35);
        g.setFont(fnt);

        g.setColor(new Color(102,255,204));
        g.fillRect(x,y,875,83);

        g.setColor(new Color(51,51,204));
        g.drawString("Score : "+ score , x+50, y+50);
        g.drawString("CPM : " + characterPerMinute,x+400,y+50);
        g.drawString( "Error : " + error, x + 600, y + 50);
    }
    public void setScore(int score){
        this.score = score;
    }
    public double getScore(){
        return score;
    }
    public void setError(int error){
        this.error = error;
    }
    public int getError(){
        return error;
    }
    public void reset(){
        //setScore(0);
        setError(0);
        setStartSec(0);
        setEndSec(0);
    }
    public void setStartSec(long sec){
        this.startSec = sec;
    }
    public long getStartSec(){
        return startSec;
    }
    public void setEndSec(long sec){
        this.endSec = sec;
    }
    public long getEndSec(){
        return endSec;
    }
    public void setSpeed(int length){
        long diff = endSec - startSec;
        System.out.println(endSec + " " + startSec);
        System.out.println("DIFF : " + diff);
        double speed = length/(double)diff;
        speed *= 60;
        characterPerMinute = (int)speed;
        score = (error == 0)?100:100-((double)error/(double)length);
        SCORE.add(score);
        ERROR.add(error);
        CPM.add(characterPerMinute);
        reset();
    }
    public int getCPM(){
        return CPM.getLast();
    }
    public double getSCORE(){
        return SCORE.getLast();
    }
    public int getERROR(){
        return ERROR.getLast();
    }
}
