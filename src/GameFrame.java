import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{
  
  private GamePanel gp ;

  public GameFrame(){
    gp = new GamePanel();
    this.setTitle("Ping Pong");
    this.add(gp);
    this.setResizable(false);
    this.setBackground(Color.BLACK);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack(); /* appelée pour redimensionner la fenêtre JFrame en fonction de la taille préférée de l'objet GamePanel */
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }

}
