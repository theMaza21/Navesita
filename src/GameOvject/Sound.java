/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author daani
 */
public class Sound {
    private Clip clip;
    private FloatControl volume;
    public Sound(Clip clip)
    {
        volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                
        this.clip= clip;
    }
    public void Play()
    {
        clip.setFramePosition(0);
      clip.start();
    }
    public void loop()
    {
          clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    public void stop()
    {
     clip.stop();
    }
    public int getFramePosition()
    {
        return clip.getFramePosition();
    }
    public void changeVolume(Float value)
    {
        volume.setValue(value);
    }
}
