package com.example.bluestarapp;

import java.util.ArrayList;
import java.util.List;

public class AppUtil {

    public static String FromLocation = "Lâm Đồng";
    public static String ToLocation = "Bình Định";
    public static String OriginalPrice = "";
    public static String edtTTLHEmail="";

    public static String[] edtTTHKName = new String[0];
    public static void capNhatKichThuocTTHKName(int soLuongVe) {
        edtTTHKName = new String[soLuongVe];
        for (int i = 0; i<soLuongVe; i++){
            edtTTHKName[i] = "";
        }
    }

    public static String departureDay = "";
    public static String departueTime = "";
    public static String arrivalTime = "";
    public static String backDay = "";
    public static String departueTimeBack = "";
    public static String arrivalTimeBack = "";
    public static String ticketKind = "";
    public static int SLVe = 0;
    public static String[] KG = new String[0];
    public static void capNhatKichThuocKG(int soLuongVe) {
        if (KhuHoi == 1) {
            KG = new String[soLuongVe * 2];
            for (int i = 0; i<soLuongVe*2; i++){
                KG[i] = "";
            }
        }
        else {
            KG = new String[soLuongVe];
            for (int i = 0; i<soLuongVe; i++){
                KG[i] = "";
            }
        }


    }
    public static String[] KGBack = new String[0];
    public static void capNhatKichThuocKGBack(int soLuongVe) {
        KGBack = new String[soLuongVe];
        for (int i = 0; i<soLuongVe; i++){
            KGBack[i] = "";
        }
    }
    public static String[] GheDaChon = new String[0];
    public static void capNhatKichThuocGheDaChon(int soLuongVe) {
        GheDaChon = new String[soLuongVe];
        for (int i = 0; i<soLuongVe; i++){
            GheDaChon[i] = "";
        }
    }
    public static int KhuHoi = 0;

    public static String[] GioiTinh = new String[0];
    public static void capNhatKichThuocGioiTinh(int soLuongVe) {
        GioiTinh = new String[soLuongVe];
        for (int i = 0; i<soLuongVe; i++){
            GioiTinh[i] = "";
        }
    }
    public static String[] NgaySinhHK = new String[0];
    public static void capNhatKichThuocNgaySinhHK(int soLuongVe) {
        NgaySinhHK = new String[soLuongVe];
        for (int i = 0; i<soLuongVe; i++){
            NgaySinhHK[i] = "";
        }
    }

    public static int[] SLComChienChay = new int[0];
    public static void capNhatKichThuocSLComChienChay(int soLuongVe) {
        if (KhuHoi == 1) {
            SLComChienChay = new int[soLuongVe * 2];
            for (int i = 0; i<soLuongVe*2; i++){
                SLComChienChay[i] = 0;
            }
        }
        else {
            SLComChienChay = new int[soLuongVe];
            for (int i = 0; i<soLuongVe; i++){
                SLComChienChay[i] = 0;
            }
        }


    }
    public static int[] SLComTam = new int[0];
    public static void capNhatKichThuocSLComTam(int soLuongVe) {
        if (KhuHoi == 1) {
            SLComTam = new int[soLuongVe * 2];
            for (int i = 0; i<soLuongVe*2; i++){
                SLComTam[i] = 0;
            }
        }
        else {
            SLComTam = new int[soLuongVe];
            for (int i = 0; i<soLuongVe; i++){
                SLComTam[i] = 0;
            }
        }
    }
    public static int[] SLBanhMi = new int[0];
    public static void capNhatKichThuocSLBanhMi(int soLuongVe) {
        if (KhuHoi == 1) {
            SLBanhMi = new int[soLuongVe * 2];
            for (int i = 0; i<soLuongVe*2; i++){
                SLBanhMi[i] = 0;
            }
        }
        else {
            SLBanhMi = new int[soLuongVe];
            for (int i = 0; i<soLuongVe; i++){
                SLBanhMi[i] = 0;
            }
        }
    }
    public static int[] SLMiY = new int[0];
    public static void capNhatKichThuocSLMiY(int soLuongVe) {
        if (KhuHoi == 1) {
            SLMiY = new int[soLuongVe * 2];
            for (int i = 0; i<soLuongVe*2; i++){
                SLMiY[i] = 0;
            }
        }
        else {
            SLMiY = new int[soLuongVe];
            for (int i = 0; i<soLuongVe; i++){
                SLMiY[i] = 0;
            }
        }
    }
    public static int SLComChienChayBack = 0;
    public static int SLComTamBack = 0;
    public static int SLMiYBack = 0;
    public static int SLBanhMiBack = 0;


}
