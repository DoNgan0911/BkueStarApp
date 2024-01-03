package com.example.bluestarapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final int FROM_LOCATION_REQUEST_CODE = 1;
    private static final int TO_LOCATION_REQUEST_CODE = 2;


    private TextView textViewCalendarDepart;
    private TextView textViewCalendarBack;
    private TextView textViewKind, textViewNum;
    private ImageView imageViewSwap, imageViewUpSLVe, imageViewDownSLVe;
    private RecyclerView recyclerView;
    private AutoCompleteTextView textViewDepart, textViewArrive;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMotChieu, radioButtonKhuHoi;
    private Button buttonSearch;

    private TextView textView18;

    private ImageView imageViewChatBox;

    private int index = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
            View view = inflater.inflate(R.layout.fragment_home, container, false);

            textViewCalendarDepart = view.findViewById(R.id.textViewCalendarDepart);
            textViewCalendarBack = view.findViewById(R.id.textViewCalendarBack);
            textViewArrive = view.findViewById(R.id.textViewArrive);
            textViewDepart = view.findViewById(R.id.textViewDepart);
            buttonSearch = view.findViewById(R.id.buttonSearch);
            radioGroup = view.findViewById(R.id.radioGroup);
            recyclerView = view.findViewById(R.id.recycleView);
            radioButtonMotChieu = view.findViewById(R.id.radioButtonMotChieu);
            radioButtonKhuHoi = view.findViewById(R.id.radioButtonKhuHoi);
            textViewKind = view.findViewById(R.id.textViewKind);
            imageViewSwap = view.findViewById(R.id.imageViewSwap);
            imageViewDownSLVe = view.findViewById(R.id.imageViewDownSLVe);
            imageViewUpSLVe = view.findViewById(R.id.imageViewUpSLVe);
            textViewNum = view.findViewById(R.id.textViewNum);

            textView18 = view.findViewById(R.id.textView18);

            imageViewChatBox = view.findViewById(R.id.chatbox);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
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

            // Thiết lập sự kiện cho các thành phần UI
            setupListeners();

            return view;
        }

    private void setupListeners() {
        // Xử lý sự kiện khi chọn ngày đi
        textViewCalendarDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                ChonNgay();
            }
        });

        // Xử lý sự kiện khi chọn ngày về
        textViewCalendarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = -1;
                ChonNgay();
            }
        });

        // Xử lý sự kiện khi click chọn điểm đi
        textViewDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FromLocation.class);
//                startActivity(intent);
                startActivityForResult(intent, FROM_LOCATION_REQUEST_CODE);

            }
        });
        textViewDepart.setText(AppUtil.FromLocation);

        // Xử lý sự kiện khi click chọn điểm đến
        textViewArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ToLocation.class);
                startActivityForResult(intent, TO_LOCATION_REQUEST_CODE);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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


        // Xử lý sự kiện khi nhấn nút tìm kiếm
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLocation = textViewDepart.getText().toString();
                String toLocation = textViewArrive.getText().toString();
                String departureDay = textViewCalendarDepart.getText().toString();
                String backDay = textViewCalendarBack.getText().toString();

                String slVe = textViewNum.getText().toString();
                int SLVe = Integer.parseInt(slVe);
                AppUtil.capNhatKichThuocGheDaChon(SLVe);
                AppUtil.capNhatKichThuocGioiTinh(SLVe);
                AppUtil.capNhatKichThuocTTHKName(SLVe);
                AppUtil.capNhatKichThuocNgaySinhHK(SLVe);
                AppUtil.capNhatKichThuocKG(SLVe);
                AppUtil.capNhatKichThuocSLMiY(SLVe);
                AppUtil.capNhatKichThuocSLBanhMi(SLVe);
                AppUtil.capNhatKichThuocSLComTam(SLVe);
                AppUtil.capNhatKichThuocSLComChienChay(SLVe);
                AppUtil.capNhatKichThuocCCCDHK(SLVe);
                AppUtil.capNhatKichThuocOriginalPriceDetailChieuDiTungNguoi(SLVe);
                AppUtil.capNhatKichThuocOriginalPriceDetailChieuVeTungNguoi(SLVe);
                AppUtil.capNhatKichThuocFoodTungNguoiChieuDi(SLVe);
                AppUtil.capNhatKichThuocHLKGTungNguoiChieuDi(SLVe);
                AppUtil.capNhatKichThuocHLKGTungNguoiChieuVe(SLVe);
                AppUtil.capNhatKichThuocFoodTungNguoiChieuVe(SLVe);
                AppUtil.departureDay = textViewCalendarDepart.getText().toString();
                AppUtil.backDay = textViewCalendarBack.getText().toString();
                AppUtil.ticketKind = textViewKind.getText().toString();
                AppUtil.SLVe = SLVe;


                Intent intent = new Intent(getActivity(), ResultFlight.class);
                Bundle bundle = new Bundle();
                bundle.putString("fromLocation", fromLocation);
                bundle.putString("toLocation", toLocation);
                bundle.putString("departureDay", departureDay);
                bundle.putString("backDay", backDay);

                intent.putExtra("mypackage", bundle);

                if (AppUtil.KhuHoi == 1) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                    try {
                        Date departureDate = dateFormat.parse(AppUtil.departureDay);
                        Date returnDate = dateFormat.parse(AppUtil.backDay);

                        if (departureDate != null && returnDate != null && departureDate.after(returnDate)) {
                            // Departure date is after return date
                            Toast.makeText(getActivity(), "Ngày đi không được lớn hơn ngày về", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(intent);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Khởi chạy ResultFlightActivity với Intent và Bundle
                    startActivity(intent);
                }




            }
        });

        // Các sự kiện khác cũng được xử lý tương tự
        // ...
        imageViewChatBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), TrangCaNhan.class);
                Intent intent = new Intent(getActivity(), Chat.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }
        });


    }
    private void ChonNgay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                if (selectedDate.after(Calendar.getInstance())) {
                    // Ngày được chọn là ngày trong tương lai
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    if (index == 1) {
                        textViewCalendarDepart.setText(simpleDateFormat.format(selectedDate.getTime()));
                    } else {
                        textViewCalendarBack.setText(simpleDateFormat.format(selectedDate.getTime()));
                    }
                } else {
                    // Ngày được chọn là ngày trong quá khứ
                    Toast.makeText(getActivity(), "Vui lòng chọn ngày trong tương lai", Toast.LENGTH_SHORT).show();
                }
            }
        }, year, month, day);

        // Set giới hạn cho DatePickerDialog không cho chọn ngày trong quá khứ
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FROM_LOCATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String selectedAirport = data.getStringExtra("selectedAirport");
            textViewDepart.setText(selectedAirport);

        }
        else if (requestCode == TO_LOCATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String selectedAirport = data.getStringExtra("selectedAirportt");
            textViewArrive.setText(selectedAirport);
        }
    }

}