package GameStateManger;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameStateManager {

	//suplements for another type
	private ArrayList<GameState> gameState;
	
	public static final int NUM_STATES =2;
	public static final int MENU = 0;
	public static final int GAME = 1;
	public static final int TEST = 2;
	public static final int PAUSED = 3;
	
	
	public static PausedState pausedState;
	public static boolean paused;
	
	private int currentState = MENU;
	
	public GameStateManager(){
		gameState = new ArrayList<GameState>();
		paused = false;
		pausedState = new PausedState(this);
		gameState.add(new MenuState(this));
		gameState.add(new LevelOne(this));
		gameState.add(new Level(this));		
	}
	
	
	//-----------------setters and getters-----------------------
	/**
	 * set state 
	 * */
	public void setState(int state){
		currentState = state;
		gameState.get(currentState).init();
	}
	
	/**
	 * @return true if paused
	 * */
	public boolean isPaused() {
		return paused;
	}
	/**
	 * setpaused
	 * */
	public void setPaused(boolean p) {
		paused = p;
	}
	//-----------------update and draw---------------------------
	public void update(){
		if(isPaused()){
			pausedState.update();
		}else 
			gameState.get(currentState).update();
	}
	
	public void draw(Graphics2D g){
		if(isPaused()){
			pausedState.draw(g);
		}else 
			gameState.get(currentState).draw(g);
	}
	
	//-------------------events for gameState----------
	public void keyPressed(int key){
		if(key == KeyEvent.VK_ESCAPE) paused = true;
		if(paused && key == KeyEvent.VK_ENTER  ){
			paused = false;
		}
		gameState.get(currentState).keyPressed(key);
	}
	
	public void keyRelesed(int key){
		gameState.get(currentState).keyRelesed(key);
	}

}
