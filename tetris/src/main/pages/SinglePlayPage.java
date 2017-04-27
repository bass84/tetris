package main.pages;

import main.Grid;
import main.Shape;
import main.ShapeMapping.Kind;
import pages.PlayPage;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class SinglePlayPage extends PlayPage{

	private Shape shape;
	private Grid grid;
	private int totalScore = 0;
	private int term = 60;
	private int[][] usedBlock = new int[11][16];
	
	public SinglePlayPage(PApplet pApplet) {
		this.shape = new Shape(pApplet);
		this.grid = new Grid(pApplet);
		
		for(int i = 0; i < this.usedBlock.length; ++i) this.usedBlock[i][15] = -1;
		for(int i = 0; i < this.usedBlock[0].length; ++i) this.usedBlock[0][i] = -1;
	}
	
	public Shape getShape() {
		return this.shape;
	}
	public int[][] getUsedBlock() {
		return this.usedBlock;
	}
	public Grid getGrid() {
		return this.grid;
	}
	public int getTotalScore() {
		return this.totalScore;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public void setUsedBlock(int[][] usedBlock) {
		for(int i = 0; i < usedBlock.length; i++) {
			for(int j = 0; j < usedBlock[i].length; j++) {
				this.usedBlock[i][j] = usedBlock[i][j];
			}
		}
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public void drawPage(PApplet pApplet, PFont mono) throws Exception{
			
			if(grid.isBottom(this.usedBlock, this.shape)) {
				this.increaseTotalScore(1000);
				this.addUsedBlock(shape, usedBlock);
				this.shape = new Shape(pApplet);
				this.usedBlock = this.grid.getNewGridLine(this.usedBlock, this.shape, this);
			}else{
				pApplet.clear();
				this.grid.drawShape(this.usedBlock, this.shape);
				this.shape.drawShape(this.usedBlock, shape);
				if(pApplet.frameCount % this.term == 0) this.shape.increasePositionY();
			}
			
			// draw text
			mono = pApplet.createFont("mono", 15);
			pApplet.textFont(mono);
			pApplet.fill(255, 255, 255);
			pApplet.textAlign(PConstants.LEFT, PConstants.CENTER);
			pApplet.text("SCORE : " + totalScore, 12, 30);	
		
		
	}
	
	
	public void increaseTotalScore(int addScore) {
		this.totalScore += addScore;
	}

	public void addUsedBlock(Shape shape, int[][] usedBlock) {
		int positionX = shape.getPositionX();
		int positionY = shape.getPositionY();
		int shapeColor = shape.getShapeColor();
		int shapeInfo[][] = shape.getShapeInfo();
		for(int i = 0; i < shapeInfo.length; i++) {
			usedBlock[shapeInfo[i][0] + 1 + positionX][shapeInfo[i][1] - 1  + positionY] = shapeColor;
		}
	}
	
	
	public void reset() {
		this.totalScore = 0;
		for(int i = 0; i < this.usedBlock.length; ++i) {
			for(int j = 0; j < this.usedBlock[i].length; ++j) {
				if(i == 0 || j == 15) this.usedBlock[i][j] = -1;
				else this.usedBlock[i][j] = 0;
			}
		}
	}

	@Override
	public void keyPressed(int keyCode) {
		if(this.grid.isBottom(this.usedBlock, this.shape)) return;
			switch(keyCode) {
				case(37) :	//left
					if(!this.grid.isLeftEnd(this.usedBlock, this.shape)) this.shape.decreasePositionX();
					break;
				
				case(38) :	//up
					if(this.shape.getShapeKind() == Kind.O || !this.grid.isPossibleRotation(this.usedBlock, this.shape)) return;
					this.shape.rotate();
					this.shape.increaseRotationIdx();
					break;
				
				case(39) :	//right
					if(!this.grid.isRightEnd(this.usedBlock, this.shape)) this.shape.increasePositionX();
					break;
				
				case(40) :	//down
					if(!this.grid.isBottom(this.usedBlock, this.shape)) this.shape.increasePositionY();
					break;
				
				case(80) :	// 'p' - pause
					//this.gameStatus.setGameStatus(Status.pause);
					break;
			}
		}
	

}
