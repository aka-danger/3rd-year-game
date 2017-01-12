package Game_Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.GamePanel;
import Map.TileMap;

//root of all game objects
public abstract class GameObject {

	// tile stuff
	protected int tileSize;
	protected TileMap map;

	// position and vectors and movement
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	protected boolean left;
	protected boolean right; 
	protected boolean falling;
	protected boolean jumping;
	protected boolean up;
	protected boolean down;

	// dimensions of object
	protected int width;
	protected int height;


	// collision variables
	protected int currentCol;
	protected int currentRow;
	protected double destX;
	protected double destY;
	protected double tempX;
	protected double tempY;

	// corners
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomRight;
	protected boolean bottomLeft;

	// physics variables
	protected double acceleration;
	protected double maxSpeed;
	protected double maxFallingSpeed;
	protected double friction;
	protected double jumpSpeed;
	protected double gravity;
	
	protected Color color;

	public GameObject(TileMap map){
	
		this.map = map;

		width = map.getTileSize();
		height = map.getTileSize();
		
		width = map.getTileSize();
		height = map.getTileSize();
		

		
		setX(GamePanel.WIDTH/2);
		setY(GamePanel.HEIGHT/4);
		
		acceleration = 0.5;
		maxSpeed = 4;
		maxFallingSpeed =10;
		friction = 0.2;
		jumpSpeed = -12;
		gravity = 2.0;
		
		color = Color.red;
		
		init();
	}

	//--------------------------Collisions-----------------------------
	/**
	 * rectangle collision detection. 
	 * @return true if objects intersect and false if they do not
	 * */
	public boolean intersects(GameObject o) throws NullPointerException{
		
		Rectangle r1 = getRec();
		Rectangle r2 = o.getRec();
		if(r1 == null || r2 == null) throw new NullPointerException();
		return r1.intersects(r2);
	}
	
	private Rectangle getRec(){
		//return new Rectangle((int)x - width, (int)y- height, width, height);
		int tileX = map.getX();
		int tileY = map.getY();
		return new Rectangle((int)(tileX + x - width/2),(int)(tileY + y - height/2),width, height);
	}
	
	private  void calculateCorners(double x, double y){
		int leftTile = map.getColTile((int)(x - width/2));
		int rightTile = map.getColTile((int)(x + width/2) -1); // -1 to not go out of bounce
		int topTile = map.getRowTile((int)(y - height/2));
		int bottomTile = map.getRowTile((int)(y+ height/2)-1); // -1 to not go out of bounce
		
		topLeft = map.getTile(topTile, leftTile) == 0;
		topRight = map.getTile(topTile, rightTile) == 0 ;
		bottomLeft = map.getTile(bottomTile, leftTile) == 0;
		bottomRight = map.getTile(bottomTile, rightTile) == 0;
	}
	
	protected void tileMapCollision(){
		// check for collisions on the tile map
	
		int currentCol = map.getColTile((int) x );
		int currentRow = map.getRowTile((int) y  );
		
		//destination after updating
		double destX = x + dx;
		double destY = y + dy;
		
		// we dont want to effect the actual x and y  until the very end
		double tempX = x ;
		double tempY = y;
				
		calculateCorners(x, destY);
		if(dy < 0){
			if (topLeft || topRight){
				dy = 0 ; 
				tempY = currentRow * map.getTileSize() + height/2;
			}
			else tempY += dy ;
		}
		if(dy > 0){
			if (bottomLeft || bottomRight){
				dy = 0 ; 
				falling = false ;
				tempY = (currentRow+1) * map.getTileSize() - height/2;
			}
			else tempY += dy ;
		}
		
		calculateCorners(destX, y);
		if (dx <0){
			if (topLeft || topRight){
				dx = 0 ; 
				tempX = currentCol * map.getTileSize() + width/2;
			}else tempX += dx;
		}
		if (dx >0){
			if (bottomLeft || bottomRight){
				dx = 0 ; 
				tempX = (currentCol+1) * map.getTileSize() - width/2;
			}else tempX += dx;
		}
		
		//checking if we falling off a cliff
		
		if(! falling){
			calculateCorners(x, y+1);
			if (!bottomLeft && !bottomRight){
				falling = true ;
			}
		}
		x = tempX;
		y = tempY;
	}
	
	//------------------player movement-------------------------------
	protected void playerMovement() {
		// left and right
		if (right) {
			// accelerating
			dx += +acceleration;
			if (dx <= maxSpeed)
				dx = +maxSpeed;
		}
		 if (left) {
			// accelerating
			dx += -acceleration;
			if (dx >= -maxSpeed)
				dx = -maxSpeed;
		} 
			// decelerating
			if (dx > 0) {
				dx -= friction;
				if (dx < 0)
					dx = 0;
			} else if (dx < 0) {
				dx += friction;
				if (dx > 0)
					dx = 0;
			
		}

		// up and down
		if (jumping) {
			dy = jumpSpeed; // instant change in velocity
			falling = true;
		}
		if (falling) {
			dy += gravity;
			jumping = false;
			if (dy > maxFallingSpeed)
				dy = maxFallingSpeed;
		} else
			dy = 0;

		
	}
	
	//-------------------------Setters and getters---------------------
	/**
	 * set X position of object
	 * */
	public void setX(int x ) { this.x = x ;}
	/**
	 * set Y position of object
	 * */
	public void setY(int y) {this.y = y;}
	/**
	 *@return Y position of object
	 * */
	public double getY(){return y;}
	/**
	 *@return X position of object
	 * */
	public double getX(){return x;}
	/**
	 *set X and y position at the same time
	 * */
	public void setPosition(double x , double y){
		this.x = x;
		this.y = y;
	}
	/**
	 *set left boolean which is used to describe if the object is left
	 * */
	public void setLeft (boolean left){this.left = left;}
	/**
	 *@return if object left return true else false
	 * */
	public boolean isLeft(){return left;}
	/**
	 *set right boolean which is used to describe if the object is right
	 * */
	public void  setRight(boolean right){this.right = right;}
	/**
	 *@return true if right else false
	 * */
	public boolean isRight(){return right;}
	/**
	 *set object jumping
	 * */
	public void setJumping (boolean jump){this.jumping = jump;}
	/**
	 *set object up
	 * */
	public void setUp(boolean up){this.up = up;}
	/**
	 *@return if object up true else false
	 * */
	public boolean getUp(){return up;}
	/**
	 *set down
	 * */
	public void setDown(boolean down){this.down = down;}
	/**
	 *@return true if down else false
	 * */
	public boolean getDown(){return down;}
	/**
	 *set color of object
	 * */
	public void setColor(Color color){this.color = color;}
	
	
	//--------------------------update & draw & init---------------------
	/**
	 * Initialize game object for constructor
	 * - not part of game loop
	 * */
	public void init(){
		
	}
	
	/**
	 * update for any concurrent changes that an object experiances
	 * - part of game loop
	 * */
	public void update(){
		tileMapCollision();
		
	}
	
	/**
	 * draw for any graphical changes that an object experiances
	 * - part of game loop
	 * */
	public void draw(Graphics2D g){
		int tileX = map.getX();
		int tileY = map.getY();
		
		String controls = "D-Drop / Space-Shoot / arrows to move";
		g.drawString(controls,(int) width/2,
				(int) height/2);
		
		g.setColor(color);
		g.fillRect(
				(int)(tileX + x - width/2),
				(int)(tileY + y - height/2),
				width, height
				);
	}

	
}