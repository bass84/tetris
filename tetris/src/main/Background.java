package main;

import processing.core.PApplet;

public class Background {
	
	private PApplet pApplet;
	
	public Background(PApplet p) {
		pApplet = p;
	}
	
	public void init() {
		pApplet.fill(0, 0, 0);
		pApplet.rect(0, 0, MainController.backgroundWidth, MainController.backgroundHeight);
	}
	
	
	
	
	
	

}