/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclienttank;

/**
 *
 * @author hp pc
 */
public class Player {
    private String name;
    private int x;
    private int y;
    private int direction;
    private int damage;

    public Player(){
        
    }
    public Player(String name, int x, int y,int direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    
}
