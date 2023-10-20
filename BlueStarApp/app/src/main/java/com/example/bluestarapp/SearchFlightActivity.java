package com.example.bluestarapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;


public class SearchFlightActivity extends AppCompatActivity {

    List<Airport> list_airport;
    AirportAdapter airportAdapter;
    AutoCompleteTextView searchViewFrom;
    RecyclerView recyclerView;
    FirebaseFirestore db ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        // Khởi tạo list_airport và RecyclerView
        list_airport = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_airport);

        // Khởi tạo và thiết lập AutoCompleteTextView
        searchViewFrom = findViewById(R.id.from);
        ArrayAdapter<Airport> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_airport);
        searchViewFrom.setAdapter(autoCompleteAdapter);
        db = FirebaseFirestore.getInstance(); // Khởi tạo FirebaseFirestore


        searchViewFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý khi người dùng chọn một mục từ danh sách gợi ý
                Airport selectedAirport = (Airport) parent.getItemAtPosition(position);
                processSearch(selectedAirport.getPlace());
            }
        });

        searchViewFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Xử lý tìm kiếm mỗi khi người dùng thay đổi văn bản trong AutoCompleteTextView
                String newText = s.toString();
                processSearch(newText);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing here
            }
        });
    }
    private void processSearch(String query){
        db.collection("Airport").whereEqualTo("place", query.toLowerCase()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list_airport.clear();
                        for(DocumentSnapshot doc: task.getResult()){
                            Airport airport = new Airport(doc.getId(),doc.getString("airportName"), doc.getString("place"));
                            list_airport.add(airport);
                        }
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        airportAdapter = new AirportAdapter(list_airport);
                        recyclerView.setAdapter(airportAdapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }


}