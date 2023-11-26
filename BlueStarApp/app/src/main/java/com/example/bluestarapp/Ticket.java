package com.example.bluestarapp;

public class Ticket {
    private String t_id;
    private String ccid;
    private String name;
    private String fly_id;
    private String kg_id;
    private String seat_id;
    private String food_id;
    private int ticket_price;
    private String mail;
    private String dis_id;
    public Ticket(){
    }
    public Ticket(String t_id,String ccid, String name,String fly_id,String kg_id,String seat_id,String food_id, int ticket_price, String mail, String dis_id)
    {
        this.t_id=t_id;
        this.ccid=ccid;
        this.name=name;
        this.fly_id=fly_id;
        this.kg_id=kg_id;
        this.seat_id=seat_id;
        this.food_id=food_id;
        this.ticket_price=ticket_price;
        this.mail=mail;
        this.dis_id=dis_id;
    }
    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFly_id() {
        return fly_id;
    }

    public void setFly_id(String fly_id) {
        this.fly_id = fly_id;
    }

    public String getKg_id() {
        return kg_id;
    }

    public void setKg_id(String kg_id) {
        this.kg_id = kg_id;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public int getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDis_id() {
        return dis_id;
    }

    public void setDis_id(String dis_id) {
        this.dis_id = dis_id;
    }
}
