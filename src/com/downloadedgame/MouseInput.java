package com.downloadedgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private GameObject tempPlayer=null;
	private Camera camera;
//	public MouseInput(Handler handler) {
//		this.handler=handler;
//	}
	public MouseInput(Handler handler, Camera camera) {
		this.camera=camera;
		this.handler=handler;
	}
	public void mousePressed(MouseEvent e) {
	
		 
		float mx=e.getX()+camera.getX();
		float my=e.getY()+camera.getY();
		tempPlayer=handler.findPlayer();
		
			GameObject tempBullet =handler.addObject(new PlayerProjectile((float) tempPlayer.x+Player.width/2,(float)tempPlayer.y+Player.height/2,10,10, handler, ID.PlayerProjectile));
			
			float angle = (float) Math.atan2(my - tempPlayer.y-Player.width/2, mx - tempPlayer.x-Player.height/2);
			int bulletVelocity = 15;
			tempBullet.velX = (float) ((bulletVelocity) * Math.cos(angle));
			tempBullet.velY = (float) ((bulletVelocity) * Math.sin(angle));
	}
	}
