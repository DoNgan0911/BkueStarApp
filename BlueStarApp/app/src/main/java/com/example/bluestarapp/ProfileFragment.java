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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    EditText email_et, password_et;
    Button btn_signin;
    FirebaseAuth mAuth;
    TextView signup_tv, forgot_password;


    TextView fullname, pointEditText;
    TextView telEditText, emailEditText;
    Button button_capnhat, button_logout;

    FirebaseAuth auth;
    //    Button button;
//    TextView textView;
    FirebaseUser user;
    private boolean isLoggedIn = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view;

        // Kiểm tra người dùng có đăng nhập chưa?????
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            fullname = view.findViewById(R.id.fullname);
            pointEditText = view.findViewById(R.id.pointEditText);
            telEditText = view.findViewById(R.id.telEditText);

            emailEditText = view.findViewById(R.id.emailEditText);
            emailEditText.setText(currentUser.getEmail());

//            XỬ LÍ FIREBASE lấy các thông tin tên, điểm, email, số cccd;
            button_capnhat = view.findViewById(R.id.button_capnhap);

//            Xử lý đăng xuất
            button_logout = view.findViewById(R.id.button_logout);
            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();

            retrieveCCCDFromFirestore(currentUser.getUid());
            button_capnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), EditProfile.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(myIntent);
                }
            });
            button_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {FirebaseAuth.getInstance().signOut();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // Đảm bảo rằng ProfileFragment đã được khởi tạo và thêm vào trước đó
                    ProfileFragment profileFragment = new ProfileFragment();

                    // Thay đổi Fragment hiện tại thành ProfileFragment
                    fragmentTransaction.replace(R.id.framelayout, profileFragment);
                    fragmentTransaction.addToBackStack(null);  // Nếu bạn muốn thêm vào Stack Back
                    fragmentTransaction.commit();
                }
            });
        } else {
            // Nếu chưa đăng nhập, trả về  activity SignIn
            view = inflater.inflate(R.layout.activity_sign_in, container, false);
            mAuth = FirebaseAuth.getInstance();
            email_et = view.findViewById(R.id.email_signin);
            password_et = view.findViewById(R.id.pass_signin);
            btn_signin = view.findViewById(R.id.btn_signin);
//            signup_tv = view.findViewById(R.id.click_signup);
            forgot_password = view.findViewById(R.id.forgot_pass);
            forgot_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ForgotPasswordFragment forgotPassFragment = new ForgotPasswordFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    // Thay thế fragment hiện tại bằng ForgotPassFragment
                    transaction.replace(R.id.framelayout, forgotPassFragment); // R.id.fragment_container là ID của layout chứa fragment trong activity của bạn
                    transaction.addToBackStack(null); // Để có thể nhấn nút back để trở về
                    transaction.commit();
                }
            });

//            signup_tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(getActivity(), SignUpActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
//            });
            btn_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email, password;
                    email = String.valueOf(email_et.getText().toString());
                    password = String.valueOf((password_et.getText().toString()));
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getActivity(), "Enter email", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getActivity(), "Enter password", Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            String userEmail = mAuth.getCurrentUser().getEmail();
                                            AppUtil.edtSignInEmail = userEmail;
                                            Toast.makeText(getActivity(), "Authentication successed.",
                                                    Toast.LENGTH_SHORT).show();

                                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                            // Đảm bảo rằng ProfileFragment đã được khởi tạo và thêm vào trước đó
                                            ProfileFragment profileFragment = new ProfileFragment();

                                            // Thay đổi Fragment hiện tại thành ProfileFragment
                                            fragmentTransaction.replace(R.id.framelayout, profileFragment);
                                            fragmentTransaction.addToBackStack(null);  // Nếu bạn muốn thêm vào Stack Back
                                            fragmentTransaction.commit();

                                        } else {
                                            Toast.makeText(getActivity(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
        }

        return view;

    }
    private void retrieveCCCDFromFirestore(String uid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get reference to the "customer" collection
        db.collection("CUSTOMER")
                .whereEqualTo("account_id", uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                // Retrieve the CCCD from the document
                                String cccd = document.getString("num_id");
                                String fullName = document.getString("full_name");
                                Integer points = document.getLong("point").intValue();
                                String pointsString = String.valueOf(points);

                                telEditText.setText(cccd);
                                fullname.setText(fullName);
                                pointEditText.setText(pointsString);

                            }
                        } else {
                            // Handle errors
                        }
                    }
                });
    }
}