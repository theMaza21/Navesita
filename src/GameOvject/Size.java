/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import grafipx.Assests;
import java.awt.image.BufferedImage;

/**
 *
 * @author daani
 */
public enum Size
{
    BIG(2,Assests.meds), MED(2,Assests.smalls),SMALL(2,Assests.tinies),TINY(0,null);
    public int quantity;
    public BufferedImage[] textures;

    private Size(int quantity, BufferedImage[] textures) {
        this.quantity = quantity;
        this.textures = textures;
    }
    
}
