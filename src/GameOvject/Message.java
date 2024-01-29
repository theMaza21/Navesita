/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import grafipx.Text;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import math.Vector2D;
import state.GameState;

/**
 *
 * @author daani
 */
public class Message 
{
   
    private float alpha;
    private String text;
    private Vector2D position;
    private Color color;
    private boolean cente;
    private boolean fade;
    private Font font;
    private final float deltaAlpha = 0.01f;
    private boolean dead;

    public Message(Vector2D position, boolean fade, String text, Color color, boolean cente, Font font) {
        this.font = font;
        
        this.text = text;
        this.position = position;
         this.fade = fade;
        this.color = color;
        this.cente = cente;
        this.dead = false;
        if (fade)
        
        alpha = 1;   
        else
        alpha = 0;
           
    }
      public void draw(Graphics2D g2d)
       {
           g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
           Text.drawText(g2d,text,position,cente,color,font);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
            position.setY(position.getY()- 1);
           if (fade) 
           alpha -= deltaAlpha;
           else 
           alpha += deltaAlpha;    
           if (fade && alpha < 0 )
           {
               dead = true;
              
           }
           if (!fade && alpha > 1) 
           {
               fade = true;
               alpha = 1;
                       
           }
         
       }
        public boolean isDead() {return dead;}
      
}
    


