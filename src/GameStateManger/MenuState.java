package GameStateManger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import Map.Background;


public class MenuState extends GameState {

	private Background bg;
	
	private int currentChoice =0;
	
	private String[] options ={
			"Start", 
			"Quit"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		
		try{
			bg = new Background("data/menu.gif", 0);
			bg.setVector(0, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		bg.update();
	}

	@Override
	protected void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		
		//draw options
		Font font= new Font("Franklin Gothic Medium",Font.BOLD, 28);
		g.setBackground(Color.WHITE);
		g.setFont(font);
		for(int i = 0 ; i < options.length; i++){
			if(currentChoice == i){
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.YELLOW);
			}
			g.drawString(options[i], GamePanel.WIDTH/2,GamePanel.HEIGHT/2 + i * 15);
		}
		Font f = new Font("Times New Romen",Font.BOLD,18);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("Collect items and find the door", GamePanel.WIDTH/2 - 125,GamePanel.HEIGHT/2+200);
	}

	public void select(){
		if(currentChoice == 0){
			//start
			gsm.setState(GameStateManager.TEST);
		}else{
			//quit
			System.exit(QUIT_GAME);
		}
	}
	
	@Override
	protected void keyPressed(int key) {
		// TODO Auto-generated method stub
		if(key == KeyEvent.VK_ENTER){
			select();
		}
		if(key == KeyEvent.VK_UP){
			currentChoice --;
			if(currentChoice == -1) currentChoice = options.length -1;
		}
		if(key == KeyEvent.VK_DOWN){
			currentChoice ++;
			if(currentChoice == options.length) currentChoice = 0;
		}
		
	}

	@Override
	protected void keyRelesed(int key) {
		// TODO Auto-generated method stub
		
	}

}
