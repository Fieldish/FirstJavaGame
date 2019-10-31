package com.downloadedgame;

import java.awt.image.BufferedImage;

public class Camera {
private float x,y;
private static int levelW=64, levelH=64;

public Camera(float x,float y) {
	this.x=x;
	this.y=y;
}

public void tick(GameObject object) {
	 
 	
	x+= ((object.getX()-x)-Game.WIDTH/2+Player.width/2)*0.2f;
	y+= ((object.getY()-y)-Game.HEIGHT/2+Player.height/2)*0.2f;
	
	
	if(x<=0)x=0;
	if(x>=levelW*32-Game.WIDTH) x=levelW*32-Game.WIDTH;
	if(y<=0)y=0;
	if(y>=levelH*32-Game.HEIGHT) y=levelH*32-Game.HEIGHT;
}
 
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

}
