package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {
	private PApplet pApplet;
	private int[][] shapeInfo;
	private Kind shapeKind;
	private int rotationIndex = 0;
	
	public Shape(PApplet pApplet) {
		int randomNum = new Random().nextInt(Kind.values().length);
		this.pApplet = pApplet;
		for(Kind kind : Kind.values()) {
			if(kind.ordinal() == randomNum) this.shapeKind = kind; 
		}
		this.shapeInfo = new ShapeMapping().getShapeInfo(this.shapeKind);
		//this.shapeInfo = new ShapeMapping().getShapeInfo(new Random().nextInt(Kind.values().length));
	}
	
	public int[][] getShapeInfo() {
		return this.shapeInfo;
	}
	public Kind getShapeKind() {
		return this.shapeKind;
	}
	public int getRotationIndex() {
		return this.rotationIndex;
	}
	public void setRotationIndex(int rotationIndex) {
		this.rotationIndex = rotationIndex;
	}
	
	public void downOneBlock() {
		for(int i = 0; i < this.shapeInfo.length; i++) this.shapeInfo[i][1] += 1;
	}
	
	public void move(Direction direction) {
		switch(direction) {
			case left :
				for(int i = 0; i < this.shapeInfo.length; i++) this.shapeInfo[i][0] -= 1;
				break;
			case right :
				for(int i = 0; i < this.shapeInfo.length; i++) this.shapeInfo[i][0] += 1;
				break;
			case down :
				for(int i = 0; i < this.shapeInfo.length; i++) this.shapeInfo[i][1] += 1;
				break;
		}
	}
	
	public void rotate(int[] rotatedLocation) {
		switch(this.shapeKind) {
			case I :
				for(int i = 0; i < this.shapeInfo.length; i++) {
					this.shapeInfo[i][0] = rotatedLocation[0];
					this.shapeInfo[i][1] = rotatedLocation[1];
					
					if(this.rotationIndex == 0) rotatedLocation[1]++;
					else rotatedLocation[0]++;
				}
				this.rotationIndex = this.rotationIndex == 0 ? 1 : 0; 
				break;
			case J :
				
				break;
			case L :
				
				break;
			case O :
				
				break;
			case S :
				
				break;
			case T :
				
				break;
			case Z :
				
				break;
		}
	}
	
}
