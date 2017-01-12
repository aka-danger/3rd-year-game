package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import GameStateManger.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{

	private final int FPS = 30; //desired frames
	
	// Size of panel
	public static final int WIDTH = 400; //size of a tile
	public static final int HEIGHT = 400; //size of a tile
	public static final int SCALE = 30;
	
	private Thread animator; // for animation
	private boolean running; // stop animation/game

	// newer form of graphics object
	private Graphics2D g;
	// discribes an image
	private BufferedImage image;
	

	private GameStateManager gsm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH , HEIGHT ));
		setFocusable(true);
		requestFocus();
	}

	/**
	 * - overriding method
	 * gets called when a component is added to a container. 
	 * can get information without the possiblity of getting nulls like constructuer
	 * that is why the addKeylistenr is here 
	 */
	public void addNotify() {
		super.addNotify();
		startGame();
	}

	/**
	 * start thread of the game
	 * */
	public void startGame() {
		// if thread is null or program is not running
		if (animator == null) {
			addKeyListener(this);
			animator = new Thread(this);
			animator.start();
		}
	}

	/**
	 * stop thread of the game
	 * */
	public void stopGame() {
		running = false;
	}
	
/////////INIT/////////////INIT////////////INIT////////////////////////
	private void init (){
		running = true;
		// image platform for graphics to draw
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// getGraphics from image
		g = (Graphics2D) image.getGraphics();

		gsm = new GameStateManager();
	}
/////////RUN/////////////RUN////////////RUN//////////////////////////	
	/**
	 * Game loop run function
	 * */
	public void run() {
		init();
		long startTime;
		long URDTimeMillis;
		long waitTime;
		// the time it takes for one loop to reach FPS
		long targetTime = 1000 / FPS;

		while (running) {
			// gets time in nano seconds
			startTime = System.nanoTime();

			update();
			render();
			draw();

			// get the time in milliseconds
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			if(waitTime < 0) waitTime = targetTime;
			
			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	///////UPDATE////////////RENDER/////////DRAW/////////////////////////
	/**
	 * loop for updating the game
	 * */
	public void update() {
		gsm.update();
	}

	/**
	 * loop for graphical content of game state manager
	 * */
	private void render() {
		gsm.draw(g);
	}
	
	/**
	 * loop for graphical content
	 * */
	private void draw() {
		Graphics g2 = this.getGraphics(); 
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

///////////////////////////////EVENTS//////////////////////////
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyRelesed(e.getKeyCode());
	}

}


