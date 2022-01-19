package breakout;


import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class PowerUp extends Ball {

  private final double DEFAULT_X_SPEED = 0;
  private final double DEFAULT_Y_SPEED = 180;
  private final double POWER_UP_RADIUS = 5;
  private final Point2D DEFAULT_POWER_UP_SPEED = new Point2D(DEFAULT_X_SPEED, DEFAULT_Y_SPEED);


  private int powerUpType;

  public PowerUp(double xPos, double yPos) {
    super(xPos, yPos, 5);
    Random ran = new Random();
    powerUpType = ran.nextInt(3);
    setBallPropertiesAccordingToType(powerUpType);
  }

  private void setBallPropertiesAccordingToType(int powerUpType) {
    if (powerUpType == 0) {
      this.getBallNode().setFill(Color.RED);
    }
    if (powerUpType == 1) {
      this.getBallNode().setFill(Color.GREEN);
    }
    if (powerUpType == 2) {
      this.getBallNode().setFill(Color.BLUE);
    }

  }

  @Override
  public void move(double elapsedTime) {
    super.move(elapsedTime);
  }

  public boolean checkPowerUpStatus(Paddle myPaddle, Ball myBall, Group root, Scene scene) {
    boolean powerUpDestroyed = false;
    if (this.getBallNode().intersects(myPaddle.getPaddleNode().getBoundsInLocal())) {

      if (powerUpType == 0) {
        myBall.ballGetPowerUp();
      }
      if (powerUpType == 1) {
        Main.increaseLives();
      }
      if (powerUpType == 2) {
        myPaddle.paddleGetPowerUp();
      }
      root.getChildren().remove(this.getBallNode());
      scene.setRoot(root);
      powerUpDestroyed = true;


    } else if (this.checkBallOutOfBounds()) {
      root.getChildren().remove(this.getBallNode());
      scene.setRoot(root);
      powerUpDestroyed = true;

    }
    return powerUpDestroyed;
  }

}
