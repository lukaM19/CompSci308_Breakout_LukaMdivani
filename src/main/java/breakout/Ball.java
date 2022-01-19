package breakout;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Ball {

  private final double DEFAULT_X_POS;
  private final double DEFAULT_Y_POS;
  private final int DEFAULT_BALL_SPEED_Y = 90;
  private final int DEFAULT_BALL_SPEED_X = 1;
  private final double EDGE_DEFLECT_ANGLE_INCREMENT = 15.0;
  private final double BALL_SPEED_POWER_UP_FACTOR = 1.05;
  private final double BALL_SPEED_SLOW_FACTOR = 0.7;

  private Circle ball;
  private Point2D ballSpeed;

  public Ball(double xPos, double yPos, double ballRadius) {
    ball = new Circle();
    ball.setCenterX(xPos);
    ball.setCenterY(yPos);
    ball.setRadius(ballRadius);
    ball.setFill(Color.HOTPINK);
    ball.setStroke(Color.BLACK);
    ball.setEffect(new Lighting());
    ballSpeed = new Point2D(DEFAULT_BALL_SPEED_X, DEFAULT_BALL_SPEED_Y);
    DEFAULT_X_POS = xPos;
    DEFAULT_Y_POS = yPos;


  }

  public Circle getBallNode() {
    return ball;
  }

  public void move(double elapsedTime) {
    ball.setCenterX(ball.getCenterX() + ballSpeed.getX() * elapsedTime);
    ball.setCenterY(ball.getCenterY() + ballSpeed.getY() * elapsedTime);
  }

  public void paddleDeflectBall(Paddle myPaddle) {
    if (ball.intersects(myPaddle.getPaddleNode().getBoundsInLocal())) {

      if (ball.getCenterX() >= myPaddle.getXPos() + 0.75 * myPaddle.getPaddleNode().getWidth()) {
        ballSpeed = new Point2D(ballSpeed.getX() + EDGE_DEFLECT_ANGLE_INCREMENT, -ballSpeed.getY());
      } else if (ball.getCenterX() <= myPaddle.getXPos() + 0.25 * myPaddle.getPaddleNode()
          .getWidth()) {
        ballSpeed = new Point2D(ballSpeed.getX() - EDGE_DEFLECT_ANGLE_INCREMENT, -ballSpeed.getY());
      } else {
        ballSpeed = new Point2D(ballSpeed.getX(), -ballSpeed.getY());
      }
    }
  }

  public void wallDeflectBall(ArrayList<Wall> myWalls) {

    for (Wall myWall : myWalls) {

      boolean intersectBool = checkBlockIntersectionAndDeflectBall(myWall.getWallNode(),
          myWall.getWidth());
    }
  }

  public boolean checkBlockIntersectionAndDeflectBall(Rectangle myRect, double blockWidth) {
    boolean intersectionCheck = false;
    if (ball.intersects(myRect.getBoundsInLocal())) {
      intersectionCheck = true;
      if (myRect.getX() <= ball.getCenterX() && myRect.getX() + blockWidth >= ball.getCenterX()) {
        ballSpeed = new Point2D(ballSpeed.getX(), -ballSpeed.getY());

      } else {
        ballSpeed = new Point2D(-ballSpeed.getX(), ballSpeed.getY());
      }

    }
    return intersectionCheck;
  }


  public void resetBall() {

    ball.setCenterX(DEFAULT_X_POS);
    ball.setCenterY(DEFAULT_Y_POS);
    ballSpeed = new Point2D(DEFAULT_BALL_SPEED_X, DEFAULT_BALL_SPEED_Y);

  }

  public void ballGetPowerUp() {
    ballSpeed = new Point2D(BALL_SPEED_POWER_UP_FACTOR * ballSpeed.getX(),
        BALL_SPEED_POWER_UP_FACTOR * ballSpeed.getY());
  }

  public void ballCheatSlowDown() {
    ballSpeed = new Point2D(BALL_SPEED_SLOW_FACTOR * ballSpeed.getX(),
        BALL_SPEED_SLOW_FACTOR * ballSpeed.getY());
  }

  public boolean checkBallOutOfBounds() {
    return ball.getCenterY() >= Main.SIZE_VERTICAL;
  }

}
