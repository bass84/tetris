package main;

import main.GameStatus.Status;
import processing.core.PApplet;
import processing.core.PFont;

public class MainController extends PApplet{
	
	public final static int block = 40;
	//private int[][] usedBlock = new int[11][16];
	//private Shape shape;
	//private int term = 60;
	//private Grid grid;
	//private int totalScore = 0;
	public static GameStatus gameStatus;
	private GamePage gamePage;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
	}

	public void settings(){
		size(400, 600);	//전체 크기 설정
    }
	
	public void setup(){
		/*this.shape = new Shape(this);
		this.grid = new Grid(this);*/
		gameStatus = new GameStatus();
		background(48);
		
		/*for(int i = 0; i < usedBlock.length; ++i) usedBlock[i][15] = -1;
		for(int i = 0; i < usedBlock[0].length; ++i) usedBlock[0][i] = -1;*/
		
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		try {
			this.newGamePage(gameStatus.getGameStatus());
			this.gamePage.drawPage();
			this.gamePage.drawText();
		}catch(Exception e) {
			gameStatus.setGameStatus(Status.gameOver);
			this.gamePage = PlayingPage.getPlayingPage(true);
			//this.reset();
		}
	}
	
	private void newGamePage(Status gameStatus) {
		switch(gameStatus) {
			case playing :
				System.out.println("Game Start!");
				this.gamePage = PlayingPage.getPlayingPage(false);
				System.out.println(this.gamePage);
				break;
			case pause :
				this.gamePage = MenuPage.getPausePage();
				break;
			case beforeStart :
				System.out.println("BeforeStartPage call");
				this.gamePage = BeforeStartPage.getBeforeStartPage();
				break;
			case gameOver :
				this.gamePage = GameOverPage.getGameOverPage();
				break;
		}
		this.gamePage.setInit(this);
	}
	
	/*public void reset() {
		this.totalScore = 0;
		for(int i = 0; i < this.usedBlock.length; ++i) {
			for(int j = 0; j < this.usedBlock[i].length; ++j) {
				if(i == 0 || j == 15) this.usedBlock[i][j] = -1;
				else this.usedBlock[i][j] = 0;
			}
		}
	}
	
	public void addUsedBlock() {
		int positionX = this.shape.getPositionX();
		int positionY = this.shape.getPositionY();
		int shapeColor = this.shape.getShapeColor();
		int shapeInfo[][] = this.shape.getShapeInfo();
		for(int i = 0; i < shapeInfo.length; i++) {
			this.usedBlock[shapeInfo[i][0] + 1 + positionX][shapeInfo[i][1] - 1  + positionY] = shapeColor;
		}
	}
	
	public void increaseTotalScore(int addScore) {
		this.totalScore += addScore;
	}*/
	
	/*public void saveGame(File selection) {
		if(selection != null) {
			String saveFile = selection.getAbsolutePath(); 
			System.out.println("User selected " + saveFile);
			
			try(DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(saveFile)))) {
				// 1. usedBlock write
				for(int i = 0; i < this.usedBlock.length; i++) {
					for(int j = 0; j < this.usedBlock[i].length; j++) {
						dos.writeInt(this.usedBlock[i][j]);
					}
				}
				// 2. totalScore write
				dos.writeInt(this.totalScore);
				
				// 3. shapeInfo write
				int[][] saveShapeInfo = this.shape.getShapeInfo();
				for(int i = 0; i < saveShapeInfo.length; i++) {
					for(int j = 0; j < saveShapeInfo[i].length; j++) {
						dos.writeInt(saveShapeInfo[i][j]);
					}
				}
				
				dos.writeInt(shape.getShapeKind().ordinal());
				// 4. positionX write
				dos.writeInt(this.shape.getPositionX());
				
				// 5. positionY write
				dos.writeInt(this.shape.getPositionY());
				
				// 6. shapeColor write
				dos.writeInt(this.shape.getShapeColor());
				
			}catch(IOException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}
		}
	}*/
	
	/*public void loadGame(File selection) {
		if(selection != null) {
			String loadedFile = selection.getAbsolutePath();
			System.out.println("User selected " + loadedFile);
			
			try(DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(loadedFile)))) {
				
				for(int i = 0; i < this.usedBlock.length; i++) {
					for(int j = 0; j < this.usedBlock[i].length; j++) {
						this.usedBlock[i][j] = dis.readInt();
					}
				}
				// 2. totalScore write
				this.totalScore = dis.readInt();
				
				// 3. shapeInfo write
				int[][] loadShapeInfo = this.shape.getShapeInfo();
				for(int i = 0; i < loadShapeInfo.length; i++) {
					for(int j = 0; j < loadShapeInfo[i].length; j++) {
						loadShapeInfo[i][j] = dis.readInt();
						
					}
				}
				this.shape.setShapeInfo(loadShapeInfo);
				// 4. positionX write
				this.shape.setPositionX(dis.readInt());
				
				// 5. positionY write
				this.shape.setPositionY(dis.readInt());
				
				// 6. shapeColor write
				this.shape.setShapeColor(dis.readInt());
				
			}catch(InvalidClassException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}catch(IOException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}
			
			this.gameStatus.setGameStatus(Status.playing);
		}
		
		
	}*/
	
	public void keyPressed() {	// 키 이벤트
		
		System.out.println(keyCode);
		this.gamePage.keyPressed(keyCode);
		/*if(this.grid.isBottom(this.usedBlock, this.shape)) return;
		
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
					selectInput("Select a file to process:", "GameUtil.loadGame");
					this.gameStatus.setGameStatus(Status.playing);
					break;
					
				case(83) :	// 'S' - save
					selectInput("Select a file to process:", "GameUtil.saveGame");
					break;
			}
			System.out.println(keyCode);
		}*/
	
	}

	

	
	
	
}