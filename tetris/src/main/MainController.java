package main;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class MainController extends PApplet{

	private Background back;
	private Shape shape;
	public final static int backgroundWidth = 500;	//너비
	public final static int backgroundHeight = 700;	//깊이
	public final static int blockLength = 25;	//블럭 한개 크기
	public static boolean[][] isUsed = new boolean[(backgroundWidth / blockLength) + 1][(backgroundHeight / blockLength) + 1];	// 각 블럭 boolean값
	private List<Shape> shapeList = new ArrayList<Shape>();
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
    }

	public void settings(){
		size(this.backgroundWidth, this.backgroundHeight);	//전체 크기 설정
    }
	
	public void setup(){
		back = new Background(this);
		shape = new Shape(this);	//도형 생성
		
		for(int i = 1; i < (this.backgroundWidth / this.blockLength) + 1; i++){	//	가장 아랫줄 true
			isUsed[i][this.backgroundHeight / this.blockLength] = true;
		}
		for(int i = 0; i < (this.backgroundHeight / this.blockLength) + 1; i++) {	//	가장 왼쪽 줄 true
			isUsed[0][i] = true;
		}
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		back.init(backgroundWidth, backgroundHeight);
		this.drawShapeList();
		
		if(shape.drawShape(Direction.nothing)) {
			shapeList.add(shape);
			shape = new Shape(this);
		}
		
	}
	
	private void drawShapeList() {	// 각 도형들을 내려진 자리에 그린다.
		if(this.shapeList.size() == 0) return;
		
		for(int i = 0; i < this.shapeList.size(); i++) {
			Shape shape = this.shapeList.get(i);
			fill(Integer.parseInt(shape.getRgb()[0].trim()), Integer.parseInt(shape.getRgb()[1].trim()), Integer.parseInt(shape.getRgb()[2].trim()));	//도형 색상 지정
			rect(shape.getWidthLocation(), shape.getHeightLocation(), shape.getSizeX(), shape.getSizeY());	// 도형 그리기
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