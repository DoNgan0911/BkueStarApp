package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MuaThemDichVu extends AppCompatActivity {
    ImageView imageViewHLKG;
    ImageView imageViewCGN, imageViewSA;
    ConstraintLayout layoutChieuVe;
    TextView textViewTongTien, fromLocation, fromLocationBack, toLocation, toLocationBack , textViewNgayDi, textViewThoiGianDi, textViewThoiGianDen, textViewNgayDiBack, textViewThoiGianDiBack, textViewThoiGianDenBack;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_them_dich_vu);
        imageViewHLKG = findViewById(R.id.imageViewHLKG);
        imageViewCGN = findViewById(R.id.imageViewCGN);
        imageViewSA = findViewById(R.id.imageViewSA);
        textViewTongTien = findViewById(R.id.textViewTongTien);
        fromLocation = findViewById(R.id.fromLocation);
        fromLocationBack = findViewById(R.id.fromLocationBack);
        toLocation = findViewById(R.id.toLocation);
        toLocationBack = findViewById(R.id.toLocationBack);
        textViewNgayDi = findViewById(R.id.textViewNgayDi);
        textViewThoiGianDi = findViewById(R.id.textViewThoiGianDi);
        textViewThoiGianDen = findViewById(R.id.textViewThoiGianDen);
        layoutChieuVe = findViewById(R.id.layoutChieuVe);

        if (AppUtil.KhuHoi == 0 ) {
            layoutChieuVe.setVisibility(View.GONE);
        }
        else {
            layoutChieuVe.setVisibility(View.VISIBLE);

            fromLocationBack = findViewById(R.id.fromLocationBack);
            toLocationBack = findViewById(R.id.toLocationBack);
            textViewNgayDiBack = findViewById(R.id.textViewNgayDiBack);
            textViewThoiGianDiBack = findViewById(R.id.textViewThoiGianDiBack);
            textViewThoiGianDenBack = findViewById(R.id.textViewThoiGianDenBack);

            fromLocationBack.setText(AppUtil.ToLocation);
            toLocationBack.setText(AppUtil.FromLocation);
            textViewNgayDiBack.setText(AppUtil.backDay);
            textViewThoiGianDiBack.setText(AppUtil.departueTimeBack);
            textViewThoiGianDenBack.setText(AppUtil.arrivalTimeBack);
        }


        fromLocation.setText(AppUtil.FromLocation);
        toLocation.setText(AppUtil.ToLocation);
        textViewTongTien.setText(AppUtil.OriginalPrice);
        textViewNgayDi.setText(AppUtil.departureDay);
        textViewThoiGianDi.setText(AppUtil.departueTime);
        textViewThoiGianDen.setText(AppUtil.arrivalTime);


        imageViewHLKG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MuaThemDichVu.this, HanhLyKyGui.class);
                startActivity(myintent);

            }
        });

        imageViewCGN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MuaThemDichVu.this, ChonGheNgoi.class);
                startActivity(myintent);

            }
        });

        imageViewSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MuaThemDichVu.this, SuatAn.class);
                startActivity(myintent);

            }
        });





    }
}