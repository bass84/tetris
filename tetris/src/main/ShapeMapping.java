package main;

public class ShapeMapping {

	private String shapeParam;
	
	public enum Kind {	//도형 종류 (r, g, b, x위치, y위치, 도형 좌우 길이, 도형 상하 길이)
		square("255, 0, 0, 200, 0, 100, 100")	//사각형
		, stick("0, 0, 255, 150, 0, 200, 25");	//1자형
		
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