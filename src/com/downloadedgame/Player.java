	package com.downloadedgame;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Player extends GameObject{

	private float _acc=1.5f;
	private float _dcc=0.1f;
	private float maxVel=8;
	private KeyInput input;
	public static int width = 64;
	public static int height = 64;
	public static float PLAYER_VEL=10;
	private Handler handler;
	public static Color color = Color.white;
	public static boolean boundsShowing = false;
	
	public Player(float x, float y, float width, float height, Handler handler, ID id, KeyInput input) {
		super(x, y, width, height, id);
		this.handler = handler;
		this.input=input;
	}

	public void tick(LinkedList<GameObject> object) {
		move();
		
		if(KeyInput.keyDown[0]) velY-=_acc;
		else if(KeyInput.keyDown[1])  velY+=_acc;
		else if(!KeyInput.keyDown[0] && KeyInput.keyDown[1]) {
			if (velY>0) velY-=_dcc;
			else if(velY<0) velY+=_dcc;
		}
		
		if(KeyInput.keyDown[2]) velX-=_acc;
		else if(KeyInput.keyDown[3]) velX+=_acc;
		else if(!KeyInput.keyDown[2] && KeyInput.keyDown[3]) {
			if (velX>0) velX-=_dcc;
			else if(velX<0) velX+=_dcc;
		}
		velX=Game.clamp(velX, -maxVel, maxVel);
		velY=Game.clamp(velY, -maxVel, maxVel);
		
	}
	private void move() {
		
		float tempX=x;
		float tempY=y;
		
		x+=velX;
		if(Collision.Check(this.id, ID.Block, handler))
			x=tempX;
		
		y+=velY;
		if(Collision.Check(this.id, ID.Block, handler))
			y=tempY;
		
	}

	public void render(Graphics g) {
		
	
		g.setColor(color);
		g.fillOval((int) x, (int) y, (int) width, (int) height);
		
	}

}