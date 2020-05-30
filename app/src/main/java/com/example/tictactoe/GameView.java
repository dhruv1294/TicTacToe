package com.example.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.ArrayList;

public class GameView extends View {
    Paint board;
    Paint zeroColor;
    Paint crossColor;
    int playerState=2;
    int width;
    int height;
    int cellSize;
    int lineWidth;
    int radius;
    public static String winner="";
    Game game;
    public static boolean isended=false;
    DualPlayerPlayActivity activityDual;
    SinglePlayerPlayActivity activitySingle;
    MediaPlayer user;
    public static double player1Time=0,player2Time=0;
    double player1BufferTime,player2BufferTime;

    public static ArrayList<Integer> player1values,player2values,alreadyTouched;


    public GameView(Context context) {
        super(context);
        init(null);

    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet attributeSet){
        board = new Paint(Paint.ANTI_ALIAS_FLAG);
        board.setColor(Color.BLACK);
        zeroColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        zeroColor.setStyle(Paint.Style.STROKE);
        zeroColor.setColor(Color.parseColor("#eb3349"));
        zeroColor.setStrokeWidth(32);
        crossColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        crossColor.setColor(Color.parseColor("#004e92"));
        crossColor.setStrokeWidth(32);
        player1values = new ArrayList<Integer>();
        player2values = new ArrayList<Integer>();
        alreadyTouched = new ArrayList<Integer>();
        game = new Game();
        player1BufferTime =(double)System.currentTimeMillis()/1000;
    }
    public void setDualActivity(DualPlayerPlayActivity a){
        activityDual = a;
    }
    public void setSingleActivity(SinglePlayerPlayActivity a){
        activitySingle = a;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        canvas.drawColor(Color.parseColor("#eacda3"));
        cellSize=width/3;
        lineWidth = cellSize/10;
        radius = cellSize/3;
        for(int i=0;i<2;i++){
            canvas.drawRect(cellSize*(i+1),cellSize/2,cellSize*(i+1)+lineWidth,cellSize/2+cellSize*3,board);
            canvas.drawRect(0,cellSize/2 + cellSize*(i+1),width,cellSize/2 + cellSize*(i+1)+lineWidth,board);
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                //if(i==0&&j==0)
                //  canvas.drawCircle(cellSize/2*(i+1)+lineWidth/2,cellSize/2 + cellSize/2*(j+1)+lineWidth/2,cellSize/3,zeroColor);
                //  else
                if(player2values.contains(3*i+j)){
                    zeroColor.setColor(Color.RED);
                }else{
                    zeroColor.setColor(Color.TRANSPARENT);
                }
                canvas.drawCircle(cellSize / 2 + cellSize * i + lineWidth / 2, cellSize * (j + 1) + lineWidth / 2, radius, zeroColor);

            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                if(player1values.contains(3*i+j)){
                    crossColor.setColor(Color.BLUE);
                }else{
                    crossColor.setColor(Color.TRANSPARENT);
                }
                canvas.drawLine(cellSize / 2 + cellSize * i + lineWidth / 2, cellSize * (j + 1) + lineWidth / 2, cellSize / 2 + cellSize * i + lineWidth / 2 - radius, cellSize * (j + 1) + lineWidth / 2 - radius, crossColor);
                canvas.drawLine(cellSize / 2 + cellSize * i + lineWidth / 2, cellSize * (j + 1) + lineWidth / 2, cellSize / 2 + cellSize * i + lineWidth / 2 + radius, cellSize * (j + 1) + lineWidth / 2 - radius, crossColor);
                canvas.drawLine(cellSize / 2 + cellSize * i + lineWidth / 2, cellSize * (j + 1) + lineWidth / 2, cellSize / 2 + cellSize * i + lineWidth / 2 - radius, cellSize * (j + 1) + lineWidth / 2 + radius, crossColor);
                canvas.drawLine(cellSize / 2 + cellSize * i + lineWidth / 2, cellSize * (j + 1) + lineWidth / 2, cellSize / 2 + cellSize * i + lineWidth / 2 + radius, cellSize * (j + 1) + lineWidth / 2 + radius, crossColor);
            }

        }



    }
    public boolean checkDraw(){
        if(alreadyTouched.size()==9 && winner.equals("")){
            Log.i("game","Draw");
            user.stop();
            MediaPlayer.create(getContext(),R.raw.tadastrings).start();
            winner = "It's a Draw";
            isended = true;
            return true;
        }
        return false;
    }
    public void newGame(){
        player1values.clear();
        player2values.clear();
        alreadyTouched.clear();
        isended = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            float x = event.getX();
            float y = event.getY();
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(x<cellSize*(i+1) && x>cellSize*i && y<cellSize/2 + cellSize*(j+1) && y>cellSize/2 + cellSize*j){
                         user =MediaPlayer.create(getContext(),R.raw.sound);
                         user.start();
                        if(!alreadyTouched.contains(3*i+j) && Game.gameActive) {
                            if(HomeActivity.gameMode.equals("dual")) {
                              if (playerState == 2) {
                                  if (!player1values.contains(3 * i + j)) {

                                      player1values.add(3 * i + j);
                                      alreadyTouched.add(3 * i + j);
                                      playerState = 1;
                                      game.setState(playerState,3*i+j);
                                      player1BufferTime = ((double)System.currentTimeMillis()/1000)-player1BufferTime;
                                      player1Time+=player1BufferTime;
                                      player2BufferTime=(double)System.currentTimeMillis()/1000;
                                  }

                              } else {
                                  if (!player2values.contains(3 * i + j)) {
                                      player2values.add(3 * i + j);
                                      alreadyTouched.add(3 * i + j);
                                      playerState = 2;
                                      game.setState(playerState,3*i+j);
                                      player2BufferTime=((double)System.currentTimeMillis()/1000)-player2BufferTime;
                                      player2Time+=player2BufferTime;
                                      player1BufferTime=(double)System.currentTimeMillis()/1000;

                                  }


                              }
                                activityDual.changeState(playerState);

                            }else if(HomeActivity.gameMode.equals("single")){
                                player1values.add(3 * i + j);
                                alreadyTouched.add(3 * i + j);
                                game.setState(1, 3 * i + j);
                                Log.i("player1",Integer.toString(3 * i + j));
                                playerState = 1;
                                winner = game.checkWinner(playerState);

                                if (!winner.equals("")) {
                                    winner = winner + " WINS";
                                    isended = true;
                                }
                                if (alreadyTouched.size() != 9 && !isended) {
                                    game.compPlay();
                                    int player2move = game.compMove();
                                  /*if(alreadyTouched.contains(player2move)){
                                      game.compPlay();
                                      player2move=game.compMove();
                                  }*/

                                    player2values.add(player2move);
                                    alreadyTouched.add(player2move);
                                    game.setState(2, player2move);
                                    playerState = 2;

                                }
                            }


                        }
                    }
                }
            }
            //
            winner = game.checkWinner(playerState);

            if(!winner.equals("")){
                if (winner.equals("CROSS")) {
                    user.stop();
                    MediaPlayer.create(getContext(),R.raw.correctanswer).start();
                }else if(winner.equals("ZERO")){
                    user.stop();
                    MediaPlayer.create(getContext(),R.raw.scorewin).start();
                }
                winner = winner + " WINS";
                isended=true;
            }

            checkDraw();
            if(!winner.equals("")){
                if(HomeActivity.gameMode.equals("dual"))
                    activityDual.isEnded(winner);
                else if(HomeActivity.gameMode.equals("single"))
                    activitySingle.isEnded(winner);
            }

            invalidate();
            return true;
        }

        return value;
    }
}
