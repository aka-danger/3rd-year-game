package Enemies;

import java.awt.Color;

import Game_Objects.Enemy;
import Map.TileMap;

public class Elevator  extends Enemy{
	
	public Elevator(TileMap map,int type) {
		super(map, type);
		acceleration = 1;
		color = Color.GRAY;
		gravity = 0;
		setUp(true);
		
	}
	//----------------------utility----------------------
	private void move() {
		if (up) {
			// accelerating
			dy -= -acceleration;
			if (dy < -maxSpeed)
				dy = -maxSpeed;
		}
		if (down) {
			// accelerating
			dy += -acceleration;
			if (dy > maxSpeed)
				dy = maxSpeed;
		}
	}

	private void changeDirection() {
		if (up && dy == 0) {
			up = false;
			down = true;
		} else if (down && dy == 0) {
			up = true;
			down = false;
		}
	}
	//--------------------update--------------------------
	public void update() {

		move();
		tileMapCollision();
		changeDirection();
	}
}
