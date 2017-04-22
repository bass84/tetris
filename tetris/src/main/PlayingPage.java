package main;

import main.ShapeMapping.Kind;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class PlayingPage extends PlayPage{

	//private PlayingPage playingPage = new PlayingPage();
	private Shape shape;
	private Grid grid;
	private static int totalScore = 0;
	private int term = 60;
	private PFont mono;
	private PApplet pApplet;
	private static int[][] usedBlock = new int[11][16];
	
	public PlayingPage() {
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = -1;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = -1;
	}
	
	public void getPlayingPage(boolean isRestart) {
		if(isRestart) {
			totalScore = 0;
			for(int i = 0; i < usedBlock.length; ++i) {
				for(int j = 0; j < usedBlock[i].length; ++j) {
					if(i == 0 || j == 15) usedBlock[i][j] = -1;
					else usedBlock[i][j] = 0;
				}
			}
		}
		
	}
	
	public void setInit(PApplet pApplet) {
		if(this.pApplet == null) {
			this.pApplet = pApplet;
			this.shape = new Shape(this.pApplet);
			this.grid = new Grid(this.pApplet);
		}
		
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
	public void drawPage() throws Exception{
		System.out.println("gamePage drawPage");
		
			if(grid.isBottom(usedBlock, shape)) {
				this.increaseTotalScore(1000);
				this.addUsedBlock(shape, usedBlock);
				this.shape = new Shape(this.pApplet);
				this.usedBlock = this.grid.getNewGridLine(this.usedBlock, this.shape, this);
			}else{
				pApplet.clear();
				this.grid.drawShape(this.usedBlock, this.shape);
				this.shape.drawShape(this.usedBlock, shape);
				if(pApplet.frameCount % this.term == 0) this.shape.increasePositionY();
			}
		
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
	
	/*@Override
	public void drawText() {
		this.mono = pApplet.createFont("mono", 15);
		pApplet.textFont(this.mono);
		pApplet.fill(255, 255, 255);
		pApplet.textAlign(PConstants.LEFT, PConstants.CENTER);
		pApplet.text("SCORE : " + totalScore, 12, 30);
	}*/
	
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
