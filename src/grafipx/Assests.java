/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafipx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.sound.sampled.Clip;

/**
 *
 * @author daani
 */
public class Assests 
{
public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 460;
        public static BufferedImage Player;
    public static BufferedImage speed;
 
    public static BufferedImage blueLaser, greenLaser,redLaser;
    public static BufferedImage[] bigs = new BufferedImage[4];
     public static BufferedImage[] meds = new BufferedImage[2];
      public static BufferedImage[] smalls = new BufferedImage[2];
       public static BufferedImage[] tinies = new BufferedImage[2];
       public static BufferedImage[] exp =new BufferedImage[8];
       public static BufferedImage[] num = new BufferedImage[11];
       public static BufferedImage life;
       public static Font fontBig;
       public static Font fontMed;
      public static BufferedImage ufo;
      public static Clip  backgrrooundMusic, explocion, PalayerLoser, PlayerShoot, ufoshoot;
      public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;
    public static void init() throws IOException
    {
        
        greyBtn = Loader.imageLoader("/Botones/1.png");
		blueBtn = Loader.imageLoader("/Botones/blue_button.png");
        
        Player = Loader.imageLoader("/ships/1.png");
        speed = Loader.imageLoader("/effects/fire08.png");
        blueLaser = Loader.imageLoader("/laser/laser1.png");
        greenLaser = Loader.imageLoader("/laser/laser2.png");
        redLaser = Loader.imageLoader("/laser/laser3.png");
        life = Loader.imageLoader("/vida/playerLife1_blue.png");
        ufo = Loader.imageLoader("/ships/2.png");
        fontBig = Loader.LoadFond("/fonts/kenvector_future.ttf", 42);
        fontBig = Loader.LoadFond("/fonts/kenvector_future.ttf", 20);
        
       for (int i = 0; i < bigs.length; i++)
    bigs[i] = Loader.imageLoader("/meteors/big1.png");
      for (int i = 0; i < meds.length; i++)
    meds[i] = Loader.imageLoader("/meteors/med1.png");
    for (int i = 0; i < smalls.length; i++)
    smalls[i] = Loader.imageLoader("/meteors/small1.png");
     for (int i = 0; i < tinies.length; i++)
    tinies[i] = Loader.imageLoader("/meteors/tiny1.png");
      for (int i = 0; i < exp.length; i++) 
    exp[i] = Loader.imageLoader("/explosion/"+i+".png");
        for (int i = 0; i < num.length; i++) 
       num[i] = Loader.imageLoader("/vista/"+i+".png");
        backgrrooundMusic = Loader.loadSound("/sounds/backgroundMusic.wav");
        explocion = Loader.loadSound("/sounds/explosion.wav");
        PlayerShoot = Loader.loadSound("/sounds/playerShoot.wav");
        PalayerLoser = Loader.loadSound("/sounds/playerLoose.wav");
        ufoshoot = Loader.loadSound("/sounds/ufoShoot.wav");
        
        //---------------------------------------------------------------------------
        
     loaded = true;

    }
    public static BufferedImage loadImage(String path) throws IOException {
		count ++;
		return Loader.imageLoader(path);
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.LoadFond(path, size);
	}
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
}
