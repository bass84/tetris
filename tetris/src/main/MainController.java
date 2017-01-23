package main;

import processing.core.PApplet;

public class MainController extends PApplet{

	private Background back;
	private Shape shape;
	public final static int backgroundWidth = 500;
	public final static int backgroundHeight = 700;
	public final static int blockLength = 25;
	//private int moveHeight = 25;
	private boolean[][] isUsed = new boolean[(this.backgroundWidth / this.blockLength) + 1][(this.backgroundHeight / this.blockLength) + 1];
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
    }

	public void settings(){
		size(this.backgroundWidth, this.backgroundHeight);
    }
	
	public void setup(){
		back = new Background(this);
		shape = new Shape(this);
		
		for(int i = 1; i < (this.backgroundWidth / this.blockLength) + 1; i++){	//	가장 아랫줄 true
			isUsed[i][this.backgroundHeight / this.blockLength] = true;
		}
		for(int i = 0; i < (this.backgroundHeight / this.blockLength) + 1; i++) {	//	가장 왼쪽 줄 true
			isUsed[0][i] = true;
		}
	}
	
	public void draw() {
		back.init(backgroundWidth, backgroundHeight);
		shape.drawShape(this.isUsed, Direction.nothing);
		
	}
	
	public void keyPressed() {
		switch(keyCode) {
			case(37) : 
				System.out.println("left");
				shape.drawShape(this.isUsed, Direction.left);
				break;
			case(38) : 
				System.out.println("up");
				break;
			case(39) : 
				System.out.println("right");
			shape.drawShape(this.isUsed, Direction.right);
				break;
			case(40) : 
				//System.out.println("down");
				shape.drawShape(this.isUsed, Direction.down);
				break;
		}
	}
	
}