package com.example.hackathon;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<tools> toolsArrayList;
    private int expandedPosition = -1;

    private int selectedPosition = -1;


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
        holder.phoneNumber.setText(tools.phoneNumber);
        holder.expandableText.setText(tools.description);



        if (selectedPosition == position) {
            holder.expandableText.setVisibility(View.VISIBLE);
            holder.phoneNumber.setVisibility(View.VISIBLE);
            holder.toolTitle.setVisibility(View.GONE);
            holder.toolRent.setVisibility(View.GONE);
            holder.toolImage.setVisibility(View.GONE);
        } else {
            holder.expandableText.setVisibility(View.GONE);
            holder.phoneNumber.setVisibility(View.GONE);
            holder.toolTitle.setVisibility(View.VISIBLE);
            holder.toolRent.setVisibility(View.VISIBLE);
            holder.toolImage.setVisibility(View.VISIBLE);
        }

        holder.phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = toolsArrayList.get(position).phoneNumber;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition == position) {
                    selectedPosition = -1;
                } else {
                    selectedPosition = position;
                }
                notifyDataSetChanged();
            }
        });







    }

    @Override
    public int getItemCount() {
        return toolsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView toolImage;
        TextView toolTitle;
        TextView toolRent;
        TextView expandableText;
        TextView phoneNumber;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toolImage=itemView.findViewById(R.id.toolImage);
            toolTitle=itemView.findViewById(R.id.titleofTool);
            toolRent=itemView.findViewById(R.id.toolRent);
            expandableText = itemView.findViewById(R.id.expandableText);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);

        }
    }

}
