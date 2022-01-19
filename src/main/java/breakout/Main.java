package breakout;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author YOUR NAME HERE
 */
public class Main extends Application {

  // useful names for constant values used
  public static final String TITLE = "BREAKOUT";
  public static final int SIZE_VERTICAL = 400;
  public static final int PADDLE_START_POSITION  = 120;
  public static final int SIZE_HORIZONTAL = 290;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
  public static final int WALL_SIZE_VERTICAL = 350;
  public static final int WALL_WIDTH = 20;
  public static final int BALL_RADIUS=10;
  private Group root;
  private Stage myStage;
  private Scene myScene;
  private LevelSetup myLevel;
  private Group walls;
  private ArrayList<Wall> wallList;
  private Ball myBall;
  private Paddle myPaddle;
  private static int playerLives=3;
  private int curLevelID=0;
  /**
   * Initialize what will be displayed.
   */
  @Override
  public void start(Stage stage) throws Exception {
    myBall = new Ball(SIZE_HORIZONTAL/2, WALL_SIZE_VERTICAL, BALL_RADIUS);

    myPaddle = new Paddle(PADDLE_START_POSITION, WALL_SIZE_VERTICAL,SIZE_HORIZONTAL,SIZE_VERTICAL);

    myStage=stage;

    root =setUpRoot(myBall, myPaddle,curLevelID);

    myScene = new Scene(root, SIZE_HORIZONTAL, SIZE_VERTICAL, Color.DARKBLUE);
    myStage.setScene(myScene);

    myStage.setTitle(TITLE);


    myScene.setOnKeyPressed(e -> {
      myPaddle.handleKeyInput(e.getCode());
      try {
        cheatKeys(e.getCode());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    myStage.show();
    //Timeline
    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
      try {
        step(SECOND_DELAY,myPaddle,myBall);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();



  }

  private Group setUpRoot(Ball myBall, Paddle myPaddle, int levelID) throws Exception {
    Group newRoot = new Group();
    myLevel =new LevelSetup();
    myLevel.readFileTo2DArray(levelID);
    Group walls=myLevel.createWalls();
    wallList= myLevel.getLevelWallList();
    newRoot.getChildren().add(myBall.getBallNode());
    newRoot.getChildren().add(myPaddle.getPaddleNode());
    newRoot.getChildren().add(walls);
    myLevel.addLevelLayoutToRoot(newRoot);

    return newRoot;
  }


  public void changeLevel(int levelId) throws Exception {
    resetPaddleAndBall();
    root=setUpRoot(myBall, myPaddle,levelId);

    myScene.setRoot(root);
    myStage.setScene(myScene);
  }

  public void cheatKeys(KeyCode code) throws Exception {
    switch (code){
      case DIGIT1 -> {changeLevel(0); curLevelID=0;}
      case DIGIT2 -> {changeLevel(1);curLevelID=1;}
      case DIGIT3 -> {changeLevel(2);curLevelID=2;}
      case R -> resetPaddleAndBall();
      case L -> increaseLives();
      case E -> myPaddle.paddleGetPowerUp();
      case S -> myBall.ballCheatSlowDown();

    }
  }
  public void resetPaddleAndBall() {myBall.resetBall();myPaddle.resetPaddleLocation();}
  public void autoIncreaseLevels() throws Exception{
    curLevelID=curLevelID+1;
    if (curLevelID<3){
    changeLevel(curLevelID);
    }
  }
  public static void increaseLives(){
    playerLives=playerLives+3;
  }
  public void step(double elapsedTime, Paddle myPaddle, Ball myBall) throws Exception {


    myBall.move(elapsedTime);
    myBall.wallDeflectBall(wallList);
    myBall.paddleDeflectBall(myPaddle);
    myLevel.checkAndHandleBallBlockCollision(myBall,root,myScene);
    myLevel.handlePowerUp(root,myScene,elapsedTime,myBall,myPaddle);
    if (myBall.checkBallOutofBounds()){
      playerLives--;
      myBall.resetBall();
      myPaddle.resetPaddleLocation();
    }
    if(playerLives<0)
    {System.out.println("GAME OVER");}
    if(myLevel.checkWinCondition())
    { autoIncreaseLevels();}
  }




}
