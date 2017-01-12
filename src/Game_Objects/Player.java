package Game_Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;
import Map.Tile;
import Map.TileMap;
import Queue.Queue;
import Tools.Tool;


public class Player  extends GameObject{
    
	private ArrayList<Bullet> bullets;
	private boolean shooting;
	
	private boolean facingRight;
	private boolean dropping; //dropping an item
	private Tile[] playerTileset;
	private boolean run;
	
	private Font font;
	
	
	private int health;
	
	private String bag = "";
	
	public Player(TileMap map){
		super(map);
		facingRight = true;
		bullets = new ArrayList<Bullet>();
		shooting = false;
		maxSpeed = 1;
		dropping = false;
		friction = 1;
		acceleration = 1.2;
		maxSpeed = 3;
		//acceleration =0.3;
		Tool.start();
		playerTileset = map.loadTileset("/Resource/p.png", 20, 64);
		health = 10;
		font = new Font("Impact", Font.PLAIN, 15);
	} 
	//------------------------setters and getters-----------
	/**
	 * @return true if dropping item
	 * */
	public boolean isDropping() {
		return dropping;
	}

	/**
	 * set dropping
	 * */
	public void setDropping(boolean dropping) {
		this.dropping = dropping;
	}
	
	/**
	 * set shooting
	 * */
	public void setShooting(boolean shooting){
		this.shooting = shooting;
	}
	
	/**
	 * set shooting
	 * */
	public void setFacingRight (boolean facingRight){
		this.facingRight = facingRight;
	}
	
	/**
	 * @return true if facing right
	 * */
	public boolean isFacingRight(){return facingRight;}

	/**
	 * set bag
	 * */
	public void setBag(String bag){
		this.bag = bag;
	}
	//-------------------methods------------------------
	/**
	 * removing items from bag
	 * */
	public void checkItems(ArrayList<Item> items){
		for(int i = 0 ; i< items.size() ;i++){
			for(int b = 0 ; b< bullets.size(); b++){
				if(items.get(i).intersects(bullets.get(b))){
					//remove item
					items.remove(i);
					i--;
				}
			}
		}
	}
	
	/**
	 * player hit by enemies
	 * */
	public void playerHit(){
		health--;
		if(health <= 0)health=0;
	}
	
	/**
	 * @return health of player as a string
	 * */
	public String getHealth() {
		switch (health) {
		case 1:
			return "x";
		case 2:
			return "xx";

		case 3:
			return "xxx";

		case 4:
			return "xxxx";

		case 5:
			return "xxxxx";

		case 6:
			return "xxxxxx";

		case 7:
			return "xxxxxxx";

		case 8:
			return "xxxxxxxx";

		case 9:
			return "xxxxxxxxx";

		case 10:
			return "xxxxxxxxxx";

		default:
			return "dead";

		}
	}

	/**
	 * @return string of bag contents
	 * */
	public String getBag(Queue<Item> bag){
		return bag.toString();
	}
	
	/**
	 * @return true if player is dead
	 * */
	public boolean isDead(){
		return(health == 0 );
	}
	
	/**
	 * used for the player hitting eneimes
	 * */
	public void hitEnemeis(ArrayList<Enemy> enemies){
		for(int e = 0 ; e< enemies.size();e++){
			for(int b = 0 ; b < bullets.size();b++){
				if(enemies.get(e).intersects(bullets.get(b))){
					enemies.get(e).hit(bullets.get(b).getDamage());
				}
				if(enemies.get(e).dead){
					//remove enemy
					enemies.remove(e);
					e--;
				}
			}
		}
	}
	
	private BufferedImage flipImage(BufferedImage image){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}
	//---------------Update & draw----------------------
	public void update(){
		playerMovement();
		tileMapCollision();
		
		map.setX((int)-x + GamePanel.WIDTH/2 );
		map.setY((int)-y + GamePanel.HEIGHT/2);
		
		if(shooting && Tool.getCount() >= 1){
			Bullet b = new Bullet(map, facingRight);
			b.setPosition(x, y);
			bullets.add(b);
			Tool.initCounter();
		}
		
		
		
		// update bullets
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			if(bullets.get(i).shouldRemove()) {
				bullets.remove(i);
				i--;
			}
		}
		
		if(left || right) run = true;
		else run = false;
		
	}
	
	public void draw(Graphics2D g){
		
		//bag
		
		for(Bullet b: bullets){
			b.draw(g);
		}
		int tileX = map.getX();
		int tileY = map.getY();
		
		
		{
			g.setFont(font);
			g.setColor(Color.CYAN);
			
			g.drawString(getHealth(),
					(int)(tileX + x - width/2)-20,
					(int)(tileY + y - height/2) +50
					);
		}
		
		
		g.setFont(font);
		g.drawString(bag,
				(int)(tileX + x - width/2) -50,
				(int)(tileY + y - height/2) -50
				);
		
		
		BufferedImage standing = playerTileset[6].getImage();
		BufferedImage [] running = new BufferedImage[5];
		
		running[0] = playerTileset[8].getImage();
		running[1] = playerTileset[9].getImage();
		running[2] = playerTileset[10].getImage();
		running[3] = playerTileset[11].getImage();
		running[4] = playerTileset[12].getImage();
		
		if(facingRight){
			
			if(run){
				for(int i = 0 ; i < 5;i++){
					g.drawImage(running[i],
							(int)(tileX + x - width/2),
							(int)(tileY + y - height/2),
							width ,height,null);
				}
				
			}else {
				g.drawImage(standing,
						(int)(tileX + x - width/2),
						(int)(tileY + y - height/2),
						width ,height ,null);
			}
			
			
			
		}else {
			if(run){
				for(int i = 0 ; i < 5;i++){
					g.drawImage(flipImage(running[i]),
							(int)(tileX + x - width/2),
							(int)(tileY + y - height/2),
							width ,height,null);
				}
				
			}else {
				g.drawImage(flipImage(standing),
						(int)(tileX + x - width/2),
						(int)(tileY + y - height/2),
						width ,height,null);
			}
		}
		
	}

}
