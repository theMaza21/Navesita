/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import UI.Action;
import UI.Button;
import grafipx.Assests;
import grafipx.Text;
import io.JSONPerser;
import io.ScoreDate;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import math.Vector2D;

/**
 *
 * @author daani
 */
public class ScoreState extends State{

	
	private Button returnButton;
	
	private PriorityQueue<ScoreDate> highScores;
	
	private Comparator<ScoreDate> scoreComparator;
	
	private ScoreDate[] auxArray;
	
	public ScoreState() throws FileNotFoundException {
		returnButton = new Button(
				Assests.greyBtn,
				Assests.blueBtn,
				Assests.greyBtn.getHeight(),
				Constants.Height - Assests.greyBtn.getHeight() * 2,
				Constants.RETURN,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new MenuState());
					}
				}
			);
		
		scoreComparator = new Comparator<ScoreDate>() 
                {

			@Override
			public int compare(ScoreDate e1, ScoreDate e2) {
				return e1.getScore() < e2.getScore() ? 
                                        -1: e1.getScore() > e2.getScore() ?
                                        1: 0;
			}
		};
		
		highScores = new PriorityQueue<ScoreDate>(10, scoreComparator);
                
                try 
                {
                 ArrayList<ScoreDate> dataList = JSONPerser.readFile();
                 for(ScoreDate d: dataList) {
				highScores.add(d);
			}
			
			while(highScores.size() > 10) {
				highScores.poll();
			}
                } 
                catch (FileNotFoundException e) 
            {
                e.printStackTrace();
            }
              
                
		
	}
	
	@Override
	public void update() {
		returnButton.update();
	}

	@Override
	public void draw(Graphics g) {
		returnButton.draw(g);
		
		auxArray = highScores.toArray(new ScoreDate[highScores.size()]);
		
		Arrays.sort(auxArray, scoreComparator);
		
		
		Vector2D scorePos = new Vector2D(
				Constants.Windth / 2 - 200,
				100
				);
		Vector2D datePos = new Vector2D(
				Constants.Windth / 2 + 200,
				100
				);
		
		Text.drawText(g, Constants.SCORE, scorePos, true, Color.BLUE, Assests.fontBig);
		Text.drawText(g, Constants.DATE, datePos, true, Color.BLUE, Assests.fontBig);
		
		scorePos.setY(scorePos.getY() + 40);
		datePos.setY(datePos.getY() + 40);
		
		for(int i = auxArray.length - 1; i > -1; i--) {
			
			ScoreDate d = auxArray[i];
			
			Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assests.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assests.fontMed);
			
			scorePos.setY(scorePos.getY() + 40);
			datePos.setY(datePos.getY() + 40);
			
		}
		
	}
	
}
