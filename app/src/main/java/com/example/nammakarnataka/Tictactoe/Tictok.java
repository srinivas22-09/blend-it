package com.example.nammakarnataka.Tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nammakarnataka.R;

public class Tictok extends AppCompatActivity implements View.OnClickListener {
        private TextView player_one_score;
        ImageView back;
        private TextView player_two_score;
        private TextView player_status;
        private Button[] buttons = new Button[9];
        private Button reset,play_again;
        private int playeronescorecount;
        private int playertwoscorecount;
        boolean playeroneactive;
        int[] gamestate={2,2,2,2,2,2,2,2,2};
        int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictok);

        player_one_score=findViewById(R.id.score_player1);
        player_two_score=findViewById(R.id.score_player2);
        player_status=findViewById(R.id.text_status);
        reset=findViewById(R.id.btn_restart);
        play_again=findViewById(R.id.button_play_again);
        buttons[0]=findViewById(R.id.btn0);
        buttons[1]=findViewById(R.id.btn1);
        buttons[2]=findViewById(R.id.btn2);
        buttons[3]=findViewById(R.id.btn3);
        buttons[4]=findViewById(R.id.btn4);
        buttons[5]=findViewById(R.id.btn5);
        buttons[6]=findViewById(R.id.btn6);
        buttons[7]=findViewById(R.id.btn7);
        buttons[8]=findViewById(R.id.btn8);
        back=findViewById(R.id.back_button_tictactoe);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(this);


        }
        playeronescorecount=0;
        playertwoscorecount=0;
        playeroneactive=true;
        rounds=0;

        back.setOnClickListener((v)->{
            finish();
        });

    }

    @Override
    public void onClick(View view) {

        if(!((Button)view).getText().toString().equals("")){
            return;
        } else if (checkwinner()) {
            return;

        }
        String buttonid = view.getResources().getResourceEntryName(view.getId());

        int gameStatepointer= Integer.parseInt(buttonid.substring(buttonid.length()-1,buttonid.length()));
        if(playeroneactive){
            ((Button)view).setText("x");
            ((Button)view).setTextColor(Color.parseColor("black"));
            gamestate[gameStatepointer] =0;

        }
        else {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("black"));
            gamestate[gameStatepointer] =1;


        }
        rounds++;

        if(checkwinner()) {
            if (playeroneactive) {
                playeronescorecount++;
                updateplayerscore();
                player_status.setText("player one has won");

            } else {
                playertwoscorecount++;
                updateplayerscore();
                player_status.setText("player two has won");


            }

        } else if (rounds==9) {
            player_status.setText("no winner");

        }
        else{
            playeroneactive=!playeroneactive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playeronescorecount=0;
                playertwoscorecount=0;
                updateplayerscore();
            }
        });
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });

    }
    private void updateplayerscore(){
        player_one_score.setText(Integer.toString(playeronescorecount));
        player_two_score.setText(Integer.toString(playertwoscorecount));

    }
    private void playAgain(){
        rounds=0;
        playeroneactive=true;
        for (int i = 0; i < buttons.length; i++) {
            gamestate[i]=2;
            buttons[i].setText("");

        }
        player_status.setText("status");
    }
    private boolean checkwinner(){
        boolean winnerresult=false;
        for (int[] winningpositions:winningpositions){
            if (gamestate[winningpositions[0]]==gamestate[winningpositions[1]]&&
                    gamestate[winningpositions[1]]==gamestate[winningpositions[2]]&&
            gamestate[winningpositions[0]]!=2){
                winnerresult=true;
            }
        }
        return winnerresult;
    }
}