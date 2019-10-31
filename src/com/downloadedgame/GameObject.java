package com.downloadedgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float health;

	protected ID id;
	protected float velX = 0; 
	protected float velY = 0;

	public GameObject(float x, float y, float width, float height, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.height=height;
		this.width=width;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);

	
	public void setHeight(int height)
	{
		this.height = height;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight(int height)
	{
		return height;
	}
	public int getWidth(int width)
	{
		return width;
	}
	public  float getX()
	{
		return x;
	}
	public  float getY()
	{
		return y;
	}
	public void setX(float x)
	{
		this.x = x;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getVelX()
	{
		return velX;
	}
	public float getVelY()
	{
		return velY;
	}
	
	public void setVelX(float velX)
	{
		this.velX = velX;
	}
	
	public void setVelY(float velY)
	{
		this.velY = velY;
	}
	
	public ID getId()
	{
		return id;
	}

	public float getHealth() {
		return health;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	}