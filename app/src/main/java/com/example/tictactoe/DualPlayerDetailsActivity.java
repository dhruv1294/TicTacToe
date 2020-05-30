package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DualPlayerDetailsActivity extends AppCompatActivity {
    EditText player1Name,player2Name;
    public static String user1,user2;
    Button playDualButton;
    RecyclerView leaderBoard;
    public static MyAdapter myAdapter;
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> times = new ArrayList<>();
    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("myprefs", Context.MODE_PRIVATE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player_details);
        leaderBoard = findViewById(R.id.leaderBoard);

        leaderBoard.setLayoutManager(new LinearLayoutManager(this));
        names.add(sharedPreferences.getString("1","player"));
        times.add(sharedPreferences.getString("1t","0"));
        myAdapter = new MyAdapter(this,names,times);

        leaderBoard.setAdapter(myAdapter);
        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        playDualButton = findViewById(R.id.playDualButton);
        playDualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer.create(getApplicationContext(),R.raw.sound2).start();
                if(player1Name.getText().toString().equals("")){
                    Toast.makeText(DualPlayerDetailsActivity.this, "Enter player1 name.", Toast.LENGTH_SHORT).show();
                }else if(player2Name.getText().toString().equals("")){
                    Toast.makeText(DualPlayerDetailsActivity.this, "Enter player2 name.", Toast.LENGTH_SHORT).show();
                }else{
                    user1 = player1Name.getText().toString();
                    user2 =player2Name.getText().toString();
                    Intent intent = new Intent(getApplicationContext(),DualPlayerPlayActivity.class);
                    intent.putExtra("player1name",player1Name.getText().toString());
                    intent.putExtra("player2name",player2Name.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
