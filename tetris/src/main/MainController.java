package main;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.GameStatus.Status;
import main.ShapeMapping.Kind;
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
	private GameStatus gameStatus;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
	}

	public void settings(){
		size(400, 600);	//전체 크기 설정
    }
	
	public void setup(){
		this.shape = new Shape(this);
		this.grid = new Grid(this);
		this.gameStatus = new GameStatus();
		background(48);
		
		for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = -1;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = -1;
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		try {
			this.drawByStatus(this.gameStatus.getGameStatus());
		}catch(Exception e) {
			this.gameStatus.setGameStatus(Status.gameOver);
			this.reset();
		}
	}
	
	public void drawByStatus(Status gameStatus) {
		switch(gameStatus) {
			case playing: 
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
				break;
			
			case pause : 
				this.grid.drawShape(this.usedBlock, this.shape);
				this.shape.drawShape(usedBlock, this.shape);
				break;
			}
		
		this.drawText(gameStatus);
	}
	public void drawText(Status gameStatus) {
		switch(gameStatus) {
			case playing :
				this.mono = createDefaultFont(15);
				textFont(mono);
				fill(255, 255, 255);
				textAlign(LEFT, CENTER);
				text("SCORE : " + totalScore, 12, 30);
				break;
			case beforeStart :
				this.mono = createDefaultFont(30);
				textFont(mono);
				fill(255, 255, 255);
				text("PRESS ENTER START ", 55, 280);
				break;
			case pause :
				this.mono = createDefaultFont(30);
				textFont(mono);
				fill(255, 255, 255);
				textAlign(CENTER, CENTER);
				text("ENTER : START", 200, 250);
				textAlign(CENTER, CENTER);
				text("S : SAVE", 200, 290);
				textAlign(CENTER, CENTER);
				text("L : LOAD", 200, 330);
				break;
			case gameOver :
				background(0);
				this.mono = createDefaultFont(50);
				textFont(mono);
				fill(255, 255, 255);
				text("GAME OVER ", 55, 280);
				break;
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
	
	public void saveGame(File selection) {
		if(selection != null) {
			String saveFile = selection.getAbsolutePath(); 
			System.out.println("User selected " + saveFile);
			try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(saveFile)))) {
				GameStorage gameStorage = new GameStorage(this.usedBlock, this.shape, this.totalScore);
				oos.writeObject(gameStorage);
			}catch(IOException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}
		}
	}
	
	public void loadGame(File selection) {
		if(selection != null) {
			String loadedFile = selection.getAbsolutePath();
			System.out.println("User selected " + loadedFile);
			
			GameStorage gameStorage = null;
			try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(loadedFile)))) {
				gameStorage = (GameStorage)ois.readObject();
			}catch(InvalidClassException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}catch(ClassNotFoundException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}catch(IOException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}
			
			this.usedBlock = gameStorage.getUsedBlock();
			this.shape = new Shape(this);
			this.totalScore = gameStorage.getTotalScore();
			this.gameStatus.setGameStatus(Status.playing);
		}
		
		
	}
	
	public void keyPressed() {	// 키 이벤트
		if(this.grid.isBottom(this.usedBlock, this.shape)) return;
		
		if(this.gameStatus.getGameStatus() == Status.playing) {
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
					this.gameStatus.setGameStatus(Status.pause);
					break;
			}
		}else {
			switch(keyCode) {
				case(10) :
					this.gameStatus.setGameStatus(Status.playing);
					break;
				
				case(76) :	// 'L' - load
					selectInput("Select a file to process:", "loadGame");
					break;
					
				case(83) :	// 'S' - save
					selectInput("Select a file to process:", "saveGame");
					break;
			}
			System.out.println(keyCode);
		}
	
	}

	

	
	
	
}