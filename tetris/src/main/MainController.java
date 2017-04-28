package main;

import main.pages.BeforeStartPage;
import navigator.Navigator;
import processing.core.PApplet;

public class MainController extends PApplet{
	
	private Navigator navigator;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
	}

	public void settings(){
		size(400, 600);		//전체 크기 설정
    }
	
	public void setup(){	//초기 세팅작업
		background(48);
		this.navigator = new Navigator((Object)this);
		this.navigator.push(new BeforeStartPage(this.navigator));
	}
	
	public void draw() {	// 각 도형의 움직임을 그린다.
		try {
			this.navigator.draw();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed() {	// 키 이벤트
		this.navigator.keyPressed(keyCode);
	}

	

	
	
	
}