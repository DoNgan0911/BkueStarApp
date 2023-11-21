package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicketReview extends AppCompatActivity {

    TextView ongOrBa, name,ngaySinh,toReview,fromReview,dayGo,timeGo,gheNgoi;
    TextView ongOrBaBack, nameBack,ngaySinhBack,toReviewBack,fromReviewBack,dayGoBack,timeGoBack,gheNgoiBack;
    ConstraintLayout layoutChieuVe;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_review);
        ongOrBa = findViewById(R.id.ongOrBa);
        name = findViewById(R.id.name);
        ngaySinh = findViewById(R.id.ngaySinh);
        toReview = findViewById(R.id.toReview);
        fromReview = findViewById(R.id.fromReview);
        dayGo = findViewById(R.id.dayGo);
        timeGo = findViewById(R.id.timeGo);
        gheNgoi = findViewById(R.id.gheNgoi);
        ongOrBaBack = findViewById(R.id.ongOrBaBack);
        nameBack = findViewById(R.id.nameBack);
        ngaySinhBack = findViewById(R.id.ngaySinhBack);
        toReviewBack = findViewById(R.id.toReviewBack);
        fromReviewBack = findViewById(R.id.fromReviewBack);
        dayGoBack = findViewById(R.id.dayGoBack);
        timeGoBack = findViewById(R.id.timeGoBack);
        gheNgoiBack = findViewById(R.id.gheNgoiBack);
        layoutChieuVe = findViewById(R.id.layoutChieuVe);
        btn = findViewById(R.id.btn);


        if (AppUtil.KhuHoi == 0) layoutChieuVe.setVisibility(View.GONE);

        ongOrBa.setText(AppUtil.GioiTinh);
        name.setText(AppUtil.edtTTHKName);
        ngaySinh.setText(AppUtil.NgaySinhHK);
        toReview.setText(AppUtil.ToLocation);
        fromReview.setText(AppUtil.FromLocation);
        dayGo.setText(AppUtil.departureDay);
        timeGo.setText(AppUtil.departueTime);
        for (int i = 0; i < AppUtil.SLVe; i++) {
            gheNgoi.setText(gheNgoi.getText() + AppUtil.GheDaChon[i] + " ");
        }
        ongOrBaBack.setText(AppUtil.GioiTinh);
        nameBack.setText(AppUtil.edtTTHKName);
        ngaySinhBack.setText(AppUtil.NgaySinhHK);
        toReviewBack.setText(AppUtil.FromLocation);
        fromReviewBack.setText(AppUtil.ToLocation);
        dayGoBack.setText(AppUtil.backDay);
        timeGoBack.setText(AppUtil.departueTimeBack);
        for (int i=0;i<AppUtil.SLVe;i++) {
            gheNgoiBack.setText(AppUtil.GheDaChon[i]);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(TicketReview.this, SearchFlightActivity.class);
                startActivity(myintent);
            }
        });


    }
}