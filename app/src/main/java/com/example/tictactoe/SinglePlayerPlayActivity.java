package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class SinglePlayerPlayActivity extends AppCompatActivity {
    GameView gameView;
    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_play);
        gameView = findViewById(R.id.gameView);
        gameView.setSingleActivity(this);
        game = new Game();
    }
    public void isEnded(String winner){
        new AlertDialog.Builder(SinglePlayerPlayActivity.this)
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
                        finish();
                        System.exit(0);
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
