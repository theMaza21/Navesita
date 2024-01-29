/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import grafipx.Assests;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class Ufo extends MovingObject 
{
    private ArrayList<Vector2D> path;
    private Vector2D currentNode;
    private int index;
    private boolean following;
    private Cronometro fireReat;
    private Sound shoot;
    public Ufo(Vector2D posicion, Vector2D veleocity, double maxVel, BufferedImage textur,
            ArrayList<Vector2D> path,
            GameState gameState) {
        super(posicion, veleocity, maxVel, textur, gameState);
        this.path = path;
        index = 0;
        following = true;
        fireReat = new Cronometro();
        fireReat.run(Constants.UFO_FIRE_RATE);
        shoot = new Sound(Assests.ufoshoot);
    }
    private Vector2D pathFollowing()
    {
        currentNode = path.get(index);
        double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
        if (distanceToNode < Constants.NODE_RADIUS) 
        {
         index ++;
            if (index >= path.size())
            {
             following = false;   
            }
        }
        return  seekForce(currentNode);
    }
    public Vector2D seekForce(Vector2D target)
    {
        Vector2D desiredVelocity = target.subtract(getCenter());
        desiredVelocity = desiredVelocity.normalize().scale(maxVel);
       return desiredVelocity.subtract(veleocity);
    }
       @Override
    public void update() 
    {
     Vector2D phatFollowing;
        if (following)
         phatFollowing = pathFollowing();
        else 
         phatFollowing = new Vector2D();
         
        phatFollowing = phatFollowing.scale(1/Constants.UFO_MASS);
        veleocity = veleocity.add(phatFollowing);
        veleocity = veleocity.limit(maxVel);
        posicion = posicion.add(veleocity);
        if (posicion.getX() > Constants.Windth || posicion.getY() > Constants.Height
                || posicion.getX() < 0 || posicion.getY() < 0)
      Destroy();
        
        if (!fireReat.isRunning())
        {
         Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());
         toPlayer = toPlayer.normalize();
         double  currentAngle = toPlayer.getAngle();
         currentAngle +=  Math.random()*Constants.UFO_ANGLE_RANGE - Constants.UFO_ANGLE_RANGE / 2;
         
         if(toPlayer.getX() < 0)
             currentAngle = -currentAngle + Math.PI;
         
                 toPlayer = toPlayer.setDirection(currentAngle);
         Laser laser = new Laser(
                 getCenter().add(toPlayer.scale(width)),
                 toPlayer,
                 Constants.LASER_VEL,
                 currentAngle + Math.PI/2,
                 Assests.redLaser,
                 gameState
         );
         gameState.getMovingObject().add(0,laser);
         fireReat.run(Constants.UFO_FIRE_RATE);
         shoot.Play();
         if (shoot.getFramePosition() > 8500)
            {
             shoot.stop();
            }
         
         
        }
        
        
        angle += 0.5;
        collidesWith();
        fireReat.update();
        
        
    }
    
       @Override
	public void Destroy() {
		gameState.addScore(Constants.UFO_SCORE,posicion);
		super.Destroy();
	}

    @Override
    public void drwa(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        at = AffineTransform.getTranslateInstance(posicion.getX(),posicion.getY());
        at.rotate(angle, width/2 , height/2);
        g2d.drawImage(textur,at,null);
        
     
    }
}
