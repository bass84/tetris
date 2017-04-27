package main;

import main.pages.BeforeStartPage;
import navigator.Navigator;
import pages.IGamePage;
import processing.core.PApplet;

public class MainController extends PApplet{
	
	private Navigator navigator;
	private IGamePage gamePage;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
	}

	public void settings(){
		size(400, 600);	//전체 크기 설정
    }
	
	public void setup(){
		background(48);
		this.navigator = new Navigator(this);
		this.gamePage = new BeforeStartPage(this.navigator);
		this.navigator.push(this.gamePage);
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		try {
			this.navigator.draw();
		}catch(Exception e) {
			
		}
	}
	
	public void keyPressed() {	// 키 이벤트
		this.navigator.keyPressed(keyCode);
	}

	

	
	
	
}