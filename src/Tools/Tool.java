package Tools;
import java.awt.Component;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;


public class Tool {
	//--------------------Timer--------------------------
	private static Timer  timer;
	private static TimerTask task;
	private static int count = 0;
	
	
	/**
	 * start a timer
	 * */
	public static void start(){
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				count++;
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}

	/**
	 * resart count of timer
	 * */
	public static void initCounter(){count = 0;}
	
	/**
	 * get count of timer in seconds
	 * */
	public static int getCount(){return count;}
	
	/**
	 * stop a timer
	 * */
	public static void stop(){
		timer.cancel();
	}
	//------------------------end of timer--------------------
	/**
	 * @param min min value
	 * @param max  maxt value
	 * @return random integer
	 * */
	public static int randomInt(int min , int max){
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * @param min min value
	 * @param max  maxt value
	 * @return random Double
	 * */
	public static  double randomDouble(int min, int max){
		Random random = new Random();
		return min + (max - min ) *random.nextDouble();
	}
	
	/**
	 * @param string string to be printed to the console
	 * */
	public static void print(Object string){
		System.out.println(string);
	}
	
	
	public static Object msgBox(Component parentComponent, String label,String textbox){
		String result = JOptionPane.showInputDialog(parentComponent,label, textbox);
		return (Object)result;
	}
	
	//00000
	//01110
	//01110
	//01110
	//01110
	//00000
	public static StringBuilder mapCreator(int numRows, int numCols){
		
		StringBuilder line = new StringBuilder();
		
		for(int r = 0 ; r<numRows ; r++){
			for(int c = 0 ; c<numCols ; c ++){
				line.append( "1" + " ");
			}
			line.append("\n");
		}
	
		return line ;
		
	}
	
	public static int[][] map(int numRows, int numCols){
		
		int map [][] = new int [numRows][numCols];
		
		for(int r = 0 ; r<numRows ; r++){
			for(int c = 0 ; c<numCols ; c ++){
				map[0][c] = 0;
				map[numRows-1][c] = 0;
				map[r][0] = 0;
				map[r][numCols -1] = 0;
			}
		}
		return map ;
		
	}

	public static void main(String [] args){
		System.out.println(mapCreator(10,10 ));
		
	}

}
