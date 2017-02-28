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
	private int shapeColor;
	
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
		this.shapeColor = this.shapeMapping.getShapeColor(this.shapeKind);
		this.curRotationIdx = 0;
	}
	
	public int[][] getShapeInfo() {return this.shapeInfo;}
	public Kind getShapeKind() {return this.shapeKind;}
	public int getPostiionX() {return this.positionX;}
	public int getPostiionY() {return this.positionY;}
	public void increasePositionX() {++this.positionX;}
	public void decreasePositionX() {--this.positionX;}
	public void increasePositionY() {++this.positionY;}
	public int getNextRotationIdx() {return this.curRotationIdx == this.rotationLimit ? 0 : this.curRotationIdx + 1;}
	public void increaseRotationIdx() {this.curRotationIdx = this.curRotationIdx == this.rotationLimit ? 0 : ++this.curRotationIdx;}
	public void rotate() {this.shapeInfo = this.getNextShapeInfo();}
	public int getShapeColor() {return this.shapeColor;}
	
	
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
	public void drawShape(int[][] usedBlock, int shapeColor) {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			pApplet.fill(shapeColor, 255);
			pApplet.rect(
					(this.shapeInfo[i][0] + this.positionX) * MainController.block
					, (this.shapeInfo[i][1] + this.positionY) * MainController.block
					, MainController.block
					, MainController.block);
		}
		
		
		
	}	
	
}
