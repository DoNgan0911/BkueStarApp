package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bluestarapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.bluestarapp.R;

public class MainActivity extends AppCompatActivity {
//    FirebaseAuth auth;
//    Button button;
//    TextView textView;
//    FirebaseUser user;

//    MENU
    ActivityMainBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        MENU
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.ticket:
                    replaceFragment(new TicketFragment());
                    break;

            }
            return true;
        });

//        auth = FirebaseAuth.getInstance();
//        button = findViewById(R.id.click_logout);
//        textView = findViewById(R.id.textView);
//        user = auth.getCurrentUser();
//        if(user == null){
//            Intent i = new Intent(getApplicationContext(), SignInActivity.class);
//            startActivity(i);
//            finish();
//        }
//        else {
//            textView.setText(user.getEmail());
//        }
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
//    }
}
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}