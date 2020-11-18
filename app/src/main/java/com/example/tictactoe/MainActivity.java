package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView headerText;

    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_O;
    int filledPos[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.headerText);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
    }

    public void implement(View view) {

        if(!isGameActive)
            return;

        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        if(filledPos[clickedTag] != -1) {
            return;
        }

        filledPos[clickedTag] = activePlayer;

        if(activePlayer == 0) {
            clickedBtn.setText("O");
            clickedBtn.setTextColor(Color.parseColor("#FFB6C1"));
            activePlayer = PLAYER_X;
            headerText.setText("PLAYER 2 TURN");
        } else {
            clickedBtn.setText("X");
            clickedBtn.setTextColor(Color.parseColor("#00FFFF"));
            activePlayer = PLAYER_O;
            headerText.setText("PLAYER 1 TURN");
        }

        checkForWin();

    }

    private void checkForWin() {
        int winningPos[][] = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        for (int i = 0; i<8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if(filledPos[val0] != -1) {
                    //winner declare

                    isGameActive = false;
                    if(filledPos[val0] == PLAYER_O) {
                        headerText.setText("PLAYER 1 WINS");
                       showDialog("PLAYER 1 IS WINNER");
                        headerText.setTextColor(Color.parseColor("#FFB6C1"));
                    } else {
                        headerText.setText("PLAYER 2 WINS");
                        showDialog("PLAYER 2 IS WINNER");
                        headerText.setTextColor(Color.parseColor("#00FFFF"));
                    }
                }
            }

        }
        int count=0;
        for(int i=0;i<9;i++) {
            if(filledPos[i] != -1) {
                count++;
            }
        }
        if(count==9) {
            headerText.setText("DRAW");
            headerText.setTextColor(Color.parseColor("#FF0000"));
            showDialog(" Match is drawn");
        }
    }

    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        }).show();
    }

    private void restartGame() {

        activePlayer = PLAYER_O;
        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        headerText.setText("Tic Tac Toe");
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        headerText.setTextColor(Color.parseColor("#FFFFFF"));
        isGameActive = true;
    }

}