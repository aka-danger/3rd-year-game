package Game_Objects;

import java.awt.Color;
import java.awt.Graphics2D;

import Map.Coordinate;
import Map.TileMap;
import Tools.Tool;

//defination of a item: any object in the game which is not a enemy
public  class Item extends GameObject{

	//constants
	
	public static final int BATTERY = 0;
	public static final int DIAMOND = 1;
	public static final int GEM = 2;
	public static final int TABLE = 3;
	public static final int DOOR = 4;

	protected String type;
	protected int itemType ;
	
	public Item(TileMap map) {
		super(map);
		color = Color.YELLOW;

		// setting positions of the items
		if (TileMap.itemPosition.isEmpty()) {
			System.out.println("item position null");
		} else {
			Coordinate coord = TileMap.itemPosition.pop();
			setX((int) coord.getX());
			setY((int) coord.getY());
		}

	}
	
	//-------------------------setters and getters---------------------
	/**
	 * @return item type
	 * */
	public int getItemType() {
		return itemType;
	}

	/**
	 * set item type
	 * */
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	/**
	 * throwing an item away
	 * */
	public void throwItem(){
		if(Tool.randomInt(0, 1) == 0){
			dx = acceleration;
			x = acceleration;
		}else {
			dx = -acceleration;
			x = acceleration;
		}
	}
	
	/**
	 * get referance to tile map object
	 * */
	public TileMap getMap(){return this.map;}

	protected void move(){
		if (right) {
			// accelerating
			dx -= -acceleration;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
			right = false;
		}
		if (left) {
			// accelerating
			dx += -acceleration;
			if (dx > maxSpeed)
				dx = maxSpeed;
			}
		left = false;
		
		if (falling) {
			dy += gravity;
			if (dy > maxFallingSpeed)
				dy = maxFallingSpeed;
		} else
			dy = 0;
	}
	
	//------------------update & draw---------------------------------
	public void update(){
		move();
		
		tileMapCollision();
	}

	public void draw(Graphics2D g){
		int tileX = map.getX();
		int tileY = map.getY();
		
		g.setColor(color);
		g.drawString(toString(), (int)(tileX + x - width/2) -20
				,(int)(tileY + y - height/2));
		g.fillRect(
				(int)(tileX + x - width/2),
				(int)(tileY + y - height/2),
				width, height
				);
	}
	
	//-----------------utility ---------------------------------------
	public String toString(){
		return " [" +type+"] ";
	}
	
}
