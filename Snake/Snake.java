import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Rectangle> body;

    private String move ;//Nothing,up,down,left,right

    public Snake()
    {
        body = new ArrayList<>();

        Rectangle temp = new Rectangle(Utility.Dimension,Utility.Dimension);
        temp.setLocation(Utility.width/2 * Utility.Dimension,Utility.height/2 *Utility.Dimension);
        body.add(temp);

        temp = new Rectangle(Utility.Dimension,Utility.Dimension);
        temp.setLocation((Utility.width/2 -1 ) * Utility.Dimension,(Utility.height/2) *Utility.Dimension);
        body.add(temp);

        temp = new Rectangle(Utility.Dimension,Utility.Dimension);
        temp.setLocation((Utility.width/2 - 2 ) * Utility.Dimension,(Utility.height/2) *Utility.Dimension);
        body.add(temp);

        move = "NOTHING";
    }

    public void move()
    {
        if(!move.equals("NOTHING")) {
            body = moveGrow();
            body.remove(body.size()-1);
        }
    }

    public ArrayList<Rectangle> moveGrow()
    {
        Rectangle first = body.get(0);
        Rectangle temp = new Rectangle(Utility.Dimension, Utility.Dimension);

        if(move.equals("UP")) {
            temp.setLocation(first.x, first.y - Utility.Dimension);
        }
        else if(move.equals("DOWN")) {
            temp.setLocation(first.x, first.y + Utility.Dimension);
        }
        else if(move.equals("LEFT")) {
            temp.setLocation(first.x - Utility.Dimension, first.y);
        }
        else{
            temp.setLocation(first.x + Utility.Dimension, first.y);
        }
        body.add(0, temp);

        return body;

    }

    public void grow(){
        body = moveGrow();
    }

    public ArrayList<Rectangle> getBody() {
        return body;
    }


    // to check wall collision
    // ----------------------------------------------
    public int getX()
    {
        return body.get(0).x;
    }

    public int getY()
    {
        return body.get(0).y;
    }
    // ----------------------------------------------

    public void up()
    {
        if(!move.equals("DOWN"))
            move = "UP";
    }

    public void down()
    {
        if(!move.equals("UP"))
            move = "DOWN";
    }
    public void left()
    {
        if(!move.equals("RIGHT"))
            move = "LEFT";
    }
    public void right()
    {
        if(!move.equals("LEFT"))
            move = "RIGHT";
    }

}
