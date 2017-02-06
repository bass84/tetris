package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {
	private PApplet pApplet;
	private int[][] shapeInfo;
	private Kind shapeKind;
	private int positionX;
	private int positionY;
	
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
		
	}
	
}
