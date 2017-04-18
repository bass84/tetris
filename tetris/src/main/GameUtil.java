package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameUtil {
	/*private static PlayingPage playingPageForUtil = PlayingPage.getPlayingPage(false);
	private static Shape shape = playingPageForUtil.getShape();
	private static Grid grid = playingPageForUtil.getGrid();
	private static int[][] usedBlock = playingPageForUtil.getUsedBlock();
	private static int totalScore = playingPageForUtil.getTotalScore();
	
	public static void saveGame(File selection) {
		if(selection != null) {
			String saveFile = selection.getAbsolutePath(); 
			System.out.println("User selected " + saveFile);
			
			try(DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(saveFile)))) {
				// 1. usedBlock write
				for(int i = 0; i < usedBlock.length; i++) {
					for(int j = 0; j < usedBlock[i].length; j++) {
						dos.writeInt(usedBlock[i][j]);
					}
				}
				// 2. totalScore write
				dos.writeInt(totalScore);
				
				// 3. shapeInfo write
				int[][] saveShapeInfo = shape.getShapeInfo();
				for(int i = 0; i < saveShapeInfo.length; i++) {
					for(int j = 0; j < saveShapeInfo[i].length; j++) {
						dos.writeInt(saveShapeInfo[i][j]);
					}
				}
				
				dos.writeInt(shape.getShapeKind().ordinal());
				// 4. positionX write
				dos.writeInt(shape.getPositionX());
				
				// 5. positionY write
				dos.writeInt(shape.getPositionY());
				
				// 6. shapeColor write
				dos.writeInt(shape.getShapeColor());
				
			}catch(IOException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}
		}
	}
	
	public static void loadGame(File selection) {
		if(selection != null) {
			String loadedFile = selection.getAbsolutePath();
			System.out.println("User selected " + loadedFile);
			
			try(DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(loadedFile)))) {
				
				for(int i = 0; i < usedBlock.length; i++) {
					for(int j = 0; j < usedBlock[i].length; j++) {
						usedBlock[i][j] = dis.readInt();
					}
				}
				playingPageForUtil.setUsedBlock(usedBlock);
				// 2. totalScore write
				playingPageForUtil.setTotalScore(dis.readInt());
				
				// 3. shapeInfo write
				int[][] loadShapeInfo = shape.getShapeInfo();
				for(int i = 0; i < loadShapeInfo.length; i++) {
					for(int j = 0; j < loadShapeInfo[i].length; j++) {
						loadShapeInfo[i][j] = dis.readInt();
						
					}
				}
				shape.setShapeInfo(loadShapeInfo);
				// 4. positionX write
				shape.setPositionX(dis.readInt());
				
				// 5. positionY write
				shape.setPositionY(dis.readInt());
				
				// 6. shapeColor write
				shape.setShapeColor(dis.readInt());
				
			}catch(InvalidClassException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}catch(IOException e) {
				System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			}
			
			
		}
		
		
	}*/

}
