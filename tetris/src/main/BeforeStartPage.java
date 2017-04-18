package main;

import main.GameStatus.Status;
import processing.core.PApplet;
import processing.core.PFont;

public class BeforeStartPage implements GamePage{

	private static BeforeStartPage beforeStartPage = new BeforeStartPage();
	private PApplet pApplet;
	private PFont mono;
	
	public static synchronized BeforeStartPage getBeforeStartPage() {
		if(beforeStartPage == null) beforeStartPage = new BeforeStartPage();
		return beforeStartPage;
	}
	
	
	public void setInit(PApplet pApplet) {
		this.pApplet = pApplet;
		
	}

	@Override
	public void drawPage() {
	}

	@Override
	public void drawText() {
		this.mono = this.pApplet.createFont("mono", 30);
		this.pApplet.textFont(this.mono);
		this.pApplet.fill(255, 255, 255);
		this.pApplet.text("PRESS ENTER START ", 55, 280);
		
	}

	@Override
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case(10) :
				MainController.gameStatus.setGameStatus(Status.playing);
				break;
			}
		
	}

}
