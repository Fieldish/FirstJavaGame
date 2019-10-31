package com.downloadedgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;


public class BasicEnemy extends GameObject{

	private int health=1
			;
	private Handler handler;
	private GameObject tempPlayer=null;
	Random random = new Random();
	
	private int attackTimer=0;
	
	int r1=random.nextInt(255);
	int r2=random.nextInt(255);
	int r3=random.nextInt(255);
	
	float rx=random.nextInt(10);
	float ry=random.nextInt(10);

	public BasicEnemy(float x, float y,float width, float height, Handler handler, ID id) {
		super(x, y, width, height, id);
		this.handler = handler;

		velX=rx;
		velY=ry;
	}
		
	public void tick(LinkedList<GameObject> object) {
		
		move();
		tempPlayer=handler.findPlayer();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject o = handler.object.get(i);	
			if (o.id == ID.PlayerProjectile) 
				if (Collision.Collide(this, o))
						health--;
		}
	
		if (health<=0) 
		handler.removeObject(this);

		if(attackTimer == 50) {
			shoot();
			attackTimer=0;
		}
		attackTimer++;
	}
	
	public void render(Graphics g) {
		
		Color color = new Color (r1,r2,r3);
		g.setColor(color);
		g.fillOval((int) x, (int) y, (int) width, (int) height);
	}
	

	public void shoot() {
		
			tempPlayer=handler.findPlayer();
			GameObject tempBullet = handler.addObject(new EnemyProjectile((float) x+width/2,(float)y+height/2,15,15, handler, ID.EnemyProjectile));
			
			float angle = (float) Math.atan2(tempPlayer.y-y, tempPlayer.x-x);
			int bulletVelocity = 20;
			tempBullet.velX = (float) ((bulletVelocity) * Math.cos(angle));
			tempBullet.velY = (float) ((bulletVelocity) * Math.sin(angle));

	}
	private void move() {
		
		float tempX=x;
		float tempY=y;
		float tempVelX=velX;
		float tempVelY=velY;
		
		x+=velX;
		if(Collision.Check(this.id, ID.Block, handler)) {
			x=tempX;
			velX*=-1;
		}
		y+=velY;
		if(Collision.Check(this.id, ID.Block, handler)) {
			y=tempY;
			velY=tempVelY;
			velY*=-1;
		}
		
	}
}

