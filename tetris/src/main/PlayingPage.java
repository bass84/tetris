package main;

public class PlayingPage implements GamePage{

	private static GamePage playingPage = new PlayingPage();
	
	public static synchronized GamePage getPlayingPage() {
		if(playingPage == null) playingPage = new PlayingPage();
		return playingPage;
	}

	@Override
	public void drawPage() {
		// TODO Auto-generated method stub
		
	}
}
