/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafipx;

import java.awt.image.BufferedImage;
import math.Vector2D;


/**
 *
 * @author daani
 */

public class Animacion {
    private BufferedImage[] frame;
    private int velocity;
    private int index;
    private boolean running;
    private Vector2D position;
    private long time,lastTime;

    public Animacion(BufferedImage[] frame, int velocity, Vector2D position) {
        this.frame = frame;
        this.velocity = velocity;
        this.position = position;
        index = 0;
        running = true;
        time = 0;
        lastTime = System.currentTimeMillis();
    }
    public void update()
    {
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (time > velocity) 
        {
        time = 0;
        index++;
            if (index >= frame.length)
            {
             running = false;
            }
        }
    }
    public boolean isRunnig()
    {
        return running;
    }

    public BufferedImage getFrame() {
        return frame[index];
    }

    public Vector2D getPosition() {
        return position;
    }
    
}
