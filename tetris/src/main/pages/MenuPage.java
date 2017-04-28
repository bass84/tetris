package main.pages;

import pages.IGamePage;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class MenuPage extends IGamePage {

	/*public MenuPage(PageListener listener, PApplet pApplet) {
		super(listener, pApplet);
	}*/

	private PApplet pApplet;
	private PFont mono;
	

	@Override
	public void drawPage(Object pApplet, Object mono) {
		//this.drawText();
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

	public void setInit(PApplet pApplet) {
		this.pApplet = pApplet;
		
	}

	/*@Override
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
	}*/

	@Override
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case(10) :
				//Navigator.gameStatus.setGameStatus(Status.playing);
			break;
			
			case(76) :	// 'L' - load
				this.pApplet.selectInput("Select a file to process:", "GameUtil.loadGame");
				//Navigator.gameStatus.setGameStatus(Status.playing);
			break;
			
			case(83) :	// 'S' - save
				this.pApplet.selectInput("Select a file to process:", "GameUtil.saveGame");
			break;
		}
		
	}
	
	

}
