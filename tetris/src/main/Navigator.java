package main;

import processing.core.PApplet;

public class Navigator {
	private IGamePage gamePage;
	private PlayPage playPage;
	private PApplet pApplet;
	public static GameStatus gameStatus;
	
	public Navigator(PApplet pApplet) {
		this.pApplet = pApplet;
		this.gamePage = new BeforeStartPage(pApplet);
	}
	
	
	public void draw() {
		switch(gameStatus.getGameStatus()) {
			case playing :
				try {
					this.playPage.drawPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			
			default :
				try {
					gamePage.drawPage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
		}
	}
	
	
	
	
}
