package GameStateManger;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class PausedState extends GameState {
	
	private int currentChoice =0;
	
	private String message = "Paused";
	
	public String getMessage(){return message;}
	public void setMessage(String string){this.message = string;}
	
	
	//get referance to this object
	public PausedState getState(){
		return this;
	}

	public PausedState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void update() {
		
	}

	@Override
	protected void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		//g.setBackground(Color.WHITE);
		g.setColor(Color.RED);
		g.drawString(message, GamePanel.WIDTH/2,GamePanel.HEIGHT/2);
		g.drawString("Press Enter", GamePanel.WIDTH/2,GamePanel.HEIGHT/4);
	}
	
	public void select(){
		if(currentChoice == 0){
			//resume
			gsm.setPaused(false);
		
		}else if(currentChoice == 1){
			//restart
			gsm.setState(GameStateManager.GAME);
		
		}else if(currentChoice == 2 ){
			//menu
			gsm.setState(GameStateManager.MENU);
		}else{
			//quit
			System.exit(QUIT_GAME);
		}
	}

	@Override
	protected void keyPressed(int key) {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void keyRelesed(int key) {
		
	}

}
