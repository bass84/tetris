package main.pages;

import main.listeners.PageListener;
import processing.core.PApplet;
import processing.core.PFont;

public class ParentPage {
	
	private static ParentPage parentPage;
	private PageListener listener;
	private PApplet pApplet;
	private PFont mono;
	
	public static ParentPage getParentPage() {
		return parentPage;
	}
	public static void setParentPage(ParentPage parentPage) {
		ParentPage.parentPage = parentPage;
	}
 	
	public ParentPage(PageListener listener, PApplet pApplet) {
		this.listener = listener;
		this.pApplet = pApplet;
	}
	
	public PageListener getPageListener() {
		return this.listener;
	}
	public void setPageListener(PageListener listener) {
		this.listener = listener;
	}
	public void setPApplet(PApplet pApplet) {
		this.pApplet = pApplet;
	}
	public PFont getMono() {
		return mono;
	}
	public void setMono(PFont mono) {
		this.mono = mono;
	}
	public PApplet getPApplet() {
		return this.pApplet;
	}
	
	
}
