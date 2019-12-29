package dev;

import java.awt.*;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;

public class Dashboard extends AppObject {

    protected ArrayList<Integer> CPM;
    protected ArrayList<Double> SCORE;
    protected ArrayList<Integer> ERROR;
    protected double avgCPM, avgSCORE, avgERROR;
    protected String data;
    public Dashboard(int x, int y, TAG tag) throws IOException {
        super(x, y, tag);
        CPM = new ArrayList<Integer>();
        SCORE = new ArrayList<Double>();
        ERROR = new ArrayList<Integer>();
        data = readData();
        slicer();
    }

    @Override
    public void tick() throws IOException {
        avgCPM = 0;
        avgSCORE = 0;
        avgERROR = 0;
        for( int i = 0; i < CPM.size(); i++){
            avgCPM += CPM.get(i);
            avgSCORE += SCORE.get(i);
            avgERROR += ERROR.get(i);
        }
        avgCPM = avgCPM/CPM.size();
        avgSCORE = avgSCORE/SCORE.size();
        avgERROR = avgERROR/ERROR.size();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("CPM : " + avgCPM, 100, 100);
        g.drawString("SCORE : " + avgSCORE, 100, 300);
        g.drawString("ERROR : " + avgERROR, 100, 500);
    }

    private String readData() throws IOException {
        FileReader fr = new FileReader("DB/record.txt");
        char buffer[] = new char[256];
        int num = 0;
        String str = "";
        while(true) {
            try {
                num = fr.read(buffer);
                System.out.println(num);
                if( num == -1 ){
                    break;
                }
                str += new String(buffer, 0, num);
            } catch (EOFException ex) {
                ex.printStackTrace();
            }
        }
        fr.close();
        return str;
    }
    private void slicer() throws IOException {
        String trimmed[] = data.split("&");
        double dd = 0;
        for( int i = 0; i < trimmed.length; i+=3 ){
            //System.out.println("index " + i);
            SCORE.add(Double.parseDouble(trimmed[i]));
            CPM.add(Integer.parseInt(trimmed[i+1]));
            ERROR.add(Integer.parseInt(trimmed[i+2]));
        }
    }
}
