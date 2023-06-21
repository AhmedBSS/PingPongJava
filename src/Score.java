import java.awt.*;

public class Score extends Rectangle{

  private static int GAME_WIDTH;
  private static int GAME_HEIGHT;
  private int player1;
  private int player2;

  public Score(int gameWidth, int gameHeight) {
    Score.GAME_HEIGHT = gameHeight;
    Score.GAME_WIDTH = gameWidth;
  }

  public void setScore(int player){
    if(player == 1)
      this.player1++;
    else if(player == 2)
      this.player2++;
  }

  public void draw (Graphics g){
    g.setColor(Color.white);
    g.setFont(new Font(null, Font.PLAIN, 60));
    g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);
    g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),(GAME_WIDTH/2)-85,50 );
    g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),(GAME_WIDTH/2)+20,50 );

  }
}
