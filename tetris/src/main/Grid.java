package main;

import java.util.Arrays;

import processing.core.PApplet;

public class Grid implements BlockDraw{
	private PApplet pApplet;
	private boolean[][] currentUsedBlock = new boolean[11][16];
	
	public Grid(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	public void addShape(int[][] shapeInfo, int positionX, int positionY) {
		for(int i = 0; i < shapeInfo.length; i++) {
			this.currentUsedBlock[shapeInfo[i][0] + positionX][shapeInfo[i][1] + positionY - 1] = true;
		}
	}
	@Override
	public void drawShape(boolean[][] usedBlock) {
		for(int i = 0; i < this.currentUsedBlock.length; i++) {
			for(int j = 0; j < this.currentUsedBlock[i].length; j++) {
				if(this.currentUsedBlock[i][j]) {
					pApplet.fill(0, 0, 255);
					pApplet.rect(
							(i * MainController.block) 
							, (j * MainController.block) 
							, MainController.block
							, MainController.block);
				}
			}
		}
	}

}
