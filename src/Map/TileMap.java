package Map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import java.io.InputStreamReader;

import javax.imageio.ImageIO;



import Stack.Stack;
;

public class TileMap {
	
	private int xOffset ; //used for camera
	private int yOffset;
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	
	public static final int BLOCKED = 0;
	public static final int LAND = 1;
	public static final int ITEM_POSITION = 2;
	public static final int ENEMY_POSITION= 3 ;
	public static final int PLAYER = 4;
	public static final int DOOR = 5;
	
	//enemies
	public static final int SLUG = 6;
	public static final int HOPPER = 7;
	public static final int BIRD = 8;
	public static final int ELEVATOR = 9;
	
	
	public static Stack<Coordinate> itemPosition  = new Stack<Coordinate>();
	public static Stack<Coordinate> slugPosition = new Stack<Coordinate>();
	public static Stack<Coordinate> hopperPosition = new Stack<Coordinate>();
	public static Stack<Coordinate> birdPosition = new Stack<Coordinate>();
	public static Stack<Coordinate> elevatorPosition = new Stack<Coordinate>();
	
	//public static Stack<Coordinate> enemyPosition = new Stack<Coordinate>();
	

	
	public Coordinate doorCoord;
	public Coordinate getDoorCoord(){return doorCoord;}

	//private Tile[][] tileset;
	
	public TileMap (String path, int tileSize){
		this.tileSize = tileSize;
		loadMap(path);
		//init();
	}
	
	
	//------------ utility-------------------------------------------
	public String toString(){
		for(int r = 0; r < numRows ; r ++){
			for(int c = 0 ; c <numCols ; c++){
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		return "";
	}
	
	
	/**
	 * @param path of tileset
	 * @param number of colums of tile set
	 * @param number if rows of tile set
	 * @param ps pixel size of image
	 * */
	public Tile[] loadTileset(String path, int numAcross,int pixelSize){
		
		//assume all tiles are 64
		BufferedImage image  = null;
		Tile [] tileset = null;
		
		
		try {

			image = ImageIO.read(
				TileMap.class.getResource(path)
			);
			
			tileset = new Tile[numRows];
			
			BufferedImage subimage;
		
			for(int c = 0 ; c < numAcross; c++){
				subimage = image.getSubimage(
						c * (tileSize*2),
						0,
						pixelSize,
						pixelSize
					);
				tileset[c] = new Tile(subimage, PLAYER);
				
				/*subimage = image.getSubimage(
						c * (tileSize*2),
						0,
						ps,
						ps
					);
				tileset[r][c] = new Tile(subimage, PLAYER);
				*/
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		return tileset;
	}

	/**
	 * @param path of file
	 * loading a matrix of tile sets
	 * */
	public Tile[][] loadTileset(String path) {
		Tile[][] tiles = null;
		BufferedImage tileset;
		try {
			tileset = ImageIO.read(new File(path));
			int numTilesAcross = tileset.getWidth() / (tileSize * 2);
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * (tileSize*2), 0, tileSize,
						tileSize);
				tiles[0][col] = new Tile(subimage, BLOCKED );
				subimage = tileset.getSubimage(col * (tileSize*2), tileSize,
						tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, BLOCKED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tiles;
	}
	
	/**
	 * loading of the map from a text file
	 * */
	public void loadMap(String path){
		//read from file and put into array of map;
		//getting info into map
		
		BufferedReader reader = null ;
	 
		try {
			reader = new BufferedReader(new InputStreamReader(TileMap.class.getResource(path).openStream()));
		
			//number of rows and cols
			numRows = (int)Integer.parseInt(reader.readLine());
			numCols = (int)Integer.parseInt(reader.readLine()) ;
			
			
			map = new int[numRows][numCols];
			
			for(int r = 0 ; r < numRows ; r ++){
				//each row being a line of textfile
				String line = reader.readLine();
				String [] cells = line.split(" ");
				for(int c = 0 ; c< numCols ; c++){
					int item = Integer.parseInt(cells[c]);
					if(item == ITEM_POSITION){
						itemPosition.push(new Coordinate(r * tileSize, c* tileSize));
					}else if( item == SLUG){
						slugPosition.push( new Coordinate(r * tileSize, c* tileSize));
					}else if(item == HOPPER){
						hopperPosition.push( new Coordinate(r * tileSize, c* tileSize));
					}else if(item == BIRD){
						birdPosition.push(new Coordinate(r * tileSize, c* tileSize));
					}else if(item == ELEVATOR){
						elevatorPosition.push(new Coordinate(r * tileSize, c* tileSize));
					}else if(item == DOOR){
						doorCoord = new Coordinate(r * tileSize, c * tileSize);
					}
					map[r][c] = item;
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("io exception");
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//------------ setters & getters --------------------------------
	public int getX(){return xOffset;}
	public int getY(){return yOffset;}
	public void setX(int x){xOffset = x;}
	public void setY(int y){yOffset = y;}
	
	// if our players x and y position was at 32 and our tile size was 32
	// then we would get 0 which would be the first tile
	//allows you to get the current tile the object is on
	public int getRowTile (int y){ return y/tileSize ;}
	public int getColTile (int x){ return x/tileSize;}
	
	//getter to get the row and the col of the map int array
	public int getTile(int row, int col){
		return map[row][col];
	}
	
	public int getTileSize(){return tileSize;}
	public void setTileSize(int tileSize){this.tileSize = tileSize;}
	
	public void setTile(int row, int col,int element){
		map[row][col] = element;
	}
	//-------------------Update + Draw-------------------------------
	public void update(){
	
	}
	
	public void draw(Graphics2D g){
		
		
		for(int r = 0 ; r< numRows ; r++){
			for(int c = 0 ; c< numCols; c++){
				
				int item = map[r][c];
				
				switch(item){
				case BLOCKED:
					g.setColor(Color.BLACK);
					//g.drawImage(tileset[0][1].getImage(),xOffset + c * tileSize, yOffset + r * tileSize, tileSize, tileSize,null);
					//g.drawImage(image,xOffset + c * tileSize, yOffset + r * tileSize, tileSize, tileSize,null);
					break;
				case LAND:
					g.setColor(Color.white);
					break;
				case ITEM_POSITION:
					//g.setColor(Color.pink);
					//g.fillRect(xOffset + c * tileSize, yOffset + r * tileSize, tileSize, tileSize);
					break;
				default:
					g.fillRect(xOffset + c * tileSize, yOffset + r * tileSize, tileSize, tileSize);
					break ;
				}
				g.fillRect(xOffset + c * tileSize, yOffset + r * tileSize, tileSize, tileSize);
				
			}
		}
	}
	

}
