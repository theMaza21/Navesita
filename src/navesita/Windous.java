/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navesita;

import Input.KeyBoard;
import Input.Mouse;
import grafipx.Assests;
import state.GameState;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import state.Constants;
import state.LoadingState;
import state.MenuState;
import state.State;

/**
 *
 * @author daani
 */
public class Windous extends JFrame implements Runnable{
private Canvas canvas;
private Thread thrad;
private boolean runed =false;
private BufferStrategy bs;
private Graphics g;
private final int FPS = 60;
private double TargetTime = 1000000000/FPS;
private double delta = 0;
private int AvereageFPS =FPS;
private KeyBoard keyBoart;
private Mouse mouse;

    public Windous() {
        setTitle("Space Ship Game");
        setSize(Constants.Windth,Constants.Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        canvas = new Canvas();
        keyBoart = new KeyBoard();
        mouse = new Mouse();
        
        canvas.setPreferredSize(new Dimension(Constants.Windth, Constants.Height));
        canvas.setMaximumSize(new Dimension(Constants.Windth, Constants.Height));
        canvas.setMinimumSize(new Dimension(Constants.Windth, Constants.Height));
        canvas.setFocusable(true);
        
        add(canvas);        
        canvas.addKeyListener(keyBoart);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        setVisible(true);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Windous().start();
    }
   
  
    
    private void update()
    {
        keyBoart.update();
     State.getCurrState().update();
    }
    private void draw()
    {
        bs =  canvas.getBufferStrategy();
        if (bs == null) 
        {
         canvas.createBufferStrategy(3);
         return;
        }
        g = bs.getDrawGraphics();
        //--------------------------
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.Windth, Constants.Height);
       
       State.getCurrState().draw(g);
        g.drawString(""+AvereageFPS,10,10);
      
        
        //--------------------------
        g.dispose();
        bs.show();
        
    }
    private void init() 
    {
       Thread loadingThread = new Thread(new Runnable() {

			@Override
			public void run() {
                            try {
                                Assests.init();
                            } catch (IOException ex) {
                                Logger.getLogger(Windous.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
       
            State.changeState(new LoadingState(loadingThread));
    } 
    

    @Override
    public void run() {
        long now = 0;
        long lastTime =  System.nanoTime();
        int frames = 0;
        long time = 0;
        init();
        
        while(runed)
        {
         now = System.nanoTime();
         delta += (now - lastTime)/TargetTime;
         lastTime = now;
            if (delta >= 1) 
            {
                update();
                draw();
                delta --;
            }
            if (time >= 1000000000) 
            {
                AvereageFPS = frames;
                frames = 0;
                time = 0;
            }
        }
     stop();
    }
    private void  start()
    {
        thrad = new Thread(this);
        thrad.start();
        runed = true;
    }
    private void stop()
    {
        try {
            thrad.join();
            runed= false;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
}
