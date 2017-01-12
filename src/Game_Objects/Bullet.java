package Game_Objects;

import java.awt.Color;

import Map.TileMap;

public class Bullet extends GameObject{

	private boolean hit; //hit an enemy?
	private boolean remove; //must be remove from the list
	private int damage = 20;
	
	public Bullet(TileMap map ,boolean right) {
		super(map);
		
		this.right = right;
		acceleration = 10;
		color =Color.gray;
		if(right) dx=acceleration;
		else dx  = -acceleration;
		width=5;
		height = 5;
	}
	//----------------------utility----------------------
	/**
	 * Get damage points for this type of bullet
	 * */
	public int getDamage(){return damage;}
	
	/**
	 * @return true if bullet must be removed and false if it must not be removed
	 * */
	public boolean shouldRemove(){return remove;}
	//--------------------update--------------------------
	public void update(){
		tileMapCollision();
		if(dx == 0 || hit) remove = true;
	}
	
	
	

}
