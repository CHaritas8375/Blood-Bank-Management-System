package com.example.bloodbankadmin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.login_btn).setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,HomeAdmin.class);
            startActivity(intent);
        });
    }
}