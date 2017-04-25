package main.pages;

public abstract class PlayPage extends IGamePage{

	public PlayPage() {
		super();
	}

	public final static int block = 40;
	
	public abstract void increaseTotalScore(int score); 
	
}
