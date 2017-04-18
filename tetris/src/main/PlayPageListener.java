package main;

import processing.core.PApplet;

public class PlayPageListener {

	private int playPageType;
	
	public PlayPageListener(int playerCount) {
		if(playerCount == 1) 
			this.playPageType = playerCount;
		else if(playerCount == 2) {
			
		}
	}
	
	public PlayPage getPlayPage(PApplet pApplet) {
		if(this.playPageType == 1) {
			return new SinglePlayPage(pApplet);
		}
		return null;
	}
	
}
