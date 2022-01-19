package breakout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class LevelSetup {

  private static final String[] LEVELS = {"lvl1.txt", "lvl2.txt", "lvl3.txt"};
  private static final String FILEPATH = "D:\\CS308\\breakout_lm378\\src\\main\\resources\\";
  private static final double GAP_BETWEEN_BLOCKS = 0.0;
  private static final double DEFAULT_BLOCK_COORDINATE = Main.WALL_WIDTH + 0.5 * GAP_BETWEEN_BLOCKS;
  public static final int WALL_SIZE_HORIZONTAL = 290;
  public static final int WALL_SIZE_VERTICAL = 350;
  public static final int WALL_WIDTH = 20;

  private int fileColumnNumber = 0;
  private int fileRowNumber = 0;
  private double levelBlockSize;
  private static int lives=3;
  private double startYPos = DEFAULT_BLOCK_COORDINATE;
  private double startXPos = DEFAULT_BLOCK_COORDINATE;
  private ArrayList<ArrayList<Integer>> blockInfoMatrix = new ArrayList<ArrayList<Integer>>();
  private Rectangle[][] myBlocks;
  private int[][] blockHealth ;
  private boolean availablePowerUp=false;
  private ArrayList<PowerUp> availblePowerUps= new ArrayList<>();
  private ArrayList<Wall> levelWallList = new ArrayList<>();

  public void readFileTo2DArray(int i) throws Exception {
    File levelFile = new File(FILEPATH + LEVELS[i]);
    BufferedReader buffReader
        = new BufferedReader(new FileReader(levelFile));
    String lineString;

    fileColumnNumber = 0;
    fileRowNumber = 0;

    blockInfoMatrix = new ArrayList<ArrayList<Integer>>();

    while ((lineString = buffReader.readLine()) != null) {

      String[] lineElements = lineString.split(" ");
      if (fileRowNumber == 0) {
        fileColumnNumber = lineElements.length;
      }

      ArrayList<Integer> currentRowElements = new ArrayList<Integer>();
      for (String numberSting : lineElements) {
        currentRowElements.add(Integer.parseInt(numberSting));

      }
      blockInfoMatrix.add(currentRowElements);
      fileRowNumber++;
    }
    getBlockHealthInfo();
    createBlocks();
  }

  public void createBlocks() {
    levelBlockSize =
        (Main.SIZE_HORIZONTAL - 2 * Main.WALL_WIDTH - fileColumnNumber * GAP_BETWEEN_BLOCKS)
            / fileColumnNumber;
    myBlocks = new Rectangle[fileRowNumber][fileColumnNumber];
    for (int i = 0; i < fileRowNumber; i++) {
      startXPos = DEFAULT_BLOCK_COORDINATE;
      for (int j = 0; j < fileColumnNumber; j++) {
        Rectangle newBlock = new Rectangle(startXPos, startYPos, levelBlockSize, levelBlockSize);
        newBlock.setStroke(Color.BLACK);
        startXPos = startXPos + levelBlockSize + GAP_BETWEEN_BLOCKS;
        int currentBlockType = blockInfoMatrix.get(i).get(j);

        if (currentBlockType != 5) {

          if (currentBlockType == 1) {
            newBlock.setFill(Color.LEMONCHIFFON);
          } else if (currentBlockType == 2) {
            newBlock.setFill(Color.PURPLE);
          } else if (currentBlockType == 3) {
            newBlock.setFill(Color.ORANGE);
          } else if (currentBlockType == 0) {
            newBlock.setFill(Color.DARKGRAY);
          }
          myBlocks[i][j] = newBlock;

        }
      }
      startYPos = startYPos + levelBlockSize + GAP_BETWEEN_BLOCKS;
    }


  }

  public void getBlockHealthInfo() {
     blockHealth = new int[fileRowNumber][fileColumnNumber];
    for (int i = 0; i < fileRowNumber; i++) {
      for (int j = 0; j < fileColumnNumber; j++) {
        if (blockInfoMatrix.get(i).get(j) == 3) {
          blockHealth[i][j] = 2;
        } else if (blockInfoMatrix.get(i).get(j) == 0) {
          blockHealth[i][j] = 0;
        } else {
          blockHealth[i][j] = 1;
        }
      }
    }
  }

  public void addLevelLayoutToRoot(Group root){
    for (int i = 0; i < fileRowNumber; i++) {
      for (int j = 0; j < fileColumnNumber; j++) {
        if(myBlocks[i][j]!=null) {
          root.getChildren().add(myBlocks[i][j]);
        }
      }
    }
  }


  public Group createWalls(){
    Group walls = new Group();

    Wall topWall = new Wall(0,0,WALL_SIZE_HORIZONTAL,WALL_WIDTH);
    Wall sideWallLeft = new Wall(270,20,WALL_WIDTH,WALL_SIZE_VERTICAL);
    Wall sideWallRight = new Wall(  0,20,WALL_WIDTH,WALL_SIZE_VERTICAL);

    walls.getChildren().addAll(topWall.getWallNode(),sideWallLeft.getWallNode(),sideWallRight.getWallNode());
    levelWallList.add(topWall);
    levelWallList.add(sideWallLeft);
    levelWallList.add(sideWallRight);
    return walls;
  }
  public ArrayList<Wall> getLevelWallList(){return levelWallList;}


  public void checkAndHandleBallBlockCollision(Ball myBall,Group root, Scene scene) {

    for (int i = 0; i < fileRowNumber; i++) {
      for (int j = 0; j < fileColumnNumber; j++) {
        if(blockInfoMatrix.get(i).get(j) != 5 ) {
          Rectangle currentBlock = myBlocks[i][j];
          if (myBall.checkBlockIntersectionAndDeflectBall(currentBlock, levelBlockSize) ) {
            blockHealth[i][j]--;
            if(blockHealth[i][j]==0){
              if(blockInfoMatrix.get(i).get(j)==2) {
                createAndAddPowerUpToRoot(root, i, j);
              }
              removeDestroyedBlockFromRoot(root, scene, i, j);

            }
          }
        }
      }
    }
  }

  private void removeDestroyedBlockFromRoot(Group root, Scene scene, int i, int j) {
    root.getChildren().remove(myBlocks[i][j]);
    blockInfoMatrix.get(i).set(j,5);
    scene.setRoot(root);
  }

  private void createAndAddPowerUpToRoot(Group root, int i, int j) {
    PowerUp newPowerUp=new PowerUp(myBlocks[i][j].getX() + levelBlockSize / 2,
        myBlocks[i][j].getY() + levelBlockSize / 2);
    availblePowerUps.add( newPowerUp);
    availablePowerUp=true;
    root.getChildren().add(newPowerUp.getBallNode());
  }

  public void handlePowerUp(Group root, Scene scene, double elapsedTime,Ball myBall, Paddle myPaddle) {
    if (availablePowerUp) {
      ArrayList<PowerUp> removablePowerUps= new ArrayList<>();
      for (PowerUp currentPowerUp : availblePowerUps) {
        currentPowerUp.move(elapsedTime);
        if(currentPowerUp.checkPowerUpActions(myPaddle, myBall, root, scene)){
          removablePowerUps.add(currentPowerUp);
        }
      }
      for(PowerUp currentPowerUp:removablePowerUps ){
        availblePowerUps.remove(currentPowerUp);
      }
    }
  }

}
