package com.example.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<tools> toolsArrayList;
    public MyAdapter(Context context, ArrayList<tools> toolsArrayList) {
        this.context=context;
        this.toolsArrayList=toolsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  v= LayoutInflater.from(context).inflate(R.layout.cardviewlistitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        tools tools=toolsArrayList.get(position);

        holder.toolImage.setImageResource(tools.toolImage);
        holder.toolTitle.setText(tools.title);
        holder.toolRent.setText(tools.rent);

    }

    @Override
    public int getItemCount() {
        return toolsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView toolImage;
        TextView toolTitle;
        TextView toolRent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toolImage=itemView.findViewById(R.id.toolImage);
            toolTitle=itemView.findViewById(R.id.titleofTool);
            toolRent=itemView.findViewById(R.id.toolRent);
        }
    }

}
