/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import GameOvject.Cronometro;
import GameOvject.Message;
import GameOvject.Meteor;
import GameOvject.MovingObject;
import GameOvject.Player;
import GameOvject.Size;
import GameOvject.Sound;
import GameOvject.Ufo;
import grafipx.Animacion;
import java.awt.Graphics;
import grafipx.Assests;
import grafipx.Text;
import io.JSONPerser;
import io.ScoreDate;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import math.Vector2D;

/**
 *
 * @author daani
 */
public class GameState  extends State {
  public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constants.Windth/2 - Assests.Player.getWidth()/2,
	Constants.Height/2 - Assests.Player.getHeight()/2);
    private Player player;
     private ArrayList<MovingObject> movingObject = new ArrayList<MovingObject>();
     private int meteors;
     private ArrayList<Animacion> explosion = new ArrayList<Animacion>();
     private ArrayList<Message> message = new ArrayList<Message>();
     private int score = 0;
     private int lives = 3;
     private int waves = 1;
     private Sound backgroundMusic;
     private Cronometro gameOverTimer;
     private boolean gameOver;
     private Cronometro ufoSpawner;
     
    public GameState() 
    {
           try {
            Assests.init();
            if (Assests.Player != null) {
                player = new Player(new Vector2D(100, 500),new Vector2D(), 6,Assests.Player,this);
                gameOverTimer = new Cronometro();
                gameOver = false;
                movingObject.add(player); 
            } else {
                System.err.println("Error: Assests.Player es nulo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de la excepción (puedes mostrar un mensaje, log, etc.)
        }
           meteors = 1;
           startWave();
           backgroundMusic = new Sound(Assests.backgrrooundMusic);
           backgroundMusic.loop();
           backgroundMusic.changeVolume(-10.0f);
           
           ufoSpawner = new Cronometro();
           ufoSpawner.run(Constants.UFO_SPAWN_RATE);
                   
    }
    public void addScore(int value, Vector2D position)
    {
        score += value;
        message.add(new Message(position, true, "+"+value+"score", Color.WHITE
                , false, Assests.fontMed));  
                }
    public void dividirMeteoro(Meteor meteor)
    {
     Size size = meteor.getSize();
     BufferedImage[] textures = size.textures;
     Size newSize = null;
     switch(size)
     {
         case BIG:
             newSize = Size.MED;
             break;
          case MED:
             newSize = Size.SMALL;
             break;
              case SMALL:
             newSize = Size.TINY;
             break;
             default:
                 return;
     }
        for (int i = 0; i < size.quantity; i++)
        {
          movingObject.add(new Meteor(meteor.getPosicion(),
                    new Vector2D(0,1).setDirection(Math.random() * Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+ 1,
                    textures [(int)(Math.random()*textures.length)],
                    this, newSize));   
        }
             
    }
    
    private void startWave()
    {
        message.add(new Message(new Vector2D(Constants.Windth/2, Constants.Height/2),true,
        "Wave"+waves,Color.WHITE,true,Assests.fontBig
        ));
        double x,y;
        for (int i = 0; i < meteors; i++) 
        {
         x = i % 2 == 0 ? Math.random()*Constants.Windth:0;
         y = i % 2 == 0 ? 0 : Math.random()*Constants.Height;
            BufferedImage texture = Assests.bigs[(int)(Math.random()*Assests.bigs.length)];
            movingObject.add(new Meteor(new Vector2D(x,y),
                    new Vector2D(0,1).setDirection(Math.random() * Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+ 1,
                    texture,
                    this, Size.BIG));
        }
        meteors++;
         spawnUFO();
        
    }
    public void PlayerExplosion(Vector2D position)
    {
        explosion.add(new Animacion(
                Assests.exp,
                50,
                position.subtract(new Vector2D(Assests.exp[0].getWidth()/2,Assests.exp[0].getHeight()/2))
        ));
    }
    private void spawnUFO()
    {
        int rand = (int)(Math.random()*2);
        double  x = rand == 0 ? (Math.random()*Constants.Windth): 0;
        double y = rand == 0 ? 0 : (Math.random()*Constants.Height);
        
        ArrayList<Vector2D> path = new ArrayList<Vector2D>();
        double posX, posY;
        posX = Math.random()* Constants.Windth/2;
        posY = Math.random() * Constants.Height/2;
        path.add(new Vector2D(posX,posY));
        
        posX = Math.random()*(Constants.Windth/2)+ Constants.Windth/2;
        posY = Math.random()* (Constants.Height/2);
        path.add(new Vector2D(posX,posY));
        
        posX = Math.random()* Constants.Windth/2;
        posY = Math.random() * (Constants.Height/2) + Constants.Height/2;
        path.add(new Vector2D(posX,posY));
         
        posX = Math.random()* (Constants.Windth/2)+ Constants.Windth/2;
        posY = Math.random()* (Constants.Height/2)+ Constants.Height/2;
        path.add(new Vector2D(posX,posY));
        movingObject.add(new Ufo(new Vector2D(x,y),
                new Vector2D(),
                Constants.UFO_MASS,
                Assests.ufo,
                path,
                this));
        
                
    }
   public void update()
   {
       for (int i = 0; i < movingObject.size(); i++){
           MovingObject mo = movingObject.get(i);
           mo.update();
			if(mo.isDead()) {
				movingObject.remove(i);
				i--;
			}
              
       }
       	for(int i = 0; i < explosion.size(); i++){
			Animacion anim = explosion.get(i);
			anim.update();
			if(!anim.isRunnig()){
				explosion.remove(i);
			}
			
		}
       
   
         if(gameOver && !gameOverTimer.isRunning()) 
         {
             try 
             {
                 ArrayList<ScoreDate> dataList = JSONPerser.readFile();
                 dataList.add(new ScoreDate(score));
                 JSONPerser.writeFile(dataList);
                 
             }
             catch (IOException e) 
             {
                 e.printStackTrace();
             }         
			State.changeState(new MenuState());
		}
		
		
		    if (!ufoSpawner.isRunning()) 
                    {
                         ufoSpawner.run(Constants.UFO_SPAWN_RATE);
                        spawnUFO();
                         }

        gameOverTimer.update();
        ufoSpawner.update();
           
       for (int i = 0; i < movingObject.size(); i++){
           if(movingObject.get(i) instanceof Meteor)
           {
               return;
           }
       }
         startWave();
   }
   

   public void draw(Graphics g )
   {
       Graphics2D g2d = (Graphics2D)g;
       g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for (int i = 0; i < message.size(); i++){
       
           message.get(i).draw(g2d);
        
     if(message.get(i).isDead())
         message.remove(i);
        }
       
       for (int i = 0; i < movingObject.size(); i++)
       {
           movingObject.get(i).drwa(g);
       }
       for (int i = 0; i < explosion.size(); i++)
       {
        Animacion anim = explosion.get(i);
        g2d.drawImage(anim.getFrame(),(int)anim.getPosition().getX(),(int)anim.getPosition().getY(),null);
       drawScore(g);
       drawLives(g);
           Text.drawText(g, "Wave"+ waves, new Vector2D(Constants.Windth/ 2, Constants.Height/2),
                   true,
                   Color.WHITE, Assests.fontBig);
       }
   }
   public void drawScore(Graphics g)
   {
     	Vector2D pos = new Vector2D(850, 25);
		
		String scoreToString = Integer.toString(score);
		
		for(int i = 0; i < scoreToString.length(); i++) {
			
			g.drawImage(Assests.num[Integer.parseInt(scoreToString.substring(i, i + 1))],
					(int)pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX() + 20);
			
		}
		
	}
  private void drawLives(Graphics g){
      if(lives < 1)
          return;
		
		Vector2D livePosition = new Vector2D(25, 25);
		
		g.drawImage(Assests.life, (int)livePosition.getX(), (int)livePosition.getY(), null);
		
		g.drawImage(Assests.num[10], (int)livePosition.getX() + 40,
				(int)livePosition.getY() + 5, null);
		
		String livesToString = Integer.toString(lives);
		
		Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());
		
		for(int i = 0; i < livesToString.length(); i ++)
		{
			int number = Integer.parseInt(livesToString.substring(i, i+1));
			
			if(number <= 0)
				break;
			g.drawImage(Assests.num[number],
					(int)pos.getX() + 60, (int)pos.getY() + 5, null);
			pos.setX(pos.getX() + 20);
		}
		
	}

    public ArrayList<MovingObject> getMovingObject() {
        return movingObject;
    }
    public ArrayList<Message> getMessages()
    {
        return message;
            }

    public Player getPlayer()
    {
        return player;
    }
    public  boolean subtractLife()
    {
        lives --;
        return  lives > 0;
    }
    public void gameOver()
    {
        Message gamOverMag = new Message
        (
                PLAYER_START_POSITION,
                true,
                "Game Over",
                Color.WHITE,
                true,
                Assests.fontBig
                
        );
        this.message.add(gamOverMag);
        gameOverTimer.run(Constants.GAME_OVER_TIME);
        gameOver = true;
    }
    
    
}
