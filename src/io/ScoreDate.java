/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author daani
 */
public class ScoreDate{
	
	private String date;
	private int score;
	
	public ScoreDate(int score) {
		this.score = score;
		
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.format(today);
		
	}
	
	public ScoreDate () {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
