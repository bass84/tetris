package main;

import processing.core.PApplet;

public interface GamePage {

	public void setInit(PApplet pApplet);
	
	public void drawPage() throws Exception;
	
	public void drawText();
	
	public void keyPressed(int keyCode);
}
