package com.example.bluestarapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bluestarapp.myinterface.IClickItemFlightListener;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.MyViewHolder> {
    private List<Flight> mListFlights;

    private IClickItemFlightListener iClickItemFlightListener;


    public FlightAdapter(List<Flight> mListFlights, IClickItemFlightListener listener) {
        this.mListFlights = mListFlights;
        this.iClickItemFlightListener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Flight flight = mListFlights.get(position);
        if (flight == null ) return;
        holder.fromLocation.setText(flight.getFromLocation());
        holder.toLocation.setText(flight.getToLocation());
        holder.departureTime.setText(flight.getDepartureTime());
        holder.arrivalTime.setText(flight.getArrivalTime());

        if (AppUtil.ticketKind.equals("Thương gia")) { // Sử dụng phương thức equals để so sánh chuỗi
            int originalPrice = Integer.parseInt(flight.getOriginalPrice()) + 500000;
            holder.originalPrice.setText(String.valueOf(originalPrice)); // Chuyển về chuỗi trước khi hiển thị
        } else {
            holder.originalPrice.setText(flight.getOriginalPrice());
        }

        holder.departureDay.setText(flight.getDepartureDay());

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemFlightListener.onClickItemFlight(flight);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListFlights != null) return mListFlights.size();
        return 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout layout_item;
        TextView fromLocation,departureTime,toLocation,arrivalTime, originalPrice, departureDay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            fromLocation = itemView.findViewById(R.id.fromLocation);
            departureTime = itemView.findViewById(R.id.departureTime);
            toLocation = itemView.findViewById(R.id.toLocation);
            arrivalTime = itemView.findViewById(R.id.arrivalTime);
            originalPrice = itemView.findViewById(R.id.originalPrice);
            departureDay = itemView.findViewById(R.id.departureDay);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
