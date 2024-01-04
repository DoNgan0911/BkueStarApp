package com.example.bluestarapp;

//import android.content.Intent;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import vn.momo.momo_partner.AppMoMoLib;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;





public class ticket_information extends AppCompatActivity {
        Button thanhtoan;
        LinearLayout layoutMain;
    int iddonhang;
    private String amount = "10000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "ĐỖ THỊ BÍCH NGÂN";
    private String merchantCode = "MOMOTI7220231126";
    private String merchantNameLabel = "ĐỖ THỊ BÍCH NGÂN";
    private String description = "Thanh toán đặt vé máy bay BlueStar";
    String ticketNumber = "";
    int mabooker ;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ticket_information);
            layoutMain = findViewById(R.id.layoutMain);
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
            thanhtoan = findViewById(R.id.thanhtoan);
            // Get the Flight object from the intent
            thanhtoan.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Map<String, Object> bookerMap = new HashMap<>();
                                                 bookerMap.put("mail", AppUtil.edtTTLHEmail);
                                                 bookerMap.put("name", AppUtil.edtTTLHName);
                                                 bookerMap.put("phone", AppUtil.edtTTLHSdt);
                                                 bookerMap.put("total_price", AppUtil.OriginalPrice);
                                                 bookerMap.put("momo", "");
                                                 FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                                 FirebaseUser currentUser = mAuth.getCurrentUser();
                                                 if (currentUser != null) {
                                                     String userId = currentUser.getUid();
                                                     db.collection("CUSTOMER")
                                                             .whereEqualTo("account_id", userId)
                                                             .get()
                                                             .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                 @Override
                                                                 public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                     if (task.isSuccessful()) {
                                                                         for (QueryDocumentSnapshot document : task.getResult()) {
                                                                             // Lấy userId từ document của CUSTOMER
                                                                             String customerId = document.getId();
                                                                             bookerMap.put("c_id", customerId);

                                                                         }
                                                                     } else {
//                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                                                     }
                                                                 }
                                                             });
                                                 } else {
                                                     // Người dùng chưa đăng nhập
                                                     bookerMap.put("c_id", "");

                                                 }

                                                 CollectionReference ticketcollection = db.collection("BOOKER");
                                                 ticketcollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                     @Override
                                                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                         if (task.isSuccessful()) {
                                                             int documentCount = task.getResult().size();
                                                             // Set the next available documentId
                                                             String documentId = String.valueOf(documentCount + 1);
                                                             AppUtil.idVe = documentId;
                                                             // Rest of your code with the dynamically set documentId
                                                             db.collection("BOOKER")
                                                                     .document(documentId)
                                                                     .set(bookerMap)
                                                                     .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                         @Override
                                                                         public void onSuccess(Void aVoid) {

                                                                             mabooker = Integer.parseInt(documentId);
                                                                             requestPayment(mabooker);

                                                                         }
                                                                     })
                                                                     .addOnFailureListener(new OnFailureListener() {
                                                                         @Override
                                                                         public void onFailure(@NonNull Exception e) {
                                                                             Log.e("SignUpActivity", "Error adding user data to CUSTOMER collection: " + e.getMessage());
                                                                         }
                                                                     });
                                                         } else {

                                                         }
                                                     }
                                                 });

                                             }
                                         }
            );

            for (int i = 0; i < AppUtil.SLVe; i++) {
                // Tạo mới layoutTTHK
                LinearLayout childLayoutTTHK = (LinearLayout) getLayoutInflater().inflate(R.layout.ticket_info_chieudi, null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 0, 0, 48);

                childLayoutTTHK.setLayoutParams(layoutParams);


                childLayoutTTHK.setId(View.generateViewId());
                TextView ticketKind = childLayoutTTHK.findViewById(R.id.ticketKind);
                TextView seat = childLayoutTTHK.findViewById(R.id.seat);
                TextView departureDay = childLayoutTTHK.findViewById(R.id.departureDay);
                TextView toLocation = childLayoutTTHK.findViewById(R.id.toLocation);
                TextView idticket = childLayoutTTHK.findViewById(R.id.idticket);
                TextView nameofpassenger = childLayoutTTHK.findViewById(R.id.nameofpassenger);
                TextView departureTime = childLayoutTTHK.findViewById(R.id.departureTime);
                TextView fromLocation = childLayoutTTHK.findViewById(R.id.fromLocation);

                ticketKind.setText(AppUtil.ticketKind);
                seat.setText(AppUtil.GheDaChon[i]);
                departureTime.setText(AppUtil.departueTime);
                departureDay.setText(AppUtil.departureDay);
                toLocation.setText(AppUtil.ToLocation);
                fromLocation.setText(AppUtil.FromLocation);
                nameofpassenger.setText(AppUtil.edtTTHKName[i]);
                idticket.setText(AppUtil.idVe);
                Log.d("TicketNumber", "Value: " + AppUtil.idVe);


                layoutMain.addView(childLayoutTTHK);

            }


            if (AppUtil.KhuHoi == 1) {

                for (int i = AppUtil.SLVe; i < AppUtil.SLVe * 2; i++) {
                    // Tạo mới layoutTTHK
                    LinearLayout childLayoutTTHK = (LinearLayout) getLayoutInflater().inflate(R.layout.ticket_info_chieuve, null);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(0, 0, 0, 48);

                    childLayoutTTHK.setLayoutParams(layoutParams);

                    childLayoutTTHK.setId(View.generateViewId());
                    TextView ticketKind = childLayoutTTHK.findViewById(R.id.ticketKind);
                    TextView seat = childLayoutTTHK.findViewById(R.id.seat);
                    TextView departureDay = childLayoutTTHK.findViewById(R.id.departureDay);
                    TextView toLocation = childLayoutTTHK.findViewById(R.id.toLocation);
                    TextView idticket = childLayoutTTHK.findViewById(R.id.idticket);
                    TextView nameofpassenger = childLayoutTTHK.findViewById(R.id.nameofpassenger);
                    TextView departureTime = childLayoutTTHK.findViewById(R.id.departureTime);
                    TextView fromLocation = childLayoutTTHK.findViewById(R.id.fromLocation);

                    ticketKind.setText(AppUtil.ticketKind);
                    seat.setText(AppUtil.GheDaChon[i - AppUtil.SLVe]);
                    departureTime.setText(AppUtil.departueTimeBack);
                    departureDay.setText(AppUtil.backDay);
                    toLocation.setText(AppUtil.FromLocation);
                    fromLocation.setText(AppUtil.ToLocation);
                    nameofpassenger.setText(AppUtil.edtTTHKName[i - AppUtil.SLVe]);
                    idticket.setText(AppUtil.idVe);

                    layoutMain.addView(childLayoutTTHK);
                }
            }
        }



    private void requestPayment(int iddonhang) {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);

        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("amount", AppUtil.OriginalPrice); //Kiểu integer
        eventValue.put("orderId", iddonhang); // ID CỦA BẢNG BOOKER
        eventValue.put("orderLabel", iddonhang); //gán nhãn

        //client Optional - bill info
        eventValue.put("merchantnamelabel", "Dịch vụ");//gán nhãn
        eventValue.put("fee", "0"); //Kiểu integer
        eventValue.put("description", description); //mô tả đơn hàng - short description

        //client extra data
        eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
        eventValue.put("partnerCode", merchantCode);
        //Example extra data
        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "008");
            objExtraData.put("site_name", "CGV Cresent Mall");
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
            objExtraData.put("movie_format", "2D");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put("extraData", objExtraData.toString());

        eventValue.put("extra", "");
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                if (data.getIntExtra("status", -1) == 0) {
                    //TOKEN IS AVAILABLE
                    Log.d("thanhcong", data.getStringExtra("message"));
                    String token = data.getStringExtra("data"); //Token response
//                    update MÃ MOMO LÀ TOKEN NÈ
                        DocumentReference ticketRef = db.collection("BOOKER").document(String.valueOf(mabooker));
                        ticketRef.update("momo", token)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        CollectionReference ticketCollection = db.collection("TICKET");
                                        ticketCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    int documentCount = task.getResult().size();
                                                    addTickets(documentCount, String.valueOf(mabooker));
                                                    if (AppUtil.KhuHoi == 1) {
                                                        documentCount += AppUtil.SLVe;
                                                        addTicketsKH(documentCount, String.valueOf(mabooker));
                                                    }
                                                }
                                                sendmail();
                                            }
                                        });
                                        Log.d("UpdateToken", "Token updated successfully!");
