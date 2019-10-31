package com.downloadedgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class EnemyProjectile extends GameObject{
	
	private Handler handler;
	
	public EnemyProjectile(float x, float y, float width, float height, Handler handler, ID id) {
		super(x, y, width, height, id);
		this.handler = handler;
	}
	public void tick(LinkedList<GameObject> object) {
		
		x+=velX;
		y+=velY;
		
		if(x<0 || x > 64*32 || y<0|| y>64*32) 
			handler.removeObject(this);
		
		if(Collision.Check(this.id, ID.Block, handler))
			handler.removeObject(this);
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
		
	}

}
