package main.pages;

import main.GameStatus.Status;
import main.Navigator;
import main.listeners.PageListener;
import processing.core.PApplet;
import processing.core.PFont;

public class GameOverPage extends IGamePage{
	
	public GameOverPage(PageListener listener, PApplet pApplet) {
		super(listener, pApplet);
		// TODO Auto-generated constructor stub
	}

	private PApplet pApplet;
	private PFont mono;
	
	

	public void setInit(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	@Override
	public void drawPage() {
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
				Navigator.gameStatus.setGameStatus(Status.playing);
				break;
			}
		
	}

}
