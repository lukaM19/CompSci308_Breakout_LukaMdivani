package breakout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class LevelSetup {
  private static final String [] LEVELS = {"lvl1.txt","lvl2.txt","lvl3.txt"};
  private static final String FILEPATH = "D:\\CS308\\breakout_lm378\\src\\main\\resources\\";
  private static final double GAP_BETWEEN_BLOCKS=10.0;
  private static final  double DEFAULT_BLOCK_COORDINATE=Main.WALL_WIDTH+0.5*GAP_BETWEEN_BLOCKS;

  private int fileColumnNumber=0;
  private int fileRowNumber=0;
  private double levelBlockSize;

  private double startYPos=DEFAULT_BLOCK_COORDINATE;
  private double startXPos=DEFAULT_BLOCK_COORDINATE;
  private ArrayList<ArrayList<Integer>> blockInfoMatrix =new ArrayList<ArrayList<Integer>>();
  private Rectangle[][] myBlocks;
  public Group blockGroup = new Group();

  public void readFileTo2DArray(int i) throws Exception {
    File levelFile = new File(FILEPATH+ LEVELS[i]);
    BufferedReader buffReader
        = new BufferedReader(new FileReader(levelFile));
    String lineString;

    fileColumnNumber=0;
    fileRowNumber=0;

    blockInfoMatrix =new ArrayList<ArrayList<Integer>>();

    while ((lineString = buffReader.readLine()) != null) {

      String[] lineElements = lineString.split(" ");
      if(fileRowNumber == 0)
      {
        fileColumnNumber=lineElements.length;
      }

      ArrayList<Integer> currentRowElements = new ArrayList<Integer>();
      for (String numberSting : lineElements) {
        currentRowElements.add( Integer.parseInt(numberSting));

      }
      blockInfoMatrix.add(currentRowElements);
      fileRowNumber++;
    }
  }

  public void createBlocks() {
    levelBlockSize=(Main.SIZE_HORIZONTAL-2*Main.WALL_WIDTH-fileColumnNumber*GAP_BETWEEN_BLOCKS)/fileColumnNumber;
    blockGroup = new Group();
    myBlocks=new Rectangle[fileRowNumber][fileColumnNumber];
    for (int i=0;i<fileRowNumber;i++){
      startYPos=DEFAULT_BLOCK_COORDINATE;
      for (int j = 0; j < fileColumnNumber; j++){
        Rectangle newBlock = new Rectangle(startXPos,startYPos,levelBlockSize,levelBlockSize);
        newBlock.setStroke(Color.BLACK);
        startYPos=startYPos+levelBlockSize+GAP_BETWEEN_BLOCKS;
        int currentBlockType=blockInfoMatrix.get(i).get(j);

        if (currentBlockType != 5 ) {


        if (currentBlockType == 1) {
            newBlock.setFill(Color.LEMONCHIFFON);
          } else if (currentBlockType == 2) {
            newBlock.setFill(Color.PURPLE);
          } else if (currentBlockType == 3) {
            newBlock.setFill(Color.ORANGE);
          } else if (currentBlockType == 0) {
            newBlock.setFill(Color.DARKGRAY);
          }
        blockGroup.getChildren().add(newBlock);
        myBlocks[i][j]=newBlock;
        System.out.println(currentBlockType);
        }
      }
      startXPos=startXPos+levelBlockSize+GAP_BETWEEN_BLOCKS;
    }


  }

  public int[][] getBlockHealthInfo(){
    int[][] blockHealth = new int[fileColumnNumber][fileRowNumber];
    for (int i=0;i<fileRowNumber;i++){
      for(int j=0;j<fileColumnNumber;j++){
        if(blockInfoMatrix.get(i).get(j)==3){
          blockHealth[i][j]=2;
        }
        else if(blockInfoMatrix.get(i).get(j)==0) {
          blockHealth[i][j]=0;
        }
        else{
          blockHealth[i][j]=1;
        }
      }
    }
    return blockHealth;
  }



}
