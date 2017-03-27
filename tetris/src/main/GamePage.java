package main;

import processing.core.PApplet;
import processing.core.PFont;

public interface GamePage {

	public void setPApplet(PApplet pApplet);
	
	public void drawPage();
	
	public void drawText(PFont mono);
}
