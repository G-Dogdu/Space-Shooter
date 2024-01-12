import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class fire {

    private int x;
    private int y;

    public fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(5, this);
    private int time = 0;
    private int ammo_fired = 0;
    private BufferedImage image;
    private ArrayList<fire> shots = new ArrayList<fire>();
    private int firedirY = 3;
    private int ballX = 0;
    private int balldirX = 5;
    private int SShipX = 0;
    private int dirSShipX = 20;

    public boolean checker(){

        for (fire fire: shots) {

            if(new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(ballX, 0, 20, 20))){

                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        time += 5;
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(image, SShipX, 490, image.getWidth() / 10, image.getHeight() / 10, this);

        for (fire fire : shots) {

            if (fire.getY() < 0) {
                shots.remove(fire);
            }
        }
        g.setColor(Color.blue);
        for (fire fire : shots) {

            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }

        if (checker()){
            timer.stop();
            String message = "You won!!!!! \n" + "Fired shots : " + ammo_fired;

            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    public Game() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("src/spaceship.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(Color.BLACK);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (fire fire : shots) {
            fire.setY(fire.getY() - firedirY);
        }
        ballX += balldirX;
        if (ballX >= 750) {
            balldirX = -balldirX;
        }
        if (ballX <= 0) {
            balldirX = -balldirX;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            if (SShipX <= 0) {
                SShipX = 0;
            } else {
                SShipX -= dirSShipX;
            }
        } else if (c == KeyEvent.VK_RIGHT) {

            if (SShipX >= 750) {
                SShipX = 750;
            } else {
                SShipX += dirSShipX;
            }
        } else if (c == KeyEvent.VK_CONTROL) {
            shots.add(new fire(SShipX + 30, 490));
            ammo_fired++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
