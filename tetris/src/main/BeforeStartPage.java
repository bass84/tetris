package main;

import main.GameStatus.Status;
import processing.core.PApplet;
import processing.core.PFont;

public class BeforeStartPage implements IGamePage{

	private PApplet pApplet;
	private PFont mono;
	
	public BeforeStartPage(PApplet pApplet) {
		this.pApplet = pApplet;
	}
	
	@Override
	public void drawPage() {
	}

	@Override
	public void drawText() {
		this.mono = this.pApplet.createFont("mono", 30);
		this.pApplet.textFont(this.mono);
		this.pApplet.fill(255, 255, 255);
		this.pApplet.text("PRESS 1 then Single Play", 55, 280);
		this.pApplet.text("PRESS 2 then Multi Play", 55, 280);
		
	}

	@Override
	public void keyPressed(int keyCode) {
		System.out.println(keyCode);
		switch(keyCode) {
			case(10) :
				Navigator.gameStatus.setGameStatus(Status.playing);
				break;
			}
		
	}

}
