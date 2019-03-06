package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn=true;

    private int roundCount;
    private int player1Points = 0;
    private int player2Points = 0;

    private TextView textViewPlayerScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerScores = findViewById(R.id.text_view_playerscores);

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                String buttonID = "button_" + x + y;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[x][y] = findViewById(resID);
                buttons[x][y].setOnClickListener(this);
            }
        }

        ImageButton buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Check if button is already clicked
        if(!((Button) v).getText().toString().equals("")) {
            return;
        }

        // If button is not empty
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if(checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for(int x=0; x < 3; x++){
            for (int y=0; y < 3; y++){
                field[x][y] = buttons[x][y].getText().toString();
            }
        }

        for(int x=0; x < 3; x++){
            if(field[x][0].equals(field[x][1]) && field[x][0].equals(field[x][2])
                    && !field[x][0].equals("")){
                return true;
            }
        }

        for(int x=0; x < 3; x++){
            if(field[0][x].equals(field[1][x]) && field[0][x].equals(field[2][x])
                    && !field[0][x].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayerScores.setText("Player1 " + Integer.toString(player1Points) + ":" + Integer.toString(player2Points) + " Player2");
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    //When orientation changes, save current game variables to continue playing
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
