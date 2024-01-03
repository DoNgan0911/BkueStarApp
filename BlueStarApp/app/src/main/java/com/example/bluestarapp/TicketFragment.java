package com.example.bluestarapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketFragment extends Fragment {
    EditText search_ticket;
    ImageView imageView_search;

    private ListView listViewLichSuVe;
    private DatabaseReference databaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketFragment newInstance(String param1, String param2) {
        TicketFragment fragment = new TicketFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        super.onCreate(savedInstanceState);
        search_ticket = view.findViewById(R.id.search_ticket);
        imageView_search = view.findViewById(R.id.imageView_search);

        listViewLichSuVe = view.findViewById(R.id.listViewLichSuVe);

        if (AppUtil.edtSignInEmail != "") {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, danhSachVe);
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

                String searchTicketId = search_ticket.getText().toString();

                try {
                    // Chuyển đổi searchTicketId sang số nguyên
                    int ticketId = Integer.parseInt(searchTicketId);

                    db.collection("TICKET")
                            .whereEqualTo("b_id", String.valueOf(ticketId))
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    ArrayList<String> danhSachVe = new ArrayList<>();
                                    for (DocumentSnapshot ticketDocument : queryDocumentSnapshots.getDocuments()) {
                                        long flyId = 0; // Khởi tạo giá trị mặc định
                                        String flyIdString = ticketDocument.getString("fly_id");
                                        if (flyIdString != null) {
                                            flyId = Long.parseLong(flyIdString);
                                        }
                                        if (flyIdString != null) {
                                            db.collection("FLIGHT")
                                                    .document(flyIdString)
                                                    .get()
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onSuccess(DocumentSnapshot flightDocumentSnapshot) {
                                                            String fromLocation = flightDocumentSnapshot.getString("fromLocation");
                                                            String toLocation = flightDocumentSnapshot.getString("toLocation");
                                                            String departureDay = flightDocumentSnapshot.getString("departureDay");
                                                            if (fromLocation != null && toLocation != null && departureDay != null) {
                                                                String documentId = ticketDocument.getId();
                                                                danhSachVe.add(documentId + " | " + fromLocation + " - " + toLocation + " | " + departureDay);
                                                                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, danhSachVe);
                                                                listViewLichSuVe.setAdapter(adapter);
                                                                Log.d("TraCuuChuyenBay", "Danh sách vé: " + danhSachVe.toString());
                                                            }
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                        }
                                                    });
                                        }
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }


        });

        listViewLichSuVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] parts = selectedItem.split("\\|");
                String maVe = parts[0].trim();

                Intent intent = new Intent(getActivity(), ChiTietVe.class);
                intent.putExtra("selectedItem", maVe);
                startActivity(intent);
            }
        });

        return view;
    }
}