//
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("UpdateToken", "Error updating token", e);
                                    }
                                });

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        String phoneNumber = data.getStringExtra("phonenumber");
                        String env = data.getStringExtra("env");
                        if (env == null) {
                            env = "app";
                        }

                        if (token != null && !token.equals("")) {
                            // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                            // IF Momo topup success, continue to process your order
                        } else {

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                            Log.d("thành công", "không thành cônggggggggggg1");
                        }
                    } else if (data.getIntExtra("status", -1) == 1) {

                        //TOKEN FAIL
                        String message = data.getStringExtra("message") != null ? data.getStringExtra("message") : "Thất bại";
                    Log.d("thành công", "không thành cônggggggggggg2");

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    } else if (data.getIntExtra("status", -1) == 2) {

                        //TOKEN FAIL
                    Log.d("thành công", "không thành cônggggggggggg3");

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    } else {
                    sendPaymentFailureEmail();
                        //TOKEN FAIL
//                    CHÈN MAIL THÔNG BÁO THẤT BẠI

//                    XÓA BOOKER
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = db.collection("BOOKER").document(String.valueOf(mabooker));
                    documentReference.delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("DeleteDocument", "DocumentSnapshot successfully deleted!");
                                    // Handle any other operations after successfully deleting the document
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("DeleteDocument", "Error deleting document", e);
                                    // Handle any errors that occur while trying to delete the document
                                }
                            });
                    Log.d("thành công", "không thành cônggggggggggg4");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    }
                } else {
                Log.d("thành công", "không thành cônggggggggggg5");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                }
            } else {
            Log.d("thành công", "không thành cônggggggggggg6");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            }
        }



    private void addTickets(int startingDocumentId, String bid) {


        FirebaseFirestore dbflight = FirebaseFirestore.getInstance();
        CollectionReference flightsCollection = dbflight.collection("FLIGHT");
        CollectionReference customersCollection = dbflight.collection("CUSTOMER");

        customersCollection
                .whereEqualTo("email", AppUtil.edtTTLHEmail)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documents) {
                        // Kiểm tra xem có bản ghi nào trùng khớp không
                        if (!documents.isEmpty()) {
                            // Nếu có, tăng điểm lên 1
                            Long currentPoints = (Long) documents.getDocuments().get(0).get("point");
                            Long newPoints = currentPoints + 1;

                            // Cập nhật giá trị điểm mới vào Firestore
                            String documentId = documents.getDocuments().get(0).getId();
                            customersCollection.document(documentId)
                                    .update("point", newPoints)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Cập nhật thành công
                                            System.out.println("Điểm đã được cập nhật thành công.");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Xử lý khi cập nhật thất bại
                                            System.out.println("Lỗi khi cập nhật điểm: " + e.toString());
                                        }
                                    });
                        } else {
                            // Nếu không có bản ghi nào trùng khớp, bạn có thể xử lý theo ý của mình
                            System.out.println("Không tìm thấy bản ghi có email tương ứng.");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi truy vấn thất bại
                        System.out.println("Lỗi khi truy vấn Firestore: " + e.toString());
                    }
                });

        flightsCollection
                .whereEqualTo("fromLocation", AppUtil.FromLocation)
                .whereEqualTo("toLocation", AppUtil.ToLocation)
                .whereEqualTo("departureDay", AppUtil.departureDay)
                .whereEqualTo("departureTime", AppUtil.departueTime)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Lấy Document ID
                            String flId = document.getId();

                            for (int i = 0; i < AppUtil.SLVe; i++) {
                                int priceDetail = Integer.parseInt(String.valueOf(AppUtil.OriginalPriceDetailChieuDi)) + Integer.parseInt(String.valueOf(AppUtil.OriginalPriceDetailChieuDiTungNguoi[i]));
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("b_id", bid);
                                userMap.put("birthday", AppUtil.NgaySinhHK[i]);
                                userMap.put("ccid", AppUtil.CCCDHK[i]);
                                userMap.put("fly_id", flId);
                                userMap.put("kg_id", AppUtil.HLKGTungNguoiChieuDi[i]);
                                userMap.put("name", AppUtil.edtTTHKName[i]);
                                userMap.put("seat_id", AppUtil.GheDaChon[i]);
                                userMap.put("ticket_kind", AppUtil.ticketKind);
                                userMap.put("ticket_price", priceDetail);


                                // Increment the document ID for each new document
                                int documentId = startingDocumentId + i + 1;

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                CollectionReference ticketCollection = db.collection("TICKET");
                                // Use the incremented document ID for the document reference
                                DocumentReference documentReference = ticketCollection.document(String.valueOf(documentId));

                                // Set the data for the document
                                documentReference.set(userMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ticket_information.this, "User data added to TICKET collection.", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e("ticket_infomation", "Error adding user data to TICKET collection: " + e.getMessage());
                                                Toast.makeText(ticket_information.this, "Error adding user data to TICKET collection.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }

                        }
                    } else {
                        // Xử lý khi truy vấn không thành công
                        Exception exception = task.getException();
                        if (exception != null) {
                            exception.printStackTrace();
                        }
                    }
                });


    }

    private void addTicketsKH(int startingDocumentId, String bid) {
        FirebaseFirestore dbflight = FirebaseFirestore.getInstance();
        CollectionReference flightsCollection = dbflight.collection("FLIGHT");
        CollectionReference customersCollection = dbflight.collection("CUSTOMER");

        customersCollection
                .whereEqualTo("email", AppUtil.edtTTLHEmail)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documents) {
                        // Kiểm tra xem có bản ghi nào trùng khớp không
                        if (!documents.isEmpty()) {
                            // Nếu có, tăng điểm lên 1
                            Long currentPoints = (Long) documents.getDocuments().get(0).get("point");
                            Long newPoints = currentPoints + 1;

                            // Cập nhật giá trị điểm mới vào Firestore
                            String documentId = documents.getDocuments().get(0).getId();
                            customersCollection.document(documentId)
                                    .update("point", newPoints)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Cập nhật thành công
                                            System.out.println("Điểm đã được cập nhật thành công.");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Xử lý khi cập nhật thất bại
                                            System.out.println("Lỗi khi cập nhật điểm: " + e.toString());
                                        }
                                    });
                        } else {
                            // Nếu không có bản ghi nào trùng khớp, bạn có thể xử lý theo ý của mình
                            System.out.println("Không tìm thấy bản ghi có email tương ứng.");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi truy vấn thất bại
                        System.out.println("Lỗi khi truy vấn Firestore: " + e.toString());
                    }
                });

        flightsCollection
                .whereEqualTo("fromLocation", AppUtil.ToLocation)
                .whereEqualTo("toLocation", AppUtil.FromLocation)
                .whereEqualTo("departureDay", AppUtil.backDay)
                .whereEqualTo("departureTime", AppUtil.departueTimeBack)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Lấy Document ID
                            String flId = document.getId();

                            for (int i = 0; i < AppUtil.SLVe; i++) {
                                int priceDetail = Integer.parseInt(String.valueOf(AppUtil.OriginalPriceDetailChieuVe)) + Integer.parseInt(String.valueOf(AppUtil.OriginalPriceDetailChieuVeTungNguoi[i]));
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("b_id", bid);
                                userMap.put("birthday", AppUtil.NgaySinhHK[i]);
                                userMap.put("ccid", AppUtil.CCCDHK[i]);
                                userMap.put("fly_id", flId);
                                userMap.put("kg_id", AppUtil.HLKGTungNguoiChieuVe[i]);
                                userMap.put("name", AppUtil.edtTTHKName[i]);
                                userMap.put("seat_id", AppUtil.GheDaChon[i]);
                                userMap.put("ticket_kind", AppUtil.ticketKind);
                                userMap.put("ticket_price", priceDetail);


                                // Increment the document ID for each new document
                                int documentId = startingDocumentId + i + 1;

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                CollectionReference ticketCollection = db.collection("TICKET");
                                // Use the incremented document ID for the document reference
                                DocumentReference documentReference = ticketCollection.document(String.valueOf(documentId));

                                // Set the data for the document
                                documentReference.set(userMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ticket_information.this, "User data added to TICKET collection.", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e("ticket_infomation", "Error adding user data to TICKET collection: " + e.getMessage());
                                                Toast.makeText(ticket_information.this, "Error adding user data to TICKET collection.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }

                        }
                    } else {
                        // Xử lý khi truy vấn không thành công
                        Exception exception = task.getException();
                        if (exception != null) {
                            exception.printStackTrace();
                        }
                    }
                });
    }

    private void sendPaymentFailureEmail() {
        try {
            String stringSenderEmail = "phucnguyen6009dh@gmail.com";
            String stringReceiverEmail = AppUtil.edtTTLHEmail;
            String stringPasswordSenderEmail = "ivwnpiguadnfktpa";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
                mimeMessage.setSubject("Flight Ticket Information");
                mimeMessage.setText("Xin chào " + AppUtil.edtTTHKName + ",\n\n" +
                        "Bạn thanh toán vé chưa thành công. Vui lòng đặt lại vé");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {

                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private void sendmail(){
        try {
            String stringSenderEmail = "phucnguyen6009dh@gmail.com";
            String stringReceiverEmail = AppUtil.edtTTLHEmail;
            String stringPasswordSenderEmail = "ivwnpiguadnfktpa";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

            if (AppUtil.KhuHoi == 0) {
                mimeMessage.setSubject("Flight Ticket Information");
                mimeMessage.setText("Hello " + AppUtil.edtTTHKName + ",\n\n" +
                        "Thank you for booking your flight with BlueStar Airlines. Below are the details of your flight:\n\n" +
                        "ID Ticket: " + AppUtil.idVe + "\n" +
                        "From: " + AppUtil.FromLocation + "\n" +
                        "To: " + AppUtil.ToLocation + "\n" +
                        "Departure Day: " + AppUtil.departureDay + "\n" +
                        "Departure Time: " + AppUtil.departueTime + "\n" +
                        "Arrival Time: " + AppUtil.arrivalTime + "\n" +
                        "Seat(s): " + Arrays.toString(AppUtil.GheDaChon) + "\n" +
                        "Ticket Type: " + AppUtil.ticketKind + "\n" +
                        "We look forward to serving you on board.\n\n" +
                        "Safe travels!");
            }
            else {
                mimeMessage.setSubject("Flight Ticket Information");
                mimeMessage.setText("Hello " + AppUtil.edtTTHKName + ",\n\n" +
                        "Thank you for booking your flight with BlueStar Airlines. Below are the details of your flight:\n\n" +
                        "ID Ticket: " + AppUtil.idVe + "\n" +
                        "From: " + AppUtil.FromLocation + "\n" +
                        "To: " + AppUtil.ToLocation + "\n" +
                        "Departure Day: " + AppUtil.departureDay + "\n" +
                        "Departure Time: " + AppUtil.departueTime + "\n" +
                        "Return Day: " + AppUtil.backDay + "\n" +
                        "Departure Time: " + AppUtil.departueTimeBack + "\n" +
                        "Seat(s): " + Arrays.toString(AppUtil.GheDaChon) + "\n" +
                        "Ticket Type: " + AppUtil.ticketKind + "\n" +
                        "We look forward to serving you on board.\n\n" +
                        "Safe travels!");
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {

                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
