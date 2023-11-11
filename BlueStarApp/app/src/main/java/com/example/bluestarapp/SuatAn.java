package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.A;

public class SuatAn extends AppCompatActivity {

    ImageView imageViewBack, imageViewUpComChienChay, imageViewDownComChienChay,imageViewUpComTam,imageViewDownComTam,imageViewUpMiY,imageViewDownMiY,imageViewUpBanhMi,imageViewDownBanhMi, imageViewUpComChienChayBack,imageViewDownComChienChayBack,imageViewUpComTamBack,imageViewDownComTamBack,imageViewUpMiYBack,imageViewDownMiYBack,imageViewUpBanhMiBack,imageViewDownBanhMiBack;
    TextView textViewSLComChienChay, textViewSLComTam,textViewSLMiY,textViewSLBanhMi;
    TextView textViewSLComChienChayBack, textViewSLComTamBack,textViewSLMiYBack,textViewSLBanhMiBack, textViewTongTien, fromLocation, toLocation, fromLocationBack, toLocationBack, hovaten, hovatenBack;

    Button btnNext;
    int SLComChienChay = 0;
    int SLComTam = 0;
    int SLMiY = 0;
    int SLBanhMi = 0;
    int SLComChienChayBack = 0;
    int SLComTamBack = 0;
    int SLMiYBack = 0;
    int SLBanhMiBack = 0;

