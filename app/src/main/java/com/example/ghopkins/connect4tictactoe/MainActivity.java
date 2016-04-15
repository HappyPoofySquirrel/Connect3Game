package com.example.ghopkins.connect4tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
   //0=yellow 1=red
        int activePlayer=0;

        boolean gameIsActive = true;
        int[] gameState ={2,2,2,2,2,2,2,2,2}; //2=open space

    int[][] winningPosiions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive==true) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(600);

            for (int[] winningPosition : winningPosiions) {
                if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2) {

                    final LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);

                    gameIsActive = false;

                    TextView winner = (TextView) findViewById(R.id.winnerMessage);
                    if (activePlayer == 1) {
                        winner.setText("Yellow is the winner");
                    } else {
                        winner.setText("Red is the winner");
                    }
                }
                    else{
                        boolean gamIsOver = true;
                        for (int counterState:gameState){
                        if(counterState==2)gamIsOver=false;
                    }
                         if(gamIsOver){
                             final LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                             playAgainLayout.setVisibility(View.VISIBLE);
                             TextView winner = (TextView) findViewById(R.id.winnerMessage);

                                winner.setText("Draw");

                         }
                    }


            }
        }
    }

    public void playAgain(View view){
        LinearLayout playAgainLayout =(LinearLayout)findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);
        activePlayer=0;

        gameIsActive =true;
        for(int i=0; i<gameState.length;i++){
            gameState[i] =2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridView);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
         //2=open space

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
