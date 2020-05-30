package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button singlePlayerButton,dualPlayerButton;
    public static String gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        singlePlayerButton = findViewById(R.id.singlePlayerButton);
        dualPlayerButton = findViewById(R.id.dualPlayerButton);
        dualPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer.create(getApplicationContext(),R.raw.sound2).start();
                Intent intent = new Intent(getApplicationContext(),DualPlayerDetailsActivity.class);
                gameMode = "dual";
                startActivity(intent);
            }
        });
        singlePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer.create(getApplicationContext(),R.raw.sound2).start();
                Intent intent = new Intent(getApplicationContext(),SinglePlayerPlayActivity.class);
                gameMode = "single";
                startActivity(intent);

            }
        });
    }
}
