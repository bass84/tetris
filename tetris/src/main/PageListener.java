package main;

import processing.core.PApplet;

public class PageListener {
	private Navigator navigator;
	private PApplet pApplet;
	private IGamePage gamePage;
	
	public PageListener(PApplet pApplet, Navigator navigator) {
		this.navigator = navigator;
		this.pApplet = pApplet;
		this.gamePage = new BeforeStartPage(this.pApplet);
		this.navigator.setGamePage(this.gamePage);
	}
	
	public void keyPressed(int keyCode) {
		if(gamePage instanceof BeforeStartPage) {
			switch(keyCode) {
				case 49 :
					this.navigator.setGamePage(new SinglePlayPage(this.pApplet));
					break;
				case 50 :
					// something do
					break;
			}
		}
		else {
			this.gamePage.keyPressed(keyCode);
		}
	}
	
	
	
}
