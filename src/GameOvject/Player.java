/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import Input.KeyBoard;
import grafipx.Assests;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import navesita.Windous;
import state.Constants;
import state.GameState;


public class Player extends MovingObject
{
    private Vector2D heading;
     private Vector2D acceleracion;
     private final double ACC = 0.1;
     private final double DELTAANGEL = 0.1;
     private boolean accelerating = false;
     private Cronometro fireRate;
	private boolean spawning, visible;
	private Sound shoot;
        private Sound loose;
	private Cronometro spawnTime, flickerTime;
    

//    public Player(BufferedImage textur, Vector2D veleocity,double maxVel, Vector2D posicion, GameState gameState) {
//        super(textur, veleocity, maxVel, posicion);
     public Player(Vector2D posicion,Vector2D veleocity,double maxVel,BufferedImage textur,GameState gameState)
     {
         super(posicion,veleocity,maxVel,textur,gameState); 
        heading = new Vector2D(0, 1);
        acceleracion = new Vector2D();
        fireRate = new Cronometro();
        spawnTime = new Cronometro();  // Agrega esta línea
        flickerTime = new Cronometro();
        shoot = new Sound(Assests.PlayerShoot);
       loose = new Sound(Assests.PalayerLoser);
    }

  @Override
	public void update() 
	{
	if(!spawnTime.isRunning())
        {
            spawning = false;
            visible = true;
            if (spawning) 
            {
                if (!flickerTime.isRunning())
                {
                 flickerTime.run(Constants.FLICKER_TIME);
                 visible =!visible;
                }
   
            }
        }
        
        
        if (KeyBoard.space && !fireRate.isRunning() && !spawning ) 
        {
         gameState.getMovingObject().add( 0,new Laser(
        getCenter().add(heading.scale(width)),
                 heading,
                 Constants.LASER_VEL,
                 angle,
                 Assests.redLaser,
                 gameState
         ));
          fireRate.run(Constants.FIRERATE);
         shoot.Play();
        }
            if (shoot.getFramePosition() > 8500)
            {
             shoot.stop();
            }
        if (KeyBoard.d)
            angle += DELTAANGEL;
        if (KeyBoard.a)
            angle -= DELTAANGEL;
        if (KeyBoard.w)
        {
         acceleracion =  heading.scale(Constants.ACC);
         accelerating = true;
        }
        else
        {
            if (veleocity.getMagnitude() != 0 )
            {
             acceleracion = (veleocity.scale(-1).normalize().scale(ACC/2)); 
             accelerating = false; 
            }
        }
        veleocity = veleocity.add(acceleracion);
        veleocity = veleocity.limit(maxVel);
        veleocity.limit(maxVel);
        heading = heading.setDirection(angle - Math.PI / 2);
        posicion = posicion.add(veleocity);
        
            if (posicion.getX() > Constants.Windth) 
              posicion.setX(0);


           if (posicion.getY() > Constants.Height) 
               posicion.setY(0);


           if (posicion.getX() < 0) 
               posicion.setX(Constants.Windth);


           if (posicion.getY() < 0) 
               posicion.setY(Constants.Height);

         fireRate.update();
         spawnTime.update();
         flickerTime.update();
        collidesWith();
       
    }
  @Override
    public void Destroy()
{
    spawning = true;
    spawnTime.run(Constants.SPAWNING_TIME);
    loose.Play();
    if (!gameState.subtractLife())
    {
        gameState.gameOver();
        super.Destroy();
    }
    ressetValues();
}
    
    private void  ressetValues()
    {
        angle = 0;
        veleocity = new Vector2D();
        posicion = new Vector2D(Constants.Windth/2 - Assests.Player.getWidth()/2,
        Constants.Height/2 - Assests.Player.getHeight()/2
        );
      
    }
    @Override
    public void drwa(Graphics g) 
    {
        if (!visible) 
            return;
        
        Graphics2D g2d = (Graphics2D) g;
    AffineTransform at1 = new AffineTransform();
at1.translate(posicion.getX() + width / 2 + 5, posicion.getY() + height / 2 + 10);
at1.rotate(angle, -5, -10);

AffineTransform at2 = new AffineTransform();
at2.translate(posicion.getX() + 5, posicion.getY() + height / 2 + 10);
at2.rotate(angle, width/2 -5, -10);

        if (accelerating) 
        {
               g2d.drawImage(Assests.speed, at2,null);
               g2d.drawImage(Assests.speed, at1,null);
        }
       
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angle, width/2,height/2);

        g2d.drawImage(textur, at, null);
    }
    public boolean isSpawning()
    {
        return spawning;
    }
    
}

