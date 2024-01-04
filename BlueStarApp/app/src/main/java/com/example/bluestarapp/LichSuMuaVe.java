package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluestarapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class LichSuMuaVe extends AppCompatActivity {

    EditText search_ticket;
    ImageView imageView_search;

    private ListView listViewLichSuVe;
    private DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_ve);
        search_ticket = findViewById(R.id.search_ticket);
        imageView_search = findViewById(R.id.imageView_search);

        listViewLichSuVe = findViewById(R.id.listViewLichSuVe);

        if (AppUtil.edtSignInEmail != "") {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("BOOKER")
                    .whereEqualTo("mail", AppUtil.edtSignInEmail)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            // Danh sách vé đã mua


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Xử lý khi có lỗi xảy ra trong quá trình truy vấn dữ liệu từ Firestore
                        }
                    });



            db.collection("TICKET")
                    .whereEqualTo("mail", AppUtil.edtSignInEmail)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            // Danh sách vé đã mua
                            ArrayList<String> danhSachVe = new ArrayList<>();

                            // Duyệt qua các tài liệu (documents) trong kết quả truy vấn
                            for (DocumentSnapshot ticketDocument : queryDocumentSnapshots.getDocuments()) {
                                // Đọc thông tin từ mỗi vé và thêm vào danh sách
                                String documentId = ticketDocument.getId();
                                String noiDi = ticketDocument.getString("fromLocation");
                                String noiDen = ticketDocument.getString("toLocation");
                                String thoiGian = ticketDocument.getString("departureDay");

                                if (noiDi != null && noiDen != null && thoiGian != null) {
                                    danhSachVe.add(documentId + " | " + noiDi + " - " + noiDen + " | " + thoiGian);
                                }
                            }

                            // Hiển thị danh sách vé trong ListView
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(LichSuMuaVe.this, android.R.layout.simple_list_item_1, danhSachVe);
                            listViewLichSuVe.setAdapter(adapter);
                            Log.d("TraCuuChuyenBay", "Danh sách vé: " + danhSachVe.toString());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Xử lý khi có lỗi xảy ra trong quá trình truy vấn dữ liệu từ Firestore
                        }
                    });
        }

        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference ticketCollection = db.collection("TICKET");

                // Get the ticket ID from the search_ticket EditText
                String searchTicketId = search_ticket.getText().toString();

                // Truy vấn document theo ticket ID
                ticketCollection.document(searchTicketId)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    // Document tồn tại, đọc thông tin và hiển thị lên ListView

                                    String noiDi = documentSnapshot.getString("fromLocation");
                                    String noiDen = documentSnapshot.getString("toLocation");
                                    String thoiGian = documentSnapshot.getString("departureDay");

                                    // Kiểm tra thông tin và hiển thị lên ListView
                                    if (noiDi != null && noiDen != null && thoiGian != null) {
                                        ArrayList<String> danhSachVe = new ArrayList<>();
                                        danhSachVe.add(searchTicketId + " | " + noiDi + " - " + noiDen + " | " + thoiGian);

                                        // Hiển thị danh sách vé trong ListView
                                        ArrayAdapter<String> adapter = new ArrayAdapter<>(LichSuMuaVe.this, android.R.layout.simple_list_item_1, danhSachVe);
                                        listViewLichSuVe.setAdapter(adapter);
                                    }
                                } else {
                                    // Document không tồn tại, xử lý tương ứng
                                    Toast.makeText(LichSuMuaVe.this, "Không tìm thấy vé có ID: " + searchTicketId, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xử lý khi có lỗi xảy ra trong quá trình truy vấn dữ liệu từ Firestore
                                Toast.makeText(LichSuMuaVe.this, "Lỗi khi truy vấn dữ liệu từ Firestore", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        listViewLichSuVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy thông tin của item được chọn từ danh sách
                String selectedItem = (String) parent.getItemAtPosition(position);
                String maVe = selectedItem.substring(0, 4);

                // Tại đây, bạn có thể tách thông tin từ chuỗi selectedItem
                // Ví dụ: Sử dụng StringTokenizer để tách thông tin

                // Chuyển sang Activity mới và chuyển theo dữ liệu từ document
                Intent intent = new Intent(LichSuMuaVe.this, ChiTietVe.class);
                intent.putExtra("selectedItem", maVe); // Truyền dữ liệu từ item được chọn
                startActivity(intent);
            }
        });
    }
}

