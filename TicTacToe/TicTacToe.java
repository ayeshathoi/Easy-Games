import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    //randomly select whose turn is first
    Random random = new Random();

    JFrame Frame        = new JFrame();
    JPanel title_Panel  = new JPanel();
    JPanel button_Panel = new JPanel();
    JLabel textField    = new JLabel();
    JButton[] button    = new JButton[9];
    Boolean player1_turn;

    public TicTacToe()
    {
        //---------------------------------------------------------------------------
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(800,800);
        Frame.getContentPane().setBackground(new Color(50,50,50));
        Frame.setLayout(new BorderLayout());
        Frame.setVisible(true);
        //----------------------------------------------------------------------------
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free",Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("TIC-TAC-TOE");
        textField.setOpaque(true);
        //----------------------------------------------------------------------------
        title_Panel.setLayout(new BorderLayout());
        title_Panel.setBounds(0,0,800,100);
        //----------------------------------------------------------------------------
        button_Panel.setLayout(new GridLayout(3,3));
        button_Panel.setBackground(new Color(150,150,150));
        //----------------------------------------------------------------------------
        for(int i = 0;i < 9; i++)
        {
            button[i] = new JButton();
            button_Panel.add(button[i]);
            button[i].setFont(new Font("MV Boli",Font.BOLD,120));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
        }

        //----------------------------------------------------------------------------
        title_Panel.add(textField);
        Frame.add(title_Panel,BorderLayout.NORTH);
        Frame.add(button_Panel);
        //----------------------------------------------------------------------------
        FirstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 9; i++)
        {
            if(e.getSource() == button[i])
            {
                if(player1_turn)
                {
                    if(button[i].getText() == "")
                    {
                        button[i].setForeground(new Color(255,0,0));
                        button[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        Check();
                    }
                }
                else {
                    if(button[i].getText() == "")
                    {
                        button[i].setForeground(new Color(0,0,255));
                        button[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        Check();
                    }
                }
            }
        }

    }

    public void FirstTurn()
    {
        try{
            Thread.sleep(2000); //2000 ms
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if(random.nextInt(2) == 0)
        {
            player1_turn = true;
            textField.setText("X turn");
        }
        else {
            player1_turn = false;
            textField.setText("O turn");
        }

    }

    public void Check()
    {
        // X win condition
        if((button[0].getText() == "X") && (button[1].getText() == "X") && (button[2].getText() == "X"))
            xWins(0,1,2);
        if((button[3].getText() == "X") && (button[4].getText() == "X") && (button[5].getText() == "X"))
            xWins(3,4,5);
        if((button[6].getText() == "X") && (button[7].getText() == "X") && (button[8].getText() == "X"))
            xWins(6,7,8);
        if((button[0].getText() == "X") && (button[3].getText() == "X") && (button[6].getText() == "X"))
            xWins(0,1,2);
        if((button[1].getText() == "X") && (button[4].getText() == "X") && (button[7].getText() == "X"))
            xWins(1,4,7);
        if((button[0].getText() == "X") && (button[4].getText() == "X") && (button[8].getText() == "X"))
            xWins(0,4,8);
        if((button[2].getText() == "X") && (button[4].getText() == "X") && (button[6].getText() == "X"))
            xWins(2,4,6);

        //O win condition
        if((button[0].getText() == "O") && (button[1].getText() == "O") && (button[2].getText() == "O"))
            oWins(0,1,2);
        if((button[3].getText() == "O") && (button[4].getText() == "O") && (button[5].getText() == "O"))
            oWins(3,4,5);
        if((button[6].getText() == "O") && (button[7].getText() == "O") && (button[8].getText() == "O"))
            oWins(6,7,8);
        if((button[0].getText() == "O") && (button[3].getText() == "O") && (button[6].getText() == "O"))
            oWins(0,1,2);
        if((button[1].getText() == "O") && (button[4].getText() == "O") && (button[7].getText() == "O"))
            oWins(1,4,7);
        if((button[0].getText() == "O") && (button[4].getText() == "O") && (button[8].getText() == "O"))
            oWins(0,4,8);
        if((button[2].getText() == "O") && (button[4].getText() == "O") && (button[6].getText() == "O"))
            oWins(2,4,6);

    }

    public void xWins(int a,int b,int c)
    {
        button[a].setBackground(Color.GREEN);
        button[b].setBackground(Color.GREEN);
        button[c].setBackground(Color.GREEN);
        for (int i=0; i<9 ;i++)
        {
            button[i].setEnabled(false);
        }
        textField.setText("X WINS");
    }

    public void oWins(int a,int b,int c)
    {
        button[a].setBackground(Color.cyan);
        button[b].setBackground(Color.cyan);
        button[c].setBackground(Color.cyan);

        for (int i=0; i<9 ;i++)
        {
            button[i].setEnabled(false);
        }
        textField.setText("O WINS");

    }
}
