package main;

import processing.core.PApplet;
import processing.core.PFont;

public class PausePage implements GamePage {

	private static GamePage pausePage = new PausePage();
	
	public static synchronized GamePage getPausePage() {
		if(pausePage == null) pausePage = new PausePage();
		return pausePage;
	}

	@Override
	public void drawPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPApplet(PApplet pApplet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawText(PFont mono) {
		// TODO Auto-generated method stub
		
	}
	
	

}
