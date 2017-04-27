package main.pages;

import navigator.Navigator;
import pages.IGamePage;
import processing.core.PApplet;
import processing.core.PFont;

public class BeforeStartPage extends IGamePage{
	private PApplet pApplet;
	
	public BeforeStartPage(Navigator navigator) {
		this.navigator = navigator;
	}

	@Override
	public void drawPage(PApplet pApplet, PFont mono) throws Exception {
		this.pApplet = pApplet;
		mono = pApplet.createFont("mono", 22);
		pApplet.textFont(mono);
		pApplet.fill(255, 255, 255);
		pApplet.text("PRESS '1' Then Play Single", 55, 280);
		pApplet.text("PRESS '2' Then Play Multi", 55, 330);
		
	}

	@Override
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case 49 :
				this.navigator.push(new SinglePlayPage(this.pApplet));
				break;
			case 50 :
				
				break;
		}
		
	}

}
