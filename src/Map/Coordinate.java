package Map;

public class Coordinate {
	
	private double x;
	private double y;
	
	public Coordinate(double x, double y){
		this.x = x;
		this.y = y;
	}

	
	public Coordinate(int row, int col){
		this.x = col;
		this.y = row;
	}
	
	public int getRow(){
		return (int)y;
	}
	
	public int getCol(){
		return (int)x;
	}
	
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
	public String toString(){
		return String.format("x-> %s y-> %s",String.valueOf(x),String.valueOf(y));
	}
	

}
