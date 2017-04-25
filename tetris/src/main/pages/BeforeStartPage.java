package main.pages;

import main.Navigator;
import main.listeners.PageListener;
import processing.core.PApplet;
import processing.core.PFont;

public class BeforeStartPage extends IGamePage{
	private PFont mono;
	
	public BeforeStartPage(PApplet pApplet, Navigator navigator) {
		super(new PageListener(navigator), pApplet);
	}
	
	@Override
	public void drawPage() {
		PApplet thisPApplet = this.getPApplet();
		this.mono = thisPApplet.createFont("mono", 30);
		thisPApplet.textFont(this.mono);
		thisPApplet.fill(255, 255, 255);
		thisPApplet.text("PRESS 1 then Single Play", 55, 280);
		thisPApplet.text("PRESS 2 then Multi Play", 55, 330);
	}

	@Override
	public void keyPressed(int keyCode) {
		
		switch(keyCode) {
			case 49 :	// single play page
				this.getPageListener().movePlayPage(new SinglePlayPage());
				break;
			case 50 :	// multi play page
				// something do
				break;
		}
	}

}
