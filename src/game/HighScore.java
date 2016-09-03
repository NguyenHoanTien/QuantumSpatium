/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author s3500286
 */
public class HighScore 
{
    private final String path = "res/highscore.txt";
    private int[] hs = new int[11];// highscore
    public HighScore()
    {
        loadScore();
    }              
    
    public void loadScore(){
        String file = loadFileAsString(path);    
        String[] tokens = file.split("\\s+");
        for(int i = 0; i < hs.length - 1; i++){
             hs[i] = parseInt(tokens[i]);
        }       
    }
    
    public void setScore(int newScore) throws IOException{        
        loadScore();
        hs[hs.length - 1] = newScore;
        Arrays.sort(hs);               
        
        FileWriter fw = new FileWriter("res/highscore.txt");
 
	for (int i = 1; i < hs.length; i++) {
            fw.write(Integer.toString(hs[i]) + "\n");
	}
	fw.close();
        
     
    }
    
    public int[] getHighScore(){
        return hs;
    }
    
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                builder.append(line + "\n");
            }
            br.close();
        }
        catch(IOException e){
            System.out.print(e);
        }
        
        return builder.toString();
    }
    
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e){
            System.out.println(e);
            return 0;
        }
    }
}
