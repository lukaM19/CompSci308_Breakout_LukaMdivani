package breakout;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Ball {

    private final double DEFAULT_X_POS ;
    private final double DEFAULT_Y_POS ;
    private final int DEFAULT_BALL_SPEED_Y=90;
    private final int EPSILON=1;


    private Circle ball;
    private Point2D ballSpeed;

    public Ball(double xPos, double yPos, double ballRadius,int width, int height) {
        ball = new Circle();
        ball.setCenterX(xPos);
        ball.setCenterY(yPos);
        ball.setRadius(ballRadius);
        ball.setFill(Color.HOTPINK);
        ball.setStroke(Color.BLACK);
        ball.setEffect(new Lighting());
        ballSpeed = new Point2D(0,DEFAULT_BALL_SPEED_Y);
        DEFAULT_X_POS= xPos;
        DEFAULT_Y_POS= yPos;


    }

    public Circle getBallNode() {
        return ball;
    }

    public void move (double elapsedTime) {
        ball.setCenterX(ball.getCenterX() + ballSpeed.getX() * elapsedTime);
        ball.setCenterY(ball.getCenterY() + ballSpeed.getY() * elapsedTime);
    }
    public void paddleDeflectBall(Paddle myPaddle){
        if(ball.intersects( myPaddle.getPaddleNode().getBoundsInLocal())){

            if (   ball.getCenterX() >= myPaddle.getXPos()+0.75*myPaddle.DEFAULT_PADDLE_WIDTH){
                ballSpeed=new Point2D(ballSpeed.getX()+15, -ballSpeed.getY());
            }
            else if (ball.getCenterX() <= myPaddle.getXPos()+0.25*myPaddle.DEFAULT_PADDLE_WIDTH){
                ballSpeed=new Point2D(ballSpeed.getX()-15, -ballSpeed.getY());
            }
            else {
                ballSpeed=new Point2D( ballSpeed.getX(), -ballSpeed.getY());
            }
        }
    }

    public void horizontalDeflectBall( ArrayList<Rectangle> myRects){
        for(Rectangle myRect : myRects){


        if(ball.intersects( myRect.getBoundsInLocal())){
                if(approximatelyEqual(myRect.getX(),ball.getCenterX()+ball.getRadius()) || approximatelyEqual(myRect.getX(),ball.getCenterX()-ball.getRadius())
                    || approximatelyEqual(myRect.getX()+20,ball.getCenterX()-ball.getRadius()) || approximatelyEqual(myRect.getX()+20,ball.getCenterX()+ball.getRadius())){
                    ballSpeed=new Point2D(-ballSpeed.getX(), ballSpeed.getY());
                }
                else{
                    ballSpeed=new Point2D(ballSpeed.getX(), -ballSpeed.getY());
                }

            }
        }
    }

    public boolean approximatelyEqual (double coord1, double coord2) {

        return Math.abs(coord1-coord2)<=EPSILON;
    }

    public void resetBall(KeyCode code){
        if (code==KeyCode.Q){
            ball.setCenterX(DEFAULT_X_POS);
            ball.setCenterY(DEFAULT_Y_POS);
            ballSpeed=new Point2D(0,DEFAULT_BALL_SPEED_Y);
        }
    }

}
