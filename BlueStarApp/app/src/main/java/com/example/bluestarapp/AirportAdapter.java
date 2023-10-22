package com.example.bluestarapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluestarapp.myinterface.IClickItemAirportListener;

import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.MyViewHolder> {
    private List<Airport> mListAirports;
    private OnItemClickListener onItemClickListener;
    private IClickItemAirportListener iClickItemAirportListener;




    public AirportAdapter(List<Airport> mListAirports, IClickItemAirportListener listener) {
        this.mListAirports = mListAirports;
        this.iClickItemAirportListener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airport, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Airport airport = mListAirports.get(position);
        if (airport == null ) return;
        holder.name_airport.setText(airport.getAirportName());
        holder.location.setText(airport.getPlace());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemAirportListener.onClickItemAirport(airport);
            }
        });

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(String selectedAirportPlace);
    }


    @Override
    public int getItemCount() {
        if (mListAirports != null) return mListAirports.size();
        return 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout layout_item;
        TextView name_airport;
        TextView location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            name_airport = itemView.findViewById(R.id.name_airport);
            location = itemView.findViewById(R.id.location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
