package main.pages;

import processing.core.PApplet;
import processing.core.PFont;

public class BeforeStartPage implements IGamePage{
	private PFont mono;
	private PApplet pApplet;
	
	@Override
	public void drawPage() {
		this.mono = ParentPage.getParentPage().getMono();
		this.pApplet = ParentPage.getParentPage().getPApplet();
		
		this.mono = this.pApplet.createFont("mono", 22);
		this.pApplet.textFont(this.mono);
		this.pApplet.fill(255, 255, 255);
		this.pApplet.text("PRESS '1' Then Play Single", 55, 280);
		this.pApplet.text("PRESS '2' Then Play Multi", 55, 330);
	}

	@Override
	public void keyPressed(int keyCode) {
		
		switch(keyCode) {
			case 49 :	// single play page
				ParentPage.getParentPage().getPageListener().movePlayPage(new SinglePlayPage());
				break;
			case 50 :	// multi play page
				// something do
				break;
		}
	}

}
