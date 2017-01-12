package Enemies;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import Game_Objects.Enemy;
import Map.TileMap;

public class Hopper extends Enemy {	
	
	
	//------timer--------------------
	private Timer timer;
	private TimerTask task;
	private int count = 0;
	private void begin(){
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				count++;
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	private void end(){
		timer.cancel();
	}
	private void initCount(){
		count = 0;
	}
	private int count(){return count;}
	//--------end timer---------------
	
	
	public Hopper(TileMap map, int type) {
		super(map, type);
		acceleration = 0.5;
		color = Color.GREEN;
		setLeft(true);
		jumpSpeed = -22;
		falling = true;
		begin();
	
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
				dx = maxSpeed;}
		if (falling) {
			dy += gravity;
			if (dy > maxFallingSpeed)
				dy = maxFallingSpeed;
			jumping = false;
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
	
	private void jump(){
		jumping = true;
		if (jumping) {
			dy = jumpSpeed; // instant change in velocity
			falling = true;
		}
	}
	//--------------------update--------------------------
	public void update(){
		
		if(count () >=1){
			jump();
			initCount();
		}
		move();
		tileMapCollision();
		changeDirection();
	}

}
