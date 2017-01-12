package Map;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import Main.GamePanel;

/**
 * class for the background image in the game
 * @author Brendon Clark 201321527
 * */
public class Background {
	//private BufferedImage image;
	private Image icon = new ImageIcon(Background.class.getResource("/Resource/menu.gif")).getImage();
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;//for parallex looking effect
	
	public Background(String path, double moveScale){
		
		//read from a file
		/*try{
			image = ImageIO.read(new File(path));
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}
	
	//--------------setters and getters--------------------------
	public void setPosition(double x, double y){
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public Image getImage(){return icon;}
	
	//---------------update and draw--------------------------------
	public void update(){
		x += dx;
		y += dy;
	}
	
	
	
	public void draw(Graphics2D g){
		
		
		//draw to panel
		g.drawImage(icon,(int)x,(int)y , null);
		//draw a copy for if it goes off the screen
		
		if(x > 0){
			//draw to the left
			g.drawImage(icon, (int) x- GamePanel.WIDTH, (int) y, null);
		}else if( x< 0)
		{
			//draw to the left
			g.drawImage(icon, (int) x+ GamePanel.WIDTH, (int) y, null);
		}
	}
	
}
