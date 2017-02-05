package main;


import java.util.Arrays;

import main.ShapeMapping.Kind;
import processing.core.PApplet;

public class MainController extends PApplet{
	public final static int block = 40;
	private boolean[][] usedBlock = new boolean[11][16];
	private Shape shape;
	private int term = 60;
	private int[][] shapeInfo;
	private Grid grid;
	private int[][] newShapeInfo;
	
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
		this.newShapeInfo = new int[4][2];
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = true;
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
		for(int i = 0; i < this.shapeInfo.length; i++) this.usedBlock[this.shapeInfo[i][0] + 1][this.shapeInfo[i][1] - 1] = true;
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
			if(this.shapeInfo[i][0] < 10 && this.usedBlock[this.shapeInfo[i][0] + 1][this.shapeInfo[i][1]]) return true;
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
			if(this.shapeInfo[i][0] == 9 || this.usedBlock[this.shapeInfo[i][0] + 1][this.shapeInfo[i][1]]) return true;
		}
		return false;
	}
	private boolean isPossibleRotation() {
		this.makeRotatedLocation();
		for(int i = 0; i < this.shapeInfo.length; i++) {
			System.out.println(Arrays.toString(this.shapeInfo[i]));
		}
		for(int i = 0; i < this.newShapeInfo.length; i++) {
			System.out.println("new" + Arrays.toString(this.newShapeInfo[i]));
		}
		
		int x;
		int y;
		
		for(int i = 0; i < this.newShapeInfo.length; i++) {
			x = this.newShapeInfo[i][0] + 1;
			y = this.newShapeInfo[i][1];
			
			if(x < 1 || x > 10) return false;
			if(y < 0 || y > 14) return false;
			if(this.usedBlock[x][y]) return false;
		}
		
		return true;
	}
	
	private void makeRotatedLocation() {
		int rotationIndex = this.shape.getRotationIndex();
		
		switch(this.shape.getShapeKind()) {
			case I :
				for(int i = 0; i < this.shapeInfo.length; i++) {
					if(rotationIndex == 0) {
						this.newShapeInfo[i][0] = this.shapeInfo[1][0];
						this.newShapeInfo[i][1] = (this.shapeInfo[0][1] - 2) + i;
					}
					else {
						this.newShapeInfo[i][0] = (this.shapeInfo[0][0] - 1) + i;
						this.newShapeInfo[i][1] = this.shapeInfo[2][1];
					}
				}
				break;
			case J :
				if(rotationIndex != 3) System.arraycopy(this.shapeInfo, 0, this.newShapeInfo, 2, 2);
				else System.arraycopy(this.shapeInfo, 0, this.newShapeInfo, 0, 2);
				if(rotationIndex == 0) {
					for(int i = 2; i < this.shapeInfo.length; i++) {
						this.newShapeInfo[i - 2][0] = this.shapeInfo[i][0] - 1;
						this.newShapeInfo[i - 2][1] = this.shapeInfo[i][1] - 2;
					}
				}else if(rotationIndex == 1) {
					for(int i = 2; i < this.shapeInfo.length; i++) {
						this.newShapeInfo[i - 2][0] = this.shapeInfo[i][0] + 2;
						this.newShapeInfo[i - 2][1] = this.shapeInfo[i][1] - 1;
					}
				}else if(rotationIndex == 2) {
					for(int i = 2; i < this.shapeInfo.length; i++) {
						this.newShapeInfo[i - 2][0] = this.shapeInfo[i][0] + 1;
						this.newShapeInfo[i - 2][1] = this.shapeInfo[i][1] + 2;
					}
				}else {
					for(int i = 0; i < this.shapeInfo.length - 2; i++) {
						this.newShapeInfo[i][0] = this.shapeInfo[i][0] - 2;
						this.newShapeInfo[i][1] = this.shapeInfo[i][1] - 1;
					}
				}
				break;
			case O :
				break;
			default :
			
			break;
		}
	}
	
	public void keyPressed() {	// 키 이벤트
		if(this.isBottom()) return;
		
		switch(keyCode) {
			case(37) :	//left
				if(!this.isLeftEnd()) this.shape.move(Direction.left);
				break;
			case(38) :	//up
				if(this.isPossibleRotation()) this.shape.rotate(this.newShapeInfo);
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