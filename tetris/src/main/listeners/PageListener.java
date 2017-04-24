package main.listeners;

import main.Navigator;
import main.pages.BeforeStartPage;
import main.pages.IGamePage;
import main.pages.SinglePlayPage;
import processing.core.PApplet;

public class PageListener implements Listener{
	private Navigator navigator;
	private PApplet pApplet;
	private IGamePage gamePage;
	
	public PageListener(PApplet pApplet, Navigator navigator) {
		this.navigator = navigator;
		this.pApplet = pApplet;
		this.gamePage = new BeforeStartPage(this.pApplet);
		this.navigator.setGamePage(this.gamePage);
	}
	
	private void setGamePage(IGamePage gamePage) {
		this.gamePage = gamePage;
		this.navigator.setGamePage(gamePage);
	}
	
	public void keyPressed(int keyCode) {
		if(gamePage instanceof BeforeStartPage) {
			switch(keyCode) {
				case 49 :
					this.setGamePage(new SinglePlayPage(this.pApplet));
					break;
				case 50 :
					// something do
					break;
			}
		}
		else {
			this.navigator.keyPressed(keyCode);
		}
	}
	
	
	
}
