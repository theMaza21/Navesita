/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.spec.KeySpec;

/**
 *
 * @author daani
 */
public class KeyBoard implements KeyListener
{
    private boolean[] keys = new boolean[256];
    public static boolean w,a,d,s,space;
    // w= Up, a = left, s = down, d = right

    public KeyBoard() 
    {
        w= false;
        a = false;
        s = false;
        d = false;
    }
    public void  update()
    {
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
        s = keys[KeyEvent.VK_S];
        space = keys[KeyEvent.VK_SPACE];
    }
     @Override
    public void keyPressed(KeyEvent e) 
    {
      keys[e.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
         keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

   
    
    
}
