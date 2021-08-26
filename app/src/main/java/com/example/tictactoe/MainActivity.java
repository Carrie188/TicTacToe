package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private TextView displayBoard;
    private Button resetButton;
    private boolean playerX = true;
    private int runTime =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayBoard = findViewById(R.id.textview_display);
        resetButton = findViewById(R.id.button_reset);

        //set all the buttons in the right position of "buttons" array by identifying the resource id;
        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++){
                String buttonId = "button_"+i+j;
                int sourceId = getResources().getIdentifier(buttonId,"id",this.getPackageName());
                buttons[i][j] = findViewById(sourceId);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }

        if (playerX){
            ((Button)v).setText("X");
            displayBoard.setText("Player O's turn");
            playerX =false;
        }else {
            ((Button)v).setText("O");
            displayBoard.setText("Player X's turn");
            playerX =true;
        }
        runTime++;
        if (checkWhoWins()){
            return;
        }else if (runTime>8) {
            displayBoard.setText("Game Ends!");
            }



    }
    public boolean checkWhoWins(){


        for (int i=0; i<3;i++){

                if ((buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString()))&&(buttons[i][0].getText().toString().equals(buttons[i][2].getText().toString()))&&(!buttons[i][0].getText().toString().equals(""))){
                    displayBoard.setText(buttons[i][0].getText().toString()+" wins!");
                    return true;
                }

        }

        for (int i=0; i<3;i++){
                if ((buttons[0][i].getText().toString().equals(buttons[1][i].getText().toString()))
                        &&(buttons[0][i].getText().toString().equals(buttons[2][i].getText().toString()))&&(!buttons[0][i].getText().toString().equals(""))){
                    displayBoard.setText(buttons[0][i].getText().toString()+" wins!");
                    return true;
                }
        }

        if ((buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()))
                &&(buttons[0][0].getText().toString().equals(buttons[2][2].getText().toString()))&&(!buttons[0][0].getText().toString().equals(""))){
            displayBoard.setText(buttons[0][0].getText().toString()+" wins!");
            return true;
        }
        if ((buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()))
                &&(buttons[0][2].getText().toString().equals(buttons[2][0].getText().toString()))&&(!buttons[0][2].getText().toString().equals(""))){
            displayBoard.setText(buttons[0][2].getText().toString()+" wins!");
            return true;
        }
        return false;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayBoard.setText(savedInstanceState.getString("displayBoard"));
    }

    public void resetGame(View view) {
        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++){
                buttons[i][j].setText("");

            }
        }
        displayBoard.setText("");
        playerX =true;
        runTime =0;

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("displayBoard",displayBoard.getText().toString());
    }

}