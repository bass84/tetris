package main;

import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape extends PApplet{

	private int shapeOrder;	//매핑되는 도형번호
	private ShapeMapping mapping;
	private String[] param;	// 도형에 대한 파라미터
	private PApplet pApplet;
	private String[] rgb = new String[3];	// 도형색상
	private float widthLocation;	//좌우 위치
	private float heightLocation;	//높이 위치
	private float velocity = (float)1.3;	//내려오는 속도
	private float sizeX;	// 도형 좌우사이즈
	private float sizeY;	// 도형 높이사이즈 
	private int backgroundWidth;	//배경 좌우사이즈
	private int backgroundHeight;	//배경 높이사이즈
	
	
	public Shape(PApplet p, int backgroundWidth, int backgroundHeight) {
		this.pApplet = p;
		this.shapeOrder = new Random().nextInt(Kind.values().length);
		this.mapping = new ShapeMapping(shapeOrder);
		this.param = this.mapping.getShapeParam().split(",");
		this.widthLocation = Integer.parseInt(this.param[3].trim());
		this.heightLocation = Integer.parseInt(this.param[4].trim());
		this.sizeX = Integer.parseInt(this.param[5].trim());
		this.sizeY = Integer.parseInt(this.param[6].trim());
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
	}
	
	
	public void drawShape(int backgroundWidth, int backgroundHeight, int moveWidth, int moveHeight) {
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		System.arraycopy(param, 0, rgb, 0, 3);
		pApplet.fill(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));	//도형 색상 지정
	
		if(this.heightLocation + this.velocity + moveHeight < (this.backgroundHeight - this.sizeY + 5)) {	// 도형이 바닥과 만나기 전까지만 내려간다.
			this.heightLocation += this.velocity + moveHeight;	// 높이값 지정
			
			if(this.widthLocation + moveWidth < 5) {	// 왼쪽 끝으로 갔을 때
				this.widthLocation = 5;
			}else if(this.widthLocation + moveWidth > this.backgroundWidth - sizeX + 5) {	//오른쪽 끝으로 갔을 때
				this.widthLocation = this.backgroundWidth - sizeX + 5;
			}else{
				this.widthLocation += moveWidth;	//좌우값 지정
			}
		}
		else {	// 도형과 바닥이 만난다.
			this.heightLocation = this.backgroundHeight - this.sizeY + 5;	
			System.out.println("width = " + this.widthLocation + ", height = " + this.heightLocation);
		}
		pApplet.rect(this.widthLocation, this.heightLocation, this.sizeX, this.sizeY);
	}
	
	
	
	
}
