package dev;

import java.awt.*;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
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

        slicer();
        Dashboard.FLAG = true;
    }

    @Override
    public void tick() throws IOException {
        if( avgCPM == 0 ) {
            for (int i = 0; i < CPM.size(); i++) {
                avgCPM += CPM.get(i);
                avgSCORE += SCORE.get(i);
                avgERROR += ERROR.get(i);
            }
            avgCPM = avgCPM / CPM.size();
            avgSCORE = avgSCORE / SCORE.size();
            avgERROR = avgERROR / ERROR.size();
        }
    }

    @Override
    public void render(Graphics g) {
        Font fnt = new Font("TimesRoman",2,50);
        g.setFont(fnt);
        g.setColor(Color.BLUE);
        g.drawString("DASHBOARD OF " + Application.getPlayer(),240,50);
        if( CPM.size() != 0 ){
            g.setColor(Color.BLACK);
            int xA = 100, yA = 400, ht = 300, wt = 800;
            //g.drawString("CPM Count : " + CPM.size(), 100, 100);
            //g.drawString("CPM : " + avgCPM, 100, 100);
            //g.drawString("SCORE : " + avgSCORE, 100, 150);
            //g.drawString("ERROR : " + avgERROR, 100, 200);
            g.drawLine(xA, 100, xA, yA);
            g.drawLine(xA,yA, xA+wt, yA);
            // CPM
            g.setColor(Color.darkGray);
            int xx = 100;
            int previousY = yA- CPM.get(0)/10;
            int currentY;
            for(int i = 1; i < CPM.size(); i++){
                currentY = yA - CPM.get(i).intValue()/10;
                g.drawLine(xx, previousY, xx+(int)((double)800/CPM.size()),currentY );
                previousY = currentY;
                xx+=(int)((double)800/CPM.size());
            }
            g.fillRect(100, 450, 100, 40);
            Font fnt2 = new Font("TimesRoman",2,20);
            g.setFont(fnt2);
            g.drawString("Character Per Minute", 250, 480);
            //
            g.setColor(Color.YELLOW);
            xx = 100;
            previousY = yA- ERROR.get(0)*10;
            for(int i = 1; i < ERROR.size(); i++){
                currentY = yA - ERROR.get(i).intValue()*10;
                g.drawLine(xx, previousY, xx+(int)((double)800/ERROR.size()),currentY );
                previousY = currentY;
                xx+=(int)((double)800/ERROR.size());
            }
            g.fillRect(100, 530, 100, 40);
            g.drawString("Error", 250, 560);

            // SCORE
            g.setColor(Color.RED);
            xx = 100;
            previousY = yA- SCORE.get(0).intValue();
            for(int i = 1; i < SCORE.size(); i++){
                currentY = yA- SCORE.get(i).intValue();
                g.drawLine(xx, previousY, xx+(int)((double)800/SCORE.size()),currentY );
                previousY = currentY;
                xx+=(int)((double)800/SCORE.size());
            }


            g.fillRect(100, 610, 100, 40);
            g.drawString("Score", 250, 640);
            // Average Table
            g.setColor(Color.BLACK);
            fnt2 = new Font("Arial", 2, 20);
            g.drawRect(500,450, 400, 200);
            g.drawString("ALL TIME AVERAGE", 600, 480);
            g.drawString("Character Per Minute : ", 540, 520);
            DecimalFormat numberFormat = new DecimalFormat("#.000");
            g.drawString(" "+numberFormat.format(avgCPM), 750, 520);
            g.drawString("Score : ", 675, 560);
            g.drawString(" "+numberFormat.format(avgSCORE), 750, 560);
            g.drawString("Error : ", 680, 600);
            g.drawString(" "+numberFormat.format(avgERROR), 750, 600);
            g.drawString("Total Practice : ", 600, 640);
            g.drawString(" "+CPM.size(), 755, 640);
        }
        else{
            g.setColor(Color.BLACK);
            g.setFont(fnt);
            g.drawString("No record found", 300,300);
        }

    }
    public static boolean getFLAG(){
        return Dashboard.FLAG;
    }
    public static void clearFLAG(){
        Dashboard.FLAG = false;
    }
    private String readData() throws IOException {
        FileReader fr = null;
        String str = null;
        try{
            fr = new FileReader("DB/data_"+ Application.getPlayer() + ".txt");
            char buffer[] = new char[256];
            int num = 0;
            str = "";
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
                    break;
                }
            }
            fr.close();
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }


        return str;
    }
    private void slicer() throws IOException {
        data = readData();
        //if( data != null){
            String trimmed[] = data.split("&");
            double dd = 0;
            for( int i = 0; i < trimmed.length; i+=3 ){
                //System.out.println("index " + i);
                SCORE.add(Double.parseDouble(trimmed[i]));
                CPM.add(Integer.parseInt(trimmed[i+1]));
                ERROR.add(Integer.parseInt(trimmed[i+2]));
            }
        //}
    }
}
