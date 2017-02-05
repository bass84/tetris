package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {
	private PApplet pApplet;
	private int[][] shapeInfo;
	private Kind shapeKind;
	private int rotationLimit;
	private int rotationIndex = 0;
	
	public Shape(PApplet pApplet) {
		int randomNum = new Random().nextInt(Kind.values().length);
		this.pApplet = pApplet;
		for(Kind kind : Kind.values()) {
			if(kind.ordinal() == randomNum) this.shapeKind = kind; 
		}
		this.shapeInfo = new ShapeMapping().getShapeInfo(this.shapeKind);
		this.rotationLimit = new ShapeMapping().getRotationLimit(this.shapeKind);
		//this.shapeInfo = new ShapeMapping().getShapeInfo(Kind.J);
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
	
	public void rotate(int[][] newShapeInfo) {
		
		for(int i = 0; i < newShapeInfo.length; i++) {
			this.shapeInfo[i][0] = newShapeInfo[i][0];
			this.shapeInfo[i][1] = newShapeInfo[i][1];
		}
		for(int i = 0; i < newShapeInfo.length; i++) {
			System.out.println("new2" + Arrays.toString(newShapeInfo[i]));
		}
		
		this.rotationIndex = this.rotationIndex < this.rotationLimit ? ++this.rotationIndex : 0;
	}
	
}
