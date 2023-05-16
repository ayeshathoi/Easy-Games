import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Graphics extends JPanel implements ActionListener {

    private final Snake snake;
    private final Food  food;
    private final Game  game;

    private final Timer timer = new Timer(Utility.TimerDelay,this);
    public String state;


    public Graphics(Game g)
    {
        timer.start();
        state = "START";
        game  = g;
        food  = g.getFood();
        snake = g.getPlayer();

        //add a keyListener
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics graphics)
    {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D) graphics;

        //background
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0,0, Utility.width * Utility.Dimension,
                Utility.height * Utility.Dimension);


        //state start
        if(state.equals("START"))
        {
            g2D.setColor(Color.white);
            g2D.drawString("Press any key", Utility.width/2 * Utility.Dimension -40 ,
                    Utility.height / 2 * Utility.Dimension - 20 );
        }

        else if(state.equals("RUNNING")) {
            //food
            g2D.setColor(Color.RED);
            g2D.fillRect(food.getX() * Utility.Dimension, food.getY()
                    * Utility.Dimension, Utility.Dimension, Utility.Dimension);

            //snake
            g2D.setColor(Color.green);
            for (Rectangle r : snake.getBody())
                g2D.fill(r);
        }

        else {
            g2D.setColor(Color.white);
            g2D.drawString("Your Score : " + (snake.getBody().size() - 3), Utility.width/2 * Utility.Dimension -40 ,
                    Utility.height / 2 * Utility.Dimension - 20 );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
