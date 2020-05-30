package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DualPlayerPlayActivity extends AppCompatActivity {
    GameView gameView;
    Game game;
    ImageView direction;
    TextView player1name,player2name;
    String name1,name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player_play);
        Intent intent = getIntent();
        player1name = findViewById(R.id.playerName1);
        player2name = findViewById(R.id.playerName2);
        gameView = findViewById(R.id.gameView);
        gameView.setDualActivity(this);
        direction = findViewById(R.id.direction);
        name1 = intent.getStringExtra("player1name");
        name2 = intent.getStringExtra("player2name");
        Log.i("name",name1);
        Log.i("name",name2);
        player1name.setText(name1);
        player2name.setText(name2);
        game = new Game();
    }
    public void changeState(int playerState){
        if(playerState==2){
            direction.setImageResource(R.drawable.leftarrow);
        }else{
            direction.setImageResource(R.drawable.rightarrow);
        }
    }
    public void isEnded(String winner){
        new AlertDialog.Builder(DualPlayerPlayActivity.this)
                .setTitle("TIC-TAC-TOE")
                .setMessage(winner)
                .setPositiveButton("PLAY AGAIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        newGame();
                        recreate();
                    }
                })
                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                       startActivity(intent);
                    }
                })
                .show();
    }

    private void newGame(){
        game.newGame();
        gameView.newGame();
        gameView.invalidate();
    }
}