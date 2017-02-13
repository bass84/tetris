package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import processing.core.PApplet;

public class Grid implements BlockDraw{
	private PApplet pApplet;
	//private boolean[][] currentUsedBlock = new boolean[11][16];
	
	public Grid(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	/*public void addShape(int[][] shapeInfo, int positionX, int positionY) {
		for(int i = 0; i < shapeInfo.length; i++) {
			this.currentUsedBlock[shapeInfo[i][0] + positionX][shapeInfo[i][1] + positionY - 1] = true;
		}
	}*/
	@Override
	public void drawShape(boolean[][] usedBlock) {
		/*for(int i = 0; i < this.currentUsedBlock.length; i++) {
			for(int j = 0; j < this.currentUsedBlock[i].length; j++) {
				if(this.currentUsedBlock[i][j]) {
					pApplet.fill(0, 0, 255);
					pApplet.rect(
							(i * MainController.block) + MainController.block
							, (j * MainController.block) + MainController.block
							, MainController.block
							, MainController.block);
				}
			}
		}*/
		for(int i = 1; i < usedBlock.length; i++) {
			for(int j = 0; j < usedBlock[i].length - 1; j++) {
				if(usedBlock[i][j]) {
					pApplet.fill(0, 0, 255);
					pApplet.rect(
							(i * MainController.block) - MainController.block 
							, (j * MainController.block)
							, MainController.block
							, MainController.block);
				}
			}
		}
	}

	public boolean[][] getNewGridLine(boolean[][] usedBlock) {
		Set<Integer> removeLines = new HashSet<Integer>();
		
		for(int i = usedBlock[0].length - 2; i >= 0; i--) {
			boolean blockEmpty = false;
			for(int j = 1; j < usedBlock.length; j++) {
				if(!usedBlock[j][i]) {
					blockEmpty = true;
					break;
				}
			}
			if(!blockEmpty) {
				System.out.println("x = " + (usedBlock.length - 1) + ", y = " + i);
				removeLines.add(i); 
			}
		}
		System.out.println("$$$$$$$$$$$$$$$$$" + removeLines.toString());
		return usedBlock;
	}

}
