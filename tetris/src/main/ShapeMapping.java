package main;

public class ShapeMapping {

	private String shapeParam;
	
	public enum Kind {	//���� ���� (r, g, b, x��ġ, y��ġ, ���� �¿� ����, ���� ���� ����)
		square("255, 0, 0, 205, 5, 100, 100")	//�簢��
		, stick("0, 0, 255, 155, 5, 200, 25");	//1����
		
		private String param;
		
		Kind(String param) {
			this.param = param;
		}
		
		String getKind() {
			return this.param;
		}
	}
	
	public ShapeMapping(int shapeOrder) {
		this.setShapeParam(shapeOrder);
	}
	
	public String getShapeParam() {
		return this.shapeParam;
	}
	
	public void setShapeParam(int shapeOrder) {
		this.shapeParam = Kind.values()[shapeOrder].getKind();
	}
	
	
}