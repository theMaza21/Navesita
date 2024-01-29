/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

/**
 *
 * @author daani
 */
public class Cronometro  
{
    private long delta, lastTimel;
    private long time;
    private boolean running;

    public Cronometro() {
        delta = 0;
        lastTimel=System.currentTimeMillis();  
         running = false;
    }
 public void run(long time)
 {
     running = true;
     this.time = time;
 }
 public void update()
 {
     if (running) 
     {
      delta += System.currentTimeMillis() - lastTimel;
         if (delta >= time)
         {
             running = false;
             delta = 0;
         }
         lastTimel = System.currentTimeMillis();
     }
 }
 public boolean isRunning()
 {
     return running;
 }
}
