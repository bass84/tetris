package main;

public class PausePage implements GamePage {

	private static GamePage pausePage = new PausePage();
	
	public static synchronized GamePage getPausePage() {
		if(pausePage == null) pausePage = new PausePage();
		return pausePage;
	}
	
	@Override
	public void drawPage() {
		// TODO Auto-generated method stub

	}

}
