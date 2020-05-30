package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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
    SharedPreferences sharedPreferences;
   static int firstTime=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player_details);
        leaderBoard = findViewById(R.id.leaderBoard);
        sharedPreferences=this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        leaderBoard.setLayoutManager(new LinearLayoutManager(this));
        if(firstTime==0) {
            Log.i("t",Integer.toString(firstTime));
            names.add(sharedPreferences.getString("1", ""));
            times.add(sharedPreferences.getString("1t", ""));
            //names.add(sharedPreferences.getString("2", ""));
            //times.add(sharedPreferences.getString("2t", ""));
            //names.add(sharedPreferences.getString("3", ""));
            //times.add(sharedPreferences.getString("3t", ""));
            firstTime=1;
        }
        if(names.get(0).equals("")){
            names.remove(0);
        }
        if(times.get(0).equals("")){
            times.remove(0);
        }
        if(names.size()>1){
        if(names.get(1).equals("")){
            names.remove(1);
        }
        if(times.get(1).equals("")){
            times.remove(1);
        }}
        /*if(names.get(2).equals("")){
            names.remove(2);
        }
        if(times.get(2).equals("")){
            times.remove(2);
        }*/
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
