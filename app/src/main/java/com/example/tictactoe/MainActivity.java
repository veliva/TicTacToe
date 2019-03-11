package com.example.tictactoe;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn=true;

    private int roundCount;
    private int player1Points = 0;
    private int player2Points = 0;

    private TextView textViewPlayerScores;
    private TextView textViewCrosses;
    private TextView textViewNoughts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerScores = findViewById(R.id.text_view_playerscores);
        textViewCrosses = findViewById(R.id.text_view_Crosses);
        textViewNoughts = findViewById(R.id.text_view_Noughts);

        textViewCrosses.setTextColor(getResources().getColor(R.color.colorTurnGreen));
        textViewNoughts.setTextColor(getResources().getColor(R.color.colorDefaultGray));

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                String buttonID = "button_" + x + y;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[x][y] = findViewById(resID);
                buttons[x][y].setOnClickListener(this);
            }
        }

        // Set the toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add the nav drawer button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24px);
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
            textViewCrosses.setTextColor(getResources().getColor(R.color.colorDefaultGray));
            textViewNoughts.setTextColor(getResources().getColor(R.color.colorTurnGreen));
        } else {
            ((Button) v).setText("O");
            textViewCrosses.setTextColor(getResources().getColor(R.color.colorTurnGreen));
            textViewNoughts.setTextColor(getResources().getColor(R.color.colorDefaultGray));
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
        Toast.makeText(this, "Crosses wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Noughts wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayerScores.setText(Integer.toString(player1Points) + ":" + Integer.toString(player2Points));
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
        textViewCrosses.setTextColor(getResources().getColor(R.color.colorTurnGreen));
        textViewNoughts.setTextColor(getResources().getColor(R.color.colorDefaultGray));
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
        Toast.makeText(this,"Game has been reset!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.button_reset:
                resetBoard();
                resetGame();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maintoolbar_view, menu);
        return true;
    }
}
