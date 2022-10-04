package com.example.bloodbankadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeAdmin extends AppCompatActivity {
    private CardView quantity;
    private CardView usersData;
    private CardView bloodBank;
    private CardView hospital;
    private CardView profile;
    private CardView addAdmins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        quantity = findViewById(R.id.card_view_quantity);
        usersData = findViewById(R.id.card_view_user);
        bloodBank = findViewById(R.id.card_view_blood_bank);
        hospital = findViewById(R.id.card_view_hospital);
        profile = findViewById(R.id.card_view_user_profile);
        addAdmins= findViewById(R.id.card_view_add_admin);

        quantity.setOnClickListener(v -> {
            startActivity(new Intent(HomeAdmin.this,BloodQuantity.class));
        });

        usersData.setOnClickListener(v -> {
            startActivity(new Intent(HomeAdmin.this,UserDataView.class));
        });

        bloodBank.setOnClickListener(v -> {
            startActivity(new Intent(HomeAdmin.this, BloodBankDataViewer.class));
        });

        hospital.setOnClickListener(v -> {
            startActivity(new Intent(HomeAdmin.this,HospitalDataViewer.class));
        });

        profile.setOnClickListener(v -> {
            startActivity(new Intent(HomeAdmin.this,AddAdmin.class));
        });

        addAdmins.setOnClickListener(v->{
            startActivity(new Intent(HomeAdmin.this,AddAdmin.class));
        });
    }
}