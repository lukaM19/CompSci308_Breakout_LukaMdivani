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
  public static final int WALL_SIZE_HORIZONTAL = 290;
  public static final int WALL_SIZE_VERTICAL = 350;
  public static final int WALL_WIDTH = 20;
  public static final int BALL_RADIUS=10;
  private Group root;
  private Stage myStage;
  private Scene myScene;
  /**
   * Initialize what will be displayed.
   */
  @Override
  public void start(Stage stage) throws Exception {
    Ball myBall = new Ball(SIZE_HORIZONTAL/2, WALL_SIZE_VERTICAL, BALL_RADIUS);

    Paddle myPaddle = new Paddle(PADDLE_START_POSITION, WALL_SIZE_VERTICAL,SIZE_HORIZONTAL,SIZE_VERTICAL);

    myStage=stage;
    ArrayList<Wall> wallList
        = new ArrayList<>();
    Group walls = new Group();

    Wall topWall = new Wall(0,0,WALL_SIZE_HORIZONTAL,WALL_WIDTH);
    Wall sideWallLeft = new Wall(270,20,WALL_WIDTH,WALL_SIZE_VERTICAL);
    Wall sideWallRight = new Wall(  0,20,WALL_WIDTH,WALL_SIZE_VERTICAL);

    walls.getChildren().addAll(topWall.getWallNode(),sideWallLeft.getWallNode(),sideWallRight.getWallNode());
    wallList.add(topWall);
    wallList.add(sideWallLeft);
    wallList.add(sideWallRight);

    LevelSetup ls =new LevelSetup();
    ls.readFileTo2DArray(2);




    root = new Group();
    setUpRoot(myBall, myPaddle, walls, ls);

    myScene = new Scene(root, SIZE_HORIZONTAL, SIZE_VERTICAL, Color.DARKBLUE);
    myStage.setScene(myScene);

    myStage.setTitle(TITLE);


    myScene.setOnKeyPressed(e -> {
      myPaddle.handleKeyInput(e.getCode());
      myBall.resetBall(e.getCode());
      myPaddle.resetPaddleLocation(e.getCode());
    });
    myStage.show();
    //Timeline
    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY,myPaddle,myBall, wallList,ls));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();



  }

  private void setUpRoot(Ball myBall, Paddle myPaddle, Group walls, LevelSetup ls) {
    root.getChildren().add(myBall.getBallNode());
    root.getChildren().add(myPaddle.getPaddleNode());
    root.getChildren().add(walls);
    ls.addLevelLayoutToRoot(root);
  }

  public void removeBlockFromScene(Rectangle rect){
      root.getChildren().remove(rect);
      myScene.setRoot(root);
      myStage.setScene(myScene);


  }

  public void changeLevel(int levelId){
    root.getChildren().removeAll();

  }


  public void step(double elapsedTime, Paddle myPaddle, Ball myBall, ArrayList<Wall> walls,LevelSetup ls){


    myBall.move(elapsedTime);
    myBall.wallDeflectBall(walls);
    myBall.paddleDeflectBall(myPaddle);
    ls.checkAndHandleBallBlockCollision(myBall,root,myScene);
    ls.handlePowerUp(root,myScene,elapsedTime,myBall,myPaddle);


  }




}
