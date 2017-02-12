package main;

import processing.core.PApplet;

public class MainController extends PApplet{
	public final static int block = 40;
	private boolean[][] usedBlock = new boolean[11][16];
	private Shape shape;
	private int term = 60;
	private int[][] shapeInfo;
	private Grid grid;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
		
    }

	public void settings(){
		width = 400;
		height = 600;
		size(width, height);	//전체 크기 설정
    }
	
	public void setup(){
		this.shape = new Shape(this);
		this.shapeInfo = this.shape.getShapeInfo();
		this.grid = new Grid(this);
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = true;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = true;
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		if(this.shape.isBottom(this.usedBlock)) {
			this.addUsedBlock(this.shape.getPostiionX(), this.shape.getPostiionY());
			this.grid.addShape(this.shapeInfo, this.shape.getPostiionX(), this.shape.getPostiionY());
			this.shape = new Shape(this);
			this.shapeInfo = this.shape.getShapeInfo();
		}else{
			clear();
			this.grid.drawShape(this.usedBlock);
			this.shape.drawShape(usedBlock);
			if(frameCount % this.term == 0) this.shape.increasePositionY();
		}
	}
	
	public void addUsedBlock(int positionX, int positionY) {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			this.usedBlock[this.shapeInfo[i][0] + 1 + positionX][this.shapeInfo[i][1] - 1  + positionY] = true;
		}
	}
	
	public void keyPressed() {	// 키 이벤트
		if(this.shape.isBottom(this.usedBlock)) return;
		
		switch(keyCode) {
			case(37) :	//left
				if(!this.shape.isLeftEnd(this.usedBlock)) this.shape.decreasePositionX();
				break;
			
			case(38) :	//up
				if(!this.shape.isPossibleRotation(this.usedBlock)) return;
			
				this.shape.rotate();
				this.shape.increaseRotationIdx();
				this.shapeInfo = this.shape.getShapeInfo();
				break;
			
			case(39) :	//right
				if(!this.shape.isRightEnd(this.usedBlock)) this.shape.increasePositionX();
				break;
			
			case(40) :	//down
				if(!this.shape.isBottom(this.usedBlock)) this.shape.increasePositionY();
				break;
		}
	
	}

	
	
	
}