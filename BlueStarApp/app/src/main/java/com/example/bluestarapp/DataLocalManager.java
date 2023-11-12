package com.example.bluestarapp;

import android.content.Context;

import java.util.Set;

public class DataLocalManager {
    private  static final String PREF_FIRST_INSTALL_I = "PREF_FIRST_INSTALL_I";
    private  static final String PREF_FIRST_INSTALL_J = "PREF_FIRST_INSTALL_J";
    private  static final String GHE_NGOI = "GHE_NGOI";
    private  static final String PREF_STATUS_I = "PREF_STATUS_I";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;
    public static  void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }
    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static void setIntStatusI(int intStatusI){
        DataLocalManager.getInstance().mySharedPreferences.putIntValueI(PREF_FIRST_INSTALL_I, intStatusI);
    }
    public static int getIntStatusI(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValueI(PREF_FIRST_INSTALL_I);
    }

    public static void setIntStatusJ(int intStatusJ){
        DataLocalManager.getInstance().mySharedPreferences.putIntValueJ(PREF_FIRST_INSTALL_J, intStatusJ);
    }
    public static int getIntStatusJ(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValueJ(PREF_FIRST_INSTALL_J);
    }

    public static void setStringGheNgoi(String stringGheNgoi){
        DataLocalManager.getInstance().mySharedPreferences.putStringGheNgoi(GHE_NGOI, stringGheNgoi);
    }
    public static String getStringGheNgoi(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringGheNgoi(GHE_NGOI);
    }

    public static void setStatusI(Set<String> statusI){
        DataLocalManager.getInstance().mySharedPreferences.putStringSetValue(PREF_STATUS_I, statusI);
    }
    public static Set<String> getStatusI(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringSetValue(PREF_STATUS_I);
    }


}
