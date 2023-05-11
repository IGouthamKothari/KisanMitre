package com.example.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WeatherRVModel> WeatherRVModelArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        WeatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {

        WeatherRVModel model= WeatherRVModelArrayList.get(position);
        holder.temperatureTV.setText(model.getTemperature()+"°C");
       // if (holder.conditionIV != null) {
           // Picasso.get().load("http:".concat(model.getIcon())).into(holder.conditionIV);
       // }

        if (holder.conditionIV != null) {
            Picasso.get()
                    .load("http:".concat(model.getIcon()))
                    .fit()
                    .centerCrop()
                    .into(holder.conditionIV, new Callback() {
                        @Override
                        public void onSuccess() {
                            // Image loaded successfully
                        }

                        @Override
                        public void onError(Exception e) {
                            // Failed to load image
                        }
                    });
        }

        holder.windTV.setText(model.getWindspeed()+"km/h");
        SimpleDateFormat input =new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output=new SimpleDateFormat("hh:mm aa");
        try{
            Date t =input.parse(model.getTime());
            holder.timeTV.setText(output.format(t));
        }
        catch (ParseException e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return WeatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView windTV,temperatureTV,timeTV;
        private ImageView conditionIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            windTV=itemView.findViewById(R.id.TVWindSpeed);
            temperatureTV=itemView.findViewById(R.id.TVTemperature);
            timeTV=itemView.findViewById(R.id.TVTime);
            conditionIV=itemView.findViewById(R.id.TVCondition);
        }
    }
}
