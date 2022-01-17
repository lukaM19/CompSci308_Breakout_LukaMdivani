package breakout;

import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import breakout.Main;

public class Paddle {

  private static final double DEFAULT_SPEED = 0.0;
  private static final double PADDLE_SPEED = 10.0;
  public static final double DEFAULT_PADDLE_WIDTH = 50.0;
  public static final double LARGE_PADDLE_WIDTH = 150.0;
  private static final double PADDLE_HEIGHT = 20.0;
  public static final int BORDER_CHANGE_CUTOFF = 5;
  public  final int WINDOW_WIDTH;
  public final int WINDOW_HEIGHT;

  private Rectangle paddle;
  private double paddleSpeed;
  private boolean paddleVerticalMove;

  public Paddle(double xPos, double yPos, int width, int height) {
    paddle = new Rectangle();
    paddle.setX(xPos);
    paddle.setY(yPos+PADDLE_HEIGHT);
    paddle.setWidth(DEFAULT_PADDLE_WIDTH);
    paddle.setHeight(PADDLE_HEIGHT);
    paddle.setFill(Color.BROWN);
    paddle.setStroke(Color.BLACK);
    paddle.setEffect(new Lighting());
    paddleSpeed = DEFAULT_SPEED;
    paddleVerticalMove = false;
    WINDOW_WIDTH= width;
    WINDOW_HEIGHT= height;


  }

  public Rectangle getPaddleNode() {
    return paddle;
  }

  private void setXPos(double xPos) {
    paddle.setX(xPos);
  }

  public double getXPos() {
    return paddle.getX();
  }

  private void setYPos(double yPos) {
    paddle.setY(yPos);
  }

  private double getYPos() {
    return paddle.getY();
  }

  private double getPaddleSpeed() {
    return paddleSpeed;
  }

  private double getPaddleWidth() {
    return paddle.getWidth();
  }

  private void handleBorderChange() {
    if (paddle.getX() < -0.9 * DEFAULT_PADDLE_WIDTH) {
      paddle.setX(285);
    }
    if (paddle.getX() > 285) {
      paddle.setX(-5);
    }
  }

  public void changePaddleVerticalMove() {
    paddleVerticalMove = !paddleVerticalMove;
  }

  public void handleKeyInput(KeyCode code) {
    switch (code) {
      case RIGHT -> paddle.setX(paddle.getX() + PADDLE_SPEED);
      case LEFT -> paddle.setX(paddle.getX() - PADDLE_SPEED);

    }
    handleBorderChange();
    if (paddleVerticalMove) {
      switch (code) {
        case UP -> paddle.setY(paddle.getY() - PADDLE_SPEED);
        case DOWN -> paddle.setY(paddle.getY() + PADDLE_SPEED);
      }
    }
  }

}
