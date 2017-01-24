package main;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class MainController extends PApplet{

	private Background back;
	private Shape shape;
	public final static int backgroundWidth = 500;
	public final static int backgroundHeight = 700;
	public final static int blockLength = 25;
	//private int moveHeight = 25;
	public static boolean[][] isUsed = new boolean[(backgroundWidth / blockLength) + 1][(backgroundHeight / blockLength) + 1];
	private List<Shape> shapeList = new ArrayList<Shape>();
	
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
		
		
		if(shape.drawShape(Direction.nothing)) {
			shapeList.add(shape);
			shape = new Shape(this);
		};
		if(shapeList.size() > 0) {
			this.drawShapeList();
		}
		
	}
	
	private void drawShapeList() {
		for(int i = 0; i < this.shapeList.size(); i++) {
			this.shapeList.get(i).drawShape(Direction.nothing);
		}
	}

	public void keyPressed() {
		switch(keyCode) {
			case(37) : 
				//System.out.println("left");
				shape.drawShape(Direction.left);
				break;
			case(38) : 
				//System.out.println("up");
				break;
			case(39) : 
				//System.out.println("right");
			shape.drawShape(Direction.right);
				break;
			case(40) : 
				//System.out.println("down");
				shape.drawShape(Direction.down);
				break;
		}
	}
	
}