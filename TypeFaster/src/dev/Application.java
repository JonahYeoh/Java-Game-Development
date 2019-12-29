package dev;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Application extends Canvas implements Runnable{
    private String title;
    private boolean running;
    private Thread thread;
    public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
    private Handler handler;
    private Generator postman;
    public static PAGE page;
    protected static String player;
    public Application(){
        title = "Type Racer";
        handler = new Handler();
        postman = new Generator(handler,this);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));
        Window window = new Window(WIDTH, HEIGHT,title, this);
        page = PAGE.STARTUP;
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
        g.setColor(new Color(51, 204, 204));
        g.fillRect(0,0,WIDTH, HEIGHT);
        handler.render(g);

        g.dispose();
        bs.show();
    }
    public static PAGE getPage(){
        return Application.page;
    }
    public static void setPage(PAGE state){
        Application.page = state;
    }
    public static void setPlayer(String name){
        Application.player = name;
    }
    public static String getPlayer(){
        return Application.player;
    }
    public static void main(String[] args){
        Application app = new Application();
    }
}
