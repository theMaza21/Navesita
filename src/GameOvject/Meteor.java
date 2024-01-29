/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import grafipx.Assests;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import state.Constants;
import state.GameState;

/**
 *
 * @author daani
 */
public class Meteor extends MovingObject{
    
    private Size size;
    public Meteor(Vector2D posicion, Vector2D veleocity, double maxVel, BufferedImage textur, GameState gameState, Size size) {
        super(posicion, veleocity, maxVel, textur, gameState);
        this.size = size;
        this.veleocity = veleocity.scale(maxVel);
    }
   @Override
    public void update() {
      posicion = posicion.add(veleocity);
      
            if (posicion.getX() > Constants.Windth) 
              posicion.setX(-width);


           if (posicion.getY() > Constants.Height) 
               posicion.setY(-height);


           if (posicion.getX() < -width) 
               posicion.setX(Constants.Windth);


           if (posicion.getY() < -height) 
               posicion.setY(Constants.Height);
           angle += Constants.DELTAANGLE/2;
           
    }
    @Override
    public void Destroy()
    {
        gameState.dividirMeteoro(this);
        gameState.addScore(Constants.METEOR_SCORE,posicion);
        super.Destroy();
    }

    @Override
    public void drwa(Graphics g)
    {
     Graphics2D g2d = (Graphics2D)g;
     at = AffineTransform.getTranslateInstance(posicion.getX(),posicion.getY());
     at.rotate(angle,width/2,height/2);
     g2d.drawImage(textur,at, null);
    }

    public Size getSize() {
        return size;
    }
    
}
