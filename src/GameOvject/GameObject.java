/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOvject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author daani
 */
public abstract class GameObject 
{
    protected BufferedImage textur; 
    protected Vector2D posicion;

    public GameObject(BufferedImage textur, Vector2D posicion) {
        this.textur = textur;
        this.posicion = posicion;
    }
   public abstract void update();
   
   public abstract  void drwa(Graphics g);

    public BufferedImage getTextur() {
        return textur;
    }

    public void setTextur(BufferedImage textur) {
        this.textur = textur;
    }

    public Vector2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2D posicion) {
        this.posicion = posicion;
    }
    
}
