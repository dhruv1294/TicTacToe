package com.example.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public static boolean gameActive = true;
    String winner="";
    ArrayList<Integer> safeSpots;
    ArrayList<Integer> goodSpots;
    Random random;
    int firstCompMove=0;
    int secondCompMove=0;
    SharedPreferences sharedPreferences;
    Context context;
    Set<String> names = new HashSet<>();
    Set<String> times = new HashSet<>();
    DualPlayerPlayActivity activity ;


    public Game(){
        newGame();
        safeSpots = new ArrayList<Integer>();
        goodSpots = new ArrayList<Integer>();
        random = new Random();
    }

    public void setState(int playerState, int tappedCounter) {
        gameState[tappedCounter] = playerState;
    }

    public String checkWinner(int playerState) {
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 0) {

                gameActive = false;
                if (playerState == 1) {
                    Log.i("winner!!!", "crosss");
                    Log.i("player 1",Double.toString(GameView.player1Time));
                    if(GameView.player1Time!=0) {
                        GameView.player1Time = Math.round(GameView.player1Time * 100.0) / 100.0;
                        if(DualPlayerDetailsActivity.names.size()>2){
                            for(int i=0;i<DualPlayerDetailsActivity.times.size();i++){
                                Log.i("i",Integer.toString(i));
                                if(GameView.player1Time< Double.parseDouble(DualPlayerDetailsActivity.times.get(i))){
                                    if(i+1<DualPlayerDetailsActivity.names.size()) {
                                        DualPlayerDetailsActivity.times.set(i + 1, DualPlayerDetailsActivity.times.get(i));
                                        DualPlayerDetailsActivity.names.set(i + 1, DualPlayerDetailsActivity.names.get(i));
                                    }else{
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.names.get(i));
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.times.get(i));
                                    }
                                    DualPlayerDetailsActivity.times.set(i,Double.toString(GameView.player1Time));
                                    DualPlayerDetailsActivity.names.set(i,DualPlayerDetailsActivity.user1);
                                    break;
                                }
                            }
                        }else {
                            if(!DualPlayerDetailsActivity.names.contains(DualPlayerDetailsActivity.user1)) {
                                DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.user1);

                                DualPlayerDetailsActivity.times.add(Double.toString(GameView.player1Time));
                            }
                            for(int i=0;i<DualPlayerDetailsActivity.times.size();i++){
                                Log.i("i",Integer.toString(i));
                                if(GameView.player1Time< Double.parseDouble(DualPlayerDetailsActivity.times.get(i))){
                                    if(i+1<DualPlayerDetailsActivity.names.size()) {
                                        DualPlayerDetailsActivity.times.set(i + 1, DualPlayerDetailsActivity.times.get(i));
                                        DualPlayerDetailsActivity.names.set(i + 1, DualPlayerDetailsActivity.names.get(i));
                                    }else{
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.names.get(i));
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.times.get(i));
                                    }
                                    DualPlayerDetailsActivity.times.set(i,Double.toString(GameView.player1Time));
                                    DualPlayerDetailsActivity.names.set(i,DualPlayerDetailsActivity.user1);
                                    break;
                                }
                            }
                            GameView.player1Time = 0;
                        }
                    }
                    //DualPlayerDetailsActivity.myAdapter.notifyItemInserted(1);
                   // activity.saveData();
                    winner = "CROSS";

                } else {
                    Log.i("winner", "zero");
                    Log.i("player 2",Double.toString(GameView.player2Time));
                    if(GameView.player2Time!=0) {
                        GameView.player2Time = Math.round(GameView.player2Time * 100.0) / 100.0;
                        if(DualPlayerDetailsActivity.names.size()>2){
                            for(int i=0;i<DualPlayerDetailsActivity.times.size();i++){
                                Log.i("i",Integer.toString(i));
                                if(GameView.player2Time< Double.parseDouble(DualPlayerDetailsActivity.times.get(i))){
                                    if(i+1<DualPlayerDetailsActivity.names.size()) {
                                        DualPlayerDetailsActivity.times.set(i + 1, DualPlayerDetailsActivity.times.get(i));
                                        DualPlayerDetailsActivity.names.set(i + 1, DualPlayerDetailsActivity.names.get(i));
                                    }else{
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.names.get(i));
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.times.get(i));
                                    }
                                    DualPlayerDetailsActivity.times.set(i,Double.toString(GameView.player2Time));
                                    DualPlayerDetailsActivity.names.set(i,DualPlayerDetailsActivity.user2);
                                    break;
                                }
                            }
                        }else {
                            if(!DualPlayerDetailsActivity.names.contains(DualPlayerDetailsActivity.user2)) {
                                DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.user2);

                                DualPlayerDetailsActivity.times.add(Double.toString(GameView.player2Time));
                            }
                            for(int i=0;i<DualPlayerDetailsActivity.times.size();i++){
                                Log.i("i",Integer.toString(i));
                                if(GameView.player2Time< Double.parseDouble(DualPlayerDetailsActivity.times.get(i))){
                                    if(i+1<DualPlayerDetailsActivity.names.size()) {
                                        DualPlayerDetailsActivity.times.set(i + 1, DualPlayerDetailsActivity.times.get(i));
                                        DualPlayerDetailsActivity.names.set(i + 1, DualPlayerDetailsActivity.names.get(i));
                                    }else{
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.names.get(i));
                                        DualPlayerDetailsActivity.names.add(DualPlayerDetailsActivity.times.get(i));
                                    }
                                    DualPlayerDetailsActivity.times.set(i,Double.toString(GameView.player2Time));
                                    DualPlayerDetailsActivity.names.set(i,DualPlayerDetailsActivity.user2);
                                    break;
                                }
                            }
                            GameView.player2Time = 0;
                        }
                    }
                   // activity.saveData();
                    //DualPlayerDetailsActivity.myAdapter.notifyItemInserted(1);
                    winner = "ZERO";
                }
            }
        }
        return winner;
    }
    public void newGame(){
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }
        gameActive=true;
    }
    public void compPlay(){
        safeSpots.clear();
        goodSpots.clear();
        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[0]] != 0 && gameState[winningPosition[2]] ==0){
                goodSpots.add(winningPosition[2]);
            }else if (gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[1]] != 0 && gameState[winningPosition[0]] ==0){
                goodSpots.add(winningPosition[0]);
            }else if (gameState[winningPosition[0]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]] != 0 && gameState[winningPosition[1]] ==0){
                goodSpots.add(winningPosition[1]);
            }
        }
        if(goodSpots.size()==0){
            do{
                try {
                    if(gameState[4]==0&&firstCompMove==0){
                        safeSpots.add(4);
                        firstCompMove=1;
                    }else if(gameState[4]==1&& firstCompMove==0) {
                        safeSpots.add(0);
                        firstCompMove=1;
                    }else if(gameState[5]==1&&gameState[7]==1&&secondCompMove==0){
                        safeSpots.add(6);
                        secondCompMove=1;
                    }else if((gameState[3]==1&&gameState[7]==1&&secondCompMove==0)){
                        safeSpots.add(8);
                        secondCompMove=1;

                    }else if(gameState[1]==1 && gameState[5]==1&& secondCompMove==0){
                        safeSpots.add(8);
                        secondCompMove=1;

                    }else if(gameState[1]==1 && gameState[3]==1&&secondCompMove==0){
                        safeSpots.add(6);
                        secondCompMove=1;
                    }
                    else if(gameState[4]==1 && gameState[8]==1&&secondCompMove==0){
                        safeSpots.add(6);
                        secondCompMove=1;
                    }else if(((gameState[1]==1&&gameState[6]==1)||(gameState[1]==1&&gameState[8]==1))&&secondCompMove==0){
                        safeSpots.add(2);
                        secondCompMove=1;
                    }else if(((gameState[3]==1&&gameState[8]==1)||(gameState[3]==1&&gameState[2]==1))&&secondCompMove==0){
                        safeSpots.add(0);
                        secondCompMove=1;
                    }else if(((gameState[5]==1&&gameState[6]==1)||(gameState[5]==1&&gameState[0]==1))&&secondCompMove==0){
                        safeSpots.add(8);
                        secondCompMove=1;
                    }
                    else if(((gameState[2]==1&&gameState[7]==1)||(gameState[0]==1&&gameState[7]==1))&&secondCompMove==0){
                        safeSpots.add(8);
                        secondCompMove=1;
                    }else if(((gameState[0]==1&&gameState[8]==1)||(gameState[2]==1&&gameState[6]==1))&&secondCompMove==0){
                        safeSpots.add(1);
                        secondCompMove=1;
                    }
                    else{
                        safeSpots.add(random.nextInt(9));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }while(gameState[safeSpots.get(safeSpots.size()-1)]==1||gameState[safeSpots.get(safeSpots.size()-1)]==2);
        }



    }
    public int compMove(){
        if(goodSpots.size()!=0) {
            Log.i("spot","good " + Integer.toString(goodSpots.get(goodSpots.size()-1)));

            return goodSpots.get(0);
        }
        Log.i("spot","Safe " + Integer.toString(safeSpots.get(safeSpots.size()-1)));
        return safeSpots.get(safeSpots.size()-1);
    }
}
