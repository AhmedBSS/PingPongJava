import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

  private static final int GAME_WIDTH = 1000;
  private static final int GAME_HEIGHT = (int) ((0.555) * GamePanel.GAME_WIDTH);
  private static final Dimension SCREEN_SIZE = new Dimension(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
  private static final int BALL_SIZE = 20;
  private static final int PADDLE_WIDTH = 25;
  private static final int PADDLE_HEIGHT = 100;

  private Thread thread;
  private Image image;
  private Graphics graphics;
  private Random rand;
  private Paddle player1;
  private Paddle player2;
  private Ball ball;
  private Score score;

  public GamePanel() {
    newPaddle();
    newBall();
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
    this.setFocusable(true); /* Recevoir les événements clavier et souris */
    this.addKeyListener(new AL());
    this.setPreferredSize(SCREEN_SIZE);

    thread = new Thread(this);
    thread.start();
  }

  public void newBall() {
    rand = new Random();
    ball = new Ball((GAME_WIDTH / 2) - (BALL_SIZE / 2), rand.nextInt(GAME_HEIGHT - BALL_SIZE), BALL_SIZE, BALL_SIZE);
  }

  public void newPaddle() {

    player1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);

    player2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
        PADDLE_HEIGHT, 2);
  }

  public void paint(Graphics g) {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  public void draw(Graphics g) {
    player1.draw(g);
    player2.draw(g);
    ball.draw(g);
    score.draw(g);
  }

  public void move() {
    player1.move();
    player2.move();
    ball.move();

  }

  public void checkCollision() {

    // assurer que ball ne sort pas du l'ecran

    if (ball.y <= 0 || ball.y >= GAME_HEIGHT - BALL_SIZE)
      ball.setYDirection(-ball.getYVelocity());

    if (ball.intersects(player1)) {
      ball.setXVelocity(-ball.getXVelocity());
      ball.setXVelocity(ball.getXVelocity() + 1);
      if (ball.getYVelocity() > 0)
        ball.setYVelocity(ball.getYVelocity() + 1);
      else
        ball.setYVelocity(ball.getYVelocity() - 1);
      ball.setXDirection(ball.getXVelocity());
      ball.setYDirection(ball.getYVelocity());
    }

    if (ball.intersects(player2)) {
      ball.setXVelocity(-ball.getXVelocity());
      ball.setXVelocity(ball.getXVelocity() + 1);
      if (ball.getYVelocity() > 0)
        ball.setYVelocity(ball.getYVelocity() + 1);
      else
        ball.setYVelocity(ball.getYVelocity() - 1);
      ball.setXDirection(-ball.getXVelocity());
      ball.setYDirection(ball.getYVelocity());
    }

    // assurer que les Paddle ne sort pas du l'ecran

    if (player1.y <= 0)
      player1.y = 0;
    if (player1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
      player1.y = GAME_HEIGHT - PADDLE_HEIGHT;
    if (player2.y <= 0)
      player2.y = 0;
    if (player2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
      player2.y = GAME_HEIGHT - PADDLE_HEIGHT;

    // donner 1 point pour les joueur

    if (ball.x <= 0) {
      score.setScore(2);
      newPaddle();
      newBall();
    }

    if (ball.x >= GAME_WIDTH - BALL_SIZE) {
      score.setScore(1);
      newPaddle();
      newBall();
    }

  }

  public void run() {
    // THREAD !!!
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;

    while (true) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      if (delta >= 1) {
        move();
        checkCollision();
        repaint();
        delta--;
      }
    }
  }

  public class AL extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
      player1.keyPressed(e);
      player2.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
      player1.keyReleased(e);
      player2.keyReleased(e);
    }
  }
}
