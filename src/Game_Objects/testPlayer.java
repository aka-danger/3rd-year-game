package Game_Objects;

import Main.GamePanel;
import Map.TileMap;

public class testPlayer extends GameObject {
	
	// class not used
	
	public testPlayer(TileMap map){
		super(map);
	}
	
	//---------------Update & draw----------------------
	public void update(){
		playerMovement();
		tileMapCollision();
		
		map.setX((int)-x + GamePanel.WIDTH/2 );
		map.setY((int)-y + GamePanel.HEIGHT/2);
	}
}
