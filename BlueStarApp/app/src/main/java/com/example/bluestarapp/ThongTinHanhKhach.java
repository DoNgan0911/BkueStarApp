package com.example.bluestarapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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


                if (kiemTraTrong()) {
                    if(edtTTLHSdt.getText().toString().length() != 10){
                        Toast.makeText(getApplicationContext(), "Số điện thoại phải có ít nhất 10 kí tự!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!edtTTLHEmail.getText().toString().endsWith("@gmail.com")){
                        Toast.makeText(getApplicationContext(), "Mail không hợp lệ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(edtTTLHSdt.length() != 10 ){
                        Toast.makeText(getApplicationContext(), "Số điện thoại phải có ít nhất 10 kí tự!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Nếu không có trường nào trống, thì tiến hành chuyển màn hình
                    AppUtil.edtTTLHEmail = edtTTLHEmail.getText().toString();
                    AppUtil.edtTTLHName = edtTTLHName.getText().toString();
                    AppUtil.edtTTLHSdt = edtTTLHSdt.getText().toString();

                    capNhatAppUtil();

                    Intent myintent = new Intent(ThongTinHanhKhach.this, MuaThemDichVu.class);
                    startActivity(myintent);
                    finish();
                } else {
                    // Nếu có trường nào đó trống, hiển thị thông báo hoặc thực hiện hành động phù hợp
                    // Ví dụ: Toast.makeText(ThongTinHanhKhach.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("Debug", "Vui lòng nhập đầy đủ thông tin");
                }
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
                EditText edtTTHKCCCD= childView.findViewById(R.id.edtTTHKCCCD);


                // Kiểm tra null trước khi sử dụng
                // Trong capNhatAppUtil()
                if (edtTTHKName != null  && edtTTHKNgaySinh != null && edtTTHKCCCD !=null) {

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

                    AppUtil.CCCDHK[i - start] = edtTTHKCCCD.getText().toString();

                } else {
                    Log.e("Debug", "Một hoặc nhiều view là null");
                }

            }
        }
    }

    private boolean kiemTraTrong() {
        if (edtTTLHEmail.getText().toString().isEmpty()) {
            showToast("Vui lòng nhập email");
            return false;
        } else if (!isValidEmail(edtTTLHEmail.getText().toString())) {
            showToast("Vui lòng nhập địa chỉ email hợp lệ");
            return false;
        } else if (edtTTLHName.getText().toString().isEmpty()) {
            showToast("Vui lòng nhập tên");
            return false;
        } else if (edtTTLHSdt.getText().toString().isEmpty()) {
            showToast("Vui lòng nhập số điện thoại");
            return false;
        } else if (!kiemTraTrongChildViews()) {
            // Thêm kiểm tra cho các trường thông tin hành khách con nếu cần
            return false;
        }

        return true;
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    // Phương thức hiển thị Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    // Phương thức kiểm tra các trường thông tin hành khách con (nếu có)
    private boolean kiemTraTrongChildViews() {
        int soLuongVe = AppUtil.SLVe;
        int start = 5;

        for (int i = start; i < soLuongVe + start; i++) {
            LinearLayout childView = (LinearLayout) layout_Parent_TTHK.getChildAt(i);

            if (childView != null) {
                EditText edtTTHKName = childView.findViewById(R.id.edtTTHKName);
                RadioGroup radioGroup = childView.findViewById(R.id.radioGroup);
                RadioButton radioButton1 = childView.findViewById(R.id.radioButtonNam);
                RadioButton radioButton2 = childView.findViewById(R.id.radioButtonNu);
                EditText edtTTHKNgaySinh = childView.findViewById(R.id.edtTTHKNgaySinh);
                EditText edtTTHKCCCD = childView.findViewById(R.id.edtTTHKCCCD);

                // Kiểm tra null và trống trước khi sử dụng
                if (edtTTHKName != null && radioGroup != null &&
                        edtTTHKNgaySinh != null && edtTTHKCCCD != null &&
                        !edtTTHKName.getText().toString().isEmpty() &&
                        (radioButton1.isChecked() || radioButton2.isChecked()) &&
                        !edtTTHKNgaySinh.getText().toString().isEmpty() &&
                        !edtTTHKCCCD.getText().toString().isEmpty()) {
                    continue;  // Nếu không có trường nào trống, tiếp tục vòng lặp
                } else {
                    return false;  // Nếu có trường nào đó trống, trả về false
                }
            }
        }
        return true;  // Nếu không có lỗi, trả về true
    }

}
