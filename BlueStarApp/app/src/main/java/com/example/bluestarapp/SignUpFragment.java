package com.example.bluestarapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    EditText numberid_et, fullname_et, email_et, password_et;
    Button btn_signup;
    FirebaseAuth mAuth;
    TextView login_tv;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mAuth = FirebaseAuth.getInstance();
        numberid_et = view.findViewById(R.id.numberid_sigup);
        fullname_et = view.findViewById(R.id.fullname_signup);
        email_et = view.findViewById(R.id.email_signup);
        password_et = view.findViewById(R.id.pass_signup);
        btn_signup = view.findViewById(R.id.btn_signup);
//        login_tv = view.findViewById(R.id.click_signin);
//
//        login_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                String number_id, fullname;
                email = String.valueOf(email_et.getText().toString());
                password = String.valueOf((password_et.getText().toString()));
                number_id = String.valueOf(numberid_et.getText().toString());
                fullname = String.valueOf(fullname_et.getText().toString());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(number_id) || number_id.length() != 12 ) {
                    Toast.makeText(getActivity(), "Enter number_id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(getActivity(), "Enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.endsWith("@gmail.com")) {
                    Toast.makeText(getActivity(), "Email is wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.collection("CUSTOMER")
                        .whereEqualTo("num_id", number_id)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().isEmpty()) {
                                        mAuth.createUserWithEmailAndPassword(email, password)
                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                                            String uid = currentUser.getUid();
                                                            String userFullname = fullname;
                                                            String userNumberId = number_id;
                                                            Map<String, Object> userMap = new HashMap<>();
                                                            userMap.put("account_id", uid);
                                                            userMap.put("full_name", userFullname);
                                                            userMap.put("num_id", userNumberId);
                                                            userMap.put("email", email);
                                                            userMap.put("point", 0);

                                                            CollectionReference customerCollection = db.collection("CUSTOMER");
                                                            // Query the collection to get the count of documents
                                                            customerCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                    if (task.isSuccessful()) {

                                                                        int documentCount = task.getResult().size();
                                                                        // Set the next available documentId
                                                                        String documentId = String.valueOf(documentCount + 1);
                                                                        // Rest of your code with the dynamically set documentId
                                                                        db.collection("CUSTOMER")
                                                                                .document(documentId)
                                                                                .set(userMap)
                                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                    @Override
                                                                                    public void onSuccess(Void aVoid) {
                                                                                        Toast.makeText(getActivity(), "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                                                                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                                                                        // Đảm bảo rằng ProfileFragment đã được khởi tạo và thêm vào trước đó
                                                                                        SignUpFragment signUpFragment = new SignUpFragment();

                                                                                        // Thay đổi Fragment hiện tại thành ProfileFragment
                                                                                        fragmentTransaction.replace(R.id.framelayout, signUpFragment);
                                                                                        fragmentTransaction.addToBackStack(null);  // Nếu bạn muốn thêm vào Stack Back
                                                                                        fragmentTransaction.commit();

                                                                                    }
                                                                                })
                                                                                .addOnFailureListener(new OnFailureListener() {
                                                                                    @Override
                                                                                    public void onFailure(@NonNull Exception e) {
                                                                                        Log.e("SignUpActivity", "Error adding user data to CUSTOMER collection: " + e.getMessage());
                                                                                        Toast.makeText(getActivity(), "Error adding user data to CUSTOMER collection.", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                });
                                                                    } else {
                                                                        // If sign in fails, display a message to the user.
//                                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        if (e instanceof FirebaseAuthUserCollisionException) {
                                                            // Nếu là ngoại lệ do email đã được sử dụng, thông báo cho người dùng
                                                            Toast.makeText(getActivity(), "The email address is already in use by another account.", Toast.LENGTH_SHORT).show();
                                                            Log.e("SignUpFragment", "Error signing up: Email is already in use.", e);
                                                        } else {
                                                            // Xử lý các ngoại lệ khác
                                                            Log.e("SignUpFragment", "Error signing up: " + e.getMessage(), e);
                                                            Toast.makeText(getActivity(), "Error signing up: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }) ;
                                    } else {
                                        // Nếu number_id đã tồn tại, hiển thị thông báo cho người dùng
                                        Toast.makeText(getActivity(), "Number ID is already in use.", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } else {
                                    // Xử lý lỗi khi truy vấn Firestore
                                    Log.e("SignUpFragment", "Error querying CUSTOMER collection: " + task.getException().getMessage());
                                    Toast.makeText(getActivity(), "Error querying CUSTOMER collection.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        }); // <-- This was missing

        return view;
    }
}