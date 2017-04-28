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
	public void drawPage(Object pApplet, Object mono) throws Exception {
		this.pApplet = (PApplet) pApplet;
		mono = this.pApplet.createFont("mono", 22);
		this.pApplet.textFont((PFont) mono);
		this.pApplet.fill(255, 255, 255);
		this.pApplet.text("PRESS '1' Then Play Single", 55, 280);
		this.pApplet.text("PRESS '2' Then Play Multi", 55, 330);
		
	}

	@Override
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case 49 :	// press '1'
				this.navigator.push(new SinglePlayPage(this.pApplet));
				break;
			case 50 :	// press '2'
				//this.navigator.push(new MultiPlayPage(this.pApplet));
				break;
		}
		
	}

}
