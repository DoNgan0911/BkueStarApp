package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    private TextView textViewCalendarDepart;
    private TextView textViewCalendarBack;
    private RecyclerView recyclerView;
    private AutoCompleteTextView textViewDepart, textViewArrive;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMotChieu, radioButtonKhuHoi;
    private Button buttonSearch;

    int index = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        textViewCalendarDepart = findViewById(R.id.textViewCalendarDepart);
        textViewCalendarBack = findViewById(R.id.textViewCalendarBack);
        textViewArrive = findViewById(R.id.textViewArrive);
        textViewDepart = findViewById(R.id.textViewDepart);
        buttonSearch = findViewById(R.id.buttonSearch);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup = findViewById(R.id.radioGroup);
        recyclerView = findViewById(R.id.recycleView);
        radioButtonMotChieu = findViewById(R.id.radioButtonMotChieu);
        radioButtonKhuHoi = findViewById(R.id.radioButtonKhuHoi);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        if (recyclerView != null) {
            recyclerView.addItemDecoration(dividerItemDecoration);
        }

        textViewCalendarBack.setVisibility(View.GONE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonKhuHoi) {
                    // Khi Radiobutton "Khứ hồi" được chọn
                    textViewCalendarBack.setVisibility(View.VISIBLE); // Hiển thị ngày về
                } else {
                    // Khi Radiobutton "Một chiều" được chọn
                    textViewCalendarBack.setVisibility(View.GONE); // Ẩn ngày về
                }
            }
        });

        textViewCalendarDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                ChonNgay();
            }
        });
        textViewCalendarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = -1;
                ChonNgay();
            }
        });

        textViewDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(BookingActivity.this, FromLocation.class);
                startActivity(myintent);
            }
        });
        textViewDepart.setText(AppUtil.FromLocation);

        textViewArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(BookingActivity.this, ToLocation.class);
                startActivity(myintent);
            }
        });

        textViewArrive.setText(AppUtil.ToLocation);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLocation = textViewDepart.getText().toString();
                String toLocation = textViewArrive.getText().toString();

                Intent intent = new Intent(BookingActivity.this, ResultFlight.class);
                Bundle bundle = new Bundle();
                bundle.putString("fromLocation", fromLocation);
                bundle.putString("toLocation", toLocation);

                intent.putExtra("mypackage", bundle);

                // Khởi chạy ResultFlightActivity với Intent và Bundle
                startActivity(intent);

            }
        });



    }
    public void ChonNgay(){
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet (DatePicker datePicker,int i, int i1, int i2){
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if (index == 1) {
                    textViewCalendarDepart.setText(simpleDateFormat.format(calendar.getTime()));
                } else {
                    textViewCalendarBack.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }

        }, nam,thang,ngay);
        datePickerDialog.show();
    }



}