package main.pages;

import pages.IGamePage;
import processing.core.PApplet;
import processing.core.PFont;

public class GameOverPage extends IGamePage{
	
	
	@Override
	public void drawPage(PApplet pApplet, PFont mono) {
	}

	/*@Override
	public void drawText() {
		this.pApplet.background(0);
		this.mono = this.pApplet.createFont("mono", 50);
		this.pApplet.textFont(this.mono);
		this.pApplet.fill(255, 255, 255);
		this.pApplet.text("GAME OVER ", 55, 280);
	}*/
	@Override
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case(10) :
				//Navigator.gameStatus.setGameStatus(Status.playing);
				break;
			}
		
	}

}
