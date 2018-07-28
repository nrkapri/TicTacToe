package com.nayank.tictactoe;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    char[][] board = new char [3][3];

    Button[][] buttonBoard = new Button [3][3];

    boolean isSolvedFlag=false;

    boolean xMove=true;
    int xwins =0;
    int ywins =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loadBoard();
        initBoard();

    }

    public void buttonClicked(View cickedButton)
    {
        Button btn = (Button) cickedButton;

        int x=(int)btn.getTag(R.id.KEY_X);
        int y=(int)btn.getTag(R.id.KEY_Y);

        if(isSolvedFlag==true)
        {
            //Toast.makeText(this,"Reset Board first!",Toast.LENGTH_SHORT).show();
            Snackbar.make(this.findViewById(R.id.button1),"Reset Board first!",Snackbar.LENGTH_SHORT).show();
        }
        else if(board[x][y]==' ')
        {
            if(xMove)
            {
                board[x][y]='X';
                buttonBoard[x][y].setText(" X ");
            }
            else
            {
                board[x][y]='O';
                buttonBoard[x][y].setText(" O ");
            }

            isSolved();

            Button buttonX= (Button) findViewById(R.id.button10);

            Button buttonY= (Button) findViewById(R.id.button12);

            buttonX.setText("X won:"+xwins);
            buttonY.setText("O won:"+ywins);
            if(!xMove)
            {
                buttonX.setBackgroundColor(Color.GREEN);
                buttonY.setBackgroundColor(Color.GRAY);
            }
            else
            {
                buttonY.setBackgroundColor(Color.GREEN);
                buttonX.setBackgroundColor(Color.GRAY);
            }

            xMove=!xMove;
        }
        else
        {
            Snackbar.make(cickedButton,"Not valid Move!",Snackbar.LENGTH_LONG).show();
        }
    }

    public void isSolved()
    {
        boolean solved=true;

        //row wise
        for(int i=0;i<3;i++)
        {
            solved=true;
            for(int j=0;j<3;j++)
            {
                if(xMove)
                {
                    if(board[i][j]=='O'||board[i][j]==' ') {
                        solved=false;
                        break;
                    }
                }
                else
                {
                    if(board[i][j]=='X'||board[i][j]==' ')
                    {
                        solved=false;
                        break;
                    }
                }
            }

            if(solved)
            {
                //System.out.println("rowwise solved");

                if(xMove)
                {
                    xwins++;
                    Snackbar.make(this.findViewById(R.id.button1),"X Wins!",Snackbar.LENGTH_SHORT).show();
                    //Toast.makeText(this,"X Wins!", Toast.LENGTH_SHORT).show();
                    isSolvedFlag=true;
                }
                else
                {
                    Snackbar.make(this.findViewById(R.id.button1),"O Wins!",Snackbar.LENGTH_SHORT).show();
                  //  Toast.makeText(this,"O  Wins!", Toast.LENGTH_SHORT).show();
                    ywins++;
                    isSolvedFlag=true;
                }

                buttonBoard[i][0].setTextColor(Color.RED);
                buttonBoard[i][1].setTextColor(Color.RED);
                buttonBoard[i][2].setTextColor(Color.RED);
                return;
            }

        }

        //column wise
        for(int i=0;i<3;i++)
        {
            solved=true;
            for(int j=0;j<3;j++)
            {
                if(xMove)
                {
                    if(board[j][i]=='O'||board[j][i]==' ') {
                        solved=false;
                        break;
                    }
                }
                else
                {
                    if(board[j][i]=='X'||board[j][i]==' ')
                    {
                        solved=false;
                        break;
                    }
                }
            }

            if(solved)
            {
                //System.out.println("col wise solved");
                if(xMove)
                {
                    xwins++;
                    //Toast.makeText(this,"X Wins!", Toast.LENGTH_SHORT).show();
                    Snackbar.make(this.findViewById(R.id.button1),"X Wins!",Snackbar.LENGTH_SHORT).show();
                    isSolvedFlag=true;
                }
                else
                {
                    //Toast.makeText(this,"O  Wins!", Toast.LENGTH_SHORT).show();
                    Snackbar.make(this.findViewById(R.id.button1),"O Wins!",Snackbar.LENGTH_SHORT).show();
                    ywins++;
                    isSolvedFlag=true;
                }

                buttonBoard[0][i].setTextColor(Color.RED);
                buttonBoard[1][i].setTextColor(Color.RED);
                buttonBoard[2][i].setTextColor(Color.RED);
                return;
            }

        }

        //diagonal

        if(xMove)
        {

            if (board[0][0]==board[1][1] &&
                board[0][0]==board[2][2] &&
                        board[0][0] =='X')
            {
                xwins++;
                //Toast.makeText(this,"X Wins!", Toast.LENGTH_SHORT).show();
                Snackbar.make(this.findViewById(R.id.button1),"X Wins!",Snackbar.LENGTH_SHORT).show();
                isSolvedFlag=true;
                //System.out.println("daig X wise solved");
                buttonBoard[0][0].setTextColor(Color.RED);
                buttonBoard[1][1].setTextColor(Color.RED);
                buttonBoard[2][2].setTextColor(Color.RED);
                return;
            }
            else if (board[0][2]==board[1][1] &&
                    board[0][2]==board[2][0] &&
                    board[1][1] =='X')
            {
                xwins++;
                //Toast.makeText(this,"X Wins!", Toast.LENGTH_SHORT).show();
                Snackbar.make(this.findViewById(R.id.button1),"X Wins!",Snackbar.LENGTH_SHORT).show();
                isSolvedFlag=true;
                //System.out.println("daig X wise solved");
                buttonBoard[0][2].setTextColor(Color.RED);
                buttonBoard[1][1].setTextColor(Color.RED);
                buttonBoard[2][0].setTextColor(Color.RED);
                return;
            }
        }
        else
        {

            if (board[0][0]==board[1][1] &&
                board[0][0]==board[2][2] &&
                board[0][0] =='O')
            {
                //Toast.makeText(this,"O  Wins!", Toast.LENGTH_SHORT).show();
                Snackbar.make(this.findViewById(R.id.button1),"O Wins!",Snackbar.LENGTH_SHORT).show();
                ywins++;
                isSolvedFlag=true;
                //System.out.println("daig O wise solved");
                buttonBoard[0][0].setTextColor(Color.RED);
                buttonBoard[1][1].setTextColor(Color.RED);
                buttonBoard[2][2].setTextColor(Color.RED);

                return;
            }
            else if(board[2][0]==board[1][1] &&
                    board[1][1]==board[0][2] &&
                    board[1][1] =='O')
            {
                //Toast.makeText(this,"O  Wins!", Toast.LENGTH_SHORT).show();
                Snackbar.make(this.findViewById(R.id.button1),"O Wins!",Snackbar.LENGTH_SHORT).show();
                ywins++;
                isSolvedFlag=true;
                //System.out.println("daig O wise solved");

                buttonBoard[2][0].setTextColor(Color.RED);
                buttonBoard[1][1].setTextColor(Color.RED);
                buttonBoard[0][2].setTextColor(Color.RED);
                return;
            }
        }




    }

    public void resetClicked(View view)
    {

        loadBoard();
        initBoard();

    }

    private void initBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                board[i][j]=' ';
        }
        isSolvedFlag=false;

        xMove=true;

    }

    private void loadBoard() {
        buttonBoard[0][0] = (Button) findViewById(R.id.button1);
        buttonBoard[0][1] = (Button) findViewById(R.id.button2);
        buttonBoard[0][2] = (Button) findViewById(R.id.button3);
        buttonBoard[1][0] = (Button) findViewById(R.id.button4);
        buttonBoard[1][1] = (Button) findViewById(R.id.button5);
        buttonBoard[1][2] = (Button) findViewById(R.id.button6);
        buttonBoard[2][0] = (Button) findViewById(R.id.button7);
        buttonBoard[2][1] = (Button) findViewById(R.id.button8);
        buttonBoard[2][2] = (Button) findViewById(R.id.button9);

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++) {
                buttonBoard[i][j].setText("     ");
                buttonBoard[i][j].setTextSize(100);
                buttonBoard[i][j].setTag(R.id.KEY_X, i);
                buttonBoard[i][j].setTag(R.id.KEY_Y, j);
                buttonBoard[i][j].setTextColor(Color.BLACK);
            }
        }

        Button buttonX= (Button) findViewById(R.id.button10);
        buttonX.setText("X won: "+xwins);
        Button buttonReset= (Button) findViewById(R.id.button11);
        //buttonReset.setTextSize(110);
        Button buttonY= (Button) findViewById(R.id.button12);
        buttonY.setText("O won: "+ywins);

        buttonX.setBackgroundColor(Color.GREEN);
        buttonY.setBackgroundColor(Color.GRAY);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settings) {
       //     return true;
        //}

        return true;
    }
}
