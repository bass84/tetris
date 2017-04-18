package main;

import main.GameStatus.Status;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class MenuPage implements GamePage {

	private static MenuPage menuPage = new MenuPage();
	private PApplet pApplet;
	private PFont mono;
	
	public static synchronized MenuPage getPausePage() {
		if(menuPage == null) menuPage = new MenuPage();
		return menuPage;
	}

	@Override
	public void drawPage() {
		this.drawText();
		
	}

	public void setInit(PApplet pApplet) {
		this.pApplet = pApplet;
		
	}

	@Override
	public void drawText() {
		this.mono = this.pApplet.createFont("mono", 30);
		this.pApplet.textFont(this.mono);
		this.pApplet.fill(255, 255, 255);
		this.pApplet.textAlign(PConstants.CENTER, PConstants.CENTER);
		this.pApplet.text("ENTER : START", 200, 250);
		this.pApplet.textAlign(PConstants.CENTER, PConstants.CENTER);
		this.pApplet.text("S : SAVE", 200, 290);
		this.pApplet.textAlign(PConstants.CENTER, PConstants.CENTER);
		this.pApplet.text("L : LOAD", 200, 330);
	}

	@Override
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case(10) :
				MainController.gameStatus.setGameStatus(Status.playing);
			break;
			
			case(76) :	// 'L' - load
				this.pApplet.selectInput("Select a file to process:", "GameUtil.loadGame");
				MainController.gameStatus.setGameStatus(Status.playing);
			break;
			
			case(83) :	// 'S' - save
				this.pApplet.selectInput("Select a file to process:", "GameUtil.saveGame");
			break;
		}
		
	}
	
	

}
