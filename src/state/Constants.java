/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author daani
 */
public class Constants {
    
    public static final int Windth = 1000;
    public static final int Height =  800;
   
    public static final int FIRERATE = 300;
    public static final double DELTAANGLE = 0.1;
    public static final double ACC = 0.2;
    public static final double PLAYER_MAX_VEL= 7.0;
    public static final long FLICKER_TIME = 200;
    public static final long SPAWNING_TIME = 3000;
    
    
    public static final double LASER_VEL = 15.0;
    
    public static final double METEOR_VEL = 2.0;
    
    public static final double NODE_RADIUS = 160;
    public static final double UFO_MASS = 3;
    public static final double UFO_MAX_VEL = 3; 
    public static long UFO_FIRE_RATE = 1000;
    public static double UFO_ANGLE_RANGE = Math.PI/2;
    public static final int UFO_SCORE = 40;
    public static final int METEOR_SCORE = 20;
    public static final String PLAY = "PLAY";
	public static final String EXIT = "EXIT";
        public static final String RETURN = "RETURN";
        public static final String HIGH_SCORES = "HIGHEST SCORES";
        public static final String SCORE = "SCORE";
	public static final String DATE = "DATE";
        public static final int LOADING_BAR_WIDTH = 500;
	public static final int LOADING_BAR_HEIGHT = 50;
        public static final long GAME_OVER_TIME = 3000;
        public static final long UFO_SPAWN_RATE = 10000;
        public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
			"\\NAVESITA\\data.json"; // data.xml if you use XMLParser
        
        public static final String PLAYER = "PLAYER";
	public static final String PLAYERS = "PLAYERS";
}
