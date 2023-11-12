package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChonGheNgoi extends AppCompatActivity {

    ImageView imageViewA1,imageViewA2,imageViewA3,imageViewA4,imageViewA5,imageViewA6,imageViewA7,imageViewA8,imageViewA9,imageViewA10;
    ImageView imageViewA11,imageViewA12,imageViewA13,imageViewA14,imageViewA15,imageViewA16,imageViewA17,imageViewA18,imageViewA19,imageViewA20;
    ImageView imageViewB1,imageViewB2,imageViewB3,imageViewB4,imageViewB5,imageViewB6,imageViewB7,imageViewB8,imageViewB9,imageViewB10;
    ImageView imageViewB11,imageViewB12,imageViewB13,imageViewB14,imageViewB15,imageViewB16,imageViewB17,imageViewB18,imageViewB19, imageViewB20;
    ImageView imageViewC1,imageViewC2,imageViewC3,imageViewC4,imageViewC5,imageViewC6,imageViewC7,imageViewC8,imageViewC9,imageViewC10;
    ImageView imageViewC11,imageViewC12,imageViewC13,imageViewC14,imageViewC15,imageViewC16,imageViewC17,imageViewC18,imageViewC19,imageViewC20;
    ImageView imageViewD1,imageViewD2,imageViewD3,imageViewD4,imageViewD5,imageViewD6,imageViewD7,imageViewD8,imageViewD9,imageViewD10;
    ImageView imageViewD11,imageViewD12,imageViewD13,imageViewD14,imageViewD15,imageViewD16,imageViewD17,imageViewD18,imageViewD19,imageViewD20;
    ImageView imageViewE1,imageViewE2,imageViewE3,imageViewE4,imageViewE5,imageViewE6,imageViewE7,imageViewE8,imageViewE9,imageViewE10;
    ImageView imageViewE11,imageViewE12,imageViewE13,imageViewE14,imageViewE15,imageViewE16,imageViewE17,imageViewE18,imageViewE19,imageViewE20;
    ImageView imageViewF1,imageViewF2,imageViewF3,imageViewF4,imageViewF5,imageViewF6,imageViewF7,imageViewF8,imageViewF9,imageViewF10;
    ImageView imageViewF11,imageViewF12,imageViewF13,imageViewF14,imageViewF15,imageViewF16,imageViewF17,imageViewF18,imageViewF19,imageViewF20;

    TextView fromLocation, toLocation, fromLocationBack, toLocationBack, hovaten;
    ImageView imageViewBack;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe_ngoi);

        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        fromLocationBack = findViewById(R.id.fromLocationBack);
        toLocationBack = findViewById(R.id.toLocationBack);
        hovaten = findViewById(R.id.hovaten);

        fromLocation.setText(AppUtil.FromLocation);
        toLocation.setText(AppUtil.ToLocation);
        fromLocationBack.setText(AppUtil.ToLocation);
        toLocationBack.setText(AppUtil.FromLocation);
        hovaten.setText(AppUtil.edtTTHKName);

        button = findViewById(R.id.button);
        imageViewBack = findViewById(R.id.imageViewBack);
        imageViewA1 = findViewById(R.id.imageViewA1);
        imageViewA2 = findViewById(R.id.imageViewA2);
        imageViewA3 = findViewById(R.id.imageViewA3);
        imageViewA4 = findViewById(R.id.imageViewA4);
        imageViewA5 = findViewById(R.id.imageViewA5);
        imageViewA6 = findViewById(R.id.imageViewA6);
        imageViewA7 = findViewById(R.id.imageViewA7);
        imageViewA8 = findViewById(R.id.imageViewA8);
        imageViewA9 = findViewById(R.id.imageViewA9);
        imageViewA10 = findViewById(R.id.imageViewA10);
        imageViewA11 = findViewById(R.id.imageViewA11);
        imageViewA12 = findViewById(R.id.imageViewA12);
        imageViewA13 = findViewById(R.id.imageViewA13);
        imageViewA14 = findViewById(R.id.imageViewA14);
        imageViewA15 = findViewById(R.id.imageViewA15);
        imageViewA16 = findViewById(R.id.imageViewA16);
        imageViewA17 = findViewById(R.id.imageViewA17);
        imageViewA18 = findViewById(R.id.imageViewA18);
        imageViewA19 = findViewById(R.id.imageViewA19);
        imageViewA20 = findViewById(R.id.imageViewA20);

        imageViewB1 = findViewById(R.id.imageViewB1);
        imageViewB2 = findViewById(R.id.imageViewB2);
        imageViewB3 = findViewById(R.id.imageViewB3);
        imageViewB4 = findViewById(R.id.imageViewB4);
        imageViewB5 = findViewById(R.id.imageViewB5);
        imageViewB6 = findViewById(R.id.imageViewB6);
        imageViewB7 = findViewById(R.id.imageViewB7);
        imageViewB8 = findViewById(R.id.imageViewB8);
        imageViewB9 = findViewById(R.id.imageViewB9);
        imageViewB10 = findViewById(R.id.imageViewB10);
        imageViewB11 = findViewById(R.id.imageViewB11);
        imageViewB12 = findViewById(R.id.imageViewB12);
        imageViewB13 = findViewById(R.id.imageViewB13);
        imageViewB14 = findViewById(R.id.imageViewB14);
        imageViewB15 = findViewById(R.id.imageViewB15);
        imageViewB16 = findViewById(R.id.imageViewB16);
        imageViewB17 = findViewById(R.id.imageViewB17);
        imageViewB18 = findViewById(R.id.imageViewB18);
        imageViewB19 = findViewById(R.id.imageViewB19);
        imageViewB20 = findViewById(R.id.imageViewB20);

        imageViewC1 = findViewById(R.id.imageViewC1);
        imageViewC2 = findViewById(R.id.imageViewC2);
        imageViewC3 = findViewById(R.id.imageViewC3);
        imageViewC4 = findViewById(R.id.imageViewC4);
        imageViewC5 = findViewById(R.id.imageViewC5);
        imageViewC6 = findViewById(R.id.imageViewC6);
        imageViewC7 = findViewById(R.id.imageViewC7);
        imageViewC8 = findViewById(R.id.imageViewC8);
        imageViewC9 = findViewById(R.id.imageViewC9);
        imageViewC10 = findViewById(R.id.imageViewC10);
        imageViewC11 = findViewById(R.id.imageViewC11);
        imageViewC12 = findViewById(R.id.imageViewC12);
        imageViewC13 = findViewById(R.id.imageViewC13);
        imageViewC14 = findViewById(R.id.imageViewC14);
        imageViewC15 = findViewById(R.id.imageViewC15);
        imageViewC16 = findViewById(R.id.imageViewC16);
        imageViewC17 = findViewById(R.id.imageViewC17);
        imageViewC18 = findViewById(R.id.imageViewC18);
        imageViewC19 = findViewById(R.id.imageViewC19);
        imageViewC20 = findViewById(R.id.imageViewC20);

        imageViewD1 = findViewById(R.id.imageViewD1);
        imageViewD2 = findViewById(R.id.imageViewD2);
        imageViewD3 = findViewById(R.id.imageViewD3);
        imageViewD4 = findViewById(R.id.imageViewD4);
        imageViewD5 = findViewById(R.id.imageViewD5);
        imageViewD6 = findViewById(R.id.imageViewD6);
        imageViewD7 = findViewById(R.id.imageViewD7);
        imageViewD8 = findViewById(R.id.imageViewD8);
        imageViewD9 = findViewById(R.id.imageViewD9);
        imageViewD10 = findViewById(R.id.imageViewD10);
        imageViewD11 = findViewById(R.id.imageViewD11);
        imageViewD12 = findViewById(R.id.imageViewD12);
        imageViewD13 = findViewById(R.id.imageViewD13);
        imageViewD14 = findViewById(R.id.imageViewD14);
        imageViewD15 = findViewById(R.id.imageViewD15);
        imageViewD16 = findViewById(R.id.imageViewD16);
        imageViewD17 = findViewById(R.id.imageViewD17);
        imageViewD18 = findViewById(R.id.imageViewD18);
        imageViewD19 = findViewById(R.id.imageViewD19);
        imageViewD20 = findViewById(R.id.imageViewD20);

        imageViewE1 = findViewById(R.id.imageViewE1);
        imageViewE2 = findViewById(R.id.imageViewE2);
        imageViewE3 = findViewById(R.id.imageViewE3);
        imageViewE4 = findViewById(R.id.imageViewE4);
        imageViewE5 = findViewById(R.id.imageViewE5);
        imageViewE6 = findViewById(R.id.imageViewE6);
        imageViewE7 = findViewById(R.id.imageViewE7);
        imageViewE8 = findViewById(R.id.imageViewE8);
        imageViewE9 = findViewById(R.id.imageViewE9);
        imageViewE10 = findViewById(R.id.imageViewE10);
        imageViewE11 = findViewById(R.id.imageViewE11);
        imageViewE12 = findViewById(R.id.imageViewE12);
        imageViewE13 = findViewById(R.id.imageViewE13);
        imageViewE14 = findViewById(R.id.imageViewE14);
        imageViewE15 = findViewById(R.id.imageViewE15);
        imageViewE16 = findViewById(R.id.imageViewE16);
        imageViewE17 = findViewById(R.id.imageViewE17);
        imageViewE18 = findViewById(R.id.imageViewE18);
        imageViewE19 = findViewById(R.id.imageViewE19);
        imageViewE20 = findViewById(R.id.imageViewE20);

        imageViewF1 = findViewById(R.id.imageViewF1);
        imageViewF2 = findViewById(R.id.imageViewF2);
        imageViewF3 = findViewById(R.id.imageViewF3);
        imageViewF4 = findViewById(R.id.imageViewF4);
        imageViewF5 = findViewById(R.id.imageViewF5);
        imageViewF6 = findViewById(R.id.imageViewF6);
        imageViewF7 = findViewById(R.id.imageViewF7);
        imageViewF8 = findViewById(R.id.imageViewF8);
        imageViewF9 = findViewById(R.id.imageViewF9);
        imageViewF10 = findViewById(R.id.imageViewF10);
        imageViewF11 = findViewById(R.id.imageViewF11);
        imageViewF12 = findViewById(R.id.imageViewF12);
        imageViewF13 = findViewById(R.id.imageViewF13);
        imageViewF14 = findViewById(R.id.imageViewF14);
        imageViewF15 = findViewById(R.id.imageViewF15);
        imageViewF16 = findViewById(R.id.imageViewF16);
        imageViewF17 = findViewById(R.id.imageViewF17);
        imageViewF18 = findViewById(R.id.imageViewF18);
        imageViewF19 = findViewById(R.id.imageViewF19);
        imageViewF20 = findViewById(R.id.imageViewF20);


        if (!AppUtil.GheDaChon.isEmpty()) {
            String gheDaChonId = "imageView" + AppUtil.GheDaChon;

            int resId = getResources().getIdentifier(gheDaChonId, "id", getPackageName());
            ImageView imageView = findViewById(resId);

            if (imageView != null) {
                imageView.setImageResource(R.drawable.ghe_dang_chon);
            }
        }




        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewA" + i, "id", getPackageName()));
            int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    initChair();
                    imageView.setImageResource(R.drawable.ghe_dang_chon);
                    AppUtil.GheDaChon = "A"+ finalI;
                }
            });
        }

            for (int i = 1; i <= 20; i++) {
                ImageView imageView = findViewById(getResources().getIdentifier("imageViewB" + i, "id", getPackageName()));
                int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        initChair();
                        imageView.setImageResource(R.drawable.ghe_dang_chon);
                        AppUtil.GheDaChon = "B"+ finalI;
                    }
                });
            }

                for (int i = 1; i <= 20; i++) {
                    ImageView imageView = findViewById(getResources().getIdentifier("imageViewC" + i, "id", getPackageName()));
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View view) {
                            initChair();
                            imageView.setImageResource(R.drawable.ghe_dang_chon);
                            AppUtil.GheDaChon = "C"+ finalI;
                        }
                    });
                }

                    for (int i = 1; i <= 20; i++) {
                        ImageView imageView = findViewById(getResources().getIdentifier("imageViewD" + i, "id", getPackageName()));
                        int finalI = i;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint("ResourceAsColor")
                            @Override
                            public void onClick(View view) {
                                initChair();
                                imageView.setImageResource(R.drawable.ghe_dang_chon);
                                AppUtil.GheDaChon = "D"+ finalI;
                            }
                        });
                    }

                        for (int i = 1; i <= 20; i++) {
                            ImageView imageView = findViewById(getResources().getIdentifier("imageViewE" + i, "id", getPackageName()));
                            int finalI = i;
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick(View view) {
                                    initChair();
                                    imageView.setImageResource(R.drawable.ghe_dang_chon);
                                    AppUtil.GheDaChon = "E"+ finalI;
                                }
                            });
                        }


        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewF" + i, "id", getPackageName()));
            int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    initChair();
                    imageView.setImageResource(R.drawable.ghe_dang_chon);
                    AppUtil.GheDaChon = "F"+ finalI;
                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLocalManager.setStringGheNgoi(AppUtil.GheDaChon);
                Intent myintent = new Intent(ChonGheNgoi.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLocalManager.setStringGheNgoi(AppUtil.GheDaChon);
                Intent myintent = new Intent(ChonGheNgoi.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });
    }
    public void initChair(){
        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewA" + i, "id", getPackageName()));
            imageView.setImageResource(R.drawable.baseline_chair_24_da_chon);
        }
        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewB" + i, "id", getPackageName()));
            imageView.setImageResource(R.drawable.baseline_chair_24_da_chon);
        }
        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewC" + i, "id", getPackageName()));
            imageView.setImageResource(R.drawable.baseline_chair_24_da_chon);
        }
        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewD" + i, "id", getPackageName()));
            imageView.setImageResource(R.drawable.baseline_chair_24_da_chon);
        }
        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewE" + i, "id", getPackageName()));
            imageView.setImageResource(R.drawable.baseline_chair_24_da_chon);
        }
        for (int i = 1; i <= 20; i++) {
            ImageView imageView = findViewById(getResources().getIdentifier("imageViewF" + i, "id", getPackageName()));
            imageView.setImageResource(R.drawable.baseline_chair_24_da_chon);
        }
    }
}