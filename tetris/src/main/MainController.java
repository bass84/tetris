package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import processing.core.PApplet;

public class MainController extends PApplet{
	public final static int block = 40;
	private boolean[][] usedBlock = new boolean[11][16];
	private Shape shape;
	private int term = 60;
	private Grid grid;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
		
    }

	public void settings(){
		size(400, 600);	//전체 크기 설정
    }
	
	public void setup(){
		this.shape = new Shape(this);
		this.grid = new Grid(this);
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = true;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = true;
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		
		if(this.grid.isBottom(this.usedBlock, this.shape)) {
			this.addUsedBlock(this.shape.getPostiionX(), this.shape.getPostiionY());
			this.shape = new Shape(this);
			this.usedBlock = this.grid.getNewGridLine(this.usedBlock); 
		}else{
			clear();
			this.grid.drawShape(this.usedBlock);
			this.shape.drawShape(usedBlock);
			if(frameCount % this.term == 0) this.shape.increasePositionY();
		}
	}
	
	public void addUsedBlock(int positionX, int positionY) {
		int shapeInfo[][] = this.shape.getShapeInfo();
		for(int i = 0; i < shapeInfo.length; i++) {
			this.usedBlock[shapeInfo[i][0] + 1 + positionX][shapeInfo[i][1] - 1  + positionY] = true;
		}
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