package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThongTinHanhKhach extends AppCompatActivity {
    EditText edtTTLHName, edtTTLHSdt, edtTTLHEmail, edtTTHKName, edtTTHKNgaySinh;
    ConstraintLayout layoutChieuVe;
    TextView fromLocation, toLocation, fromLocationBack, toLocationBack, textViewTongTien, textViewNgayDi, textViewThoiGianDi, textViewThoiGianDen, textViewNgayDiBack, textViewThoiGianDiBack, textViewThoiGianDenBack;
    TextView textViewLoaiVeChieudi, textViewLoaiVeChieudiBack;
    Button btnNext;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_hanh_khach);
        edtTTLHName = findViewById(R.id.edtTTLHName);
        edtTTLHSdt = findViewById(R.id.edtTTLHSdt);
        edtTTLHEmail = findViewById(R.id.edtTTLHEmail);
        edtTTHKName = findViewById(R.id.edtTTHKName);
        edtTTHKNgaySinh = findViewById(R.id.edtTTHKNgaySinh);
        btnNext = findViewById(R.id.btnNext);
        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        textViewTongTien = findViewById(R.id.textViewTongTien);
        textViewNgayDi = findViewById(R.id.textViewNgayDi);
        textViewThoiGianDi = findViewById(R.id.textViewThoiGianDi);
        textViewThoiGianDen = findViewById(R.id.textViewThoiGianDen);
        layoutChieuVe = findViewById(R.id.layoutChieuVe);
        textViewLoaiVeChieudi = findViewById(R.id.textViewLoaiVeChieudi);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);



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
            textViewLoaiVeChieudiBack = findViewById(R.id.textViewLoaiVeChieudiBack);

            fromLocationBack.setText(AppUtil.ToLocation);
            toLocationBack.setText(AppUtil.FromLocation);
            textViewNgayDiBack.setText(AppUtil.backDay);
            textViewThoiGianDiBack.setText(AppUtil.departueTimeBack);
            textViewThoiGianDenBack.setText(AppUtil.arrivalTimeBack);
            if ("Thương gia".equals(AppUtil.ticketKind)) { // Sử dụng phương thức equals để so sánh chuỗi
                textViewLoaiVeChieudiBack.setText("BlueStar (Thương gia)");
            } else {
                textViewLoaiVeChieudiBack.setText("BlueStar (Thường)");
            }
        }


        fromLocation.setText(AppUtil.FromLocation);
        toLocation.setText(AppUtil.ToLocation);
        textViewTongTien.setText(AppUtil.OriginalPrice);
        textViewNgayDi.setText(AppUtil.departureDay);
        textViewThoiGianDi.setText(AppUtil.departueTime);
        textViewThoiGianDen.setText(AppUtil.arrivalTime);
        if ("Thương gia".equals(AppUtil.ticketKind)) { // Sử dụng phương thức equals để so sánh chuỗi
            textViewLoaiVeChieudi.setText("BlueStar (Thương gia)");
        } else {
            textViewLoaiVeChieudi.setText("BlueStar (Thường)");
        }



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.edtTTHKName = edtTTHKName.getText().toString();
                AppUtil.edtTTLHEmail=edtTTLHEmail.getText().toString();
                if (radioButton1.isSelected()) {
                    AppUtil.GioiTinh = "Ông";
                }
                else if (radioButton2.isSelected()) {
                    AppUtil.GioiTinh = "Bà";
                }
                AppUtil.NgaySinhHK = edtTTHKNgaySinh.getText().toString();
                Intent myintent = new Intent(ThongTinHanhKhach.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });

    }
}