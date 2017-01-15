package main;

import processing.core.PApplet;

public class MainController extends PApplet{

	private Background back;
	private Shape shape;
	private int backWidth = 500;
	private int backHeight = 700;
	private int moveWidth = 25;
	private int moveHeight = 25;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
    }

	public void settings(){
		size(510, 710);
    }
	
	public void setup(){
		back = new Background(this);
		shape = new Shape(this, backWidth, backHeight);
	}
	
	public void draw() {
		back.init(backWidth, backHeight);
		shape.drawShape(backWidth, backHeight, 0, 0);
	}
	
	public void keyPressed() {
		switch(keyCode) {
			case(37) : 
				System.out.println("left");
				shape.drawShape(backWidth, backHeight, moveWidth * -1, 0);
				break;
			case(38) : 
				System.out.println("up");
				break;
			case(39) : 
				System.out.println("right");
			shape.drawShape(backWidth, backHeight, moveWidth, 0);
				break;
			case(40) : 
				System.out.println("down");
			shape.drawShape(backWidth, backHeight, 0, moveHeight);
				break;
		}
	}
	
}