package main;

import java.util.Arrays;

import processing.core.PApplet;

public class Grid {
	private PApplet pApplet;
	private boolean[][] currentUsedBlock = new boolean[10][15];
	
	public Grid(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	public void addShape(int[][] shapeInfo, int positionX, int positionY) {
		for(int i = 0; i < shapeInfo.length; i++) {
			this.currentUsedBlock[shapeInfo[i][0] + positionX][shapeInfo[i][1] - 1 + positionY] = true;
		}
	}

	public void drawShapeList() {
		for(int i = 0; i < this.currentUsedBlock.length; i++) {
			for(int j = 0; j < this.currentUsedBlock[i].length; j++) {
				if(this.currentUsedBlock[i][j]) {
					pApplet.fill(0, 0, 255);
					pApplet.rect(i * MainController.block, j * MainController.block, MainController.block, MainController.block);
				}
			}
		}
	}

}
