package main;

import main.pages.BeforeStartPage;
import main.pages.IGamePage;
import processing.core.PApplet;

public class Navigator {
	private IGamePage gamePage;
	public static GameStatus gameStatus;
	
	public Navigator(PApplet pApplet) {
		this.gamePage = new BeforeStartPage(pApplet, this);
	}
	
	public IGamePage getGamePage() {
		return this.gamePage;
	}
	public void setGamePage(IGamePage gamePage) {
		this.gamePage = gamePage;
	}
	
	public void draw() {
		try {
			this.gamePage.drawPage();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void keyPressed(int keyCode) {
		System.out.println("keyCode = " + keyCode);
		this.gamePage.keyPressed(keyCode);
	}
	
	
}
