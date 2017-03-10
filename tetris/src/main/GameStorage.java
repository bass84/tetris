package main;

import java.io.Serializable;

public class GameStorage implements Serializable{
	
	private static final long serialVersionUID = 5273830068933038273L;
	private int[][] usedBlock = new int[11][16];
	private Shape shape;
	private int totalScore;

	public GameStorage(int[][] usedBlock, Shape shape, int totalScore) {
		this.usedBlock = usedBlock;
		this.shape = (Shape)shape.clone();
		this.totalScore = totalScore;
	}
	
	public int[][] getUsedBlock() {
		return usedBlock;
	}

	public void setUsedBlock(int[][] usedBlock) {
		this.usedBlock = usedBlock;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return "GameStorage [usedBlock=" + this.usedBlock + ", shape=" + shape + ", totalScore="
				+ totalScore + "]";
	}

	

	
	
	

}
