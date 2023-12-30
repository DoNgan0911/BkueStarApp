package com.example.bluestarapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LichSuMuaVe extends AppCompatActivity {

    private ListView listViewLichSuVe;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_ve);

        listViewLichSuVe = findViewById(R.id.listViewLichSuVe);

        // Lấy thông tin người dùng đăng nhập
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("Ticket").child(userId);

            // Lắng nghe sự kiện khi dữ liệu thay đổi trong Firebase
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Danh sách vé đã mua
                    ArrayList<String> danhSachVe = new ArrayList<>();

                    for (DataSnapshot ticketSnapshot : dataSnapshot.getChildren()) {
                        // Đọc thông tin từ mỗi vé và thêm vào danh sách
                        String noiDi = ticketSnapshot.child("fromLocation").getValue(String.class);
                        String noiDen = ticketSnapshot.child("toLocation").getValue(String.class);
                        String thoiGian = ticketSnapshot.child("departureDay").getValue(String.class);

                        if (noiDi != null && noiDen != null && thoiGian != null) {
                            danhSachVe.add(noiDi + " - " + noiDen + " | " + thoiGian);
                        }
                    }

                    // Hiển thị danh sách vé trong ListView
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(LichSuMuaVe.this, android.R.layout.simple_list_item_1, danhSachVe);
                    listViewLichSuVe.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(LichSuMuaVe.this, "Không thể đọc dữ liệu từ Firebase.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
