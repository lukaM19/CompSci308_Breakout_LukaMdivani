package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {


  private double effectiveWallWidth;

  private Rectangle wall;

  public Wall(double xPos, double yPos, double wallSizeHorizontal, double wallSizeVertical) {
    wall = new Rectangle(xPos, yPos, wallSizeHorizontal, wallSizeVertical);
    wall.setFill(Color.GRAY);
    wall.setStroke(Color.BLACK);
    effectiveWallWidth = wallSizeHorizontal;
  }

  public Rectangle getWallNode() {
    return wall;
  }

  public double getWidth() {
    return effectiveWallWidth;
  }


}
