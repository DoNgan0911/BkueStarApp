package com.example.bluestarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    private EditText Email_et;
    private Button btn_forgot;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        Email_et = findViewById(R.id.email_signin);
        btn_forgot = findViewById(R.id.btn_signin);

        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email_et.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(ForgotPassActivity.this, "Please provide your email", Toast.LENGTH_SHORT).show();
                }
                else forgotPass();
            }
        });
    }
    private void forgotPass(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                    Fragment profileFragment = new ProfileFragment(); // Khởi tạo fragment
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    // Thay thế fragment hiện tại bằng ProfileFragment
                    transaction.replace(R.id.framelayout, profileFragment); // R.id.fragment_container là ID của layout chứa fragment trong activity của bạn
                    transaction.addToBackStack(null); // Để có thể nhấn nút back để trở về
                    transaction.commit();
                }else Toast.makeText(ForgotPassActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}