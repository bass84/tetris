package main;

public class ShapeMapping {

	public enum Kind {
		I
		, J
		, L
		, O
		, S
		, T
		, Z
	}
	
	public int[][] getShapeInfo(int index) {
		int[][] shapeInfo;
		
		switch(index) {
			case 0 : 
				shapeInfo = new int[][]{{4, 0}, {5, 0}, {6, 0}, {7, 0}};
				return shapeInfo;
			case 1 :
				shapeInfo = new int[][]{{5, 0}, {5, 1}, {6, 1}, {7, 1}};
				return shapeInfo;
			case 2 :
				shapeInfo = new int[][]{{4, 1}, {5, 1}, {6, 0}, {6, 1}};
				return shapeInfo;
			case 3 :
				shapeInfo = new int[][]{{5, 0}, {6, 0}, {5, 1}, {6, 1}};
				return shapeInfo;
			case 4 :
				shapeInfo = new int[][]{{6, 0}, {7, 0}, {5, 1}, {6, 1}};
				return shapeInfo;
			case 5 :
				shapeInfo = new int[][]{{4, 1}, {5, 0}, {5, 1}, {6, 1}};
				return shapeInfo;
			case 6 :
				shapeInfo = new int[][]{{5, 0}, {6, 0}, {6, 1}, {7, 1}};
				return shapeInfo;
		}
		
		return null;
	}
	
	
}