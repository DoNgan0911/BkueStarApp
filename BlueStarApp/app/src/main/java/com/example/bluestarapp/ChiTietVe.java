package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.annotation.NonNull;


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
        // Get the ticket ID from the search_ticket EditText
        // replace with the actual ticket ID you want to search for

        DocumentReference ticketDocument = ticketCollection.document(selectedItem);

        // Query the ticket with the specified ticket ID
        ticketDocument.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            // Document exists, you can retrieve its data
                            String fromLocationSearch = documentSnapshot.getString("fromLocation");
                            String toLocationSearch = documentSnapshot.getString("toLocation");
                            String arrivalTimeSearch = documentSnapshot.getString("arrivalTime");
                            String ccidSearch = documentSnapshot.getString("ccid");
                            String departureDaySearch = documentSnapshot.getString("departureDay");
                            String departureTimeSearch = documentSnapshot.getString("departureTime");
                            String mailSearch = documentSnapshot.getString("mail");
                            String nameSearch = documentSnapshot.getString("name");
                            String seat_idSearch = documentSnapshot.getString("seat_id");
                            String ticket_kindSearch = documentSnapshot.getString("ticket_kind");

                            fromLocation.setText(fromLocationSearch);
                            toLocation.setText(toLocationSearch);
                            nameofpassenger.setText(nameSearch);
                            departureDay.setText(departureDaySearch);
                            departureTime.setText(departureTimeSearch);
                            seat.setText(seat_idSearch);
                            idticket.setText(selectedItem);
                            ticketKind.setText(ticket_kindSearch);



                        } else {
                            // Document does not exist
                            // Handle the case where the ticket with the given ID is not found
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle errors
                    }
                });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietVe.this, LichSuMuaVe.class);
                startActivity(intent);
            }
        });
    }
}