package com.example.bluestarapp;

import java.util.ArrayList;
import java.util.List;

public class AppUtil {

    public static String FromLocation = "Hà Nội";
    public static String ToLocation = "TPHCM";
    public static String OriginalPrice = "";
    public static String edtTTLHEmail="";
    public static String edtTTHKName = "";
    public static String departureDay = "";
    public static String departueTime = "";
    public static String arrivalTime = "";
    public static String backDay = "";
    public static String departueTimeBack = "";
    public static String arrivalTimeBack = "";
    public static String ticketKind = "";
    public static int SLVe = 0;
    public static String[] GheDaChon = new String[0];
    public static void capNhatKichThuocDanhSach(int soLuongVe) {
        GheDaChon = new String[soLuongVe];
        for (int i = 0; i<soLuongVe; i++){
            GheDaChon[i] = "";
        }
    }
    public static int KhuHoi = 0;

    public static String GioiTinh = "";
    public static String NgaySinhHK = "";

    public static int SLComChienChay = 0;
    public static int SLComTam = 0;
    public static int SLMiY = 0;
    public static int SLBanhMi = 0;
    public static int SLComChienChayBack = 0;
    public static int SLComTamBack = 0;
    public static int SLMiYBack = 0;
    public static int SLBanhMiBack = 0;


}
