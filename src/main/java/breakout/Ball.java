package breakout;

import javafx.geometry.Point2D;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Ball {

    private final int WINDOW_HEIGHT ;
    private final int WINDOW_WIDTH ;
    private final int DEFAULT_BALL_SPEED=90;

    private Circle ball;
    private Point2D ballSpeed;

    public Ball(double xPos, double yPos, double ballRadius,int width, int height) {
        ball = new Circle();
        ball.setCenterX(xPos);
        ball.setCenterY(100);
        ball.setRadius(ballRadius);
        ball.setFill(Color.HOTPINK);
        ball.setStroke(Color.BLACK);
        ball.setEffect(new Lighting());
        ballSpeed = new Point2D(0,DEFAULT_BALL_SPEED);
        WINDOW_WIDTH= width;
        WINDOW_HEIGHT= height;


    }

    public Circle getBallNode() {
        return ball;
    }

    public void move (double elapsedTime) {
        ball.setCenterX(ball.getCenterX() + ballSpeed.getX() * elapsedTime);
        ball.setCenterY(ball.getCenterY() + ballSpeed.getY() * elapsedTime);
    }
    public void deflectBall(Paddle myPaddle){
        if(ball.intersects( myPaddle.getPaddleNode().getBoundsInLocal())){
            if (   ball.getCenterX() >= myPaddle.getXPos()+0.75*myPaddle.DEFAULT_PADDLE_WIDTH){
                ballSpeed=new Point2D(15, -ballSpeed.getY());
            }
            else if (ball.getCenterX() <= myPaddle.getXPos()+0.25*myPaddle.DEFAULT_PADDLE_WIDTH){
                ballSpeed=new Point2D(-15, -ballSpeed.getY());
            }
            else {
                ballSpeed=new Point2D(0, -ballSpeed.getY());
            }
        }
    }
}
