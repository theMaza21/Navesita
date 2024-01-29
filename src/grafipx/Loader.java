/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafipx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



/**
 *
 * @author daani
 */
public class Loader {
    public static BufferedImage imageLoader(String path) throws IOException
    {
        try 
        {  
         return ImageIO.read(Loader.class.getResource(path));
        }
        catch(IOException e)
        {
        e.printStackTrace();
        }
        return null;
        }
    public static Font LoadFond(String path, int size)
    {
        try {
        return Font.createFont(Font.TRUETYPE_FONT,
                Loader.class.getResourceAsStream(path)).deriveFont
        (Font.PLAIN,size);
        }
    catch (FontFormatException |IOException e) 
    {
        return null;
    }
        
    }
    public static Clip loadSound(String path)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
            return clip;
        }catch (LineUnavailableException | IOException |UnsupportedAudioFileException e)
        {
        e.printStackTrace();
            }
        return null;   
    }
}
