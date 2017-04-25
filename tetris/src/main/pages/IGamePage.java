package main.pages;

import main.listeners.PageListener;
import processing.core.PApplet;
import processing.core.PFont;

public abstract class IGamePage {
	
	private PageListener listener;
	private PApplet pApplet;
	private PFont mono;
	
	public IGamePage() {
		
	}
 	
	public IGamePage(PageListener listener, PApplet pApplet) {
		this.listener = listener;
		this.pApplet = pApplet;
	}
	
	public PageListener getPageListener() {
		return this.listener;
	}
	public void setPageListener(PageListener listener) {
		this.listener = listener;
	}
	public PApplet getPApplet() {
		return this.pApplet;
	}
	
	public abstract void drawPage() throws Exception;
	
	public abstract void keyPressed(int keyCode);
	
	
	
}
