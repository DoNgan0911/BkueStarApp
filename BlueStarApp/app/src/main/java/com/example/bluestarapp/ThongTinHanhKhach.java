package com.example.bluestarapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.checkerframework.checker.units.qual.C;

public class ThongTinHanhKhach extends AppCompatActivity {
    EditText edtTTLHName, edtTTLHSdt, edtTTLHEmail, edtTTHKNgaySinh;
    TextView fromLocation, toLocation, fromLocationBack, toLocationBack, textViewTongTien,
            textViewNgayDi, textViewThoiGianDi, textViewThoiGianDen, textViewNgayDiBack,
            textViewThoiGianDiBack, textViewThoiGianDenBack, textViewTTHK;
    TextView textViewLoaiVeChieudi, textViewLoaiVeChieudiBack;
    ConstraintLayout layoutChieuVe;
    Button btnNext;
    LinearLayout layout_Parent_TTHK ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_hanh_khach);

        edtTTLHName = findViewById(R.id.edtTTLHName);
        edtTTLHSdt = findViewById(R.id.edtTTLHSdt);
        edtTTLHEmail = findViewById(R.id.edtTTLHEmail);
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
        textViewTTHK = findViewById(R.id.textViewTTHK);
        layout_Parent_TTHK = findViewById(R.id.layout_Parent_TTHK);



        if (AppUtil.KhuHoi == 0) {
            layoutChieuVe.setVisibility(View.GONE);
        } else {
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

            if ("Thương gia".equals(AppUtil.ticketKind)) {
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

        if ("Thương gia".equals(AppUtil.ticketKind)) {
            textViewLoaiVeChieudi.setText("BlueStar (Thương gia)");
        } else {
            textViewLoaiVeChieudi.setText("BlueStar (Thường)");
        }
        int soLuongVe = AppUtil.SLVe;
        for (int i = 0; i < soLuongVe; i++) {
            // Tạo mới layoutTTHK
            LinearLayout childLayoutTTHK = (LinearLayout) getLayoutInflater().inflate(R.layout.parent_layou_ttthk, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 48);

            childLayoutTTHK.setLayoutParams(layoutParams);


            childLayoutTTHK.setId(View.generateViewId());
            TextView textViewTTHK = childLayoutTTHK.findViewById(R.id.textViewTTHK);

            textViewTTHK.setText("Thông tin hành khách thứ "+(i+1));
            layout_Parent_TTHK.addView(childLayoutTTHK);

        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.edtTTLHEmail=edtTTLHEmail.getText().toString();
                capNhatAppUtil();
                Intent myintent = new Intent(ThongTinHanhKhach.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });
    }

    private void capNhatAppUtil() {
        int soLuongVe = AppUtil.SLVe;
        int start = 5;


        for (int i = start; i < soLuongVe + start; i++) {
            LinearLayout childView =(LinearLayout) layout_Parent_TTHK.getChildAt(i);

            if (childView != null) {
                // Lấy ra các thành phần trong layoutTTHK con
                EditText edtTTHKName = childView.findViewById(R.id.edtTTHKName);
                RadioGroup radioGroup = childView.findViewById(R.id.radioGroup);
                RadioButton radioButton1 = childView.findViewById(R.id.radioButtonNam);
                RadioButton radioButton2 = childView.findViewById(R.id.radioButtonNu);
                EditText edtTTHKNgaySinh = childView.findViewById(R.id.edtTTHKNgaySinh);

                // Kiểm tra null trước khi sử dụng
                // Trong capNhatAppUtil()
                if (edtTTHKName != null  && edtTTHKNgaySinh != null) {
                    // Cập nhật thông tin vào AppUtil
                    String updatedName = edtTTHKName.getText().toString();
                    Log.e("Debug", "Updated Name: " + updatedName);
                    AppUtil.edtTTHKName[i - start] = updatedName;

                    if (radioButton1.isChecked()) {
                        AppUtil.GioiTinh[i - start] = "Ông";
                    } else if (radioButton2.isChecked()) {
                        AppUtil.GioiTinh[i - start] = "Bà";
                    }
                    AppUtil.NgaySinhHK[i - start] = edtTTHKNgaySinh.getText().toString();
                } else {
                    Log.e("Debug", "Một hoặc nhiều view là null");
                }

            }
        }
    }

}
