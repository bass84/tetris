package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import processing.core.PApplet;

public class Grid implements BlockDraw{
	private PApplet pApplet;
	
	public Grid(PApplet pApplet) {
		this.pApplet = pApplet;
	}
	
	public boolean isBottom(int[][] usedBlock, Shape shape){
		int[][] shapeInfo = shape.getShapeInfo();
		int positionX = shape.getPostiionX();
		int positionY = shape.getPostiionY();
		
		for(int i = 0; i < shapeInfo.length; i++) {
			if((shapeInfo[i][0] + positionX) < 10 
					&& usedBlock[shapeInfo[i][0] + 1 + positionX][shapeInfo[i][1] + positionY] == -1) return true;
		}
		return false;
	}
	
	public boolean isLeftEnd(int[][] usedBlock, Shape shape) {
		int[][] shapeInfo = shape.getShapeInfo();
		int positionX = shape.getPostiionX();
		int positionY = shape.getPostiionY();
		
		for(int i = 0; i < shapeInfo.length; i++) {
			if(usedBlock[shapeInfo[i][0] + positionX][shapeInfo[i][1] + positionY] == -1) return true;
		}
		return false;
	}
	
	public boolean isRightEnd(int[][] usedBlock, Shape shape) {
		int[][] shapeInfo = shape.getShapeInfo();
		int positionX = shape.getPostiionX();
		int positionY = shape.getPostiionY();
		
		for(int i = 0; i < shapeInfo.length; i++) {
			if((shapeInfo[i][0] + positionX) == 9 
					|| usedBlock[shapeInfo[i][0] + 2 + positionX][shapeInfo[i][1] + positionY] == -1) return true;
		}
		return false;
	}
	
	public boolean isPossibleRotation(int[][] usedBlock, Shape shape) {
		
		int positionX = shape.getPostiionX();
		int positionY = shape.getPostiionY();
		
		int newX = 0;
		int newY = 0;
		int[][] nextShapeInfo = shape.getNextShapeInfo();
		
		for(int i = 0; i < nextShapeInfo.length; i++) {
			newX = nextShapeInfo[i][0] + positionX + 1;
			newY = nextShapeInfo[i][1] + positionY;
			if(newX < 1 || newX > 10 || newY < 0 || newY > 14 ) return false;
			if(usedBlock[newX][newY] == -1) return false;
		}
		return true;
	}

	
	@Override
	public void drawShape(int[][] usedBlock, int shapeColor) {
		for(int i = 1; i < usedBlock.length; i++) {
			for(int j = 0; j < usedBlock[i].length - 1; j++) {
				if(usedBlock[i][j] == -1) {
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

	public int[][] getNewGridLine(int[][] usedBlock, Shape shape) {
		List<Integer> removeLines = new ArrayList<Integer>();
		
		for(int i = usedBlock[0].length - 2; i >= 0; i--) {
			boolean blockEmpty = false;
			for(int j = 1; j < usedBlock.length; j++) {
				if(usedBlock[j][i] != -1) {
					blockEmpty = true;
					break;
				}
			}
			if(!blockEmpty)removeLines.add(i); 
		}
		if(removeLines.size() > 0) this.removeLines(usedBlock, removeLines, shape.getShapeColor());
		
		return usedBlock;
	}


	private int[][] removeLines(int[][] usedBlock, List<Integer> removeLines, int shapeColor) {
		Collections.sort(removeLines);
		
		for(int i = 0; i < removeLines.size(); i++) {
			int heighestLine = this.getHeighestLine(usedBlock, removeLines.get(i));
			usedBlock = this.moveLines(removeLines.get(i) - 1, heighestLine, usedBlock, shapeColor);
		}
		return usedBlock;
	}
	

	private int[][] moveLines(int lowestLine, int heighestLine, int[][] usedBlock, int shapeColor) {
		for(int i = lowestLine; i >= heighestLine; i--) {
			for(int j = 1; j < usedBlock.length; j++) usedBlock[j][i + 1] = usedBlock[j][i];
		}
		
		for(int i = 0; i < usedBlock.length; i++) usedBlock[i][heighestLine] = shapeColor;
		
		return usedBlock;
	}

	private int getHeighestLine(int[][] usedBlock, int heightRemovingLine) {
		int currentHeightestLine = heightRemovingLine;
		
		for(int i = heightRemovingLine - 1; i >= 0 ; i--) {
			boolean blockEmpty = false;
			for(int j = 1; j < usedBlock.length; j++) {
				if(usedBlock[j][i] == -1) {
					currentHeightestLine = i;
					blockEmpty = true;
					break;
				}
			}
			if(!blockEmpty) break;
		}
		
		return currentHeightestLine;
	}

	
}
