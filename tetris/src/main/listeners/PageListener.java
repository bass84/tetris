package main.listeners;

import main.Navigator;
import main.pages.BeforeStartPage;
import main.pages.IGamePage;

public class PageListener implements Listener{
	private Navigator navigator;
	
	public PageListener(Navigator navigator) {
		this.navigator = navigator;
		this.navigator.setGamePage(new BeforeStartPage());
	}
	
	public PageListener getPageListener() {
		return this;
	}
	
	public void movePlayPage(IGamePage gamePage) {
		navigator.setGamePage(gamePage);
	}
	
	@Override
	public void keyPressed(int keyCode) {
		
	}
}
