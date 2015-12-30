package routingtool.gui.damagecalculator;

public class Region {
	public Region(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	boolean containsPoint(int x, int y){
		if (x >= this.x && x <= this.x + this.width){
			if (y >= this.y && y <= this.y + this.height){
				return true;
			}
		}
		return false;
	}
	
	boolean containsPoint(int x, int y, float scale){
		if (x >= this.x * scale  && x  <= (this.x + this.width) * scale){
			if (y >= this.y * scale && y <= (this.y + this.height) * scale){
				return true;
			}
		}
		return false;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	private int x;
	private int y;
	private int width;
	private int height;
}
