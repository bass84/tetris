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
		
		for(int i = 1; i < (backgroundWidth / blockLength) + 1; i++) isUsed[i][backgroundHeight / blockLength] = true;	//	가장 아랫줄 true
		for(int i = 0; i < (backgroundHeight / blockLength) + 1; i++) isUsed[0][i] = true;	//	가장 왼쪽 줄 true
			
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		int x1 = shape.getWidthLocation() / blockLength;
		int x2 = x1 + (shape.getSizeX() / blockLength);
		int y1 = shape.getHeightLocation() / blockLength;
		int y2 = y1 + (shape.getSizeY() / blockLength);
		
		
		this.back.init();
		this.shapeList.drawShapeList();
		this.drawShapes(x1, x2, y1, y2);
		
	}
	

	public void keyPressed() {	// 키 이벤트
		switch(keyCode) {
			case(37) :	//left
				this.shape.drawShape(Direction.left);
				break;
			case(38) :	//up 
				break;
			case(39) :	//right 
				this.shape.drawShape(Direction.right);
				break;
			case(40) :	//down
				this.shape.drawShape(Direction.down);
				break;
		}
	}
	
	private void drawShapes(int x1, int x2, int y1, int y2) {	// 도형이 바닥에 붙었는지 체크해서 붙었으면 새 도형 생성하고 아니면 현재 도형을 계속 그린다.
		for(int i = x1; i < x2; i ++) {
			for(int j = y1; j < y2; j ++) {
				if(isUsed[i + 1][j + 1]) {
					this.addUsed(x1, x2, y1, y2);
					this.shapeList.addShape(this.shape);
					this.shape = new Shape(this);
					return;
				}
			}
		}
		this.shape.drawShape(Direction.nothing);
	}

	private void addUsed(int x1, int x2, int y1, int y2) {	// 바닥에 붙었으면 boolean 값을 추가한다.
		for(int i = x1; i < x2; i ++) {
			for(int j = y1; j < y2; j ++) {
				isUsed[i + 1][j] = true;
			}
		}
		
	}
	
}