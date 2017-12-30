package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;


@SuppressWarnings("serial")
public class Game2 extends JPanel{

    Ball ball = new Ball(this);
    Raquet raquet = new Raquet(this);
    int speed = 1;

    private int getScore(){
        return speed - 1;
    }

    public Game2(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                raquet.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                raquet.keyReleased(e);
            }
        });
        setFocusable(true);
    }

    private void move(){
        ball.move();
        raquet.move();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        raquet.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()),10,30);
    }

    public void gameOver(){
        JOptionPane.showMessageDialog(this,
                "your score is: " + getScore(), "Game over",
                JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args)throws InterruptedException{
        JFrame frame = new JFrame("Mini Tennis");
        Game2 game = new Game2();
        frame.add(game);
        frame.setSize(300,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            game.move();
            game.repaint();
            Thread.sleep(10);
        }

    }

}
