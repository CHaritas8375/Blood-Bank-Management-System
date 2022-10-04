package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    private CardView donateBlood;
    private CardView receiveBlood;
    private CardView bloodBank;
    private CardView hospital;
    private CardView profile;
    private CardView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        donateBlood = findViewById(R.id.card_view_donor);
        receiveBlood = findViewById(R.id.card_view_receiver);
        bloodBank = findViewById(R.id.card_view_blood_bank);
        hospital = findViewById(R.id.card_view_hospital);
        profile = findViewById(R.id.card_view_profile);
        info = findViewById(R.id.card_view_info);


        donateBlood.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, ReceiversDataViewer.class);
            startActivity(i);
        });

        receiveBlood.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, DonorsDataViewer.class);
            startActivity(i);
        });

        bloodBank.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, BloodBankViewer.class);
            startActivity(i);
        });

        hospital.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, HospitalViewer.class);
            startActivity(i);
        });

        profile.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, ProfileView.class);
            startActivity(i);
        });

        info.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, ProfileView.class);
            startActivity(i);
        });
    }
}