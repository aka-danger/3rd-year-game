package Game_Objects;

import Map.Coordinate;
import Map.TileMap;
import Tools.Tool;

public abstract class Enemy extends GameObject {

	protected int health = 100;
	protected boolean dead = false;
	protected int type;
	
	public Enemy(TileMap map, int type) {
		super(map);
		this.type = type;
		switch (type) {
		case TileMap.BIRD:
			if(TileMap.birdPosition.isEmpty()) Tool.print("empty bird position");
			else {
				Coordinate coord = TileMap.birdPosition.pop();
				setX((int) coord.getX());
				setY((int)coord.getY());
			}
			break;
		case TileMap.ELEVATOR:
			if(TileMap.elevatorPosition.isEmpty()) Tool.print("empty elev position");
			else {
				Coordinate coord = TileMap.elevatorPosition.pop();
				setX((int) coord.getX());
				setY((int)coord.getY());
			}
			break;
		case TileMap.HOPPER:
			if(TileMap.hopperPosition.isEmpty()) Tool.print("empty hopper position");
			else {
				Coordinate coord = TileMap.hopperPosition.pop();
				setX((int) coord.getX());
				setY((int)coord.getY());
			}
			break;
		case TileMap.SLUG:
			if(TileMap.slugPosition.isEmpty()) Tool.print("empty slug position");
			else {
				Coordinate coord = TileMap.slugPosition.pop();
				setX((int) coord.getX());
				setY((int)coord.getY());
			}
			break;
		default:
			Tool.print("error enemy type is wrong");
			break;
		}
		
		//setting positions of the items
		/*if(TileMap.enemyPosition.isEmpty()){
			System.out.println("enemy posiiton null");
		}else {
			Coordinate coord = TileMap.enemyPosition.pop();
			setX((int)coord.getX());
			setY((int)coord.getY());
		}*/
	}
	
	//----------------------utility----------------------
	/**
	 * Every time a enemey gets hit by a bullet this method is called
	 * to decrease its health until it dies
	 * */
	public void hit(int damage){
		this.health -= damage;
		if(this.health <= 0) dead = true;
	}
	
}
