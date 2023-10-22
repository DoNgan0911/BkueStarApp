package com.example.bluestarapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;


public class SearchFlightActivity extends AppCompatActivity {

    List<Airport> mListAirport;
    AirportAdapter airportAdapter;
    TextView searchViewFrom, searchViewTo;
    Button btnSearch;
    RecyclerView recyclerView;



    int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
        initUI();
        searchViewFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(SearchFlightActivity.this, FromLocation.class);
                startActivity(myintent);
            }
        });
        searchViewFrom.setText(AppUtil.FromLocaiton);

        searchViewTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(SearchFlightActivity.this, ToLocation.class);
                startActivity(myintent);
            }
        });

        searchViewTo.setText(AppUtil.ToLocaiton);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLocation = searchViewFrom.getText().toString();
                String toLocation = searchViewTo.getText().toString();

                Intent intent = new Intent(SearchFlightActivity.this, ResultFlight.class);
                Bundle bundle = new Bundle();
                bundle.putString("fromLocation", fromLocation);
                bundle.putString("toLocation", toLocation);

                // Tạo một Intent để chuyển từ SearchFlightActivity sang ResultFlightActivity


                // Đính kèm Bundle vào Intent
                intent.putExtra("mypackage", bundle);

                // Khởi chạy ResultFlightActivity với Intent và Bundle
                startActivity(intent);

            }
        });

    }




    private void initUI() {
        recyclerView = findViewById(R.id.recycler_airport);
        searchViewFrom = findViewById(R.id.from);
        searchViewTo = findViewById(R.id.To);
        btnSearch = findViewById(R.id.button);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


}


