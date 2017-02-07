package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {
	private PApplet pApplet;
	private int[][] shapeInfo;
	private ShapeMapping shapeMapping;
	private Kind shapeKind;
	private int positionX;
	private int positionY;
	
	public Shape(PApplet pApplet) {
		this.pApplet = pApplet;
		int kindIndex = new Random().nextInt(Kind.values().length);
		for(Kind kind : Kind.values()) {
			if(kind.ordinal() == kindIndex) this.shapeKind = kind; 
		}
		this.shapeMapping = new ShapeMapping();
		this.shapeInfo = this.shapeMapping.getShapeInfo(this.shapeKind);
		this.positionX = this.shapeMapping.getMovingValue(this.shapeKind)[0];
		this.positionY = this.shapeMapping.getMovingValue(this.shapeKind)[1];
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
	
	
	
	public void rotate() {
		for(int i = 0; i < this.shapeInfo.length; i++) {
			int newX = (this.shapeInfo[i][1] * -1) - 1;
			int newY = this.shapeInfo[i][0];
			System.out.println("newX = " + newX);
			System.out.println("newY = " + newY);
			this.shapeInfo[i][0] = newX;
			this.shapeInfo[i][1] = newY;
		}
	}
	
}
