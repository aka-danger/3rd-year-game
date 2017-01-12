package Enemies;

import java.awt.Color;

import Game_Objects.Enemy;
import Map.TileMap;

public class Bird extends Enemy{

	public Bird(TileMap map, int type) {
		super(map, type);
		acceleration = 1;
		color = Color.CYAN;
		gravity = 0;
		setRight(true);
	}
	//----------------------utility----------------------
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
