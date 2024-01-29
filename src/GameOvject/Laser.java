/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;


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
public class Laser extends MovingObject {
    
    public Laser(Vector2D posicion, Vector2D veleocity, double maxVel,double angle, BufferedImage textur, GameState gameState) {
        super(posicion, veleocity, maxVel, textur,gameState);
        this.angle = angle;
        this.veleocity =veleocity.scale(maxVel);
    }
  @Override  
  public void update()
  {
      posicion = posicion.add(veleocity);
      if (posicion.getX() < 0 || 
              posicion.getX() > Constants.Windth 
              || posicion.getY()< 0
              || posicion.getY() > Constants.Height)
      {
       Destroy();
      }
      collidesWith();
  }
  
      @Override
    public void drwa(Graphics g)
    {
       Graphics2D g2d =(Graphics2D)g;
       at = AffineTransform.getTranslateInstance(posicion.getX() - width/ 2,posicion.getY());
       at.rotate(angle);
       g2d.drawImage(textur,at,null);
    }
    
    @Override
    public Vector2D getCenter()
    {
        return new Vector2D(posicion.getX() + width/2, posicion.getY() + width/2);
    }
}
