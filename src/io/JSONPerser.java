/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

/**
 *
 * @author daani
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import state.Constants;
public class JSONPerser {
    public static ArrayList<ScoreDate> readFile() throws FileNotFoundException{
		ArrayList<ScoreDate> dataList = new ArrayList<ScoreDate>();
		
		File file = new File(Constants.SCORE_PATH);
		
		if(!file.exists() || file.length() == 0) {
			return dataList;
		}
		
		JSONTokener parser = new JSONTokener(new FileInputStream(file));
		JSONArray jsonList = new JSONArray(parser);
		
		for(int i = 0; i < jsonList.length(); i++) {
			JSONObject obj = (JSONObject)jsonList.get(i);
			ScoreDate data = new ScoreDate();
			data.setScore(obj.getInt("score"));
			data.setDate(obj.getString("date"));
			dataList.add(data);
		}
		
		return dataList;
	}
	
	public static void writeFile(ArrayList<ScoreDate> dataList) throws IOException {
		
		File outputFile = new File(Constants.SCORE_PATH);
		
		outputFile.getParentFile().mkdirs();
		outputFile.createNewFile();
		
		JSONArray jsonList = new JSONArray();
		
		for(ScoreDate data: dataList) {
			
			JSONObject obj = new JSONObject();
			obj.put("score", data.getScore());
			obj.put("date", data.getDate());
			
			jsonList.put(obj);
			
		}
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile.toURI()));
		jsonList.write(writer);
		writer.close();
		
	}
	
}