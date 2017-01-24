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
	
	public Shape(PApplet p) {
		this.pApplet = p;
		this.shapeOrder = new Random().nextInt(Kind.values().length);
		this.mapping = new ShapeMapping(shapeOrder);
		this.param = this.mapping.getShapeParam().split(",");
		this.widthLocation = Integer.parseInt(this.param[3].trim());
		this.heightLocation = Integer.parseInt(this.param[4].trim());
		this.sizeX = Integer.parseInt(this.param[5].trim());
		this.sizeY = Integer.parseInt(this.param[6].trim());
	}
	
	
	public boolean drawShape(Direction direction) {
		System.arraycopy(param, 0, rgb, 0, 3);
		pApplet.fill(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));	//도형 색상 지정
		
		boolean isCannotDown = false;
		isCannotDown = this.fallDownShape(direction) == true ?  true : false;
		//this.changeShape();
		
		pApplet.rect(this.widthLocation, this.heightLocation, this.sizeX, this.sizeY);
		
		return isCannotDown;
	}


	private boolean fallDownShape(Direction direction) {
		if(this.getUsedShape() == Direction.cannotDown) {
			System.out.println("cannotDown");
			return true;
		}
		else if(this.getUsedShape() == Direction.nothing) this.widthLocation += (direction == Direction.left ? (MainController.blockLength) * -1 : direction == Direction.right ? (MainController.blockLength) : 0);
		else if(this.getUsedShape() == Direction.cannotLeft) this.widthLocation += (direction == Direction.right ? (MainController.blockLength) : 0);
		else if(this.getUsedShape() == Direction.cannotRight) this.widthLocation += (direction == Direction.left ? (MainController.blockLength) * -1 : 0);
		
		this.heightLocation += (direction == Direction.down ? MainController.blockLength : 0);
		if(pApplet.frameCount % this.downTerm == 0) this.heightLocation += MainController.blockLength;
		
		return false;
	}

	private Direction getUsedShape() {
		for(int i = this.widthLocation; i < this.widthLocation + this.sizeX; i += MainController.blockLength) {
			for(int j = this.heightLocation; j < this.heightLocation + this.sizeY; j += MainController.blockLength) {
				if(isUsed[(i / MainController.blockLength) + 1][(j / MainController.blockLength) + 1]) {
					this.addUser(this.widthLocation, this.sizeX, this.heightLocation, this.sizeY);
					return Direction.cannotDown;
				}
				else if(isUsed[i / MainController.blockLength][(j / MainController.blockLength)]) return Direction.cannotLeft;
				else if(isUsed[i / MainController.blockLength][(j / MainController.blockLength)] || (i / MainController.blockLength) + 1 == 20) return Direction.cannotRight;
				
				
			}
		}
		return Direction.nothing;
	}


	private void addUser(int widthLocation, int sizeX, int heightLocation, int sizeY) {
		for(int i = this.widthLocation; i < this.widthLocation + this.sizeX; i += MainController.blockLength) {
			for(int j = this.heightLocation; j < this.heightLocation + this.sizeY; j += MainController.blockLength) {
				MainController.isUsed[i / MainController.blockLength + 1][j / MainController.blockLength] = true;
			}
		}
		
	}
	
	
	
	
}
