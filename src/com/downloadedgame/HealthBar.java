package com.downloadedgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class HealthBar extends GameObject {
	
private Handler handler;
	private float health;
	private float maxHealth;
	private float x,y;
	float healthChange = 0;
	Camera camera;

	public HealthBar(float x, float y, float health, Handler handler, ID id, Camera camera) {
		super(x, y, health , 0, id);
		this.handler = handler;
		this.health = health;
		this.camera = camera;
		this.x=x;
		this.y=y;
		this.maxHealth=health;
	}
 
	public void tick(LinkedList<GameObject> object) {

		if (Collision.Check(ID.Player, ID.EnemyProjectile, handler))
			healthChange = 5;
		if (Collision.Check(ID.Player, ID.BasicEnemy, handler))
			healthChange = 5;
		
		health -= healthChange; 
		healthChange = 0;
		
		if (health <= 0)
			Game.inGame = false;
		Game.clamp(health, 0, health);

		
		x+= ((camera.getX()-x)+10)*0.7f;
		y+= ((camera.getY()-y)+10)*0.7f;
		
		System.out.println(handler.object.size());
	}

	public void render(Graphics g) {
		Color c = new Color (20,20,20);
		g.setColor(c);
		g.fillRect((int) x-5, (int) y-5, (int) maxHealth+10, 25+10);
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, (int) health, 25);
	}
}
