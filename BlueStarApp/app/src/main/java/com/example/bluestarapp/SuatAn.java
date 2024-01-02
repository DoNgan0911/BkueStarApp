package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.A;

public class SuatAn extends AppCompatActivity {

    ImageView imageViewBack, imageViewUpComChienChay, imageViewDownComChienChay,imageViewUpComTam,imageViewDownComTam,imageViewUpMiY,imageViewDownMiY,imageViewUpBanhMi,imageViewDownBanhMi, imageViewUpComChienChayBack,imageViewDownComChienChayBack,imageViewUpComTamBack,imageViewDownComTamBack,imageViewUpMiYBack,imageViewDownMiYBack,imageViewUpBanhMiBack,imageViewDownBanhMiBack;
    TextView textViewSLComChienChay, textViewSLComTam,textViewSLMiY,textViewSLBanhMi;
    TextView textViewSLComChienChayBack, textViewSLComTamBack,textViewSLMiYBack,textViewSLBanhMiBack, textViewTongTien, fromLocation, toLocation, fromLocationBack, toLocationBack, hovaten, hovatenBack;

    TextView textViewTienChieuDi, textViewTienChieuVe;
    Button btnNext;
    int[] SLComChienChay = new int[0];
    int[] SLComTam = new int[0];
    int[] SLBanhMi= new int[0];
    int[] SLMiY = new int[0];
    int SLComChienChayBack = 0;
    int SLComTamBack = 0;
    int SLMiYBack = 0;
    int SLBanhMiBack = 0;

    int TongTien = Integer.parseInt(String.valueOf(AppUtil.OriginalPrice));
    LinearLayout linearLayout_Parent_SuatAn;
    int TienSuatAn = 0;

