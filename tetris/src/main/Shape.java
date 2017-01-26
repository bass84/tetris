package main;

import java.util.Arrays;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {
	private PApplet pApplet;
	private int[][] shapeInfo;
	
	public Shape(PApplet pApplet) {
		this.pApplet = pApplet;
		this.shapeInfo = new ShapeMapping().getShapeInfo(new Random().nextInt(Kind.values().length));
		
	}
	
	public int[][] getShapeInfo() {
		return this.shapeInfo;
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
	
}
