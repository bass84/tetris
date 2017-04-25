package main.listeners;

import main.Navigator;
import main.pages.BeforeStartPage;
import main.pages.IGamePage;
import main.pages.SinglePlayPage;
import processing.core.PApplet;

public class PageListener implements Listener{
	private Navigator navigator;
	private IGamePage gamePage;
	
	public PageListener(Navigator navigator) {
		this.navigator = navigator;
	}
	
	private void setGamePage(IGamePage gamePage) {
		this.gamePage = gamePage;
		this.navigator.setGamePage(gamePage);
	}
	
	public void keyPressed(int keyCode) {
		
	}
	
	public void movePlayPage(IGamePage gamePage) {
		this.gamePage = gamePage;
	}
	
	
}
