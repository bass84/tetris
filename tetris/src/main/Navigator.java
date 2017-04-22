package main;

public class Navigator {
	private IGamePage gamePage;
	public static GameStatus gameStatus;
	
	
	public IGamePage getGamePage() {
		return this.gamePage;
	}
	public void setGamePage(IGamePage gamePage) {
		this.gamePage = gamePage;
	}
	
	
	public void draw() {
		try {
			this.gamePage.drawPage();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void keyPressed(int keyCode) {
		this.gamePage.keyPressed(keyCode);
	}
	
	
}
