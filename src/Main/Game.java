package Main;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public Game(){
		super("TheGame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new GamePanel());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String [] args){
		new Game();
		//TileMap map = new TileMap(path, 2);
		//System.out.print(map.toString());
	}

}
