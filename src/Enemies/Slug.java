package Enemies;

import java.awt.Color;

import Game_Objects.Enemy;
import Map.TileMap;

public class Slug extends Enemy {

	public Slug(TileMap map, int type) {
		super(map, type);
		acceleration = 1;
		color = Color.BLUE;
		setRight(true);
	}
	
	private void move(){
		if (right) {
			// accelerating
			dx -= -acceleration;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		}
		if (left) {
			// accelerating
			dx += -acceleration;
			if (dx > maxSpeed)
				dx = maxSpeed;
			}
		if (falling) {
			dy += gravity;
			if (dy > maxFallingSpeed)
				dy = maxFallingSpeed;
		} else
			dy = 0;
	}
	
	private void changeDirection(){
		if(right && dx == 0) {
			right = false;
			left = true;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
		}
	}
	//--------------------update--------------------------
	public void update(){
		move();
		tileMapCollision();
		changeDirection();
	}
	
	
}
