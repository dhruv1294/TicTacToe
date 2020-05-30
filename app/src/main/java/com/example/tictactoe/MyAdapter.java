package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> namesfinal;
    private ArrayList<String> timerfinal;
   private  Context context;

    public MyAdapter(Context ctx,ArrayList<String> names,ArrayList<String> timer){
        namesfinal = new ArrayList<>();
        timerfinal= new ArrayList<>();
        context = ctx;

        namesfinal = names;
        timerfinal = timer;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     String name = namesfinal.get(position);
     String timerOne = timerfinal.get(position);
     holder.playerName.setText(name);
     holder.timer.setText(timerOne);
     //if(name != null)
     holder.srNo.setText(Integer.toString(position+1));
    }

    @Override
    public int getItemCount() {
        return namesfinal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView srNo,playerName,timer;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            srNo =  itemView.findViewById(R.id.srno);
            playerName =  itemView.findViewById(R.id.playerName);
            timer =  itemView.findViewById(R.id.timer);
        }
    }
}
