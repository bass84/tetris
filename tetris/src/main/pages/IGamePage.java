package main.pages;

import main.listeners.Listener;

public abstract class IGamePage {
	
	private Listener listener;
	
	public IGamePage(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getPageListener() {
		return this.listener;
	}
	public void setPageListener(Listener listener) {
		this.listener = listener;
	}
	
	public abstract void drawPage() throws Exception;
	
	public abstract void keyPressed(int keyCode);
	
	
	
}
