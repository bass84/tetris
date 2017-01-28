package main;


import java.util.Arrays;

import processing.core.PApplet;

public class MainController extends PApplet{
	public final static int block = 40;
	private boolean[][] usedBlock = new boolean[13][18];
	private Shape shape;
	private int term = 60;
	private int[][] shapeInfo;
	private Grid grid;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
    }

	public void settings(){
		width = 480;
		height = 680;
		size(width, height);	//전체 크기 설정
    }
	
	public void setup(){
		this.shape = new Shape(this);
		this.shapeInfo = this.shape.getShapeInfo();
		this.grid = new Grid(this);
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][17] = true;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = true;
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		
		if(this.isBottom()) {
			this.addUsedBlock();
			this.grid.addShape(this.shapeInfo);
			this.shape = new Shape(this);
			this.shapeInfo = this.shape.getShapeInfo();
		}else{
			this.drawShape();
			if(frameCount % this.term == 0) this.shape.downOneBlock();
		}
		
	}
		

	private void addUsedBlock() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			this.usedBlock[this.shapeInfo[i][0] + 1][this.shapeInfo[i][1] - 1] = true;
		}
		
		
	}
	
	private void drawShape() {
		clear();
		this.grid.drawShapeList();
		
		for(int i = 0; i < this.shapeInfo.length; i++) {
			fill(0, 0, 255);
			rect(this.shapeInfo[i][0] * block, this.shapeInfo[i][1] * block, block, block);
		}
		
	}
	
	private boolean isBottom(){
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if(this.usedBlock[this.shapeInfo[i][0] + 1][this.shapeInfo[i][1]]) return true;
		}
		return false;
	}
	
	private boolean isLeftEnd() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if(this.usedBlock[this.shapeInfo[i][0]][this.shapeInfo[i][1]]) return true;
		}
		return false;
	}
	
	private boolean isRightEnd() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if(this.shapeInfo[i][0] == 11 || this.usedBlock[this.shapeInfo[i][0] + 1][this.shapeInfo[i][1]]) return true;
		}
		return false;
	}
	
	
	public void keyPressed() {	// 키 이벤트
		if(!this.isBottom()) {
			switch(keyCode) {
			case(37) :	//left
				if(!this.isLeftEnd()) this.shape.move(Direction.left);
			break;
			case(38) :	//up 
				break;
			case(39) :	//right
				if(!this.isRightEnd()) this.shape.move(Direction.right);
			break;
			case(40) :	//down
				this.shape.move(Direction.down);
			break;
			}
			
		}
	
	}
	
	
	
}