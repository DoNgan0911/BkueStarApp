package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import androidx.annotation.NonNull;


public class TrangCaNhan extends AppCompatActivity {
    TextView fullname, pointEditText;
    TextView cccdEditText, emailEditText;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);
        fullname = findViewById(R.id.fullname);
        pointEditText = findViewById(R.id.pointEditText);
        cccdEditText = findViewById(R.id.cccdEditText);
        emailEditText = findViewById(R.id.emailEditText1);

        emailEditText.setText(AppUtil.edtSignInEmail);


        retrieveCCCDFromFirestore(AppUtil.edtSignInEmail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TrangCaNhan.this, EditProfile.class);
                startActivity(myIntent);
            }
        });


    }

    private void retrieveCCCDFromFirestore(String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get reference to the "customer" collection
        db.collection("CUSTOMER")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                // Retrieve the CCCD from the document
                                String cccd = document.getString("num_id");

                                // Display the CCCD in the EditText
                                cccdEditText.setText(cccd);

                                // You may also want to update other UI elements based on the retrieved data
                                // For example, update the fullname and points if they are stored in the document
                                String fullName = document.getString("fullname");
                                Integer points = document.getLong("point").intValue();
                                String pointsString = String.valueOf(points);


                                fullname.setText(fullName);
                                pointEditText.setText(pointsString);

                            }
                        } else {
                            // Handle errors
                        }
                    }
                });
    }
}