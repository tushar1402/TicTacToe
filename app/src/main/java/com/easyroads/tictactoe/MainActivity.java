package com.easyroads.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int active_player = 0;

    boolean gameIsActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {0, 3, 6}, {6, 7, 8}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public void dropIn(View v) {
        ImageView counter = (ImageView) v;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = active_player;


            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.green);
                counter.animate().translationYBy(1000f).setDuration(300);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).setDuration(300);
                active_player = 0;
            }

            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Green";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winner);
                    winnerMessage.setText(winner + "Has won!");

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;
                    for (int c : gameState) {
                        if (c == 2) {
                            gameIsOver = false;
                        }
                    }

                        if (gameIsOver) {
                            TextView winnerMessage = (TextView) findViewById(R.id.winner);
                          winnerMessage.setText("It's a draw");

                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.GONE);
        active_player = 0;


        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
