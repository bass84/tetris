package main;

import processing.core.PApplet;
import processing.core.PFont;

public class MainController extends PApplet{
	public final static int block = 40;
	private int[][] usedBlock = new int[11][16];
	private Shape shape;
	private int term = 60;
	private Grid grid;
	private int totalScore = 0;
	private PFont mono;	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
	}

	public void settings(){
		size(400, 600);	//전체 크기 설정
    }
	
	public void setup(){
		this.mono = createDefaultFont(15);
		this.shape = new Shape(this);
		this.grid = new Grid(this);
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = -1;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = -1;
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		try {
			if(this.grid.isBottom(this.usedBlock, this.shape)) {
				this.increaseTotalScore(1000);
				this.addUsedBlock();
				this.shape = new Shape(this);
				this.usedBlock = this.grid.getNewGridLine(this.usedBlock, this.shape);
			}else{
				clear();
				this.grid.drawShape(this.usedBlock, this.shape);
				this.shape.drawShape(usedBlock, this.shape);
				if(frameCount % this.term == 0) this.shape.increasePositionY();
			}
			this.drawScore("SCORE");
		}catch(Exception e) {
			background(0);
			this.drawScore("GAME OVER");
			return;
		}
	}
	
	public void drawScore(String text) {
		textFont(mono);
		fill(255, 255, 255);
		if(text.equals("SCORE")) {
			text("SCORE : " + totalScore, 12, 30);
		}else{
			this.mono = createDefaultFont(50);
			text("GAME OVER", 55, 280);
		}
	}
	
	public void addUsedBlock() {
		int positionX = this.shape.getPostitionX();
		int positionY = this.shape.getPostitionY();
		int shapeColor = this.shape.getShapeColor();
		int shapeInfo[][] = this.shape.getShapeInfo();
		for(int i = 0; i < shapeInfo.length; i++) {
			this.usedBlock[shapeInfo[i][0] + 1 + positionX][shapeInfo[i][1] - 1  + positionY] = shapeColor;
		}
	}
	
	public void increaseTotalScore(int addScore) {
		this.totalScore += addScore;
	}
	
	public void keyPressed() {	// 키 이벤트
		if(this.grid.isBottom(this.usedBlock, this.shape)) return;
		
		switch(keyCode) {
			case(37) :	//left
				if(!this.grid.isLeftEnd(this.usedBlock, this.shape)) this.shape.decreasePositionX();
				break;
			
			case(38) :	//up
				if(this.shape.getShapeKind().toString().equals("O") || !this.grid.isPossibleRotation(this.usedBlock, this.shape)) return;
			
				this.shape.rotate();
				this.shape.increaseRotationIdx();
				break;
			
			case(39) :	//right
				if(!this.grid.isRightEnd(this.usedBlock, this.shape)) this.shape.increasePositionX();
				break;
			
			case(40) :	//down
				if(!this.grid.isBottom(this.usedBlock, this.shape)) this.shape.increasePositionY();
				break;
		}
	
	}

	
	
	
}