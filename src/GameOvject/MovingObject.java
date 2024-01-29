/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import grafipx.Assests;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import state.Constants;
import state.GameState;

/**
 *
 * @author daani
 */
public abstract class MovingObject extends GameObject
{
    protected Vector2D veleocity;
    protected  AffineTransform at;
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState gameState;
    private Sound explosion;
    protected  boolean Dead;

    public MovingObject( Vector2D posicion, Vector2D veleocity,double maxVel,BufferedImage textur,GameState gameState) {
        super(textur, posicion);
        this.veleocity = veleocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = textur.getWidth();
        height = textur.getHeight();
        angle = 0;
        explosion = new Sound(Assests.explocion);
        Dead = false;

    }
    protected  void collidesWith()
    {
         ArrayList<MovingObject> movingObjects = gameState.getMovingObject();
         for (int i = 0; i < movingObjects.size(); i++) 
         {
             MovingObject m = movingObjects.get(i);
             if (m.equals(this)) 
             continue;
             double distance = m.getCenter().subtract(getCenter()).getMagnitude();
             if (distance < m.width/2 + width/2 && movingObjects.contains(this) && !m.Dead && !Dead)
             {
                 objectCollision(m, this);
             }
            
        }
    }
    private void  objectCollision(MovingObject a, MovingObject b)
    {
        if (a instanceof Player && ((Player)a).isSpawning()) 
        {
            return;
        }
          if (b instanceof Player && ((Player)b).isSpawning()) 
        {
            return;
        }
        if (!(a instanceof Meteor && b instanceof Meteor ))
        {
            gameState.PlayerExplosion(getCenter());
            a.Destroy();
            b.Destroy();
        }
    }
    protected void Destroy()
    {
        Dead = true;
        if (!(this instanceof Laser)) {
            explosion.Play();
            
        }
    }
    
    protected Vector2D getCenter()
    {
        return new Vector2D(posicion.getX() + width/2, posicion.getY() + height/2);
    }
public boolean isDead() {return Dead;}
  
}