package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {
	private int[][] shapeInfo;
	private ShapeMapping shapeMapping;
	private Kind shapeKind;
	private int positionX;
	private int positionY;
	private int rotationLimit;
	private int curRotationIdx;
	
	public Shape() {
		int kindIndex = new Random().nextInt(Kind.values().length);
		for(Kind kind : Kind.values()) {
			if(kind.ordinal() == kindIndex) this.shapeKind = kind; 
		}
		this.shapeMapping = new ShapeMapping();
		this.shapeInfo = this.shapeMapping.getShapeInfo(this.shapeKind);
		this.positionX = this.shapeMapping.getMovingValue(this.shapeKind)[0];
		this.positionY = this.shapeMapping.getMovingValue(this.shapeKind)[1];
		this.rotationLimit = this.shapeMapping.getRotationLimit(this.shapeKind);
		this.curRotationIdx = 0;
		//this.shapeInfo = new ShapeMapping().getShapeInfo(new Random().nextInt(Kind.values().length));
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
	
	
	public int[][] getNextShapeInfo() {
		int[][] nextShapeInfo = new int[4][2];
		
		switch(this.shapeKind) {
			case I :
			case S :
			case Z :
				nextShapeInfo = this.getShapeInfoTwoRotation();
				break;
			case J :
			case L :
			case T :
				nextShapeInfo = this.getShapeInfoFourRotation();
				break;
		}
		return nextShapeInfo;
	}
	
	
	public int[][] getShapeInfoTwoRotation() {
		int nextRotationIdx = this.getNextRotationIdx();
		int[][] newShapeInfo = new int[4][2];
		int newX = 0;
		int newY = 0;
		
		for(int i = 0; i < this.shapeInfo.length; i++) {
			newX = nextRotationIdx == 1 ? this.shapeInfo[i][1] * -1 : this.shapeInfo[i][1];
			newY = this.shapeInfo[i][0];
			newShapeInfo[i][0] = newX;
			newShapeInfo[i][1] = newY;
		}
		return newShapeInfo;
	}	
	
	public int[][] getShapeInfoFourRotation() {
		int nextRotationIdx = this.getNextRotationIdx();
		int[][] newShapeInfo = new int[4][2];
		int newX = 0;
		int newY = 0;
		
		for(int i = 0; i < this.shapeInfo.length; i++) {
			if(nextRotationIdx == 1) {
				newX = this.shapeInfo[i][1] * -1;
				newY = this.shapeInfo[i][0] - 1;
			}else if(nextRotationIdx == 2) {
				newX = (this.shapeInfo[i][1] * -1) - 1;
				newY = this.shapeInfo[i][0] - 1;
			}else if(nextRotationIdx == 3) {
				newX = (this.shapeInfo[i][1] * -1) - 1;
				newY = (this.shapeInfo[i][0] * -1) - 1;
			}else{
				newX = this.shapeInfo[i][1] + 1;
				newY = this.shapeInfo[i][0];
			}
			newShapeInfo[i][0] = newX;
			newShapeInfo[i][1] = newY;
		}
		return newShapeInfo;
	}
}
