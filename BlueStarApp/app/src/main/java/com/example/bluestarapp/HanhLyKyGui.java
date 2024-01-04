package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class HanhLyKyGui extends AppCompatActivity {


    TextView textViewTongTien, fromLocation, toLocation, fromLocationBack, toLocationBack, hovaten;
    ImageView imageViewBack;
    Button btnNext;
    LinearLayout linearLayout_Parent;

    int[] l = new int[0];

    int Tong = Integer.parseInt(AppUtil.OriginalPrice);
    int TongTien = Tong;
    int[] TienTungNguoi = new int[0];

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanh_ly_ky_gui);

        textViewTongTien = findViewById(R.id.textViewTongTien);
        btnNext = findViewById(R.id.btnNext);
        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        fromLocationBack = findViewById(R.id.fromLocationBack);
        toLocationBack = findViewById(R.id.toLocationBack);
        hovaten = findViewById(R.id.hovaten);
        imageViewBack = findViewById(R.id.imageViewBack);
        linearLayout_Parent = findViewById(R.id.linearLayout_Parent);


        textViewTongTien.setText(AppUtil.OriginalPrice);



        int soLuongVe = AppUtil.SLVe;
        if (AppUtil.KhuHoi == 1) {
            TienTungNguoi = new int[soLuongVe * 2];
            l = new int[soLuongVe * 2];
        }
        else {
            TienTungNguoi = new int[soLuongVe];
            l = new int[soLuongVe];
        }


        for (int i =0; i < soLuongVe;i++) TienTungNguoi[i] = 0;



        for (int i = 0; i < soLuongVe; i++) {
            // Tạo mới layoutTTHK
            LinearLayout childLayoutHLKGChieuDi = (LinearLayout) getLayoutInflater().inflate(R.layout.child_hanhlykygui_chieudi, null);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 16);  // Đặt khoảng cách dưới là 16dp

            childLayoutHLKGChieuDi.setLayoutParams(layoutParams);



            childLayoutHLKGChieuDi.setId(View.generateViewId());
            TextView fromLocation = childLayoutHLKGChieuDi.findViewById(R.id.fromLocation);
            TextView toLocation = childLayoutHLKGChieuDi.findViewById(R.id.toLocation);
            TextView hovaten = childLayoutHLKGChieuDi.findViewById(R.id.hovaten);

            fromLocation.setText(AppUtil.FromLocation);
            toLocation.setText(AppUtil.ToLocation);
            hovaten.setText(AppUtil.edtTTHKName[i]);


            linearLayout_Parent.addView(childLayoutHLKGChieuDi);

        }

        for (int i = 0; i<soLuongVe;i++) {

            final int finalI = i;
            LinearLayout childLayout = (LinearLayout) linearLayout_Parent.getChildAt(i);
            LinearLayout layout20kg = childLayout.findViewById(R.id.layout20kg);
            LinearLayout layout30kg = childLayout.findViewById(R.id.layout30kg);
            LinearLayout layout40kg = childLayout.findViewById(R.id.layout40kg);
            LinearLayout layout50kg = childLayout.findViewById(R.id.layout50kg);
            LinearLayout layout60kg = childLayout.findViewById(R.id.layout60kg);
            LinearLayout layout70kg = childLayout.findViewById(R.id.layout70kg);

            setBeforeLayout(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg, finalI);

            Log.e("TongTien", "TongTien: " + TongTien);

            layout20kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l[finalI] == 2 ) {
                        TienTungNguoi[finalI] -= 200000;
                        TongTien -= 200000;
                        layout20kg.setBackgroundResource(R.color.white);
                        textViewTongTien.setText(String.valueOf(TongTien));
                        l[finalI] = 0;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "0";
                    }
                    else {
                        checkPriceGo(finalI);
                        checkPriceGoTongTien(finalI);
                        resetBackground(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg);
                        layout20kg.setBackgroundResource(R.color.blue);
                        TienTungNguoi[finalI] += 200000;
                        TongTien += TienTungNguoi[finalI];
                        textViewTongTien.setText(String.valueOf( TongTien));
                        AppUtil.KG[finalI] = "20kg";
                        l[finalI] = 2;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "2";
                    }
                    AppUtil.OriginalPrice = String.valueOf( TongTien);
                }
            });
            layout30kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( l[finalI] == 3){
                        TienTungNguoi[finalI] -= 300000;
                        TongTien -= 300000;
                        layout30kg.setBackgroundResource(R.color.white);
                        textViewTongTien.setText(String.valueOf( TongTien));
                        l[finalI] = 0;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "0";

                    }
                    else {
                        checkPriceGo(finalI);
                        checkPriceGoTongTien(finalI);
                        resetBackground(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg);
                        layout30kg.setBackgroundResource(R.color.blue);
                        TienTungNguoi[finalI] += 300000;
                        TongTien += TienTungNguoi[finalI];
                        textViewTongTien.setText(String.valueOf( TongTien));
                        AppUtil.KG[finalI] = "30kg";
                        l[finalI] = 3;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "3";
                    }
                    AppUtil.OriginalPrice = String.valueOf(TongTien);
                }
            });
            layout40kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l[finalI] ==4 ){
                        TienTungNguoi[finalI] -= 400000;
                        TongTien -= 400000;
                        layout40kg.setBackgroundResource(R.color.white);
                        textViewTongTien.setText(String.valueOf(TongTien));
                        l[finalI] = 0;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "0";
                    }
                    else {
                        checkPriceGo(finalI);
                        checkPriceGoTongTien(finalI);
                        resetBackground(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg);
                        layout40kg.setBackgroundResource(R.color.blue);
                        TienTungNguoi[finalI] += 400000;
                        TongTien += TienTungNguoi[finalI];
                        textViewTongTien.setText(String.valueOf( TongTien));
                        AppUtil.KG[finalI] = "40kg";
                        l[finalI] = 4;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "4";
                    }
                    AppUtil.OriginalPrice = String.valueOf(TongTien);
                }
            });

            layout50kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l[finalI] ==5 ){
                        TienTungNguoi[finalI] -= 500000;
                        TongTien -= 500000;
                        layout50kg.setBackgroundResource(R.color.white);
                        textViewTongTien.setText(String.valueOf(TongTien));
                        l[finalI] = 0;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "0";
                    }
                    else {
                        checkPriceGo(finalI);
                        checkPriceGoTongTien(finalI);
                        resetBackground(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg);
                        layout50kg.setBackgroundResource(R.color.blue);
                        TienTungNguoi[finalI] += 500000;
                        TongTien += TienTungNguoi[finalI];
                        textViewTongTien.setText(String.valueOf( TongTien));
                        AppUtil.KG[finalI] = "50kg";
                        l[finalI] = 5;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "5";
                    }
                    AppUtil.OriginalPrice = String.valueOf(TongTien);
                }
            });
            layout60kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l[finalI] ==6 ){
                        TienTungNguoi[finalI] -= 600000;
                        TongTien -= 600000;
                        layout60kg.setBackgroundResource(R.color.white);
                        textViewTongTien.setText(String.valueOf(TongTien));
                        l[finalI] = 0;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "0";
                    }
                    else {
                        checkPriceGo(finalI);
                        checkPriceGoTongTien(finalI);
                        resetBackground(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg);
                        layout60kg.setBackgroundResource(R.color.blue);
                        TienTungNguoi[finalI] += 600000;
                        TongTien += TienTungNguoi[finalI];
                        textViewTongTien.setText(String.valueOf( TongTien));
                        AppUtil.KG[finalI] = "60kg";
                        l[finalI] = 6;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "6";
                    }
                    AppUtil.OriginalPrice = String.valueOf(TongTien);
                }
            });
            layout70kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l[finalI] ==7 ){
                        TienTungNguoi[finalI] -= 700000;
                        TongTien -= 700000;
                        layout70kg.setBackgroundResource(R.color.white);
                        textViewTongTien.setText(String.valueOf(TongTien));
                        l[finalI] = 0;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "0";
                    }
                    else {
                        checkPriceGo(finalI);
                        checkPriceGoTongTien(finalI);
                        resetBackground(layout20kg,layout30kg,layout40kg,layout50kg,layout60kg,layout70kg);
                        layout70kg.setBackgroundResource(R.color.blue);
                        TienTungNguoi[finalI] += 700000;
                        TongTien += TienTungNguoi[finalI];
                        textViewTongTien.setText(String.valueOf( TongTien));
                        AppUtil.KG[finalI] = "70kg";
                        l[finalI] = 7;
                        Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                        AppUtil.HLKGTungNguoiChieuDi[finalI] = "7";
                    }
                    AppUtil.OriginalPrice = String.valueOf(TongTien);
                }
            });

        }




        if (AppUtil.KhuHoi == 1) {
            for (int i = 0; i < soLuongVe; i++) {

                LinearLayout childLayoutHLKGChieuVe = (LinearLayout) getLayoutInflater().inflate(R.layout.child_hanhlykygui_chieuve, null);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 0, 0, 16);  // Đặt khoảng cách dưới là 16dp

                childLayoutHLKGChieuVe.setLayoutParams(layoutParams);


                childLayoutHLKGChieuVe.setId(View.generateViewId());
                TextView fromLocation = childLayoutHLKGChieuVe.findViewById(R.id.fromLocationBack);
                TextView toLocation = childLayoutHLKGChieuVe.findViewById(R.id.toLocationBack);
                TextView hovaten = childLayoutHLKGChieuVe.findViewById(R.id.hovaten);

                fromLocation.setText(AppUtil.ToLocation);
                toLocation.setText(AppUtil.FromLocation);
                hovaten.setText(AppUtil.edtTTHKName[i]);


                linearLayout_Parent.addView(childLayoutHLKGChieuVe);

            }

            for (int i = soLuongVe; i<soLuongVe*2;i++) {

                final int finalI = i;
                LinearLayout childLayout = (LinearLayout) linearLayout_Parent.getChildAt(i);
                LinearLayout layout20kgBack = childLayout.findViewById(R.id.layout20kgBack);
                LinearLayout layout30kgBack = childLayout.findViewById(R.id.layout30kgBack);
                LinearLayout layout40kgBack = childLayout.findViewById(R.id.layout40kgBack);
                LinearLayout layout50kgBack = childLayout.findViewById(R.id.layout50kgBack);
                LinearLayout layout60kgBack = childLayout.findViewById(R.id.layout60kgBack);
                LinearLayout layout70kgBack = childLayout.findViewById(R.id.layout70kgBack);

                setBeforeLayout(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack, finalI);

                layout20kgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l[finalI] == 2 ) {
                            TienTungNguoi[finalI] -= 200000;
                            TongTien -= 200000;
                            layout20kgBack.setBackgroundResource(R.color.white);
                            textViewTongTien.setText(String.valueOf(TongTien));
                            l[finalI] = 0;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "0";
                        }
                        else {
                            checkPriceGo(finalI);
                            checkPriceGoTongTien(finalI);
                            resetBackground(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack);
                            layout20kgBack.setBackgroundResource(R.color.blue);
                            TienTungNguoi[finalI] += 200000;
                            TongTien += TienTungNguoi[finalI];
                            textViewTongTien.setText(String.valueOf( TongTien));
                            AppUtil.KG[finalI] = "20kg";
                            l[finalI] = 2;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "2";
                        }
                        AppUtil.OriginalPrice = String.valueOf( TongTien);
                    }
                });
                layout30kgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( l[finalI] == 3){
                            TienTungNguoi[finalI] -= 300000;
                            TongTien -= 300000;
                            layout30kgBack.setBackgroundResource(R.color.white);
                            textViewTongTien.setText(String.valueOf( TongTien));
                            l[finalI] = 0;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "0";

                        }
                        else {
                            checkPriceGo(finalI);
                            checkPriceGoTongTien(finalI);
                            resetBackground(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack);
                            layout30kgBack.setBackgroundResource(R.color.blue);
                            TienTungNguoi[finalI] += 300000;
                            TongTien += TienTungNguoi[finalI];
                            textViewTongTien.setText(String.valueOf( TongTien));
                            AppUtil.KG[finalI] = "30kg";
                            l[finalI] = 3;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "3";
                        }
                        AppUtil.OriginalPrice = String.valueOf(TongTien);
                    }
                });
                layout40kgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l[finalI] ==4 ){
                            TienTungNguoi[finalI] -= 400000;
                            TongTien -= 400000;
                            layout40kgBack.setBackgroundResource(R.color.white);
                            textViewTongTien.setText(String.valueOf(TongTien));
                            l[finalI] = 0;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "0";
                        }
                        else {
                            checkPriceGo(finalI);
                            checkPriceGoTongTien(finalI);
                            resetBackground(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack);
                            layout40kgBack.setBackgroundResource(R.color.blue);
                            TienTungNguoi[finalI] += 400000;
                            TongTien += TienTungNguoi[finalI];
                            textViewTongTien.setText(String.valueOf( TongTien));
                            AppUtil.KG[finalI] = "40kg";
                            l[finalI] = 4;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "4";
                        }
                        AppUtil.OriginalPrice = String.valueOf(TongTien);
                    }
                });

                layout50kgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l[finalI] ==5 ){
                            TienTungNguoi[finalI] -= 500000;
                            TongTien -= 500000;
                            layout50kgBack.setBackgroundResource(R.color.white);
                            textViewTongTien.setText(String.valueOf(TongTien));
                            l[finalI] = 0;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "0";
                        }
                        else {
                            checkPriceGo(finalI);
                            checkPriceGoTongTien(finalI);
                            resetBackground(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack);
                            layout50kgBack.setBackgroundResource(R.color.blue);
                            TienTungNguoi[finalI] += 500000;
                            TongTien += TienTungNguoi[finalI];
                            textViewTongTien.setText(String.valueOf( TongTien));
                            AppUtil.KG[finalI] = "50kg";
                            l[finalI] = 5;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "5";
                        }
                        AppUtil.OriginalPrice = String.valueOf(TongTien);
                    }
                });
                layout60kgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l[finalI] ==6 ){
                            TienTungNguoi[finalI] -= 600000;
                            TongTien -= 600000;
                            layout60kgBack.setBackgroundResource(R.color.white);
                            textViewTongTien.setText(String.valueOf(TongTien));
                            l[finalI] = 0;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "0";
                        }
                        else {
                            checkPriceGo(finalI);
                            checkPriceGoTongTien(finalI);
                            resetBackground(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack);
                            layout60kgBack.setBackgroundResource(R.color.blue);
                            TienTungNguoi[finalI] += 600000;
                            TongTien += TienTungNguoi[finalI];
                            textViewTongTien.setText(String.valueOf( TongTien));
                            AppUtil.KG[finalI] = "60kg";
                            l[finalI] = 6;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "6";
                        }
                        AppUtil.OriginalPrice = String.valueOf(TongTien);
                    }
                });
                layout70kgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l[finalI] ==7 ){
                            TienTungNguoi[finalI] -= 700000;
                            TongTien -= 700000;
                            layout70kgBack.setBackgroundResource(R.color.white);
                            textViewTongTien.setText(String.valueOf(TongTien));
                            l[finalI] = 0;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "0";
                        }
                        else {
                            checkPriceGo(finalI);
                            checkPriceGoTongTien(finalI);
                            resetBackground(layout20kgBack,layout30kgBack,layout40kgBack,layout50kgBack,layout60kgBack,layout70kgBack);
                            layout70kgBack.setBackgroundResource(R.color.blue);
                            TienTungNguoi[finalI] += 700000;
                            TongTien += TienTungNguoi[finalI];
                            textViewTongTien.setText(String.valueOf( TongTien));
                            AppUtil.KG[finalI] = "70kg";
                            l[finalI] = 7;
                            Log.e("TienTungNguoi[" + finalI + "]", "TienTungNguoi: " + TienTungNguoi[finalI]);
                            AppUtil.HLKGTungNguoiChieuVe[finalI - AppUtil.SLVe] = "7";
                        }
                        AppUtil.OriginalPrice = String.valueOf(TongTien);
                    }
                });

            }


        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int il = 0; il < AppUtil.SLVe; il++) {
                    int price = AppUtil.OriginalPriceDetailChieuDiTungNguoi[il] + TienTungNguoi[il];
                    AppUtil.OriginalPriceDetailChieuDiTungNguoi[il] = price;
                }

                if (AppUtil.KhuHoi == 1 ) {
                    for (int i = 0; i < AppUtil.SLVe; i++) {
                        int price = TienTungNguoi[i + AppUtil.SLVe]  + AppUtil.OriginalPriceDetailChieuVeTungNguoi[i] ;
                        AppUtil.OriginalPriceDetailChieuVeTungNguoi[i] = price;
                    }
                }
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(HanhLyKyGui.this, MuaThemDichVu.class);
                startActivity(myintent);
                Log.d("adas", String.valueOf(AppUtil.OriginalPriceDetailChieuDiTungNguoi[0]));
                Log.d("adas2", String.valueOf(TienTungNguoi[0]));
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(HanhLyKyGui.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });


    }


    private void setBeforeLayout(LinearLayout a, LinearLayout b,LinearLayout c,LinearLayout x,LinearLayout y,LinearLayout z, int i){
        if (AppUtil.KG[i].equals("20kg")) {
            a.setBackgroundResource(R.color.blue);
            l[i] = 2;
        }
        else if (AppUtil.KG[i].equals("30kg")){
            b.setBackgroundResource(R.color.blue);
            l[i] = 3;
        }
        else if (AppUtil.KG[i].equals("40kg")){
            c.setBackgroundResource(R.color.blue);
            l[i] = 4;
        }
        else if (AppUtil.KG[i].equals("20kg")){
            x.setBackgroundResource(R.color.blue);
            l[i] = 5;
        }
        else if (AppUtil.KG[i].equals("20kg")){
            y.setBackgroundResource(R.color.blue);
            l[i] = 6;
        }
        else if (AppUtil.KG[i].equals("20kg")){
            z.setBackgroundResource(R.color.blue);
            l[i] = 7;
        }
    }
    public void resetBackground (LinearLayout a,LinearLayout b, LinearLayout c,LinearLayout x,LinearLayout y,LinearLayout z) {
        a.setBackgroundResource(R.color.white);
        b.setBackgroundResource(R.color.white);
        c.setBackgroundResource(R.color.white);
        x.setBackgroundResource(R.color.white);
        y.setBackgroundResource(R.color.white);
        z.setBackgroundResource(R.color.white);
    }

    public void checkPriceGo(int a){
        if ( l[a] == 2) TienTungNguoi[a] -= 200000;
        else if ( l[a] == 3) TienTungNguoi[a] -= 300000;
        else if ( l[a] == 4) TienTungNguoi[a] -= 400000;
        else if ( l[a] == 5) TienTungNguoi[a] -= 500000;
        else if ( l[a] == 6) TienTungNguoi[a] -= 600000;
        else if ( l[a] == 7) TienTungNguoi[a] -= 700000;
    }
    public void checkPriceGoTongTien(int a){
        if ( l[a] == 2) TongTien -= 200000;
        else if ( l[a] == 3) TongTien -= 300000;
        else if ( l[a] == 4) TongTien -= 400000;
        else if ( l[a] == 5) TongTien -= 500000;
        else if ( l[a] == 6) TongTien -= 600000;
        else if ( l[a] == 7) TongTien -= 700000;
    }
//
//    public void checkPriceBack(){
//        if ( j == 2) TongTien -= 200000;
//        else if ( j == 3) TongTien -= 300000;
//        else if ( j == 4) TongTien -= 400000;
//        else if ( j == 5) TongTien -= 500000;
//        else if ( j == 6) TongTien -= 600000;
//        else if ( j == 7) TongTien -= 700000;
//
//
//    }



}