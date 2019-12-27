package dev;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public  Window(int width, int height, String title, Application app){
        JFrame frame = new JFrame(title);
        Dimension dim = new Dimension(width, height);
        frame.setPreferredSize(dim);
        frame.setMinimumSize(dim);
        frame.setMaximumSize(dim);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(app);
        frame.setVisible(true);
        app.Start();
    }
}
