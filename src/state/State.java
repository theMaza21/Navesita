/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.awt.Graphics;

/**
 *
 * @author daani
 */
public abstract class State
{
    private static State currenState = null;
    public static State getCurrState() 
    {
        return currenState;
    }
    public static void changeState(State newState)
    {
       currenState =newState;
    }
    public abstract void update();
    public abstract void draw(Graphics g);
}