    int TienChieuDi  = 0;
    int TienChieuVe = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suat_an);

        linearLayout_Parent_SuatAn = findViewById(R.id.linearLayout_Parent_SuatAn);

        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        fromLocationBack = findViewById(R.id.fromLocationBack);
        toLocationBack = findViewById(R.id.toLocationBack);
        hovaten = findViewById(R.id.hovaten);


        imageViewUpComChienChay = findViewById(R.id.imageViewUpComChienChay);
        imageViewDownComChienChay = findViewById(R.id.imageViewDownComChienChay);
        imageViewUpComTam = findViewById(R.id.imageViewUpComTam);
        imageViewDownComTam = findViewById(R.id.imageViewDownComTam);
        imageViewUpMiY = findViewById(R.id.imageViewUpMiY);
        imageViewDownMiY = findViewById(R.id.imageViewDownMiY);
        imageViewUpBanhMi = findViewById(R.id.imageViewUpBanhMi);
        imageViewDownBanhMi = findViewById(R.id.imageViewDownBanhMi);


        textViewTongTien = findViewById(R.id.textViewTongTien);
        textViewSLComChienChay = findViewById(R.id.textViewSLComChienChay);
        textViewSLComTam = findViewById(R.id.textViewSLComTam);
        textViewSLMiY = findViewById(R.id.textViewSLMiY);
        textViewSLBanhMi = findViewById(R.id.textViewSLBanhMi);

        imageViewBack = findViewById(R.id.imageViewBack);
        btnNext = findViewById(R.id.btnNext);




        textViewTongTien.setText(String.valueOf(TongTien));




        int soLuongVe = AppUtil.SLVe;

        if (AppUtil.KhuHoi == 1) {
            SLBanhMi = new int[soLuongVe * 2];
            SLComChienChay = new int[soLuongVe * 2];
            SLComTam = new int[soLuongVe * 2];
            SLMiY = new int[soLuongVe * 2];
        }
        else {
            SLBanhMi = new int[soLuongVe];
            SLComChienChay = new int[soLuongVe];
            SLComTam = new int[soLuongVe];
            SLMiY = new int[soLuongVe];
        }
        for (int i = 0; i < soLuongVe; i++) {
            // Tạo mới layoutTTHK
            LinearLayout childLayoutSuatAnChieuDi = (LinearLayout) getLayoutInflater().inflate(R.layout.child_suatan_chieudi, null);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 16);  // Đặt khoảng cách dưới là 16dp

            childLayoutSuatAnChieuDi.setLayoutParams(layoutParams);



            childLayoutSuatAnChieuDi.setId(View.generateViewId());
            TextView fromLocation = childLayoutSuatAnChieuDi.findViewById(R.id.fromLocation);
            TextView toLocation = childLayoutSuatAnChieuDi.findViewById(R.id.toLocation);
            TextView hovaten = childLayoutSuatAnChieuDi.findViewById(R.id.hovaten);

            fromLocation.setText(AppUtil.FromLocation);
            toLocation.setText(AppUtil.ToLocation);


            linearLayout_Parent_SuatAn.addView(childLayoutSuatAnChieuDi);

        }

        for (int i = 0; i < soLuongVe;i++) {

            final int finalI = i;
            LinearLayout childLayout = (LinearLayout) linearLayout_Parent_SuatAn.getChildAt(i);
            TextView textViewSLBanhMi = childLayout.findViewById(R.id.textViewSLBanhMi);
            TextView textViewSLMiY = childLayout.findViewById(R.id.textViewSLMiY);
            TextView textViewSLComTam = childLayout.findViewById(R.id.textViewSLComTam);
            TextView textViewSLComChienChay = childLayout.findViewById(R.id.textViewSLComChienChay);

            ImageView imageViewDownBanhMi = childLayout.findViewById(R.id.imageViewDownBanhMi);
            ImageView imageViewDownMiY = childLayout.findViewById(R.id.imageViewDownMiY);
            ImageView imageViewDownComTam = childLayout.findViewById(R.id.imageViewDownComTam);
            ImageView imageViewDownComChienChay = childLayout.findViewById(R.id.imageViewDownComChienChay);
            ImageView imageViewUpBanhMi = childLayout.findViewById(R.id.imageViewUpBanhMi);
            ImageView imageViewUpMiY = childLayout.findViewById(R.id.imageViewUpMiY);
            ImageView imageViewUpComTam = childLayout.findViewById(R.id.imageViewUpComTam);
            ImageView imageViewUpComChienChay = childLayout.findViewById(R.id.imageViewUpComChienChay);

            SLMiY[finalI] = AppUtil.SLMiY[finalI];
            SLComTam[finalI] = AppUtil.SLComTam[finalI];
            SLBanhMi[finalI] = AppUtil.SLBanhMi[finalI];
            SLComChienChay[finalI] = AppUtil.SLComChienChay[finalI];
            textViewSLComChienChay.setText(String.valueOf(AppUtil.SLComChienChay[finalI]));
            textViewSLComTam.setText(String.valueOf(AppUtil.SLComTam[finalI]));
            textViewSLMiY.setText(String.valueOf(AppUtil.SLMiY[finalI]));
            textViewSLBanhMi.setText(String.valueOf(AppUtil.SLBanhMi[finalI]));

            imageViewUpComChienChay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SLComChienChay[finalI] += 1;
                    textViewSLComChienChay.setText(String.valueOf(SLComChienChay[finalI]));
                    AppUtil.SLComChienChay[finalI] +=1;
                    TongTien += 236000;
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewDownComChienChay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SLComChienChay[finalI] == 0) {
                        SLComChienChay[finalI] = 0;
                    }
                    else {
                        SLComChienChay[finalI] -= 1;
                        AppUtil.SLComChienChay[finalI] -=1;
                        TongTien -= 236000;
                    }
                    textViewSLComChienChay.setText(String.valueOf(SLComChienChay[finalI]));
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewUpComTam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SLComTam[finalI] += 1;
                    textViewSLComTam.setText(String.valueOf(SLComTam[finalI]));
                    AppUtil.SLComTam[finalI] +=1;
                    TongTien += 186000;
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewDownComTam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SLComTam[finalI] == 0) {
                        SLComTam[finalI] = 0;
                    }
                    else {
                        SLComTam[finalI] -= 1;
                        AppUtil.SLComTam[finalI] -=1;
                        TongTien -= 186000;
                    }
                    textViewSLComTam.setText(String.valueOf(SLComTam[finalI]));
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewUpMiY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SLMiY[finalI] += 1;
                    textViewSLMiY.setText(String.valueOf(SLMiY[finalI]));
                    AppUtil.SLMiY[finalI] +=1;
                    TongTien += 136000;
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewDownMiY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SLMiY[finalI] == 0) {
                        SLMiY[finalI] = 0;
                    }
                    else {
                        SLMiY[finalI] -= 1;
                        AppUtil.SLMiY[finalI] -=1;
                        TongTien -= 136000;
                    }
                    textViewSLMiY.setText(String.valueOf(SLMiY[finalI]));
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewUpBanhMi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SLBanhMi[finalI] += 1;
                    textViewSLBanhMi.setText(String.valueOf(SLBanhMi[finalI]));
                    AppUtil.SLBanhMi[finalI] +=1;
                    TongTien += 86000;
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });

            imageViewDownBanhMi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SLBanhMi[finalI] == 0) {
                        SLBanhMi[finalI] = 0;
                    }
                    else {
                        SLBanhMi[finalI] -= 1;
                        AppUtil.SLBanhMi[finalI] -=1;
                        TongTien -= 86000;
                    }
                    textViewSLBanhMi.setText(String.valueOf(SLBanhMi[finalI]));
                    textViewTongTien.setText(String.valueOf(TongTien));
                }
            });


        }




        if (AppUtil.KhuHoi == 1) {
            for (int i = 0; i < soLuongVe; i++) {
                LinearLayout childLayoutSuatAnChieuVe = (LinearLayout) getLayoutInflater().inflate(R.layout.child_suatan_chieuve, null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 0, 0, 16);  // Đặt khoảng cách dưới là 16dp

                childLayoutSuatAnChieuVe.setLayoutParams(layoutParams);



                childLayoutSuatAnChieuVe.setId(View.generateViewId());
                TextView fromLocation = childLayoutSuatAnChieuVe.findViewById(R.id.fromLocation);
                TextView toLocation = childLayoutSuatAnChieuVe.findViewById(R.id.toLocation);
                TextView hovaten = childLayoutSuatAnChieuVe.findViewById(R.id.hovaten);

                fromLocation.setText(AppUtil.FromLocation);
                toLocation.setText(AppUtil.ToLocation);


                linearLayout_Parent_SuatAn.addView(childLayoutSuatAnChieuVe);


            }

            for (int i = soLuongVe; i<soLuongVe*2;i++) {

                final int finalI = i;
                LinearLayout childLayout = (LinearLayout) linearLayout_Parent_SuatAn.getChildAt(i);

                TextView textViewSLBanhMiBack = childLayout.findViewById(R.id.textViewSLBanhMiBack);
                TextView textViewSLMiYBack = childLayout.findViewById(R.id.textViewSLMiYBack);
                TextView textViewSLComTamBack = childLayout.findViewById(R.id.textViewSLComTamBack);
                TextView textViewSLComChienChayBack = childLayout.findViewById(R.id.textViewSLComChienChayBack);

                ImageView imageViewDownBanhMiBack = childLayout.findViewById(R.id.imageViewDownBanhMiBack);
                ImageView imageViewDownMiYBack = childLayout.findViewById(R.id.imageViewDownMiYBack);
                ImageView imageViewDownComTamBack = childLayout.findViewById(R.id.imageViewDownComTamBack);
                ImageView imageViewDownComChienChayBack = childLayout.findViewById(R.id.imageViewDownComChienChayBack);
                ImageView imageViewUpBanhMiBack = childLayout.findViewById(R.id.imageViewUpBanhMiBack);
                ImageView imageViewUpMiYBack = childLayout.findViewById(R.id.imageViewUpMiYBack);
                ImageView imageViewUpComTamBack = childLayout.findViewById(R.id.imageViewUpComTamBack);
                ImageView imageViewUpComChienChayBack = childLayout.findViewById(R.id.imageViewUpComChienChayBack);

                SLMiY[finalI] = AppUtil.SLMiY[finalI];
                SLComTam[finalI] = AppUtil.SLComTam[finalI];
                SLBanhMi[finalI] = AppUtil.SLBanhMi[finalI];
                SLComChienChay[finalI] = AppUtil.SLComChienChay[finalI];

                textViewSLComChienChayBack.setText(String.valueOf(AppUtil.SLComChienChay[finalI]));
                textViewSLComTamBack.setText(String.valueOf(AppUtil.SLComTam[finalI]));
                textViewSLMiYBack.setText(String.valueOf(AppUtil.SLMiY[finalI]));
                textViewSLBanhMiBack.setText(String.valueOf(AppUtil.SLBanhMi[finalI]));

                imageViewUpComChienChayBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SLComChienChay[finalI] += 1;
                        textViewSLComChienChayBack.setText(String.valueOf(SLComChienChay[finalI]));
                        AppUtil.SLComChienChay[finalI] +=1;
                        TongTien += 236000;
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewDownComChienChayBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SLComChienChay[finalI] == 0) {
                            SLComChienChay[finalI] = 0;
                        }
                        else {
                            SLComChienChay[finalI] -= 1;
                            AppUtil.SLComChienChay[finalI] -=1;
                            TongTien -= 236000;
                        }
                        textViewSLComChienChayBack.setText(String.valueOf(SLComChienChay[finalI]));
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewUpComTamBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SLComTam[finalI] += 1;
                        textViewSLComTamBack.setText(String.valueOf(SLComTam[finalI]));
                        AppUtil.SLComTam[finalI] +=1;
                        TongTien += 186000;
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewDownComTamBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SLComTam[finalI] == 0) {
                            SLComTam[finalI] = 0;
                        }
                        else {
                            SLComTam[finalI] -= 1;
                            AppUtil.SLComTam[finalI] -=1;
                            TongTien -= 186000;
                        }
                        textViewSLComTamBack.setText(String.valueOf(SLComTam[finalI]));
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewUpMiYBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SLMiY[finalI] += 1;
                        textViewSLMiYBack.setText(String.valueOf(SLMiY[finalI]));
                        AppUtil.SLMiY[finalI] +=1;
                        TongTien += 136000;
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewDownMiYBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SLMiY[finalI] == 0) {
                            SLMiY[finalI] = 0;
                        }
                        else {
                            SLMiY[finalI] -= 1;
                            AppUtil.SLMiY[finalI] -=1;
                            TongTien -= 136000;
                        }
                        textViewSLMiYBack.setText(String.valueOf(SLMiY[finalI]));
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewUpBanhMiBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SLBanhMi[finalI] += 1;
                        textViewSLBanhMiBack.setText(String.valueOf(SLBanhMi[finalI]));
                        AppUtil.SLBanhMi[finalI] +=1;
                        TongTien += 86000;
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

                imageViewDownBanhMiBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SLBanhMi[finalI] == 0) {
                            SLBanhMi[finalI] = 0;
                        }
                        else {
                            SLBanhMi[finalI] -= 1;
                            AppUtil.SLBanhMi[finalI] -=1;
                            TongTien -= 86000;
                        }
                        textViewSLBanhMiBack.setText(String.valueOf(SLBanhMi[finalI]));
                        textViewTongTien.setText(String.valueOf(TongTien));
                    }
                });

            }

        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int price = (TongTien - Integer.parseInt(String.valueOf(AppUtil.OriginalPrice)));
                if (AppUtil.KhuHoi == 1 ) {
                    price /= 2;
                }
                for (int i = 0; i< AppUtil.SLVe; i++) {
                    int priceDetail = AppUtil.OriginalPriceDetailChieuDiTungNguoi[i] + price / AppUtil.SLVe;
                    AppUtil.OriginalPriceDetailChieuDiTungNguoi[i] = priceDetail;
                }
                for (int i = 0; i< AppUtil.SLVe; i++) {
                    int priceDetail = AppUtil.OriginalPriceDetailChieuVeTungNguoi[i] + price / AppUtil.SLVe;
                    AppUtil.OriginalPriceDetailChieuVeTungNguoi[i] = priceDetail;
                }

                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(SuatAn.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.OriginalPrice = String.valueOf(TongTien);
                Intent myintent = new Intent(SuatAn.this, MuaThemDichVu.class);
                startActivity(myintent);
            }
        });


    }
}