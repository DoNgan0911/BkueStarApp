package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class ChiTietVe extends AppCompatActivity {

    TextView ticketKind,seat,departureDay,toLocation,idticket,nameofpassenger,departureTime,fromLocation;
    String selectedItem = "";
    ImageView imageViewBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ve);
        ticketKind = findViewById(R.id.ticketKind);
        seat = findViewById(R.id.seat);
        departureDay = findViewById(R.id.departureDay);
        toLocation = findViewById(R.id.toLocation);
        idticket = findViewById(R.id.idticket);
        nameofpassenger = findViewById(R.id.nameofpassenger);
        departureTime = findViewById(R.id.departureTime);
        fromLocation = findViewById(R.id.fromLocation);
        imageViewBack = findViewById(R.id.imageViewBack);

        Intent intent = getIntent();

        // Lấy Bundle từ Intent
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            selectedItem = bundle.getString("selectedItem");
        }
        Log.d("ChiTietVe", "Selected Item: " + selectedItem);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ticketCollection = db.collection("TICKET");
        DocumentReference ticketDocument = ticketCollection.document(selectedItem);


        ticketDocument.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {
                            String flyIdString = documentSnapshot.getString("fly_id");
                            long flyId = Long.parseLong(flyIdString);
                                String name = documentSnapshot.getString("name");
                                String seatID = documentSnapshot.getString("seat_id");
                                String ticket_kind = documentSnapshot.getString("ticket_kind");

                                nameofpassenger.setText(name);
                                seat.setText(seatID);
                                ticketKind.setText(ticket_kind);
                                idticket.setText(selectedItem);

                                if (flyIdString != null) {
                                    db.collection("FLIGHT")
                                            .document(flyIdString)
                                            .get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot flightDocumentSnapshot) {
                                                    String fromLocation1 = flightDocumentSnapshot.getString("fromLocation");
                                                    String toLocation1 = flightDocumentSnapshot.getString("toLocation");
                                                    String departureDay1 = flightDocumentSnapshot.getString("departureDay");
                                                    String departureTime1 = flightDocumentSnapshot.getString("departureTime");

                                                    fromLocation.setText(fromLocation1);
                                                    toLocation.setText(toLocation1);
                                                    departureDay.setText(departureDay1);
                                                    departureTime.setText(departureTime1);

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietVe.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}