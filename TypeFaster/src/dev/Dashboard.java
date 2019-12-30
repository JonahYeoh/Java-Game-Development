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
    private static boolean FLAG = false;
    public Dashboard(int x, int y, TAG tag) throws IOException {
        super(x, y, tag);
        CPM = new ArrayList<Integer>();
        SCORE = new ArrayList<Double>();
        ERROR = new ArrayList<Integer>();
        avgCPM = 0;
        avgSCORE = 0;
        avgERROR = 0;
        data = readData();
        slicer();
        Dashboard.FLAG = true;
    }

    @Override
    public void tick() throws IOException {
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
        g.drawString("CPM Count : " + CPM.size(), 100, 50);
        g.drawString("CPM : " + avgCPM, 100, 100);
        g.drawString("SCORE : " + avgSCORE, 100, 150);
        g.drawString("ERROR : " + avgERROR, 100, 200);
        g.drawLine(100, 250, 100, 500);
        g.drawLine(100,500, 900, 500);
        // CPM
        g.setColor(Color.darkGray);
        int xx = 100;
        int previousY = 500- CPM.get(0)/10;
        int currentY;
        for(int i = 1; i < CPM.size(); i++){
            currentY = 500 - CPM.get(i).intValue()/10;
            g.drawLine(xx, previousY, xx+(int)((double)800/CPM.size()),currentY );
            previousY = currentY;
            xx+=(int)((double)800/CPM.size());
        }
        //
        g.setColor(Color.YELLOW);
        xx = 100;
        previousY = 500- ERROR.get(0)*10;
        for(int i = 1; i < ERROR.size(); i++){
            currentY = 500 - ERROR.get(i).intValue()*10;
            g.drawLine(xx, previousY, xx+(int)((double)800/ERROR.size()),currentY );
            previousY = currentY;
            xx+=(int)((double)800/ERROR.size());
        }
        // SCORE
        g.setColor(Color.RED);
        xx = 100;
        previousY = 500- SCORE.get(0).intValue();
        for(int i = 1; i < SCORE.size(); i++){
            currentY = 500- SCORE.get(i).intValue();
            g.drawLine(xx, previousY, xx+(int)((double)800/SCORE.size()),currentY );
            previousY = currentY;
            xx+=(int)((double)800/SCORE.size());
        }
    }
    public static boolean getFLAG(){
        return Dashboard.FLAG;
    }
    public static void clearFLAG(){
        Dashboard.FLAG = false;
    }
    private String readData() throws IOException {
        FileReader fr = new FileReader("DB/data_"+ Application.getPlayer() + ".txt");
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
