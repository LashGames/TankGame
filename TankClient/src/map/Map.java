/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Map {
    
    int mapSize = 10;
    int x,y;
    String map[][] = new String[mapSize][mapSize];
    ArrayList<String> brick = new ArrayList<>();
    ArrayList<String> stone = new ArrayList<>();
    ArrayList<String> water = new ArrayList<>();
    
    
    public void createMap(String message){
        
        String species[] = new String[3];
        species = message.substring(5, message.length()-1).split(":");
        for (int i = 0; i < species[0].split(";").length; i++) {
            brick.add(species[0].split(";")[i]);
        }
        for (int i = 0; i < species[1].split(";").length; i++) {
            stone.add(species[1].split(";")[i]);
        }
        for (int i = 0; i < species[2].split(";").length; i++) {
            water.add(species[2].split(";")[i]);
        }
        
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                map[i][j]="N";
            } 
        }
        
        for (int i = 0; i < brick.size(); i++) {
            x = Integer.parseInt(brick.get(i).split(",")[1]);
            y = Integer.parseInt(brick.get(i).split(",")[0]);
            map[x][y] = "B";
        }
        
        for (int i = 0; i < stone.size(); i++) {
            x = Integer.parseInt(stone.get(i).split(",")[1]);
            y = Integer.parseInt(stone.get(i).split(",")[0]);
            map[x][y] = "S";
        }
        
        for (int i = 0; i < water.size(); i++) {
            x = Integer.parseInt(water.get(i).split(",")[1]);
            y = Integer.parseInt(water.get(i).split(",")[0]);
            map[x][y] = "W";
        }
        
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                System.out.print(map[i][j] +"  ");
            } 
             System.out.println();
         }
        
    }
   
}
