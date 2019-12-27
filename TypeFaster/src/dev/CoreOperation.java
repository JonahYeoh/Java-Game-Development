package dev;

import java.awt.*;
import java.io.IOException;

public interface CoreOperation {
    void tick() throws IOException;
    void render(Graphics g);
}
