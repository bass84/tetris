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
		size(backgroundWidth, backgroundHeight);	//전체 크기 설정
    }
	
	public void setup(){
		this.back = new Background(this);
		this.shapeList = new ShapeList(this);	//도형 리스트 생성
		this.shape = new Shape(this);	//도형 생성
		
		for(int i = 1; i < (backgroundWidth / blockLength) + 1; i++){	//	가장 아랫줄 true
			isUsed[i][backgroundHeight / blockLength] = true;
		}
		for(int i = 0; i < (backgroundHeight / blockLength) + 1; i++) {	//	가장 왼쪽 줄 true
			isUsed[0][i] = true;
		}
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		this.back.init();
		this.shapeList.drawShapeList();
		
		if(this.shape.isBottom(Direction.nothing)) {
			this.shapeList.addShape(this.shape);
			this.shape = new Shape(this);
		}
		
	}


	public void keyPressed() {
		switch(keyCode) {
			case(37) :	//left
				this.shape.isBottom(Direction.left);
				break;
			case(38) :	//up 
				break;
			case(39) :	//right 
				this.shape.isBottom(Direction.right);
				break;
			case(40) :	//down
				this.shape.isBottom(Direction.down);
				break;
		}
	}
	
}