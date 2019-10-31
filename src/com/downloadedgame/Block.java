package com.downloadedgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Block extends GameObject {
	private static Color color = Color.BLACK;
	
	public Block(float x, float y, float width, float height, ID id) {
		super(x, y, width, height, id);
	}

	public void tick(LinkedList<GameObject> object) {
		
	}
 
	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}
}
