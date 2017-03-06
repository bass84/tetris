package main;


public class GameStatus {
	private Status currentStatus = Status.beforeStart;
	private MainController mainController;
	private Shape shape;
	private Grid grid;
	
	public enum Status {
		playing
		, gameOver
		, pause
		, beforeStart
	}
	
	
	public Status getGameStatus() {
		return currentStatus;
	}
	public void setGameStatus(Status gameStatus) {
		this.currentStatus = gameStatus;
	}
	
		
}
