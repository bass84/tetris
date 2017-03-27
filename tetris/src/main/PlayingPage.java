package main;

import processing.core.PApplet;
import processing.core.PFont;

public class PlayingPage implements GamePage{

	private static GamePage playingPage = new PlayingPage();
	private Shape shape;
	private Grid grid;
	private int totalScore = 0;
	private int term = 60;
	private PFont mono;
	private PApplet pApplet;
	private int[][] usedBlock;
	
	private PlayingPage() {
		this.usedBlock = new int[11][16];
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = -1;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = -1;
	}
	
	public static synchronized GamePage getPlayingPage(boolean isRestart) {
		if(playingPage == null || isRestart) playingPage = new PlayingPage();
		return playingPage;
	}
	
	@Override
	public void setPApplet(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	@Override
	public void drawPage() {
		try {
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
		}catch(Exception e) {
			this.reset();
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
	
	@Override
	public void drawText(PFont mono) {
		pApplet.textFont(mono);
		pApplet.fill(255, 255, 255);
		pApplet.textAlign(pApplet.LEFT, pApplet.CENTER);
		pApplet.text("SCORE : " + totalScore, 12, 30);
		
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
	
}
