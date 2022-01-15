package breakout;

import java.util.Formatter.BigDecimalLayoutForm;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import breakout.Paddle;
import breakout.Ball;
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
  public static final String BALL_COLOR = "LIGHTSTEELBLUE";
  public static final int WALL_SIZE_HORIZONTAL = 290;
  public static final int WALL_SIZE_VERTICAL = 350;
  public static final int WALL_WIDTH = 20;
  public static final int BALL_RADIUS=10;

  /**
   * Initialize what will be displayed.
   */
  @Override
  public void start(Stage stage) {
    Ball myBall = new Ball(SIZE_HORIZONTAL/2, WALL_SIZE_VERTICAL, BALL_RADIUS,SIZE_HORIZONTAL,SIZE_VERTICAL);

    Paddle myPaddle = new Paddle(PADDLE_START_POSITION, WALL_SIZE_VERTICAL,SIZE_HORIZONTAL,SIZE_VERTICAL);


    Group walls = new Group();

    Rectangle topWall = new Rectangle(0,0,WALL_SIZE_HORIZONTAL,WALL_WIDTH);
    topWall.setFill(Color.GRAY);
    topWall.setStroke(Color.BLACK);
    Rectangle sideWallLeft = new Rectangle(270,20,WALL_WIDTH,WALL_SIZE_VERTICAL);
    sideWallLeft.setFill(Color.GRAY);
    sideWallLeft.setStroke(Color.BLACK);
    Rectangle sideWallRight = new Rectangle(  0,20,WALL_WIDTH,WALL_SIZE_VERTICAL);
    sideWallRight.setFill(Color.GRAY);
    sideWallRight.setStroke(Color.BLACK);
    walls.getChildren().addAll(topWall,sideWallLeft,sideWallRight);


    Group root = new Group();
    root.getChildren().add(myBall.getBallNode());
    root.getChildren().add(myPaddle.getPaddleNode());
    root.getChildren().add(walls);
    Scene scene = new Scene(root, SIZE_HORIZONTAL, SIZE_VERTICAL, Color.DARKBLUE);
    stage.setScene(scene);

    stage.setTitle(TITLE);


    scene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));

    stage.show();
    //Timeline
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY,myPaddle,scene,myBall)));
    animation.play();



  }



  public void step(double elapsedTime, Paddle myPaddle, Scene scene,Ball myBall){


    myBall.move(elapsedTime);

    myBall.deflectBall(myPaddle);
  }


}
