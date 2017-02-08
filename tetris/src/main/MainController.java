package main;


import java.util.Arrays;

import processing.core.PApplet;

public class MainController extends PApplet{
	public final static int block = 40;
	private boolean[][] usedBlock = new boolean[11][16];
	private Shape shape;
	private int term = 60;
	private int[][] shapeInfo;
	private Grid grid;
	private int shapePositionX;
	private int shapePositionY;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
    }

	public void settings(){
		width = 400;
		height = 600;
		size(width, height);	//전체 크기 설정
    }
	
	public void setup(){
		this.shape = new Shape();
		this.shapeInfo = this.shape.getShapeInfo();
		this.grid = new Grid(this);
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = true;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = true;
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		this.setShapePosition();
		
		if(this.isBottom()) {
			this.addUsedBlock();
			this.grid.addShape(this.shapeInfo, this.shapePositionX, this.shapePositionY);
			this.shape = new Shape();
			this.shapeInfo = this.shape.getShapeInfo();
		}else{
			this.drawShape();
			if(frameCount % this.term == 0) this.shape.increasePositionY();
		}
	}
	
	private void setShapePosition() {
		this.shapePositionX = this.shape.getPostiionX();
		this.shapePositionY = this.shape.getPostiionY();
	}
	
	private void addUsedBlock() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			this.usedBlock[this.shapeInfo[i][0] + 1 + this.shapePositionX][this.shapeInfo[i][1] - 1  + this.shapePositionY] = true;
		}
	}
	
	private void drawShape() {
		clear();
		this.grid.drawShapeList();
		
		for(int i = 0; i < this.shapeInfo.length; i++) {
			fill(0, 0, 255);
			rect((this.shapeInfo[i][0] + this.shapePositionX) * block, (this.shapeInfo[i][1] + this.shapePositionY) * block, block, block);
		}
	}
	
	private boolean isBottom(){
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if((this.shapeInfo[i][0] + shapePositionX) < 10 
					&& this.usedBlock[this.shapeInfo[i][0] + 1 + this.shapePositionX][this.shapeInfo[i][1] + this.shapePositionY]) return true;
		}
		return false;
	}
	
	private boolean isLeftEnd() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if(this.usedBlock[this.shapeInfo[i][0] + this.shapePositionX][this.shapeInfo[i][1] + this.shapePositionY]) return true;
		}
		return false;
	}
	
	private boolean isRightEnd() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if((this.shapeInfo[i][0] + this.shapePositionX) == 9 
					|| this.usedBlock[this.shapeInfo[i][0] + 2 + this.shapePositionX][this.shapeInfo[i][1] + this.shapePositionY]) return true;
		}
		return false;
	}
	private boolean isPossibleRotation() {
		int newX = 0;
		int newY = 0;
		int[][] nextShapeInfo = this.shape.getNextShapeInfo();
		
		for(int i = 0; i < nextShapeInfo.length; i++) {
			newX = nextShapeInfo[i][0] + this.shapePositionX;
			newY = nextShapeInfo[i][1] + this.shapePositionY;
			if(newX < 1 || newX > 10 || newY < 0 || newY > 14 ) return false;
			if(this.usedBlock[newX][newY]) return false;
		}
		return true;
	}
	
	public void keyPressed() {	// 키 이벤트
		if(this.isBottom()) return;
		
		switch(keyCode) {
			case(37) :	//left
				if(!this.isLeftEnd()) this.shape.decreasePositionX();
				break;
			
			case(38) :	//up
				if(!this.isPossibleRotation()) return;
			
				this.shape.rotate();
				this.shape.increaseRotationIdx();
				this.shapeInfo = this.shape.getShapeInfo();
				break;
			
			case(39) :	//right
				if(!this.isRightEnd()) this.shape.increasePositionX();
				break;
			
			case(40) :	//down
				if(!this.isBottom()) this.shape.increasePositionY();
				break;
		}
	
	}

	
	
	
}