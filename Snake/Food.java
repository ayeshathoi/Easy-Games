import java.awt.*;

public class Food {

    private int x;
    private int y;

    public Food(Snake player)
    {
        this.rand_spawn(player);
    }

    public void rand_spawn(Snake player)
    {
        // check if the position is on snake or not
        boolean onSnake = true;

        while(onSnake)
        {
            onSnake = false;
            x = (int)(Math.random() * Utility.width - 1);
            y = (int)(Math.random() * Utility.height - 1);

            for(Rectangle r : player.getBody())
            {
                if (r.x == x && r.y == y)
                    onSnake = true;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
