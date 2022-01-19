package breakout;

import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import breakout.Main;

public class Paddle {

  private static final double DEFAULT_SPEED = 0.0;
  private static final double PADDLE_SPEED = 10.0;
  private static final double DEFAULT_PADDLE_WIDTH = 50.0;
  private static final double PADDLE_HEIGHT = 20.0;
  private final double PADDLE_WIDTH_POWER_UP_FACTOR=1.02;
  private  final double DEFAULT_PADDLE_X_POS;
  private final double DEFAULT_PADDLE_Y_POS;

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
    DEFAULT_PADDLE_X_POS= xPos;
    DEFAULT_PADDLE_Y_POS= yPos;


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
  }

  public void paddleGetPowerUp() {
    paddle.setWidth(PADDLE_WIDTH_POWER_UP_FACTOR* paddle.getWidth());

  }

  public void resetPaddleLocation() {
      paddle.setX(DEFAULT_PADDLE_X_POS);
      paddle.setY(DEFAULT_PADDLE_Y_POS+PADDLE_HEIGHT);
      paddle.setWidth(DEFAULT_PADDLE_WIDTH);

  }


}
