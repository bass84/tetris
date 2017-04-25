package main;

import processing.core.PApplet;

public class MainController extends PApplet{
	
	//public final static int block = 40;
	//private PageListener pageListener;
	private Navigator navigator;
	
	public static void main(String[] args) {
		PApplet.main("main.MainController");
	}

	public void settings(){
		size(400, 600);	//전체 크기 설정
    }
	
	public void setup(){
		background(48);
		this.navigator = new Navigator(this);
		//this.pageListener = new PageListener(this, this.navigator);
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