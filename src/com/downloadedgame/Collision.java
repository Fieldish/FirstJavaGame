package com.downloadedgame;


public class Collision{


	public static boolean Collide(GameObject object1, GameObject object2) {

		double x1 = object1.x + object1.width / 2;
		double y1 = object1.y + object1.height / 2;
		double x2 = object2.x + object2.width / 2;
		double y2 = object2.y + object2.height / 2;
		
		double distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
		
		float radius1 = (object1.width + object1.height) / 4;
		float radius2 = (object2.width + object2.height) / 4;
		
		if (distance < (radius1 + radius2))
			return true;
		else
			return false;
	}
	public static boolean Check(ID Id1, ID Id2, Handler handler) {
		boolean check = false;
			for(int i = 0; i < handler.object.size(); i++){
				GameObject object1 = handler.object.get(i);	
				
				if (object1.id == Id1) {
					for (int j = 0; j < handler.object.size(); j++) {
						GameObject object2 = handler.object.get(j);
						if (object2.id == Id2) {
								if (Collide(object1,object2))
									check=true;
								
							}
						}
				}
			}
			return check;
	}
}
