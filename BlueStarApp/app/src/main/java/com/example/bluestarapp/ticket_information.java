package com.example.bluestarapp;

//import android.content.Intent;

import android.annotation.SuppressLint;

import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
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




    @SuppressLint("MissingInflatedId")
public class ticket_information extends AppCompatActivity {
        Button trangchu;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ticket_information);
            trangchu = findViewById(R.id.trangchu);
            // Get the Flight object from the intent

            Flight flight = (Flight) getIntent().getSerializableExtra("flight");
            Ticket ticket = (Ticket) getIntent().getSerializableExtra("ticket");

            // Set values to TextViews
            TextView fromTextView = findViewById(R.id.from);
            fromTextView.setText(AppUtil.FromLocation);

            TextView timeGoTextView = findViewById(R.id.timego);
            timeGoTextView.setText(AppUtil.departueTime);

            TextView nameOfPassengerTextView = findViewById(R.id.nameofpassager);

            // Set your passenger name here
// phải comment lại mới được
//        nameOfPassengerTextView.setText(AppUtil.edtTTHKName); // Set your passenger name here


            //TextView foodTextView = findViewById(R.id.food);
            //foodTextView.setText("...");


            //TextView kgTextView = findViewById(R.id.kg);
            //kgTextView.setText("..."); // Set your kg here

            TextView toWhereTextView = findViewById(R.id.towhere);
            toWhereTextView.setText(AppUtil.ToLocation);

            TextView timeArriveTextView = findViewById(R.id.timearrive);
            timeArriveTextView.setText(AppUtil.arrivalTime);

            TextView seatTextView = findViewById(R.id.seat);
            seatTextView.setText(Arrays.toString(AppUtil.GheDaChon));

            TextView typeOfTicketTextView = findViewById(R.id.typeofticket);
            typeOfTicketTextView.setText(AppUtil.ticketKind); // Set your ticket type here

            //test_sent_mail.setOnClickListener(new View.OnClickListener() {

            //@Override
            //public void onClick(View v) {
            //Intent myintent = new Intent(MuaThemDichVu.this, TicketReview.class);
            //Intent myintent = new Intent(ticket_information.this, Send_mail.class);
            //startActivity(myintent);
            //}

            //@Override
            //public void onClick(View v) {
            //Intent myintent = new Intent(MuaThemDichVu.this, TicketReview.class);
            //Intent myintent = new Intent(ticket_information.this, Send_mail.class);
            //startActivity(myintent);
            //}

            //});
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
                mimeMessage.setText("Hello " + AppUtil.edtTTHKName + ",\n\n" +
                        "Thank you for booking your flight with BlueStar Airlines. Below are the details of your flight:\n\n" +
                        "ID Ticket: " + "Tố sửa cái này thành cái id ticket á" + "\n" +
                        "From: " + AppUtil.FromLocation + "\n" +
                        "To: " + AppUtil.ToLocation + "\n" +
                        "Departure Time: " + AppUtil.departueTime + "\n" +
                        "Arrival Time: " + AppUtil.arrivalTime + "\n" +
                        "Seat(s): " + Arrays.toString(AppUtil.GheDaChon) + "\n" +
                        "Ticket Type: " + AppUtil.ticketKind + "\n" +
                        "We look forward to serving you on board.\n\n" +
                        "Safe travels!");

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
