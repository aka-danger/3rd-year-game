package GameStateManger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Enemies.Bird;
import Enemies.Elevator;
import Enemies.Hopper;
import Enemies.Slug;
import Game_Objects.Enemy;
import Game_Objects.GameObject;
import Game_Objects.Item;
import Game_Objects.Player;
import Items.Battery;
import Items.Diamond;
import Items.Door;
import Items.Fruit;
import Items.Gem;
import Items.Gold;
import Items.Hammer;
import Items.Table;
import Main.GamePanel;
import Map.TileMap;
import Queue.Queue;
import Tools.Tool;
import Trees.GameLogic;


public class Level extends GameState{

	private TileMap map;
	private Player player;
	private boolean facingRight;
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	
	private Door door;
	
	private String message = "";
	
	private boolean winner ;
	private boolean dead;
	
	private PausedState state;

	//private Weapon w;
	//A player has a bag of gems that you collect
	public static Queue<Item> bag = new Queue<Item>();
	
	public Level(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	//---------------utilites ------------------------------
	private void creatItems() {
		// have to win
		items.add(new Battery(map));
		items.add(new Gem(map));
		items.add(new Table(map));
		// enemies = new ArrayList<Enemy>();
		for (int i = 0; i < TileMap.itemPosition.size() - 3; i++) {
			switch (Tool.randomInt(1, 7)) {
			case 1:
				items.add(new Battery(map));
				break;
			case 2:
				items.add(new Diamond(map));
				break;
			case 3:
				items.add(new Fruit(map));
				break;
			case 4:
				items.add(new Gem(map));
				break;
			case 5:
				items.add(new Gold(map));
				break;
			case 6:
				items.add(new Hammer(map));
				break;
			case 7:
				items.add(new Table(map));
			default:
				items.add(new Gem(map));
				break;
			}
		}
	}

	private void createDoor(){
		items = new ArrayList<Item>();
		door = new Door(map);
		door.setPosition(map.getDoorCoord().getX(), map.getDoorCoord().getY());
	}
	
	private void game(){
		if (bag.size() ==3 && player.intersects(door)){
			Queue<Item> temp = bag.copy();
			if(temp.first() instanceof Door) System.out.println("first one is correct");
			else System.out.println("hint the first item is a Batterty");
			
			if(GameLogic.isWinner(temp)){
				winner = true;
				
			}else{
				winner = false;
				message ="No match";
				//wrong order
				
				//System.out.println("winner");
			}
		}
		
		if(player.intersects(door)){
			if(bag.isEmpty()){
				//bag empty
				message = "Bag is empty";
			}else if(!(bag.first() instanceof Battery)){
				//first item not correct get a bag
				message = "get a <battery> first";
			}
			
		}
	}
	
	private void playerDroppingItem() {
		// droping item
		if (player.isDropping()) {
			System.out.println(bag.size());
			Tool.start();
			if (Tool.getCount() >= 2) {
				// System.out.println("dropping");
				if (!bag.isEmpty()) {
					Item temp = bag.dequeue();

					if (player.getX() <= 1488.3)
						temp.setPosition(player.getX() + 50, player.getY() - 20);
					else
						temp.setPosition(player.getX() - 50, player.getY() - 20);

					if (!player.isFacingRight()) {
						if (player.getX() >= 81.0000000012)
							temp.setPosition(player.getX() - 50,
									player.getY() - 20);
						else
							temp.setPosition(player.getX() + 50,
									player.getY() - 20);
					}

					items.add(temp);
					System.out.println(bag.toString());
					Tool.initCounter();
				}
			}
			Tool.stop();
		}
	}
	
	private void playerPickingUpItem() {
		// pick up item
		for (int i = 0; i < items.size(); i++) {
			// items.get(i).update();
			try {
				GameObject temp = (GameObject) items.get(i);
				if (player.intersects(temp) && bag.size() != 3) {
					if (!(bag.size() >= 3)) {
						bag.enqueue(items.remove(i));
						System.out.println(bag.size());
						System.out.println(bag.toString());
						i--;
					} else
						System.out.println("cant pick up: " + bag.toString());
					if (i <= 0)
						i = 0;
				} else
					items.get(i).update();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	//---------------init update and draw-------------------
	@Override
	protected void init() {
		map = new TileMap("/Resource/level.txt", 32);
		player = new Player(map);
		state = GameStateManager.pausedState;
		
		winner = false;
		dead =false;
		
		createDoor();
		creatItems();
		
		enemies = new ArrayList<Enemy>();
		
		
		//bird
		for(int b = 0 ; b < TileMap.birdPosition.size(); b++){
			enemies.add(new Bird(map, TileMap.BIRD));
		}
		
		//elevator
		for(int e = 0 ; e <TileMap.elevatorPosition.size(); e ++){
			enemies.add(new Elevator(map, TileMap.ELEVATOR));
		}
		
		//slug 
		for(int s = 0 ; s < TileMap.slugPosition.size();s++){
			enemies.add(new Slug(map,TileMap.SLUG));
		}
		
		//hopper
		for(int h = 0 ; h <TileMap.hopperPosition.size();h++){
			enemies.add(new Hopper(map, TileMap.HOPPER));
		}
		
	}
	
	
	
	@Override
	protected void update() {

		map.update();
		player.update();
		player.setFacingRight(facingRight);
		// System.out.println(player.getX());
		door.update();

		game();
		playerDroppingItem();
		playerPickingUpItem();

		for (Enemy e : enemies) {
			e.update();
			if (player.intersects(e)) {
				Tool.start();

				if (Tool.getCount() >= 2) {
					player.playerHit();
					message ="ouch";
					if(player.isDead()) dead = true;
					Tool.initCounter();
				}
			}

		}
		Tool.stop();

		player.hitEnemeis(enemies);
		
		if(dead){
			state.setMessage("dead");
			GameStateManager.paused = true;
		}
		
		if(winner){
			state.setMessage("winner");
			GameStateManager.paused = true;
		}

		// check bullet collision
		// player.hitEnemeis(enemies);

	}

	@Override
	protected void draw(Graphics2D g) {

		map.draw(g);
		player.draw(g);
		if (!bag.isEmpty())
			player.setBag(bag.toString());
		else
			player.setBag(bag.toString());
		door.draw(g);

		for (Item i : items) {
			i.draw(g);

		}

		for (Enemy e : enemies) {
			e.draw(g);
		}
		
		
		
		g.setColor(Color.RED);
		g.drawString(message, GamePanel.WIDTH/2,GamePanel.HEIGHT/2 -15);
		Tool.start();
		if(Tool.getCount() >= 3){
			message ="";
			Tool.initCounter();
		}
		Tool.stop();
	}

	//------------events ------------------------
	@Override
	protected void keyPressed(int key) {
	int code = key;
		
		switch (code){
			case KeyEvent.VK_LEFT:
				player.setLeft(true );
				facingRight = false;
				break;
			case KeyEvent.VK_RIGHT:
				player.setRight(true );
				facingRight = true;
				break;
			case KeyEvent.VK_UP:
				player.setJumping(true);
				
				break;	
			case KeyEvent.VK_SPACE:
				//weapon.setShooting(true);
				player.setShooting(true);
				break;
			case KeyEvent.VK_D:
				//Item temp = bag.dequeue();
				//temp.setPosition(player.getX(), player.getY());
				player.setDropping(true);
				break;
		
			default:
				//do nothing
				
			break;
		}
	}

	@Override
	protected void keyRelesed(int key) {
		int code = key;
		
		
		switch (code){
		case KeyEvent.VK_LEFT:
			player.setLeft(false);
			
			break;
		case KeyEvent.VK_RIGHT:
			player.setRight(false);
			break;
		case KeyEvent.VK_UP:
			player.setJumping(false);
			break;
		case KeyEvent.VK_SPACE:
			//weapon.setShoot(false);
			//player.setShooting(false);
			player.setShooting(false);
			break;
		case KeyEvent.VK_D:
			player.setDropping(false);
		break;
	
		default:
			//do nothing
			
		break;
		}
	}

}
