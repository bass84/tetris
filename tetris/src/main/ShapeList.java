package main;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ShapeList {

	private List<Shape> shapes;
	private PApplet pApplet;
	
	public ShapeList(PApplet p) {
		this.shapes = new ArrayList<Shape>();
		this.pApplet = p;
	}
	
	public void addShape(Shape shape) {
		this.shapes.add(shape);
	}
	
	public void drawShapeList() {
		for(int i = 0; i < this.shapes.size(); i++) {
			Shape shape = this.shapes.get(i);
			pApplet.fill(shape.getRgb()[0], shape.getRgb()[1], shape.getRgb()[2]);	//도형 색상 지정
			pApplet.rect(shape.getWidthLocation(), shape.getHeightLocation(), shape.getSizeX(), shape.getSizeY());	// 도형 그리기
		}
	}
	
	
}
