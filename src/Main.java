import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args)
    {
      Main screen = new Main("Space Shooter");
      screen.setResizable(false);
      screen.setFocusable(false);
      screen.setSize(800,600);
      screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Game new_game = new Game();
      new_game.requestFocus();
      new_game.addKeyListener(new_game);
      new_game.setFocusable(true);
      new_game.setFocusTraversalKeysEnabled(false);

      screen.add(new_game);
      screen.setVisible(true);
    }
}