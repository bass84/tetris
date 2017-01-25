package main;

import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape {

	private int shapeOrder;	//매핑되는 도형번호
	private ShapeMapping mapping;
	private String[] param;	// 도형에 대한 파라미터
	private PApplet pApplet;
	private int[] rgb;	// 도형색상
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
		this.rgb = new int[3];
		for(int i = 0; i < this.rgb.length; i++) {
			this.rgb[i] = Integer.parseInt(param[i].trim());
		}
	}
	
	public int getWidthLocation() {
		return this.widthLocation;
	}
	public int getHeightLocation() {
		return this.heightLocation;
	}
	public int getSizeX() {
		return this.sizeX;
	}
	public int getSizeY() {
		return this.sizeY;
	}
	public int[] getRgb() {
		return this.rgb;
	}
	
	protected boolean isBottom(Direction direction) {
		for(int i = this.widthLocation; i < this.widthLocation + this.sizeX; i += MainController.blockLength) {
			for(int j = this.heightLocation; j < this.heightLocation + this.sizeY; j += MainController.blockLength) {
				if(MainController.isUsed[(i / MainController.blockLength) + 1][(j / MainController.blockLength) + 1]) {
					this.addUsed();
					return true;
				}
			}
		}
		
		this.moveWidth(direction);
		this.drawShape(direction);
		
		return false;
	}
	
	
	private void drawShape(Direction direction) {
		this.heightLocation += (direction == Direction.down ? MainController.blockLength : 0);
		if(pApplet.frameCount % this.downTerm == 0) this.heightLocation += MainController.blockLength;
		pApplet.fill(rgb[0], rgb[1], rgb[2]);	//도형 색상 지정
		pApplet.rect(this.widthLocation, this.heightLocation, this.sizeX, this.sizeY);
	}
	

	private void addUsed() {
		for(int i = this.widthLocation; i < this.widthLocation + this.sizeX; i += MainController.blockLength) {
			for(int j = this.heightLocation; j < this.heightLocation + this.sizeY; j += MainController.blockLength) {
				MainController.isUsed[i / MainController.blockLength + 1][j / MainController.blockLength] = true;
			}
		}
		
	}
	
	
	private void moveWidth(Direction direction) {
		for(int i = this.widthLocation; i < this.widthLocation + this.sizeX; i += MainController.blockLength) {
			for(int j = this.heightLocation; j < this.heightLocation + this.sizeY; j += MainController.blockLength) {
				if(MainController.isUsed[i / MainController.blockLength][(j / MainController.blockLength)]) {
					this.widthLocation += (direction == Direction.right ? (MainController.blockLength) : 0);
					return;
				}else if(MainController.isUsed[i / MainController.blockLength][(j / MainController.blockLength)] 
						|| (i / MainController.blockLength) + 1 == (MainController.backgroundWidth / MainController.blockLength)) {
					this.widthLocation += (direction == Direction.left ? (MainController.blockLength) * -1 : 0);
					return;
				}
			}
		}
		this.widthLocation += (direction == Direction.left ? (MainController.blockLength) * -1 : direction == Direction.right ? (MainController.blockLength) : 0);
	}
	
	
}
