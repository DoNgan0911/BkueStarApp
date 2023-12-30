package com.example.bluestarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfile extends AppCompatActivity {

    EditText name, cccd;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.nameEditText);
        cccd = findViewById(R.id.cccdEditText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            String newName = name.getText().toString();
            String newCCCD = cccd.getText().toString();

            // Update the user's information in the Realtime Database (using "customer" node)
            reference = database.getReference("customer").child(userId);

            reference.child("full_name").setValue(newName)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("EditProfile", "Full name updated successfully");
                            // Show success notification
                            Toast.makeText(EditProfile.this, "Full name updated successfully", Toast.LENGTH_SHORT).show();

                            // Now, update the num_id
                            updateCCCD(newCCCD);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("EditProfile", "Error updating full name", e);
                            Toast.makeText(EditProfile.this, "Failed to update full name", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void updateCCCD(String newCCCD) {
        reference.child("num_id").setValue(newCCCD)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("EditProfile", "Num ID updated successfully");
                        // Show success notification
                        Toast.makeText(EditProfile.this, "Num ID updated successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("EditProfile", "Error updating num ID", e);
                        Toast.makeText(EditProfile.this, "Failed to update num ID", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

