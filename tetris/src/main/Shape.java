package main;

import java.util.Random;

import processing.core.PApplet;
import main.ShapeMapping.Kind;

public class Shape extends PApplet{

	private int shapeOrder;	//���εǴ� ������ȣ
	private ShapeMapping mapping;
	private String[] param;	// ������ ���� �Ķ����
	private PApplet pApplet;
	private String[] rgb = new String[3];	// ��������
	private float widthLocation;	//�¿� ��ġ
	private float heightLocation;	//���� ��ġ
	private float velocity = (float)1.3;	//�������� �ӵ�
	private float sizeX;	// ���� �¿������
	private float sizeY;	// ���� ���̻����� 
	private int backgroundWidth;	//��� �¿������
	private int backgroundHeight;	//��� ���̻�����
	
	
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
		pApplet.fill(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));	//���� ���� ����
	
		if(this.heightLocation + this.velocity + moveHeight < (this.backgroundHeight - this.sizeY + 5)) {	// ������ �ٴڰ� ������ �������� ��������.
			this.heightLocation += this.velocity + moveHeight;	// ���̰� ����
			
			if(this.widthLocation + moveWidth < 5) {	// ���� ������ ���� ��
				this.widthLocation = 5;
			}else if(this.widthLocation + moveWidth > this.backgroundWidth - sizeX + 5) {	//������ ������ ���� ��
				this.widthLocation = this.backgroundWidth - sizeX + 5;
			}else{
				this.widthLocation += moveWidth;	//�¿찪 ����
			}
		}
		else {	// ������ �ٴ��� ������.
			this.heightLocation = this.backgroundHeight - this.sizeY + 5;	
			System.out.println("width = " + this.widthLocation + ", height = " + this.heightLocation);
		}
		pApplet.rect(this.widthLocation, this.heightLocation, this.sizeX, this.sizeY);
	}
	
	
	
	
}
