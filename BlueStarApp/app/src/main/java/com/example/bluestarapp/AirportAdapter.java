package com.example.bluestarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.MyViewHolder> {
    private List<Airport> AirportList;



    public AirportAdapter(List<Airport> itemList) {
        this.AirportList = itemList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Airport item = AirportList.get(position);
        holder.tensanbay.setText(item.getAirportName());
        holder.diadiem.setText(item.getPlace());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tensanbay;
        TextView diadiem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensanbay = itemView.findViewById(R.id.name_airport);
            diadiem = itemView.findViewById(R.id.location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