    int TongTien = Integer.parseInt(String.valueOf(AppUtil.OriginalPrice));
    int TienSuatAn = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suat_an);

        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        fromLocationBack = findViewById(R.id.fromLocationBack);
        toLocationBack = findViewById(R.id.toLocationBack);
        hovaten = findViewById(R.id.hovaten);
        hovatenBack = findViewById(R.id.hovatenBack);

        imageViewUpComChienChay = findViewById(R.id.imageViewUpComChienChay);
        imageViewDownComChienChay = findViewById(R.id.imageViewDownComChienChay);
        imageViewUpComTam = findViewById(R.id.imageViewUpComTam);
        imageViewDownComTam = findViewById(R.id.imageViewDownComTam);
        imageViewUpMiY = findViewById(R.id.imageViewUpMiY);
        imageViewDownMiY = findViewById(R.id.imageViewDownMiY);
        imageViewUpBanhMi = findViewById(R.id.imageViewUpBanhMi);
        imageViewDownBanhMi = findViewById(R.id.imageViewDownBanhMi);
        imageViewUpComChienChayBack = findViewById(R.id.imageViewUpComChienChayBack);
        imageViewDownComChienChayBack = findViewById(R.id.imageViewDownComChienChayBack);
        imageViewUpComTamBack = findViewById(R.id.imageViewUpComTamBack);
        imageViewDownComTamBack = findViewById(R.id.imageViewDownComTamBack);
        imageViewUpMiYBack = findViewById(R.id.imageViewUpMiYBack);
        imageViewDownMiYBack = findViewById(R.id.imageViewDownMiYBack);
        imageViewUpBanhMiBack = findViewById(R.id.imageViewUpBanhMiBack);
        imageViewDownBanhMiBack = findViewById(R.id.imageViewDownBanhMiBack);

        textViewTongTien = findViewById(R.id.textViewTongTien);
        textViewSLComChienChay = findViewById(R.id.textViewSLComChienChay);
        textViewSLComTam = findViewById(R.id.textViewSLComTam);
        textViewSLMiY = findViewById(R.id.textViewSLMiY);
        textViewSLBanhMi = findViewById(R.id.textViewSLBanhMi);
        textViewSLComChienChayBack = findViewById(R.id.textViewSLComChienChayBack);
        textViewSLComTamBack = findViewById(R.id.textViewSLComTamBack);
        textViewSLMiYBack = findViewById(R.id.textViewSLMiYBack);
        textViewSLBanhMiBack = findViewById(R.id.textViewSLBanhMiBack);
        imageViewBack = findViewById(R.id.imageViewBack);
        btnNext = findViewById(R.id.btnNext);

        textViewTongTien.setText(String.valueOf(TongTien));

        fromLocation.setText(AppUtil.FromLocation);
        toLocation.setText(AppUtil.ToLocation);
        fromLocationBack.setText(AppUtil.ToLocation);
        toLocationBack.setText(AppUtil.FromLocation);
        hovaten.setText(AppUtil.edtTTHKName);
        hovatenBack.setText(AppUtil.edtTTHKName);
        textViewSLComChienChay.setText(String.valueOf(AppUtil.SLComChienChay));
        textViewSLComTam.setText(String.valueOf(AppUtil.SLComTam));
        textViewSLMiY.setText(String.valueOf(AppUtil.SLMiY));
        textViewSLBanhMi.setText(String.valueOf(AppUtil.SLBanhMi));
        textViewSLComChienChayBack.setText(String.valueOf(AppUtil.SLComChienChayBack));
        textViewSLComTamBack.setText(String.valueOf(AppUtil.SLComTamBack));
        textViewSLMiYBack.setText(String.valueOf(AppUtil.SLMiYBack));
        textViewSLBanhMiBack.setText(String.valueOf(AppUtil.SLBanhMiBack));

        SLComChienChay = AppUtil.SLComChienChay;
        SLComTam = AppUtil.SLComTam;
        SLMiY = AppUtil.SLMiY;
        SLBanhMi = AppUtil.SLBanhMi;
        SLComChienChayBack = AppUtil.SLComChienChayBack;
        SLComTamBack = AppUtil.SLComTamBack;
        SLMiYBack = AppUtil.SLMiYBack;
        SLBanhMiBack = AppUtil.SLBanhMiBack;


        imageViewUpComChienChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLComChienChay += 1;
                textViewSLComChienChay.setText(String.valueOf(SLComChienChay));
                AppUtil.SLComChienChay +=1;
                TongTien += 236000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownComChienChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLComChienChay == 0) {
                    SLComChienChay = 0;
                }
                else {
                    SLComChienChay -= 1;
                    AppUtil.SLComChienChay -=1;
                    TongTien -= 236000;
                }
                textViewSLComChienChay.setText(String.valueOf(SLComChienChay));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpComTam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLComTam += 1;
                textViewSLComTam.setText(String.valueOf(SLComTam));
                AppUtil.SLComTam +=1;
                TongTien += 186000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownComTam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLComTam == 0) {
                    SLComTam = 0;
                }
                else {
                    SLComTam -= 1;
                    AppUtil.SLComTam -=1;
                    TongTien -= 186000;
                }
                textViewSLComTam.setText(String.valueOf(SLComTam));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpMiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLMiY += 1;
                textViewSLMiY.setText(String.valueOf(SLMiY));
                AppUtil.SLMiY +=1;
                TongTien += 136000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownMiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLMiY == 0) {
                    SLMiY = 0;
                }
                else {
                    SLMiY -= 1;
                    AppUtil.SLMiY -=1;
                    TongTien -= 136000;
                }
                textViewSLMiY.setText(String.valueOf(SLMiY));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpBanhMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLBanhMi += 1;
                textViewSLBanhMi.setText(String.valueOf(SLBanhMi));
                AppUtil.SLBanhMi +=1;
                TongTien += 86000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownBanhMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLBanhMi == 0) {
                    SLBanhMi = 0;
                }
                else {
                    SLBanhMi -= 1;
                    AppUtil.SLBanhMi -=1;
                    TongTien -= 86000;
                }
                textViewSLBanhMi.setText(String.valueOf(SLBanhMi));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpComChienChayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLComChienChayBack += 1;
                textViewSLComChienChayBack.setText(String.valueOf(SLComChienChayBack));
                AppUtil.SLComChienChayBack +=1;
                TongTien += 236000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownComChienChayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLComChienChayBack == 0) {
                    SLComChienChayBack = 0;
                }
                else {
                    SLComChienChayBack -= 1;
                    AppUtil.SLComChienChayBack -=1;
                    TongTien -= 236000;
                }
                textViewSLComChienChayBack.setText(String.valueOf(SLComChienChayBack));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpComTamBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLComTamBack += 1;
                textViewSLComTamBack.setText(String.valueOf(SLComTamBack));
                AppUtil.SLComTamBack +=1;
                TongTien += 186000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownComTamBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLComTamBack == 0) {
                    SLComTamBack = 0;
                }
                else {
                    SLComTamBack -= 1;
                    AppUtil.SLComTamBack -=1;
                    TongTien -= 186000;
                }
                textViewSLComTamBack.setText(String.valueOf(SLComTamBack));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpMiYBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLMiYBack += 1;
                textViewSLMiYBack.setText(String.valueOf(SLMiYBack));
                AppUtil.SLMiYBack +=1;
                TongTien += 136000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownMiYBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLMiYBack == 0) {
                    SLMiYBack = 0;
                }
                else {
                    SLMiYBack -= 1;
                    AppUtil.SLMiYBack -=1;
                    TongTien -= 136000;
                }
                textViewSLMiYBack.setText(String.valueOf(SLMiYBack));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewUpBanhMiBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLBanhMiBack += 1;
                textViewSLBanhMiBack.setText(String.valueOf(SLBanhMiBack));
                AppUtil.SLBanhMiBack +=1;
                TongTien += 86000;
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });

        imageViewDownBanhMiBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SLBanhMiBack == 0) {
                    SLBanhMiBack = 0;
                }
                else {
                    SLBanhMiBack -= 1;
                    AppUtil.SLBanhMiBack -=1;
                    TongTien -= 86000;
                }
                textViewSLBanhMiBack.setText(String.valueOf(SLBanhMiBack));
                textViewTongTien.setText(String.valueOf(TongTien));
            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(SuatAn.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(SuatAn.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });


    }
}