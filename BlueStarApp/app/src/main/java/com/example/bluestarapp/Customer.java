package com.example.bluestarapp;

public class Customer {
//    private String c_id;
    private String num_id;
    private String full_name;
    private int point;
    private String account_id;

    public Customer( String num_id, String full_name, int point, String account_id) {
//        this.c_id = c_id;
        this.num_id = num_id;
        this.full_name = full_name;
        this.point = point;
        this.account_id = account_id;
    }

    public Customer() {
    }
//
//    public String getC_id() {
//        return c_id;
//    }
//
//    public void setC_id(String c_id) {
//        this.c_id = c_id;
//    }

    public String getNum_id() {
        return num_id;
    }

    public void setNum_id(String num_id) {
        this.num_id = num_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}

