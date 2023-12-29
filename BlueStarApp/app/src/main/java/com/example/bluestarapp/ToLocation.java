package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;

import com.example.bluestarapp.myinterface.IClickItemAirportListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ToLocation extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Airport> mListAirport;
    AirportAdapter airportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_location);
        initUI();
        getListAirportFromFirestore();

        // Xử lý sự kiện khi người dùng chọn một mục từ danh sách sân bay


    }

    private void initUI() {
        recyclerView = findViewById(R.id.recycleView);
        mListAirport = new ArrayList<>(); // Khởi tạo danh sách mListAirport
        airportAdapter = new AirportAdapter(mListAirport, new IClickItemAirportListener() {
            @Override
            public void onClickItemAirport(Airport airport) {
                String ToLocation = airport.getPlace().toString();
                AppUtil.ToLocation = ToLocation;
                onClickGoTo(airport);
            }
        }); // Khởi tạo AirportAdapter

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(airportAdapter); // Gán adapter cho RecyclerView


    }

    private void getListAirportFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Airport").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Airport airport = document.toObject(Airport.class);
                        mListAirport.add(airport); // Thêm sân bay vào danh sách mListAirport
                    }
                    airportAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView khi có dữ liệu mới
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

    private void onClickGoTo(Airport item) {
        // Trả về dữ liệu (ví dụ: tên sân bay) qua Intent
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedAirportt", AppUtil.ToLocation); // Ví dụ
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}