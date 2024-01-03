package com.example.bluestarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {
    EditText numberid_et, fullname_et, email_et, password_et;
    Button btn_signup;
    FirebaseAuth mAuth;
    TextView login_tv;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        numberid_et = findViewById(R.id.numberid_sigup);
        fullname_et = findViewById(R.id.fullname_signup);
        email_et = findViewById(R.id.email_signup);
        password_et = findViewById(R.id.pass_signup);
        btn_signup = findViewById(R.id.btn_signup);
        login_tv = findViewById(R.id.click_signin);

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                String number_id, fullname;
                email = String.valueOf(email_et.getText().toString());
                password = String.valueOf((password_et.getText().toString()));
                number_id = String.valueOf(numberid_et.getText().toString());
                fullname = String.valueOf(fullname_et.getText().toString());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUpActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    String uid = currentUser.getUid();
                                    String userFullname = fullname;
                                    String userNumberId = number_id;
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put("account_id", uid);
                                    userMap.put("fullname", userFullname);
                                    userMap.put("num_id", userNumberId);
                                    userMap.put("point", 0);

                                    CollectionReference customerCollection = db.collection("CUSTOMER");
                                    // Query the collection to get the count of documents
                                    customerCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {

                                                int documentCount = task.getResult().size();
                                                // Set the next available documentId
                                                String documentId = String.valueOf(documentCount + 1);
                                                // Rest of your code with the dynamically set documentId
                                                db.collection("CUSTOMER")
                                                        .document(documentId)
                                                        .set(userMap)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(SignUpActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                                                                Intent intent  = new Intent(getApplicationContext(), ProfileFragment.class);
                                                                startActivity(intent);
                                                                finish();

                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.e("SignUpActivity", "Error adding user data to CUSTOMER collection: " + e.getMessage());
                                                                Toast.makeText(SignUpActivity.this, "Error adding user data to CUSTOMER collection.", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
            }
        }); // <-- This was missing
    }
}
