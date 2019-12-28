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
    public Application(){
        title = "Type Racer";
        handler = new Handler();
        postman = new Generator(handler,this);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));
        Window window = new Window(WIDTH, HEIGHT,title, this);
        page = PAGE.HOME;
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
        if(page == PAGE.PLAY){
            g.setColor(new Color(0, 255, 204));
            g.fillRect(0,0,WIDTH,HEIGHT);
            handler.render(g);
        }
        else if( page == PAGE.HOME){
            handler.removeAllObject();
            //drawHome(g);
            //drawStartUp(g);
            //drawRegistration(g);
            drawLogIn(g);
        }
        g.dispose();
        bs.show();
    }
    public static PAGE getPage(){
        return page;
    }
    public static void setState(PAGE state){
        Application.page = state;
    }

    protected void drawHome(Graphics g){
        int x = 350; int y = 250;
        Font fnt = new Font("Arial",1,50);
        g.setColor(new Color(172, 230, 213));
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,300,100);
        g.setFont(fnt);
        g.drawString("STAT", x+75, y+70);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+150,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+150,300,100);
        g.drawString("PLAY", x+85, y+220);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+300,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+300,300,100);
        g.drawString("QUIT", x+85, y+370);
    }
    public void drawStartUp(Graphics g){
        int x = 350; int y = 250;
        Font fnt = new Font("Arial",1,50);
        g.setColor(new Color(172, 230, 213));
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,300,100);
        g.setFont(fnt);
        g.drawString("LOGIN", x+75, y+70);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+150,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+150,300,100);
        g.drawString("REGISTER", x+23, y+220);
        g.setColor(new Color(172, 223, 230));
        g.fillRect(x,y+300,300,100);
        g.setColor(Color.BLACK);
        g.drawRect(x,y+300,300,100);
        g.drawString("QUIT", x+85, y+370);
    }
    public void drawRegistration(Graphics g){
        int x = 300; int y = 250;
        Font fnt = new Font("Arial",1,50);
        g.setFont(fnt);
        g.setColor(new Color(172, 230, 213));
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(Color.BLUE);
        g.drawString("REGISTRATION", x+10, y-100);
        g.setColor(Color.BLACK);
        g.drawString("NAME : ", x, y);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y - 50, 200, 60);

        g.setColor(Color.BLACK);
        g.drawString("PWD   : ", x, y+100);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y + 50, 200, 60);
        g.setColor(Color.CYAN);
        g.fillRect(x+50,y+200,300,80);
        g.setColor(Color.BLACK);
        g.drawString("CONFIRM",x+80,y+260);
    }
    public void drawLogIn(Graphics g){
        int x = 300; int y = 250;
        Font fnt = new Font("Arial",1,50);
        g.setFont(fnt);
        g.setColor(new Color(172, 230, 213));
        g.fillRect(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(Color.BLUE);
        g.drawString("LOG-IN", x+100, y-100);
        g.setColor(Color.BLACK);
        g.drawString("NAME : ", x, y);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y - 50, 200, 60);

        g.setColor(Color.BLACK);
        g.drawString("PWD   : ", x, y+100);
        g.setColor(Color.WHITE);
        g.fillRect(x+200, y + 50, 200, 60);
        g.setColor(Color.CYAN);
        g.fillRect(x+50,y+200,300,80);
        g.setColor(Color.BLACK);
        g.drawString("CONFIRM",x+80,y+260);
    }
    public static void main(String[] args){
        Application app = new Application();
    }
}
