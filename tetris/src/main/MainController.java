package main;


import processing.core.PApplet;

public class MainController extends PApplet{

	private Background back;
	private Shape shape;
	public final static int backgroundWidth = 500;	//너비
	public final static int backgroundHeight = 700;	//깊이
	public final static int blockLength = 25;	//블럭 한개 크기
	public static boolean[][] isUsed = new boolean[(backgroundWidth / blockLength) + 1][(backgroundHeight / blockLength) + 1];	// 각 블럭 boolean값
	private ShapeList shapeList;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
    }

	public void settings(){
		size(this.backgroundWidth, this.backgroundHeight);	//전체 크기 설정
    }
	
	public void setup(){
		back = new Background(this);
		shapeList = new ShapeList(this);	//도형 리스트 생성
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
		shapeList.drawShapeList();
		
		if(shape.isBottom(Direction.nothing)) {
			shapeList.addShape(shape);
			shape = new Shape(this);
		}
		
	}


	public void keyPressed() {
		switch(keyCode) {
			case(37) :	//left
				shape.isBottom(Direction.left);
				break;
			case(38) :	//up 
				break;
			case(39) :	//right 
				shape.isBottom(Direction.right);
				break;
			case(40) :	//down
				shape.isBottom(Direction.down);
				break;
		}
	}
	
}