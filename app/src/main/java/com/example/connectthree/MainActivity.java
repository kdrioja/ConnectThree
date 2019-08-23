package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //could use a boolean but the int has the advantage of allowing more players
    //to be added in the future
    // 0: yellow
    // 1: red
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //Log.i("tag", counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                gameState[tappedCounter] = 0;
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else {
                gameState[tappedCounter] = 1;
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    gameActive = false;

                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    String winner;

                    if (activePlayer == 0) {
                        winner = "Red";
                    }
                    else {
                        winner = "Yellow";
                    }

                    winnerTextView.setText(winner + " has won!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {

    }
}
