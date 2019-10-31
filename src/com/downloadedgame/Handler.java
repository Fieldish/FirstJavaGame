package com.downloadedgame;

import java.awt.Graphics; 
import java.util.LinkedList;


public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i); 
			tempObject.tick(object);

		}
	} 
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			tempObject.render(g);
			
			
		}
	}
	
	public GameObject addObject(GameObject tempObject){
		object.add(tempObject);
		return tempObject;
		}
	
	public void removeObject(GameObject tempObject)
	{
		this.object.remove(tempObject);
	}
	
	private GameObject tempPlayer=null;
	
	public GameObject findPlayer() {
		for(int i =0;i<object.size();i++) {
			if (object.get(i).getId()==ID.Player) {
				tempPlayer=object.get(i);
				break;
			}
		}
		return tempPlayer;
		}
	
}
