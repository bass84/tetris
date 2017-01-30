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
	
	public int[][] getShapeInfo(Kind kind) {
		int[][] shapeInfo;
		
		switch(kind) {
			case I : 
				shapeInfo = new int[][]{{3, 0}, {4, 0}, {5, 0}, {6, 0}};
				return shapeInfo;
			case J :
				shapeInfo = new int[][]{{4, 0}, {4, 1}, {5, 1}, {6, 1}};
				return shapeInfo;
			case L :
				shapeInfo = new int[][]{{3, 1}, {4, 1}, {5, 0}, {5, 1}};
				return shapeInfo;
			case O :
				shapeInfo = new int[][]{{4, 0}, {5, 0}, {4, 1}, {5, 1}};
				return shapeInfo;
			case S :
				shapeInfo = new int[][]{{5, 0}, {6, 0}, {4, 1}, {5, 1}};
				return shapeInfo;
			case T :
				shapeInfo = new int[][]{{3, 1}, {4, 0}, {4, 1}, {5, 1}};
				return shapeInfo;
			case Z :
				shapeInfo = new int[][]{{4, 0}, {5, 0}, {5, 1}, {6, 1}};
				return shapeInfo;
		}
		
		return null;
	}
	
	
}