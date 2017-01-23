package main;

import processing.core.PApplet;

public class Background extends PApplet{
	private PApplet pApplet;
	//private int aperture = 5;
	
	public Background(PApplet p) {
		pApplet = p;
	}
	
	public void init(int backgroundWidth, int backgroundHeight) {
		pApplet.fill(0, 0, 0);
		pApplet.rect(0, 0, backgroundWidth, backgroundHeight);
	}
	
	public void changeBackground() {
		
	}
	
	
	
	

}