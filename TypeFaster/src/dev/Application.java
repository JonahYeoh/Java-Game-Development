package dev;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Application extends Canvas implements Runnable{
    private String title;
    private boolean running;
    private Thread thread;
    public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
    private Handler handler;
    private Generator postman;
    public static STATE state;
    public Application(){
        title = "Type Racer";
        handler = new Handler();
        postman = new Generator(handler,this);
        this.addKeyListener(new KeyInput(handler));
        Window window = new Window(WIDTH, HEIGHT,title, this);
        state = STATE.MENU;
        //state = STATE.PLAY;
    }
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while ( running ){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while ( delta >= 1){
                try {
                    tick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                delta--;
            }
            if( running )
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000 )
            {
                timer += 1000;
                //System.out.println("FPS : " + frames);
                frames = 0;
            }
        }
        Stop();
    }
    public void Start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public void Stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void tick() throws IOException {
        postman.tick();
        handler.tick();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // Rendering start here
        if(state == STATE.PLAY){
            g.setColor(new Color(0, 255, 204));
            g.fillRect(0,0,WIDTH,HEIGHT);
            handler.render(g);
        }
        else{
            handler.removeAllObject();
            int x = 350; int y = 300;
            Font fnt = new Font("Arial",1,50);
            g.setColor(new Color(172, 230, 213));
            g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
            g.setColor(new Color(172, 223, 230));
            g.fillRect(x,y,300,100);
            g.setColor(Color.BLACK);
            g.drawRect(x,y,300,100);
            g.setFont(fnt);
            g.drawString("MENU", x+75, y+70);
            g.setColor(new Color(172, 223, 230));
            g.fillRect(x,y+200,300,100);
            g.setColor(Color.BLACK);
            g.drawRect(x,y+200,300,100);
            g.drawString("PLAY", x+85, y+270);
        }
        g.dispose();
        bs.show();
    }
    public static STATE getState(){
        return state;
    }
    public static void setState(STATE state){
        Application.state = state;
    }
    public static void main(String[] args){
        Application app = new Application();
    }
}
