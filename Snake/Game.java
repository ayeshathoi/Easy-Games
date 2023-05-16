import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private final Snake player;
    private final Food  food;
    private final Graphics graphics;

    public Game()
    {
        JFrame window = new JFrame();

        player   = new Snake();
        food     = new Food(player);
        graphics = new Graphics(this);

        window.add(graphics);
        window.setTitle("Snake");
        window.setSize(Utility.width * Utility.Dimension ,Utility.height * Utility.Dimension);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
    }

    public Snake getPlayer() {
        return player;
    }

    public Food getFood() {
        return food;
    }

    public void Start()
    {
        graphics.state = "RUNNING";
    }

    public void update()
    {
        if(graphics.state.equals("RUNNING"))
        {
            if(check_FoodCollision())
            {
                player.grow();
                food.rand_spawn(player);
            }
            else if(check_WallCollision() || check_SelfCollision())
                graphics.state = "END";

            else player.move();
        }
    }

    public boolean check_WallCollision()
    {
        if(player.getX() < 0 || player.getX() >= Utility.width * Utility.Dimension || player.getY() < 0
                || player.getY() >= Utility.height * Utility.Dimension)
            return true;
        return false;
    }

    public boolean check_FoodCollision()
    {
        if(player.getX() == food.getX() * Utility.Dimension && player.getY() == food.getY() * Utility.Dimension)
            return true;
        return false;
    }

    public boolean check_SelfCollision()
    {
        for(int i = 1; i < player.getBody().size(); i++)
        {
            if(player.getX() == player.getBody().get(i).x && player.getY() == player.getBody().get(i).y)
                return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(graphics.state.equals("RUNNING")) {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
                player.up();
            else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
                player.down();
            else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
                player.left();
            else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
                player.right();
        }
        else {
            this.Start();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
