package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HanhLyKyGui extends AppCompatActivity {

    LinearLayout layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg;
    LinearLayout layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack;
    TextView textViewTongTien, fromLocation, toLocation, fromLocationBack, toLocationBack, hovaten, hovatenBack;
    Button btnNext;

    int i =0 ,j = 0;
    int Tong = Integer.parseInt(AppUtil.OriginalPrice);
    int TongTien = Tong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanh_ly_ky_gui);

        layout20kg = findViewById(R.id.layout20kg);
        layout30kg = findViewById(R.id.layout30kg);
        layout40kg = findViewById(R.id.layout40kg);
        layout50kg = findViewById(R.id.layout50kg);
        layout60kg = findViewById(R.id.layout60kg);
        layout70kg = findViewById(R.id.layout70kg);
        layout20kgBack = findViewById(R.id.layout20kgBack);
        layout30kgBack = findViewById(R.id.layout30kgBack);
        layout40kgBack = findViewById(R.id.layout40kgBack);
        layout50kgBack = findViewById(R.id.layout50kgBack);
        layout60kgBack = findViewById(R.id.layout60kgBack);
        layout70kgBack = findViewById(R.id.layout70kgBack);
        textViewTongTien = findViewById(R.id.textViewTongTien);
        btnNext = findViewById(R.id.btnNext);
        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        fromLocationBack = findViewById(R.id.fromLocationBack);
        toLocationBack = findViewById(R.id.toLocationBack);
        hovaten = findViewById(R.id.hovaten);
        hovatenBack = findViewById(R.id.hovatenBack);


        fromLocation.setText(AppUtil.FromLocation);
        toLocation.setText(AppUtil.ToLocation);
        fromLocationBack.setText(AppUtil.ToLocation);
        toLocationBack.setText(AppUtil.FromLocation);
        textViewTongTien.setText(AppUtil.OriginalPrice);
        hovaten.setText(AppUtil.edtTTHKName);
        hovatenBack.setText(AppUtil.edtTTHKName);



        layout20kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceGo();
                offBackgroundDepart();
                layout20kg.setBackgroundResource(R.color.blue);
                TongTien += 200000;
                textViewTongTien.setText(String.valueOf(TongTien));
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                i = 2;
            }
        });

        layout30kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceGo();
                offBackgroundDepart();
                layout30kg.setBackgroundResource(R.color.blue);
                TongTien += 300000;
                textViewTongTien.setText(String.valueOf(TongTien));
                i = 3;
            }
        });
        layout40kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceGo();
                offBackgroundDepart();
                layout40kg.setBackgroundResource(R.color.blue);
                TongTien += 400000;
                textViewTongTien.setText(String.valueOf(TongTien));
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                i = 4;
            }
        });
        layout50kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceGo();
                offBackgroundDepart();
                layout50kg.setBackgroundResource(R.color.blue);
                TongTien += 500000;
                textViewTongTien.setText(String.valueOf(TongTien));
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                i = 5;
            }
        });
        layout60kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceGo();
                offBackgroundDepart();
                layout60kg.setBackgroundResource(R.color.blue);
                TongTien += 600000;
                textViewTongTien.setText(String.valueOf(TongTien));
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                 i = 6;
            }
        });
        layout70kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceGo();
                offBackgroundDepart();
                layout70kg.setBackgroundResource(R.color.blue);
                TongTien += 700000;
                textViewTongTien.setText(String.valueOf(TongTien));
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                 i = 7;
            }
        });

        layout20kgBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkPriceBack();
                offBackgroundBack();
                layout20kgBack.setBackgroundResource(R.color.blue);
                TongTien += 200000;
                textViewTongTien.setText(String.valueOf(TongTien));

                j = 2;
            }
        });

        layout30kgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceBack();
                offBackgroundBack();
                layout30kgBack.setBackgroundResource(R.color.blue);
                TongTien += 300000;
                textViewTongTien.setText(String.valueOf(TongTien));
                j = 3;

            }
        });
        layout40kgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceBack();
                offBackgroundBack();
                layout40kgBack.setBackgroundResource(R.color.blue);
                TongTien += 400000;
                textViewTongTien.setText(String.valueOf(TongTien));
                j = 4;
            }
        });
        layout50kgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceBack();
                offBackgroundBack();
                layout50kgBack.setBackgroundResource(R.color.blue);
                TongTien += 500000;
                textViewTongTien.setText(String.valueOf(TongTien));
                j = 5;
            }
        });
        layout60kgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceBack();
                offBackgroundBack();
                layout60kgBack.setBackgroundResource(R.color.blue);
                TongTien += 600000;
                textViewTongTien.setText(String.valueOf(TongTien));
                j = 6;
            }
        });
        layout70kgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPriceBack();
                offBackgroundBack();
                layout70kgBack.setBackgroundResource(R.color.blue);
                TongTien += 700000;
                textViewTongTien.setText(String.valueOf(TongTien));
                j = 7;
            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(HanhLyKyGui.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });

    }

    private void offBackgroundDepart(){
        layout20kg.setBackgroundResource(R.color.white);
        layout30kg.setBackgroundResource(R.color.white);
        layout40kg.setBackgroundResource(R.color.white);
        layout50kg.setBackgroundResource(R.color.white);
        layout60kg.setBackgroundResource(R.color.white);
        layout70kg.setBackgroundResource(R.color.white);
    }

    private void offBackgroundBack(){
        layout20kgBack.setBackgroundResource(R.color.white);
        layout30kgBack.setBackgroundResource(R.color.white);
        layout40kgBack.setBackgroundResource(R.color.white);
        layout50kgBack.setBackgroundResource(R.color.white);
        layout60kgBack.setBackgroundResource(R.color.white);
        layout70kgBack.setBackgroundResource(R.color.white);
    }

    public void checkPriceGo(){
        if ( i == 2) TongTien -= 200000;
        else if ( i == 3) TongTien -= 300000;
        else if ( i == 4) TongTien -= 400000;
        else if ( i == 5) TongTien -= 500000;
        else if ( i == 6) TongTien -= 600000;
        else if ( i == 7) TongTien -= 700000;
    }

    public void checkPriceBack(){
        if ( j == 2) TongTien -= 200000;
        else if ( j == 3) TongTien -= 300000;
        else if ( j == 4) TongTien -= 400000;
        else if ( j == 5) TongTien -= 500000;
        else if ( j == 6) TongTien -= 600000;
        else if ( j == 7) TongTien -= 700000;
    }



}