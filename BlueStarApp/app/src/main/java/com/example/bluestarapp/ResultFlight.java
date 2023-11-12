package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bluestarapp.myinterface.IClickItemAirportListener;
import com.example.bluestarapp.myinterface.IClickItemFlightListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ResultFlight extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Flight> mListFlight;
    FlightAdapter flightAdapter;
    String fromLocation, toLocation, departureDay;
    int price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_flight);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("mypackage");
            fromLocation = bundle.getString("fromLocation");
            toLocation = bundle.getString("toLocation");
            departureDay = bundle.getString("departureDay");
        initUI();
        getListFlightFromFirestore();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recycleView);
        mListFlight = new ArrayList<>(); // Khởi tạo danh sách mListAirport
        flightAdapter = new FlightAdapter(mListFlight, new IClickItemFlightListener() {
            @Override
            public void onClickItemFlight(Flight flight) {
                String Fromlocation = flight.getFromLocation().toString();
                String Tolocation = flight.getToLocation().toString();
                String OriginalPrice = flight.getOriginalPrice().toString();
                String departureTime = flight.getDepartureTime().toString();
                String arrivalTime = flight.getArrivalTime().toString();
                String departureDay = flight.getDepartureDay().toString();

                AppUtil.FromLocation = Fromlocation;
                AppUtil.ToLocation = Tolocation;
                AppUtil.OriginalPrice = OriginalPrice;
                AppUtil.departueTime = departureTime;
                AppUtil.arrivalTime = arrivalTime;
                AppUtil.departureDay = departureDay;
                onClickGoTo(flight);
            }
        }); // Khởi tạo AirportAdapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(flightAdapter); // Gán adapter cho RecyclerView


    }
    private void getListFlightFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Flight").whereEqualTo("fromLocation", fromLocation).whereEqualTo("toLocation", toLocation).whereEqualTo("departureDay", AppUtil.departureDay).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Flight flight = document.toObject(Flight.class);
                        mListFlight.add(flight); // Thêm sân bay vào danh sách mListAirport
                    }
                    flightAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView khi có dữ liệu mới
                } else {
                    Log.d("FirestoreError", "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // Xử lý khi gặp lỗi
            }
        });
    }
    private void  onClickGoTo(Flight item){
        if (AppUtil.KhuHoi == 0) {
            Intent intent = new Intent(ResultFlight.this, ThongTinHanhKhach.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(ResultFlight.this, ResultFlightBack.class);
            startActivity(intent);
            finish();
        }
    }
}