package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape implements BlockDraw{
	private PApplet pApplet;
	private int[][] shapeInfo;
	private ShapeMapping shapeMapping;
	private Kind shapeKind;
	private int positionX;
	private int positionY;
	private int rotationLimit;
	private int curRotationIdx;
	
	public Shape(PApplet pApplet) {
		this.pApplet = pApplet;
		int kindIndex = new Random().nextInt(Kind.values().length);
		for(Kind kind : Kind.values()) {
			if(kind.ordinal() == kindIndex) this.shapeKind = kind; 
		}
		this.shapeMapping = new ShapeMapping();
		this.shapeInfo = this.shapeMapping.getShapeInfo(this.shapeKind)[0];
		this.positionX = this.shapeMapping.getMovingValue(this.shapeKind)[0];
		this.positionY = this.shapeMapping.getMovingValue(this.shapeKind)[1];
		this.rotationLimit = this.shapeMapping.getRotationLimit(this.shapeKind);
		this.curRotationIdx = 0;
	}
	
	public int[][] getShapeInfo() {
		return this.shapeInfo;
	}
	public Kind getShapeKind() {
		return this.shapeKind;
	}
	public int getPostiionX() {
		return this.positionX;
	}
	public int getPostiionY() {
		return this.positionY;
	}
	public void increasePositionX() {
		++this.positionX;
	}
	public void decreasePositionX() {
		--this.positionX;
	}
	public void increasePositionY() {
		++this.positionY;
	}
	public int getNextRotationIdx() {
		return this.curRotationIdx == this.rotationLimit ? 0 : this.curRotationIdx + 1;
	}
	public void increaseRotationIdx() {
		this.curRotationIdx = this.curRotationIdx == this.rotationLimit ? 0 : ++this.curRotationIdx;
	}
	public void rotate() {
		this.shapeInfo = this.getNextShapeInfo();
	}
	
	public boolean isBottom(boolean[][] usedBlock){
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if((this.shapeInfo[i][0] + positionX) < 10 
					&& usedBlock[this.shapeInfo[i][0] + 1 + this.positionX][this.shapeInfo[i][1] + this.positionY]) return true;
		}
		return false;
	}
	
	public boolean isLeftEnd(boolean[][] usedBlock) {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if(usedBlock[this.shapeInfo[i][0] + positionX][this.shapeInfo[i][1] + positionY]) return true;
		}
		return false;
	}
	
	public boolean isRightEnd(boolean[][] usedBlock) {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if((this.shapeInfo[i][0] + this.positionX) == 9 
					|| usedBlock[this.shapeInfo[i][0] + 2 + this.positionX][this.shapeInfo[i][1] + this.positionY]) return true;
		}
		return false;
	}
	
	public boolean isPossibleRotation(boolean[][] usedBlock) {
		int newX = 0;
		int newY = 0;
		int[][] nextShapeInfo = this.getNextShapeInfo();
		
		for(int i = 0; i < nextShapeInfo.length; i++) {
			newX = nextShapeInfo[i][0] + this.positionX + 1;
			newY = nextShapeInfo[i][1] + this.positionY;
			if(newX < 1 || newX > 10 || newY < 0 || newY > 14 ) return false;
			if(usedBlock[newX][newY]) return false;
		}
		return true;
	}
	
	
	public int[][] getNextShapeInfo() {
		switch(this.shapeKind) {
			case I :
			case S :
			case Z :
			case J :
			case L :
			case T :
				return this.shapeMapping.getShapeInfo(this.shapeKind)[this.getNextRotationIdx()];
		}
		return null;
	}

	@Override
	public void drawShape(boolean[][] usedBlock) {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			pApplet.fill(0, 0, 255);
			pApplet.rect(
					(this.shapeInfo[i][0] + this.positionX) * MainController.block
					, (this.shapeInfo[i][1] + this.positionY) * MainController.block
					, MainController.block
					, MainController.block);
		}
		
		
		
	}	
	
}
