package main;

import processing.core.PApplet;

public class Background extends PApplet{
	private PApplet pApplet;
	private int aperture = 5;
	
	public Background(PApplet p) {
		pApplet = p;
	}
	
	public void init(int backWidth, int backHeight) {
		pApplet.fill(0, 0, 0);
		pApplet.quad(aperture, aperture, aperture, backHeight + aperture, backWidth + aperture, backHeight + aperture, backWidth + aperture, aperture);
	}
	
	public void changeBackground() {
		
	}
	
	
	
	

}