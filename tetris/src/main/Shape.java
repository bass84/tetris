package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape extends MainController{

	private int shapeOrder;	//매핑되는 도형번호
	private ShapeMapping mapping;
	private String[] param;	// 도형에 대한 파라미터
	private PApplet pApplet;
	private String[] rgb = new String[3];	// 도형색상
	private int widthLocation;	//좌우 위치
	private int heightLocation;	//높이 위치
	private int downTerm = 60;	//내려오는 속도
	private int sizeX;	// 도형 좌우사이즈
	private int sizeY;	// 도형 높이사이즈 
	//private int backgroundWidth;	//배경 좌우사이즈
	//private int backgroundHeight;	//배경 높이사이즈
	//private int moveLength;
	
	
	public Shape(PApplet p) {
		this.pApplet = p;
		this.shapeOrder = new Random().nextInt(Kind.values().length);
		this.mapping = new ShapeMapping(shapeOrder);
		this.param = this.mapping.getShapeParam().split(",");
		this.widthLocation = Integer.parseInt(this.param[3].trim());
		this.heightLocation = Integer.parseInt(this.param[4].trim());
		this.sizeX = Integer.parseInt(this.param[5].trim());
		this.sizeY = Integer.parseInt(this.param[6].trim());
		//this.backgroundWidth = backgroundWidth;
		//this.backgroundHeight = backgroundHeight;
		//this.moveLength = MainController.blockLength;
	}
	
	
	public void drawShape(boolean[][] isUsed, Direction direction) {
		System.arraycopy(param, 0, rgb, 0, 3);
		pApplet.fill(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));	//도형 색상 지정
		
		this.fallDownShape(isUsed, direction);
		//this.moveShape();
		//this.changeShape();
		
		
		pApplet.rect(this.widthLocation, this.heightLocation, this.sizeX, this.sizeY);
	}


	private void fallDownShape(boolean[][] isUsed, Direction direction) {
		if(!this.getUsedShape(isUsed)) {
			this.heightLocation += (direction == Direction.down ? MainController.blockLength : 0);
			
			if(pApplet.frameCount % this.downTerm == 0) {
				this.heightLocation += MainController.blockLength;
			}
		}
		
	}


	private boolean getUsedShape(boolean[][] isUsed) {
		for(int i = this.widthLocation; i < this.widthLocation + this.sizeX; i += MainController.blockLength) {
			for(int j = this.heightLocation; j < this.heightLocation + this.sizeY; j += MainController.blockLength) {
				if(isUsed[i / MainController.blockLength][(j / MainController.blockLength) + 1]) return true;
			}
		}
		return false;
	}
	
	
	
	
}
