package com.example.bluestarapp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchFlightActivity extends AppCompatActivity {
    private TextView textViewCalendarDepart;
    private TextView textViewCalendarBack;
    private TextView textViewKind, textViewNum;
    private ImageView imageViewSwap, imageViewUpSLVe, imageViewDownSLVe;
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
        textViewKind = findViewById(R.id.textViewKind);
        imageViewSwap = findViewById(R.id.imageViewSwap);
        imageViewDownSLVe = findViewById(R.id.imageViewDownSLVe);
        imageViewUpSLVe = findViewById(R.id.imageViewUpSLVe);
        textViewNum = findViewById(R.id.textViewNum);


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
                    AppUtil.KhuHoi = 1;
                    // Khi Radiobutton "Khứ hồi" được chọn
                    textViewCalendarBack.setVisibility(View.VISIBLE); // Hiển thị ngày về
                } else {
                    AppUtil.KhuHoi = 0;
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
                Intent myintent = new Intent(SearchFlightActivity.this, FromLocation.class);
                startActivity(myintent);
            }
        });
        textViewDepart.setText(AppUtil.FromLocation);

        textViewArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(SearchFlightActivity.this, ToLocation.class);
                startActivity(myintent);
            }
        });

        textViewArrive.setText(AppUtil.ToLocation);

        imageViewSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDepart.setText(AppUtil.ToLocation);
                textViewArrive.setText(AppUtil.FromLocation);

                AppUtil.FromLocation = textViewDepart.getText().toString();
                AppUtil.ToLocation = textViewArrive.getText().toString();
                AppUtil.departureDay = textViewCalendarDepart.getText().toString();
                AppUtil.backDay = textViewCalendarBack.getText().toString();

            }
        });

        textViewKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchFlightActivity.this);
                builder.setTitle("Chọn loại vé");

                // Danh sách các lựa chọn
                String[] kinds = {"Thường", "Thương gia"};

                builder.setItems(kinds, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi lựa chọn được chọn
                        String selectedKind = kinds[which];

                        // Ứng với mỗi lựa chọn, bạn có thể thực hiện các hành động cụ thể ở đây
                        if (selectedKind.equals("Thường")) {
                            textViewKind.setText("Thường");
                        } else if (selectedKind.equals("Thương gia")) {
                            textViewKind.setText("Thương gia");
                        }
                    }
                });

                // Tạo và hiển thị Dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        imageViewUpSLVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slVe = textViewNum.getText().toString();
                int SLVe = Integer.parseInt(slVe);
                SLVe++;
                textViewNum.setText(String.valueOf(SLVe));
            }
        });

        imageViewDownSLVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slVe = textViewNum.getText().toString();
                int SLVe = Integer.parseInt(slVe);
                if (SLVe == 1) {
                    SLVe = 1;
                    textViewNum.setText(String.valueOf(SLVe));
                }
                else {
                    SLVe--;
                    textViewNum.setText(String.valueOf(SLVe));
                }
            }
        });



        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLocation = textViewDepart.getText().toString();
                String toLocation = textViewArrive.getText().toString();
                String departureDay = textViewCalendarDepart.getText().toString();
                String backDay = textViewCalendarBack.getText().toString();

                String slVe = textViewNum.getText().toString();
                int SLVe = Integer.parseInt(slVe);

                AppUtil.departureDay = textViewCalendarDepart.getText().toString();
                AppUtil.backDay = textViewCalendarBack.getText().toString();
                AppUtil.ticketKind = textViewKind.getText().toString();
                AppUtil.SLVe = SLVe;

                Intent intent = new Intent(SearchFlightActivity.this, ResultFlight.class);
                Bundle bundle = new Bundle();
                bundle.putString("fromLocation", fromLocation);
                bundle.putString("toLocation", toLocation);
                bundle.putString("departureDay", departureDay);
                bundle.putString("backDay", backDay);

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